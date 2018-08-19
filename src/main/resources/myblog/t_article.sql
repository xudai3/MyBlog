DROP TABLE IF EXISTS t_article;
CREATE TABLE IF NOT EXISTS t_article(
    article_id int AUTO_INCREMENT PRIMARY KEY,
    article_title varchar(128) NOT NULL,
    article_author varchar(32)  NOT NULL,
    article_content text NOT NULL,
    article_status varchar(16) DEFAULT 'public',
    user_id int NOT NULL,
    read_count int DEFAULT '0',
    like_count int DEFAULT '0',
    comment_count int DEFAULT '0',
    create_date datetime,
    change_date datetime
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
