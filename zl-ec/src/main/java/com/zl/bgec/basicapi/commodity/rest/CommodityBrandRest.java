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
import com.zl.bgec.basicapi.commodity.po.CommodityBrand;
import com.zl.bgec.basicapi.commodity.service.ICommodityBrandService;
import com.zl.bgec.basicapi.commodity.vo.CommodityBrandVo;
import com.zl.bgec.basicapi.common.BaseVo;
import com.zl.bgec.basicapi.common.ResultVo;
import com.zl.bgec.basicapi.common.util.GsonUtil;
import com.zl.bgec.basicapi.common.util.RestUtil;


@Component
@Path("/commodity/commo_brand")
public class CommodityBrandRest{
	
	private static Logger log = Logger.getLogger(CommodityBrandRest.class);
	
	@Resource
	private ICommodityBrandService commodityBrandService;
	/**
	 * 增加商品品牌
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/add")
    @Produces({ MediaType.APPLICATION_JSON })
    public String addBrand(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<CommodityBrandVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityBrandVo>>(){}.getType());
    	String brandNo= commodityBrandService.saveBrand(baseVo.getBody());
    	ResultVo result = new ResultVo(true);
    	result.put("brandNo", brandNo);
        return result.toString();
    }
	/**
	 * 更新商品品牌
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/update")
    @Produces({ MediaType.APPLICATION_JSON })
    public String updateBrand(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<CommodityBrandVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityBrandVo>>(){}.getType());
    	commodityBrandService.updateBrand(baseVo.getBody());
    	ResultVo result = new ResultVo(true);
        return result.toString();
    }
	/**
	 * 删除商品品牌
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/delete")
    @Produces({ MediaType.APPLICATION_JSON })
    public String deleteBrand(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<CommodityBrandVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityBrandVo>>(){}.getType());
    	commodityBrandService.deleteBrand(baseVo.getBody());
    	ResultVo result = new ResultVo(true);
        return result.toString();
    }
	/**
	 * 分页查询
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/queryPage")
    @Produces({ MediaType.APPLICATION_JSON })
    public String queryPageBrand(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<CommodityBrandVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityBrandVo>>(){}.getType());
    	PageFinder<CommodityBrand> brands= commodityBrandService.pagedBrandList(baseVo.getBody());
    	ResultVo result = new ResultVo(true,brands);
        return result.toString();
    }
	/**
	 * 查询
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/query")
    @Produces({ MediaType.APPLICATION_JSON })
    public String queryBrand(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<CommodityBrandVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityBrandVo>>(){}.getType());
    	List<CommodityBrand> brands= commodityBrandService.getBrandList(baseVo.getBody());
    	ResultVo result = new ResultVo(true,brands);
        return result.toString();
    }
	/**
	 * 批量显示品牌
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/display")
    @Produces({ MediaType.APPLICATION_JSON })
    public String display(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	System.out.println(json);
    	BaseVo<CommodityBrandVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityBrandVo>>(){}.getType());
    	commodityBrandService.batchDisplayBrand(baseVo.getBody().getBrandNos());
    	ResultVo result = new ResultVo(true);
        return result.toString();
    }
	
	/**
	 * 批量隐藏品牌
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/hidden")
    @Produces({ MediaType.APPLICATION_JSON })
    public String hidden(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<CommodityBrandVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityBrandVo>>(){}.getType());
    	commodityBrandService.batchHiddenBrand(baseVo.getBody().getBrandNos());
    	ResultVo result = new ResultVo(true);
        return result.toString();
    }
}
