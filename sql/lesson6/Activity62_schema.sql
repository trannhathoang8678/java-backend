-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Student`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Student` (
  `id_Student` VARCHAR(10) NOT NULL,
  `nameOfStudent` VARCHAR(45) NULL,
  `class` VARCHAR(10) NULL,
  PRIMARY KEY (`id_Student`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `mydb`.`Teacher`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Teacher` (
  `id_Teacher` VARCHAR(10) NOT NULL,
  `academicRank` VARCHAR(45) NULL,
  `degree` VARCHAR(45) NULL,
  PRIMARY KEY (`id_Teacher`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Research`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Research` (
  `id_Research` VARCHAR(10) NOT NULL,
  `nameOfResearch` VARCHAR(45) NULL,
  `Field` VARCHAR(10) NULL,
  `id_Teacher` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`id_Research`),
  INDEX `fk_Research_Teacher1_idx` (`id_Teacher` ASC) VISIBLE,
  CONSTRAINT `fk_Research_Teacher1`
    FOREIGN KEY (`id_Teacher`)
    REFERENCES `mydb`.`Teacher` (`id_Teacher`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `mydb`.`Student_Research`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Student_Research` (
  `id_Member` VARCHAR(10) NOT NULL,
  `id_Research` VARCHAR(10) NOT NULL,
  `isLeader` TINYINT NOT NULL,
  INDEX `fk_Student_Research_Student1_idx` (`id_Member` ASC) VISIBLE,
  INDEX `fk_Student_Research_Research1_idx` (`id_Research` ASC) VISIBLE,
  CONSTRAINT `fk_Student_Research_Student1`
    FOREIGN KEY (`id_Member`)
    REFERENCES `mydb`.`Student` (`id_Student`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Student_Research_Research1`
    FOREIGN KEY (`id_Research`)
    REFERENCES `mydb`.`Research` (`id_Research`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
