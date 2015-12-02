package com.zl.bgec.basicapi.commodity.service;

import java.util.List;

import com.zl.bgec.basicapi.basecomponent.page.PageFinder;
import com.zl.bgec.basicapi.commodity.po.CommodityCat;
import com.zl.bgec.basicapi.commodity.po.CommodityProp;
import com.zl.bgec.basicapi.commodity.po.CommodityPropVal;
import com.zl.bgec.basicapi.commodity.vo.CommodityPropVo;


public interface ICommodityPropService {
	/**
	 * 分页查询属性
	 */
	public PageFinder<CommodityProp> pagedCommoPropList(CommodityPropVo propVo) throws Exception;

	/**
	 * 根据商品属性编号查询商品属性(包含属性值)
	 */
	public CommodityProp getCommoProp(String propNo) throws Exception;
	
    /**
     * 根据商品属性编号逻辑删除商品属性
     */
	public void deleteCommoProp(String propNo) throws Exception;
	
   /**
    *  保存商品属性
    * 同时保存该属性的属性值
    * 同时保存二级分类和属性的关联
    */
	public String saveCommoProp(CommodityPropVo commoProVo) throws Exception;
	
    /**
     * 更新属性
     * 同时更新该属性的属性值
     * 同时更新二级分类和属性的关联
     */
    public void updateCommoProp(CommodityPropVo commoProVo) throws Exception;
    
    /**
     * 依据属性编号查询关联的二级分类
     */
	public List<CommodityCat> getBaseCommoCategoryListByPropNo(String propNo) throws Exception;
	
	/**
	 * 批量保存 非叶子级商品分类与属性的关联
	 * @param commoProVo
	 * @throws Exception
	 */
	public void saveCommoCatProp(CommodityPropVo commoProVo)throws Exception;

    /**
     * 删除属性和分类的关系
     */
	public void deleteCommoCatProp(CommodityPropVo commoProVo) throws Exception;
	
    /**
     * 通过属性编号查询关联的属性值选项
     */
	public List<CommodityPropVal> getCommoPropValOptionList(String propNo) throws Exception;
	
	/**
	 * 删除属性值
	 * @param propNo
	 * @param propValNos
	 * @throws Exception
	 */
	public void deleteCommoPropVal(String propNo,List<String> propValNos)throws Exception;

	/**
	 * 根据各种条件查询商品属性
	 * @param propVo
	 * @return
	 * @throws Exception
	 */
	public List<CommodityProp> getCommoPropList(CommodityPropVo propVo)throws Exception;
}
