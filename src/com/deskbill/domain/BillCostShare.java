package com.deskbill.domain;
/**
 * 分享用户账单记录
 * @author fwl admin
 *
 */

import java.util.Date;

public class BillCostShare {
	private int id;// 主键
	private int shareUser;// 分享用户
	private int showUser;// 可看用户
	private int shareType;// 可看账单类型
	private Date beginTime;// 账单开始时间
	private Date endTime;// 账单结束时间

	public BillCostShare() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getShareUser() {
		return shareUser;
	}

	public void setShareUser(int shareUser) {
		this.shareUser = shareUser;
	}

	public int getShowUser() {
		return showUser;
	}

	public void setShowUser(int showUser) {
		this.showUser = showUser;
	}

	public int getShareType() {
		return shareType;
	}

	public void setShareType(int shareType) {
		this.shareType = shareType;
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

	@Override
	public String toString() {
		return "BillCostShare [id=" + id + ", shareUser=" + shareUser + ", showUser=" + showUser + ", shareType="
				+ shareType + ", beginTime=" + beginTime + ", endTime=" + endTime + "]";
	}

}
