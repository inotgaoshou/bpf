/**
 * Copyright (c) 2008 Asiainfo Technologies(China),Inc.
 *
 * FileName: ${bean_type}.java
 *
 * Description: ${bean_sql}
 *
 * Created: ${bean_author} ${bean_created}
 * 
 * |--------------------------------------------------History---------------------------------------------------|
 * |                                                                                                            |
 * |-----Author-----|-----Date-----|----Version----|----------------------------Desc----------------------------| 
 * || 
 * |------------------------------------------------------------------------------------------------------------|
 */
package ${bean_pkg};

/**
 * ${bean_type}
 * 
 * Create From SQL: ${bean_sql}
 * 
 * @author ${bean_author} ${bean_created}
 * @version 0.0.1
 */
public class ${bean_type} <#if bean_parent??>extends ${bean_parent}</#if>
{
	// ----------------------------------------------------- Properties
	
	<#list bean_property as p>
	<#if p.need>
	private ${p.javaTypeDesc} ${p.fields?lower_case};
	</#if>
	</#list>
	
	// ----------------------------------------------------- Constructors

	// ----------------------------------------------------- Methods
	
	<#list bean_property as p>
	<#if p.need>
	public ${p.javaTypeDesc} get${p.fields?lower_case?cap_first}()
	{
		return ${p.fields?lower_case};
	}

	public void set${p.fields?lower_case?cap_first}( ${p.javaTypeDesc} ${p.fields?lower_case} )
	{
		this.${p.fields?lower_case} = ${p.fields?lower_case};
	}
	
	</#if>
	</#list>
	public String getTableName()
	{
		return "${bean_type}";
	}
	
	public String getQueryId()
	{
		return "${bean_pkg}.${bean_type?lower_case}.query_${bean_type}";
	}
	
	public String getInsertId()
	{
		return "${bean_pkg}.${bean_type?lower_case}.insert_${bean_type}";
	}
	
	public String getUpdateAllId()
	{
		return "${bean_pkg}.${bean_type?lower_case}.updateAll_${bean_type}";
	}
	
	public String getUpdateNotAllId()
	{
		return "${bean_pkg}.${bean_type?lower_case}.updateNotNull_${bean_type}";
	}
}
