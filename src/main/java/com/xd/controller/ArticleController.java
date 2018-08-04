package com.xd.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xd.domain.Article;
import com.xd.domain.User;
import com.xd.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @RequestMapping(value = "article_editor", method = RequestMethod.GET)
    public String articleEditor(){
        return "article_editor";
    }

    @RequestMapping(value = "/article/{aid}", method = RequestMethod.GET)
    public String showArticle(HttpServletRequest request){
        return  "article_detail";
    }
    @RequestMapping(value = "/article/{aid}", method = RequestMethod.POST)
    @ResponseBody
    public String getArticle(HttpServletRequest request, @PathVariable("aid") int aid){
        Article article = articleService.findArticleByArticleId(aid);
        ObjectMapper mapper  = new ObjectMapper();
        String jsonRes = null;
        try {
            jsonRes = mapper.writeValueAsString(article);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return  jsonRes;
    }

    @RequestMapping(value = "article", method = RequestMethod.POST)
    public String addArticle(HttpServletRequest request){
        Article article = new Article();
        System.out.println("test ajax");
        String articleJson = request.getParameter("data");
        System.out.println(articleJson);
        ObjectMapper mapper = new ObjectMapper();
        Article jsonArticle = new Article();
        try {
            jsonArticle = mapper.readValue(articleJson, Article.class);
        }catch (IOException e){
            e.printStackTrace();
        }

        String articleTitle = jsonArticle.getArticleTitle();
        String articleContent = jsonArticle.getArticleContent();
        System.out.println(articleTitle);
        System.out.println(articleContent);
        User user = (User)WebUtils.getSessionAttribute(request,"user");
        System.out.println(user.getUserId());
        article.setArticleContent(articleContent);
        article.setArticleTitle(articleTitle);
        article.setArticleStatus("public");
        article.setArticleAuthor(user.getUserName());
        article.setUserId(user.getUserId());
        articleService.addArticle(article);
        return "/u/"+user.getUserId();
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
