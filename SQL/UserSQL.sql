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

alter table `findmember` add column `stack` int not null;

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



use baesisi;

/*바꾼거 rate 추가 */ 
create view `progress_view` as Select p.id as progressID, l.name as `lecture_Name`, l.grade as`grade`, p.year as `year`,
pf.name as `prof_Name`, COALESCE(Avg(c.rate), 0) as `rate`
from progress p join lecture l on p.Lid = l.Lid 
join professor pf on p.Pid = pf.Pid
LEFT join comment c on p.id = c.progress_id group by p.id;



create view `test_view` as Select c.progress_id as Pid, avg(c.rate) as Rate from comment as c group by Pid;

select pv.progressID, AVG(c.rate) as Rate,pv.grade, pv.lecture_Name, pv.prof_Name from comment as c join progress_view as pv on c.progress_id = pv.progressID where pv.grade = 3 group by c.progress_id;

select COALESCE(Avg(c.rate), 0) as Rate from comment as c where c.progress_id = 3;

use baesisi;

select pv.progressID, avg(c.rate) as Rate, pv.lecture_Name, pv.prof_Name 
from comment as c join progress_view as pv on c.progress_id=pv.progressID
where c.progress_id=1;

select pv.progressID, avg(c.rate) as Rate, pv.lecture_Name, pv.prof_Name 
from comment as c join progress_view as pv on c.progress_id=pv.progressID group by c.progress_id;



SELECT CASE WHEN EXISTS( SELECT 1 FROM progress AS pr WHERE pr.Pid = (SELECT p.Pid FROM professor AS p WHERE p.name = "정지훈") AND pr.Lid = (SELECT l.lid FROM lecture AS l WHERE l.name = "인공지능") )
THEN (select pr.id from progress as pr where pr.Pid = (SELECT p.Pid FROM professor AS p WHERE p.name = "정지훈") AND pr.Lid = (SELECT l.lid FROM lecture AS l WHERE l.name = "인공지능") ) ELSE 'True' END AS Result;

SELECT
  CASE
    WHEN ev.AsignFreq > 2 THEN '많음'
    WHEN ev.AsignFreq > 1 THEN '보통'
    WHEN ev.AsignFreq > 0 THEN '없음'
    ELSE 'None'
  END AS `AF`,
  case
	when ev.GrpFreq > 2 then '많음'
    when ev.GrpFreq > 1 then '보통'
    when ev.GrpFreq > 0 then '없음'
    ELSE 'None'
  END AS `GF`,
  case
	when ev.Grading > 2 then '너그러움'
    when ev.Grading > 1 then '보통'
    when ev.Grading > 0 then '깐깐함'
    ELSE 'None'
  END AS `GR`,
  case
	when ev.Attending > 4 then '복합적'
    when ev.Attending > 3 then '직접호명'
    when ev.Attending > 2 then '지정좌석'
    when ev.Attending > 1 then '전자출결'
    when ev.Attending > 0 then '반영안함'
	ELSE 'None'
  END AS `Att`,
  case 
	when ev.ExamN > 4 then '네번이상'
    when ev.ExamN > 3 then '세번'
    when ev.ExamN > 2 then '두번'
    when ev.ExamN > 1 then '한번'
    when ev.ExamN > 0 then '없음'
    ELSE 'None'
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
    pgid = 5
) AS ev; 





