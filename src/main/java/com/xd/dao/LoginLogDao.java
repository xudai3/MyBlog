package com.xd.dao;

import com.xd.entity.LoginLog;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginLogDao {
    void saveLoginLog(LoginLog loginLog);
}
