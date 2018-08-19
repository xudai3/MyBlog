package com.xd.service;

import com.xd.dao.LoginLogDao;
import com.xd.dao.UserDao;
import com.xd.entity.LoginLog;
import com.xd.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private LoginLogDao loginLogDao;

    public boolean hasMatchUser(User user){
        int matchCount = userDao.getMatchCount(user);
        return matchCount > 0;
    }

    public int addUser(User user){
        return userDao.addUser(user);
    }

    public User getUserByName(String userName){
        return userDao.getUserByUserName(userName);
    }

    public User getUserByUserId(int userId) {
        return userDao.getUserByUserId(userId);
    }

    public List<User> getUserList(){
        return userDao.getUserList();
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
