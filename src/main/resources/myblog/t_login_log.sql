DROP TABLE IF EXISTS t_login_log;
CREATE TABLE IF NOT EXISTS t_login_log(
  login_log_id int AUTO_INCREMENT PRIMARY KEY,
  user_id int,
  ip varchar(32),
  login_date datetime
)ENGINE = InnoDB DEFAULT CHARSET=utf8;
