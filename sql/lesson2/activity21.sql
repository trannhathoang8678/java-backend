CREATE SCHEMA IF NOT EXISTS student_cms_plusplus DEFAULT CHARACTER SET UTF8MB4;
USE student_cms_plusplus;
-- id (PK, AI), name, mssv, password, phone, address, age, email, created_timstamp, last_updated_timestamp
CREATE TABLE IF NOT EXISTS `student` (
    `id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(32) NOT NULL,
    `mssv` VARCHAR(10) NOT NULL,
    `password` VARCHAR(16) NOT NULL,
    `phone` VARCHAR(10) NULL,
    `address` VARCHAR(50) NULL,
    `age` TINYINT NULL,
    `email` VARCHAR(50) NOT NULL,
    `created_timestamp` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `last_updated_timestamp` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
--  id (PK, AI), name, major, total_student, teacher_name, teacher_phone, created_timestamp, last_updated_timestamp
CREATE TABLE IF NOT EXISTS `class` (
    `id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(32) NULL,
    `major` VARCHAR(20) NOT NULL,
    `total_student` SMALLINT NULL,
    `teacher_name` VARCHAR(32) NULL,
    `teacher_phone` VARCHAR(10) NULL,
    `created_timestamp` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `last_updated_timestamp` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
CREATE TABLE IF NOT EXISTS `student_class` (
    `student_id` INT NOT NULL,
    CONSTRAINT FOREIGN KEY (student_id)
        REFERENCES student (id),
    `class_id` INT NOT NULL,
    CONSTRAINT FOREIGN KEY (class_id)
        REFERENCES class (id)
);
INSERT INTO `student_cms_plusplus`.`student` (`id`,`name`,`mssv`,`password`,`phone`,`address`,`age`,`email`,`created_timestamp`,`last_updated_timestamp`) VALUES ('1','Trần Nhật Hoàng','1E01','123456','0329609321','33 Nguyễn Công Trứ','18','trannhathoang8678@gmail.com',CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP());
INSERT INTO `student_cms_plusplus`.`student` (`id`,`name`,`mssv`,`password`,`age`,`email`,`created_timestamp`,`last_updated_timestamp`) VALUES ('2','Nguyễn Trần Vinh ','1E02','123456','18','vinhnt@gmail.com',CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP());
INSERT INTO `student_cms_plusplus`.`class` (`id`,`name`,`major`,`total_student`,`teacher_name`,`created_timestamp`,`last_updated_timestamp`) VALUES ('1','JVBE','JAVA BACKEND','12','Nguyễn Xuân Kiểm',CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP());
INSERT INTO `student_cms_plusplus`.`class` (`id`,`name`,`major`,`created_timestamp`,`last_updated_timestamp`) VALUES ('2','JVFT','JAVA FULLSTACK',CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP());
INSERT INTO `student_cms_plusplus`.`student_class` (`student_id`,`class_id`) VALUES ('1','1');
INSERT INTO `student_cms_plusplus`.`student_class` (`student_id`,`class_id`) VALUES ('2','2');
