package com.xd.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xd.entity.Article;
import com.xd.entity.User;
import com.xd.response.Response;
import com.xd.response.StatusCode;
import com.xd.service.ArticleService;
import com.xd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@CrossOrigin
@RestController
public class ArticleController {
    @Autowired
    ArticleService articleService;
    @Autowired
    UserService userService;


    @RequestMapping(
            value = "/v1/articles/{aid}",
            method = RequestMethod.GET)
    public Response getArticle(HttpServletRequest request, @PathVariable("aid") int aid){
        System.out.println("getting article...");
        Article article = articleService.findArticleByArticleId(aid);
        System.out.println(article);
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
            value = "/v1/articles",
            method = RequestMethod.POST,
            produces = "application/json",
            consumes = "application/json")
    public Response  addArticle(@RequestBody Article article){
        Response resp = new Response();
        System.out.println("add article ing...");
        // System.out.println(article.getArticleTitle());
        // System.out.println(article.getArticleContent());
        // System.out.println(article.getUserId());
        User articleUser = userService.getUserByUserId(article.getUserId());
        article.setArticleAuthor(articleUser.getUserName());
        article.setArticleStatus("public");
        article.setCreateDate(new Date());

        boolean isSuccess = articleService.addArticle(article);
        if(isSuccess) return resp.success();
        else return resp.failure(StatusCode.DATA_IS_WRONG);
    }

    @RequestMapping(
            value = "/v1/articles",
            method = RequestMethod.PUT,
            produces = "application/json",
            consumes = "application/json")
    public Response editArticle(@RequestBody Article article){
        Response resp = new Response();
        article.setChangeDate(new Date());

        boolean isSuccess = articleService.updateArticle(article);

        System.out.println("editing...");
        if(isSuccess) return resp.success();
        else return resp.failure(StatusCode.DATA_IS_WRONG);
    }
    @RequestMapping(
            value = "/v1/articles",
            method = RequestMethod.DELETE)
    public Response deleteArticle(@RequestParam("articleId") int articleId){
        Response resp = new Response();
        System.out.println("deleting...");
        System.out.println(articleId);
        boolean isSuccess = articleService.deleteArticleByArticleId(articleId);
        if(isSuccess) return resp.success();
        else return resp.failure(StatusCode.DATA_IS_WRONG);
    }

}
