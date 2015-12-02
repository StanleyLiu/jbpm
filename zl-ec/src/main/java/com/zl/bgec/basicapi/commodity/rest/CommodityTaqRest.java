package com.zl.bgec.basicapi.commodity.rest;

import java.util.List;

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
import com.zl.bgec.basicapi.commodity.po.CommodityTag;
import com.zl.bgec.basicapi.commodity.service.ICommodityTagService;
import com.zl.bgec.basicapi.commodity.vo.CommodityBrandVo;
import com.zl.bgec.basicapi.commodity.vo.CommodityTagVo;
import com.zl.bgec.basicapi.common.BaseVo;
import com.zl.bgec.basicapi.common.ResultVo;
import com.zl.bgec.basicapi.common.util.GsonUtil;
import com.zl.bgec.basicapi.common.util.ParamUtil;
import com.zl.bgec.basicapi.common.util.RestUtil;

@Component
@Path("/commodity/commo_tag")
public class CommodityTaqRest {
	
	private static Logger log = Logger.getLogger(CommodityTaqRest.class);
	
	@Resource
	private ICommodityTagService commodityTagService;
	/**
	 * 添加商品标签
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/add")
    @Produces({ MediaType.APPLICATION_JSON })
    public String addTag(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<CommodityTagVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityTagVo>>(){}.getType());
    	String tagNo= commodityTagService.saveCommoTag(baseVo.getBody());
    	ResultVo result = new ResultVo(true);
    	result.put("tagNo", tagNo);
        return result.toString();
    }
	/**
	 * 更新商品标签
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/update")
    @Produces({ MediaType.APPLICATION_JSON })
    public String updateTag(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<CommodityTagVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityTagVo>>(){}.getType());
    	commodityTagService.updateCommoTag(baseVo.getBody());
    	ResultVo result = new ResultVo(true);
        return result.toString();
    }
	/**
	 * 删除商品标签(物理)
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/delete")
    @Produces({ MediaType.APPLICATION_JSON })
    public String deleteTag(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<CommodityTagVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityTagVo>>(){}.getType());
    	commodityTagService.deleteCommoTag(baseVo.getBody());
    	ResultVo result = new ResultVo(true);
        return result.toString();
    }
	
	/**
	 * 查询商品标签
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/query")
    @Produces({ MediaType.APPLICATION_JSON })
    public String queryTag(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<CommodityTagVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityTagVo>>(){}.getType());
    	List<CommodityTag> tags= commodityTagService.getCommoTagList(baseVo.getBody());
    	ResultVo result = new ResultVo(true,tags);
        return result.toString();
    }
	/**
	 * 分页查询商品标签
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/queryPage")
    @Produces({ MediaType.APPLICATION_JSON })
    public String queryPageTag(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	System.out.println(json);
    	BaseVo<CommodityTagVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityTagVo>>(){}.getType());
    	System.out.println(baseVo.getBody());
    	PageFinder<CommodityTag> tags= commodityTagService.pagedCommoTagList(baseVo.getBody());
    	ResultVo result = new ResultVo(true,tags);
        return result.toString();
    }
	/**
	 * 商品标签绑定商品
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/bindCommo")
    @Produces({ MediaType.APPLICATION_JSON })
    public String bindCommo(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<CommodityTagVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityTagVo>>(){}.getType());
    	commodityTagService.updateCommoTagCommo(baseVo.getBody());
    	ResultVo result = new ResultVo(true);
        return result.toString();
    }
	/**
	 * 查询和标签关联的商品（支持批量）
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/queryPageCommo")
    @Produces({ MediaType.APPLICATION_JSON })
    public String queryPageCommo(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<CommodityTagVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityTagVo>>(){}.getType());
    	PageFinder<Commodity> commos= commodityTagService.pagedCommoTagRelateCommodityList(baseVo.getBody());
    	ResultVo result = new ResultVo(true,commos);
    	if(baseVo.getBody().getReturnInfo()==0){
    		return result.toStringNoSkip(ParamUtil.getFilter("commodity", ParamUtil.COMMODITY_NO_SKIP));
    	}else{
    		String[] strs = (String[]) ArrayUtils.addAll(ParamUtil.COMMODITY_NO_SKIP, ParamUtil.COMMODITY_BIG_INFO);
    		return result.toStringNoSkip(ParamUtil.getFilter("commodity", strs));
    	}
    }
	/**
	 * 查询和标签无关联的商品
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/queryPageCommoNoRelate")
    @Produces({ MediaType.APPLICATION_JSON })
    public String queryPageCommoNoRelate(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<CommodityTagVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityTagVo>>(){}.getType());
    	PageFinder<Commodity> commos= commodityTagService.pagedCommoTagNotRelateCommodityList(baseVo.getBody());
    	ResultVo result = new ResultVo(true,commos);
    	if(baseVo.getBody().getReturnInfo()==0){
    		return result.toStringNoSkip(ParamUtil.getFilter("commodity", ParamUtil.COMMODITY_NO_SKIP));
    	}else{
    		String[] strs = (String[]) ArrayUtils.addAll(ParamUtil.COMMODITY_NO_SKIP, ParamUtil.COMMODITY_BIG_INFO);
    		return result.toStringNoSkip(ParamUtil.getFilter("commodity", strs));
    	}
    }
	/**
	 * 查询和标签关联的商品（支持批量）
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/queryCommo")
    @Produces({ MediaType.APPLICATION_JSON })
    public String queryCommo(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<CommodityTagVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityTagVo>>(){}.getType());
    	List<Commodity> commos= commodityTagService.getCommoTagRelateCommodityList(baseVo.getBody());
    	ResultVo result = new ResultVo(true,commos);
    	if(baseVo.getBody().getReturnInfo()==0){
    		return result.toStringNoSkip(ParamUtil.getFilter("commodity", ParamUtil.COMMODITY_NO_SKIP));
    	}else{
    		String[] strs = (String[]) ArrayUtils.addAll(ParamUtil.COMMODITY_NO_SKIP, ParamUtil.COMMODITY_BIG_INFO);
    		return result.toStringNoSkip(ParamUtil.getFilter("commodity", strs));
    	}
    }
	/**
	 * 查询和标签无关联的商品
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/queryCommoNoRelate")
    @Produces({ MediaType.APPLICATION_JSON })
    public String queryCommoNoRelate(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<CommodityTagVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityTagVo>>(){}.getType());
    	List<Commodity> commos= commodityTagService.getCommoTagNotRelateCommodityList(baseVo.getBody());
    	ResultVo result = new ResultVo(true,commos);
    	if(baseVo.getBody().getReturnInfo()==0){
    		return result.toStringNoSkip(ParamUtil.getFilter("commodity", ParamUtil.COMMODITY_NO_SKIP));
    	}else{
    		String[] strs = (String[]) ArrayUtils.addAll(ParamUtil.COMMODITY_NO_SKIP, ParamUtil.COMMODITY_BIG_INFO);
    		return result.toStringNoSkip(ParamUtil.getFilter("commodity", strs));
    	}
    }
	/**
	 * 取消商品标签和商品的关联
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/removeBind")
    @Produces({ MediaType.APPLICATION_JSON })
    public String removeBind(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<CommodityTagVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityTagVo>>(){}.getType());
    	commodityTagService.deleteCommoTagCommo(baseVo.getBody().getTagNo(), baseVo.getBody().getCommodityNos());
    	ResultVo result = new ResultVo(true);
        return result.toString();
    }
	
	
}
