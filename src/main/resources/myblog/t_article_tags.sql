DROP TABLE IF EXISTS t_article_tags;
CREATE TABLE IF NOT EXISTS t_article_tags(
    article_id int NOT NULL,
    tag_id int NOT NULL,
    PRIMARY KEY(article_id, tag_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
