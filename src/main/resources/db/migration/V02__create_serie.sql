CREATE TABLE `serie` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `image` VARCHAR(200) NULL,
  `rating` CHAR(2) NOT NULL,
  `summary` VARCHAR(256) NOT NULL,
  `release_date` DATE NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;