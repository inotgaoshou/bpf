
package com.remind.bpf.common.model;

import java.io.Serializable;
import java.util.Date;


public abstract class AbstractBean implements Serializable
{
	// ----------------------------------------------------- Properties

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DB行ID
	 */
	private Long row_id;

	/**
	 * ID
	 */
	private Long id;

	/**
	 * 创建时间
	 */
	private Date created;

	/**
	 * 创建用户id
	 */
	private Long created_by;

	/**
	 * 状态
	 */
	private String status;

	/**
	 * 最后修改时间
	 */
	private Date last_upd;

	/**
	 * 最后修改用户id
	 */
	private Long last_upd_by;

	/**
	 * 备注
	 */
	private String remarks;

	// ----------------------------------------------------- Constructors

	// ----------------------------------------------------- Methods

	// ----------------------------------------------------- JavaBean Methods

	public Long getRow_id()
	{
		return row_id;
	}

	public void setRow_id( Long rowId )
	{
		row_id = rowId;
	}

	public void setId( Long id )
	{
		this.id = id;
	}

	public Long getId()
	{
		return id;
	}

	public Date getCreated()
	{
		return created;
	}

	public void setCreated( Date created )
	{
		this.created = created;
	}

	public Long getCreated_by()
	{
		return created_by;
	}

	public void setCreated_by( Long createdBy )
	{
		this.created_by = createdBy;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus( String status )
	{
		this.status = status;
	}

	public Date getLast_upd()
	{
		return last_upd;
	}

	public void setLast_upd( Date lastUpdate )
	{
		this.last_upd = lastUpdate;
	}

	public Long getLast_upd_by()
	{
		return last_upd_by;
	}

	public void setLast_upd_by( Long lastUpdateBy )
	{
		this.last_upd_by = lastUpdateBy;
	}

	public String getRemarks()
	{
		return remarks;
	}

	public void setRemarks( String remarks )
	{
		this.remarks = remarks;
	}

	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( created_by == null ) ? 0 : created_by.hashCode() );
		result = prime * result + ( ( last_upd_by == null ) ? 0 : last_upd_by.hashCode() );
		result = prime * result + ( ( remarks == null ) ? 0 : remarks.hashCode() );
		result = prime * result + ( ( status == null ) ? 0 : status.hashCode() );
		return result;
	}

	public boolean equals( Object obj )
	{
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( getClass() != obj.getClass() )
			return false;
		final AbstractBean other = (AbstractBean) obj;
		if ( created_by == null )
		{
			if ( other.created_by != null )
				return false;
		}
		else if ( !created_by.equals( other.created_by ) )
			return false;
		if ( last_upd_by == null )
		{
			if ( other.last_upd_by != null )
				return false;
		}
		else if ( !last_upd_by.equals( other.last_upd_by ) )
			return false;
		if ( remarks == null )
		{
			if ( other.remarks != null )
				return false;
		}
		else if ( !remarks.equals( other.remarks ) )
			return false;
		if ( status == null )
		{
			if ( other.status != null )
				return false;
		}
		else if ( !status.equals( other.status ) )
			return false;

		return true;
	}

	public Date getLastModified()
	{
		if ( last_upd != null )
		{
			return last_upd;
		}

		return created;
	}
}
