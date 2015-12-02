package com.zl.bgec.basicapi.commodity.service;

public interface ICommoNoCreatorService {
	

    /**
     * 产生商品分类编号
     * 
     * @param noType
     *            编号类型 取值为：level1,level2,level3
     * @param catPno
     *            商品分类父编号，顶级分类的父编号请输入“0”
     * @return
     * @throws
     * @author: Hualong
     * @date: 2013-7-27上午10:27:29
     */
    public String createCatNo(String noType, String catPno)throws Exception;

    /**
     * 产生运营分类编号
     * 
     * @param noType
     *            编号类型 取值为：level1,level2,level3
     * @param opertCatPno
     *            运营分类父编号，顶级分类的父编号请输入“0”
     * @return
     * @throws
     * @author: Hualong
     * @date: 2013-7-27上午10:28:31
     */
    public String createOprtCatNo(String noType, String opertCatPno)throws Exception;

    /**
     * 产生商品编号
     * 
     * @param catNo
     *            顶级分类编号
     * @return
     * @throws
     * @author: Hualong
     * @date: 2013-7-27上午10:26:48
     */
    public String createCommoNo(String catNo)throws Exception;

    /**
     * 
     * 产生货品编号
     * 
     * @param commoNo
     *            商品编号
     * @return
     * @throws
     * @author: Hualong
     * @date: 2013-7-27上午10:27:12
     */
    public String createProdNo(String commoNo)throws Exception;

    /**
     * 产生品牌编号
     * 
     * @return
     * @throws
     * @author: Hualong
     * @date: 2013-7-27上午10:29:05
     */
    public String createBrandNo()throws Exception;

    /**
     * 产生标签编号
     * 
     * @return
     * @throws
     * @author: Hualong
     * @date: 2013-7-27上午10:29:15
     */
    public String createTagNo()throws Exception;

    /**
     * 产生属性组编号
     * 
     * @return
     * @throws
     * @author: Hualong
     * @date: 2013-7-27上午10:29:24
     */
    public String createGroupNo()throws Exception;

    /**
     * 产生属性编号
     * 
     * @return
     * @throws
     * @author: Hualong
     * @date: 2013-7-27上午10:29:37
     */
    public String createPropNo()throws Exception;

    /**
     * 产生属性值选项编号
     * 
     * @return
     * @throws
     * @author: Hualong
     * @date: 2013-7-27上午10:29:47
     */
    public String createValOptionNo()throws Exception;

    /**
     * 产生商品相关图片编号
     * 
     * @return
     * @throws
     * @author: Hualong
     * @date: 2013-7-27上午10:30:01
     */
    public String createCommoPicNo()throws Exception;

    /**
     * 生成调价单编号。
     * 
     * @return
     * @throws
     * @author: Hualong
     * @date: 2013-7-27下午01:39:07
     */
    public String createPriceChangeNo()throws Exception;
    
}
