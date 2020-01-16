package com.junyi.mall.dao;

import com.junyi.mall.entity.AdminUser;
import org.apache.ibatis.annotations.Param;

public interface AdminUserMapper {
    int insert(AdminUser record);

    int insertSelective(AdminUser record);

    //unknow
    AdminUser login(@Param("userName") String userName, @Param("password") String password);

    AdminUser selectByPrimaryKey(Integer adminUserId);

    int updateByPrimaryKeySelective(AdminUser record);

    int updateByPrimaryKey(AdminUser record);
}
