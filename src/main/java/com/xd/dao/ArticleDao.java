package com.xd.dao;

import com.xd.domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ArticleDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public ArticleDao(){System.out.println("ArticleDao");}

    public List<Article> findArticleByUserId(final int userId){
        String sqlStr = "SELECT * from t_article where user_id = ?";
        List<Article> articles = jdbcTemplate.query(sqlStr, new Object[]{userId}, new BeanPropertyRowMapper<Article>(Article.class));
        return articles;
    }


    public Article findArticleByAritcleId(final int aiticleId){
        String sqlStr = "SELECT * from t_article where article_id = ?";
        Article article = jdbcTemplate.queryForObject(sqlStr, new Object[]{aiticleId}, new BeanPropertyRowMapper<Article>(Article.class));
        return article;
    }

    public List<Article> findArticleByTag(final int tagId){
        String sqlStr = "SELECT * FROM t_article WHERE article_id = (SELECT article_id FROM article_tags WHERE tag_id = ?)";
        List<Article> articles = jdbcTemplate.query(sqlStr, new Object[]{tagId}, new BeanPropertyRowMapper<Article>(Article.class));
        return articles;
    }

    public void addArticle(Article article){
        String sqlStr = "INSERT INTO t_article(" +
                "article_title, " +
                "article_author, " +
                "article_content, " +
                "article_status, " +
                "user_id" +
                ") VALUES(?, ?, ?, ?, ?)";
        int opRes = jdbcTemplate.update(sqlStr, new Object[]{article.getArticleTitle(),
                article.getArticleAuthor(),
                article.getArticleContent(),
                article.getArticleStatus(),
                article.getUserId()
        });
        System.out.println("operation result is:" + opRes);
    }
}
