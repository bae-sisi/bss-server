create database besisi;

use besisi;

CREATE TABLE `attempt` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `attempts` int NOT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb3;

create table `user`(
	`id` bigint NOT NULL auto_increment,
	`sid` varchar(20) NOT NULL,
	`is_banned` bit(1) NOT NULL,
	`is_deleted` bit(1) NOT NULL,
	`is_locked` bit(1) NOT NULL,
	`password` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
	`role` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
    `attempt_id` bigint DEFAULT NULL,
    `username` varchar(20) Not null,
    `email` varchar(30) not nULL,
	 PRIMARY KEY (`id`) USING BTREE,
	KEY `FKs4rjqs4udaere8q7udq91fg19` (`attempt_id`) USING BTREE,
	CONSTRAINT `FKs4rjqs4udaere8q7udq91fg19` FOREIGN KEY (`attempt_id`) REFERENCES `attempt` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
)ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb3;