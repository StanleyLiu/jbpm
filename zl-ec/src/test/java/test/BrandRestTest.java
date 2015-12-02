package test;

import com.alibaba.fastjson.JSONObject;
import com.zl.bgec.basicapi.commodity.vo.CommodityVo;
import com.zl.bgec.basicapi.common.util.RestUtil;

public class BrandRestTest {
	public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("sysid", "SYSID_001");
		jsonObject.put("sid", "SYSID_001");
		jsonObject.put("sidv", "SYSID_001");
		jsonObject.put("sign", "SYSID_001");
		jsonObject.put("token", "SYSID_001");
		jsonObject.put("encry", "SYSID_001");
		jsonObject.put("format", "SYSID_001");
		jsonObject.put("session", "SYSID_001");
		
		
//        add
//        CommodityTagVo vo = new CommodityTagVo();
		CommodityVo vo = new CommodityVo();
//		CommodityVo cvo = new CommodityVo();
//        vo.setBrandEnName("2222");
//        vo.setBrandKey("222");
//        vo.setBrandName("2222");
//        vo.setBrandTitle("4444");
//        vo.setDescription("33333");
//        vo.setIsDisplay((byte)0);
//        vo.setSeoDescription("11111aaaa");
//        vo.setSeoKey("2222");
//        vo.setSeoTitle("3333333");
//        vo.setWebsite("555555");
//        vo.setBrandNo("10000762");
//        vo.setPageNo(1);
//        vo.setPageSize(5);
//        cvo.setPageNo(1);
//        cvo.setPageSize(5);
//        vo.setBrandNo("123");
//        vo.setBrandName("品牌");
//        vo.setBrandEnName("pinyin");
//		vo.setIsDisplay((byte)0);
//		List<String> nos = new ArrayList<String>();
//		nos.add("123123");
//		nos.add("1231234");
//        vo.setBrandNos(nos);
//		vo.setCommodityCatNos(nos);
//		vo.setPropName("属性");
//		vo.setInputType((byte)0);
//		vo.setDescription("描述");
//		vo.setIsSpecProp((byte)1);
//		List<CommodityPropValueVo> list = new ArrayList<CommodityPropValueVo>();
//		for(int i=1;i<3;i++){
//			CommodityPropValueVo value = new CommodityPropValueVo();
//			value.setOptionName("属性值名称"+i);
//			list.add(value);
//		}
//		vo.setPropValues(list);
//		vo.setCommentNo("123123");
//		vo.setIsSpecProp((byte)1);
//		List<CommodityPropGroupPropVo> vos = new ArrayList<CommodityPropGroupPropVo>();
//		for(int i=0;i<2;i++){
//			CommodityPropGroupPropVo pgp = new CommodityPropGroupPropVo();
//			pgp.setPropGroupNo("123"+i);
//			pgp.setPropNo("333"+i);
//			vos.add(pgp);
//		}
//		vo.setPropGroupPropVos(vos);
		
		vo.setBrandNo("");
		vo.setCatNo("");
		
		
		
		
		vo.setPageNo(1);
		vo.setPageSize(2);
        jsonObject.put("body", vo);
//        String json = RestUtil.restWan(jsonObject.toString(),"http://127.0.0.1:8088/EDS_COMMODITY/rest/commodity/commo_brand/add");
//        System.out.println(json);
//        
        //edit
//        jsonObject.put("id", "4028a18e48a1ff390148a1ff40670000");
//        jsonObject.put("name", "aaaabbbbcccddddeee");
//        String json = RestUtil.restWan(jsonObject.toString(),"http://localhost:8088/EDS_COMMODITY/rest/commodity/commo_brand/update");
//        System.out.println(json);
        
        //delete
//        jsonObject.put("id", "4028a18e48a084710148a084770b0000");
//        String json = RestUtil.restWan(jsonObject.toString(),"http://localhost:8088/EDS_COMMODITY/rest/commodity/commo_brand/delete");
//        System.out.println(json);
        
        
        //query
//      System.out.println(jsonObject.toString());
//      String json = RestUtil.restWan(jsonObject.toString(),"http://192.168.5.90:8080/EDS_COMMODITY/rest/commodity/commo_prop/queryPropVal");
      String json = RestUtil.restWan(jsonObject.toString(),"http://localhost:8080/EDS_COMMODITY/rest/commodity/product/queryPageChangePrice");
//      String json2 = RestUtil.restWan(jsonObject.toString(),"http://192.168.5.90:8080/EDS_COMMODITY/rest/commodity/commodity/queryPage");
//      System.out.println(json);
//    
     
        
		// 演示消息信息转为Java实体对象
//        String v_RequestMsgInfo = "{\"sysid\":\"SYSID_001\""
//                + ",\"sid\":\"queryByNo\""
//                + ",\"sidv\":\"SIDV_003\""
//                + ",\"sign\":\"SIGN_004\""
//                + ",\"token\":\"TOKEN_005\""
//                + ",\"encry\":\"ENCRY_006\""
//                + ",\"format\":\"FORMAT_007\""
//                + ",\"session\":\"SESSION_008\""
//                + ",\"body\":[{"
//                + "\"orderBatchNo\":\"orderBatchNo_001\""
//                + ",\"orderNo\":\"orderNo_001\""
//                + ",\"operatorNo\":\"operatorNo_001\""
//                + "}]"
//                + "}";
//        Gson gson = new Gson();
//        BaseVo<List<MsgOrderNo>> obj= GsonUtil.fromJson(v_RequestMsgInfo,new TypeToken<BaseVo<List<MsgOrderNo>>>(){}.getType());
//        System.out.println(obj.getBody().get(0).getOrderBatchNo());
	}
}
