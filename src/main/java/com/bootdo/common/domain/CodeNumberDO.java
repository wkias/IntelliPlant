package com.bootdo.common.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author xuhui
 * @email 1992lcg@163.com
 * @date 2020-04-28 10:50:15
 */
public class CodeNumberDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//
	private String codeType;
	//
	private String codeNumber;

	/**
	 * 设置：
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：
	 */
	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}
	/**
	 * 获取：
	 */
	public String getCodeType() {
		return codeType;
	}
	/**
	 * 设置：
	 */
	public void setCodeNumber(String codeNumber) {
		this.codeNumber = codeNumber;
	}
	/**
	 * 获取：
	 */
	public String getCodeNumber() {
		return codeNumber;
	}
}
