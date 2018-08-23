package com.xd.dao;

import com.xd.entity.Article;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleDao {
    List<Article> listArticlesByUserId(int userId);

    Article getArticleByArticleId(int articleId);

    List<Article> listArticlesByTagId(int tagId);

    boolean saveArticle(Article article);

    boolean removeArticleByArticleId(int articleId);

    boolean updateArticle(Article article);
}
