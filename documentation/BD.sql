-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema vvv
-- -----------------------------------------------------
-- VVV System DB
DROP SCHEMA IF EXISTS `vvv` ;

-- -----------------------------------------------------
-- Schema vvv
--
-- VVV System DB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `vvv` DEFAULT CHARACTER SET utf8 ;
USE `vvv` ;

-- -----------------------------------------------------
-- Table `vvv`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vvv`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(255) NOT NULL,
  `last_name` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `password_hash` BINARY(60) NULL,
  `created_at` DATETIME NOT NULL,
  `updated_at` DATETIME NOT NULL,
  `type` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vvv`.`address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vvv`.`address` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `street` VARCHAR(255) NOT NULL,
  `number` INT NULL,
  `city` VARCHAR(255) NOT NULL,
  `state` VARCHAR(255) NOT NULL,
  `country` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vvv`.`oAuth`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vvv`.`oAuth` (
  `token` BINARY(96) NOT NULL,
  `last_login_at` DATETIME NOT NULL,
  `user_id` INT NOT NULL,
  INDEX `fk_oAuth_user1_idx` (`user_id` ASC),
  PRIMARY KEY (`user_id`),
  CONSTRAINT `fk_oAuth_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `vvv`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vvv`.`employee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vvv`.`employee` (
  `user_id` INT NOT NULL,
  `cpf` VARCHAR(11) NOT NULL,
  `phone` VARCHAR(50) NULL,
  `address_id` INT NOT NULL,
  `post` TINYINT NOT NULL,
  `work_days` BINARY(7) NULL,
  INDEX `fk_employee_user1_idx` (`user_id` ASC),
  UNIQUE INDEX `cpf_UNIQUE` (`cpf` ASC),
  PRIMARY KEY (`user_id`),
  INDEX `fk_employee_address1_idx` (`address_id` ASC),
  UNIQUE INDEX `address_id_UNIQUE` (`address_id` ASC),
  CONSTRAINT `fk_employee_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `vvv`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_employee_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `vvv`.`address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vvv`.`passenger`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vvv`.`passenger` (
  `user_id` INT NOT NULL,
  `cpf` VARCHAR(11) NULL,
  `phone` VARCHAR(50) NOT NULL,
  `occupation` VARCHAR(255) NOT NULL,
  `address_id` INT NOT NULL,
  INDEX `fk_passenger_address1_idx` (`address_id` ASC),
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `address_id_UNIQUE` (`address_id` ASC),
  UNIQUE INDEX `cpf_UNIQUE` (`cpf` ASC),
  CONSTRAINT `fk_passenger_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `vvv`.`address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_passenger_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `vvv`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vvv`.`retail_outlet`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vvv`.`retail_outlet` (
  `id` INT NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `cnpj` VARCHAR(14) NULL,
  `phone` VARCHAR(50) NULL,
  `address_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_retail_outlet_address1_idx` (`address_id` ASC),
  CONSTRAINT `fk_retail_outlet_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `vvv`.`address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vvv`.`staff`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vvv`.`staff` (
  `retail_outlet_id` INT NOT NULL,
  `employee_user_id` INT NOT NULL,
  PRIMARY KEY (`retail_outlet_id`, `employee_user_id`),
  INDEX `fk_retail_outlet_has_employee_employee1_idx` (`employee_user_id` ASC),
  INDEX `fk_retail_outlet_has_employee_retail_outlet1_idx` (`retail_outlet_id` ASC),
  CONSTRAINT `fk_retail_outlet_has_employee_retail_outlet1`
    FOREIGN KEY (`retail_outlet_id`)
    REFERENCES `vvv`.`retail_outlet` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_retail_outlet_has_employee_employee1`
    FOREIGN KEY (`employee_user_id`)
    REFERENCES `vvv`.`employee` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vvv`.`carrier`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vvv`.`carrier` (
  `id` INT NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `url` TEXT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vvv`.`modal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vvv`.`modal` (
  `id` INT NOT NULL,
  `type` INT NOT NULL,
  `capacity` INT NOT NULL,
  `model` VARCHAR(255) NOT NULL,
  `manufacture` YEAR NOT NULL,
  `maintenance_start` DATE NOT NULL,
  `maintenance_end` DATE NOT NULL,
  `carriers_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_modal_carriers1_idx` (`carriers_id` ASC),
  CONSTRAINT `fk_modal_carriers1`
    FOREIGN KEY (`carriers_id`)
    REFERENCES `vvv`.`carrier` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vvv`.`city`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vvv`.`city` (
  `id` INT NOT NULL,
  `name` VARCHAR(255) NULL,
  `latitute` FLOAT NULL,
  `longitude` FLOAT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vvv`.`route`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vvv`.`route` (
  `id` INT NOT NULL,
  `departure` DATETIME NOT NULL,
  `arrival` DATETIME NOT NULL,
  `modal_id` INT NOT NULL,
  `value` FLOAT NOT NULL,
  `origin_id` INT NOT NULL,
  `destination_id` INT NOT NULL,
  INDEX `fk_viagem_modal1_idx` (`modal_id` ASC),
  PRIMARY KEY (`id`),
  INDEX `fk_viagem_city1_idx` (`origin_id` ASC),
  INDEX `fk_viagem_city2_idx` (`destination_id` ASC),
  CONSTRAINT `fk_viagem_modal1`
    FOREIGN KEY (`modal_id`)
    REFERENCES `vvv`.`modal` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_viagem_city1`
    FOREIGN KEY (`origin_id`)
    REFERENCES `vvv`.`city` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_viagem_city2`
    FOREIGN KEY (`destination_id`)
    REFERENCES `vvv`.`city` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vvv`.`reservation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vvv`.`reservation` (
  `id` INT NOT NULL,
  `date` DATETIME NOT NULL,
  `status` TINYINT(1) NOT NULL,
  `value` FLOAT NOT NULL,
  `passenger_user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_reservation_passenger1_idx` (`passenger_user_id` ASC),
  CONSTRAINT `fk_reservation_passenger1`
    FOREIGN KEY (`passenger_user_id`)
    REFERENCES `vvv`.`passenger` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vvv`.`itinerary`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vvv`.`itinerary` (
  `reservation_id` INT NOT NULL,
  `viagem_id` INT NOT NULL,
  PRIMARY KEY (`reservation_id`, `viagem_id`),
  INDEX `fk_reservation_has_viagem_viagem1_idx` (`viagem_id` ASC),
  INDEX `fk_reservation_has_viagem_reservation1_idx` (`reservation_id` ASC),
  CONSTRAINT `fk_reservation_has_viagem_reservation1`
    FOREIGN KEY (`reservation_id`)
    REFERENCES `vvv`.`reservation` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_reservation_has_viagem_viagem1`
    FOREIGN KEY (`viagem_id`)
    REFERENCES `vvv`.`route` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
