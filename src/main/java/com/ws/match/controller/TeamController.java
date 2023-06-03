package com.ws.match.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.match.common.BaseResponse;
import com.ws.match.common.ErrorCode;
import com.ws.match.common.ResultUtils;
import com.ws.match.exception.BusinessException;
import com.ws.match.model.domain.Team;
import com.ws.match.model.domain.User;
import com.ws.match.model.dto.TeamQuery;
import com.ws.match.model.request.TeamAddRequest;
import com.ws.match.model.request.TeamJoinRequest;
import com.ws.match.model.request.TeamUpdateRequest;
import com.ws.match.model.vo.TeamUserVO;
import com.ws.match.service.TeamService;
import com.ws.match.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    public BaseResponse<Long> addTeam(@RequestBody TeamAddRequest teamAddRequest, HttpServletRequest request) {
        if (teamAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);

        }
        Team team = new Team();
        BeanUtils.copyProperties(teamAddRequest, team);
        User loginUser = userService.getLoginUser(request);
        long id = teamService.addTeam(team, loginUser);
        return ResultUtils.success(id);

    }

    /**
     * 删除
     *
     * @param team
     * @return
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteTeam(@RequestBody Long id) {

        if (id < 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);

        }
        boolean b = teamService.removeById(id);

        if (!b) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "删除失败");
        }
        return ResultUtils.success(true);

    }

    /**
     * @param team
     * @return
     */
    @PostMapping("/update")
    public BaseResponse<Boolean> updateTeam(@RequestBody TeamUpdateRequest teamUpdateRequest, HttpServletRequest request) {
        if (teamUpdateRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);

        }
        User loginUser = userService.getLoginUser(request);
        boolean result = teamService.updateTeam(teamUpdateRequest, loginUser);
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
    public BaseResponse<List<TeamUserVO>> listTeams(TeamQuery teamQuery, HttpServletRequest request) {
        if (teamQuery == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);

        }
        boolean isAdmin = userService.isAdmin(request);
        List<TeamUserVO> teamUserVOS = teamService.listTeams(teamQuery, isAdmin);
        return ResultUtils.success(teamUserVOS);
    }


    @GetMapping("/list/page")
    public BaseResponse<Page<Team>> listTeamsByPage(TeamQuery teamQuery) {
        if (teamQuery == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);

        }
        Team team = new Team();
        BeanUtils.copyProperties(teamQuery, team);
        Page<Team> page = new Page<>(teamQuery.getPageNum(), teamQuery.getPageSize());
        QueryWrapper<Team> teamWrapper = new QueryWrapper<>(team);
        Page<Team> pageResult = teamService.page(page, teamWrapper);
        return ResultUtils.success(pageResult);
    }

    @PostMapping("/join")
    public BaseResponse<Boolean> joinTeam(@RequestBody TeamJoinRequest teamJoinRequest, HttpServletRequest request) {
        if (teamJoinRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);

        }
        User loginUser = userService.getLoginUser(request);
        boolean result = teamService.joinTeam(teamJoinRequest, loginUser);
        return ResultUtils.success(result);
    }


}
