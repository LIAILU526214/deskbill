package com.deskbill.domain;
/**
 * 账单详情
 * @author fwl admin
 *
 */

import java.util.Date;

public class BillCostDetail {
	private Long id;// 主键
	private String account;// 分类名称支出、收入
	private String type;// 分类名称支出、收入
	private String costType;// 支出方式
	private String ddesc;// 描述
	private double money;// 金额
	private Date costTime;// 时间
	private Date createTime;// 创建时间
	private Date updateTime;// 更新时间
	private int state;// 状态

	public BillCostDetail() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCostType() {
		return costType;
	}

	public void setCostType(String costType) {
		this.costType = costType;
	}

	public String getDdesc() {
		return ddesc;
	}

	public void setDdesc(String ddesc) {
		this.ddesc = ddesc;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public Date getCostTime() {
		return costTime;
	}

	public void setCostTime(Date costTime) {
		this.costTime = costTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "BillCostDetail [id=" + id + ", type=" + type + ", costType=" + costType + ", desc=" + ddesc + ", money="
				+ money + ", costTime=" + costTime + ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", state=" + state + "]";
	}

}
