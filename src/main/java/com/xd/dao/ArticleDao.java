package com.xd.dao;

import com.xd.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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

    public boolean addArticle(Article article){
        String sqlStr = "INSERT INTO t_article(" +
                "article_title, " +
                "article_author, " +
                "article_content, " +
                "article_status, " +
                "create_date, " +
                "user_id" +
                ") VALUES(?, ?, ?, ?, ?, ?)";
//        System.out.println(article.getArticleTitle());
//        System.out.println(article.getArticleAuthor());
//        System.out.println(article.getArticleContent());
//        System.out.println(article.getArticleStatus());
//        System.out.println(article.getUserId());
        int opRes = jdbcTemplate.update(sqlStr, new Object[]{
                article.getArticleTitle(),
                article.getArticleAuthor(),
                article.getArticleContent(),
                article.getArticleStatus(),
                article.getCreateDate(),
                article.getUserId()
        });
        System.out.println(opRes);
        if(opRes > 0)
            return true;
        else return false;
    }

    public boolean deleteArticleByArticleId(final int articleId){
        String sqlStr = "DELETE FROM t_article where article_id = ?";
        int opRes = jdbcTemplate.update(sqlStr, new Object[]{articleId});

        if(opRes > 0)
            return true;
        else return false;
    }

    public boolean updateArticle(Article article){
        String sqlStr = "UPDATE t_article SET " +
                "article_title = ?, " +
                "article_content = ?, " +
                "article_status = ?, " +
                "change_date = ? " +
                " WHERE article_id = ?";
        int opRes = jdbcTemplate.update(sqlStr, new Object[]{
                article.getArticleTitle(),
                article.getArticleContent(),
                article.getArticleStatus(),
                article.getChangeDate(),
                article.getArticleId()
        });
        if(opRes > 0)
            return true;
        else return false;
    }
}
