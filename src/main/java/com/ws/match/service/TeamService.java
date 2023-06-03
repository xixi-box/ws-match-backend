package com.ws.match.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ws.match.model.domain.Team;
import com.ws.match.model.domain.User;
import com.ws.match.model.dto.TeamQuery;
import com.ws.match.model.request.TeamAddRequest;
import com.ws.match.model.request.TeamJoinRequest;
import com.ws.match.model.request.TeamQuitRequest;
import com.ws.match.model.request.TeamUpdateRequest;
import com.ws.match.model.vo.TeamUserVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 26062
 * @description 针对表【team(队伍)】的数据库操作Service
 * @createDate 2023-06-01 23:10:52
 */
public interface TeamService extends IService<Team> {

    @Transactional(rollbackFor = Exception.class)/*指定异常*/
    long addTeam(Team team, User loginUser);

    List<TeamUserVO> listTeams(TeamQuery teamQuery, boolean isAdmin);

    boolean updateTeam(TeamUpdateRequest teamUpdateRequest, User loginUser);

    boolean joinTeam(TeamJoinRequest teamJoinRequest, User loginUser);

    @Transactional(rollbackFor = Exception.class)
    boolean quitTeam(TeamQuitRequest teamQuitRequest, User loginUser);

    @Transactional(rollbackFor = Exception.class)
    boolean deleteTeam(long id, User loginUser);
}
