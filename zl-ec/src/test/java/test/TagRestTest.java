package test;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.zl.bgec.basicapi.commodity.vo.CommodityTagVo;
import com.zl.bgec.basicapi.common.util.RestUtil;

public class TagRestTest {
	public static void main(String[] args) {
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
        CommodityTagVo vo = new CommodityTagVo();
//        vo.setTagName("2222");
//        vo.setDescription("123123");
//        vo.setEnableFlag((byte)1);
//        vo.setSellerNo("admin");
//        vo.setTagName("rest标签");
        vo.setTagNo("00000081");
        
        vo.setPageNo(2);
        vo.setPageSize(2);
//        vo.setBrandName("2");
        
        List<String> nos = new ArrayList<String>();
        nos.add("10000001");
//        nos.add("10000002");
        nos.add("10000003");
        vo.setCommodityNos(nos);
        jsonObject.put("body", vo);
        //add
//        String json = RestUtil.restWan(jsonObject.toString(),"http://localhost:8088/EDS_COMMODITY/rest/commodity/commo_tag/add");

        //edit
//        String json = RestUtil.restWan(jsonObject.toString(),"http://localhost:8088/EDS_COMMODITY/rest/commodity/commo_brand/update");
        
        //delete
//        String json = RestUtil.restWan(jsonObject.toString(),"http://localhost:8088/EDS_COMMODITY/rest/commodity/commo_brand/delete");
        
        
        //query
      String json = RestUtil.restWan(jsonObject.toString(),"http://localhost:8088/EDS_COMMODITY/rest/commodity/commo_tag/removeBind");
      System.out.println(json);
        
	}
}
