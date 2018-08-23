package com.xd.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xd.entity.User;
import com.xd.response.Response;
import com.xd.response.StatusCode;
import com.xd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@CrossOrigin
@RestController
public class LoginController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/v1/{uid}/sign_out", method = RequestMethod.GET)
    public Response signOut(@PathVariable int uid){
        Response resp = new Response();
        return resp;
    }

    @RequestMapping(
            value = "/v1/sign_in",
            method = RequestMethod.POST,
            produces = "application/json",
            consumes = "application/json")
    public Response signIn(@RequestBody User user, HttpServletRequest request){
        System.out.println("sign_in...");

        Response resp = new Response();
        boolean isValidUser = userService.countMatchLoginUser(user);
        System.out.println(user.getUserName());
        System.out.println(user.getPassword());
        if(!isValidUser){
            return resp.failure(StatusCode.USER_LOGIN_ERROR);
        }

        User loginUser = userService.getUserByUserName(user.getUserName());

        String loginIp = request.getRemoteAddr();
        Date loginDate = new Date();
        loginUser.setLastIp(loginIp);
        loginUser.setLastVisit(loginDate);

        userService.loginSuccess(loginUser);

        return resp.success(loginUser);

    }
    @RequestMapping(value = "/v1/sign_up", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public Response signUp(){
        Response resp = new Response();
        return resp;
    }
}
