
package com.remind.bpf.common.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * JavaBeanUtils
 * 
 * <P>
 * 提供对JavaBean规则的支持，可以在执行期动态实现对一个符合JavaBean规则的实例进行IOC的注入，以及获取实例的相应属性。
 * </P>
 * 
 * @version 0.0.1
 */
public abstract class JavaBeanUtils
{
	// ----------------------------------------------------- Properties

	/**
	 * Log4j
	 */
	private static Log log = LogFactory.getLog( JavaBeanUtils.class );

	// ----------------------------------------------------- Constructors

	/**
	 * 隐藏构造函数防止实例化
	 */
	private JavaBeanUtils()
	{
	}

	// ----------------------------------------------------- Methods

	/**
	 * 创建一个对象实例
	 * 
	 * @param classType 对象类型
	 * 
	 * @return 对象实例
	 */
	public static Object newInstance( String classType )
	{
		return newInstance( classType, new HashMap() );
	}

	/**
	 * 创建一个对象实例，并且初始化其属性
	 * 
	 * @param classType 对象类型
	 * @param properties 属性Map集
	 * 
	 * @return 对象实例
	 */
	public static Object newInstance( String classType, Map properties )
	{
		if ( classType == null )
		{
			throw new NullPointerException( "build a new instance, the classType is null" );
		}

		Object object = null;

		try
		{
			object = ReflectUtils.forName( classType ).newInstance();
		}
		catch ( Exception e )
		{
			ReflectUtils.handleReflectionException( e );
		}

		setProperties( object, properties );

		return object;
	}

	/**
	 * 设置对象属性
	 * 
	 * @param object 目标对象
	 * @param properties 属性集
	 */
	public static void setProperties( Object object, Map properties )
	{
		if ( object == null )
		{
			throw new NullPointerException( "set object property, the target object is null" );
		}

		if ( properties == null )
		{
			properties = new HashMap();
		}

		for ( Object property : properties.keySet() )
		{
			setProperty( object, (String) property, properties.get( property ) );
		}
	}

	/**
	 * 设置对象属性
	 * 
	 * @param object 目标对象
	 * @param property 属性名
	 * @param value 属性值
	 */
	public static void setProperty( Object object, String property, Object value )
	{
		if ( object == null || property == null || value == null )
		{
			if ( log.isDebugEnabled() )
			{
			//	log.debug( "set object property the argument avoid:" + object + "," + property + "," + value );
			}
			return;
		}

		Method setMethod = null;

		try
		{
			setMethod = ReflectUtils.getSetter( object.getClass(), property, value.getClass() );
		}
		catch ( Exception e )
		{
			if ( log.isDebugEnabled() )
			{
				log.debug( e.getMessage() );
			}
		}

		if ( setMethod != null )
		{
			try
			{
				Class clazz = setMethod.getParameterTypes()[0];
				Object objValue = null;
				if (clazz.getName() == value.getClass().getName()) {
					objValue = value;
				} else {
					if (clazz.getName().equals("java.lang.Long")) {
						objValue = Long.parseLong(value.toString());
					} else if (clazz.getName().equals("java.lang.Double")) {
						objValue = Double.parseDouble(value.toString());
					} else if (clazz.getName().equals("java.lang.Integer")) {
						objValue = Integer.parseInt(value.toString());
					}
					 else if (clazz.getName().equals("java.util.Date")) {
						 try{
							objValue = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse( value.toString() );
						 }
						 catch(Exception e)
						 {
							 objValue = new SimpleDateFormat("yyyy-MM-dd").parse( value.toString() );
						 }
						}
					else {
						objValue = value;
					}
				}
				setMethod.invoke(object, new Object[] { objValue });
			}
			catch ( Exception e )
			{
				try
				{
					if ( value instanceof String )
					{
						Class[] parClazz = setMethod.getParameterTypes();

						if ( !( parClazz.length > 0 ) )
						{
							if ( log.isDebugEnabled() )
							{
								log.debug( "setter method argument length can't be zero" );
							}
						}

						value = parClazz[0].getConstructor( String.class ).newInstance( value );
						setMethod.invoke( object, new Object[] { value } );
					}
				}
				catch ( Exception ee )
				{
					if ( log.isErrorEnabled() )
					{
						log.error( "set object property failed:" + e.getMessage() + ", argument:" + object + ","
								+ property + "," + value );
					}
				}
			}
		}
	}

	/**
	 * 获取对象属性
	 * 
	 * @param object 目标对象
	 * @param property 属性名
	 * 
	 * @return 属性值
	 */
	public static Object getProperty( Object object, String property )
	{
		if ( object == null )
		{
			throw new NullPointerException( "set object property, the target object is null" );
		}

		try
		{
			return ReflectUtils.getGetter( object.getClass(), property ).invoke( object, new Object[] {} );
		}
		catch ( Exception e )
		{
			if ( log.isErrorEnabled() )
			{
				log.error( "get object property value failed:" + e.getMessage() + ", argument:" + object + ","
						+ property );
			}
		}

		return null;
	}

	/**
	 * 获取对象所有属性
	 * 
	 * @param object 目标对象
	 * 
	 * @return 对象属性集
	 */
	@ SuppressWarnings( "unchecked" )
	public static Map getProperties( Object object )
	{
		if ( object == null )
		{
			throw new NullPointerException( "set object property, the target object is null" );
		}

		Map properties = new HashMap();

		for ( Field field : object.getClass().getDeclaredFields() )
		{
			try
			{
				Method getMethod = ReflectUtils.getGetter( object.getClass(), field.getName() );

				if ( getMethod != null )
				{
					Object result = getMethod.invoke( object, new Object[] {} );
					properties.put( field.getName(), result );
				}
			}
			catch ( Exception e )
			{
				if ( log.isErrorEnabled() )
				{
					log.error( "get object property value failed:" + e.getMessage() + ", argument:" + object );
				}
			}
		}

		return properties;
	}
	
	/**
	 * 获取对象所有属性，包括父类的属性
	 * 
	 * @param object 目标对象
	 * 
	 * @return 对象属性集
	 */
	@ SuppressWarnings( "unchecked" )
	public static Map getAllProperties( Object object )
	{
		if ( object == null )
		{
			throw new NullPointerException( "set object property, the target object is null" );
		}

		Map properties = new HashMap();

		for ( Field field : object.getClass().getDeclaredFields() )
		{
			try
			{
				Method getMethod = ReflectUtils.getGetter( object.getClass(), field.getName() );

				if ( getMethod != null )
				{
					Object result = getMethod.invoke( object, new Object[] {} );
					properties.put( field.getName(), result );
				}
			}
			catch ( Exception e )
			{
				if ( log.isErrorEnabled() )
				{
					log.error( "get object property value failed:" + e.getMessage() + ", argument:" + object );
				}
			}
		}
		//循环获取父类的字段
		Class superClass = object.getClass().getSuperclass();
		while ( superClass!=null )
		{
			for ( Field field : superClass.getDeclaredFields() )
			{
				try
				{
					Method getMethod = ReflectUtils.getGetter( object.getClass(), field.getName() );

					if ( getMethod != null )
					{
						Object result = getMethod.invoke( object, new Object[] {} );
						properties.put( field.getName(), result );
					}
				}
				catch ( Exception e )
				{
					if ( log.isErrorEnabled() )
					{
						log.error( "get object property value failed:" + e.getMessage() + ", argument:" + object );
					}
				}
			}
			superClass = superClass.getSuperclass();
		}
		

		return properties;
	}
}
