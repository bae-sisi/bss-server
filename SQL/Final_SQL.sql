create database baesisi;

use baesisi;

/* 해당 table은 spring sequrity 사용시 자동으로 생성되어 ERD에는 포함이 되있지 않습니다*/
CREATE TABLE `attempt` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `attempts` int NOT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb3;

/* user table 생성*/
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
);


create table `event`(
	`eid` bigint not null auto_increment,
    `title` varchar(100) not null,
    `content` varchar(1500) not null,
    `created_at` datetime not null,
    `user_id` varchar(20) not null,
    primary key(`eid`) using btree
);


create table `findmember`(
	`fid` bigint not null auto_increment,
    `title` varchar(100) not null,
    `content` varchar(1500) not null, 
    `end_date` varchar(100) not null,
    `prof_name` varchar(20) not null,
    `lecture_name` varchar(20) not null,
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

INSERT INTO lecture (name, grade)
VALUES
('이산수학', 1),
('미래설계탐색', 1),
('컴퓨터시스템개론', 1),
('오픈소스소프트웨어 이해와 실습', 1),
('미래설계준비', 1),
('자료구조', 2),
('컴퓨터구조', 2),
('소프트웨어 실전영어', 2),
('객체지향 프로그래밍', 2),
('선형대수학', 2),
('미래설계구현', 2),
('오픈소스 기초프로젝트', 2),
('알고리즘', 2),
('프로그래밍언어론', 2),
('시스템 소프트웨어', 2),
('창업탐색', 2),
('오픈소스 개발프로젝트', 2),
('확률 및 통계', 2),
('컴퓨터 그래픽스', 2),
('운영체제', 3),
('객체지향 설계', 3),
('컴파일러', 3),
('컴퓨터 네트워크', 3),
('오픈소스 전문프로젝트', 3),
('오픈소스 웹소프트웨어', 3),
('창업기획', 3),
('소프트웨어공학', 3),
('데이터베이스시스템', 3),
('인공지능', 3),
('정보보호', 3),
('펌웨어프로그래밍', 3),
('산학프로젝트(종합설계)', 3),
('창업설계', 3),
('창업산학초청세미나Ⅰ', 4),
('임베디드시스템', 4),
('영상처리', 4),
('빅테이터시스템설계', 4),
('기계학습', 4),
('캡스톤디자인', 4),
('정보·컴퓨터교재연구및지도법', 4),
('창업산학초청세미나Ⅱ', 4),
('정보검색', 4),
('클라우드컴퓨팅', 4),
('자연언어처리', 4),
('창업파잇럿프로젝트(종합설계)', 4),
('빅데이터분석시각화', 4),
('정보·컴퓨터교육론', 4);


CREATE TABLE `professor` (
  `Pid` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `Is_official` tinyint(1) NOT NULL,
  `Lab_intro` varchar(200) DEFAULT NULL,
  `edu_background` varchar(200) DEFAULT NULL,
  `office` varchar(20) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `major` varchar(100) DEFAULT NULL,
  `Lab_url` varchar(300) DEFAULT NULL,
  `email` varchar(100) default null,
  PRIMARY KEY (`Pid`)
);

INSERT INTO `professor` (`name`, `Lab_Link`, `edu_background`, `major`,  `office`, `phone`, `email`, `Is_officier`, `Lab_into`)
VALUES
('전중남', NULL, '연세대학교', '임베디드시스템', 'S4-1 314', '043-261-2264', 'joongnam@cbnu.ac.kr', 1, 'test1'),
('이건명', NULL, 'KAIST', '인공지능', 'S4-1 325', '043-261-2263', 'kmlee@cbnu.ac.kr', 1, 'test2'),
('홍장의', NULL, 'KAIST', '소프트웨어공학', 'S4-1 320', '043-261-2261', 'jehong@chungbuk.ac.kr', 1, 'test3'),
('최경주', NULL, '연세대학교', '영상처리', 'S4-1 318', '043-261-3487', 'kjcheoi@chungbuk.ac.kr', 1, 'test4'),
('류관희', NULL, 'KAIST', '컴퓨터 그래픽스 및 콘텐츠', 'S4-1 319', '043-261-2788', 'khyoo@chungbuk.ac.kr', 1, 'test5'),
('이재성', NULL, 'KAIST', '자연언어처리 및 정보검색', 'S4-1 324', '043-261-2252', 'jasonlee@cbnu.ac.kr', 1, 'test6'),
('이종연', NULL, '충북대학교', '데이터베이스', 'S4-1 328', '043-261-2789', 'jongyun@chungbuk.ac.kr', 1, 'test7'),
('아지즈', 'http://dalab.cbnu.ac.kr/', 'Dongguk University', 'Data Analytics', 'S4-1, 326', '043-261-3597', 'aziz@chungbuk.ac.kr', 1, 'test8'),
('조오현', 'http://ohyunjo.wixsite.com/islcbnu', 'KAIST', '컴퓨터네트워크', 'S4-1 323', '043-261-2328', 'ohyunjo@cbnu.ac.kr', 1, 'test9'),
('노서영', 'http://dclab.cbnu.ac.kr/', 'Iowa State University', '데이터 컴퓨팅', 'S4-1-315', '043-261-2253', 'rsyoung@cbnu.ac.kr', 1, 'test10'),
('조희승', 'http://cslab.cbnu.ac.kr/', '한국과학기술원(KAIST)', '시스템 소프트웨어', 'S4-1-327', '043-261-3757', 'heesn@chungbuk.ac.kr', 1, 'test11'),
('이의종', 'https://sites.google.com/view/kongjjagae', '고려대학교', '자가-적응 소프트웨어', 'S4-1-317', '043-249-1924', 'kongjjagae@chungbuk.ac.kr', 1, 'test12'),
('정지훈', 'https://sites.google.com/view/cbnu-mil/home', '고려대학교', '기계 지능', 'S4-1-329', '043-261-2254', 'jh.jeong@chungbuk.ac.kr', 1, 'test13');

INSERT INTO `professor` (`name`, `Lab_Link`, `edu_background`, `major`,  `office`, `phone`, `email`, `Is_officier`, `Lab_into`)
VALUES
('김세민', NULL, 'company1', 'major1', 'office1', '010-0000-0000', 'email1@gmail.com', 0, NULL),
('김정훈', NULL, 'company2', 'major2', 'office2', '010-0000-0001', 'email2@gmail.com', 0, NULL),
('이병훈', NULL, 'company3', 'major3', 'office3', '010-0000-0002', 'email3@gmail.com', 0, NULL),
('조영복', NULL, 'company4', 'major4', 'office4', '010-0000-0003', 'email4@gmail.com', 0, NULL),
('서민자', NULL, 'company5', 'major5', 'office5', '010-0000-0004', 'email5@gmail.com', 0, NULL),
('문현주', NULL, 'company6', 'major6', 'office6', '010-0000-0005', 'email6@gmail.com', 0, NULL),
('강재구', NULL, 'company7', 'major7', 'office7', '010-0000-0006', 'email7@gmail.com', 0, NULL),
('신재혁', NULL, 'company9', 'major9', 'office9', '010-0000-0008', 'email9@gmail.com', 0, NULL),
('안광모', NULL, 'company11', 'major11', 'office11', '010-0000-0010', 'email11@gmail.com', 0, NULL),
('김윤성', NULL, 'company12', 'major12', 'office12', '010-0000-0011', 'email12@gmail.com', 0, NULL),
('손호선', NULL, 'company13', 'major13', 'office13', '010-0000-0012', 'email13@gmail.com', 0, NULL),
('박정희', NULL, 'company14', 'major14', 'office14', '010-0000-0013', 'email14@gmail.com', 0, NULL),
('장순선', NULL, 'company16', 'major16', 'office16', '010-0000-0015', 'email16@gmail.com', 0, NULL);

create table `progress`(
	`id` bigint not null  AUTO_INCREMENT,
	`Pid` bigint ,
    `Lid` bigint ,
    `year` int default null,
    primary key (`id`),
    foreign key (`Pid`) references `professor`(`Pid`) ON delete cascade ,
    foreign key (`Lid`) references `lecture`(`Lid`) ON delete cascade
);

INSERT INTO progress (Pid, Lid, year)
VALUES
  ((SELECT Pid FROM professor WHERE name = '박정희'),
   (SELECT Lid FROM lecture WHERE name = '알고리즘'),
   18),
  
  ((SELECT Pid FROM professor WHERE name = '조희승'),
   (SELECT Lid FROM lecture WHERE name = '창업산학초청세미나Ⅰ'),
   21),
  
  ((SELECT Pid FROM professor WHERE name = '김세민'),
   (SELECT Lid FROM lecture WHERE name = '오픈소스 웹소프트웨어'),
   22),
   
  ((SELECT Pid FROM professor WHERE name = '이재성'),
   (SELECT Lid FROM lecture WHERE name = '소프트웨어 실전영어'),
   22),
   
  ((SELECT Pid FROM professor WHERE name = '이의종'),
   (SELECT Lid FROM lecture WHERE name = '이산수학'),
   19),
   
  ((SELECT Pid FROM professor WHERE name = '이건명'),
   (SELECT Lid FROM lecture WHERE name = '창업탐색'),
   21),
   
  ((SELECT Pid FROM professor WHERE name = '홍장의'),
   (SELECT Lid FROM lecture WHERE name = '컴퓨터시스템개론'),
   23),
   
  ((SELECT Pid FROM professor WHERE name = '장순선'),
   (SELECT Lid FROM lecture WHERE name = '캡스톤디자인'),
   19),
   
  ((SELECT Pid FROM professor WHERE name = '최경주'),
   (SELECT Lid FROM lecture WHERE name = '캡스톤디자인'),
   21),
   
  ((SELECT Pid FROM professor WHERE name = '노서영'),
   (SELECT Lid FROM lecture WHERE name = '빅데이터분석시각화'),
   22),
   
  ((SELECT Pid FROM professor WHERE name = '김세민'),
   (SELECT Lid FROM lecture WHERE name = '알고리즘'),
   20),
   
  ((SELECT Pid FROM professor WHERE name = '노서영'),
   (SELECT Lid FROM lecture WHERE name = '정보보호'),
   23),
   
  ((SELECT Pid FROM professor WHERE name = '조오현'),
   (SELECT Lid FROM lecture WHERE name = '컴퓨터 그래픽스'),
   19),
   
  ((SELECT Pid FROM professor WHERE name = '이재성'),
   (SELECT Lid FROM lecture WHERE name = '오픈소스 전문프로젝트'),
   23),
   
  ((SELECT Pid FROM professor WHERE name = '이의종'),
   (SELECT Lid FROM lecture WHERE name = '오픈소스소프트웨어 이해와 실습'),
   18),
   
  ((SELECT Pid FROM professor WHERE name = '정지훈'),
   (SELECT Lid FROM lecture WHERE name = '컴파일러'),
   23),
   
  ((SELECT Pid FROM professor WHERE name = '조오현'),
   (SELECT Lid FROM lecture WHERE name = '오픈소스 개발프로젝트'),
   19),
   
  ((SELECT Pid FROM professor WHERE name = '문현주'),
   (SELECT Lid FROM lecture WHERE name = '정보검색'),
   23),
   
  ((SELECT Pid FROM professor WHERE name = '아지즈'),
   (SELECT Lid FROM lecture WHERE name = '객체지향 설계'),
   18),
   
  ((SELECT Pid FROM professor WHERE name = '이건명'),
   (SELECT Lid FROM lecture WHERE name = '정보보호'),
   21),
   
  ((SELECT Pid FROM professor WHERE name = '조희승'),
   (SELECT Lid FROM lecture WHERE name = '정보·컴퓨터교재연구및지도법'),
   23),
   
  ((SELECT Pid FROM professor WHERE name = '김윤성'),
   (SELECT Lid FROM lecture WHERE name = '정보보호'),
   23),
   
  ((SELECT Pid FROM professor WHERE name = '류관희'),
   (SELECT Lid FROM lecture WHERE name = '알고리즘'),
   18),
   
  ((SELECT Pid FROM professor WHERE name = '조영복'),
   (SELECT Lid FROM lecture WHERE name = '정보·컴퓨터교육론'),
   23),
   
  ((SELECT Pid FROM professor WHERE name = '안광모'),
   (SELECT Lid FROM lecture WHERE name = '빅데이터분석시각화'),
   20),
   
  ((SELECT Pid FROM professor WHERE name = '아지즈'),
   (SELECT Lid FROM lecture WHERE name = '오픈소스소프트웨어 이해와 실습'),
   18),
   
  ((SELECT Pid FROM professor WHERE name = '김세민'),
   (SELECT Lid FROM lecture WHERE name = '영상처리'),
   18),
   
  ((SELECT Pid FROM professor WHERE name = '장순선'),
   (SELECT Lid FROM lecture WHERE name = '정보보호'),
   23),
   
  ((SELECT Pid FROM professor WHERE name = '박정희'),
   (SELECT Lid FROM lecture WHERE name = '미래설계구현'),
   21),
   
  ((SELECT Pid FROM professor WHERE name = '조영복'),
   (SELECT Lid FROM lecture WHERE name = '컴퓨터 그래픽스'),
   23);


-- INSERT IGNORE INTO `progress` (`Pid`, `Lid`, `year`)
-- SELECT
--   `professor`.`Pid`,
--   `lecture`.`Lid`,
--   FLOOR(RAND() * (23 - 18 + 1)) + 18
-- FROM
--   `professor`, `lecture`
-- ORDER BY
--   RAND()
-- LIMIT 15;


create table `comment`(
	`cid` bigint not null auto_increment,
    `content` varchar(1500) not null,
    `rate`  int(5) default 1,
    `enroll_Sems` varchar(15) not null,
    `recmnd_Cnt` int default 0,
    `user_id` varchar(20) not null, 
    `progress_id` bigint not null,
    primary key(`cid`),
    foreign key(`progress_id`) references `progress`(`id`)
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


create view `progress_view` as Select p.id as progressID, l.name as `lecture_Name`, l.grade as`grade`, p.year as `year`,
pf.name as `prof_Name`, COALESCE(Avg(c.rate), 0) as `rate`
from progress p join lecture l on p.Lid = l.Lid 
join professor pf on p.Pid = pf.Pid
LEFT join comment c on p.id = c.progress_id group by p.id;

