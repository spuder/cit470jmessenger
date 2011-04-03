SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

DROP SCHEMA IF EXISTS `Palantir` ;
CREATE SCHEMA IF NOT EXISTS `Palantir` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
USE `Palantir` ;

-- -----------------------------------------------------
-- Table `Palantir`.`CommonLookup`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `Palantir`.`CommonLookup` (
  `CommonLookupID` INT NOT NULL AUTO_INCREMENT ,
  `CommonLookupDescription` VARCHAR(45) NULL ,
  PRIMARY KEY (`CommonLookupID`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Palantir`.`User`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `Palantir`.`User` (
  `UserID` INT NOT NULL AUTO_INCREMENT ,
  `UserName` VARCHAR(45) NULL ,
  `UserPass` VARCHAR(45) NULL ,
  `UserRole` INT NULL ,
  PRIMARY KEY (`UserID`) ,
  INDEX `FK_User1` (`UserRole` ASC) ,
  CONSTRAINT `FK_User1`
    FOREIGN KEY (`UserRole` )
    REFERENCES `Palantir`.`CommonLookup` (`CommonLookupID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Palantir`.`Session`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `Palantir`.`Session` (
  `SessionID` INT NOT NULL AUTO_INCREMENT ,
  `SessionNumber` VARCHAR(45) NULL ,
  `SessionName` VARCHAR(45) NULL ,
  `SessionActive` TINYINT(1)  NULL ,
  PRIMARY KEY (`SessionID`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Palantir`.`BannedUser`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `Palantir`.`BannedUser` (
  `BannedUserID` INT NOT NULL AUTO_INCREMENT ,
  `UserID` INT NULL ,
  `SessionID` INT NULL ,
  PRIMARY KEY (`BannedUserID`) ,
  INDEX `FK_BannedUser1` (`UserID` ASC) ,
  INDEX `FK_BannedUser2` (`SessionID` ASC) ,
  CONSTRAINT `FK_BannedUser1`
    FOREIGN KEY (`UserID` )
    REFERENCES `Palantir`.`User` (`UserID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_BannedUser2`
    FOREIGN KEY (`SessionID` )
    REFERENCES `Palantir`.`Session` (`SessionID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Palantir`.`SessionParticipants`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `Palantir`.`SessionParticipants` (
  `SessionParticipantsID` INT NOT NULL AUTO_INCREMENT ,
  `SessionID` INT NULL ,
  `UserID` INT NULL ,
  `SessionRole` INT NULL ,
  PRIMARY KEY (`SessionParticipantsID`) ,
  INDEX `FK_SessPart1` (`UserID` ASC) ,
  INDEX `FK_SessPart2` (`SessionID` ASC) ,
  INDEX `FK_SessPart3` (`SessionRole` ASC) ,
  CONSTRAINT `FK_SessPart1`
    FOREIGN KEY (`UserID` )
    REFERENCES `Palantir`.`User` (`UserID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SessPart2`
    FOREIGN KEY (`SessionID` )
    REFERENCES `Palantir`.`Session` (`SessionID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SessPart3`
    FOREIGN KEY (`SessionRole` )
    REFERENCES `Palantir`.`CommonLookup` (`CommonLookupID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Palantir`.`Message`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `Palantir`.`Message` (
  `MessageID` INT NOT NULL AUTO_INCREMENT ,
  `UserID` INT NULL ,
  `SessionID` INT NULL ,
  `MessageSendTime` TIMESTAMP NULL ,
  `Message` TEXT NULL ,
  PRIMARY KEY (`MessageID`) ,
  INDEX `FK_Message1` (`SessionID` ASC) ,
  INDEX `FK_Message2` (`UserID` ASC) ,
  CONSTRAINT `FK_Message1`
    FOREIGN KEY (`SessionID` )
    REFERENCES `Palantir`.`Session` (`SessionID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_Message2`
    FOREIGN KEY (`UserID` )
    REFERENCES `Palantir`.`User` (`UserID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Palantir`.`File`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `Palantir`.`File` (
  `FileID` INT NOT NULL AUTO_INCREMENT ,
  `UserID` INT NULL ,
  `SessionID` INT NULL ,
  `FileSendTime` DATETIME NULL ,
  `FileName` VARCHAR(45) NULL ,
  `FileDescription` VARCHAR(100) NULL ,
  `FileData` BLOB NULL ,
  `FileActiveFlage` TINYINT(1)  NULL ,
  PRIMARY KEY (`FileID`) ,
  INDEX `FK_File1` (`UserID` ASC) ,
  INDEX `FK_File2` (`SessionID` ASC) ,
  CONSTRAINT `FK_File1`
    FOREIGN KEY (`UserID` )
    REFERENCES `Palantir`.`User` (`UserID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_File2`
    FOREIGN KEY (`SessionID` )
    REFERENCES `Palantir`.`Session` (`SessionID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

USE Palantir;

INSERT INTO CommonLookup (CommonLookupDescription) VALUES ('ADMIN');
INSERT INTO CommonLookup (CommonLookupDescription) VALUES ('USER');
INSERT INTO CommonLookup (CommonLookupDescription) VALUES ('MODERATOR');



INSERT INTO User (UserName, UserPass, UserRole) VALUES ('Saurumon', '3b8ffa2cc7ec5ca9235b219cb07888764168c6e8', (SELECT CommonLookupID FROM CommonLookup WHERE CommonLookupDescription = 'ADMIN'));
