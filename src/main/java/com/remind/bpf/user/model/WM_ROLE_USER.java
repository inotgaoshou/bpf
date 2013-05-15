/**
 * Copyright (c) 2008 Asiainfo Technologies(China),Inc.
 *
 * FileName: WM_ROLE_USER.java
 *
 * Description: select * from WM_ROLE_USER
 *
 * Created: python 2013-05-14
 * 
 * |--------------------------------------------------History---------------------------------------------------|
 * |                                                                                                            |
 * |-----Author-----|-----Date-----|----Version----|----------------------------Desc----------------------------| 
 * || 
 * |------------------------------------------------------------------------------------------------------------|
 */
package com.remind.bpf.user.model;

/**
 * WM_ROLE_USER
 * 
 * Create From SQL: select * from WM_ROLE_USER
 * 
 * @author python 2013-05-14
 * @version 0.0.1
 */
public class WM_ROLE_USER extends com.remind.bpf.common.model.AbstractTableBean
{
	// ----------------------------------------------------- Properties
	
	private java.lang.Long id;
	private java.lang.Long role_id;
	private java.lang.Long user_id;
	private java.util.Date created;
	private java.lang.Long created_by;
	private java.lang.String status;
	private java.util.Date last_upd;
	private java.lang.Long last_upd_by;
	private java.lang.String remarks;
	
	// ----------------------------------------------------- Constructors

	// ----------------------------------------------------- Methods
	
	public java.lang.Long getId()
	{
		return id;
	}

	public void setId( java.lang.Long id )
	{
		this.id = id;
	}
	
	public java.lang.Long getRole_id()
	{
		return role_id;
	}

	public void setRole_id( java.lang.Long role_id )
	{
		this.role_id = role_id;
	}
	
	public java.lang.Long getUser_id()
	{
		return user_id;
	}

	public void setUser_id( java.lang.Long user_id )
	{
		this.user_id = user_id;
	}
	
	public java.util.Date getCreated()
	{
		return created;
	}

	public void setCreated( java.util.Date created )
	{
		this.created = created;
	}
	
	public java.lang.Long getCreated_by()
	{
		return created_by;
	}

	public void setCreated_by( java.lang.Long created_by )
	{
		this.created_by = created_by;
	}
	
	public java.lang.String getStatus()
	{
		return status;
	}

	public void setStatus( java.lang.String status )
	{
		this.status = status;
	}
	
	public java.util.Date getLast_upd()
	{
		return last_upd;
	}

	public void setLast_upd( java.util.Date last_upd )
	{
		this.last_upd = last_upd;
	}
	
	public java.lang.Long getLast_upd_by()
	{
		return last_upd_by;
	}

	public void setLast_upd_by( java.lang.Long last_upd_by )
	{
		this.last_upd_by = last_upd_by;
	}
	
	public java.lang.String getRemarks()
	{
		return remarks;
	}

	public void setRemarks( java.lang.String remarks )
	{
		this.remarks = remarks;
	}
	
	public String getTableName()
	{
		return "WM_ROLE_USER";
	}
	
	public String getQueryId()
	{
		return "com.remind.bpf.user.model.wm_role_user.query_WM_ROLE_USER";
	}
	
	public String getInsertId()
	{
		return "com.remind.bpf.user.model.wm_role_user.insert_WM_ROLE_USER";
	}
	
	public String getUpdateAllId()
	{
		return "com.remind.bpf.user.model.wm_role_user.updateAll_WM_ROLE_USER";
	}
	
	public String getUpdateNotAllId()
	{
		return "com.remind.bpf.user.model.wm_role_user.updateNotNull_WM_ROLE_USER";
	}
}
