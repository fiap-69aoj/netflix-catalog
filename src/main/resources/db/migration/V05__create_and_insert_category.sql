CREATE TABLE `category` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UK_name` (`name` ASC)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `category` (`name`) VALUES ('Ação');
INSERT INTO `category` (`name`) VALUES ('Aventura');
INSERT INTO `category` (`name`) VALUES ('Cinema de arte');
INSERT INTO `category` (`name`) VALUES ('Chanchada');
INSERT INTO `category` (`name`) VALUES ('Cinema catástrofe');
INSERT INTO `category` (`name`) VALUES ('Comédia');
INSERT INTO `category` (`name`) VALUES ('Comédia romântica');
INSERT INTO `category` (`name`) VALUES ('Comédia dramática');
INSERT INTO `category` (`name`) VALUES ('Comédia de ação');
INSERT INTO `category` (`name`) VALUES ('Dança');
INSERT INTO `category` (`name`) VALUES ('Documentário');
INSERT INTO `category` (`name`) VALUES ('Docuficção');
INSERT INTO `category` (`name`) VALUES ('Drama');
INSERT INTO `category` (`name`) VALUES ('Espionagem');
INSERT INTO `category` (`name`) VALUES ('Faroeste');
INSERT INTO `category` (`name`) VALUES ('Fantasia científica');
INSERT INTO `category` (`name`) VALUES ('Ficção científica');
INSERT INTO `category` (`name`) VALUES ('Filmes de guerra');
INSERT INTO `category` (`name`) VALUES ('Musical');
INSERT INTO `category` (`name`) VALUES ('Filme policial');
INSERT INTO `category` (`name`) VALUES ('Romance');
INSERT INTO `category` (`name`) VALUES ('Seriado');
INSERT INTO `category` (`name`) VALUES ('Suspense');
INSERT INTO `category` (`name`) VALUES ('Terror');