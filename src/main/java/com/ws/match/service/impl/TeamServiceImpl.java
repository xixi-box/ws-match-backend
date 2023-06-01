package com.ws.match.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ws.match.mapper.TeamMapper;
import com.ws.match.model.domain.Team;
import com.ws.match.service.TeamService;
import org.springframework.stereotype.Service;

/**
* @author 26062
* @description 针对表【team(队伍)】的数据库操作Service实现
* @createDate 2023-06-01 23:10:52
*/
@Service
public class TeamServiceImpl extends ServiceImpl<TeamMapper, Team>
    implements TeamService {

}




