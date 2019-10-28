CREATE TABLE `category` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UK_description` (`description` ASC)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `category` (`description`) VALUES ('Ação');
INSERT INTO `category` (`description`) VALUES ('Aventura');
INSERT INTO `category` (`description`) VALUES ('Cinema de arte');
INSERT INTO `category` (`description`) VALUES ('Chanchada');
INSERT INTO `category` (`description`) VALUES ('Cinema catástrofe');
INSERT INTO `category` (`description`) VALUES ('Comédia');
INSERT INTO `category` (`description`) VALUES ('Comédia romântica');
INSERT INTO `category` (`description`) VALUES ('Comédia dramática');
INSERT INTO `category` (`description`) VALUES ('Comédia de ação');
INSERT INTO `category` (`description`) VALUES ('Dança');
INSERT INTO `category` (`description`) VALUES ('Documentário');
INSERT INTO `category` (`description`) VALUES ('Docuficção');
INSERT INTO `category` (`description`) VALUES ('Drama');
INSERT INTO `category` (`description`) VALUES ('Espionagem');
INSERT INTO `category` (`description`) VALUES ('Faroeste');
INSERT INTO `category` (`description`) VALUES ('Fantasia científica');
INSERT INTO `category` (`description`) VALUES ('Ficção científica');
INSERT INTO `category` (`description`) VALUES ('Filmes de guerra');
INSERT INTO `category` (`description`) VALUES ('Musical');
INSERT INTO `category` (`description`) VALUES ('Filme policial');
INSERT INTO `category` (`description`) VALUES ('Romance');
INSERT INTO `category` (`description`) VALUES ('Seriado');
INSERT INTO `category` (`description`) VALUES ('Suspense');
INSERT INTO `category` (`description`) VALUES ('Terror');