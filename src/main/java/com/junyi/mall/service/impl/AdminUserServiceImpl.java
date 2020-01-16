package com.junyi.mall.service.impl;

import com.junyi.mall.dao.AdminUserMapper;
import com.junyi.mall.entity.AdminUser;
import com.junyi.mall.service.AdminUserService;
import com.junyi.mall.util.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class AdminUserServiceImpl implements AdminUserService {
    @Resource
    private AdminUserMapper adminUserMapper;

    @Override
    public AdminUser login(String userName, String password) {
        String passMD5 = MD5Util.MD5Encode(password, "utf8");
        log.info(passMD5);
        return adminUserMapper.login(userName, passMD5);
    }

    @Override
    public AdminUser getUserDetailById(Integer loginUserId) {
        return adminUserMapper.selectByPrimaryKey(loginUserId);
    }

    @Override
    public Boolean updatePassword(Integer loginUserId, String originalPassword, String newPassword) {
        AdminUser user = adminUserMapper.selectByPrimaryKey(loginUserId);
        if (user != null) {
            String originalPassMD5 = MD5Util.MD5Encode(originalPassword, "utf8");
            String newPassMD5 = MD5Util.MD5Encode(newPassword, "utf8");
            if (user.getLoginPassword() == originalPassMD5) {
                user.setLoginPassword(newPassMD5);
                if (adminUserMapper.updateByPrimaryKeySelective(user) > 0)
                    return true;
            }
        }
        return false;
    }

    @Override
    public Boolean updateName(Integer loginUserId, String loginUserName, String nickName) {
        AdminUser user = adminUserMapper.selectByPrimaryKey(loginUserId);
        if (user != null) {
            user.setLoginUserName(loginUserName);
            user.setNickName(nickName);
            if (adminUserMapper.updateByPrimaryKeySelective(user) > 0)
                return true;
        }
        return false;
    }
}
