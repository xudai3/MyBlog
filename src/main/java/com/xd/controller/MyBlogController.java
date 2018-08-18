package com.xd.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xd.domain.Article;
import com.xd.domain.User;
import com.xd.response.Response;
import com.xd.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class MyBlogController {
    @Autowired
    ArticleService articleService;

    @RequestMapping(value = "/api/user/{uid}", method = RequestMethod.GET)
    public Response initMainPage(@PathVariable int uid){
        System.out.println("Aricle List Init");
        //User user = (User) WebUtils.getSessionAttribute(request,"user");
        List<Article> articles = articleService.findArticleByUserId(uid);
        ObjectMapper mapper = new ObjectMapper();
        String jsonRes = null;
        Response resp = new Response();
        try {
            jsonRes = mapper.writeValueAsString(articles);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(jsonRes);

        return resp.success(jsonRes);
    }
}
