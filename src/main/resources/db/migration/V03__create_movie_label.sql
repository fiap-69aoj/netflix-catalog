CREATE TABLE `movie_label` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `id_movie` BIGINT(20) NOT NULL,
  `label` VARCHAR(10) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UK_movie_name` (`id_movie` ASC, `label` ASC),
  CONSTRAINT `FK_movie_label`
    FOREIGN KEY (`id_movie`)
    REFERENCES `movie` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
