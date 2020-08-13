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
-- Table `mydb`.`Product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Product` (
  `id_Product` VARCHAR(10) NOT NULL,
  `nameOfProduct` VARCHAR(45) NOT NULL,
  `unit` VARCHAR(16) NULL,
  `quantityAvaiable` INT NULL,
  `priceAtPresent` DECIMAL NULL,
  PRIMARY KEY (`id_Product`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `mydb`.`ImportCoudon`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`ImportCoudon` (
  `id_ImportCoudon` VARCHAR(10) NOT NULL,
  `dateImport` TIMESTAMP NULL,
  `nameOfSupplier` VARCHAR(45) NULL,
  PRIMARY KEY (`id_ImportCoudon`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `mydb`.`ExportBill`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`ExportBill` (
  `id_ExportBill` VARCHAR(10) NOT NULL,
  `dateExport` TIMESTAMP NULL,
  `nameOfCustomer` VARCHAR(45) NULL,
  PRIMARY KEY (`id_ExportBill`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `mydb`.`Import`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Import` (
  `id_Product` VARCHAR(10) NOT NULL,
  `id_ImportCoudon` VARCHAR(10) NOT NULL,
  `numberOfProducts` INT NULL,
  `priceOfImport` DECIMAL NULL,
  INDEX `fk_Import_Product_idx` (`id_Product` ASC) VISIBLE,
  INDEX `fk_Import_ImportCoudon1_idx` (`id_ImportCoudon` ASC) VISIBLE,
  CONSTRAINT `fk_Import_Product`
    FOREIGN KEY (`id_Product`)
    REFERENCES `mydb`.`Product` (`id_Product`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Import_ImportCoudon1`
    FOREIGN KEY (`id_ImportCoudon`)
    REFERENCES `mydb`.`ImportCoudon` (`id_ImportCoudon`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Export`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Export` (
  `id_Product` VARCHAR(10) NOT NULL,
  `id_ExportBill` VARCHAR(10) NOT NULL,
  `nameOfProduct` VARCHAR(45) NULL,
  `priceOfExport` DECIMAL NULL,
  INDEX `fk_Export_Product1_idx` (`id_Product` ASC) VISIBLE,
  INDEX `fk_Export_ExportBill1_idx` (`id_ExportBill` ASC) VISIBLE,
  CONSTRAINT `fk_Export_Product1`
    FOREIGN KEY (`id_Product`)
    REFERENCES `mydb`.`Product` (`id_Product`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Export_ExportBill1`
    FOREIGN KEY (`id_ExportBill`)
    REFERENCES `mydb`.`ExportBill` (`id_ExportBill`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
