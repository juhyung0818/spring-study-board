CREATE table post (
id INT auto_increment PRIMARY KEY
title varchar(200),
content text,
view_count INT
);

CREATE TABLE comment (
   id int auto_increment primary key,
  post_id int,
  user_key int,
  content varchar(1000),
  parent int,
  register_date datetime
);

CREATE TABLE image
(
  id int auto_increment primary key,
  post_id int,
  file_name varchar(1000),
  saved_file_name varchar(1000),
  file_size long,
  content_type varchar(100),
  deleted int
);