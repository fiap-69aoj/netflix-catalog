CREATE TABLE `serie_label` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `id_serie` BIGINT(20) NOT NULL,
  `label` VARCHAR(10) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UK_serie_name` (`id_serie` ASC, `label` ASC),
  CONSTRAINT `FK_serie_label`
    FOREIGN KEY (`id_serie`)
    REFERENCES `serie` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
