package com.ws.match.controller;

import com.ws.match.common.BaseResponse;
import com.ws.match.common.ErrorCode;
import com.ws.match.common.ResultUtils;
import com.ws.match.exception.BusinessException;
import com.ws.match.model.domain.Team;
import com.ws.match.service.TeamService;
import com.ws.match.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author: 王顺
 * @Date: 2023/6/1 23:23
 * @Version 1.0
 */
@RestController
@RequestMapping("/team")
@CrossOrigin(origins = {"http://localhost:5173"})
@Slf4j
@SuppressWarnings("all")
public class TeamController {
    @Resource
    private UserService userService;
    @Resource
    private TeamService teamService;

    @PostMapping("/add")
    public BaseResponse<Long> addTeam(@RequestBody Team team) {

        if (team == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);

        }
        boolean save = teamService.save(team);
        if (!save) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "插入失败");
        }
        return ResultUtils.success(team.getId());

    }

    /**
     * 删除
     *
     * @param team
     * @return
     */
    @PostMapping("/delte")
    public BaseResponse<Boolean> deleteTeam(@RequestBody Team team) {

        if (team == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);

        }
        boolean b = teamService.removeById(team.getId());

        if (!b) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "删除失败");
        }
        return ResultUtils.success(true);

    }

    @PostMapping("/update")
    public BaseResponse<Boolean> updateTeam(@RequestBody Team team) {
        if (team == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);

        }
        boolean result = teamService.updateById(team);
        if (!result) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "更新失败");
        }
        return ResultUtils.success(true);

    }

    /**
     * @param team
     * @return
     * @Description 根据ID更新数据
     */
    @GetMapping("/get")
    public BaseResponse<Team> getTeam(@RequestBody Team team) {
        if (team == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);

        }
        Team teamById = teamService.getById(team.getId());
        if (teamById == null) {
            throw new BusinessException(ErrorCode.NULL_ERROR);
        }
        return ResultUtils.success(teamById);
    }

    /**
     * @param team
     * @return
     * @Description 查询所有数据
     */
    @GetMapping("/list")
    public BaseResponse<Team> getTeam(@RequestBody Team team) {
        if (team == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);

        }
        Team teamById = teamService.getById(team.getId());
        if (teamById == null) {
            throw new BusinessException(ErrorCode.NULL_ERROR);
        }
        return ResultUtils.success(teamById);
    }

    @GetMapping("/get")
    public BaseResponse<Team> getTeam(@RequestBody Team team) {
        if (team == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);

        }
        Team teamById = teamService.getById(team.getId());
        if (teamById == null) {
            throw new BusinessException(ErrorCode.NULL_ERROR);
        }
        return ResultUtils.success(teamById);
    }

}
