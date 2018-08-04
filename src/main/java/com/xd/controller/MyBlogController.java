package com.xd.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xd.domain.Article;
import com.xd.domain.User;
import com.xd.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MyBlogController {
    @Autowired
    ArticleService articleService;

    @RequestMapping(value = "/u/{uid}", method = RequestMethod.GET)
    public ModelAndView mainPage(@PathVariable("uid") int uid){
        System.out.println("Main Page Produce");
        ModelAndView mav = new ModelAndView("main");
        //User user = (User) WebUtils.getSessionAttribute(request,"user");
        //List<Article> articles = articleService.findArticleByUserId(user.getUserId());
        //List<Article> articles = articleService.findArticleByUserId(uid);
        //ObjectMapper mapper = new ObjectMapper();
        //String jsonRes = null;
        //try {
        //    jsonRes = mapper.writeValueAsString(articles);
        //} catch (JsonProcessingException e) {
        //    e.printStackTrace();
        //}
        mav.addObject("uid", uid);
        return mav;
    }

    @RequestMapping(value = "/u/{uid}", method = RequestMethod.POST)
    @ResponseBody
    public String initMainPage(HttpServletRequest request){
        System.out.println("Aricle List Init");
        User user = (User) WebUtils.getSessionAttribute(request,"user");
        List<Article> articles = articleService.findArticleByUserId(user.getUserId());
        ObjectMapper mapper = new ObjectMapper();
        String jsonRes = null;
        try {
            jsonRes = mapper.writeValueAsString(articles);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(jsonRes);
        return jsonRes;
    }
}
