package com.xd.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xd.entity.Article;
import com.xd.response.Response;
import com.xd.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class MyBlogController {
    @Autowired
    ArticleService articleService;

    @RequestMapping(value = "/v1/users/{uid}/articles", method = RequestMethod.GET)
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
