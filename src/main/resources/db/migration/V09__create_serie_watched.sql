CREATE TABLE `serie_watched` (
  `id_user` BIGINT(20) NOT NULL,
  `id_serie` BIGINT(20) NOT NULL,
  `date` DATE NOT NULL,
  PRIMARY KEY (`id_user`, `id_serie`),
  INDEX `FK_serie_idx` (`id_serie` ASC),
  CONSTRAINT `FK1_serie_watched`
  FOREIGN KEY (`id_serie`)
  REFERENCES `serie` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
