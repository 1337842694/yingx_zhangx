package com.zhangxin.dao;

import com.zhangxin.entity.Admin;

public interface AdminDao {
    Admin queryByUserName(String username);

    Admin queryAllMessage(String username);
}
