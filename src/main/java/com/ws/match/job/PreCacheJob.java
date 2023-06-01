package com.ws.match.job;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.match.model.domain.User;
import com.ws.match.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author: 王顺
 * @Date: 2023/5/31 15:09
 * @Version 1.0
 * @Description 缓存预热任务
 */


@Slf4j
@Component
public class PreCacheJob {
    @Resource
    private UserService userService;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    private List<Long> mainUserList = Arrays.asList(1L);

    @Resource
    private RedissonClient redissonClient;

    @Scheduled(cron = "0 3 0 * * *")
    public void doCacheRecommendUser() {
        RLock lock = redissonClient.getLock("match:precachejob:docache:lock");

        try {
            //只有一个线程能获取锁
            //过期时间是30秒 不写默认就是30秒
            if (lock.tryLock(0, 30000, TimeUnit.MILLISECONDS)) {
                for (Long userId : mainUserList) {
                    //查数据库
                    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
                    Page<User> userPage = userService.page(new Page<>(1, 20), queryWrapper);

                    //查缓存
                    String redisKey = String.format("match:user:recommend:%s", userId);
                    ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
                    // 写缓存
                    try {
                        valueOperations.set(redisKey, userPage, 30000, TimeUnit.MILLISECONDS);
                    } catch (Exception e) {
                        log.error("redis set key error", e);
                    }

                }
            }
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        } finally {
            //无论如何也要释放锁
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }
}
