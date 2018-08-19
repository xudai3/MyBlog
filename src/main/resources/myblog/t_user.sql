DROP TABLE IF EXISTS t_user;
CREATE TABLE IF NOT EXISTS t_user(
  user_id int AUTO_INCREMENT PRIMARY KEY ,
  user_name varchar(32) NOT NULL,
  credits int DEFAULT '0',
  password varchar(32) NOT NULL,
  last_visit datetime,
  last_ip varchar(32)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
