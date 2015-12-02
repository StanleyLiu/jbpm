package test;

import java.util.HashMap;
import java.util.Map;

import com.zl.bgec.basicapi.commodity.vo.CommodityCatVo;
import com.zl.bgec.basicapi.common.BaseVo;
import com.zl.bgec.basicapi.common.util.GsonUtil;
import com.zl.bgec.basicapi.common.util.RestUtil;

public class TestCommodityCatRest {
	@org.junit.Test
	public void testAdd(){
		BaseVo<CommodityCatVo> baseVo = new BaseVo<CommodityCatVo>();
		CommodityCatVo commodityCatVo = new CommodityCatVo();
		commodityCatVo.setCatName("美食");
		commodityCatVo.setCatPno("0");
		commodityCatVo.setDescription("顶级美食");
		baseVo.setBody(commodityCatVo);
		System.out.println(GsonUtil.toJson(baseVo));
		String json = RestUtil.restWan(GsonUtil.toJson(baseVo),"http://localhost:8088/EDS_COMMODITY/rest/commodity/commo_cat/add");
		System.out.println(json);
	}
	@org.junit.Test
	public void testQuery(){
		BaseVo<CommodityCatVo> baseVo = new BaseVo<CommodityCatVo>();
		CommodityCatVo commodityCatVo = new CommodityCatVo();
		commodityCatVo.setCatPno("0");;
		baseVo.setBody(commodityCatVo);
		System.out.println(GsonUtil.toJson(baseVo));
		String json = RestUtil.restWan(GsonUtil.toJson(baseVo),"http://localhost:8088/EDS_COMMODITY/rest/commodity/commo_cat/query");
		System.out.println(json);
	}
	/**
	 * 获取店铺的所有商品分类
	 */
	@org.junit.Test
	public void testQueryCat(){
		BaseVo<Map<String,String>> baseVo = new BaseVo<Map<String,String>>();
		Map<String,String> commodityCatVo = new HashMap<String,String>();
		commodityCatVo.put("shopNo", "14197770082962049040");
		baseVo.setBody(commodityCatVo);
		System.out.println(GsonUtil.toJson(baseVo));
		String json = RestUtil.restWan(GsonUtil.toJson(baseVo),"http://localhost:8088/EDS_COMMODITY/rest/commodity/commo_cat/queryCats");
		System.out.println(json);
	}
}
