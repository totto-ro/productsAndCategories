-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema products_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema products_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `products_db` DEFAULT CHARACTER SET utf8 ;
USE `products_db` ;

-- -----------------------------------------------------
-- Table `products_db`.`products`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `products_db`.`products` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(150) NULL,
  `description` TEXT NULL,
  `price` DECIMAL NULL,
  `created_at` DATETIME NULL DEFAULT NOW(),
  `updated_at` DATETIME NULL DEFAULT NOW(),
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `products_db`.`categories`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `products_db`.`categories` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(150) NULL,
  `created_at` DATETIME NULL DEFAULT NOW(),
  `updated_at` DATETIME NULL DEFAULT NOW(),
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `products_db`.`categories_products`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `products_db`.`categories_products` (
  `category_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  PRIMARY KEY (`category_id`, `product_id`),
  INDEX `fk_categories_has_products_products1_idx` (`product_id` ASC) VISIBLE,
  INDEX `fk_categories_has_products_categories_idx` (`category_id` ASC) VISIBLE,
  CONSTRAINT `fk_categories_has_products_categories`
    FOREIGN KEY (`category_id`)
    REFERENCES `products_db`.`categories` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_categories_has_products_products1`
    FOREIGN KEY (`product_id`)
    REFERENCES `products_db`.`products` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
