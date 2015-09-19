package org.excel2db.read;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * Bean/Map 转换工具
 * 
 * @author zhaohui
 * 
 */
public class BeanUtil {
	private static Logger logger = Logger.getLogger(BeanUtil.class);
	private static final String CLASS = "class";

	/**
	 * 将map转成指定的对象
	 * 
	 * @param beanMap
	 *            map数据
	 * @param clazz
	 *            指定的类对象
	 * @return
	 */
	public static <T> T reverse(Map<String, Object> beanMap, Class<T> clazz) {
		T bean = getBean(clazz);
		try {
			PropertyDescriptor[] ps = Introspector.getBeanInfo(clazz)
					.getPropertyDescriptors();
			for (PropertyDescriptor propertyDescriptor : ps) {
				String propertyName = propertyDescriptor.getName();
				if (propertyName != null && !propertyName.equals(CLASS)) {
					Method setter = propertyDescriptor.getWriteMethod();
					Object value = beanMap.get(propertyName);
					if (setter != null && value != null) {
						setter.invoke(bean, value);
					}
				}
			}
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		return bean;
	}

	@SuppressWarnings("unchecked")
	private static <T> T getBean(Class<T> _class) {
		try {
			return (T) Class.forName(_class.getName()).newInstance();
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}
}
