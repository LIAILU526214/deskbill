package com.deskbill.domain;
/**
 * 计划进度
 * @author admin
 *
 */

import java.util.Date;

public class BillPlanProgress {
	private int id;// 主键
	private String content;// 进度描述
	private double usingTime;// 用时
	private Date createTime;// 创建时间
	private Date updateTime;// 更新时间
	private int planId;// 主计划
	private int done;// 本次是否完成
	private double rate;// 本次计划进度
	private int state;// 是否可用 1可用，2删除

	public BillPlanProgress() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getDone() {
		return done;
	}

	public void setDone(int done) {
		this.done = done;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public double getUsingTime() {
		return usingTime;
	}

	public void setUsingTime(double usingTime) {
		this.usingTime = usingTime;
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

	public int getPlanId() {
		return planId;
	}

	public void setPlanId(int planId) {
		this.planId = planId;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "BillPlanProgress [id=" + id + ", content=" + content + ", usingTime=" + usingTime + ", createTime="
				+ createTime + ", updateTime=" + updateTime + ", planId=" + planId + ", done=" + done + ", rate=" + rate
				+ ", state=" + state + "]";
	}

}
