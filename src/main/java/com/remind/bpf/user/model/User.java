
package com.remind.bpf.user.model;


import java.util.Date;

import com.remind.bpf.common.model.AbstractTableBean;

public class User extends AbstractTableBean
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// ----------------------------------------------------- Properties
	
	private String user_name;
	private String login_name;
	private String login_diyname;
	private String password;
	private String email;
	private String msisdn;
	private String telphone;
	private Long def_menu_id;
	private String company_name;
	private Integer prov_code;
	private Integer city_code;
	private Integer type;
	private Integer grade;
	private String locked;
	private Integer error_count;
	private Date login_date;
	// ----------------------------------------------------- Constructors

	// ----------------------------------------------------- Methods
	
	public String getUser_name()
	{
		return user_name;
	}

	public void setUser_name( String user_name )
	{
		this.user_name = user_name;
	}
	
	public String getLogin_name()
	{
		return login_name;
	}

	public void setLogin_name( String login_name )
	{
		this.login_name = login_name;
	}
	
	public String getLogin_diyname()
	{
		return login_diyname;
	}

	public void setLogin_diyname( String login_diyname )
	{
		this.login_diyname = login_diyname;
	}
	
	public String getPassword()
	{
		return password;
	}

	public void setPassword( String password )
	{
		this.password = password;
	}
	
	public String getEmail()
	{
		return email;
	}

	public void setEmail( String email )
	{
		this.email = email;
	}
	
	public String getMsisdn()
	{
		return msisdn;
	}

	public void setMsisdn( String msisdn )
	{
		this.msisdn = msisdn;
	}
	
	public String getTelphone()
	{
		return telphone;
	}

	public void setTelphone( String telphone )
	{
		this.telphone = telphone;
	}
	
	public Long getDef_menu_id()
	{
		return def_menu_id;
	}

	public void setDef_menu_id( Long def_menu_id )
	{
		this.def_menu_id = def_menu_id;
	}
	
	public String getCompany_name()
	{
		return company_name;
	}

	public void setCompany_name( String company_name )
	{
		this.company_name = company_name;
	}
	
	public Integer getProv_code()
	{
		return prov_code;
	}

	public void setProv_code( Integer prov_code )
	{
		this.prov_code = prov_code;
	}
	
	public Integer getCity_code()
	{
		return city_code;
	}

	public void setCity_code( Integer city_code )
	{
		this.city_code = city_code;
	}
	
	public Integer getType()
	{
		return type;
	}

	public void setType( Integer type )
	{
		this.type = type;
	}
	
	public Integer getGrade()
	{
		return grade;
	}

	public void setGrade( Integer grade )
	{
		this.grade = grade;
	}
	
	public String getLocked()
	{
		return locked;
	}

	public void setLocked( String locked )
	{
		this.locked = locked;
	}
	
	public Integer getError_count()
	{
		return error_count;
	}

	public void setError_count( Integer error_count )
	{
		this.error_count = error_count;
	}
	
	public Date getLogin_date()
	{
		return login_date;
	}

	public void setLogin_date( Date login_date )
	{
		this.login_date = login_date;
	}
	
	
}
