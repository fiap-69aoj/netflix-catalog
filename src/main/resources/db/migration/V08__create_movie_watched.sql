CREATE TABLE `movie_watched` (
  `id_user` BIGINT(20) NOT NULL,
  `id_movie` BIGINT(20) NOT NULL,
  `date` DATE NOT NULL,
  PRIMARY KEY (`id_user`, `id_movie`),
  INDEX `FK_movie_idx` (`id_movie` ASC),
  CONSTRAINT `FK1_movie_watched`
  FOREIGN KEY (`id_movie`)
  REFERENCES `movie` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
