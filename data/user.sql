CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `online` int(1) NOT NULL DEFAULT '0' COMMENT '是否在线：0-不在线；1-在线',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '12', 'zqr', 'zqr', '1');
INSERT INTO `user` VALUES ('2', '123', 'zgq', 'zgq', '1');
