CREATE TABLE `tourists` (
  `mobile_num` int(15) NOT NULL,
  `email` varchar(20) NOT NULL,
  `country` varchar(30) NOT NULL,
  `L1` varchar(11) NOT NULL, /* The tourist first language  */
  `L2` varchar(11) ,		 /* The tourist second language */
  `L3` varchar(11) ,		 /* The tourist third language  */
  `tourist_ID` int(11) NOT NULL,
  PRIMARY KEY (`tourist_ID`)
)