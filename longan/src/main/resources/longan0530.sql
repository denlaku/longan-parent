/*
SQLyog Professional v12.08 (64 bit)
MySQL - 5.7.26 : Database - fastbi
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*Table structure for table `t_biz_tag` */

DROP TABLE IF EXISTS `t_biz_tag`;

CREATE TABLE `t_biz_tag` (
  `id` varchar(20) COLLATE utf8mb4_bin NOT NULL,
  `name` varchar(60) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'tag名称',
  `biz` varchar(30) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '业务',
  `tenant_id` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '租户id',
  `created_by` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Table structure for table `t_component` */

DROP TABLE IF EXISTS `t_component`;

CREATE TABLE `t_component` (
  `id` varchar(20) COLLATE utf8mb4_bin NOT NULL,
  `dashboard_id` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `name` varchar(90) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '组件名称',
  `dataset_id` bigint(20) DEFAULT NULL COMMENT '数据集ID',
  `type` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '组件类型',
  `grid` text COLLATE utf8mb4_bin COMMENT 'PC端布局',
  `style` text COLLATE utf8mb4_bin COMMENT '样式',
  `attr` json DEFAULT NULL COMMENT '属性',
  `parent_id` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `tenant_id` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `created_by` varchar(30) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Table structure for table `t_component_cache` */

DROP TABLE IF EXISTS `t_component_cache`;

CREATE TABLE `t_component_cache` (
  `id` varchar(20) COLLATE utf8mb4_bin NOT NULL COMMENT '仪表板ID',
  `dashboard_id` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `cache` json DEFAULT NULL COMMENT '仪表板JSON缓存',
  `tenant_id` bigint(20) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Table structure for table `t_custom_api` */

DROP TABLE IF EXISTS `t_custom_api`;

CREATE TABLE `t_custom_api` (
  `id` varchar(20) COLLATE utf8mb4_bin NOT NULL,
  `name` varchar(90) COLLATE utf8mb4_bin DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `dataset_id` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `tenant_id` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `created_by` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_by` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Table structure for table `t_custom_api_field` */

DROP TABLE IF EXISTS `t_custom_api_field`;

CREATE TABLE `t_custom_api_field` (
  `id` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `api_id` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `name` varchar(60) COLLATE utf8mb4_bin DEFAULT NULL,
  `aggregator` varchar(10) COLLATE utf8mb4_bin DEFAULT NULL,
  `tenant_id` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `created_by` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `created_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Table structure for table `t_custom_page` */

DROP TABLE IF EXISTS `t_custom_page`;

CREATE TABLE `t_custom_page` (
  `id` varchar(20) COLLATE utf8mb4_bin NOT NULL,
  `name` varchar(60) COLLATE utf8mb4_bin DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL COMMENT '自定义页面状态: 0-草稿 1-发布',
  `remark` varchar(300) COLLATE utf8mb4_bin DEFAULT NULL,
  `tenant_id` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `created_by` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_by` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Table structure for table `t_dashboard` */

DROP TABLE IF EXISTS `t_dashboard`;

CREATE TABLE `t_dashboard` (
  `id` varchar(20) COLLATE utf8mb4_bin NOT NULL COMMENT '主键ID',
  `title` varchar(150) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '标题',
  `desc` varchar(300) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '描述',
  `tag` varchar(30) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '标签',
  `theme` varchar(30) COLLATE utf8mb4_bin DEFAULT NULL,
  `style` text COLLATE utf8mb4_bin COMMENT '样式',
  `attr` json DEFAULT NULL COMMENT '属性',
  `terminal` varchar(2) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '1-PC 2-Mobile',
  `tenant_id` varchar(30) COLLATE utf8mb4_bin DEFAULT NULL,
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(30) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(30) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Table structure for table `t_dashboard_his` */

DROP TABLE IF EXISTS `t_dashboard_his`;

CREATE TABLE `t_dashboard_his` (
  `id` varchar(20) COLLATE utf8mb4_bin NOT NULL,
  `biz_id` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '仪表板ID',
  `dashboard` json DEFAULT NULL COMMENT '仪表板JSON',
  `tenant_id` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '租户id',
  `created_by` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Table structure for table `t_datapower_node` */

DROP TABLE IF EXISTS `t_datapower_node`;

CREATE TABLE `t_datapower_node` (
  `id` varchar(20) COLLATE utf8mb4_bin NOT NULL,
  `type` varchar(120) COLLATE utf8mb4_bin DEFAULT NULL,
  `code` varchar(300) COLLATE utf8mb4_bin DEFAULT NULL,
  `name` varchar(300) COLLATE utf8mb4_bin DEFAULT NULL,
  `reserve` varchar(300) COLLATE utf8mb4_bin DEFAULT NULL,
  `space_id` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `tenant_id` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `created_by` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_by` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Table structure for table `t_datapower_space` */

DROP TABLE IF EXISTS `t_datapower_space`;

CREATE TABLE `t_datapower_space` (
  `id` varchar(20) COLLATE utf8mb4_bin NOT NULL,
  `name` varchar(30) COLLATE utf8mb4_bin DEFAULT NULL,
  `tenant_id` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `created_by` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_by` varchar(30) COLLATE utf8mb4_bin DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Table structure for table `t_dataset` */

DROP TABLE IF EXISTS `t_dataset`;

CREATE TABLE `t_dataset` (
  `id` varchar(20) COLLATE utf8mb4_bin NOT NULL,
  `name` varchar(150) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '数据源名称',
  `datasource_id` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '数据源ID',
  `type` varchar(10) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '数据源类型',
  `command` text COLLATE utf8mb4_bin COMMENT 'sql',
  `tenant_id` bigint(20) DEFAULT NULL,
  `created_by` varchar(30) COLLATE utf8mb4_bin DEFAULT NULL,
  `updated_by` varchar(30) COLLATE utf8mb4_bin DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Table structure for table `t_dataset_cache` */

DROP TABLE IF EXISTS `t_dataset_cache`;

CREATE TABLE `t_dataset_cache` (
  `id` varchar(20) COLLATE utf8mb4_bin NOT NULL,
  `cache` json DEFAULT NULL,
  `tenant_id` bigint(20) DEFAULT NULL,
  `created_at` datetime(3) DEFAULT NULL,
  `created_by` varchar(30) COLLATE utf8mb4_bin DEFAULT NULL,
  `updated_at` datetime(3) DEFAULT NULL,
  `updated_by` varchar(30) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Table structure for table `t_dataset_field` */

DROP TABLE IF EXISTS `t_dataset_field`;

CREATE TABLE `t_dataset_field` (
  `id` varchar(20) COLLATE utf8mb4_bin NOT NULL,
  `dataset_id` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `name` varchar(60) COLLATE utf8mb4_bin DEFAULT NULL,
  `alias` varchar(60) COLLATE utf8mb4_bin DEFAULT NULL,
  `type` char(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '1-维度 2-度量',
  `data_type` varchar(10) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'string/date/number',
  `jdbc_type` varchar(30) COLLATE utf8mb4_bin DEFAULT NULL,
  `format` varchar(30) COLLATE utf8mb4_bin DEFAULT NULL,
  `tenant_id` varchar(30) COLLATE utf8mb4_bin DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `created_by` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Table structure for table `t_datasource` */

DROP TABLE IF EXISTS `t_datasource`;

CREATE TABLE `t_datasource` (
  `id` varchar(20) COLLATE utf8mb4_bin NOT NULL,
  `name` varchar(150) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '数据源名称',
  `type` varchar(2) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '数据源类型',
  `url` varchar(3000) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '连接地址',
  `host` varchar(150) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '主机名',
  `port` varchar(5) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '端口',
  `username` varchar(30) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户名',
  `password` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '密码',
  `version` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '数据库版本',
  `driver_class_name` varchar(120) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '驱动类',
  `max_pool_size` bigint(20) DEFAULT NULL COMMENT '最大连接数',
  `min_idle` bigint(20) DEFAULT NULL COMMENT '最小闲置连接数',
  `max_lifetime` bigint(20) DEFAULT NULL COMMENT '连接生命周期',
  `idle_timeout` bigint(20) DEFAULT NULL COMMENT '闲置时间',
  `tenant_id` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `created_by` varchar(30) COLLATE utf8mb4_bin DEFAULT NULL,
  `updated_by` varchar(30) COLLATE utf8mb4_bin DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Table structure for table `t_dimension` */

DROP TABLE IF EXISTS `t_dimension`;

CREATE TABLE `t_dimension` (
  `id` varchar(20) COLLATE utf8mb4_bin NOT NULL,
  `component_id` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `name` varchar(60) COLLATE utf8mb4_bin DEFAULT NULL,
  `attr` json DEFAULT NULL,
  `order` tinyint(4) DEFAULT NULL,
  `tenant_id` bigint(20) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `created_by` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Table structure for table `t_filter` */

DROP TABLE IF EXISTS `t_filter`;

CREATE TABLE `t_filter` (
  `id` varchar(20) COLLATE utf8mb4_bin NOT NULL,
  `component_id` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `name` varchar(30) COLLATE utf8mb4_bin DEFAULT NULL,
  `aggregator` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `operator` varchar(10) COLLATE utf8mb4_bin DEFAULT NULL,
  `values` json DEFAULT NULL,
  `tenant_id` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `created_by` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Table structure for table `t_gongin` */

DROP TABLE IF EXISTS `t_gongin`;

CREATE TABLE `t_gongin` (
  `id` varchar(20) COLLATE utf8mb4_bin NOT NULL,
  `component_id` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `name` varchar(60) COLLATE utf8mb4_bin DEFAULT NULL,
  `aim` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `tenant_id` bigint(20) DEFAULT NULL,
  `created_by` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Table structure for table `t_i18n` */

DROP TABLE IF EXISTS `t_i18n`;

CREATE TABLE `t_i18n` (
  `id` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `key` varchar(300) COLLATE utf8mb4_bin DEFAULT NULL,
  `text` varchar(3000) COLLATE utf8mb4_bin DEFAULT NULL,
  `desc` varchar(3000) COLLATE utf8mb4_bin DEFAULT NULL,
  `language` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `tenant_id` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `created_by` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `updated_by` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  UNIQUE KEY `uk_key_language` (`key`,`language`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Table structure for table `t_linkage` */

DROP TABLE IF EXISTS `t_linkage`;

CREATE TABLE `t_linkage` (
  `id` varchar(20) COLLATE utf8mb4_bin NOT NULL,
  `component_id` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `name` varchar(60) COLLATE utf8mb4_bin DEFAULT NULL,
  `tenant_id` bigint(20) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `created_by` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Table structure for table `t_linkage_aim` */

DROP TABLE IF EXISTS `t_linkage_aim`;

CREATE TABLE `t_linkage_aim` (
  `id` varchar(20) COLLATE utf8mb4_bin NOT NULL,
  `linkage_id` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `component_id` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `name` varchar(60) COLLATE utf8mb4_bin DEFAULT NULL,
  `placeholders` text COLLATE utf8mb4_bin,
  `tenant_id` bigint(20) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `created_by` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Table structure for table `t_lookup` */

DROP TABLE IF EXISTS `t_lookup`;

CREATE TABLE `t_lookup` (
  `id` varchar(20) COLLATE utf8mb4_bin NOT NULL,
  `code` varchar(300) COLLATE utf8mb4_bin DEFAULT NULL,
  `name_cn` varchar(3000) COLLATE utf8mb4_bin DEFAULT NULL,
  `name_en` varchar(3000) COLLATE utf8mb4_bin DEFAULT NULL,
  `tenant_id` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `created_by` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `updated_by` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Table structure for table `t_lookup_item` */

DROP TABLE IF EXISTS `t_lookup_item`;

CREATE TABLE `t_lookup_item` (
  `id` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `lookup_code` varchar(300) COLLATE utf8mb4_bin DEFAULT NULL,
  `code` varchar(300) COLLATE utf8mb4_bin DEFAULT NULL,
  `name` varchar(3000) COLLATE utf8mb4_bin DEFAULT NULL,
  `desc` varchar(3000) COLLATE utf8mb4_bin DEFAULT NULL,
  `language` varchar(10) COLLATE utf8mb4_bin DEFAULT NULL,
  `tenant_id` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `created_by` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `updated_by` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  UNIQUE KEY `uk_key` (`lookup_code`,`code`,`language`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Table structure for table `t_measure` */

DROP TABLE IF EXISTS `t_measure`;

CREATE TABLE `t_measure` (
  `id` varchar(20) COLLATE utf8mb4_bin NOT NULL,
  `component_id` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `name` varchar(60) COLLATE utf8mb4_bin DEFAULT NULL,
  `aggregator` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `attr` json DEFAULT NULL,
  `order` tinyint(4) DEFAULT NULL,
  `tenant_id` bigint(20) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `created_by` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Table structure for table `t_placeholder` */

DROP TABLE IF EXISTS `t_placeholder`;

CREATE TABLE `t_placeholder` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dataset_id` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `name` varchar(30) COLLATE utf8mb4_bin DEFAULT NULL,
  `type` varchar(10) COLLATE utf8mb4_bin DEFAULT NULL,
  `def_value` varchar(60) COLLATE utf8mb4_bin DEFAULT NULL,
  `format` varchar(15) COLLATE utf8mb4_bin DEFAULT NULL,
  `delimiter` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `tenant_id` bigint(20) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `created_by` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Table structure for table `t_power_match` */

DROP TABLE IF EXISTS `t_power_match`;

CREATE TABLE `t_power_match` (
  `id` varchar(20) COLLATE utf8mb4_bin NOT NULL,
  `dataset_id` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `of_id` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `of_type` char(1) COLLATE utf8mb4_bin DEFAULT NULL,
  `space_id` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `type` varchar(120) COLLATE utf8mb4_bin DEFAULT NULL,
  `code` tinyint(4) DEFAULT NULL,
  `name` tinyint(4) DEFAULT NULL,
  `reserve` tinyint(4) DEFAULT NULL,
  `tenant_id` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `created_by` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Table structure for table `t_resource` */

DROP TABLE IF EXISTS `t_resource`;

CREATE TABLE `t_resource` (
  `id` varchar(20) COLLATE utf8mb4_bin NOT NULL,
  `name` varchar(300) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '资源名称',
  `content_type` varchar(120) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '资源类型',
  `size` bigint(20) DEFAULT NULL COMMENT '资源大小',
  `path` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '资源路径',
  `status` varchar(2) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '资源状态 0-正常 1-不可用',
  `created_by` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Table structure for table `t_sequence` */

DROP TABLE IF EXISTS `t_sequence`;

CREATE TABLE `t_sequence` (
  `name` varchar(60) COLLATE utf8mb4_bin NOT NULL COMMENT '序列名称',
  `value` bigint(20) DEFAULT NULL COMMENT '当前值',
  `step` bigint(20) DEFAULT NULL COMMENT '步长',
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Table structure for table `t_server` */

DROP TABLE IF EXISTS `t_server`;

CREATE TABLE `t_server` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `ip` varchar(150) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'IP',
  `platform` varchar(10) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'linux,unix,window,mac',
  `machine_id` int(11) DEFAULT NULL COMMENT '机器编号,小于1024大于等于0 的自然数',
  `create_at` datetime DEFAULT NULL COMMENT '创建时间',
  `update_at` datetime DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_machine_id` (`machine_id`),
  UNIQUE KEY `uk_ip` (`ip`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Table structure for table `t_tenant` */

DROP TABLE IF EXISTS `t_tenant`;

CREATE TABLE `t_tenant` (
  `id` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `name` varchar(90) COLLATE utf8mb4_bin DEFAULT NULL,
  `status` varchar(1) COLLATE utf8mb4_bin DEFAULT NULL,
  `created_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Table structure for table `t_tenant_member` */

DROP TABLE IF EXISTS `t_tenant_member`;

CREATE TABLE `t_tenant_member` (
  `id` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `tenant_id` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `user_id` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `created_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `account` varchar(30) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '账号',
  `name` varchar(60) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '姓名',
  `nickname` varchar(60) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '昵称',
  `email` varchar(60) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '手机号码',
  `status` varchar(2) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '1-启用 2-禁用',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_at` datetime DEFAULT NULL COMMENT '最后更新时间',
  `created_by` varchar(30) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人',
  `updated_by` varchar(30) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Table structure for table `t_user_data_power` */

DROP TABLE IF EXISTS `t_user_data_power`;

CREATE TABLE `t_user_data_power` (
  `id` varchar(20) COLLATE utf8mb4_bin NOT NULL,
  `user_id` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `space_id` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `datapower_id` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `tenant_id` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `created_by` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Table structure for table `t_word_item` */

DROP TABLE IF EXISTS `t_word_item`;

CREATE TABLE `t_word_item` (
  `id` varchar(20) COLLATE utf8mb4_bin NOT NULL,
  `type` varchar(10) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '词类型',
  `text` varchar(120) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '词文本',
  `tenant_id` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '租户ID',
  `created_by` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/* Function  structure for function  `nextval` */

/*!50003 DROP FUNCTION IF EXISTS `nextval` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` FUNCTION `nextval`(seq_name varchar(50)) RETURNS int(15)
    DETERMINISTIC
begin
declare currval integer;
  set currval = 0;
  insert into t_sequence (`name`, `value`, `step`)
  select seq_name, 0, 1 from dual where not exists (select 1 from t_sequence where `name` = seq_name);

  update t_sequence set `value` = `value` + `step` where `name` = seq_name;

  select `value` into currval from t_sequence where `name` = seq_name;

  return currval;

end */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
