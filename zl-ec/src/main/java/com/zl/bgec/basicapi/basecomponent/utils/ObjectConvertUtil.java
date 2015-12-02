package com.zl.bgec.basicapi.basecomponent.utils;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 * 对象转换工具
 * @author xs
 *
 */
public class ObjectConvertUtil {
	
	/**
	 * 日志记录器(log4j)
	 */
	private static final Logger log = LoggerFactory.getLogger(ObjectConvertUtil.class);

	/**
	 * 将VO对象转成PO(包含null值)
	 * @param vo
	 * @param po
	 * @return
	 * @throws Exception
	 */
	public static Object convertVoToPo(Object vo,Object po) throws Exception {
		if (null == vo)
			return vo;

		Class<?> classType = vo.getClass();
		Field[] fields = classType.getDeclaredFields();

		int length = fields.length;

		for (int i = 0; i < length; i++) {

			String fieldName = fields[i].getName();
			Object fieldValue = getFieldValue(vo, fieldName);
			setFieldValueByFieldType(po, fieldName, fieldValue);
		}
		return po;
	}
	public static Object convertPoToVo(Object po,Object vo) throws Exception {
		if (null == po)
			return po;
		
		Class<?> classType = po.getClass();
		Field[] fields = classType.getDeclaredFields();
		
		int length = fields.length;
		
		for (int i = 0; i < length; i++) {
			
			String fieldName = fields[i].getName();
			Object fieldValue = getFieldValue(po, fieldName);
			setFieldValueByFieldType(vo, fieldName, fieldValue);
		}
		return vo;
	}
	/**
	 * 将VO对象转成PO（不包含null值）
	 * @param vo
	 * @param po
	 * @return
	 * @throws Exception
	 */
	public static Object convertVoToPoNoNull(Object vo,Object po) throws Exception {
		if (null == vo)
			return vo;

		Class<?> classType = vo.getClass();
		Field[] fields = classType.getDeclaredFields();

		int length = fields.length;

		for (int i = 0; i < length; i++) {

			String fieldName = fields[i].getName();
			Object fieldValue = getFieldValue(vo, fieldName);
			if(fieldValue!=null){
				setFieldValueByFieldType(po, fieldName, fieldValue);
			}
		}
		return po;
	}
	/**
	 * 直接设置对象属性值, 无视private/protected修饰符, 不经过setter函数.
	 */
	public static void setFieldValueByFieldType(final Object object, final String fieldName, final Object value) {
		Field field = getDeclaredField(object, fieldName);

//		if (field == null) {
//			throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + object + "]");
//		}
		if(null!=field){
			setFieldValue(object, fieldName, value);
		}
	}
	
	/**
	 * 直接设置对象属性值, 无视private/protected修饰符, 不经过setter函数.
	 */
	public static void setFieldValue(final Object object, final String fieldName, final Object value) {
		Field field = getDeclaredField(object, fieldName);

//		if (field == null) {
//			throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + object + "]");
//		}
		
		if(null!=field){
			makeAccessible(field);
			try {
				field.set(object, value);
			} catch (IllegalAccessException e) {
				log.error("不可能抛出的异常:{}", e.getMessage());
			}
		}
	}
	
	/**
	 * 直接读取对象属性值, 无视private/protected修饰符, 不经过getter函数.
	 */
	public static Object getFieldValue(final Object object, final String fieldName) {
		Field field = getDeclaredField(object, fieldName);

		if (field == null) {
			throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + object + "]");
		}

		makeAccessible(field);

		Object result = null;
		try {
			result = field.get(object);
		} catch (IllegalAccessException e) {
			log.error("不可能抛出的异常{}", e.getMessage());
		}
		return result;
	}
	
	/**
	 * 循环向上转型, 获取对象的DeclaredField.
	 * 
	 * 如向上转型到Object仍无法找到, 返回null.
	 */
	public static Field getDeclaredField(final Object object, final String fieldName) {
		Assert.notNull(object, "object不能为空");
		Assert.hasText(fieldName, "fieldName");
		for (Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass
				.getSuperclass()) {
			try {
				return superClass.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e) {//NOSONAR
				// Field不在当前类定义,继续向上转型
			}
		}
		return null;
	}
	
	/**
	 * 强行设置Field可访问.
	 */
	protected static void makeAccessible(final Field field) {
		if (!Modifier.isPublic(field.getModifiers()) || !Modifier.isPublic(field.getDeclaringClass().getModifiers())) {
			field.setAccessible(true);
		}
	}
}
