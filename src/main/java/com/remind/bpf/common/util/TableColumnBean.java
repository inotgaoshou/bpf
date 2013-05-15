
package com.remind.bpf.common.util;

import java.util.Date;

/**
 * TableModelDesc
 * 
 * <P>
 * 表格模型描述
 * </P>
 * 
 * @version 0.0.1
 */
public class TableColumnBean
{
	// ----------------------------------------------------- Properties

	private String fields;
	private String type;
	private Integer length;
	private Integer scale;
	private String defaultValue;
	private boolean need = true;

	// ----------------------------------------------------- Constructors

	// ----------------------------------------------------- Methods

	public String getJavaTypeDesc()
	{
		return getJavaType().getName();
	}

	@ SuppressWarnings( "unchecked" )
	public Class getJavaType()
	{
		if ( type.equals( "VARCHAR2" ) )
		{
			return String.class;
		}
		else if ( type.equals( "DATE" ) )
		{
			return Date.class;
		}
		else if ( type.equals( "CLOB" ) )
		{
			return String.class;
		}
		else if ( type.equals( "NUMBER" ) )
		{
			if ( scale != 0 )
			{
				return Double.class;
			}

			if ( length >= 10 )
			{
				return Long.class;
			}
		}

		return Integer.class;
	}

	public String getFields()
	{
		return fields;
	}

	public void setFields( String fields )
	{
		this.fields = fields;
	}

	public String getType()
	{
		return type;
	}

	public void setType( String type )
	{
		this.type = type;
	}

	public Integer getLength()
	{
		return length;
	}

	public void setLength( Integer length )
	{
		this.length = length;
	}

	public Integer getScale()
	{
		return scale;
	}

	public void setScale( Integer scale )
	{
		this.scale = scale;
	}

	public void enable()
	{
		this.need = true;
	}

	public void disable()
	{
		this.need = false;
	}

	public boolean isNeed()
	{
		return need;
	}

	public String getDefaultValue()
	{
		return defaultValue;
	}
}
