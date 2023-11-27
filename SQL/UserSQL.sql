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


create table `Evaluation`(
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


insert into evaluation values();

create view `progress_view` as Select p.id, l.Lid, l.name as `lecture_Name`, pf.Pid, pf.name as `prof_Name` from progress p, lecture l, professor pf where p.Lid = l.Lid and p.Pid = pf.Pid;

select avg(c.rate) as Rate from comment as c where c.progress_id = 3;


select avg(c.rate) as Rate, pv.lecture_Name, pv.prof_Name 
from comment as c join progress_view as pv on c.progress_id=pv.id 
where c.progress_id=3;

SELECT
  CASE
    WHEN ev.AsignFreq > 2 THEN '많음'
    WHEN ev.AsignFreq > 1 THEN '보통'
    ELSE '없음'
  END AS `AF`,
  case
	when ev.GrpFreq > 2 then '많음'
    when ev.GrpFreq > 1 then '보통'
    ELSE '없음'
  END AS `GF`,
  case
	when ev.Grading > 2 then '너그러움'
    when ev.Grading > 1 then '보통'
    ELSE '깐깐함'
  END AS `GR`,
  case
	when ev.Attending > 4 then '복합적'
    when ev.Attending > 3 then '직접호명'
    when ev.Attending > 2 then '지정좌석'
    when ev.Attending > 1 then '전자출결'
	ELSE '반영안함'
  END AS `Att`,
  case 
	when ev.ExamN > 4 then '네번이상'
    when ev.ExamN > 3 then '세번'
    when ev.ExamN > 2 then '두번'
    when ev.ExamN > 1 then '한번'
    ELSE '없음'
  END AS `Ex`
FROM (
  SELECT
    AVG(assignment_freq) AS AsignFreq,
    AVG(group_freq) AS GrpFreq,
    AVG(grading) AS Grading,
    AVG(attending) AS Attending,
    AVG(exam_num) AS ExamN
  FROM
    evaluation
  WHERE
    pgid = 3
) AS ev;
