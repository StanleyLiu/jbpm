package com.zl.bgec.basicapi.commodity.service;

import java.util.List;
import java.util.Map;

import com.zl.bgec.basicapi.basecomponent.page.PageFinder;
import com.zl.bgec.basicapi.commodity.po.CommodityBrand;
import com.zl.bgec.basicapi.commodity.vo.CommodityBrandVo;


public interface ICommodityBrandService {
	/**
     * 保存品牌
     * 
     * @param brandDo
     * @return 品牌编号
     * @throws Exception
     * @author: zhaoyunlu
     */
    public String saveBrand(CommodityBrandVo brandVo) throws Exception;

    /**
     * 依据品牌编号逻辑删除品牌。
     * 
     * @param brandNo
     *            品牌编号
     * @return 0=删除成功；1=品牌关联了商品
     * @throws Exception
     * @author: zhaoyunlu
     */
    public void deleteBrand(CommodityBrandVo brandVo) throws Exception;

    /**
     * 依据品牌编号修改品牌。
     * 
     * @param brandDo
     * @throws Exception
     * @author: zhaoyunlu
     */
    public void updateBrand(CommodityBrandVo brandVo) throws Exception;

    /**
     * 批量显示品牌
     * 
     * @param brandNos
     *            品牌列表
     * @throws
     * @author: zhaoyunlu
     */
    public void batchDisplayBrand(List<String> brandNos) throws Exception;

    /**
     * 批量隐藏品牌
     * 
     * @param brandNos
     *            品牌列表
     * @throws
     * @author: zhaoyunlu
     */
    public void batchHiddenBrand(List<String> brandNos) throws Exception;

    /**
     * 依据品牌编号查询对应的品牌。
     * 
     * @param brandNo
     * @return
     * @throws Exception
     * @author: zhaoyunlu
     */
    public CommodityBrand getBrand(String brandNo) throws Exception;

    /**
     * 依据品牌名称查询品牌。
     * 
     * @param brandName
     * @return
     * @throws Exception
     * @throws
     * @author: zhaoyunlu
     */
    public CommodityBrand getBrandByName(String brandName) throws Exception;

    /**
     * 依据品牌名称搜索品牌。
     * 
     * @param brandName
     * @return
     * @throws Exception
     * @author: zhaoyunlu
     */
    public List<CommodityBrand> getBrandListByName(String brandName) throws Exception;

    /**
     * 分页查询品牌。
     * 
     * @param queryBean
     * @return
     * @throws Exception
     * @author: zhaoyunlu
     */
    public PageFinder<CommodityBrand> pagedBrandList(CommodityBrandVo brandVo) throws Exception;

    /**
     * 查询全部品牌
     * 
     * @return
     * @throws Exception
     * @author: zhachengcheng
     */
    public List<CommodityBrand> getBrandList(CommodityBrandVo brandVo) throws Exception;

    /**
     * 通过多个品牌编号查询品牌实体
     * 
     * @return:List<BrandDo>
     * @author: liuxiaolong
     */
    public List<CommodityBrand> getBrandListByBrandNos(List<String> brandNos);

    /**
     * 根据运营分类编号查询相关品牌
     * 
     * @param oprtCatNo
     *            运营分类
     * @param pageNo
     *            分页号
     * @param pageSize
     *            每页记录数
     * @return
     * @throws Exception
     * @author: zhaoyunlu
     */
    public PageFinder<CommodityBrand> pagedBrandListByOprtCatNo(String oprtCatNo, int pageNo, int pageSize) throws Exception;

    /**
     * 判断品牌是否含有商品，返回品牌编号-是否有商品的map。
     * 
     * @param brandNos
     *            品牌列表
     * @return 返回Map，key为品牌编号，value为是否有图片
     * @throws Exception
     * @author: zhaoyunlu
     */
    public Map<String, Boolean> hasCommodity(List<String> brandNos) throws Exception;

    /**
     * 根据给定的中文模糊搜索品牌供添加商品
     * 
     * @param word 输入的中文
     * @return
     * @throws Exception
     * @author: zhaoyunlu
     */
    public List<CommodityBrand> getBrandListForSearch(String word)throws Exception;
	
}
