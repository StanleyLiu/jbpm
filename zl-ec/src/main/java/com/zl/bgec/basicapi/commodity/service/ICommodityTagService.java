package com.zl.bgec.basicapi.commodity.service;

import java.util.List;

import com.zl.bgec.basicapi.basecomponent.page.PageFinder;
import com.zl.bgec.basicapi.commodity.po.Commodity;
import com.zl.bgec.basicapi.commodity.po.CommodityTag;
import com.zl.bgec.basicapi.commodity.vo.CommodityTagVo;


public interface ICommodityTagService {
	/**
     * 添加一个商品标签
     */
    public String saveCommoTag(CommodityTagVo commoTagvo) throws Exception;

    /**
     * 保存标签与商品的关联 
     */
    public void saveCommoTagCommo(CommodityTagVo tagVo) throws Exception;

    /**
     * 物理删除一个商品标签
     */
    public void deleteCommoTag(CommodityTagVo tagVo) throws Exception;

    /**
     * 物理删除标签与商品的关联 
     */
    public void deleteCommoTagCommo(String tagNo, List<String> commoNoList) throws Exception;

    /**
     * 查询一个商品标签
     */
    public CommodityTag getCommoTag(String tagNo) throws Exception;

    /**
     * 分面查询商品标签列表
     */
    public PageFinder<CommodityTag> pagedCommoTagList(CommodityTagVo tagVo) throws Exception;

    /**
     * 更新商品标签
     */
    public void updateCommoTag(CommodityTagVo tagVo) throws Exception;

    /**
     * 更新标签与商品的关系
     */
    public void updateCommoTagCommo(CommodityTagVo tagVo)
            throws Exception;

    /**
     * 删除多个标签对应一个商品的关联表
     */
    public void deleteCommoTagCommoByTagNo(List<String> tagNoList, String commoNo) throws Exception;

    /**
     * 查询全部的标签
     */
    public List<CommodityTag> getCommoTagList() throws Exception;

    /**
     * 分页查询该商品标签编号与商品关联的商品信息
     * 
     */
    public PageFinder<Commodity> pagedCommoTagRelateCommodityList(CommodityTagVo tagVo)throws Exception;

    /**
     * 分页查找没有和该商品标签关联的商品
     * 
     */
    public PageFinder<Commodity> pagedCommoTagNotRelateCommodityList(CommodityTagVo tagVo)throws Exception;
    
    /**
     * 查询该商品标签编号与商品关联的商品信息
     * 
     */
    public List<Commodity> getCommoTagRelateCommodityList(CommodityTagVo tagVo)throws Exception;

    /**
     * 查找没有和该商品标签关联的商品
     * 
     */
    public List<Commodity> getCommoTagNotRelateCommodityList(CommodityTagVo tagVo)throws Exception;

    
    /**
     * 根据商品标签查找商品信息
     */
    public List<Commodity> getCommodityListByTag(CommodityTagVo tagVo) throws Exception;
    /**
     * 根据条件搜索相对应的商品标签数据
     * @param body
     */
	public List<CommodityTag> getCommoTagList(CommodityTagVo tagVo)throws Exception ;
    
    
	
	
}
