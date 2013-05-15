
package com.remind.bpf.common.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import com.remind.bpf.common.reflect.ReflectUtils;

/**
 * PropertiesManager
 * 
 * <P>
 * 属性文件管理器
 * </P>
 * 
 * @version 0.0.1
 */
public final class PropertiesManagerUtil
{
	// ----------------------------------------------------- Properties

	/**
	 * Log4J日志
	 */
	private final static Log log = LogFactory.getLog( PropertiesManagerUtil.class );
	private static final String PROPERTY_CFG = "jdbc.properties";

	/**
	 * 查询配置文件属性管理对象
	 * 
	 * @return
	 */
	public static PropertiesManager getPropertiesManager()
	{
		String[] properties = getPropertyConfigurer();
		PropertiesManager pm = new PropertiesManager( ReflectUtils.getResource( properties[0] ) );

		return pm;
	}

	/**
	 * 查询配置文件
	 * 
	 * @return
	 */
	protected static String[] getPropertyConfigurer()
	{
		String config = System.getProperty( "bpf.config" );

		// 1.如果启动参数中包含-Dbpf.config=

		if ( StringUtils.hasText( config ) )
		{
			String[] result = new String[] { config };

			for ( String e : result )
			{
				if ( log.isDebugEnabled() )
				{
					log.info( "parse load property configurer:" + e );
				}
			}

			return result;
		}
		else
		{
			if ( log.isDebugEnabled() )
			{
				log.info( "parse load property configurer as default:" + PROPERTY_CFG );
			}

			return new String[] { PROPERTY_CFG };
		}
	}

	
}
