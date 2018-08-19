DROP TABLE IF EXISTS t_tags;
CREATE TABLE IF NOT EXISTS t_tags(
    tag_id int AUTO_INCREMENT PRIMARY KEY,
    tag_name varchar(32) NOT NULL,
    tag_type varchar(32) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
