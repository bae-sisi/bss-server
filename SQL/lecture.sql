use baesisi;

CREATE TABLE `professor` (
  `Pid` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `Is_officier` tinyint(1) NOT NULL,
  `Lab_into` varchar(200) DEFAULT NULL,
  `edu_background` varchar(200) DEFAULT NULL,
  `office` varchar(20) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `major` varchar(13) DEFAULT NULL,
  `Lab_Link` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`Pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


create table `progress`(
	`id` bigint not null  AUTO_INCREMENT,
	`Pid` bigint ,
    `Lid` bigint ,
    primary key (`id`),
    foreign key (`Pid`) references `professor`(`Pid`) ON delete cascade ,
    foreign key (`Lid`) references `lecture`(`Lid`) ON delete cascade
);

CREATE TABLE `lecture` (
  `Lid` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`Lid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

create view `progress_view` as Select p.id, l.Lid, l.name as `lecture_Name`, pf.Pid, pf.name as `prof_Name` from progress p, lecture l, professor pf where p.Lid = l.Lid and p.Pid = pf.Pid;








create table `event`(
	`eid` bigint not null auto_increment,
    `title` varchar(100) not null,
    `content` varchar(1500) not null,
    `created_at` datetime not null,
    `user_id` varchar(20) default null,
    primary key(`eid`) using btree
);

alter table `event` change column `content` `content` varchar(1500) not null;

create table `findmember`(
	`fid` bigint not null auto_increment,
    `title` varchar(100) not null,
    `content` varchar(1500) not null, 
    `end_date` varchar(100) not null,
    `prof_name` varchar(20) not null,
    `lacture_name` varchar(20) not null,
    `created_at` datetime not null,
    `user_id` varchar(20) default null ,
    primary key(`fid`) using btree
);

ALTER TABLE `baesisi`.`findmember` 
CHANGE COLUMN `content` `content` VARCHAR(1500) NOT NULL ;

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


create view `progress_view` as Select p.id, l.Lid, l.name as `lecture_Name`, pf.Pid, pf.name as `prof_Name` from progress p, lecture l, professor pf where p.Lid = l.Lid and p.Pid = pf.Pid;
