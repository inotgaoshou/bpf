
package com.remind.bpf.common.reflect;

import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;


/**
 * ReflectUtils
 * 
 * <P>
 * 提供常用的反射工具，可以在执行器通过获取ClassLoader，而对一个Class的字段、方法、构造器进行动态调用。
 * </P>
 * 
 * @version 0.0.1
 */
public abstract class ReflectUtils
{
	// ----------------------------------------------------- Properties

	/**
	 * Log4j
	 */
	private static final Log log = LogFactory.getLog( ReflectUtils.class );

	// ----------------------------------------------------- Constructors

	/**
	 * 隐藏构造函数防止实例化
	 */
	private ReflectUtils()
	{
	}

	// ----------------------------------------------------- Methods

	/**
	 * 获取默认的ClassLoader
	 * 
	 * @return ClassLoader类加载器
	 */
	public static ClassLoader getClassLoader()
	{
		ClassLoader classLoader = null;

		try
		{
			classLoader = Thread.currentThread().getContextClassLoader();
		}
		catch ( Throwable ex )
		{
		}

		if ( classLoader == null )
		{
			classLoader = ReflectUtils.class.getClassLoader();
		}

		return classLoader;
	}

	/**
	 * 由类名称获取正在运行的Java应用程序中的类和接口
	 * 
	 * @param className 类名称
	 * 
	 * @return 类的Class对象
	 * 
	 * @throws ClassNotFoundException 没有找到类
	 */
	public static Class forName( String className ) throws ClassNotFoundException
	{
		return forName( className, getClassLoader() );
	}

	/**
	 * 从指定的类加载器中。加载指定类名称的正在运行的Java应用程序中的类和接口
	 * 
	 * @param className 类名称
	 * @param classLoader 类加载器
	 * 
	 * @return 类的Class对象
	 * 
	 * @throws ClassNotFoundException 没有找到类
	 */
	public static Class forName( String className, ClassLoader classLoader ) throws ClassNotFoundException
	{
		if ( className == null )
		{
			throw new NullPointerException( "load a Class forName, the class name is null" );
		}

		Class clazz = null;

		try
		{
			clazz = Class.forName( className );
		}
		catch ( Throwable ex )
		{
		}

		if ( clazz != null )
		{
			return clazz;
		}

		ClassLoader classLoaderToUse = classLoader;

		if ( classLoaderToUse == null )
		{
			classLoaderToUse = getClassLoader();
		}

		return classLoaderToUse.loadClass( className );
	}

	/**
	 * 从当前的类加载器中获取对当前路径下某资源的字节流
	 * 
	 * @param srcPath 资源路径
	 * 
	 * @return 资源字节流
	 */
	public static InputStream getResource( String srcPath )
	{
		if ( srcPath == null )
		{
			throw new NullPointerException( "load resource by class loader, the src file path is null" );
		}

		InputStream in = ReflectUtils.class.getResourceAsStream( srcPath );

		if ( in == null )
		{
			in = getClassLoader().getResourceAsStream( srcPath );
		}

		return in;
	}

	/**
	 * 从JavaBean规则类中获取指定属性的Setter方法
	 * 
	 * @param clazz JavaBean类型
	 * @param property 属性名
	 * @param propertyClazz 属性类型
	 * 
	 * @return 指定属性的Setter方法
	 */
	public static Method getSetter( Class clazz, String property, Class propertyClazz )
	{
		if ( clazz == null )
		{
			throw new NullPointerException( "get a javabean instance setter method, the class is null" );
		}

		if ( property == null )
		{
			throw new NullPointerException( "get a javabean instance setter method, the property is null" );
		}

		final String methodName = "set" + StringUtils.capitalize( property );

		Method setMethod = null;

		try
		{
			setMethod = clazz.getMethod( methodName, new Class[] { propertyClazz } );
		}
		catch ( Exception e )
		{
		}

		if ( setMethod == null )
		{
			for ( Method eachMethod : clazz.getMethods() )
			{
				if ( eachMethod.getName().equals( methodName ) && eachMethod.getParameterTypes().length == 1 )
				{
					setMethod = eachMethod;
				}
			}
		}

		if ( setMethod == null )
		{
			throw new IllegalStateException( "can't get a javabean instance setter method, as the property is:" + property );
		}

		return setMethod;
	}

	/**
	 * 从JavaBean规则类中获取指定属性的Getter方法
	 * 
	 * @param clazz JavaBean类型
	 * @param property 属性名
	 * 
	 * @return Method对象
	 */
	public static Method getGetter( Class clazz, String property )
	{
		if ( clazz == null )
		{
			throw new NullPointerException( "get a javabean instance getter method, the class is null" );
		}

		if ( property == null )
		{
			throw new NullPointerException( "get a javabean instance getter method, the property is null" );
		}

		String methodName = "";

		if ( clazz.getName().equals( "boolean" ) )
		{
			methodName = "is" + StringUtils.capitalize( property );
		}
		else
		{
			methodName = "get" + StringUtils.capitalize( property );
		}

		try
		{
			return clazz.getMethod( methodName );
		}
		catch ( Exception e )
		{
			if ( log.isErrorEnabled() )
			{
				log.error( "can't get a javabean instance getter method, as the property is:" + property );
			}

			throw new IllegalStateException( "can't get a javabean instance getter method" );
		}
	}

	/**
	 * 按照继承树递归查找方法
	 * 
	 * @param type 类型
	 * @param name 方法名
	 * @param paramTypes 参数
	 * 
	 * @return 反射Method对象
	 */
	public static Method findMethod( Class type, String name, Class[] paramTypes )
	{
		if ( type == null )
		{
			throw new NullPointerException( "get a class type method, the class type is null." );
		}

		if ( name == null )
		{
			throw new NullPointerException( "get a class type method, the method name is null." );
		}

		try
		{
			Method method = type.getMethod( name, paramTypes );

			if ( method != null )
			{
				return method;
			}
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}

		Class searchType = type;

		while ( !Object.class.equals( searchType ) && searchType != null )
		{
			Method[] methods = ( searchType.isInterface() ? searchType.getMethods() : searchType.getDeclaredMethods() );

			for ( int i = 0; i < methods.length; i++ )
			{
				Method method = methods[i];

				if ( name.equals( method.getName() ) && Arrays.equals( paramTypes, method.getParameterTypes() ) )
				{
					return method;
				}
			}

			searchType = searchType.getSuperclass();
		}

		throw new IllegalStateException( "can't find the method of :" + name + ", for type of:" + type );
	}

	/**
	 * 获取方法原注释
	 * 
	 * @param clazz
	 * @param method
	 * @param paramTypes
	 * @param annotationClass
	 * @return
	 */
	public static Annotation findMethodAnnotation( Class clazz, String method, Class[] paramTypes, Class annotationClass )
	{
		Method m = findMethod( clazz, method, paramTypes );

		if ( m == null )
		{
			return null;
		}

		return m.getAnnotation( annotationClass );
	}

	/**
	 * 强制执行一个类型中的一个方法
	 * 
	 * @param target 执行对象
	 * @param method 预执行方法
	 * 
	 * @return 方法返回值
	 */
	public static Object invoke( Object target, Method method )
	{
		return invoke( target, method, null );
	}

	/**
	 * 强制执行一个类型中的一个方法
	 * 
	 * @param target 执行对象
	 * @param method 预执行方法
	 * @param args 方法传入参数
	 * 
	 * @return 方法返回值
	 */
	public static Object invoke( Object target, Method method, Object[] args )
	{
		try
		{
			return method.invoke( target, args );
		}
		catch ( IllegalAccessException e )
		{
			handleReflectionException( e );

			throw new IllegalStateException( e.getMessage(), e );
		}
		catch ( InvocationTargetException e )
		{
			handleReflectionException( e );

			throw new IllegalStateException( e.getMessage(), e );
		}
	}

	/**
	 * 处理反射机制Method异常
	 * 
	 * @param e 发生的异常
	 */
	protected static void handleReflectionException( Exception e )
	{
		if ( e instanceof NoSuchMethodException )
		{
			throw new IllegalStateException( "reflect invoke method has a error:" + e.getMessage(), e );
		}

		if ( e instanceof IllegalAccessException )
		{
			throw new IllegalStateException( "reflect invoke method has a error:" + e.getMessage(), e );
		}

		if ( e instanceof InvocationTargetException )
		{
			handleInvocationTargetException( (InvocationTargetException) e );
		}

		throw new IllegalStateException( "reflect invoke method has a error:" + e.getMessage(), e );
	}

	/**
	 * 处理反射机制调用异常
	 * 
	 * @param e 发生的异常
	 */
	protected static void handleInvocationTargetException( InvocationTargetException e )
	{
		if ( e.getTargetException() instanceof RuntimeException )
		{
			throw (RuntimeException) e.getTargetException();
		}

		if ( e.getTargetException() instanceof Error )
		{
			throw (Error) e.getTargetException();
		}

		throw new IllegalStateException( "reflect code has a error[" + e.getTargetException().getClass().getName() + "]:" + e.getTargetException().getMessage() );
	}
}
