package com.zl.bgec.basicapi.common.exception;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.sun.jersey.api.NotFoundException;
import com.zl.bgec.basicapi.common.ResultVo;

@Component
@Provider
public class CommodityExceptionMapperSupport implements ExceptionMapper<Exception>{
	private static Logger log = Logger.getLogger(CommodityExceptionMapperSupport.class);
	private static final String CONTEXT_ATTRIBUTE = WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE;  
  
    @Context  
    private HttpServletRequest request;  
  
    @Context  
    private ServletContext servletContext; 
	@Override
	public Response toResponse(Exception exception) {
		ResultVo result = new ResultVo(false);
		Status statusCode = Status.INTERNAL_SERVER_ERROR;  
//        WebApplicationContext context = (WebApplicationContext) servletContext  
//                .getAttribute(CONTEXT_ATTRIBUTE);
        String code="";
        String content="";
        // 处理unchecked exception  
        if (exception instanceof BaseException) {  
            BaseException baseException = (BaseException) exception;  
            code = baseException.getCode();  
            content = baseException.getContent();  
        } else if (exception instanceof NotFoundException) {  
        	code = ExceptionEnum.REQUEST_NOT_FOUND.getCode();  
            content = ExceptionEnum.REQUEST_NOT_FOUND.getContent();   
            statusCode = Status.NOT_FOUND;  
        } else{
        	code = ExceptionEnum.STSTEM.getCode();
        	content = ExceptionEnum.STSTEM.getContent();   
        }
        result.put("code", code);
        result.put("content", content);
        log.error(result.toString(), exception);  
        return Response.ok(result.toString(), MediaType.APPLICATION_JSON).status(statusCode)  
                .build();
	}

}
