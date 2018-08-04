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
DROP TABLE IF EXISTS t_article_tags;
CREATE TABLE IF NOT EXISTS t_article_tags(
    article_id int NOT NULL,
    tags_id int NOT NULL,
    PRIMARY KEY(article_id, tags_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS t_login_log;
CREATE TABLE IF NOT EXISTS t_login_log(
  login_logg_id int AUTO_INCREMENT PRIMARY KEY,
  user_id int,
  ip varchar(32),
  login_datetime datetime
)ENGINE = InnoDB DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS t_tags;
CREATE TABLE IF NOT EXISTS t_tags(
    tags_id int AUTO_INCREMENT PRIMARY KEY,
    tags_name varchar(32) NOT NULL,
    tags_type varchar(32) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS t_user;
CREATE TABLE IF NOT EXISTS t_user(
  user_id int AUTO_INCREMENT PRIMARY KEY ,
  user_name varchar(32) NOT NULL,
  credits int DEFAULT '0',
  password varchar(32) NOT NULL,
  last_visit datetime,
  last_ip varchar(32)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
