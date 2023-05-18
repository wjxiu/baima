/*
SQLyog Professional v12.08 (64 bit)
MySQL - 8.0.28 : Database - baima
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE
DATABASE /*!32312 IF NOT EXISTS*/`baima` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE
`baima`;

/*Table structure for table `admission_plan` */

DROP TABLE IF EXISTS `admission_plan`;

CREATE TABLE `admission_plan`
(
    `id`           char(19)                                                      NOT NULL,
    `name`         varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL COMMENT '招生计划名字',
    `info`         varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '简介',
    `course_type`  tinyint                                                       NOT NULL COMMENT '课程类别',
    `duration`     tinyint                                                       NOT NULL COMMENT '多久毕业，单位：月',
    `year` year NOT NULL COMMENT '所属年份，YYYY',
    `gmt_create`   datetime DEFAULT NULL,
    `gmt_modified` datetime DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `admission_plan` */

insert into `admission_plan`(`id`, `name`, `info`, `course_type`, `duration`, `year`, `gmt_create`, `gmt_modified`)
values ('1651070519659659265', 'Java招生计划', '我是一个简介', 1, 25, 2022, NULL, NULL),
       ('1656920480610037762', '测试招生计划', '啦啦啦啦', 1, 24, 2022, '2023-05-12 15:13:21', '2023-05-12 15:13:21'),
       ('1656924514205102081', '测试323333招生计划', '啦啦eerr啦啦', 1, 24, 2022, '2023-05-12 15:29:23', '2023-05-12 15:29:23'),
       ('1656925771393245186', 'dfsdfsdsdsdfdsdsfdfs', 'sfdsfdf', 1, 3, 2022, '2023-05-12 15:34:23',
        '2023-05-12 15:34:23'),
       ('1656926856484864002', 'dfsdfsdsdsdfdsdsfdfs', 'sfdsfdf', 1, 3, 2022, '2023-05-12 15:38:41',
        '2023-05-12 15:38:41');

/*Table structure for table `article` */

DROP TABLE IF EXISTS `article`;

CREATE TABLE `article`
(
    `id`           char(19)    NOT NULL,
    `public_time`  date        NOT NULL COMMENT '发布日期',
    `view`         int DEFAULT '0' COMMENT '点击量',
    `ac_id`        char(19)    NOT NULL COMMENT '文章分类id',
    `author_id`    char(19)    NOT NULL COMMENT '作者id',
    `title`        varchar(64) NOT NULL,
    `content`      text        NOT NULL COMMENT '内容，存储富文本',
    `gmt_modified` datetime    NOT NULL,
    `gmt_create`   datetime    NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `title` (`title`),
    KEY            `author_id` (`author_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `article` */

insert into `article`(`id`, `public_time`, `view`, `ac_id`, `author_id`, `title`, `content`, `gmt_modified`,
                      `gmt_create`)
values ('1', '2023-05-12', 0, '2', '1', '这是标题', '这是内容', '2023-05-12 13:10:04', '2023-05-12 13:10:05'),
       ('1656920480610037762', '2023-05-12', 0, '1', '1', '测试招生计划', '啦啦啦', '2023-05-12 15:13:36',
        '2023-05-12 15:13:36'),
       ('1656924514205102081', '2023-05-12', 0, '1', '1', '测试323333招生计划', '啦啦w3ewweew啦', '2023-05-12 15:29:23',
        '2023-05-12 15:29:23'),
       ('1656925771393245186', '2023-05-12', 0, '1', '1', 'dfsdfsdsdsdfdsdsfdfs', 'ssdffdsfsf', '2023-05-12 15:34:23',
        '2023-05-12 15:34:23'),
       ('2fb4d265277b4687b09', '2023-05-12', 0, '1', '1', '3121322113221312', '23123123213', '2023-05-12 16:09:11',
        '2023-05-12 16:09:11'),
       ('3779d3abaedf40e1a0c', '2023-05-12', 0, '1', '1', 'aaaqwewqeaa', 'aaaaa', '2023-05-12 15:28:36',
        '2023-05-12 15:28:36'),
       ('4c28aa6594fb49ecb6e', '2023-05-12', 0, '1', '1', '3123123123123312', '123213132213', '2023-05-12 16:28:33',
        '2023-05-12 16:28:33');

/*Table structure for table `article_category` */

DROP TABLE IF EXISTS `article_category`;

CREATE TABLE `article_category`
(
    `id`           char(19)    NOT NULL,
    `name`         varchar(30) NOT NULL COMMENT '分类名称',
    `p_id`         char(19)    NOT NULL DEFAULT '0' COMMENT '文章分类父ID',
    `gmt_create`   datetime    NOT NULL,
    `gmt_modified` datetime    NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `article_category` */

insert into `article_category`(`id`, `name`, `p_id`, `gmt_create`, `gmt_modified`)
values ('1', '招生计划', '0', '2023-05-11 11:56:53', '2023-05-11 11:56:56'),
       ('2', 'Java', '0', '2023-05-12 13:01:16', '2023-05-12 13:01:17');

/*Table structure for table `chat_log` */

DROP TABLE IF EXISTS `chat_log`;

CREATE TABLE `chat_log`
(
    `id`          char(19) NOT NULL,
    `context`     text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '消息内容',
    `from_ip`     int unsigned DEFAULT NULL COMMENT 'ip使用Int存储，保存时要转换',
    `customer_id` int DEFAULT NULL COMMENT '注册用户id',
    `manger_id`   char(19) NOT NULL COMMENT '招生专员id',
    `send_time`   datetime NOT NULL COMMENT '消息发送时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `chat_log` */

insert into `chat_log`(`id`, `context`, `from_ip`, `customer_id`, `manger_id`, `send_time`)
values ('1658030157490610177', 'aaa', NULL, 1, '1', '2023-05-15 16:42:49'),
       ('1658031734670508033', '1111', 1, 111, '1', '2023-05-15 16:49:05'),
       ('1658042534737158146', '1111', 3232257875, 111, '1', '2023-05-15 17:32:00'),
       ('1658042716006588418', '1111', 3232257875, 111, '1', '2023-05-15 17:32:43'),
       ('1658042719823405057', '1111', 3232257875, 111, '1', '2023-05-15 17:32:44'),
       ('1658042722998493185', '1111', 3232257875, 111, '1', '2023-05-15 17:32:45'),
       ('1658042725229862913', '1111', 3232257875, 111, '1', '2023-05-15 17:32:45'),
       ('1658042727528341506', '1111', 3232257875, 111, '1', '2023-05-15 17:32:46');

/*Table structure for table `course` */

DROP TABLE IF EXISTS `course`;

CREATE TABLE `course`
(
    `id`              char(19)                                                     NOT NULL,
    `info`            varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci         DEFAULT NULL COMMENT '介绍',
    `name`            varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '名字',
    `charge`          decimal(10, 0)                                                        DEFAULT NULL COMMENT '报班费用',
    `max_class_num`   smallint                                                     NOT NULL DEFAULT '30' COMMENT '最大班级人数',
    `current_num`     smallint                                                     NOT NULL DEFAULT '0' COMMENT '当前人数',
    `max_class_count` smallint                                                     NOT NULL DEFAULT '3' COMMENT '最大班级数量',
    `type`            tinyint                                                      NOT NULL DEFAULT '0' COMMENT '课程类型，枚举，0：前端 ，1：后端 ，2：大数据，3：UI，4：测试 ，5：运维',
    `start_time`      datetime                                                              DEFAULT NULL COMMENT '开班时间',
    `gmt_create`      datetime                                                              DEFAULT NULL,
    `gmt_modified`    datetime                                                              DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `course` */

insert into `course`(`id`, `info`, `name`, `charge`, `max_class_num`, `current_num`, `max_class_count`, `type`,
                     `start_time`, `gmt_create`, `gmt_modified`)
values ('1', '超级厉害的Java课程，月入过万', 'java高级课程', '0', 3, 11, 10, 1, '2023-06-12 10:12:33', '2023-05-12 18:20:53',
        '2023-05-18 20:31:36'),
       ('1656967920277110785', '超级厉害的Java课程，月入过万', 'java高级课程5', '0', 3, 0, 10, 1, '2023-05-12 10:12:33',
        '2023-05-12 18:21:52', '2023-05-12 18:21:52'),
       ('1656967933631774722', '超级厉害的Java课程，月入过万', 'java高级课程6', '0', 3, 0, 10, 1, '2023-05-12 10:12:33',
        '2023-05-12 18:21:55', '2023-05-12 18:21:55'),
       ('1656967948370558977', '超级厉害的Java课程，月入过万', 'java高级课程7', '0', 3, 0, 10, 1, '2023-05-12 10:12:33',
        '2023-05-12 18:21:59', '2023-05-12 18:21:59'),
       ('1656968042327162881', '超级厉害的Java课程，月入过万', 'java高级课程7', '0', 3, 0, 10, 1, '2023-05-12 10:12:33',
        '2023-05-12 18:22:21', '2023-05-12 18:22:21'),
       ('1657181560137232385', '111', 'ggg', '333', 2, 0, 1, 0, '2023-05-13 00:29:48', '2023-05-13 08:30:48',
        '2023-05-13 08:30:48'),
       ('1657250060519624705', 'aaa', 'aaaaa', '555', 2, 0, 2, 0, '2023-05-13 04:57:46', '2023-05-13 13:02:59',
        '2023-05-13 13:02:59'),
       ('1657250079863754753', 'aaa', 'aaa1aa', '555', 2, 0, 2, 0, '2023-05-13 04:57:46', '2023-05-13 13:03:04',
        '2023-05-13 13:03:04'),
       ('1657250089946861570', 'aaa', 'aaa11aa', '555', 2, 0, 2, 0, '2023-05-13 04:57:46', '2023-05-13 13:03:06',
        '2023-05-13 13:03:06'),
       ('1657250098071228418', 'aaa', 'aaa111aa', '555', 2, 0, 2, 0, '2023-05-13 04:57:46', '2023-05-13 13:03:08',
        '2023-05-13 13:03:08'),
       ('2', '超级厉害的Java课程，月入过万', 'java高级课程1', '0', 3, 0, 10, 1, '2023-05-12 10:12:33', '2023-05-12 18:21:38',
        '2023-05-12 18:21:38'),
       ('3', '超级厉害的Java课程，月入过万', 'java高级课程2', '0', 3, 0, 10, 1, '2023-05-12 10:12:33', '2023-05-12 18:21:42',
        '2023-05-12 18:21:42'),
       ('4', '超级厉害的Java课程，月入过万', 'java高级课程3', '0', 3, 0, 10, 1, '2023-05-12 10:12:33', '2023-05-12 18:21:45',
        '2023-05-12 18:21:45'),
       ('5', '超级厉害的Java课程，月入过万', 'java高级课程4', '0', 3, 0, 10, 1, '2023-05-12 10:12:33', '2023-05-12 18:21:48',
        '2023-05-12 18:21:48');

/*Table structure for table `customer` */

DROP TABLE IF EXISTS `customer`;

CREATE TABLE `customer`
(
    `id`              char(19)                                                     NOT NULL,
    `name`            varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `age`             tinyint                                                       DEFAULT NULL,
    `gender`          tinyint                                                       DEFAULT '0' COMMENT '枚举 ,0:男，1:女',
    `phone`           char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci     DEFAULT NULL COMMENT '电话号码',
    `qq_number`       varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  DEFAULT NULL COMMENT 'qq号',
    `graduate_school` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  DEFAULT NULL COMMENT '毕业学校',
    `major`           varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  DEFAULT NULL COMMENT '专业',
    `academic_degree` tinyint                                                       DEFAULT NULL COMMENT '学历 枚举：0：高中，1:大专，2:本科，3:研究生，4:其他',
    `status`          tinyint                                                       DEFAULT NULL COMMENT '自己的状态， 枚举：在读，在职，待业，其他',
    `profile`         text COMMENT '自己的简介',
    `password`        varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '密码',
    `gmt_modified`    datetime                                                      DEFAULT NULL,
    `gmt_create`      datetime                                                      DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `name` (`name`),
    UNIQUE KEY `phone` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `customer` */

insert into `customer`(`id`, `name`, `age`, `gender`, `phone`, `qq_number`, `graduate_school`, `major`,
                       `academic_degree`, `status`, `profile`, `password`, `gmt_modified`, `gmt_create`)
values ('1', '测试用户名', 55, 0, '321122', '123123', '北京大学', '护理', 0, 0, '我是斜杠青年', 'e10adc3949ba59abbe56e057f20f883e',
        '2023-05-18 19:22:40', '2023-05-12 16:07:52'),
       ('1658364429493559298', 'user', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'e10adc3949ba59abbe56e057f20f883e', '2023-05-16 14:51:06', '2023-05-16 14:51:06'),
       ('1658373501512998914', 'user1', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'e10adc3949ba59abbe56e057f20f883e', '2023-05-16 15:27:09', '2023-05-16 15:27:09'),
       ('1658373846729375746', '哈哈哈', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'e10adc3949ba59abbe56e057f20f883e', '2023-05-16 15:28:31', '2023-05-16 15:28:31'),
       ('1658801466836406274', '888', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'e10adc3949ba59abbe56e057f20f883e', '2023-05-17 19:47:43', '2023-05-17 19:47:43'),
       ('1658808063539093505', '123', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        '202cb962ac59075b964b07152d234b70', '2023-05-17 20:13:56', '2023-05-17 20:13:56'),
       ('2', '测试用户名1', 55, 1, '123456', '123123', '北京大学', '护理', 1, 0, '我是斜杠青年', 'e10adc3949ba59abbe56e057f20f883e',
        '2023-05-18 17:14:53', '2023-05-12 16:21:26'),
       ('3', '测试用户名2', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'e10adc3949ba59abbe56e057f20f883e',
        '2023-05-12 16:21:30', '2023-05-12 16:21:30');

/*Table structure for table `manager` */

DROP TABLE IF EXISTS `manager`;

CREATE TABLE `manager`
(
    `id`           char(19)                                                      NOT NULL,
    `username`     varchar(64)                                                   NOT NULL,
    `password`     varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `phone`        char(11)                                                     DEFAULT NULL,
    `qq_number`    varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
    `type`         tinyint(1) NOT NULL DEFAULT '1' COMMENT '枚举 ,0管理员，1 推广专员',
    `gmt_modified` datetime                                                     DEFAULT NULL,
    `gmt_create`   datetime                                                     DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `manager` */

insert into `manager`(`id`, `username`, `password`, `phone`, `qq_number`, `type`, `gmt_modified`, `gmt_create`)
values ('1', '测试管理员', '123', '12345678901', '12345678', 0, '2023-05-12 13:02:40', '2023-05-12 13:02:42'),
       ('1658033772884148225', '13213123', '$2a$10$uWXO2FcMTh0RBxhc8SIvBO9En4ODPw4tvcUxW3116NXR4tFdSuxwG', NULL, NULL,
        1, '2023-05-15 16:57:11', '2023-05-15 16:57:11'),
       ('1658082605303357441', '123', '$2a$10$PkIWUwjecAeIx4h.2bjwyeCiNGr9nF7JNiiCehNHa5AlCJhhN.1TK', NULL, NULL, 1,
        '2023-05-15 20:11:13', '2023-05-15 20:11:13'),
       ('1658088241902673921', '12313212132132', '$2a$10$I8ox/LTMPCXJeptSYfEAreJKaqcxMQfulq4E7MjV0zHek5JlgRAKq', NULL,
        NULL, 1, '2023-05-15 20:33:37', '2023-05-15 20:33:37'),
       ('2', '测试管理员1', '$2a$10$Ealta8y1n3vRS8abGEHhCuAB4ayh/YnV.4ruGqqQItfKFee5f40oK', '111', NULL, 1,
        '2023-05-13 13:30:16', '2023-05-13 13:30:16');

/*Table structure for table `registration` */

DROP TABLE IF EXISTS `registration`;

CREATE TABLE `registration`
(
    `id`               char(19)                                                  NOT NULL,
    `customer_id`      char(19)                                                  NOT NULL,
    `perfer_course_id` char(19)                                                  NOT NULL COMMENT '报名的课程id',
    `enroll_status`    tinyint                                                   NOT NULL COMMENT '报名状态,枚举：待检查， 检查中，报名失败，报名成功',
    `manager_id`       char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '-1' COMMENT '审核人员',
    `gmt_modified`     datetime                                                           DEFAULT NULL,
    `gmt_create`       datetime                                                           DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY                `customer_id` (`customer_id`),
    KEY                `manager_id` (`manager_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `registration` */

insert into `registration`(`id`, `customer_id`, `perfer_course_id`, `enroll_status`, `manager_id`, `gmt_modified`,
                           `gmt_create`)
values ('1', '1', '1', 2, '1', '2023-05-18 20:31:36', '2023-05-13 10:45:15'),
       ('2', '1', '2', 0, '1', '2023-05-13 14:34:18', '2023-05-13 14:34:20'),
       ('3', '1', '3', 0, '1', '2023-05-13 14:34:37', '2023-05-13 14:34:39');

/*Table structure for table `trial_lesson` */

DROP TABLE IF EXISTS `trial_lesson`;

CREATE TABLE `trial_lesson`
(
    `id`                char(19)                                                     NOT NULL,
    `course_id`         char(19)                                                     NOT NULL COMMENT '关联的课程id',
    `location`          varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '地点',
    `max_customer_num`  tinyint                                                      NOT NULL DEFAULT '30' COMMENT '试课人数上限',
    `curr_customer_num` tinyint                                                      NOT NULL DEFAULT '0' COMMENT '当前人数',
    `gmt_modified`      datetime                                                              DEFAULT NULL,
    `gmt_create`        datetime                                                              DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `trial_lesson` */

insert into `trial_lesson`(`id`, `course_id`, `location`, `max_customer_num`, `curr_customer_num`, `gmt_modified`,
                           `gmt_create`)
values ('1657687780572033025', '1', '天安门', 30, 7, '2023-05-18 17:14:53', '2023-05-14 18:02:20'),
       ('1657690499558944769', '2', '天安门1', 30, 0, '2023-05-14 18:13:08', '2023-05-14 18:13:08'),
       ('1657699170816942081', '2', '白宫', 30, 3, '2023-05-18 19:22:40', '2023-05-14 18:47:36'),
       ('1657699201989009410', '1', '白宫', 30, 0, '2023-05-14 18:47:43', '2023-05-14 18:47:43'),
       ('1659172447432761345', '1', '法国卢浮宫', 30, 0, '2023-05-18 20:23:03', '2023-05-18 20:21:52');

/*Table structure for table `trial_lesson_comment` */

DROP TABLE IF EXISTS `trial_lesson_comment`;

CREATE TABLE `trial_lesson_comment`
(
    `id`          char(19)                                                      NOT NULL,
    `context`     varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '评论内容',
    `trial_id`    char(19) DEFAULT NULL COMMENT '试课课程id',
    `customer_id` char(19) DEFAULT NULL COMMENT '试课的客户id',
    `score`       tinyint  DEFAULT NULL COMMENT '评分 [1,5]',
    `gmt_create`  datetime DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `trial_lesson_comment` */

insert into `trial_lesson_comment`(`id`, `context`, `trial_id`, `customer_id`, `score`, `gmt_create`)
values ('1657355723753222146', '评论内容', '1657687780572033025', '1', 1, '2023-05-13 20:02:51'),
       ('1657355724281704450', '评论内容', '1657687780572033025', '2', 2, '2023-05-13 20:02:52'),
       ('1657355724814381058', '评论内容', '1657687780572033025', '3', 3, '2023-05-13 20:02:52'),
       ('1657355725409972225', '评论内容', '1657699170816942081', '4', 4, '2023-05-13 20:02:52'),
       ('1657355725946843137', '评论内容', '1657699170816942081', '5', 5, '2023-05-13 20:02:52'),
       ('1657355726685040642', '评论内容', '1657699170816942081', '6', 2, '2023-05-13 20:02:52'),
       ('1657355727221911554', '评论内容', '1657699170816942081', '7', 1, '2023-05-13 20:02:52'),
       ('1657355727754588162', '评论内容', '1657699170816942081', '9', 3, '2023-05-13 20:02:52');

/*Table structure for table `trial_lesson_customer` */

DROP TABLE IF EXISTS `trial_lesson_customer`;

CREATE TABLE `trial_lesson_customer`
(
    `id`               char(19) NOT NULL,
    `customer_id`      char(19) NOT NULL COMMENT '顾客id',
    `trail_lession_id` char(19) NOT NULL COMMENT '顾客试课的id',
    `gmt_modified`     datetime DEFAULT NULL,
    `period`           tinyint  NOT NULL COMMENT '选择试课的时间段,0: 09-11时,1: 15-17时,2: 20-22时',
    `gmt_create`       datetime DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `customer_id` (`customer_id`,`trail_lession_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `trial_lesson_customer` */

insert into `trial_lesson_customer`(`id`, `customer_id`, `trail_lession_id`, `gmt_modified`, `period`, `gmt_create`)
values ('1657700997549539329', '3', '1657687780572033025', '2023-05-14 18:54:51', 0, '2023-05-14 18:54:51'),
       ('1657701013693415426', '4', '1657690499558944769', '2023-05-14 18:54:55', 0, '2023-05-14 18:54:55'),
       ('1659123136692219905', '1', '1657687780572033025', '2023-05-18 17:05:55', 0, '2023-05-18 17:05:55'),
       ('1659125391491293186', '2', '1657687780572033025', '2023-05-18 17:14:53', 0, '2023-05-18 17:14:53'),
       ('1659157551103332353', '1', '1657699170816942081', '2023-05-18 19:22:40', 0, '2023-05-18 19:22:40');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
