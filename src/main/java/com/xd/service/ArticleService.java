package com.xd.service;

import com.xd.dao.ArticleDao;
import com.xd.domain.Article;
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
    public void addArticle(Article article){
        articleDao.addArticle(article);
    }
}
