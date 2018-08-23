package com.xd.service;

import com.xd.dao.ArticleDao;
import com.xd.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {
    @Autowired
    private ArticleDao articleDao;

    public List<Article> listArticlesByUserId(int userId){
        List<Article> articlesList = new ArrayList<Article>();
        try {
            articlesList = articleDao.listArticlesByUserId(userId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return articlesList;
    }
    public Article getArticleByArticleId(int articleId){
        return articleDao.getArticleByArticleId(articleId);
    }
    public List<Article> listArticlesByTagId(int tagId){
        return articleDao.listArticlesByTagId(tagId);
    }
    public boolean saveArticle(Article article){
        boolean flag = false;
        try{
            articleDao.saveArticle(article);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }
    public boolean removeArticleByArticleId(int articleId){
        boolean flag = false;
        try{
            articleDao.removeArticleByArticleId(articleId);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }
    public boolean updateArticle(Article article){
        boolean flag = false;
        try{
            articleDao.updateArticle(article);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }
}
