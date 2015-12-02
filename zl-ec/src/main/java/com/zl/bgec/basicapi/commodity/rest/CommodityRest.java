package com.zl.bgec.basicapi.commodity.rest;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.google.gson.reflect.TypeToken;
import com.zl.bgec.basicapi.basecomponent.page.PageFinder;
import com.zl.bgec.basicapi.commodity.po.Commodity;
import com.zl.bgec.basicapi.commodity.po.CommodityComment;
import com.zl.bgec.basicapi.commodity.po.CommodityDetailPic;
import com.zl.bgec.basicapi.commodity.po.CommodityPic;
import com.zl.bgec.basicapi.commodity.service.ICommodityService;
import com.zl.bgec.basicapi.commodity.vo.CommodityCensorVo;
import com.zl.bgec.basicapi.commodity.vo.CommodityCommentVo;
import com.zl.bgec.basicapi.commodity.vo.CommodityInfoVo;
import com.zl.bgec.basicapi.commodity.vo.CommodityPicVo;
import com.zl.bgec.basicapi.commodity.vo.CommodityPublishVo;
import com.zl.bgec.basicapi.commodity.vo.CommodityVo;
import com.zl.bgec.basicapi.common.BaseVo;
import com.zl.bgec.basicapi.common.ResultVo;
import com.zl.bgec.basicapi.common.util.GsonUtil;
import com.zl.bgec.basicapi.common.util.ParamUtil;
import com.zl.bgec.basicapi.common.util.RestUtil;

@Component
@Path("/commodity/commodity")
public class CommodityRest {
	private static Logger log = Logger.getLogger(CommodityRest.class);
	
	@Resource
	private ICommodityService commodityService;
	

	/**
	 * 添加商品
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/add")
    @Produces({ MediaType.APPLICATION_JSON })
    public String addCommodity(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<CommodityVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityVo>>(){}.getType());
    	CommodityVo commodityVo = baseVo.getBody();
    	String commodityNo= commodityService.saveCommodity(commodityVo);
    	ResultVo result = new ResultVo(true);
    	result.put("commodityNo", commodityNo);
        return result.toString();
    }
	
	@POST
    @Path("/updateCommoDetailPics")
    @Produces({ MediaType.APPLICATION_JSON })
    public String updateCommoDetailPics(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<CommodityVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityVo>>(){}.getType());
    	CommodityVo commodityVo = baseVo.getBody();
    	String commodityNo= commodityService.updateCommoDetailPics(commodityVo);
    	ResultVo result = new ResultVo(true);
    	result.put("commodityNo", commodityNo);
        return result.toString();
    }
	
	@POST
    @Path("/queryCommoDetailPics")
    @Produces({ MediaType.APPLICATION_JSON })
    public String queryCommoDetailPics(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<CommodityVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityVo>>(){}.getType());
    	CommodityVo commodityVo = baseVo.getBody();
    	List<CommodityDetailPic> pics= commodityService.queryCommoDetailPics(commodityVo.getCommoNo());
    	ResultVo result = new ResultVo(true,pics);
        return result.toString();
    }
	
	/**
	 * 修改商品
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/update")
    @Produces({ MediaType.APPLICATION_JSON })
    public String updateCommodity(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<CommodityVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityVo>>(){}.getType());
    	commodityService.updateCommodity(baseVo.getBody());
    	ResultVo result = new ResultVo(true);
        return result.toString();
    }
	/**
	 * 删除商品
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/delete")
    @Produces({ MediaType.APPLICATION_JSON })
    public String deleteCommodity(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<CommodityVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityVo>>(){}.getType());
    	commodityService.deleteCommodity(baseVo.getBody());
    	ResultVo result = new ResultVo(true);
        return result.toString();
    }
	
	/**
	 * 查询商品
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/query")
    @Produces({ MediaType.APPLICATION_JSON })
    public String queryCommodity(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<CommodityVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityVo>>(){}.getType());
    	List<Commodity> commoditys= commodityService.getCommodityList(baseVo.getBody());
    	ResultVo result = new ResultVo(true,commoditys);
//    	if(baseVo.getBody().getReturnInfo()==0){
//    		return result.toStringNoSkip(ParamUtil.getFilter("commodity", ParamUtil.COMMODITY_NO_SKIP));
//    	}else{
    		String[] strs = (String[]) ArrayUtils.addAll(ParamUtil.COMMODITY_NO_SKIP, ParamUtil.COMMODITY_BIG_INFO);
    		return result.toStringNoSkip(ParamUtil.getFilter("commodity", strs));
//    	}
    }
	
	/**
	 * 查询商品(分页)
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/queryPage")
    @Produces({ MediaType.APPLICATION_JSON })
    public String queryPageCommodity(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<CommodityVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityVo>>(){}.getType());
    	PageFinder<Map<String,Object>> commoditys= commodityService.pagedCommodityList(baseVo.getBody());
    	ResultVo result = new ResultVo(true,commoditys);
    		String[] strs = (String[]) ArrayUtils.addAll(ParamUtil.COMMODITY_NO_SKIP, ParamUtil.COMMODITY_BIG_INFO);
    		return result.toStringNoSkip(ParamUtil.getFilter("commodity", strs));
    }
	/**
	 * 商品置顶
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("/sort")
	@Produces({ MediaType.APPLICATION_JSON })
	public String commoditySort(String json)throws Exception
	{
		json = RestUtil.strDecode(json);
		BaseVo<CommodityVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityVo>>(){}.getType());
		commodityService.sortCommodity(baseVo.getBody());
		ResultVo result = new ResultVo(true);
		return result.toString();
	}
	/**
	 * 取消商品置顶
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("/cancelsort")
	@Produces({ MediaType.APPLICATION_JSON })
	public String cancelCommoditySort(String json)throws Exception
	{
		json = RestUtil.strDecode(json);
		BaseVo<CommodityVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityVo>>(){}.getType());
		commodityService.cancelSortCommodity(baseVo.getBody());
		ResultVo result = new ResultVo(true);
		return result.toString();
	}
	
	/**
	 * 商品批量审核
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/batchCensor")
    @Produces({ MediaType.APPLICATION_JSON })
    public String batchCensor(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<CommodityCensorVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityCensorVo>>(){}.getType());
    	commodityService.batchCommoCensor(baseVo.getBody());
    	ResultVo result = new ResultVo(true);
        return result.toString();
    }
	
	/**
	 * 初始商品上下架
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/initPublish")
    @Produces({ MediaType.APPLICATION_JSON })
    public String initPublish(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<CommodityPublishVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityPublishVo>>(){}.getType());
    	commodityService.initCommodityPublish(baseVo.getBody());
    	ResultVo result = new ResultVo(true);
        return result.toString();
    }
	
	/**
	 * 商品上下架
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/publish")
    @Produces({ MediaType.APPLICATION_JSON })
    public String publish(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<CommodityVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityVo>>(){}.getType());
    	commodityService.commodityPublish(baseVo.getBody());
    	ResultVo result = new ResultVo(true);
        return result.toString();
    }
	/**
	 * 商品推荐
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("/recommend")
	@Produces({ MediaType.APPLICATION_JSON })
	public String recommend(String json)throws Exception
	{
		json = RestUtil.strDecode(json);
		BaseVo<CommodityVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityVo>>(){}.getType());
		commodityService.recommendCommodity(baseVo.getBody());
		ResultVo result = new ResultVo(true);
		return result.toString();
	}
	
	/**
	 * 商品批量上下架
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/batchPublish")
    @Produces({ MediaType.APPLICATION_JSON })
    public String batchPublish(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<CommodityVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityVo>>(){}.getType());
    	commodityService.commodityPublish(baseVo.getBody());
    	ResultVo result = new ResultVo(true);
        return result.toString();
    }
	
	/**
	 * 新增商品评论
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/addComment")
    @Produces({ MediaType.APPLICATION_JSON })
    public String addComment(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<CommodityCommentVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityCommentVo>>(){}.getType());
    	commodityService.createCommdityComment(baseVo.getBody());
    	ResultVo result = new ResultVo(true);
        return result.toString();
    }
	
	/**
	 * 删除商品评论
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/deleteComment")
    @Produces({ MediaType.APPLICATION_JSON })
    public String deleteComment(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<CommodityCommentVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityCommentVo>>(){}.getType());
    	commodityService.deleteCommdityComment(baseVo.getBody());
    	ResultVo result = new ResultVo(true);
        return result.toString();
    }
	
	/**
	 *查询商品评论
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/queryComment")
    @Produces({ MediaType.APPLICATION_JSON })
    public String queryComment(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<CommodityCommentVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityCommentVo>>(){}.getType());
    	List<CommodityComment> comments= commodityService.getCommdityCommentList(baseVo.getBody());
    	ResultVo result = new ResultVo(true,comments);
        return result.toString();
    }
	
	/**
	 *查询商品评论(分页)
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/queryPageComment")
    @Produces({ MediaType.APPLICATION_JSON })
    public String queryPageComment(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<CommodityCommentVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityCommentVo>>(){}.getType());
    	PageFinder<CommodityComment> comments= commodityService.pageCommdityCommentList(baseVo.getBody());
    	ResultVo result = new ResultVo(true,comments);
        return result.toString();
    }
	
	/**
	 *保存商品图片
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/savePics")
    @Produces({ MediaType.APPLICATION_JSON })
    public String savePics(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<List<CommodityPicVo>> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<List<CommodityPicVo>>>(){}.getType());
    	commodityService.savePics(baseVo.getBody());
    	ResultVo result = new ResultVo(true);
        return result.toString();
    }
	
	/**
	 * 删除商品图片
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/deletePics")
    @Produces({ MediaType.APPLICATION_JSON })
    public String deletePics(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<CommodityPicVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityPicVo>>(){}.getType());
    	commodityService.deletePics(baseVo.getBody());
    	ResultVo result = new ResultVo(true);
        return result.toString();
    }
	
	/**
	 * 查询商品图片
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/queryPics")
    @Produces({ MediaType.APPLICATION_JSON })
    public String queryPics(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<CommodityPicVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityPicVo>>(){}.getType());
    	List<CommodityPic> pics= commodityService.queryPics(baseVo.getBody());
    	ResultVo result = new ResultVo(true,pics);
        return result.toString();
    }
	
	/**
	 * 查询商品详细信息
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/queryCommodityInfo")
    @Produces({ MediaType.APPLICATION_JSON })
    public String queryCommodityInfo(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<CommodityInfoVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityInfoVo>>(){}.getType());
    	Commodity commodity=commodityService.getCommodityDetail(baseVo.getBody().getCommoNo());
    	ResultVo result = new ResultVo(true,commodity);
        return result.toString();
    }
	/**
	 * 查询商品详细信息
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("/replyComment")
	@Produces({ MediaType.APPLICATION_JSON })
	public String replayComment(String json)
	{
		json = RestUtil.strDecode(json);
		BaseVo<Map<String,String>> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<Map<String,String>>>(){}.getType());
		try {
			log.info("回复商品评价成功。");
			commodityService.replyComment(baseVo.getBody());
			ResultVo result = new ResultVo(true);
			return result.toString();
		} catch (Exception e) {
			log.error("回复商品评价失败："+e.getMessage(),e);
			ResultVo result = new ResultVo(false);
			return result.toString();
		}
	}
}
