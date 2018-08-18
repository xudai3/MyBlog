package com.xd.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xd.domain.Article;
import com.xd.domain.User;
import com.xd.response.Response;
import com.xd.service.ArticleService;
import com.xd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@CrossOrigin
@RestController
public class ArticleController {
    @Autowired
    ArticleService articleService;
    @Autowired
    UserService userService;


    @RequestMapping(
            value = "/api/article/{aid}",
            method = RequestMethod.GET)
    public Response getArticle(HttpServletRequest request, @PathVariable("aid") int aid){
        Article article = articleService.findArticleByArticleId(aid);
        ObjectMapper mapper  = new ObjectMapper();
        String jsonRes = null;
        Response resp = new Response();
        try {
            jsonRes = mapper.writeValueAsString(article);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        resp.success(jsonRes);

        return resp;
    }

    @RequestMapping(
            value = "/api/article",
            method = RequestMethod.POST,
            produces = "application/json",
            consumes = "application/json")
    public Response  addArticle(@RequestBody Article article){
        Response resp = new Response();
        System.out.println("add article ing...");
        // System.out.println(article.getArticleTitle());
        // System.out.println(article.getArticleContent());
        System.out.println(article.getUserId());
        User articleUser = userService.findUserByUserId(article.getUserId());
        article.setArticleAuthor(articleUser.getUserName());
        article.setArticleStatus("public");

        boolean isSuccess = articleService.addArticle(article);
        if(isSuccess) return resp.success();
        else return resp.failure();
    }

//    @RequestMapping(value = "/api/article", method = RequestMethod.POST)
//    public Response addArticle(HttpServletRequest request){
//        Article article = new Article();
//        String articleJson = request.getParameter("data");
//        System.out.println(articleJson);
//        ObjectMapper mapper = new ObjectMapper();
//        Article jsonArticle = new Article();
//        try {
//            jsonArticle = mapper.readValue(articleJson, Article.class);
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//
//        Response resp = new Response();
//
//        String articleTitle = jsonArticle.getArticleTitle();
//        String articleContent = jsonArticle.getArticleContent();
//        System.out.println(articleTitle);
//        System.out.println(articleContent);
//        User user = (User)WebUtils.getSessionAttribute(request,"user");
//        System.out.println(user.getUserId());
//        article.setArticleContent(articleContent);
//        article.setArticleTitle(articleTitle);
//        article.setArticleStatus("public");
//        article.setArticleAuthor(user.getUserName());
//        article.setUserId(user.getUserId());
//        articleService.addArticle(article);
//        return resp;
//    }

    @RequestMapping(
            value = "/api/article",
            method = RequestMethod.PUT,
            produces = "application/json",
            consumes = "application/json")
    public Response editArticle(@RequestBody Article article){
        Response resp = new Response();

        boolean isSuccess = articleService.updateArticle(article);

        System.out.println("editing...");
        if(isSuccess) return resp.success();
        else return resp.failure();
    }
    @RequestMapping(
            value = "/api/article",
            method = RequestMethod.DELETE)
    public Response deleteArticle(@RequestParam("articleId") int articleId){
        Response resp = new Response();
        System.out.println("deleting...");
        System.out.println(articleId);
        boolean isSuccess = articleService.deleteArticleByArticleId(articleId);
        if(isSuccess) return resp.success();
        else return resp.failure();
    }

//    @RequestMapping(value = "publish_article", method = RequestMethod.POST)
//    @ResponseBody
//    public String publishArticle(HttpServletRequest request, HttpServletResponse response){
//        Article article = new Article();
//        System.out.println("test ajax");
//        String articleJson = request.getParameter("data");
//        System.out.println(articleJson);
//        ObjectMapper mapper = new ObjectMapper();
//        Article jsonArticle = new Article();
//        try {
//            jsonArticle = mapper.readValue(articleJson, Article.class);
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//
//        String articleTitle = jsonArticle.getArticleTitle();
//        String articleContent = jsonArticle.getArticleContent();
//        System.out.println(articleTitle);
//        System.out.println(articleContent);
//        User user = (User)WebUtils.getSessionAttribute(request,"user");
//        System.out.println(user.getUserId());
//        article.setArticleContent(articleContent);
//        article.setArticleTitle(articleTitle);
//        article.setArticleStatus("public");
//        article.setArticleAuthor(user.getUserName());
//        article.setUserId(user.getUserId());
//        articleService.addArticle(article);
//        return "main";
//    }



}
