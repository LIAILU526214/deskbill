package com.deskbill.domain;
/**
 * 分类
 * @author admin
 *
 */
public class Ledger {
	private int lid; //主键
	private String parent; //父类
	private double money; //金额
	private int sid;	//
	private String account; //账户
	private String createtime;	//创建时间
	private String ldesc; //描述
	private String sname;//

	public Ledger() {
	}

	public Ledger(int lid, String parent, double money, int sid, String account, String createtime, String ldesc,
			String sname) {
		super();
		this.lid = lid;
		this.parent = parent;
		this.money = money;
		this.sid = sid;
		this.account = account;
		this.createtime = createtime;
		this.ldesc = ldesc;
		this.sname = sname;
	}

	public int getLid() {
		return lid;
	}

	public void setLid(int lid) {
		this.lid = lid;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getLdesc() {
		return ldesc;
	}

	public void setLdesc(String ldesc) {
		this.ldesc = ldesc;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	@Override
	public String toString() {
		return "Ledger [lid=" + lid + ", parent=" + parent + ", money=" + money + ", sid=" + sid + ", account="
				+ account + ", createtime=" + createtime + ", ldesc=" + ldesc + ", sname=" + sname + "]";
	}

}
