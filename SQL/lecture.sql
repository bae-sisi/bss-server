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
