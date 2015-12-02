package com.zl.bgec.basicapi.commodity.rest;

import java.util.List;

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
import com.zl.bgec.basicapi.commodity.po.CommodityProp;
import com.zl.bgec.basicapi.commodity.po.CommodityPropVal;
import com.zl.bgec.basicapi.commodity.service.ICommodityPropService;
import com.zl.bgec.basicapi.commodity.vo.CommodityPropVo;
import com.zl.bgec.basicapi.common.BaseVo;
import com.zl.bgec.basicapi.common.ResultVo;
import com.zl.bgec.basicapi.common.util.GsonUtil;
import com.zl.bgec.basicapi.common.util.RestUtil;

@Component
@Path("/commodity/commo_prop")
public class CommodityPropRest {
	private static Logger log = Logger.getLogger(CommodityPropRest.class);
	
	@Resource
	private ICommodityPropService commodityPropService;
	
	/**
	 * 添加商品属性
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/add")
    @Produces({ MediaType.APPLICATION_JSON })
    public String addProp(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<CommodityPropVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityPropVo>>(){}.getType());
    	String propNo= commodityPropService.saveCommoProp(baseVo.getBody());
    	ResultVo result = new ResultVo(true);
    	result.put("propNo", propNo);
        return result.toString();
    }
	
	/**
	 * 更新商品属性
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/update")
    @Produces({ MediaType.APPLICATION_JSON })
    public String updateProp(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<CommodityPropVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityPropVo>>(){}.getType());
    	commodityPropService.updateCommoProp(baseVo.getBody());
    	ResultVo result = new ResultVo(true);
        return result.toString();
    }
	
	/**
	 * 删除商品属性
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/delete")
    @Produces({ MediaType.APPLICATION_JSON })
    public String deleteProp(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<CommodityPropVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityPropVo>>(){}.getType());
    	commodityPropService.deleteCommoProp(baseVo.getBody().getPropNo());
    	ResultVo result = new ResultVo(true);
        return result.toString();
    }
	
	/**
	 * 查询商品属性（分页）
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/queryPage")
    @Produces({ MediaType.APPLICATION_JSON })
    public String queryPage(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<CommodityPropVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityPropVo>>(){}.getType());
    	PageFinder<CommodityProp> props=  commodityPropService.pagedCommoPropList(baseVo.getBody());
    	ResultVo result = new ResultVo(true,props);
        return result.toString();
    }
	/**
	 * 查询商品属性
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/query")
    @Produces({ MediaType.APPLICATION_JSON })
    public String query(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<CommodityPropVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityPropVo>>(){}.getType());
    	List<CommodityProp> props=  commodityPropService.getCommoPropList(baseVo.getBody());
    	ResultVo result = new ResultVo(true,props);
        return result.toString();
    }
	/**
	 * 删除商品属性和二级分类的关联
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/removeBindCat")
    @Produces({ MediaType.APPLICATION_JSON })
    public String removeBindCat(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<CommodityPropVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityPropVo>>(){}.getType());
    	commodityPropService.deleteCommoCatProp(baseVo.getBody());
    	ResultVo result = new ResultVo(true);
        return result.toString();
    }
	
	/**
	 * 查询商品属性关联的二级分类
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/queryCat")
    @Produces({ MediaType.APPLICATION_JSON })
    public String queryCat(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<CommodityPropVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityPropVo>>(){}.getType());
    	List<CommodityCat> cats= commodityPropService.getBaseCommoCategoryListByPropNo(baseVo.getBody().getPropNo());
    	ResultVo result = new ResultVo(true,cats);
        return result.toStringSkip("commoCatPropGroups");
    }
	
	/**
	 * 查询商品属性关联的商品属性值
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/queryPropVal")
    @Produces({ MediaType.APPLICATION_JSON })
    public String queryPropVal(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<CommodityPropVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityPropVo>>(){}.getType());
    	List<CommodityPropVal> propVals= commodityPropService.getCommoPropValOptionList(baseVo.getBody().getPropNo());
    	ResultVo result = new ResultVo(true,propVals);
        return result.toString();
    }
	
	/**
	 * 删除商品属性值
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/deletePropVal")
    @Produces({ MediaType.APPLICATION_JSON })
    public String deletePropVal(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<CommodityPropVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityPropVo>>(){}.getType());
    	commodityPropService.deleteCommoPropVal(baseVo.getBody().getPropNo(),baseVo.getBody().getPropValueNos());
    	ResultVo result = new ResultVo(true);
        return result.toString();
    }
	
}
