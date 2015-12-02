package com.zl.bgec.basicapi.commodity.rest;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.google.gson.reflect.TypeToken;
import com.zl.bgec.basicapi.basecomponent.page.PageFinder;
import com.zl.bgec.basicapi.commodity.po.CommodityCat;
import com.zl.bgec.basicapi.commodity.po.CommodityCatGroupProp;
import com.zl.bgec.basicapi.commodity.po.CommodityProp;
import com.zl.bgec.basicapi.commodity.service.ICommodityCatService;
import com.zl.bgec.basicapi.commodity.vo.CommodityCatVo;
import com.zl.bgec.basicapi.commodity.vo.CommodityPropGroupVo;
import com.zl.bgec.basicapi.common.BaseVo;
import com.zl.bgec.basicapi.common.ResultVo;
import com.zl.bgec.basicapi.common.util.GsonUtil;
import com.zl.bgec.basicapi.common.util.RestUtil;

@Component
@Path("/commodity/commo_cat")
public class CommodityCatRest {
	
	private static Logger log = Logger.getLogger(CommodityCatRest.class);
	
	@Resource
	private ICommodityCatService commodityCatService;
	
	/**
	 * 增加商品分类
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/add")
    @Produces({ MediaType.APPLICATION_JSON })
    public String addCat(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<CommodityCatVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityCatVo>>(){}.getType());
    	String catNo= commodityCatService.saveCommodityCat(baseVo.getBody());
    	ResultVo result = new ResultVo(true);
    	result.put("catNo", catNo);
        return result.toString();
    }
	
	/**
	 * 增加商品分类
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/update")
    @Produces({ MediaType.APPLICATION_JSON })
    public String updateCat(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<CommodityCatVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityCatVo>>(){}.getType());
    	commodityCatService.eidtCommodityCat(baseVo.getBody());
    	ResultVo result = new ResultVo(true);
        return result.toString();
    }
	
	/**
	 * 删除商品分类
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/delete")
    @Produces({ MediaType.APPLICATION_JSON })
    public String deleteCat(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<CommodityCatVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityCatVo>>(){}.getType());
    	commodityCatService.deleteCommodityCat(baseVo.getBody().getCatNo());
    	ResultVo result = new ResultVo(true);
        return result.toString();
    }
	
	
	/**
	 * 查询商品分类
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/query")
    @Produces({ MediaType.APPLICATION_JSON })
    public String queryCat(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<CommodityCatVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityCatVo>>(){}.getType());
    	List<CommodityCat> cats= commodityCatService.getCommodityCatList(baseVo.getBody());
    	ResultVo result = new ResultVo(true,cats);
        return result.toStringSkip("commoCatPropGroups");
    }
	@POST
	@Path("/queryCats")
	@Produces({ MediaType.APPLICATION_JSON })
	public String queryCatByShoId(String json)throws Exception
	{
		json = RestUtil.strDecode(json);
		BaseVo<Map<String,String>> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<Map<String,String>>>(){}.getType());
		List<Map<String, String>> cats= commodityCatService.getCommodityCatList(baseVo.getBody());
		ResultVo result = new ResultVo(true,cats);
		return result.toString();
	}
	
	/**
	 * 查询商品分类（分页）
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/queryPage")
    @Produces({ MediaType.APPLICATION_JSON })
    public String queryPageCat(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<CommodityCatVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityCatVo>>(){}.getType());
    	PageFinder<CommodityCat> cats= commodityCatService.queryCommodityCatList(baseVo.getBody());
    	ResultVo result = new ResultVo(true,cats);
        return result.toStringSkip("commoCatPropGroups");
    }
	
	/**
	 * 查询商品分类下的属性组
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/queryPropGroup")
    @Produces({ MediaType.APPLICATION_JSON })
    public String queryPropGroup(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<CommodityCatVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityCatVo>>(){}.getType());
    	List<CommodityPropGroupVo> groupVos = commodityCatService.commonCatRealateProp(baseVo.getBody().getCatNo());
    	ResultVo result = new ResultVo(true,groupVos);
        return result.toString();
    }
	
	/**
	 * 建立商品分类和属性组和属性的关联
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/bindCatGroupProp")
    @Produces({ MediaType.APPLICATION_JSON })
    public String bindCatGroupProp(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<CommodityCatVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityCatVo>>(){}.getType());
    	commodityCatService.saveCommoCatGroupProp(baseVo.getBody());
    	ResultVo result = new ResultVo(true);
        return result.toString();
    }
	
	/**
	 * 查询商品分类和属性组和属性的关联
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/queryCatGroupProp")
    @Produces({ MediaType.APPLICATION_JSON })
    public String queryCatGroupProp(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<CommodityCatVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityCatVo>>(){}.getType());
    	List<CommodityCatGroupProp> catGroupPropList= commodityCatService.getCommoCatGroupPropList(baseVo.getBody().getCatNo());
    	ResultVo result = new ResultVo(true,catGroupPropList);
        return result.toStringSkip("prop");
    }
	
	/**
	 * 查询商品分类下的属性（根据分类，属性组，属性关联表查询）（用于添加商品）
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/queryCatProp")
    @Produces({ MediaType.APPLICATION_JSON })
    public String queryCatProp(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<CommodityCatVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityCatVo>>(){}.getType());
    	List<CommodityProp> propList= commodityCatService.getCommoCatPropList(baseVo.getBody());
    	ResultVo result = new ResultVo(true,propList);
        return result.toString();
    }
}
