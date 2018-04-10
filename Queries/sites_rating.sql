CREATE TABLE `sites_rating` (
  `user_ID` int(11) NOT NULL,
  `site_name` varchar(20) NOT NULL,
  `rating` int(1) DEFAULT NULL,
  PRIMARY KEY (`user_ID`),
  CONSTRAINT `sites_rating_ibfk_1` FOREIGN KEY (`user_ID`) REFERENCES `users` (`user_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
