package com.xd.controller;

import com.xd.domain.User;
import com.xd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;
    //@RequestMapping(value = "/")
    //public String indexPage(){
    //    return "index";
    //}
    @RequestMapping(value = "/login")
    public String loginPage(){
        return "login";
    }
    @RequestMapping(value = "/loginCheck")
    public ModelAndView loginCheck(HttpServletRequest request, LoginCommand loginCommand){
        boolean isValidUser = userService.hasMatchUser(loginCommand.getUserName(), loginCommand.getPassword());
        System.out.println("checking... " + loginCommand.getUserName() + " " +loginCommand.getPassword() + " " + isValidUser);

        if (!isValidUser){
            return new ModelAndView("redirect:index");
        }else{
            System.out.println("Logging...");
            User user = userService.findUserByName(loginCommand.getUserName());
            System.out.println(user.getUserName());
            user.setLastIp(request.getLocalAddr());
            user.setLastVisit(new Date());
            userService.loginSuccess(user);
            request.getSession().setAttribute("user", user);
            System.out.println("returning view...");

            return new ModelAndView("redirect:/u/"+user.getUserId());
        }
    }
}
