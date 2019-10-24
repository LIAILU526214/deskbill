package com.deskbill.domain;

import java.util.Date;

/**
 * 用户
 * @author fwl admin
 *
 */
public class BillUser {
	private int id;// 主键
	private String userName;// 用户名
	private String password;// 密码
	private String userNick;// 用户昵称
	private String photo;// 用户昵称
	private String email;// 用户昵称
	private Date createTime;// 注册时间
	private Date updateTime;// 最后一次更改时间
	private String salt;
	private int state;// 账号状态 1在用，2注销

	public BillUser() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserNick() {
		return userNick;
	}

	public void setUserNick(String userNick) {
		this.userNick = userNick;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "BillUser [id=" + id + ", userName=" + userName + ", password=" + password + ", userNick=" + userNick
				+ ", email=" + email + ", createTime=" + createTime + ", updateTime=" + updateTime + ", state=" + state
				+ "]";
	}

}
