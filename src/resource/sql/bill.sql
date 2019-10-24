/*
Navicat MySQL Data Transfer

Source Server         : isource
Source Server Version : 50541
Source Host           : localhost:3306
Source Database       : bill

Target Server Type    : MYSQL
Target Server Version : 50541
File Encoding         : 65001

Date: 2019-10-24 11:41:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `bill_cost_detail`
-- ----------------------------
DROP TABLE IF EXISTS `bill_cost_detail`;
CREATE TABLE `bill_cost_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(255) DEFAULT NULL COMMENT '账户',
  `type` varchar(11) DEFAULT NULL COMMENT '分类名称；支出、收入',
  `costtype` varchar(32) DEFAULT NULL COMMENT '支出方式',
  `ddesc` varchar(255) DEFAULT NULL COMMENT '描述',
  `money` double DEFAULT NULL COMMENT '金额',
  `costtime` datetime DEFAULT NULL COMMENT '支出、收入时间',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `updatetime` datetime DEFAULT NULL COMMENT '更新时间',
  `userid` int(11) DEFAULT '1' COMMENT '用户id',
  `state` int(11) DEFAULT NULL COMMENT '是否删除：1：未删除，2：删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='账单详情';

-- ----------------------------
-- Records of bill_cost_detail
-- ----------------------------
INSERT INTO `bill_cost_detail` VALUES ('1', '123456', '支出', '银行卡支出', '手机充值', '99', '2019-10-16 00:00:00', '2019-10-23 14:10:52', '2019-10-23 14:10:52', '1', '1');
INSERT INTO `bill_cost_detail` VALUES ('2', '1234567', '支出', '微信支出', '吃饭', '18', '2019-10-23 00:00:00', '2019-10-23 14:11:16', '2019-10-23 14:12:25', '1', '1');
INSERT INTO `bill_cost_detail` VALUES ('3', 'i5lu', '支出', '微信-支出', '吃饭', '99', '2019-10-23 00:00:00', '2019-10-23 14:30:35', '2019-10-23 14:30:35', '3', '1');
INSERT INTO `bill_cost_detail` VALUES ('4', '23333', '收入', '微信收入', '收益', '1000', '2019-10-24 00:00:00', '2019-10-24 11:36:47', '2019-10-24 11:37:07', '1', '1');

-- ----------------------------
-- Table structure for `bill_cost_share`
-- ----------------------------
DROP TABLE IF EXISTS `bill_cost_share`;
CREATE TABLE `bill_cost_share` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `share_user` int(11) DEFAULT NULL COMMENT '源用户主键',
  `show_user` int(11) DEFAULT NULL COMMENT '目标用户主键',
  `share_type` int(11) DEFAULT NULL COMMENT '类型，0：收入，1：支出',
  `begin_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='分享表';

-- ----------------------------
-- Records of bill_cost_share
-- ----------------------------

-- ----------------------------
-- Table structure for `bill_cost_type`
-- ----------------------------
DROP TABLE IF EXISTS `bill_cost_type`;
CREATE TABLE `bill_cost_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) DEFAULT NULL COMMENT '分类名称',
  `parent` int(11) DEFAULT NULL COMMENT '父类型',
  `state` int(11) DEFAULT NULL COMMENT '状态，1不可用，2可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='类型表';

-- ----------------------------
-- Records of bill_cost_type
-- ----------------------------
INSERT INTO `bill_cost_type` VALUES ('1', '收入', null, '0');
INSERT INTO `bill_cost_type` VALUES ('2', '支出', null, '0');

-- ----------------------------
-- Table structure for `bill_plan`
-- ----------------------------
DROP TABLE IF EXISTS `bill_plan`;
CREATE TABLE `bill_plan` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(255) DEFAULT NULL COMMENT '计划名称',
  `content` varchar(255) DEFAULT NULL COMMENT '计划内容描述',
  `user_id` int(11) DEFAULT NULL COMMENT '关联用户',
  `bengin_time` datetime DEFAULT NULL COMMENT '计划开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '计划结束时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `rate` double(11,0) DEFAULT NULL COMMENT '进度百分比',
  `remind` int(11) DEFAULT '1' COMMENT '是否每次登陆提醒，1：是，2：不是',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `state` int(11) DEFAULT '1' COMMENT '状态，1：可用，2：删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='计划表';

-- ----------------------------
-- Records of bill_plan
-- ----------------------------

-- ----------------------------
-- Table structure for `bill_plan_progress`
-- ----------------------------
DROP TABLE IF EXISTS `bill_plan_progress`;
CREATE TABLE `bill_plan_progress` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL COMMENT '进度描述',
  `using_time` double(11,0) DEFAULT NULL COMMENT '用时',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `rate` double DEFAULT NULL COMMENT '进度百分比',
  `plan_id` int(11) DEFAULT NULL COMMENT '主计划ID',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `state` int(11) DEFAULT NULL COMMENT '状态：1:可用，2：删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='进度执行表';

-- ----------------------------
-- Records of bill_plan_progress
-- ----------------------------

-- ----------------------------
-- Table structure for `bill_plan_share`
-- ----------------------------
DROP TABLE IF EXISTS `bill_plan_share`;
CREATE TABLE `bill_plan_share` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_user` int(11) DEFAULT NULL COMMENT '被监督用户',
  `show_user` int(11) DEFAULT NULL COMMENT '可看用户',
  `plan_id` int(11) DEFAULT NULL COMMENT '计划',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='计划分享';

-- ----------------------------
-- Records of bill_plan_share
-- ----------------------------

-- ----------------------------
-- Table structure for `bill_reset_pwd`
-- ----------------------------
DROP TABLE IF EXISTS `bill_reset_pwd`;
CREATE TABLE `bill_reset_pwd` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `question` varchar(255) DEFAULT NULL COMMENT '问题',
  `answer` varchar(255) DEFAULT NULL COMMENT '答案',
  `userid` int(11) DEFAULT NULL COMMENT '用户id',
  `hint` varchar(255) DEFAULT NULL COMMENT '提示',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='重置密码';

-- ----------------------------
-- Records of bill_reset_pwd
-- ----------------------------
INSERT INTO `bill_reset_pwd` VALUES ('1', 'admin', 'admin', '2', 'admin');
INSERT INTO `bill_reset_pwd` VALUES ('2', 'user2', 'user2', '4', 'user2');

-- ----------------------------
-- Table structure for `bill_sort`
-- ----------------------------
DROP TABLE IF EXISTS `bill_sort`;
CREATE TABLE `bill_sort` (
  `sid` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sname` varchar(255) DEFAULT NULL COMMENT '名称',
  `sdesc` varchar(255) DEFAULT NULL COMMENT '描述',
  `userId` int(11) DEFAULT '1' COMMENT '用户id',
  `type` varchar(12) DEFAULT '' COMMENT '类型:0,支出,1:收入',
  `state` int(2) DEFAULT '1' COMMENT '状态:0不可用,1可用',
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bill_sort
-- ----------------------------
INSERT INTO `bill_sort` VALUES ('1', '银行卡支出', '银行卡支出', '1', '支出', '1');
INSERT INTO `bill_sort` VALUES ('2', '微信支出', '微信支出', '1', '支出', '1');
INSERT INTO `bill_sort` VALUES ('3', '微信收入', '微信收入', '1', '收入', '1');
INSERT INTO `bill_sort` VALUES ('4', '银行卡收入', '银行卡收入', '1', '收入', '1');
INSERT INTO `bill_sort` VALUES ('5', '支付宝收入', '支付宝收入', '1', '支出', '1');
INSERT INTO `bill_sort` VALUES ('6', '支付宝支出', '支付宝支出', '1', '支出', '1');
INSERT INTO `bill_sort` VALUES ('7', '现金支出', '现金支出', '1', '支出', '1');
INSERT INTO `bill_sort` VALUES ('8', '现金收入', '现金收入', '1', '收入', '1');
INSERT INTO `bill_sort` VALUES ('9', '日常消费', '支出', '1', '支出', '1');
INSERT INTO `bill_sort` VALUES ('10', '地铁公交', '支出', '1', '支出', '1');
INSERT INTO `bill_sort` VALUES ('11', '水电', '支出', '1', '支出', '1');
INSERT INTO `bill_sort` VALUES ('12', '股票基金收入', '股票基金收入', '1', '收入', '1');
INSERT INTO `bill_sort` VALUES ('13', '微信-支出', '微信支出', '3', '支出', '1');

-- ----------------------------
-- Table structure for `bill_user`
-- ----------------------------
DROP TABLE IF EXISTS `bill_user`;
CREATE TABLE `bill_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(32) DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `userNick` varchar(12) DEFAULT NULL COMMENT '用户昵称',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `photo` varchar(255) DEFAULT 'resource\\\\img\\\\favicon.png' COMMENT '头像',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '更新时间',
  `salt` varchar(12) DEFAULT '123456' COMMENT '盐',
  `state` int(2) DEFAULT '1' COMMENT '用户状态，1：在用，2：注销',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bill_user
-- ----------------------------
INSERT INTO `bill_user` VALUES ('1', 'admin', '1e22d765a46640454c27f801a720be89', 'admin', 'admin@i5lu.com', 'resource\\img\\favicon.png', '2019-10-23 13:10:10', '2019-10-23 13:10:10', '2a5jDR6', '1');
INSERT INTO `bill_user` VALUES ('3', 'user1', '3b71cff4263fc7def7d65eed2537feb1', 'user1', 'user1@i5lu.com', 'resource\\img\\favicon.png', '2019-10-23 13:11:45', '2019-10-24 09:49:18', 'GtzRcbHP', '1');
INSERT INTO `bill_user` VALUES ('4', 'user2', '7f43b6155a40e07ee9a6f9c3fc1b92fa', 'user2', 'user2@i5lu.com', 'resource\\img\\favicon.png', '2019-10-23 13:14:05', '2019-10-24 10:33:01', 'P1QsooC', '1');
