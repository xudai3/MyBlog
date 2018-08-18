package com.xd.service;

import com.xd.dao.LoginLogDao;
import com.xd.dao.UserDao;
import com.xd.domain.LoginLog;
import com.xd.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private LoginLogDao loginLogDao;

    public boolean hasMatchUser(User user){
        int matchCount = userDao.getMatchCount(user.getUserName(), user.getPassword());
        return matchCount > 0;
    }

    public boolean addUser(User user){
        boolean isSuccess = userDao.addUser(user);
        return isSuccess;
    }

    public User findUserByName(String userName){
        return userDao.findUserByUserName(userName);
    }

    public User findUserByUserId(int userId) {
        return userDao.findUserByUserId(userId);
    }

    public void loginSuccess(User user){
        user.setCredits(5 + user.getCredits());
        LoginLog loginLog = new LoginLog();
        loginLog.setUserId(user.getUserId());
        loginLog.setIp(user.getLastIp());
        loginLog.setLoginDate(user.getLastVisit());
        userDao.updateLoginInfo(user);
        loginLogDao.insertLoginLog(loginLog);
    }
}
