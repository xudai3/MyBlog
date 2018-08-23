package com.xd.service;

import com.xd.dao.LoginLogDao;
import com.xd.dao.UserDao;
import com.xd.entity.LoginLog;
import com.xd.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private LoginLogDao loginLogDao;
    @Autowired
    private RedisTemplate redisTemplate;

    public boolean countMatchLoginUser(User user){
        int matchCount = userDao.countMatchLoginUser(user);
        return matchCount > 0;
    }

    public boolean saveUser(User user){
        boolean flag = false;
        try{
            userDao.saveUser(user);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }
    public boolean removeUser(int userId){
        boolean flag = false;
        String key = "userId_" + userId;
        ValueOperations<String, User> valueOperations = redisTemplate.opsForValue();
        boolean hasKey = redisTemplate.hasKey(key);
        if(hasKey){
            System.out.println("Already has key:" + key);
            redisTemplate.delete(key);
        }
        return flag;

    }

    public User getUserByUserName(String userName){
        return userDao.getUserByUserName(userName);
    }

    public User getUserByUserId(int userId) {
        String key = "userId_" + userId;
        ValueOperations<String, User> valueOperations = redisTemplate.opsForValue();
        boolean hasKey = redisTemplate.hasKey(key);
        if(hasKey){
            System.out.println("Already has key:" + key);
            return valueOperations.get(key);
        }
        System.out.println("Have not found key:" + key);
        User user = userDao.getUserByUserId(userId);
        valueOperations.set(key, user, 2, TimeUnit.HOURS);
        return user;
        // return userDao.getUserByUserId(userId);
    }

    public List<User> listUsers(){
        return userDao.listUsers();
    }

    public void loginSuccess(User user){
        user.setCredits(5 + user.getCredits());
        LoginLog loginLog = new LoginLog();
        loginLog.setUserId(user.getUserId());
        loginLog.setIp(user.getLastIp());
        loginLog.setLoginDate(user.getLastVisit());
        userDao.updateLoginInfo(user);
        loginLogDao.saveLoginLog(loginLog);
    }
}
