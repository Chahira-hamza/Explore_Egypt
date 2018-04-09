CREATE TABLE `sites` (
  `SName` varchar(255) DEFAULT NULL,
  `Description` text,
  `Address` varchar(255) DEFAULT NULL,
  `Stime` int(11) DEFAULT NULL,
  `Etime` int(11) DEFAULT NULL,
  `SiteID` int(11) NOT NULL AUTO_INCREMENT,
  `photo` mediumblob,
  PRIMARY KEY (`SiteID`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1
