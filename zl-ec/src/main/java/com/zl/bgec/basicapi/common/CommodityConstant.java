package com.zl.bgec.basicapi.common;

/**
 * 后台管理——常量类
 * 
 * @author: hualong
 * @date 2012-12-17 下午02:16:11
 */
public class CommodityConstant {

    // 删除标志
    /** 未删除 */
    public static final byte UNDELETE_FLAG = 0;
    /** 已删除 */
    public static final byte DELETE_FLAG = 1;
    //是否标志
    /** 显示 */
    public static final byte DISPLAY_FLAG = 0;
    /** 隐藏 */
    public static final byte HIDDEN_FLAG = 1;

    // 商品上下架状态
    /** 待上架 */
    public static final byte COMMO_DELAY_PUBLISH = 0;
    /** 上架 */
    public static final byte COMMO_PUBLISH = 1;
    /** 下架 */
    public static final byte COMMO_UNPUBLISH = 2;
    /** 停用 */
    public static final byte COMMO_STOP = 3;

    // 商品审核状态
    /** 暂时保存 */
    public static final byte TEMP_SAVE = 0;
    /** 提交审核(未审核) */
    public static final byte SUBMIT_CENSOR = 1;
    /** 审核通过 */
    public static final byte CENSOR_PASS = 2;
    /** 审核未通过 */
    public static final byte CENSOR_UNPASS = 3;
    
    // 推荐信息
    /** 推荐 */
    public static final byte RECOMMAND_FLAG = 0;
    /** 不推荐 */
    public static final byte UNRECOMMAND_FLAG = 1;

    // 启用信息
    /** 启用 */
    public static final byte ENABLE_FLAG = 0;
    /** 不起的用 */
    public static final byte UNENABLE_FLAG = 1;

    // 属性类型
    /** 扩展属性 */
    public static final byte EXT_PROP_FLAG = 0;
    /** 规格属性 */
    public static final byte SPEC_PROP_FLAG = 1;

    /** 表示商品图片，除规格图片 */
    public static final byte COMMO_PIC = 0;
    /** 表示规格属性图片 */
    public static final byte SPEC_PROP_PIC = 1;

    /** 表示非图片属性 */
    public static final byte NOT_PIC_PROP = 0;
    /** 表示为图片属性 */
    public static final byte PIC_PROP = 1;

    /** 非默认为0 */
    public static final byte NOT_DEFAULT_FLAG = 0;
    /** 默认为1 */
    public static final byte DEFAULT_FLAG = 1;

    /** 树形结构中顶级 */
    public static final int TOP_LEVEL = 0;
    /** 树形结构中顶级节点的父节点编号 */
    public static final String TOP_LEVEL_PNO = "0";

    /** 下划线 */
    public static final String UNDERLINE = "_";
    /** 右括号 */
    public static final String RIGHT_PARENTHESIS = "(";
    /** 左括号 */
    public static final String LEFT_PARENTHESIS = ")";
    /** 空格 */
    public static final String BLANK_SPACE = " ";
    /** 大于号 */
    public static final String GREATER_FLAG = ">";
    /** 逗号 */
    public static final String COMMA_FLAG = ",";
    /** 井号 */
    public static final String POUND_FLAG = "#";

    /** 极小数，用于各种数字值的变量的初始值 */
    public static final byte MINIMUM = (byte) -127;

    /** 成功 */
    public static final boolean SUCCESS = true;
    /** 错误 */
    public static final boolean ERROR = false;
    /** 失败 */
    public static final boolean FAILED = false;

    /** 下拉多选 */
    public static final int LIST_MULTI_INPUT = 3;
    /** 平铺多选 */
    public static final int LEVEL_MULTI_INPUT = 4;

    /** 换行符 */
    public static String NEW_LINE = "\n";
    /** windows下的换行符，包含回车符 */
    public static String WIN_NEW_LINE = "\r\n";

    /** 商品款号使用商品编号时，加上前缀 */
    public static String COMMO_NO_FLAG = "CN";

    // 商品多渠道信息
    /** 官网商城 */
    public static final String CHANNEL_GWSC = "gwsc"; // 官网商城
    /** 京东 */
    public static final String CHANNEL_JD = "jd"; // 京东
    /** 淘宝 */
    public static final String CHANNEL_TB = "taobao"; // 淘宝
    /** 一号店 */
    public static final String CHANNEL_YHD = "yhd"; // 一号店
    /** 当当商城 */
    public static final String CHANNEL_DD = "dangdang"; // 当当商城
    /** 默认渠(本商城)道编号，商城本身为一个渠道 ，淘宝：gwsc */
    public static final String DEFAULT_CHANNEL = "1000";

    // 仓库
    /** 默认仓库编号 */
    public static final String DEFAULT_STORAGE = "1000";
    /** 默认操作员编号 */
    public static final String DEFAULT_OPERATOR_NO = "admin";

    // 虚实库
    /** 实库 */
    public static final int ACTUAL_STOCK = 0;
    /** 虚库 */
    public static final int VIRTUAL_STOCK = 1;
    /**金立库存 */
    public static final int JL_STOCK = 2;
    
    public static final byte PROD_STATUS_STOP = 1; //货品状态禁用
    public static final byte PROS_STATUS_OPEN = 0;	//货品状态启用
    
    //临时常量
    public static final String OPERATOR_NO = "admin";
    
    //商品发票类型配置
    /** 是否可以开发票  "0" 表示不允许开发票  */
    public static final Byte NOTALLOWUSED=0;
    /** 是否可以开发票     "1" 表示允许开发票 */
    public static final Byte ALLOWUSED=1;
    /**是否允许删除   "0" 表示允许删除   */
    public static final Byte ALLOWDELETE=0;
    /**是否允许删除     "1" 表示不允许删除  */
    public static final Byte NOTALLOWDELETE=1;
    
    /**广百的分类类型 */
    public static final byte GBCATTYPE=1;
    /**金立的分类类型 */
    public static final byte JLCATTYPE=2;
    
    /**广百的商品类型 */
    public static final byte GBCOMMOTYPE=1;
    /**金立的商品类型 */
    public static final byte JLCOMMOTYPE=2;
    
    /**广百默认特价 */
    public static final byte DEFAULTISTBARGAINPRICE = -1;
    /**不是特价 */
    public static final byte ISNOTBARGAINPRICE = 0;
    /**不是特价 */
    public static final byte ISBARGAINPRICE = 1;
    /**经营方式默认值 */
    public static final byte MANAGETYPE = 0;
    
    /**默认对接其他系统的货品编号 */
    public static final String DEFAULTOTHERPRODNO="10000";
    
    /**默认运费 */
    public static final double DEFAULTFREIGHTAGE=0;
    
    /** 货主 货品对接到顺丰的接口属性*/
    public static final String COMPANY="MLH";
    /**检验字段 */
    public static final String CHECKWORD="01f18980363f40e48416464baf4cc7c0";
    /**新建 */
    public static final String NEW_INTERFACE_ACTION_CODE="NEW";
    /**修改 */
    public static final String UPDATE_INTERFACE_ACTION_CODE="SAVE";
    
    
    /** 商品分类第一级编号 */
    public final static String CAT_NO_LEVEL_1 = "level1";
    /** 商品分类第二级编号 */
    public final static String CAT_NO_LEVEL_2 = "level2";
    /** 商品分类第三级编号 */
    public final static String CAT_NO_LEVEL_3 = "level3";
    /** 运营分类第一级编号 */
    public final static String OPRT_CAT_NO_LEVEL_1 = "level1";
    /** 运营分类第二级编号 */
    public final static String OPRT_CAT_NO_LEVEL_2 = "level2";
    /** 运营分类第三级编号 */
    public final static String OPRT_CAT_NO_LEVEL_3 = "level3";
    
    public final static String CAT_NO_LEVEL ="level";
    public final static String OPRT_CAT_NO_LEVEL ="level";
}
