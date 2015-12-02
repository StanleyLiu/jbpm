package com.zl.bgec.basicapi.order.rest;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.google.gson.reflect.TypeToken;
import com.zl.bgec.basicapi.common.BaseVo;
import com.zl.bgec.basicapi.common.ResultVo;
import com.zl.bgec.basicapi.common.oauth2.SdkConstants;
import com.zl.bgec.basicapi.common.oauth2.Signature;
import com.zl.bgec.basicapi.common.oauth2.UserInfo;
import com.zl.bgec.basicapi.common.oauth2.UserServerAddressRestResponse;
import com.zl.bgec.basicapi.common.util.GsonUtil;
import com.zl.bgec.basicapi.common.util.RestUtil;
import com.zl.bgec.basicapi.order.po.MemberAddress;
import com.zl.bgec.basicapi.order.service.IMemberAddressComponent;

@Component
@Path("/front/memberaddress")
public class FrontMemberAddressRest {
	
	private static Logger log = Logger.getLogger(FrontMemberAddressRest.class);
	
	@Resource
	private SdkConstants sdkConstants;
	@Resource
	private IMemberAddressComponent addressComponent;
	  /**
     * 查询我的收货地址
     * @param json
     * @return
     * @throws Exception
     */
    @POST
    @Path("/query")
    @Produces({ MediaType.APPLICATION_JSON })
    public String getMyOrders(String json,@Context HttpHeaders header,@Context HttpServletRequest request)throws Exception
    {
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
    	/*UserInfo userInfo = new UserInfo();
    	userInfo.setId(1031l);*/
		log.info("FrontMemberAddressRest-query-userInfo="+GsonUtil.toJson(userInfo));
    	
		Map<String,String> params = new HashMap<String, String>();
		params.put("appKey",  RestUtil.strEncoder(sdkConstants.getAPI_KEY()));
		params.put("userId", userInfo.getId()+"");
        String signature = Signature.computeSignature(params, sdkConstants.getAPI_SECRET());
        String url = String.format("%s?appKey=%s&signature=%s&userId=%s",sdkConstants.getCLIENT_USERSERVERADDRESS_URI(),
        		 RestUtil.strEncoder(sdkConstants.getAPI_KEY()), RestUtil.strEncoder(signature),userInfo.getId()+"");
        
        log.info("FrontMemberAddressRest-query-url="+url);
        String resultJson = RestUtil.restPostForm(url);
        log.info("FrontMemberAddressRest-query-resultJson="+resultJson);
        
        UserServerAddressRestResponse userServerAddressRestResponse = GsonUtil.fromJson(resultJson, UserServerAddressRestResponse.class);
    	ResultVo result = new ResultVo(true,userServerAddressRestResponse.getResponse());
        return result.toString();
    }
    /**
     * 新增收货地址
     * @param json
     * @return
     * @throws Exception
     */
    @POST
    @Path("/add")
    @Produces({ MediaType.APPLICATION_JSON })
    public String addAddress(String json,@Context HttpHeaders header,@Context HttpServletRequest request)throws Exception
    {
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
    	json = RestUtil.strDecode(json);
    	BaseVo<MemberAddress> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<MemberAddress>>(){}.getType());
    	MemberAddress memberAddress = baseVo.getBody();
    	memberAddress.setMemberNo(userInfo.getId()+"");
    	addressComponent.addMemberAddress(memberAddress);
    	ResultVo result = new ResultVo(true);
    	return result.toString();
    }
    /**
     * 新增收货地址
     * @param json
     * @return
     * @throws Exception
     */
    @POST
    @Path("delete")
    @Produces({ MediaType.APPLICATION_JSON })
    public String deleteAddress(String json,@Context HttpHeaders header,@Context HttpServletRequest request)throws Exception
    {
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
    	json = RestUtil.strDecode(json);
    	BaseVo<MemberAddress> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<MemberAddress>>(){}.getType());
    	MemberAddress memberAddress = baseVo.getBody();
    	memberAddress.setMemberNo(userInfo.getId()+"");
    	addressComponent.deleteMemberAddress(memberAddress);
    	ResultVo result = new ResultVo(true);
    	return result.toString();
    }
}
