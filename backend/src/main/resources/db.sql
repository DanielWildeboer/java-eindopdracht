CREATE DATABASE  IF NOT EXISTS `java_minor_eindopdracht`;
USE `java_minor_eindopdracht`;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `project_group` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;


--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `grade_assessment` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Table structure for table `group`
--

DROP TABLE IF EXISTS `projectGroup`;
CREATE TABLE `projectGroup` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `grade` double DEFAULT NULL,
  `students` varchar(255) DEFAULT NULL,
  `status` BOOLEAN,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Table structure for table `projectGroup`
--

DROP TABLE IF EXISTS `grade_assessment`;
CREATE TABLE `grade_assessment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_group_id` varchar(255) DEFAULT NULL,
  `student_id` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `receiver_student` varchar(255) DEFAULT NULL,
  `grade` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Table structure for table `token`
--

DROP TABLE IF EXISTS `token`;
CREATE TABLE `token` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_group_id` varchar(255) DEFAULT NULL,
  `student_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;