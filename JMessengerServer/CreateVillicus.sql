SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';


-- -----------------------------------------------------
-- Table `Villicus`.`CommonLookup`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `Villicus`.`CommonLookup` (
  `CommonLookupID` INT NOT NULL AUTO_INCREMENT ,
  `CommonLookupDescription` VARCHAR(45) NULL ,
  PRIMARY KEY (`CommonLookupID`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Villicus`.`User`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `Villicus`.`User` (
  `UserID` INT NOT NULL AUTO_INCREMENT ,
  `UserName` VARCHAR(45) NULL ,
  `UserPass` VARCHAR(45) NULL ,
  `UserRole` INT NULL ,
  `UserActiveFlag` TINYINT(1)  NULL DEFAULT 1 ,
  `UserLoggedIn` TINYINT(1)  NULL DEFAULT 0 ,
  PRIMARY KEY (`UserID`) ,
  INDEX `FK_User1` (`UserRole` ASC) ,
  CONSTRAINT `FK_User1`
    FOREIGN KEY (`UserRole` )
    REFERENCES `Villicus`.`CommonLookup` (`CommonLookupID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Villicus`.`Session`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `Villicus`.`Session` (
  `SessionID` INT NOT NULL AUTO_INCREMENT ,
  `SessionNumber` VARCHAR(45) NULL ,
  `SessionName` VARCHAR(45) NULL ,
  `SessionActive` TINYINT(1)  NULL ,
  `SessionPassword` VARCHAR(45) NULL ,
  PRIMARY KEY (`SessionID`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Villicus`.`BannedUser`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `Villicus`.`BannedUser` (
  `BannedUserID` INT NOT NULL AUTO_INCREMENT ,
  `UserID` INT NULL ,
  `SessionID` INT NULL ,
  PRIMARY KEY (`BannedUserID`) ,
  INDEX `FK_BannedUser1` (`UserID` ASC) ,
  INDEX `FK_BannedUser2` (`SessionID` ASC) ,
  CONSTRAINT `FK_BannedUser1`
    FOREIGN KEY (`UserID` )
    REFERENCES `Villicus`.`User` (`UserID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_BannedUser2`
    FOREIGN KEY (`SessionID` )
    REFERENCES `Villicus`.`Session` (`SessionID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Villicus`.`SessionParticipants`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `Villicus`.`SessionParticipants` (
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
    REFERENCES `Villicus`.`User` (`UserID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SessPart2`
    FOREIGN KEY (`SessionID` )
    REFERENCES `Villicus`.`Session` (`SessionID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SessPart3`
    FOREIGN KEY (`SessionRole` )
    REFERENCES `Villicus`.`CommonLookup` (`CommonLookupID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Villicus`.`Message`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `Villicus`.`Message` (
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
    REFERENCES `Villicus`.`Session` (`SessionID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_Message2`
    FOREIGN KEY (`UserID` )
    REFERENCES `Villicus`.`User` (`UserID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Villicus`.`File`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `Villicus`.`File` (
  `FileID` INT NOT NULL AUTO_INCREMENT ,
  `UserID` INT NULL ,
  `SessionID` INT NULL ,
  `FileSendTime` TIMESTAMP NULL ,
  `FileName` VARCHAR(45) NULL ,
  `FileDescription` VARCHAR(100) NULL ,
  `FileData` LONGBLOB NULL ,
  `FileActiveFlag` TINYINT(1)  NULL ,
  `FileNumber` VARCHAR(75) NULL ,
  PRIMARY KEY (`FileID`) ,
  INDEX `FK_File1` (`UserID` ASC) ,
  INDEX `FK_File2` (`SessionID` ASC) ,
  CONSTRAINT `FK_File1`
    FOREIGN KEY (`UserID` )
    REFERENCES `Villicus`.`User` (`UserID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_File2`
    FOREIGN KEY (`SessionID` )
    REFERENCES `Villicus`.`Session` (`SessionID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Villicus`.`Moderator`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `Villicus`.`Moderator` (
  `ModeratorID` INT NOT NULL AUTO_INCREMENT ,
  `UserID` INT NULL ,
  `SessionID` INT NULL ,
  PRIMARY KEY (`ModeratorID`) ,
  INDEX `FK_Moderator1` (`UserID` ASC) ,
  INDEX `FK_Moderator2` (`SessionID` ASC) ,
  CONSTRAINT `FK_Moderator1`
    FOREIGN KEY (`UserID` )
    REFERENCES `Villicus`.`User` (`UserID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_Moderator2`
    FOREIGN KEY (`SessionID` )
    REFERENCES `Villicus`.`Session` (`SessionID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

USE Villicus;

INSERT INTO CommonLookup (CommonLookupDescription) VALUES ('ADMIN');
INSERT INTO CommonLookup (CommonLookupDescription) VALUES ('USER');
INSERT INTO CommonLookup (CommonLookupDescription) VALUES ('MODERATOR');



INSERT INTO User (UserName, UserPass, UserRole) VALUES ('MasterAdmin', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', (SELECT CommonLookupID FROM CommonLookup WHERE CommonLookupDescription = 'ADMIN'));
