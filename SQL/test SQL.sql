/* Final Sample SQL*/

create database baesisi;

use baesisi;

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

create table `event`(
	`eid` bigint not null auto_increment,
    `title` varchar(100) not null,
    `content` varchar(500) not null,
    `created_at` datetime not null,
    `user_id` varchar(20) default null,
    primary key(`eid`) using btree
);

create table `findmember`(
	`fid` bigint not null auto_increment,
    `title` varchar(100) not null,
    `content` varchar(500) not null, 
    `end_date` varchar(100) not null,
    `prof_name` varchar(20) not null,
    `lacture_name` varchar(20) not null,
    `created_at` datetime not null,
    `user_id` varchar(20) default null ,
    primary key(`fid`) using btree
);

create table `lecture`(
	`lid` bigint not null auto_increment,
    `name` varchar(100) not null, 
    `grade` int default 0,
    primary key(`lid`)
);

create table `comment`(
	`cid` bigint not null auto_increment,
    `content` varchar(200) not null,
    `rate`  int(5) default 1,
    `enroll_Sems` varchar(15) not null,
    `recmnd_Cnt` int default 0,
    `sid` bigint not null, 
    `progress_id` bigint not null,
    primary key(`cid`)
);


create table `evaluation`(
	`vid` bigint not null auto_increment,
	`cid` bigint not null,
    `assignment_freq` int not null,
    `group_freq` int not null,
    `grading` int not null,
    `attending` int not null,
    `exam_num` int not null,
    `pgid` bigint not null,
    primary key(`vid`, `cid`),
    foreign key(`pgid`) REFERENCES `progress`(`id`)
);

CREATE TABLE `professor` (
  `pid` bigint NOT NULL AUTO_INCREMENT,
  `edu_background` varchar(255) DEFAULT NULL,
  `is_officier` bit(1) DEFAULT NULL,
  `lab_into` varchar(255) DEFAULT NULL,
  `lab_link` varchar(255) DEFAULT NULL,
  `major` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `office` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`pid`)
);

create table `progress`(
	`id` bigint not null  AUTO_INCREMENT,
	`Pid` bigint ,
    `Lid` bigint ,
    `year` int default null,
    primary key (`id`),
    foreign key (`Pid`) references `professor`(`Pid`) ON delete cascade ,
    foreign key (`Lid`) references `lecture`(`Lid`) ON delete cascade
);

create view `progress_view` as Select p.id as progressID, l.name as `lecture_Name`, l.grade as`grade`, p.year as `year`,
pf.name as `prof_Name`, COALESCE(Avg(c.rate), 0) as `rate`
from progress p join lecture l on p.Lid = l.Lid 
join professor pf on p.Pid = pf.Pid
LEFT join comment c on p.id = c.progress_id group by p.id;
