package com.zl.bgec.basicapi.commodity.service;

import java.util.List;

import com.zl.bgec.basicapi.basecomponent.page.PageFinder;
import com.zl.bgec.basicapi.commodity.po.Commodity;
import com.zl.bgec.basicapi.commodity.po.CommodityOperCat;
import com.zl.bgec.basicapi.commodity.vo.CommodityOperCatVo;


public interface ICommodityOperCatService {

    /**
     * 添加商品运营分类信息
     */
    public String saveCommodityOperCat(CommodityOperCatVo commodityOperCatVo) throws Exception;

    /**
     * 删除商品运营分类信息
     */
    public void deleteCommodityOper(String catNo) throws Exception;

    /**
     * 更新商品运营分类信息
     */
    public void updateCommodityOper(CommodityOperCatVo commodityOperCatVo) throws Exception;

    /**
     * 查询商品运营分类
     */
    public List<CommodityOperCat> getOperatorCats(CommodityOperCatVo commodityOperCatVo) throws Exception;



    /**
     * 分页查询该商品运营分类编号与商品关联的商品信息
     * 
     */
    public PageFinder<Commodity> pagedOprtCatRelateCommodityList(CommodityOperCatVo operCatVo) throws Exception;

    /**
     * 查找没有和该运营分类编号关联的商品
     *
     */
    public PageFinder<Commodity> pagedOprtCatNotRelateCommodityList(CommodityOperCatVo operCatVo) throws Exception;

    /**
     * 保存运营分类与商品之间的关联 
     * 
     */
    public void updateOperatingCatCommo(CommodityOperCatVo operCatVo) throws Exception;

    /**
     * 更新运营分类与商品的关联
     * 
     */
    public void deleteOperatingCatCommo(String oprtCatNo, List<String> commoNoList)
            throws Exception;

    /**
     * 根据商品编号查询关联的第三级运营分类实体
     * (运营分类编号和运营分类名称作为搜索条件)
     */
    public PageFinder<CommodityOperCat> getRealateOprtCatByCommono(CommodityOperCatVo operCatVo) throws Exception;

    /**
     * 根据商品编号查询没有关联的第三级运营分类实体
     * (运营分类编号和运营分类名称作为搜索条件)
     */
    public PageFinder<CommodityOperCat> getNotRealateOprtCatByCommono(CommodityOperCatVo operCatVo) throws Exception;
    
    /**
     * 保存与商品有关联 的运营分类
     */
    public void saveOperatingCatCommo(CommodityOperCatVo operCatVo) throws Exception;
    
    /**
     * 删除与该商品有关联的商品运营分类
     */
    public void delOperatingCatCommo(List<String> oprtCatNoList, String commoNo) throws Exception;
    
    /**
     * 查询运营分类（分页）
     * @param body
     * @return
     * @throws Exception
     */
	public PageFinder<CommodityOperCat> pagedOperatorCats(
			CommodityOperCatVo body)throws Exception;
    

}
