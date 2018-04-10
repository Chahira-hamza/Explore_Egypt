CREATE TABLE `tourguide` (
  `mobile_num` int(15) NOT NULL,
  `email` varchar(20) NOT NULL,
  `country` varchar(30) NOT NULL,
  `L1` varchar(11) NOT NULL,
  `L2` varchar(11) DEFAULT NULL,
  `L3` varchar(11) DEFAULT NULL,
  `tourguide_ID` int(11) NOT NULL,
  KEY `tourguide_ID` (`tourguide_ID`),
  CONSTRAINT `tourguide_ibfk_1` FOREIGN KEY (`tourguide_ID`) REFERENCES `users` (`user_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
