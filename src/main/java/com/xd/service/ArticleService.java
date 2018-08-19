package com.xd.service;

import com.xd.dao.ArticleDao;
import com.xd.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    @Autowired
    private ArticleDao articleDao;

    public List<Article> findArticleByUserId(int userId){
        return articleDao.findArticleByUserId(userId);
    }
    public Article findArticleByArticleId(int articleId){
        return articleDao.findArticleByAritcleId(articleId);
    }
    public List<Article> findArticleByTagsId(int tagId){
        return articleDao.findArticleByTag(tagId);
    }
    public boolean addArticle(Article article){
        return articleDao.addArticle(article);
    }
    public boolean deleteArticleByArticleId(int articleId){
        return articleDao.deleteArticleByArticleId(articleId);
    }
    public boolean updateArticle(Article article){
        return articleDao.updateArticle(article);
    }
}
