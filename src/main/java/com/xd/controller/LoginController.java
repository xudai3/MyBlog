package com.xd.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xd.domain.User;
import com.xd.response.Response;
import com.xd.response.StatusCode;
import com.xd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@CrossOrigin
@RestController
public class LoginController {
    @Autowired
    private UserService userService;
    //@RequestMapping(value = "/")
    //public String indexPage(){
    //    return "index";
    //}
    @RequestMapping(value = "/v1/{uid}/sign_out", method = RequestMethod.GET)
    public Response signOut(@PathVariable int uid){
        Response resp = new Response();
        return resp;
    }


    @RequestMapping(value = "/v1/sign_in", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public Response signIn(@RequestBody User user, HttpServletRequest request){
        System.out.println("sign_in...");
        //User user = new User();
        //user.setUserName(userName);
        //user.setPassword(password);
        Response resp = new Response();
        boolean isValidUser = userService.hasMatchUser(user);
        System.out.println(user.getUserName());
        System.out.println(user.getPassword());
        if(!isValidUser){
            return resp.failure(StatusCode.USER_LOGIN_ERROR);
        }

        User loginUser = userService.findUserByName(user.getUserName());

        ObjectMapper mapper = new ObjectMapper();
        String res = null;
        try {
            res = mapper.writeValueAsString(loginUser);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        System.out.println(res);

        String loginIp = request.getRemoteAddr();
        Date loginDate = new Date();
        loginUser.setLastIp(loginIp);
        loginUser.setLastVisit(loginDate);

        userService.loginSuccess(loginUser);

        return resp.success(res);

    }
    @RequestMapping(value = "/v1/sign_up", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public Response signUp(){
        Response resp = new Response();
        return resp;
    }
//    @RequestMapping(value = "/loginCheck")
//    public ModelAndView loginCheck(HttpServletRequest request, LoginCommand loginCommand){
//        boolean isValidUser = userService.hasMatchUser(loginCommand.getUserName(), loginCommand.getPassword());
//        System.out.println("checking... " + loginCommand.getUserName() + " " +loginCommand.getPassword() + " " + isValidUser);
//
//        if (!isValidUser){
//            return new ModelAndView("redirect:index");
//        }else{
//            System.out.println("Logging...");
//            User user = userService.findUserByName(loginCommand.getUserName());
//            System.out.println(user.getUserName());
//            user.setLastIp(request.getLocalAddr());
//            user.setLastVisit(new Date());
//            userService.loginSuccess(user);
//            request.getSession().setAttribute("user", user);
//            System.out.println("returning view...");
//
//            return new ModelAndView("redirect:/u/"+user.getUserId());
//        }
//    }
}
