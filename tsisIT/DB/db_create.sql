CREATE DATABASE tsis_delivery;

use tsis_delivery;

CREATE TABLE tb_register (
  seq bigint(16) NOT NULL AUTO_INCREMENT,
  gubun varchar(10) NOT NULL,
  s_id varchar(30) NOT NULL,
  s_name varchar(100) NOT NULL,
  s_phone varchar(20) NOT NULL,
  r_name varchar(100) NOT NULL,
  r_phone varchar(20) NOT NULL,
  r_post varchar(10) NOT NULL,
  r_address varchar(2000) NOT NULL,
  r_detailAddress varchar(2000) NOT NULL,
  regdate datetime NOT NULL,
  PRIMARY KEY (seq)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE tb_partner (
  seq bigint(16) NOT NULL AUTO_INCREMENT,
  gubun varchar(10) NOT NULL,
  dept varchar(100) NOT NULL,
  team varchar(100) NOT NULL,
  id varchar(30) NULL,
  name varchar(100) NOT NULL,
  rank varchar(50) NULL,
  phone varchar(20) NOT NULL,
  regdate datetime NULL,
  PRIMARY KEY (seq)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into tb_partner (gubun, dept, team, id, rank, name, phone, regdate) 
values ('202009','ITO 사업부','공통서비스팀','28050420','차장','강창구','010-3742-4264',sysdate());


CREATE TABLE `tb_user` (
 `seq` int(10) NOT NULL AUTO_INCREMENT,
 `userid` varchar(50) NOT NULL,
 `pwd` varchar(128) NOT NULL,
 `username` varchar(50) DEFAULT NULL,
 `email` varchar(150) DEFAULT NULL,
 `grade` char(1) NOT NULL,
 `regdate` datetime DEFAULT NULL,
 `last_login` datetime DEFAULT NULL,
 PRIMARY KEY (`seq`),
 KEY `userid` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


insert into tb_user (userid, pwd, username, email , grade, regdate, last_login)
value
('admin', 'tsisadmin', '관리자', 'admin@tsis.co.kr', '9', now(), now());



CREATE TABLE tb_tsis (
  seq bigint(16) NOT NULL AUTO_INCREMENT,
  gubun varchar(10) NOT NULL,
  dept varchar(100) NOT NULL,
  team varchar(100) NOT NULL,
  part varchar(100) NOT NULL,
  id varchar(30) NULL,
  name varchar(100) NOT NULL,
  jobgroup varchar(50) NULL,
  regdate datetime NULL,
  PRIMARY KEY (seq)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE tb_register_tsis (
  seq bigint(16) NOT NULL AUTO_INCREMENT,
  gubun varchar(10) NOT NULL,
  s_id varchar(30) NOT NULL,
  s_name varchar(100) NOT NULL,
  r_name varchar(100) NOT NULL,
  r_phone varchar(20) NOT NULL,
  r_post varchar(10) NOT NULL,
  r_address varchar(2000) NOT NULL,
  r_detailAddress varchar(2000) NOT NULL,
  regdate datetime NOT NULL,
  PRIMARY KEY (`seq`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

insert into tb_tsis (gubun, dept, team, part, id, name, jobgroup, regdate) 
values ('202012','ITO 사업부','공통서비스팀','모바일서비스','28054926','강창구','일반직',sysdate());

GRANT ALL ON tsis_delivery.*TO tsis@'%' IDENTIFIED BY 'tsis';

FLUSH PRIVILEGES;


CREATE TABLE tb_interview_time (
  seq bigint(16) NOT NULL AUTO_INCREMENT,
  interview_day varchar(10) NOT NULL,
  timezone varchar(11) NOT NULL,
  PRIMARY KEY (seq)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--insert tb_interview_time (interview_day, timezone) values ('2021-02-04','09:00');
--insert tb_interview_time (interview_day, timezone) values ('2021-02-04','09:35');
--insert tb_interview_time (interview_day, timezone) values ('2021-02-04','10:10');
--insert tb_interview_time (interview_day, timezone) values ('2021-02-04','10:45');
--insert tb_interview_time (interview_day, timezone) values ('2021-02-04','11:20');
--insert tb_interview_time (interview_day, timezone) values ('2021-02-04','13:00');
--insert tb_interview_time (interview_day, timezone) values ('2021-02-04','13:35');
--insert tb_interview_time (interview_day, timezone) values ('2021-02-04','14:10');
--insert tb_interview_time (interview_day, timezone) values ('2021-02-04','14:45');
--insert tb_interview_time (interview_day, timezone) values ('2021-02-04','15:20');
--insert tb_interview_time (interview_day, timezone) values ('2021-02-04','15:55');
--insert tb_interview_time (interview_day, timezone) values ('2021-02-04','16:30');
--insert tb_interview_time (interview_day, timezone) values ('2021-02-04','17:05');
--
--insert tb_interview_time (interview_day, timezone) values ('2021-02-05','09:00');
--insert tb_interview_time (interview_day, timezone) values ('2021-02-05','09:35');
--insert tb_interview_time (interview_day, timezone) values ('2021-02-05','10:10');
--insert tb_interview_time (interview_day, timezone) values ('2021-02-05','10:45');
--insert tb_interview_time (interview_day, timezone) values ('2021-02-05','14:40');
--insert tb_interview_time (interview_day, timezone) values ('2021-02-05','15:15');
--insert tb_interview_time (interview_day, timezone) values ('2021-02-05','15:50');
--
--insert tb_interview_time (interview_day, timezone) values ('2021-02-08','13:00');
--insert tb_interview_time (interview_day, timezone) values ('2021-02-08','13:35');
--insert tb_interview_time (interview_day, timezone) values ('2021-02-08','14:10');
--insert tb_interview_time (interview_day, timezone) values ('2021-02-08','14:45');
--insert tb_interview_time (interview_day, timezone) values ('2021-02-08','15:20');



CREATE TABLE tb_interview_phoneNumber (
  seq bigint(16) NOT NULL AUTO_INCREMENT,
  user_name varchar(50) NOT NULL,
  phone_number varchar(30) NOT NULL,
  PRIMARY KEY (seq)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE tb_interview_reservation (
  seq bigint(16) NOT NULL AUTO_INCREMENT,
  interview_time_seq varchar(10) NOT NULL,
  phone varchar(30) NOT NULL,
  regdate datetime NULL,
  PRIMARY KEY (seq)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;