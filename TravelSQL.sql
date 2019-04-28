-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema travel
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema travel
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `travel` DEFAULT CHARACTER SET utf8 ;
USE `travel` ;

-- -----------------------------------------------------
-- Table `travel`.`room`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travel`.`room` (
  `idroom` INT(11) NOT NULL,
  `room_size` VARCHAR(45) NOT NULL,
  `pricePerDay` DOUBLE NOT NULL,
  PRIMARY KEY (`idroom`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `travel`.`hotel`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travel`.`hotel` (
  `idhotel` INT(11) NOT NULL AUTO_INCREMENT,
  `name_hotel` VARCHAR(45) NOT NULL,
  `room_idroom` INT(11) NOT NULL,
  PRIMARY KEY (`idhotel`, `room_idroom`),
  INDEX `fk_hotel_room_idx` (`room_idroom` ASC),
  CONSTRAINT `fk_hotel_room`
    FOREIGN KEY (`room_idroom`)
    REFERENCES `travel`.`room` (`idroom`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `travel`.`tour`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travel`.`tour` (
  `idtour` INT(11) NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NOT NULL,
  `price` DOUBLE NOT NULL,
  PRIMARY KEY (`idtour`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `travel`.`transport`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travel`.`transport` (
  `idtransport` INT(11) NOT NULL AUTO_INCREMENT,
  `transport_type` VARCHAR(45) CHARACTER SET 'utf8' NOT NULL,
  `ticket_price` DOUBLE NOT NULL,
  PRIMARY KEY (`idtransport`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `travel`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travel`.`role` (
  `idrole` INT NOT NULL,
  `role` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idrole`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `travel`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travel`.`user` (
  `iduser` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) CHARACTER SET 'utf8' NOT NULL,
  `surname` VARCHAR(45) CHARACTER SET 'utf8' NOT NULL,
  `email` VARCHAR(45) CHARACTER SET 'utf8' NOT NULL,
  `discount` INT NULL DEFAULT 0,
  `login` VARCHAR(45) CHARACTER SET 'utf8' NOT NULL,
  `password` VARCHAR(45) CHARACTER SET 'utf8' NOT NULL,
  `role_idrole` INT NOT NULL,
  PRIMARY KEY (`iduser`, `role_idrole`),
  INDEX `fk_user_role1_idx` (`role_idrole` ASC),
  CONSTRAINT `fk_user_role1`
    FOREIGN KEY (`role_idrole`)
    REFERENCES `travel`.`role` (`idrole`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `travel`.`country`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travel`.`country` (
  `idcountry` INT NOT NULL AUTO_INCREMENT,
  `name_country` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idcountry`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `travel`.`vaucher`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travel`.`vaucher` (
  `idvaucher` INT NOT NULL AUTO_INCREMENT,
  `dateFrom` DATE NOT NULL,
  `dateTo` DATE NOT NULL,
  `hottour` TINYINT(1) NULL,
  `totalPrice` VARCHAR(45) NOT NULL,
  `tour_idtour` INT(11) NOT NULL,
  `transport_idtransport` INT(11) NOT NULL,
  `country_idcountry` INT NOT NULL,
  `hotel_idhotel` INT(11) NOT NULL,
  `hotel_room_idroom` INT(11) NOT NULL,
  PRIMARY KEY (`idvaucher`, `tour_idtour`, `transport_idtransport`, `country_idcountry`, `hotel_idhotel`, `hotel_room_idroom`),
  INDEX `fk_vaucher_tour1_idx` (`tour_idtour` ASC),
  INDEX `fk_vaucher_transport1_idx` (`transport_idtransport` ASC),
  INDEX `fk_vaucher_country1_idx` (`country_idcountry` ASC),
  INDEX `fk_vaucher_hotel1_idx` (`hotel_idhotel` ASC, `hotel_room_idroom` ASC),
  CONSTRAINT `fk_vaucher_tour1`
    FOREIGN KEY (`tour_idtour`)
    REFERENCES `travel`.`tour` (`idtour`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_vaucher_transport1`
    FOREIGN KEY (`transport_idtransport`)
    REFERENCES `travel`.`transport` (`idtransport`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_vaucher_country1`
    FOREIGN KEY (`country_idcountry`)
    REFERENCES `travel`.`country` (`idcountry`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_vaucher_hotel1`
    FOREIGN KEY (`hotel_idhotel` , `hotel_room_idroom`)
    REFERENCES `travel`.`hotel` (`idhotel` , `room_idroom`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `travel`.`order_history`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travel`.`order_history` (
  `idorder_history` INT NOT NULL,
  `vaucher_idvaucher` INT NOT NULL,
  `user_iduser` INT(11) NOT NULL,
  PRIMARY KEY (`idorder_history`, `vaucher_idvaucher`, `user_iduser`),
  INDEX `fk_order_history_vaucher1_idx` (`vaucher_idvaucher` ASC),
  INDEX `fk_order_history_user1_idx` (`user_iduser` ASC),
  CONSTRAINT `fk_order_history_vaucher1`
    FOREIGN KEY (`vaucher_idvaucher`)
    REFERENCES `travel`.`vaucher` (`idvaucher`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_history_user1`
    FOREIGN KEY (`user_iduser`)
    REFERENCES `travel`.`user` (`iduser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
