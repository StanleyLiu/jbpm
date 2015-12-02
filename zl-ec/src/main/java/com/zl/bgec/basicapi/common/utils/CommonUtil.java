package com.zl.bgec.basicapi.common.utils;

import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
 * 工具栏 - 公用
 * 
 * @ClassName: CommonUtil
 * @Description: TODO
 * @author: MyLife
 * @date 2012-11-21 下午3:36:26
 * 
 */
public class CommonUtil {
	public static final String PATH_PREPARED_STATEMENT_UUID = "\\{uuid\\}"; // UUID路径占位符
	public static final String PATH_PREPARED_STATEMENT_DATE = "\\{date(\\(\\w+\\))?\\}"; // 日期路径占位符

	/**
	 * 随机获取UUID字符串(无中划线)
	 * 
	 * @Title: getUUID
	 * @Description: TODO
	 * @return
	 * @author: MyLife
	 * @date: 2012-11-21下午3:37:13
	 */
	public static String getUUID() {
		String uuid = UUID.randomUUID().toString();
		return uuid.substring(0, 8) + uuid.substring(9, 13)
				+ uuid.substring(14, 18) + uuid.substring(19, 23)
				+ uuid.substring(24);
	}

	/**
	 * 获取实际路径
	 * 
	 * @Title: getPreparedStatementPath
	 * @Description: TODO
	 * @param path
	 *            上传路径
	 * @return 实际路径
	 * @author: MyLife
	 * @date: 2012-11-28下午6:08:50
	 */
	public static String getPreparedStatementPath(String path) {
		if (StringUtils.isEmpty(path)) {
			return null;
		}
		StringBuffer uuidStringBuffer = new StringBuffer();
		Matcher uuidMatcher = Pattern.compile(PATH_PREPARED_STATEMENT_UUID)
				.matcher(path);
		while (uuidMatcher.find()) {
			uuidMatcher.appendReplacement(uuidStringBuffer,
					CommonUtil.getUUID());
		}
		uuidMatcher.appendTail(uuidStringBuffer);

		StringBuffer dateStringBuffer = new StringBuffer();
		Matcher dateMatcher = Pattern.compile(PATH_PREPARED_STATEMENT_DATE)
				.matcher(uuidStringBuffer.toString());
		while (dateMatcher.find()) {
			String dateFormate = "yyyyMM";
			Matcher dateFormatMatcher = Pattern.compile("\\(\\w+\\)").matcher(
					dateMatcher.group());
			if (dateFormatMatcher.find()) {
				String dateFormatMatcherGroup = dateFormatMatcher.group();
				dateFormate = dateFormatMatcherGroup.substring(1,
						dateFormatMatcherGroup.length() - 1);
			}
			dateMatcher.appendReplacement(dateStringBuffer,
					new SimpleDateFormat(dateFormate).format(new Date()));
		}
		dateMatcher.appendTail(dateStringBuffer);

		return dateStringBuffer.toString();
	}

	/**
	 * 根据结构名，获取当前分类从父到子的结构字符串数组
	 * 
	 * @Title: getParamsByStructName
	 * @Description: TODO
	 * @param structName
	 * @return
	 * @author: MyLife
	 * @date: 2013-1-27下午4:34:30
	 */
	public static String[] getParamsByStructName(String structName) {
		if (StringUtils.isBlank(structName))
			return null;
		if (structName.indexOf("-") < 0) {
			String[] str = new String[1];
			str[0] = structName;
		}
		String[] arr = structName.trim().split("-");
		String[] result = new String[arr.length];
		for (int i = 0; i < arr.length; i++) {
			if (i == 0) {
				result[i] = arr[i];
			} else {
				result[i] = result[i - 1] + "-" + arr[i];
			}
		}
		return result;
	}

	/**
	 * 获取系统日志资源文件中的键所对应的值
	 * 
	 * @MethodName getSystemLogByResourceKey
	 * @Description TODO
	 * @author chengui
	 * @date 2013-1-14 上午10:38:09
	 * @param key
	 * @return
	 */
	public static String getSystemLogByResourceKey(String key) {
		// 获取系统默认的国家/语言环境
		Locale locale = Locale.getDefault();
		// 根据指定的国家/语言环境加载资源文件
		ResourceBundle bundle = ResourceBundle.getBundle("systemLog", locale);
		// 获取资源文件中的key的value值
		return bundle.getString(key);
	}

	/**
	 * 格式化消息中占位符字符串。占位符格式{n}，从n=0开始依次类推，如：{0},{1},{2}...{n}
	 * 
	 * @MethodName formatMsg
	 * @Description TODO
	 * @author chengui
	 * @date 2013-1-14 上午10:40:29
	 * @param pattern
	 * @param arguments
	 * @return
	 */
	public static String formatMsg(String pattern, Object[] arguments) {
		return MessageFormat.format(pattern, arguments);
	}

	public static void main(String[] args) {
		String a = getSystemLogByResourceKey("systemLog.test");
		String b = formatMsg(a, new Object[] { 123, 999 });
		System.out.println(b);
	}
	
	
	 /**
	     * 
	     * 使用反射根据属性名称获取属性值
	     * 
	     * 
	     * 
	     * @param fieldName
	     *            属性名称
	     * 
	     * @param o
	     *            操作对象
	     * 
	     * @return Object 属性值
	     */
	 
	    public static  Object getFieldValueByName(String fieldName, Object o) {
	        try {
	            String firstLetter = fieldName.substring(0, 1).toUpperCase();
	            String getter = "get" + firstLetter + fieldName.substring(1);
	            Method method = o.getClass().getMethod(getter, new Class[] {});
	            Object value = method.invoke(o, new Object[] {});
	            return value;
	        } catch (Exception e) {
	            System.out.println("属性不存在");
	            return null;
	        }
	    }
	    
	    public static String formartPhone(String phone){
	    	if(null!=phone && !StringUtils.isEmpty(phone)){
	    		 int between =phone.length()/2;
		    	 String mobile = phone.substring(0, between-2) + 
	             "****" + 
	             phone.substring(between+2, phone.length());
		    	 phone=mobile;
	    	}
	    	return phone;
	    }
}
