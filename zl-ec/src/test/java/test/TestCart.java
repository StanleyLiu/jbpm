package test;

import com.zl.bgec.basicapi.commodity.vo.CommodityVo;
import com.zl.bgec.basicapi.common.BaseVo;
import com.zl.bgec.basicapi.common.util.GsonUtil;
import com.zl.bgec.basicapi.common.util.RestUtil;

public class TestCart {
	@org.junit.Test
	public  void testQuery(){
		BaseVo<CommodityVo> baseVo = new BaseVo<CommodityVo>();
		
		CommodityVo commodityVo = new CommodityVo();
		commodityVo.setCommoNo("03000000101");
		baseVo.setBody(commodityVo);
		System.out.println(GsonUtil.toJson(baseVo));
		String json = RestUtil.restWan(GsonUtil.toJson(baseVo),"http://localhost:8088/zl-ec/rest/service/front/cart/add");
//		Client client = Client.create();
//		WebResource r = client.resource("http://localhost:8088/zl-ec/rest/service/front/cart/add");
//		String json=GsonUtil.toJson(baseVo);
//		Cookie cookie = new Cookie("a", "sss");
//		r.cookie(cookie);
//		r.header("aa", "111");
//		ClientResponse response = r.post(ClientResponse.class, json);
//		String result = response.getEntity(String.class);
//		 json = result;
		System.out.println(json);
		CommodityVo commodity = GsonUtil.fromJson(json,CommodityVo.class);
		System.out.println(commodity);
		
	}
	
	@org.junit.Test
	public  void testQueryCount(){
		BaseVo<CommodityVo> baseVo = new BaseVo<CommodityVo>();
		String json = RestUtil.restWan(GsonUtil.toJson(baseVo),"http://localhost:8088/zl-ec/rest/service/front/cart/querycount");
		System.out.println(json);
	}
}
