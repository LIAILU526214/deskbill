package com.deskbill.domain;

/**
 * 账单类型
 * 
 * @author admin
 *
 */
public class BillCostType {
	private int id;// id
	private String name;// 分类名称
	private int parent;// 父类型
	private int state;// 状态 1不可用，2，可用

	public BillCostType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getParent() {
		return parent;
	}

	public void setParent(int parent) {
		this.parent = parent;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "BillCostType [id=" + id + ", name=" + name + ", parent=" + parent + ", state=" + state + "]";
	}

}
