package com.deskbill.domain;

/**
 * 计划可看的人
 * 
 * @author fwl admin
 *
 */
public class BillPlanShare {
	private int id;// 主键
	private int adminUser;// 分享人
	private int showUser;// 可以看的人
	private int planId;// 可看的计划

	public BillPlanShare() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAdminUser() {
		return adminUser;
	}

	public void setAdminUser(int adminUser) {
		this.adminUser = adminUser;
	}

	public int getShowUser() {
		return showUser;
	}

	public void setShowUser(int showUser) {
		this.showUser = showUser;
	}

	public int getPlanId() {
		return planId;
	}

	public void setPlanId(int planId) {
		this.planId = planId;
	}

	@Override
	public String toString() {
		return "BillPlanShare [id=" + id + ", adminUser=" + adminUser + ", showUser=" + showUser + ", planId=" + planId
				+ "]";
	}

}
