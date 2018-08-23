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
    public Response getArticle(@PathVariable("aid") int aid){
        System.out.println("getting article...");

        Response resp = new Response();

        Article article = articleService.getArticleByArticleId(aid);
        if(article == null){
            System.out.println("Not Found...");
            return resp.failure(StatusCode.RESULE_DATA_NONE);
        }else return resp.success(article);
    }

    @RequestMapping(
            value = "/v1/articles",
            method = RequestMethod.POST)
    public Response saveArticle(@RequestBody Article article){
        System.out.println("adding article...");

        Response resp = new Response();
        User articleAuthor = new User();
        boolean sqlSuccess = false;

        try{
            articleAuthor = userService.getUserByUserId(article.getUserId());
            if(articleAuthor == null){
                System.out.println("Can not get articleAuthor");
                return resp.failure(StatusCode.RESULE_DATA_NONE);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        article.setArticleAuthor(articleAuthor.getUserName());
        article.setArticleStatus("public");
        article.setCreateDate(new Date());
//        System.out.println(article.getUserId());
//        System.out.println(article.getArticleTitle());
//        System.out.println(article.getArticleContent());
//        System.out.println(article.getArticleAuthor());
//        System.out.println(article.getArticleStatus());
//        System.out.println(article.getCreateDate());
        try{
            sqlSuccess = articleService.saveArticle(article);
        }catch (Exception e){
            e.printStackTrace();
        }

        if(sqlSuccess) return resp.success();
        else return resp.failure(StatusCode.DATA_IS_WRONG);
    }

    @RequestMapping(
            value = "/v1/articles",
            method = RequestMethod.PUT,
            produces = "application/json",
            consumes = "application/json")
    public Response updateArticle(@RequestBody Article article){
        Response resp = new Response();
        article.setChangeDate(new Date());

        boolean sqlSuccess = articleService.updateArticle(article);

        System.out.println("updating...");
        if(sqlSuccess) return resp.success();
        else return resp.failure(StatusCode.DATA_IS_WRONG);
    }
    @RequestMapping(
            value = "/v1/articles",
            method = RequestMethod.DELETE)
    public Response removeArticle(@RequestParam("articleId") int articleId){
        Response resp = new Response();
        boolean sqlSuccess = false;
        System.out.println("deleting...");
        try{
            sqlSuccess = articleService.removeArticleByArticleId(articleId);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(sqlSuccess) return resp.success();
        else return resp.failure(StatusCode.DATA_IS_WRONG);
    }

}
