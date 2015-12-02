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
import com.zl.bgec.basicapi.commodity.po.CommodityPropGroup;
import com.zl.bgec.basicapi.commodity.service.ICommodityPropGroupService;
import com.zl.bgec.basicapi.commodity.vo.CommodityPropGroupVo;
import com.zl.bgec.basicapi.common.BaseVo;
import com.zl.bgec.basicapi.common.ResultVo;
import com.zl.bgec.basicapi.common.util.GsonUtil;
import com.zl.bgec.basicapi.common.util.RestUtil;

@Component
@Path("/commodity/commo_prop_group")
public class CommodityPropGroupRest {
	private static Logger log = Logger.getLogger(CommodityPropGroupRest.class);
	
	@Resource
	private ICommodityPropGroupService commodityPropGroupService;
	
	/**
	 * 添加商品属性组
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/add")
    @Produces({ MediaType.APPLICATION_JSON })
    public String addPropGroup(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<CommodityPropGroupVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityPropGroupVo>>(){}.getType());
    	String propGroupNo= commodityPropGroupService.saveCommoPropGroup(baseVo.getBody());
    	ResultVo result = new ResultVo(true);
    	result.put("propGroupNo", propGroupNo);
        return result.toString();
    }
	
	/**
	 * 更新商品属性组
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/update")
    @Produces({ MediaType.APPLICATION_JSON })
    public String update(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<CommodityPropGroupVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityPropGroupVo>>(){}.getType());
    	commodityPropGroupService.updateCommoPropGroup(baseVo.getBody());
    	ResultVo result = new ResultVo(true);
        return result.toString();
    }
	
	/**
	 * 删除商品属性组
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/delete")
    @Produces({ MediaType.APPLICATION_JSON })
    public String delete(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<CommodityPropGroupVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityPropGroupVo>>(){}.getType());
    	commodityPropGroupService.deleteCommoPropGroup(baseVo.getBody().getGroupNo());
    	ResultVo result = new ResultVo(true);
        return result.toString();
    }
	
	/**
	 * 查询商品属性组
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
    	BaseVo<CommodityPropGroupVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityPropGroupVo>>(){}.getType());
    	List<CommodityPropGroup> groups= commodityPropGroupService.getCommoPropGroupList(baseVo.getBody());
    	ResultVo result = new ResultVo(true,groups);
        return result.toStringSkip("commoPropGroupProps");
    }
	
	/**
	 * 查询商品属性组(分页)
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
    	BaseVo<CommodityPropGroupVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityPropGroupVo>>(){}.getType());
    	PageFinder<CommodityPropGroup> groups= commodityPropGroupService.pagedCommoPropGroupList(baseVo.getBody());
    	ResultVo result = new ResultVo(true,groups);
        return result.toStringSkip("commoPropGroupProps");
    }
	
	/**
	 * 更新商品属性组和二级分类的关联
	 * @param json
	 * @return
	 * @throws Exception
	
	@POST
    @Path("/updateBindCat")
    @Produces({ MediaType.APPLICATION_JSON })
    public String updateBindCat(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<CommodityPropGroupVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityPropGroupVo>>(){}.getType());
    	commodityPropGroupService.updateCommoCatPropGroup(baseVo.getBody().getCatNos(),baseVo.getBody().getGroupNo());
    	ResultVo result = new ResultVo(true);
        return result.toString();
    } */
	
	/**
	 * 保存商品属性组和二级分类的关联
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/bindCat")
    @Produces({ MediaType.APPLICATION_JSON })
    public String bindCat(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<CommodityPropGroupVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityPropGroupVo>>(){}.getType());
    	commodityPropGroupService.saveCommoCatPropGroup(baseVo.getBody());
    	ResultVo result = new ResultVo(true);
        return result.toString();
    }
	
	/**
	 * 保存商品属性组和属性的关联
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/bindProp")
    @Produces({ MediaType.APPLICATION_JSON })
    public String bindProp(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<CommodityPropGroupVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityPropGroupVo>>(){}.getType());
    	commodityPropGroupService.saveCommoPropGroupProp(baseVo.getBody());
    	ResultVo result = new ResultVo(true);
        return result.toString();
    }
	
	/**
	 * 更新商品属性组和属性的关联
	 * @param json
	 * @return
	 * @throws Exception
	 
	@POST
    @Path("/updateBindProp")
    @Produces({ MediaType.APPLICATION_JSON })
    public String updateBindProp(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<CommodityPropGroupVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityPropGroupVo>>(){}.getType());
    	commodityPropGroupService.updateCommoPropGroupProp(baseVo.getBody().getGroupNo(), baseVo.getBody().getPropNos());
    	ResultVo result = new ResultVo(true);
        return result.toString();
    }*/
	
	/**
	 * 查询商品属性组绑定的二级分类
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/queryCatByGroupNo")
    @Produces({ MediaType.APPLICATION_JSON })
    public String queryCatByGroupNo(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<CommodityPropGroupVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityPropGroupVo>>(){}.getType());
    	List<CommodityCat> cats= commodityPropGroupService.getCommoCategoryListByGroupNo(baseVo.getBody().getGroupNo());
    	ResultVo result = new ResultVo(true,cats);
        return result.toStringSkip("commoCatPropGroups");
    }
}
