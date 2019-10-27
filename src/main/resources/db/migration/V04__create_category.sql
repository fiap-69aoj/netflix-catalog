CREATE TABLE `category` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UK_description` (`description` ASC)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
