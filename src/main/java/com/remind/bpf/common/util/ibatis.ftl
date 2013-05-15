<?xml version="1.0" encoding="UTF-8"?>

<!DOCTYPE sqlMap PUBLIC "-//iBATIS.com//DTD SQL Map 2.0//EN" "http://ibatis.apache.org/dtd/sql-map-2.dtd">

<sqlMap namespace="${bean_pkg}.${bean_type?lower_case}">
	
	<typeAlias alias="${bean_type?lower_case}" type="${bean_pkg}.${bean_type}" />
	
	<insert id="insert_${bean_type}" parameterClass="${bean_type?lower_case}">
		<![CDATA[
			INSERT INTO ${bean_type?upper_case}(<#assign i = 0><#list bean_property as p>${p.fields?upper_case}<#if i != (bean_property?size - 1)>, </#if><#assign i = i + 1></#list>) 
			VALUES( <#assign i = 0><#list bean_property as p><#if p.fields?lower_case != "created">#${p.fields?lower_case}#<#if i != (bean_property?size - 1)>, </#if><#else>current_date, </#if><#assign i = i + 1></#list> )
		]]>
	</insert>
		
	<update id="updateAll_${bean_type}" parameterClass="${bean_type?lower_case}">
		<![CDATA[ 
			UPDATE ${bean_type?upper_case} SET LAST_UPD = current_date,
			<#assign i = 0><#list bean_property as p><#if p.fields?lower_case != "last_upd" && p.fields?lower_case != "created" && p.fields?lower_case != "created_by">${p.fields?upper_case} = #${p.fields?lower_case}#<#if i != (bean_property?size - 1)>, </#if></#if><#assign i = i + 1></#list> 
			WHERE 1 = 1<#list bean_pk as p> AND ${p?upper_case} = #${p?lower_case}#</#list>
		]]>
	</update>
	
	<update id="updateNotNull_${bean_type}" parameterClass="${bean_type?lower_case}">
		<![CDATA[ UPDATE ${bean_type?upper_case} SET LAST_UPD = current_date ]]>
			<#assign i = 0><#list bean_property as p><#if p.fields?lower_case != "last_upd">
			<isNotNull property="${p.fields?lower_case}">
				<![CDATA[, ${p.fields?upper_case} = #${p.fields?lower_case}#]]>
			</isNotNull></#if>
			<#assign i = i + 1></#list>
		<![CDATA[ WHERE 1 = 1<#list bean_pk as p> AND ${p?upper_case} = #${p?lower_case}#</#list> ]]>
	</update>
	
	<select id="query_${bean_type}" resultClass="${bean_type?lower_case}" parameterClass="${bean_type?lower_case}">
		<![CDATA[ SELECT <#assign i = 0><#list bean_property as p>${p.fields?upper_case}<#if i != (bean_property?size - 1)>, </#if><#assign i = i + 1></#list> FROM ${bean_type?upper_case} WHERE 1 = 1 ]]>
		<dynamic>
			<#list bean_property as p> 
			<isNotNull prepend="AND" property="${p.fields?lower_case}">
				<![CDATA[ ${p.fields?upper_case} = #${p.fields?lower_case}# ]]>
			</isNotNull>
			</#list>
		</dynamic>
	</select>
    
</sqlMap>