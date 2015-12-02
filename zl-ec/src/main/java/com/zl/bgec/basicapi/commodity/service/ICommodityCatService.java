package com.zl.bgec.basicapi.commodity.service;

import java.util.List;
import java.util.Map;

import com.zl.bgec.basicapi.basecomponent.page.PageFinder;
import com.zl.bgec.basicapi.commodity.po.CommodityCat;
import com.zl.bgec.basicapi.commodity.po.CommodityCatGroupProp;
import com.zl.bgec.basicapi.commodity.po.CommodityProp;
import com.zl.bgec.basicapi.commodity.vo.CommodityCatVo;
import com.zl.bgec.basicapi.commodity.vo.CommodityPropGroupVo;



public interface ICommodityCatService{
    /**
     * 查询商品分类信息
     */
    public List<CommodityCat>  getCommodityCatList(CommodityCatVo commodityCatVo) throws Exception;

    public List<Map<String, String>>  getCommodityCatList(Map<String,String> map) throws Exception;
    /**
     * 查询商品分类信息(分页)
     */
    public PageFinder<CommodityCat>  queryCommodityCatList(CommodityCatVo commodityCatVo) throws Exception;
    /**
     * 添加商品分类信息
     * @param commodityCatVo 商品分类数据对象
     * @param catGno 商品分类父分类编号
     * @param userNo 用户编号 
     * @param catGnolevel 商品分类父分类层级
     * @return 商品分类编号
     * @throws Exception
     */
    public String saveCommodityCat(CommodityCatVo commodityCatVo)throws Exception;
    
    /**
     * 删除商品分类信息
     */
    public void deleteCommodityCat(String catNo) throws Exception;

    /**
     * 更新商品分类
     */
    public void eidtCommodityCat(CommodityCatVo commodityCatVo)throws Exception;

    /**
     * 1.依据分类编号查询属性组2.根据属性组查询关联的属性 （用于建立属性和分类之间的关联关系）
     */
    public List<CommodityPropGroupVo> commonCatRealateProp(String catNo) throws Exception;

    /**
     * 保存 商品分类、属性组、属性的关联表
     */
    public void saveCommoCatGroupProp(CommodityCatVo commodityCatVo) throws Exception;

    /**
     * 依据编号查询商品分类，属性组，属性的关联表
     */
    public List<CommodityCatGroupProp> getCommoCatGroupPropList(String catNo) throws Exception;
    /**
     * 依据编号查询商品分类，属性组，属性的关联表获取商品属性，用于添加商品
     */
    public List<CommodityProp> getCommoCatPropList(CommodityCatVo catVo) throws Exception;
}
