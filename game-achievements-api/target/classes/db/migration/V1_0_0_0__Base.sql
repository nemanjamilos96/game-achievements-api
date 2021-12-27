create table games (
id VARCHAR(36) not NULL,
display_name varchar(100),
primary key (id));

create table achievements (
id VARCHAR(36) not NULL,
created DATE,
description varchar(500),
display_name varchar(100),
display_order INTEGER,
icon varchar(255),
updated DATE,
game_id VARCHAR(36) not NULL,
primary key (id),
FOREIGN KEY (game_id) REFERENCES games(id));