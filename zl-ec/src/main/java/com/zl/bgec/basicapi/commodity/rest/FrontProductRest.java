package com.zl.bgec.basicapi.commodity.rest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.google.gson.reflect.TypeToken;
import com.zl.bgec.basicapi.basecomponent.page.PageFinder;
import com.zl.bgec.basicapi.commodity.po.CommodityComment;
import com.zl.bgec.basicapi.commodity.service.ICommodityService;
import com.zl.bgec.basicapi.commodity.service.IProductService;
import com.zl.bgec.basicapi.commodity.vo.CommodityVo;
import com.zl.bgec.basicapi.commodity.vo.ProductVo;
import com.zl.bgec.basicapi.common.BaseVo;
import com.zl.bgec.basicapi.common.ResultVo;
import com.zl.bgec.basicapi.common.oauth2.UserInfo;
import com.zl.bgec.basicapi.common.util.GsonUtil;
import com.zl.bgec.basicapi.common.util.RestUtil;

@Component
@Path("/front/product")
public class FrontProductRest {
	@Resource
	private IProductService productService;
	@Resource
	private ICommodityService commodityService;
	
	/**
	 * 校验商品库存
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/checkProdStock")
    @Produces({ MediaType.APPLICATION_JSON })
    public String checkProdStock(String json,@Context HttpHeaders header,@Context HttpServletRequest request)throws Exception
    {
    	BaseVo<ProductVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<ProductVo>>(){}.getType());
    	ProductVo productVo = baseVo.getBody();
    	ProductVo product= productService.checkProdStock(productVo.getProdNo());
    	ResultVo result = new ResultVo(true,product);
        return result.toString();
    }
	
	/**
	 * 商品详情
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/detail")
    @Produces({ MediaType.APPLICATION_JSON })
    public String queryProduct(String json,@Context HttpHeaders header,@Context HttpServletRequest request)throws Exception
    {
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
    	json = RestUtil.strDecode(json);
    	BaseVo<ProductVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<ProductVo>>(){}.getType());
    	ProductVo productVo = baseVo.getBody();
    	CommodityVo commodity= productService.getCommodityDetail(productVo.getProdNo(), userInfo.getId()+"");
    	ResultVo result = new ResultVo(true,commodity);
        return result.toString();
    }
	
	/**
	 * 查询商品评价
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("/querycomment")
	@Produces({ MediaType.APPLICATION_JSON })
	public String queryProductComments(String json,@Context HttpHeaders header)throws Exception
	{
		json = RestUtil.strDecode(json);
		BaseVo<ProductVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<ProductVo>>(){}.getType());
		ProductVo productVo = baseVo.getBody();
		PageFinder<CommodityComment> commodity= productService.pageCommdityCommentList(productVo.getProdNo(), productVo.getPageNo(), productVo.getPageSize());
		ResultVo result = new ResultVo(true,commodity);
		return result.toString();
	}
	/**
	 * 收藏商品
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("/cancelcollect")
	@Produces({ MediaType.APPLICATION_JSON })
	public String cancelCollectProduct(String json,@Context HttpHeaders header,@Context HttpServletRequest request)throws Exception
	{
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		json = RestUtil.strDecode(json);
		BaseVo<ProductVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<ProductVo>>(){}.getType());
		commodityService.cancelCollectCommodity(baseVo.getBody().getProdNo(),userInfo.getId()+"");
		ResultVo result = new ResultVo(true);
		return result.toString();
	}
	/**
	 * 收藏商品
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("/collect")
	@Produces({ MediaType.APPLICATION_JSON })
	public String collectProduct(String json,@Context HttpHeaders header,@Context HttpServletRequest request)throws Exception
	{
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		json = RestUtil.strDecode(json);
		BaseVo<ProductVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<ProductVo>>(){}.getType());
		commodityService.collectCommodity(baseVo.getBody().getProdNo(),userInfo.getId()+"");
		ResultVo result = new ResultVo(true);
		return result.toString();
	}
}
