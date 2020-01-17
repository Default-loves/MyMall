package com.junyi.mall.service.impl;

import com.junyi.mall.common.Constants;
import com.junyi.mall.common.ServiceResultEnum;
import com.junyi.mall.controller.VO.MallUserVO;
import com.junyi.mall.dao.MallUserMapper;
import com.junyi.mall.entity.MallUser;
import com.junyi.mall.service.MallUserService;
import com.junyi.mall.util.BeanUtil;
import com.junyi.mall.util.MD5Util;
import com.junyi.mall.util.PageQueryUtil;
import com.junyi.mall.util.PageResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class MallUserServiceImpl implements MallUserService {
    @Resource
    private MallUserMapper mallUserMapper;
    @Override
    public PageResult getNewBeeMallUsersPage(PageQueryUtil pageUtil) {
        int totalMallUsers = mallUserMapper.getTotalMallUsers(pageUtil);
        List<MallUser> mallUserList = mallUserMapper.findMallUserList(pageUtil);
        PageResult result = new PageResult(mallUserList, totalMallUsers, pageUtil.getLimit(), pageUtil.getPage());
        return result;
    }

    @Override
    public String register(String loginName, String password) {
        if (mallUserMapper.selectByLoginName(loginName) != null)
            return ServiceResultEnum.SAME_LOGIN_NAME_EXIST.getResult();
        MallUser mallUser = MallUser.builder()
                .loginName(loginName)
                .nickName(loginName)
                .passwordMd5(MD5Util.MD5Encode(password, "utf8"))
                .build();
        if (mallUserMapper.insertSelective(mallUser) > 0)
            return ServiceResultEnum.SUCCESS.getResult();
        else
            return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public String login(String loginName, String passwordMD5, HttpSession httpSession) {
        MallUser user = mallUserMapper.selectByLoginNameAndPasswd(loginName, passwordMD5);
        if (user != null && httpSession != null) {
            if (user.getLockedFlag() == 1) {
                return ServiceResultEnum.LOGIN_USER_LOCKED.getResult();
            }
            //昵称太长 影响页面展示
            if (user.getNickName() != null && user.getNickName().length() > 7) {
                String tempNickName = user.getNickName().substring(0, 7) + "..";
                user.setNickName(tempNickName);
            }
            MallUserVO newBeeMallUserVO = new MallUserVO();
            BeanUtil.copyProperties(user, newBeeMallUserVO);
            //设置购物车中的数量
            httpSession.setAttribute(Constants.MALL_USER_SESSION_KEY, newBeeMallUserVO);
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.LOGIN_ERROR.getResult();
    }

    @Override
    public MallUserVO updateUserInfo(MallUser mallUser, HttpSession httpSession) {
        MallUser user = mallUserMapper.selectByPrimaryKey(mallUser.getUserId());
        if (user != null) {
            user.setNickName(mallUser.getNickName());
            user.setAddress(mallUser.getAddress());
            user.setIntroduceSign(mallUser.getIntroduceSign());
            if (mallUserMapper.updateByPrimaryKeySelective(user) > 0) {
                MallUserVO newBeeMallUserVO = new MallUserVO();
                user = mallUserMapper.selectByPrimaryKey(mallUser.getUserId());
                BeanUtil.copyProperties(user, newBeeMallUserVO);
                httpSession.setAttribute(Constants.MALL_USER_SESSION_KEY, newBeeMallUserVO);
                return newBeeMallUserVO;
            }
        }
        return null;
    }

    @Override
    public Boolean lockUsers(Integer[] ids, int lockStatus) {
        if (ids.length < 1) {
            return false;
        }
        return mallUserMapper.lockUserBatch(ids, lockStatus) > 0;
    }
}
