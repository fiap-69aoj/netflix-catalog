CREATE TABLE `movie_watched` (
  `id_user` BIGINT(20) NOT NULL,
  `id_movie` BIGINT(20) NOT NULL,
  `date` DATE NOT NULL,
  PRIMARY KEY (`id_movie`, `id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
