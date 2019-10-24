package com.deskbill.domain;
/**
 * 计划表
 * @author admin
 *
 */

import java.util.Date;

public class BillPlan {
	private int id;
	private String title;// 计划名称
	private String content;// 计划内容
	private int userId;// 计划人
	private Date beginTime;// 计划开始时间
	private Date endTime;// 计划结束时间
	private Date createTime;// 计划创建时间
	private Date updateTime;// 计划更新时间
	private double rate;// 百分比
	private int remind;// 是否登录时提醒 1是，2不是
	private int state;// 是否可用1可用2不可用

	public BillPlan() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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

	public int getRemind() {
		return remind;
	}

	public void setRemind(int remind) {
		this.remind = remind;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "BillPlan [id=" + id + ", title=" + title + ", content=" + content + ", userId=" + userId
				+ ", beginTime=" + beginTime + ", endTime=" + endTime + ", createTime=" + createTime + ", updateTime="
				+ updateTime + ", rate=" + rate + ", remind=" + remind + ", state=" + state + "]";
	}

}
