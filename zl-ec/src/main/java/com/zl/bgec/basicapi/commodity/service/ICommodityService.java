package com.zl.bgec.basicapi.commodity.service;

import java.util.List;
import java.util.Map;

import com.zl.bgec.basicapi.basecomponent.page.PageFinder;
import com.zl.bgec.basicapi.commodity.po.Commodity;
import com.zl.bgec.basicapi.commodity.po.CommodityComment;
import com.zl.bgec.basicapi.commodity.po.CommodityDetailPic;
import com.zl.bgec.basicapi.commodity.po.CommodityPic;
import com.zl.bgec.basicapi.commodity.vo.CommodityCensorVo;
import com.zl.bgec.basicapi.commodity.vo.CommodityCommentVo;
import com.zl.bgec.basicapi.commodity.vo.CommodityPicVo;
import com.zl.bgec.basicapi.commodity.vo.CommodityPublishVo;
import com.zl.bgec.basicapi.commodity.vo.CommodityVo;


public interface ICommodityService {
	
	/**
    * 保存
    */
	public String saveCommodity(CommodityVo commodityVo) throws Exception;
	/**
	 * 推荐
	 */
	public void recommendCommodity(CommodityVo commodityVo) throws Exception;
	
	/**
	 * 修改商品
	 */
	public void updateCommodity(CommodityVo commodityVo) throws Exception;
	
	/**
     */
    public PageFinder<Map<String,Object>> pagedCommodityList(CommodityVo commodityVo) throws Exception;
    
    /**
     */
    public PageFinder<Commodity> pagedCommodityListSql(CommodityVo commodityVo) throws Exception;
    
    /**
     */
    public List<Commodity> getCommodityList(CommodityVo commodityVo) throws Exception;
    
    /**
     */
    public List<Commodity> getCommodityListSql(CommodityVo commodityVo) throws Exception;
    
    /**
	 * 删除商品（逻辑）
	 */
	public void deleteCommodity(CommodityVo commodityVo) throws Exception;
    
    /**
     * 通过商品编号查找商品对象
     */
    public Commodity getCommodityDetail(String commoNo) throws Exception;
    
    /**
     * 初始化商品审核
     * @param commodityVo
     * @throws Exception
     */
    public void initCommoCensor(CommodityCensorVo censorVo) throws Exception;
    
    /**
     * 商品审核
     * @return 
     */
    public void commoCensor(CommodityCensorVo censorVo) throws Exception;

    /**
     * 商品批量审核
     * @return 
     */
    public void batchCommoCensor(CommodityCensorVo censorVo) throws Exception;

    /**
     * 初始化商品上下架
     * @param commodityVo
     * @throws Exception
     */
    public void initCommodityPublish(CommodityPublishVo publishVo) throws Exception;
    /**
     * 商品上下架
     */
    public void commodityPublish(CommodityVo commodityVo) throws Exception;
    /**
     * 商品批量上下架
     */
    public void batchCommodityPublish(CommodityPublishVo publishVo) throws Exception;

    /**
     * 新增商品评论
     * @param commentVo
     * @throws Exception
     */
    public void createCommdityComment(CommodityCommentVo commentVo)throws Exception;
    
    /**
     * 删除商品评论
     * @param commentVo
     * @throws Exception
     */
    public void deleteCommdityComment(CommodityCommentVo commentVo)throws Exception;
    /**
     * 查询商品评论(分页)
     * @param commentVo
     * @throws Exception
     */
    public PageFinder<CommodityComment> pageCommdityCommentList(CommodityCommentVo commentVo)throws Exception;
    /**
     * 查询商品评论
     * @param commentVo
     * @throws Exception
     */
    public List<CommodityComment> getCommdityCommentList(CommodityCommentVo commentVo)throws Exception;
    /**
     * 批量添加商品图片
     * @param picVos
     * @throws Exception
     */
    public void savePics(List<CommodityPicVo> picVos) throws Exception;
    /**
     * 批量删除商品图片
     * @param picVos
     * @throws Exception
     */
    public void deletePics(CommodityPicVo picVo) throws Exception;
    /**
     * 查询商品图片
     * @param picVo
     * @throws Exception
     */
    public List<CommodityPic> queryPics(CommodityPicVo picVo)throws Exception;
     
    /**
     * 回复评价
     * @throws Exception
     */
    public void replyComment(Map<String,String> map) throws Exception;
    /**
     * 商品置顶
     * @param commodityVo
     * @throws Exception
     */
    public void sortCommodity(CommodityVo commodityVo) throws Exception;
    /**
     * 取消置顶
     * @param commodityVo
     * @throws Exception
     */
    public void cancelSortCommodity(CommodityVo commodityVo) throws Exception;
    
    public void collectCommodity(String prodNo,String memberNo)throws Exception;
    
    public void cancelCollectCommodity(String prodNo,String memberNo)throws Exception;
	public String updateCommoDetailPics(CommodityVo commodityVo)throws Exception;
	public List<CommodityDetailPic> queryCommoDetailPics(String commoNo)throws Exception;
}
