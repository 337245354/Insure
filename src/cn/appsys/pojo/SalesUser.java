package cn.appsys.pojo;

import java.util.Date;

public class SalesUser {
	private Integer id;//主键id
	private String salesCode;//销售员帐号
	private String salesName;//销售员名称
	private String salesPassword;//销售员密码
	private String salesEmail;//销售员电子邮箱
	private String salesInfo;	//销售员简介
	private Integer createdBy;//创建者
	private Date creationDate;//创建时间
	private Integer modifyBy;//更新者
	private Date modifyDate;//更新时间


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSalesCode() {
		return salesCode;
	}

	public void setSalesCode(String salesCode) {
		this.salesCode = salesCode;
	}

	public String getSalesName() {
		return salesName;
	}

	public void setSalesName(String salesName) {
		this.salesName = salesName;
	}

	public String getSalesPassword() {
		return salesPassword;
	}

	public void setSalesPassword(String salesPassword) {
		this.salesPassword = salesPassword;
	}

	public String getSalesEmail() {
		return salesEmail;
	}

	public void setSalesEmail(String salesEmail) {
		this.salesEmail = salesEmail;
	}

	public String getSalesInfo() {
		return salesInfo;
	}

	public void setSalesInfo(String salesInfo) {
		this.salesInfo = salesInfo;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Integer getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(Integer modifyBy) {
		this.modifyBy = modifyBy;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}


	

	
}
