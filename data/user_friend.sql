CREATE TABLE `user_friend` (
  `id` bigint(20) NOT NULL,
  `user_id` varchar(20) NOT NULL,
  `friend_id` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of user_friend
-- ----------------------------
INSERT INTO `user_friend` VALUES ('1', '12', '123');
INSERT INTO `user_friend` VALUES ('2', '123', '12');
