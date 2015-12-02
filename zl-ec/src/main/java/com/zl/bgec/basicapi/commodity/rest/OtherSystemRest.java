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
import com.zl.bgec.basicapi.commodity.service.IOtherSystemService;
import com.zl.bgec.basicapi.commodity.vo.CommodityShopVo;
import com.zl.bgec.basicapi.commodity.vo.CommodityUserVo;
import com.zl.bgec.basicapi.common.BaseVo;
import com.zl.bgec.basicapi.common.ResultVo;
import com.zl.bgec.basicapi.common.util.GsonUtil;
import com.zl.bgec.basicapi.common.util.ParamUtil;
import com.zl.bgec.basicapi.common.util.RestUtil;

@Component
@Path("/commodity/other")
public class OtherSystemRest {
private static Logger log = Logger.getLogger(OtherSystemRest.class);
	
	@Resource
	private IOtherSystemService otherSystemService;
	
	/**
	 * 增加用户关注商品
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/addCommodityUser")
    @Produces({ MediaType.APPLICATION_JSON })
    public String addCommodityUser(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<CommodityUserVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityUserVo>>(){}.getType());
    	otherSystemService.saveCommodityUser(baseVo.getBody());
    	ResultVo result = new ResultVo(true);
        return result.toString();
    }
	
	/**
	 * 取消用户关注商品
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/deleteCommodityUser")
    @Produces({ MediaType.APPLICATION_JSON })
    public String deleteCommodityUser(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<CommodityUserVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityUserVo>>(){}.getType());
    	otherSystemService.deleteCommodityUser(baseVo.getBody());
    	ResultVo result = new ResultVo(true);
        return result.toString();
    }
	
	/**
	 * 查询用户关联商品
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/queryCommodityUser")
    @Produces({ MediaType.APPLICATION_JSON })
    public String queryCommodityUser(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<CommodityUserVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityUserVo>>(){}.getType());
    	List<Commodity> commoditys = otherSystemService.getCommodityUserList(baseVo.getBody());
    	ResultVo result = new ResultVo(true,commoditys);
    	if(baseVo.getBody().getReturnInfo()==0){
    		return result.toStringNoSkip(ParamUtil.getFilter("commodity", ParamUtil.COMMODITY_NO_SKIP));
    	}else{
    		String[] strs = (String[]) ArrayUtils.addAll(ParamUtil.COMMODITY_NO_SKIP, ParamUtil.COMMODITY_BIG_INFO);
    		return result.toStringNoSkip(ParamUtil.getFilter("commodity", strs));
    	}
    }
	
	/**
	 * 查询用户关联商品(分页)
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/queryPageCommodityUser")
    @Produces({ MediaType.APPLICATION_JSON })
    public String queryPageCommodityUser(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<CommodityUserVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityUserVo>>(){}.getType());
    	PageFinder<Commodity> commoditys = otherSystemService.pagedCommodityUserList(baseVo.getBody());
    	ResultVo result = new ResultVo(true,commoditys);
    	if(baseVo.getBody().getReturnInfo()==0){
    		return result.toStringNoSkip(ParamUtil.getFilter("commodity", ParamUtil.COMMODITY_NO_SKIP));
    	}else{
    		String[] strs = (String[]) ArrayUtils.addAll(ParamUtil.COMMODITY_NO_SKIP, ParamUtil.COMMODITY_BIG_INFO);
    		return result.toStringNoSkip(ParamUtil.getFilter("commodity", strs));
    	}
    }
	
	/**
	 * 增加商铺绑定商品
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/addCommodityShop")
    @Produces({ MediaType.APPLICATION_JSON })
    public String addCommodityShop(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<CommodityShopVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityShopVo>>(){}.getType());
    	otherSystemService.saveCommodityShop(baseVo.getBody());
    	ResultVo result = new ResultVo(true);
        return result.toString();
    }
	
	/**
	 * 取消商铺关注商品
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/deleteCommodityShop")
    @Produces({ MediaType.APPLICATION_JSON })
    public String deleteCommodityShop(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<CommodityShopVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityShopVo>>(){}.getType());
    	otherSystemService.deleteCommodityShop(baseVo.getBody());
    	ResultVo result = new ResultVo(true);
        return result.toString();
    }
	
	/**
	 * 查询商铺关联商品
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/queryCommodityShop")
    @Produces({ MediaType.APPLICATION_JSON })
    public String queryCommodityShop(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<CommodityShopVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityShopVo>>(){}.getType());
    	List<Commodity> commoditys = otherSystemService.getCommodityShopList(baseVo.getBody());
    	ResultVo result = new ResultVo(true,commoditys);
    	if(baseVo.getBody().getReturnInfo()==0){
    		return result.toStringNoSkip(ParamUtil.getFilter("commodity", ParamUtil.COMMODITY_NO_SKIP));
    	}else{
    		String[] strs = (String[]) ArrayUtils.addAll(ParamUtil.COMMODITY_NO_SKIP, ParamUtil.COMMODITY_BIG_INFO);
    		return result.toStringNoSkip(ParamUtil.getFilter("commodity", strs));
    	}
    }
	
	/**
	 * 查询商铺关联商品(分页)
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/queryPageCommodityShop")
    @Produces({ MediaType.APPLICATION_JSON })
    public String queryPageCommodityShop(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<CommodityShopVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityShopVo>>(){}.getType());
    	PageFinder<Commodity> commoditys = otherSystemService.pagedCommodityShopList(baseVo.getBody());
    	ResultVo result = new ResultVo(true,commoditys);
    	if(baseVo.getBody().getReturnInfo()==0){
    		return result.toStringNoSkip(ParamUtil.getFilter("commodity", ParamUtil.COMMODITY_NO_SKIP));
    	}else{
    		String[] strs = (String[]) ArrayUtils.addAll(ParamUtil.COMMODITY_NO_SKIP, ParamUtil.COMMODITY_BIG_INFO);
    		return result.toStringNoSkip(ParamUtil.getFilter("commodity", strs));
    	}
    }
}
