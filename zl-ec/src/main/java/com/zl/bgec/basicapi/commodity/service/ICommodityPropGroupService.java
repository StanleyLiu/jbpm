package com.zl.bgec.basicapi.commodity.service;

import java.util.List;

import com.zl.bgec.basicapi.basecomponent.page.PageFinder;
import com.zl.bgec.basicapi.commodity.po.CommodityCat;
import com.zl.bgec.basicapi.commodity.po.CommodityPropGroup;
import com.zl.bgec.basicapi.commodity.vo.CommodityPropGroupVo;


public interface ICommodityPropGroupService {
	/**
	 * 1.保存商品属性组  2.属性组与非叶子级分类的关系 3.属性组与属性的关系
	 */
    public String saveCommoPropGroup(CommodityPropGroupVo commoPropGroupVo)
            throws Exception;
   
    /**
     * 保存商品属性组与非叶子级分类的关系
     */
    public void saveCommoCatPropGroup(CommodityPropGroupVo groupVo) throws Exception;

    /**
     * 逻辑删除商品属性组方法
     */
    public void deleteCommoPropGroup(String groupNo) throws Exception;

    /**
     * 查询商品属性组
     */
    public List<CommodityPropGroup> getCommoPropGroupList(CommodityPropGroupVo groupVo) throws Exception;


    /**
     * 分页查询属性组
     */
    public PageFinder<CommodityPropGroup> pagedCommoPropGroupList(CommodityPropGroupVo groupVo) throws Exception;

    /**
     * 1.批量更新属性组  2.属性组和商品分类关联表 3.属性组与属性的关系
     */
    public void updateCommoPropGroup(CommodityPropGroupVo commoPropGroupVo)
            throws Exception;
    
    /**
     *  批量更新属性组和非叶子级分类的关系
     */
    public void updateCommoCatPropGroup(List<String> catNoList, String groupNo) throws Exception;
    
    /**
     * 批量保存属性组与属性的关系
     */
    public void saveCommoPropGroupProp(CommodityPropGroupVo groupVo) throws Exception;
    
    
    /**
     * 通过属性组编号查询商品二级分类
     */
    public List<CommodityCat> getCommoCategoryListByGroupNo(String groupNo) throws Exception;
    
    /**
     * 批量更新商品属性组与属性的关系
     */
    public void updateCommoPropGroupProp(String groupNo, List<String> propNoList) throws Exception;
    

}
