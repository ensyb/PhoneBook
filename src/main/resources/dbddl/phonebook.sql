

DROP TABLE IF EXISTS `contact`;
CREATE TABLE IF NOT EXISTS `contact` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `name` varchar(24) NOT NULL,
  `phonenumber` varchar(16) NOT NULL,
  `description` varchar(620) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `userid` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;


INSERT INTO `contact` (`id`, `userid`, `name`, `phonenumber`, `description`) VALUES
(1, 1, 'Moj Kontakt', '333-222-111', 'moj prvi kontakt'),



DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(512) NOT NULL,
  `password` varchar(512) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;


INSERT INTO `user` (`id`, `email`, `password`) VALUES
(1, 'test@test.com', '$2a$12$L/hBJA5Q.P3V7VDlP/pvgueZBsXu42QIxAZ2C.D.mG9bqfbhDgxZC'),


