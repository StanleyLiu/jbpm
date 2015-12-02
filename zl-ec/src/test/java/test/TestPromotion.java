package test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.reflect.TypeToken;
import com.zl.bgec.basicapi.basecomponent.page.PageFinder;
import com.zl.bgec.basicapi.common.BaseVo;
import com.zl.bgec.basicapi.common.util.GsonUtil;
import com.zl.bgec.basicapi.common.util.RestUtil;
import com.zl.bgec.basicapi.promotion.vo.PromotionVo;

public class TestPromotion {

	@org.junit.Test
	public void testAdd() throws Exception {
		BaseVo<PromotionVo> baseVo = new BaseVo<PromotionVo>();
		PromotionVo promotionVo = new PromotionVo();
		promotionVo.setCoupCount(20);
		promotionVo.setDescription("大降价");
		promotionVo.setDiscountAmount(20d);
		promotionVo.setEndTime(new Date());
		promotionVo.setStartTime(new Date());
		promotionVo.setShopNo("14197770082962049040");
		promotionVo.setLeastAmount(200d);
		promotionVo.setPromotionName("优惠活动");
		promotionVo.setPromotionType("1");
		baseVo.setBody(promotionVo);
		System.out.println(GsonUtil.toJson(baseVo));
		String json = RestUtil.restWan(GsonUtil.toJson(baseVo),"http://localhost:8088/zl-ec/rest/promotion/promotion/add");
	    System.out.println(json);
	}
	@org.junit.Test
	public void testAddGroup() throws Exception {
		BaseVo<PromotionVo> baseVo = new BaseVo<PromotionVo>();
		PromotionVo promotionVo = new PromotionVo();
		promotionVo.setCoupCount(200);
		promotionVo.setDescription("大降价");
		promotionVo.setDiscountAmount(20d);
		promotionVo.setEndTime(new Date());
		promotionVo.setStartTime(new Date());
		promotionVo.setShopNo("14197770082962049040");
		promotionVo.setRefCommoNo("01000000011");
		promotionVo.setPromotionName("团购活动");
		promotionVo.setPromotionType("2");
		baseVo.setBody(promotionVo);
		System.out.println(GsonUtil.toJson(baseVo));
		String json = RestUtil.restWan(GsonUtil.toJson(baseVo),"http://localhost:8088/zl-ec/rest/promotion/promotion/add");
		System.out.println(json);
	}
	@org.junit.Test
	public void testQueryGroup() throws Exception {
		BaseVo<PromotionVo> baseVo = new BaseVo<PromotionVo>();
		PromotionVo promotionVo = new PromotionVo();
		promotionVo.setQueryCondition("1");
		promotionVo.setShopNo("14462789604859258168");
		promotionVo.setPageNo(1);
		promotionVo.setPageSize(50);
		baseVo.setBody(promotionVo);
		System.out.println(GsonUtil.toJson(baseVo));
		String json = RestUtil.restWan(GsonUtil.toJson(baseVo),"http://localhost:8088/zl-ec/rest/promotion/promotion/queryGroupbuyPage");
		System.out.println(json);
		BaseVo<PageFinder<Map<String,Object>>> pageFinder = GsonUtil.fromJson(json, new TypeToken<BaseVo<PageFinder<Map<String,Object>>>>(){}.getType());
		List<Map<String,Object>> data = pageFinder.getBody().getData();
		System.out.println(pageFinder.getBody().getRowCount());
	}
	@org.junit.Test
	public void testdelete() throws Exception {
		BaseVo<PromotionVo> baseVo = new BaseVo<PromotionVo>();
		PromotionVo promotionVo = new PromotionVo();
		promotionVo.setPromotionNo("14464362669172120");
		baseVo.setBody(promotionVo);
		System.out.println(GsonUtil.toJson(baseVo));
		String json = RestUtil.restWan(GsonUtil.toJson(baseVo),"http://localhost:8088/zl-ec/rest/promotion/promotion/delete");
		System.out.println(json);
	}
	@org.junit.Test
	public void testqueryDetail() throws Exception {
		BaseVo<PromotionVo> baseVo = new BaseVo<PromotionVo>();
		PromotionVo promotionVo = new PromotionVo();
		promotionVo.setPromotionNo("14455189091934372");
		baseVo.setBody(promotionVo);
		System.out.println(GsonUtil.toJson(baseVo));
		String json = RestUtil.restWan(GsonUtil.toJson(baseVo),"http://localhost:8088/zl-ec/rest/promotion/promotion/queryGroupbuyDetail");
		System.out.println(json);
	}
	@org.junit.Test
	public void testQuery() throws Exception {
		BaseVo<PromotionVo> baseVo = new BaseVo<PromotionVo>();
		PromotionVo promotionVo = new PromotionVo();
		promotionVo.setQueryCondition("1");
		promotionVo.setShopNo("14197770082962049040");
		promotionVo.setPageNo(1);
		promotionVo.setPageSize(50);
		baseVo.setBody(promotionVo);
		System.out.println(GsonUtil.toJson(baseVo));
		String json = RestUtil.restWan(GsonUtil.toJson(baseVo),"http://localhost:8088/zl-ec/rest/promotion/promotion/queryPage");
		System.out.println(json);
		BaseVo<PageFinder<Map<String,Object>>> pageFinder = GsonUtil.fromJson(json, new TypeToken<BaseVo<PageFinder<Map<String,Object>>>>(){}.getType());
		List<Map<String,Object>> data = pageFinder.getBody().getData();
		System.out.println(pageFinder.getBody().getRowCount());
		if(data!=null&&!data.isEmpty()){
			for (Map<String, Object> map : data) {
				for (Map.Entry<String, Object> entry : map.entrySet()) {
					if(entry.getKey().equals("promotionState")){
						System.out.println("key:"+entry.getKey()+",,value:"+entry.getValue());
						
					}
				}
				System.out.println("--------------------------");
			}
		}
	}
	@org.junit.Test
	public void testQueryFront() throws Exception {
		BaseVo<PromotionVo> baseVo = new BaseVo<PromotionVo>();
		PromotionVo promotionVo = new PromotionVo();
		promotionVo.setMemberNo("9876");
		promotionVo.setShopNo("14197770082962049040");
		baseVo.setBody(promotionVo);
		System.out.println(GsonUtil.toJson(baseVo));
		String json = RestUtil.restWan(GsonUtil.toJson(baseVo),"http://localhost:8088/zl-ec/rest/front/promotion/query");
		System.out.println(json);
	}
	@org.junit.Test
	public void testQueryPageFront() throws Exception {
		BaseVo<PromotionVo> baseVo = new BaseVo<PromotionVo>();
		PromotionVo promotionVo = new PromotionVo();
		promotionVo.setPageNo(1);
		promotionVo.setPageSize(30);
		baseVo.setBody(promotionVo);
		System.out.println(GsonUtil.toJson(baseVo));
		String json = RestUtil.restWan(GsonUtil.toJson(baseVo),"http://localhost:8088/zl-ec/rest/front/promotion/queryall");
		System.out.println(json);
	}
	@org.junit.Test
	public void testQueryMyPromotion() throws Exception {
		BaseVo<PromotionVo> baseVo = new BaseVo<PromotionVo>();
		PromotionVo promotionVo = new PromotionVo();
		promotionVo.setMemberNo("9876");
		promotionVo.setQueryCondition("");
		promotionVo.setPageNo(1);
		promotionVo.setPageSize(20);
		baseVo.setBody(promotionVo);
		System.out.println(GsonUtil.toJson(baseVo));
		String json = RestUtil.restWan(GsonUtil.toJson(baseVo),"http://localhost:8088/zl-ec/rest/front/promotion/mypromotion");
		System.out.println(json);
	}
	@org.junit.Test
	public void testQueryUse() throws Exception {
		BaseVo<PromotionVo> baseVo = new BaseVo<PromotionVo>();
		PromotionVo promotionVo = new PromotionVo();
		promotionVo.setPromotionNo("14333341554755154");
		promotionVo.setIsUsed("1");
		baseVo.setBody(promotionVo);
		System.out.println(GsonUtil.toJson(baseVo));
		String json = RestUtil.restWan(GsonUtil.toJson(baseVo),"http://localhost:8088/zl-ec/rest/promotion/promotion/queryUse");
		System.out.println(json);
	}
	@org.junit.Test
	public void testFetch() throws Exception {
		BaseVo<PromotionVo> baseVo = new BaseVo<PromotionVo>();
		PromotionVo promotionVo = new PromotionVo();
		promotionVo.setPromotionNo("14333341554755154");
		promotionVo.setMemberNo("111111");
		baseVo.setBody(promotionVo);
		System.out.println(GsonUtil.toJson(baseVo));
		String json = RestUtil.restWan(GsonUtil.toJson(baseVo),"http://localhost:8088/zl-ec/rest/front/promotion/fetch");
		System.out.println(json);
	}
	@org.junit.Test
	public void testQueryDetail() throws Exception {
		BaseVo<PromotionVo> baseVo = new BaseVo<PromotionVo>();
		PromotionVo promotionVo = new PromotionVo();
		promotionVo.setPromotionNo("14333341554755154");;
		baseVo.setBody(promotionVo);
		System.out.println(GsonUtil.toJson(baseVo));
		String json = RestUtil.restWan(GsonUtil.toJson(baseVo),"http://localhost:8088/zl-ec/rest/promotion/promotion/queryDetailInfo");
		System.out.println(json);
	}
	@org.junit.Test
	public void testQueryMyDetail() throws Exception {
		BaseVo<PromotionVo> baseVo = new BaseVo<PromotionVo>();
		PromotionVo promotionVo = new PromotionVo();
		promotionVo.setPromotionNo("15071014385000375");;
		baseVo.setBody(promotionVo);
		System.out.println(GsonUtil.toJson(baseVo));
		String json = RestUtil.restWan(GsonUtil.toJson(baseVo),"http://localhost:8088/zl-ec/rest/front/promotion/mypromotiondetail");
		System.out.println(json);
	}
	@org.junit.Test
	public void testLock() throws Exception {
		BaseVo<Map<String,String>> baseVo = new BaseVo<Map<String,String>>();
		Map<String,String> promotionVo = new HashMap<String,String>();
		promotionVo.put("promotionNo","14333341554755154");
		promotionVo.put("lockFlag","1");
		baseVo.setBody(promotionVo);
		System.out.println(GsonUtil.toJson(baseVo));
		String json = RestUtil.restWan(GsonUtil.toJson(baseVo),"http://localhost:8088/zl-ec/rest/promotion/promotion/lock");
		System.out.println(json);
	}
	
}
