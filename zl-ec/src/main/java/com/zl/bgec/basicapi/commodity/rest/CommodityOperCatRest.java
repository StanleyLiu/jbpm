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
import com.zl.bgec.basicapi.commodity.po.CommodityOperCat;
import com.zl.bgec.basicapi.commodity.service.ICommodityOperCatService;
import com.zl.bgec.basicapi.commodity.vo.CommodityOperCatVo;
import com.zl.bgec.basicapi.common.BaseVo;
import com.zl.bgec.basicapi.common.ResultVo;
import com.zl.bgec.basicapi.common.util.GsonUtil;
import com.zl.bgec.basicapi.common.util.ParamUtil;
import com.zl.bgec.basicapi.common.util.RestUtil;

@Component
@Path("/commodity/commo_oper_cat")
public class CommodityOperCatRest {
	private static Logger log = Logger.getLogger(CommodityOperCatRest.class);
	
	@Resource
	private ICommodityOperCatService commodityOperCatService;
	
	/**
	 * 添加商品运营分类
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/add")
    @Produces({ MediaType.APPLICATION_JSON })
    public String addOperCat(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<CommodityOperCatVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityOperCatVo>>(){}.getType());
    	String operCatNo= commodityOperCatService.saveCommodityOperCat(baseVo.getBody());
    	ResultVo result = new ResultVo(true);
    	result.put("operCatNo", operCatNo);
        return result.toString();
    }
	/**
	 * 更新商品运营分类
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/update")
    @Produces({ MediaType.APPLICATION_JSON })
    public String updateOperCat(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<CommodityOperCatVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityOperCatVo>>(){}.getType());
    	commodityOperCatService.updateCommodityOper(baseVo.getBody());
    	ResultVo result = new ResultVo(true);
        return result.toString();
    }
	/**
	 * 删除商品运营分类
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/delete")
    @Produces({ MediaType.APPLICATION_JSON })
    public String deleteOperCat(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<CommodityOperCatVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityOperCatVo>>(){}.getType());
    	commodityOperCatService.deleteCommodityOper(baseVo.getBody().getOprtCatNo());
    	ResultVo result = new ResultVo(true);
        return result.toString();
    }
	
	/**
	 * 查询商品运营分类
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/query")
    @Produces({ MediaType.APPLICATION_JSON })
    public String queryOperCat(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<CommodityOperCatVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityOperCatVo>>(){}.getType());
    	List<CommodityOperCat> operCats= commodityOperCatService.getOperatorCats(baseVo.getBody());
    	ResultVo result = new ResultVo(true,operCats);
        return result.toString();
    }
	
	/**
	 * 查询商品运营分类(分页)
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/queryPage")
    @Produces({ MediaType.APPLICATION_JSON })
    public String queryPageOperCat(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<CommodityOperCatVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityOperCatVo>>(){}.getType());
    	PageFinder<CommodityOperCat> operCats= commodityOperCatService.pagedOperatorCats(baseVo.getBody());
    	ResultVo result = new ResultVo(true,operCats);
        return result.toString();
    }
	
	/**
	 * 商品运营分类绑定商品
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
    	BaseVo<CommodityOperCatVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityOperCatVo>>(){}.getType());
    	commodityOperCatService.updateOperatingCatCommo(baseVo.getBody());
    	ResultVo result = new ResultVo(true);
        return result.toString();
    }
	
	/**
	 * 商品运营分类解除绑定商品
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/removeBindCommo")
    @Produces({ MediaType.APPLICATION_JSON })
    public String removeBindCommo(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<CommodityOperCatVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityOperCatVo>>(){}.getType());
    	commodityOperCatService.updateOperatingCatCommo(baseVo.getBody());
    	ResultVo result = new ResultVo(true);
        return result.toString();
    }
	
	/**
	 * 根据商品运营分类查询已关联商品
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/queryPageCommoRelate")
    @Produces({ MediaType.APPLICATION_JSON })
    public String queryPageCommoRelate(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<CommodityOperCatVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityOperCatVo>>(){}.getType());
    	PageFinder<Commodity> commos =  commodityOperCatService.pagedOprtCatRelateCommodityList(baseVo.getBody());
    	ResultVo result = new ResultVo(true,commos);
    	if(baseVo.getBody().getReturnInfo()==0){
    		return result.toStringNoSkip(ParamUtil.getFilter("commodity", ParamUtil.COMMODITY_NO_SKIP));
    	}else{
    		String[] strs = (String[]) ArrayUtils.addAll(ParamUtil.COMMODITY_NO_SKIP, ParamUtil.COMMODITY_BIG_INFO);
    		return result.toStringNoSkip(ParamUtil.getFilter("commodity", strs));
    	}
    }
	
	/**
	 * 根据商品运营分类查询未关联商品
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
    	BaseVo<CommodityOperCatVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityOperCatVo>>(){}.getType());
    	PageFinder<Commodity> commos =  commodityOperCatService.pagedOprtCatNotRelateCommodityList(baseVo.getBody());
    	ResultVo result = new ResultVo(true,commos);
    	if(baseVo.getBody().getReturnInfo()==0){
    		return result.toStringNoSkip(ParamUtil.getFilter("commodity", ParamUtil.COMMODITY_NO_SKIP));
    	}else{
    		String[] strs = (String[]) ArrayUtils.addAll(ParamUtil.COMMODITY_NO_SKIP, ParamUtil.COMMODITY_BIG_INFO);
    		return result.toStringNoSkip(ParamUtil.getFilter("commodity", strs));
    	}
    }
	
	/**
	 * 根据商品查询已关联商品运营分类
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/queryPageOperCatRelate")
    @Produces({ MediaType.APPLICATION_JSON })
    public String queryPageOperCatRelate(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<CommodityOperCatVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityOperCatVo>>(){}.getType());
    	PageFinder<CommodityOperCat> operCats =  commodityOperCatService.getRealateOprtCatByCommono(baseVo.getBody());
    	ResultVo result = new ResultVo(true,operCats);
        return result.toString();
    }
	/**
	 * 根据商品查询未关联商品运营分类
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/queryPageOperCatNoRelate")
    @Produces({ MediaType.APPLICATION_JSON })
    public String queryPageOperCatNoRelate(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<CommodityOperCatVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityOperCatVo>>(){}.getType());
    	PageFinder<CommodityOperCat> operCats =  commodityOperCatService.getNotRealateOprtCatByCommono(baseVo.getBody());
    	ResultVo result = new ResultVo(true,operCats);
        return result.toString();
    }
}
