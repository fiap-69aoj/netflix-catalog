CREATE TABLE `movie` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `image` varchar(200) NULL,
  `rating` CHAR(2) NOT NULL,
  `summary` varchar(256) NOT NULL,
  `release_date` date NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;