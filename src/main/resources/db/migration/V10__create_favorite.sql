CREATE TABLE `favorites` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `id_user` BIGINT(20) NOT NULL,
  `id_movie` BIGINT(20) NULL,
  `id_serie` BIGINT(20) NULL,
  `date` DATE NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_favorites_movie_idx` (`id_movie` ASC),
  INDEX `FK_favorites_serie_idx` (`id_serie` ASC),
  UNIQUE INDEX `UK_user_movie` (`id_user` ASC, `id_movie` ASC),
  UNIQUE INDEX `UK_user_serie` (`id_user` ASC, `id_serie` ASC),
  CONSTRAINT `FK1_favorites_movie_watched`
  FOREIGN KEY (`id_movie`)
  REFERENCES `movie` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
  CONSTRAINT `FK2_favorites_serie_watched`
  FOREIGN KEY (`id_serie`)
  REFERENCES `serie` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
