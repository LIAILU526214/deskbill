package com.deskbill.domain;

public class Sort {
	 private int sid  ;
	 private String sname ;
	 private String type = "收入"; //0支出,1收入
	 private String sdesc ;
	 private int state = 1;//0不可用,1可用
	 
	 public Sort(){}
	 
	public Sort(int sid, String sname, String type, String sdesc,int state) {
		this.sid = sid;
		this.sname = sname;
		this.type = type;
		this.sdesc = sdesc;
		this.state = state;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getSdesc() {
		return sdesc;
	}

	public void setSdesc(String sdesc) {
		this.sdesc = sdesc;
	}

	@Override
	public String toString() {
		return "Sort [sid=" + sid + ", sname=" + sname + ", type=" + type + ", sdesc=" + sdesc + ", state=" + state
				+ "]";
	}
	 
}
