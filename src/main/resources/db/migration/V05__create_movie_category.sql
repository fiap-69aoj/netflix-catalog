CREATE TABLE `movie_category` (
  `id_movie` BIGINT(20) NOT NULL,
  `id_category` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id_movie`, `id_category`),
  INDEX `FK_category_idx` (`id_category` ASC),
  INDEX `FK_movie_idx` (`id_movie` ASC),
  CONSTRAINT `FK1_movie_category`
    FOREIGN KEY (`id_movie`)
    REFERENCES `movie` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK2_movie_category`
    FOREIGN KEY (`id_category`)
    REFERENCES `category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
