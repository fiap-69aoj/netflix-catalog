CREATE TABLE `serie_category` (
  `id_serie` BIGINT(20) NOT NULL,
  `id_category` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id_serie`, `id_category`),
  INDEX `FK_category_idx` (`id_category` ASC),
  INDEX `FK_serie_idx` (`id_serie` ASC),
  CONSTRAINT `FK1_serie_category`
    FOREIGN KEY (`id_serie`)
    REFERENCES `serie` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK2_serie_category`
    FOREIGN KEY (`id_category`)
    REFERENCES `category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
