package test;

import com.zl.bgec.basicapi.commodity.vo.ProductVo;
import com.zl.bgec.basicapi.common.BaseVo;
import com.zl.bgec.basicapi.common.util.GsonUtil;
import com.zl.bgec.basicapi.common.util.RestUtil;

public class TestProduct {
	@org.junit.Test
	public  void testQuery(){
		BaseVo<ProductVo> baseVo = new BaseVo<ProductVo>();
		
		ProductVo productVo = new ProductVo();
		productVo.setShopNo("14197770082962049040");
		baseVo.setBody(productVo);
		productVo.setPageNo(1);
		productVo.setPageSize(2);
		productVo.setProdName("手机");
		System.out.println(GsonUtil.toJson(baseVo));
		String json = RestUtil.restWan(GsonUtil.toJson(baseVo),"http://localhost:8088/zl-ec/rest/commodity/product/queryPage");
		System.out.println(json);
//		CommodityVo commodity = GsonUtil.fromJson(json,CommodityVo.class);
//		System.out.println(commodity);
		
	}
	@org.junit.Test
	public  void testQueryDetail(){
		BaseVo<ProductVo> baseVo = new BaseVo<ProductVo>();
		
		ProductVo productVo = new ProductVo();
		productVo.setProdNo("0100000008301");
		productVo.setMemberNo("手机");
		baseVo.setBody(productVo);
		System.out.println(GsonUtil.toJson(baseVo));
		String json = RestUtil.restWan(GsonUtil.toJson(baseVo),"http://localhost:8088/zl-ec/rest/front/product/detail");
		System.out.println(json);
//		CommodityVo commodity = GsonUtil.fromJson(json,CommodityVo.class);
//		System.out.println(commodity);
		
	}
	
	@org.junit.Test
	public  void testQueryComment(){
		BaseVo<ProductVo> baseVo = new BaseVo<ProductVo>();
		
		ProductVo commodityVo = new ProductVo();
		commodityVo.setProdNo("0300000011101");
		commodityVo.setPageNo(1);
		commodityVo.setPageSize(10);
		baseVo.setBody(commodityVo);
		System.out.println(GsonUtil.toJson(baseVo));
		String json = RestUtil.restWan(GsonUtil.toJson(baseVo),"http://121.199.69.107:8080/zl-ec/rest/front/product/querycomment");
		System.out.println(json);
		
	}
}
