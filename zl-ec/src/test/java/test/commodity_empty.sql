/*
Navicat MySQL Data Transfer

Source Server         : 21
Source Server Version : 50520
Source Host           : 192.168.0.21:3306
Source Database       : commodity

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2014-12-01 18:25:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tbl_commodity`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_commodity`;
CREATE TABLE `tbl_commodity` (
  `id` varchar(96) DEFAULT NULL,
  `commo_no` varchar(96) DEFAULT NULL,
  `commo_name` varchar(600) DEFAULT NULL,
  `commo_py_name` varchar(600) DEFAULT NULL,
  `commo_title` varchar(600) DEFAULT NULL,
  `seller_no` varchar(96) DEFAULT NULL,
  `brand_no` varchar(96) DEFAULT NULL,
  `cat_no` varchar(96) DEFAULT NULL,
  `supplier_no` varchar(150) DEFAULT NULL,
  `production_date` varchar(150) DEFAULT NULL,
  `quantity_period` varchar(150) DEFAULT NULL,
  `production_place` varchar(600) DEFAULT NULL,
  `unit` varchar(30) DEFAULT NULL,
  `market_price` double DEFAULT NULL,
  `cost_price` double DEFAULT NULL,
  `seo_key` varchar(600) DEFAULT NULL,
  `seo_title` varchar(600) DEFAULT NULL,
  `seo_description` varchar(1500) DEFAULT NULL,
  `default_pic` varchar(1500) DEFAULT NULL,
  `description` blob,
  `package_info` blob,
  `warranty_info` blob,
  `is_gift` tinyint(4) DEFAULT NULL,
  `is_redeption` tinyint(4) DEFAULT NULL,
  `is_forbid_air_cargo` tinyint(4) DEFAULT NULL,
  `sell_mode` tinyint(4) DEFAULT NULL,
  `delete_flag` tinyint(4) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `is_pre_sell` tinyint(4) DEFAULT NULL,
  `is_recommand` tinyint(4) DEFAULT NULL,
  `commo_key` varchar(600) DEFAULT NULL,
  `popularize_flag` tinyint(4) DEFAULT NULL,
  `price_section` varchar(96) DEFAULT NULL,
  `tax_rate` double DEFAULT NULL,
  `commo_style_no` varchar(96) DEFAULT NULL,
  `commo_type` tinyint(4) DEFAULT NULL COMMENT '商品的类型，区分2店铺商铺，1平台商铺',
  `commo_shop` varchar(32) DEFAULT NULL COMMENT '商品关联的商铺的编号',
  KEY `NewIndex1` (`commo_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_commodity
-- ----------------------------

-- ----------------------------
-- Table structure for `tbl_commodity_comment`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_commodity_comment`;
CREATE TABLE `tbl_commodity_comment` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `commodity_no` varchar(32) DEFAULT NULL,
  `product_no` varchar(32) DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `content` varchar(500) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `parent_id` varchar(32) DEFAULT NULL,
  `delete_flag` tinyint(4) DEFAULT NULL,
  `member_no` varchar(100) DEFAULT NULL,
  `member_name` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_commodity_comment
-- ----------------------------

-- ----------------------------
-- Table structure for `tbl_commodity_expand_prop`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_commodity_expand_prop`;
CREATE TABLE `tbl_commodity_expand_prop` (
  `id` varchar(32) NOT NULL,
  `commo_no` varchar(32) NOT NULL COMMENT '商品编号',
  `prop_no` varchar(32) NOT NULL COMMENT '扩展属性编号',
  `option_no` varchar(200) NOT NULL DEFAULT '' COMMENT '选项值编号(如果输入方式为文本框直接输入的，该值为null)，当本字段存在值，则“value”字段的值无效',
  `oprion_name` varchar(200) DEFAULT NULL COMMENT '选项的值（如果输入方式为文本框直接输入的，则该字段保存该输入值）',
  `prop_name` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`,`option_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_commodity_expand_prop
-- ----------------------------

-- ----------------------------
-- Table structure for `tbl_commodity_no`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_commodity_no`;
CREATE TABLE `tbl_commodity_no` (
  `id` varchar(50) NOT NULL,
  `cat_no` varchar(2) NOT NULL COMMENT '顶级商品分类编号',
  `seq` int(9) NOT NULL COMMENT '顶级商品分类编号',
  `step` int(3) NOT NULL COMMENT '程序每次取值个数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品编号生成表';

-- ----------------------------
-- Records of tbl_commodity_no
-- ----------------------------

-- ----------------------------
-- Table structure for `tbl_commodity_shop`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_commodity_shop`;
CREATE TABLE `tbl_commodity_shop` (
  `id` varchar(32) NOT NULL,
  `seller_no` varchar(32) NOT NULL,
  `shop_no` varchar(32) NOT NULL,
  `commodity_co` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_commodity_shop
-- ----------------------------

-- ----------------------------
-- Table structure for `tbl_commodity_user`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_commodity_user`;
CREATE TABLE `tbl_commodity_user` (
  `id` varchar(32) DEFAULT NULL,
  `commo_no` varchar(32) DEFAULT NULL,
  `user_no` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_commodity_user
-- ----------------------------

-- ----------------------------
-- Table structure for `tbl_commo_brand`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_commo_brand`;
CREATE TABLE `tbl_commo_brand` (
  `id` varchar(32) NOT NULL,
  `brand_no` varchar(32) NOT NULL COMMENT '品牌编号',
  `brand_name` varchar(100) NOT NULL COMMENT '品牌中文名称',
  `brand_en_name` varchar(100) NOT NULL COMMENT '品牌英文名称',
  `brand_title` varchar(200) DEFAULT NULL COMMENT '品牌标语',
  `brand_key` varchar(200) DEFAULT NULL COMMENT '品牌关键字',
  `description` varchar(500) DEFAULT NULL COMMENT '品牌描述',
  `website` varchar(500) DEFAULT NULL COMMENT '品牌网站',
  `is_display` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否显示，0显示 1不显示',
  `logo_big_pic` varchar(500) DEFAULT NULL COMMENT '品牌logo大图',
  `logo_mid_pic` varchar(500) DEFAULT NULL COMMENT '品牌logo中图',
  `logo_sml_pic` varchar(500) DEFAULT NULL COMMENT '品牌logo小图',
  `seo_key` varchar(200) DEFAULT NULL COMMENT '搜索关键字',
  `seo_title` varchar(200) DEFAULT NULL COMMENT '搜索标题',
  `seo_description` varchar(500) DEFAULT NULL COMMENT '搜索描述',
  `delete_flag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除标识',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `brand_type` tinyint(4) DEFAULT NULL COMMENT '品牌类型 1表示高线品牌  2 表示中线品牌',
  PRIMARY KEY (`id`),
  UNIQUE KEY `tbl_brand_brand_no` (`brand_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='品牌表';

-- ----------------------------
-- Records of tbl_commo_brand
-- ----------------------------

-- ----------------------------
-- Table structure for `tbl_commo_category`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_commo_category`;
CREATE TABLE `tbl_commo_category` (
  `id` varchar(32) NOT NULL,
  `cat_no` varchar(32) NOT NULL COMMENT '类别编号',
  `cat_name` varchar(100) NOT NULL COMMENT '类别名称',
  `cat_pno` varchar(32) NOT NULL COMMENT '商品分类父类编号',
  `cat_path` varchar(500) DEFAULT NULL COMMENT '分类名称路径',
  `sort_no` int(11) DEFAULT NULL COMMENT '树形同一级的节点排序编号',
  `description` varchar(500) DEFAULT NULL COMMENT '描述',
  `delete_flag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除标识 0未删除1已删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `cat_type` tinyint(4) NOT NULL COMMENT '分类类型，1表示广百分类，2表示金立分类',
  PRIMARY KEY (`id`),
  UNIQUE KEY `tbl_commo_category_cat_no` (`cat_no`),
  KEY `NewIndex1` (`cat_pno`),
  KEY `NewIndex2` (`cat_path`(255))
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品分类表';

-- ----------------------------
-- Records of tbl_commo_category
-- ----------------------------

-- ----------------------------
-- Table structure for `tbl_commo_category_no`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_commo_category_no`;
CREATE TABLE `tbl_commo_category_no` (
  `id` varchar(96) DEFAULT NULL,
  `no_type` varchar(150) DEFAULT NULL,
  `cat_pno` varchar(96) DEFAULT NULL,
  `seq` double DEFAULT NULL,
  `step` tinyint(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_commo_category_no
-- ----------------------------

-- ----------------------------
-- Table structure for `tbl_commo_cat_group_prop`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_commo_cat_group_prop`;
CREATE TABLE `tbl_commo_cat_group_prop` (
  `id` varchar(32) NOT NULL,
  `cat_no` varchar(32) NOT NULL COMMENT '商品分类编号',
  `prop_no` varchar(32) NOT NULL COMMENT '属性值选项编号',
  `group_no` varchar(32) DEFAULT NULL COMMENT '属性组编号',
  PRIMARY KEY (`id`),
  KEY `NewIndex1` (`cat_no`,`prop_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品分类、属性组、属性、属性值选项的关联';

-- ----------------------------
-- Records of tbl_commo_cat_group_prop
-- ----------------------------

-- ----------------------------
-- Table structure for `tbl_commo_cat_prop`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_commo_cat_prop`;
CREATE TABLE `tbl_commo_cat_prop` (
  `id` varchar(96) DEFAULT NULL,
  `cat_no` varchar(96) DEFAULT NULL,
  `prop_no` varchar(96) DEFAULT NULL,
  KEY `NewIndex1` (`cat_no`,`prop_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_commo_cat_prop
-- ----------------------------

-- ----------------------------
-- Table structure for `tbl_commo_cat_prop_group`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_commo_cat_prop_group`;
CREATE TABLE `tbl_commo_cat_prop_group` (
  `id` varchar(96) DEFAULT NULL,
  `cat_no` varchar(96) DEFAULT NULL,
  `group_no` varchar(96) DEFAULT NULL,
  KEY `NewIndex1` (`cat_no`,`group_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_commo_cat_prop_group
-- ----------------------------

-- ----------------------------
-- Table structure for `tbl_commo_censor`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_commo_censor`;
CREATE TABLE `tbl_commo_censor` (
  `id` varchar(96) DEFAULT NULL,
  `commo_no` varchar(96) DEFAULT NULL,
  `reason` varchar(600) DEFAULT NULL,
  `operator_no` varchar(96) DEFAULT NULL,
  `operate_time` date DEFAULT NULL,
  `censor_status` tinyint(4) DEFAULT NULL,
  `operator_name` varchar(200) DEFAULT NULL,
  KEY `NewIndex1` (`commo_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_commo_censor
-- ----------------------------

-- ----------------------------
-- Table structure for `tbl_commo_prop`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_commo_prop`;
CREATE TABLE `tbl_commo_prop` (
  `id` varchar(32) NOT NULL,
  `prop_no` varchar(32) NOT NULL COMMENT '扩展属性编号',
  `prop_name` varchar(50) NOT NULL COMMENT '扩展属性名称',
  `input_type` tinyint(4) NOT NULL COMMENT '输入方式（0:文本输入，1:下拉单选，2:平铺单选，3下拉多选，4平铺多选)',
  `is_required` tinyint(4) NOT NULL COMMENT '表示是否必须有值：0表示非必须，1表示必须 ',
  `is_relate_pic` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否关联图片 0不关联 1关联',
  `is_spec_prop` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否为规格属性 0非规格属性 1规格属性',
  `screen_type` tinyint(4) NOT NULL COMMENT '是否用于筛选及筛选类型：0非筛选条件 1单值筛选 2多值筛选',
  `description` varchar(500) DEFAULT NULL COMMENT '描述',
  `delete_flag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除标识0未删除1已删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `tbl_commo_prop_prop_no` (`prop_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品扩展属性表';

-- ----------------------------
-- Records of tbl_commo_prop
-- ----------------------------

-- ----------------------------
-- Table structure for `tbl_commo_prop_group`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_commo_prop_group`;
CREATE TABLE `tbl_commo_prop_group` (
  `id` varchar(32) NOT NULL,
  `group_no` varchar(32) NOT NULL COMMENT '属性组编号',
  `group_name` varchar(50) NOT NULL COMMENT '属性组名称',
  `description` varchar(500) DEFAULT NULL COMMENT '描述',
  `delete_flag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除标识',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `is_spec_group` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `tbl_commo_prop_group_group_no` (`group_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品属性组表';

-- ----------------------------
-- Records of tbl_commo_prop_group
-- ----------------------------

-- ----------------------------
-- Table structure for `tbl_commo_prop_group_prop`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_commo_prop_group_prop`;
CREATE TABLE `tbl_commo_prop_group_prop` (
  `id` varchar(32) NOT NULL,
  `group_no` varchar(32) NOT NULL COMMENT '商品属性组编号',
  `prop_no` varchar(32) NOT NULL COMMENT '属性编号',
  PRIMARY KEY (`id`),
  KEY `FK1265446223BAD326` (`prop_no`),
  KEY `FK1265446217F61877` (`group_no`),
  CONSTRAINT `tbl_commo_prop_group_prop_ibfk_1` FOREIGN KEY (`group_no`) REFERENCES `tbl_commo_prop_group` (`group_no`),
  CONSTRAINT `tbl_commo_prop_group_prop_ibfk_2` FOREIGN KEY (`prop_no`) REFERENCES `tbl_commo_prop` (`prop_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='商品属性组和商品属性的关联表';

-- ----------------------------
-- Records of tbl_commo_prop_group_prop
-- ----------------------------

-- ----------------------------
-- Table structure for `tbl_commo_prop_val`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_commo_prop_val`;
CREATE TABLE `tbl_commo_prop_val` (
  `id` varchar(32) NOT NULL,
  `prop_no` varchar(32) NOT NULL COMMENT '属性编号',
  `option_no` varchar(32) NOT NULL COMMENT '选项值编号',
  `option_name` varchar(200) NOT NULL COMMENT '选项值名称',
  `delete_flag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除标识 0未删除 1已删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `option_pic` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `tbl_commo_prop_val_option_unique` (`option_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品属性值选项表';

-- ----------------------------
-- Records of tbl_commo_prop_val
-- ----------------------------

-- ----------------------------
-- Table structure for `tbl_commo_publish`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_commo_publish`;
CREATE TABLE `tbl_commo_publish` (
  `id` varchar(32) NOT NULL,
  `commo_no` varchar(32) NOT NULL COMMENT '商品编号',
  `channel_no` varchar(32) NOT NULL COMMENT '渠道编号',
  `operator_no` varchar(32) NOT NULL COMMENT '操作员编号',
  `operate_time` date NOT NULL COMMENT '操作时刻',
  `publish_status` tinyint(4) DEFAULT NULL COMMENT '上下架状态： 0-待上架(默认)，1-上架，2-下架',
  `operator_name` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `tbl_commo_publish_unique` (`commo_no`,`channel_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品上下架表';

-- ----------------------------
-- Records of tbl_commo_publish
-- ----------------------------

-- ----------------------------
-- Table structure for `tbl_commo_tag`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_commo_tag`;
CREATE TABLE `tbl_commo_tag` (
  `id` varchar(32) NOT NULL,
  `seller_no` varchar(32) NOT NULL COMMENT '卖家编号',
  `tag_no` varchar(32) NOT NULL COMMENT '标签编号',
  `tag_name` varchar(50) NOT NULL COMMENT '标签名称',
  `description` varchar(500) DEFAULT NULL COMMENT '商品标签描述',
  `enable_flag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否启用：0启用，1不启用',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `tag_no` (`tag_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品标签表';

-- ----------------------------
-- Records of tbl_commo_tag
-- ----------------------------

-- ----------------------------
-- Table structure for `tbl_commo_tag_commo`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_commo_tag_commo`;
CREATE TABLE `tbl_commo_tag_commo` (
  `id` varchar(32) NOT NULL,
  `commo_no` varchar(32) NOT NULL COMMENT '商品编号',
  `tag_no` varchar(32) NOT NULL COMMENT '商品标签编号',
  PRIMARY KEY (`id`,`commo_no`,`tag_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品标签与商品的关联表';

-- ----------------------------
-- Records of tbl_commo_tag_commo
-- ----------------------------

-- ----------------------------
-- Table structure for `tbl_operating_category`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_operating_category`;
CREATE TABLE `tbl_operating_category` (
  `id` varchar(32) NOT NULL,
  `oprt_cat_no` varchar(32) NOT NULL COMMENT '分类编号',
  `oprt_cat_name` varchar(50) NOT NULL COMMENT '分类名称',
  `oprt_cat_pno` varchar(32) NOT NULL COMMENT '父分类编号',
  `oprt_cat_path` varchar(200) DEFAULT NULL COMMENT '分类路径',
  `oprt_cat_key` varchar(200) DEFAULT NULL COMMENT '分类关键字',
  `sort_no` int(11) DEFAULT NULL COMMENT '排序号同一分类的位置',
  `description` varchar(500) DEFAULT NULL COMMENT '分类描述',
  `seo_key` varchar(200) DEFAULT NULL COMMENT 'SEO关键字',
  `seo_description` varchar(500) DEFAULT NULL COMMENT 'SEO描述',
  `is_enable` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否启用 0启用 1不启用',
  `is_recommand` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否推荐 0不推荐 1推荐',
  `delete_flag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除标识 0未删除 1已删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `seo_title` varchar(200) DEFAULT NULL,
  `oprt_pic` varchar(500) DEFAULT NULL COMMENT '运营分类图片',
  PRIMARY KEY (`id`),
  UNIQUE KEY `tbl_operating_category_oprt_cat_no` (`oprt_cat_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='运营分类';

-- ----------------------------
-- Records of tbl_operating_category
-- ----------------------------

-- ----------------------------
-- Table structure for `tbl_operating_category_no`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_operating_category_no`;
CREATE TABLE `tbl_operating_category_no` (
  `id` varchar(32) NOT NULL,
  `no_type` varchar(50) NOT NULL COMMENT '编号类型',
  `oprt_cat_pno` varchar(32) NOT NULL COMMENT '编号类型，三级分类：level1,level2,level3',
  `seq` int(2) NOT NULL COMMENT '增长序列，从1开始',
  `step` int(2) NOT NULL COMMENT '程序每次取值个数',
  PRIMARY KEY (`id`),
  UNIQUE KEY `tbl_operating_category_no_unique` (`no_type`,`oprt_cat_pno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='运营分类编号生成表';

-- ----------------------------
-- Records of tbl_operating_category_no
-- ----------------------------

-- ----------------------------
-- Table structure for `tbl_operating_cat_commo`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_operating_cat_commo`;
CREATE TABLE `tbl_operating_cat_commo` (
  `id` varchar(32) NOT NULL,
  `oprt_cat_no` varchar(32) NOT NULL COMMENT '运营分类编号',
  `commo_no` varchar(32) NOT NULL COMMENT '商品编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='运营分类与商品的关联表';

-- ----------------------------
-- Records of tbl_operating_cat_commo
-- ----------------------------

-- ----------------------------
-- Table structure for `tbl_other_table_no`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_other_table_no`;
CREATE TABLE `tbl_other_table_no` (
  `id` varchar(32) NOT NULL,
  `no_type` varchar(50) NOT NULL COMMENT '编号类型',
  `seq` int(8) NOT NULL COMMENT '增长序列，从1开始',
  `step` int(3) NOT NULL COMMENT '程序每次取值个数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='普通表记录编号生成表';

-- ----------------------------
-- Records of tbl_other_table_no
-- ----------------------------
INSERT INTO `tbl_other_table_no` VALUES ('40288545401e2d7101401e30550f0000', 'brand_no_type', '10000000', '10');
INSERT INTO `tbl_other_table_no` VALUES ('40288545401e2d7101401e3070090001', 'tag_no_type', '10000000', '10');
INSERT INTO `tbl_other_table_no` VALUES ('40288545401e2d7101401e3088440002', 'group_no_type', '10000000', '10');
INSERT INTO `tbl_other_table_no` VALUES ('40288545401e2d7101401e30a7650003', 'prop_no_type', '10000000', '10');
INSERT INTO `tbl_other_table_no` VALUES ('40288545401e2d7101401e30c1940004', 'val_option_no_type', '10000000', '10');
INSERT INTO `tbl_other_table_no` VALUES ('40288545401e2d7101401e30d9cf0005', 'commo_pic_no_type', '10000000', '10');
INSERT INTO `tbl_other_table_no` VALUES ('4028855040af81f60140af83701d0000', 'price_change_no_type', '10000000', '5');
INSERT INTO `tbl_other_table_no` VALUES ('4028858446d6eb360146d6ed730d0001', 'base_shop_no', '10000000', '5');
INSERT INTO `tbl_other_table_no` VALUES ('402885f44279b82f014279ba23920001', 'receipt_type_no', '10000000', '5');

-- ----------------------------
-- Table structure for `tbl_product`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_product`;
CREATE TABLE `tbl_product` (
  `id` varchar(32) NOT NULL,
  `prod_no` varchar(32) NOT NULL COMMENT '货品编号',
  `prod_name` varchar(200) NOT NULL COMMENT '货品名称：由 商品名称+上规格值 组成',
  `prod_status` tinyint(4) NOT NULL DEFAULT '0',
  `commo_no` varchar(32) NOT NULL COMMENT '商品编号',
  `seller_no` varchar(32) NOT NULL COMMENT '卖家编号',
  `seller_prod_no` varchar(50) DEFAULT NULL COMMENT '货品条码或卖家自定义编号',
  `weight` double DEFAULT NULL COMMENT '重量，单位千克',
  `volume` double DEFAULT NULL COMMENT '体积,单位立方米',
  `default_pic` varchar(500) DEFAULT NULL COMMENT '默认图片路径，货品所属规格相关图片组的第一张',
  `is_virtual_stock` tinyint(4) NOT NULL COMMENT '货品是否为虚库:0非虚库 1为虚库',
  `delete_flag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除标识 0未删除 1已删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `freightage` double DEFAULT NULL COMMENT '货品的运费',
  `isBargainPrice` tinyint(4) DEFAULT NULL COMMENT '是否特价',
  `other_product_no` varchar(32) DEFAULT NULL COMMENT '对接其他系统的货品编号',
  `manageType` tinyint(4) DEFAULT NULL COMMENT '货品的经营方式 1代销，2经销，3带货安装，4联营专柜 ，0为默认值',
  `price` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `prod_no` (`prod_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='货品表';

-- ----------------------------
-- Records of tbl_product
-- ----------------------------

-- ----------------------------
-- Table structure for `tbl_product_no`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_product_no`;
CREATE TABLE `tbl_product_no` (
  `id` varchar(50) NOT NULL,
  `commo_no` varchar(11) NOT NULL COMMENT '商品编号',
  `seq` int(2) NOT NULL COMMENT '增长序列，从1开始',
  `step` int(2) NOT NULL COMMENT '程序每次取值个数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='货品编号生成表';

-- ----------------------------
-- Records of tbl_product_no
-- ----------------------------

-- ----------------------------
-- Table structure for `tbl_product_price_change`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_product_price_change`;
CREATE TABLE `tbl_product_price_change` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `prod_price_change_no` varchar(32) NOT NULL DEFAULT '' COMMENT '货品调价单编号',
  `operator_name` varchar(32) NOT NULL DEFAULT '' COMMENT '调价申请人姓名',
  `operator_time` datetime NOT NULL COMMENT '申请时间',
  `conser_status` tinyint(4) NOT NULL COMMENT '审核状态 1待审核 2审核通过 3审核失败',
  `conser_desc` varchar(500) DEFAULT NULL COMMENT '审核理由',
  `prod_desc` varchar(500) DEFAULT NULL COMMENT '调价描述',
  `operator_no` varchar(32) DEFAULT NULL COMMENT '申请人编号',
  `conser_name` varchar(32) DEFAULT NULL COMMENT '审核人姓名',
  `conser_no` varchar(32) DEFAULT NULL COMMENT '审核人编号',
  `conser_time` datetime DEFAULT NULL COMMENT '审核时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_product_price_change
-- ----------------------------

-- ----------------------------
-- Table structure for `tbl_product_price_change_item`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_product_price_change_item`;
CREATE TABLE `tbl_product_price_change_item` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `prod_price_change_no` varchar(32) NOT NULL DEFAULT '' COMMENT '货品调价单编号',
  `prod_no` varchar(32) NOT NULL DEFAULT '' COMMENT '货品编号',
  `pre_sell_price` double NOT NULL COMMENT '调前销售价',
  `new_sell_price` double NOT NULL COMMENT '调后销售价',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_product_price_change_item
-- ----------------------------

-- ----------------------------
-- Table structure for `tbl_product_prop`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_product_prop`;
CREATE TABLE `tbl_product_prop` (
  `id` varchar(32) NOT NULL,
  `commo_no` varchar(32) NOT NULL COMMENT '商品编号',
  `prod_no` varchar(32) DEFAULT NULL COMMENT '货品编号',
  `prop_no` varchar(32) NOT NULL COMMENT '扩展属性编号',
  `option_no` varchar(200) NOT NULL DEFAULT '' COMMENT '选项值编号(如果输入方式为文本框直接输入的，该值为null)，当本字段存在值，则“value”字段的值无效',
  `oprion_name` varchar(200) DEFAULT NULL COMMENT '选项的值（如果输入方式为文本框直接输入的，则该字段保存该输入值）',
  `prop_name` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`,`option_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品属性值表';

-- ----------------------------
-- Records of tbl_product_prop
-- ----------------------------

-- ----------------------------
-- Table structure for `tbl_product_stock`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_product_stock`;
CREATE TABLE `tbl_product_stock` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `product_no` varchar(32) DEFAULT NULL,
  `stock` int(10) NOT NULL,
  `virtual_stock` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_product_stock
-- ----------------------------
