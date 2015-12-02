package com.zl.bgec.basicapi.commodity.rest;

import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.google.gson.reflect.TypeToken;
import com.zl.bgec.basicapi.basecomponent.page.PageFinder;
import com.zl.bgec.basicapi.commodity.po.Commodity;
import com.zl.bgec.basicapi.commodity.po.Product;
import com.zl.bgec.basicapi.commodity.po.ProductChangePrice;
import com.zl.bgec.basicapi.commodity.po.ProductStock;
import com.zl.bgec.basicapi.commodity.service.IProductService;
import com.zl.bgec.basicapi.commodity.vo.ProductChangePriceVo;
import com.zl.bgec.basicapi.commodity.vo.ProductPropVo;
import com.zl.bgec.basicapi.commodity.vo.ProductStockVo;
import com.zl.bgec.basicapi.commodity.vo.ProductVo;
import com.zl.bgec.basicapi.common.BaseVo;
import com.zl.bgec.basicapi.common.ResultVo;
import com.zl.bgec.basicapi.common.util.GsonUtil;
import com.zl.bgec.basicapi.common.util.ParamUtil;
import com.zl.bgec.basicapi.common.util.RestUtil;

@Component
@Path("/commodity/product")
public class ProductRest {
private static Logger log = Logger.getLogger(ProductRest.class);
	
	@Resource
	private IProductService productService;
	
	/**
	 * 添加货品
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/add")
    @Produces({ MediaType.APPLICATION_JSON })
    public String addProduct(String json,@Context HttpHeaders header)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<ProductVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<ProductVo>>(){}.getType());
    	String productNo= productService.saveProduct(baseVo.getBody());
    	ResultVo result = new ResultVo(true);
    	result.put("productNo", productNo);
        return result.toString();
    }
	
	/**
	 * 保存货品规格属性
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/addProp")
    @Produces({ MediaType.APPLICATION_JSON })
    public String addProp(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<ProductPropVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<ProductPropVo>>(){}.getType());
    	productService.saveProductProp(baseVo.getBody());
    	ResultVo result = new ResultVo(true);
        return result.toString();
    }
	
//	/**
//	 * 批量保存货品规格属性
//	 * @param json
//	 * @return
//	 * @throws Exception
//	 */
//	@POST
//    @Path("/batchAddProp")
//    @Produces({ MediaType.APPLICATION_JSON })
//    public String batchAddProp(String json)throws Exception
//    {
//    	json = RestUtil.strDecode(json);
//    	BaseVo<ProductVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<ProductVo>>(){}.getType());
//    	productService.saveBatchProductProp(baseVo.getBody().getPropVos());
//    	ResultVo result = new ResultVo(true);
//        return result.toString();
//    }
//	
	/**
	 * 修改货品
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/update")
    @Produces({ MediaType.APPLICATION_JSON })
    public String updateProduct(String json,@Context HttpHeaders header)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<ProductVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<ProductVo>>(){}.getType());
    	productService.updateProduct(baseVo.getBody());
    	ResultVo result = new ResultVo(true);
        return result.toString();
    }
	
	/**
	 * 删除货品
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/delete")
    @Produces({ MediaType.APPLICATION_JSON })
    public String deleteProduct(String json,@Context HttpHeaders header)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<ProductVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<ProductVo>>(){}.getType());
    	productService.deleteProduct(baseVo.getBody());
    	ResultVo result = new ResultVo(true);
        return result.toString();
    }
	
	/**
	 * 查询货品
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/query")
    @Produces({ MediaType.APPLICATION_JSON })
    public String queryProduct(String json,@Context HttpHeaders header)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<ProductVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<ProductVo>>(){}.getType());
    	List<Product> products= productService.getProductList(baseVo.getBody());
    	ResultVo result = new ResultVo(true,products);
        return result.toString();
    }
	
	/**
	 * 查询货品(分页)
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/queryPage")
    @Produces({ MediaType.APPLICATION_JSON })
    public String queryPageProduct(String json,@Context HttpHeaders header)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<ProductVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<ProductVo>>(){}.getType());
    	PageFinder<Product> products= productService.pagedProductList(baseVo.getBody());
    	ResultVo result = new ResultVo(true,products);
        return result.toString();
    }
	
	/**
	 * 新建货品调价
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/addChangePrice")
    @Produces({ MediaType.APPLICATION_JSON })
    public String addChangePrice(String json,@Context HttpHeaders header)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<ProductChangePriceVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<ProductChangePriceVo>>(){}.getType());
    	String changeNo=productService.createProductChangePrice(baseVo.getBody());
    	ResultVo result = new ResultVo(true);
    	result.put("changeNo", changeNo);
        return result.toString();
    }
	
	/**
	 * 审核货品调价
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/conserChangePrice")
    @Produces({ MediaType.APPLICATION_JSON })
    public String conserChangePrice(String json,@Context HttpHeaders header)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<ProductChangePriceVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<ProductChangePriceVo>>(){}.getType());
    	productService.conserProductChangePrice(baseVo.getBody());
    	ResultVo result = new ResultVo(true);
        return result.toString();
    }
	
	/**
	 *查询货品调价单
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/queryChangePrice")
    @Produces({ MediaType.APPLICATION_JSON })
    public String queryChangePrice(String json,@Context HttpHeaders header)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<ProductChangePriceVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<ProductChangePriceVo>>(){}.getType());
    	List<ProductChangePrice> changePrices=productService.getProductChangePriceList(baseVo.getBody());
    	ResultVo result = new ResultVo(true,changePrices);
        return result.toString();
    }
	
	/**
	 *查询货品调价单(分页)
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/queryPageChangePrice")
    @Produces({ MediaType.APPLICATION_JSON })
    public String queryPageChangePrice(String json,@Context HttpHeaders header)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<ProductChangePriceVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<ProductChangePriceVo>>(){}.getType());
    	PageFinder<ProductChangePrice> changePrices=productService.pagedProductChangePriceList(baseVo.getBody());
    	ResultVo result = new ResultVo(true,changePrices);
        return result.toString();
    }
	
	
	/**
	 *查询货品库存
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/getStock")
    @Produces({ MediaType.APPLICATION_JSON })
    public String getStock(String json,@Context HttpHeaders header)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<ProductStockVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<ProductStockVo>>(){}.getType());
    	ProductStock stock=productService.getStock(baseVo.getBody());
    	ResultVo result = new ResultVo(true,stock);
        return result.toString();
    }
	
	/**
	 *初始货品库存
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/initStock")
    @Produces({ MediaType.APPLICATION_JSON })
    public String initStock(String json,@Context HttpHeaders header)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<ProductStockVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<ProductStockVo>>(){}.getType());
    	productService.initStock(baseVo.getBody());
    	ResultVo result = new ResultVo(true);
        return result.toString();
    }
	
	/**
	 *操作货品库存
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/handleStock")
    @Produces({ MediaType.APPLICATION_JSON })
    public String hanleStock(String json,@Context HttpHeaders header)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<ProductStockVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<ProductStockVo>>(){}.getType());
    	productService.handleStock(baseVo.getBody());
    	ResultVo result = new ResultVo(true);
        return result.toString();
    }
	
	/**
	 *批量操作货品库存
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/batchHandleStock")
    @Produces({ MediaType.APPLICATION_JSON })
    public String batchHandleStock(String json,@Context HttpHeaders header)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<List<ProductStockVo>> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<List<ProductStockVo>>>(){}.getType());
    	productService.batchHandleStock(baseVo.getBody());
    	ResultVo result = new ResultVo(true);
        return result.toString();
    }
	
	/**
	 *查询货品库存
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/queryCommodity")
    @Produces({ MediaType.APPLICATION_JSON })
    public String queryCommodity(String json,@Context HttpHeaders header)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<ProductVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<ProductVo>>(){}.getType());
    	List<Commodity> commos= productService.queryCommodity(baseVo.getBody());
    	ResultVo result = new ResultVo(true,commos);
//    	if(baseVo.getBody().getReturnInfo()==0){
//    		return result.toStringNoSkip(ParamUtil.getFilter("commodity", ParamUtil.COMMODITY_NO_SKIP));
//    	}else{
    		String[] strs = (String[]) ArrayUtils.addAll(ParamUtil.COMMODITY_NO_SKIP, ParamUtil.COMMODITY_BIG_INFO);
    		return result.toStringNoSkip(ParamUtil.getFilter("commodity", strs));
//    	}
    }
}
