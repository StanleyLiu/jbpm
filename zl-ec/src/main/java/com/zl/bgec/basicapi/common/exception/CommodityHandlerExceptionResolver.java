package com.zl.bgec.basicapi.common.exception;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.zl.bgec.basicapi.common.ResultVo;


@Deprecated
public class CommodityHandlerExceptionResolver extends SimpleMappingExceptionResolver{
	
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,  
            Exception ex) {  
		ResultVo result = new ResultVo(false);
		result.put("code", ex.getClass().getName());
		result.put("content", ex.getMessage());
        PrintWriter out = null;
        try{
        	out = response.getWriter();
        	out.print(result.toString());
        }catch(IOException e){
        	e.printStackTrace();
        }finally{
        	out.close();
        }
        return null;
    } 
}
