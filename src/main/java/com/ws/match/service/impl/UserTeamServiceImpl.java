package com.ws.match.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ws.match.mapper.UserTeamMapper;
import com.ws.match.model.domain.UserTeam;

import com.ws.match.service.UserTeamService;
import org.springframework.stereotype.Service;

/**
* @author 26062
* @description 针对表【user_team(用户队伍关系)】的数据库操作Service实现
* @createDate 2023-06-01 23:12:33
*/
@Service
public class UserTeamServiceImpl extends ServiceImpl<UserTeamMapper, UserTeam>
    implements UserTeamService {
}




