
package com.remind.bpf.common.util;

/**
 * SecuritySupport
 * 
 * <P>
 * 解密支持接口
 * </P>
 * 
 * @version 0.0.1
 */
public interface SecuritySupport
{
	// ----------------------------------------------------- Properties

	/**
	 * 加密字段模式
	 */
	public static final String ENCRYPT_REGEX = "<pwd>*</pwd>";

	/**
	 * 解密字段模式
	 */
	public static final String DECRYPT_REGEX = "<pwd>.+</pwd>";

	// ----------------------------------------------------- Methods

	/**
	 * 加密属性值
	 * 
	 * @param sval 源属性值
	 * 
	 * @return 加密属性值
	 */
	public String encryptProperty( String sval );

	/**
	 * 解密属性值
	 * 
	 * @param sval 源属性值
	 * 
	 * @return 解密属性值
	 */
	public String decryptProperty( String sval );
}
