package com.deskbill.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.deskbill.domain.BillUser;
import com.deskbill.tools.JDBCUtils;
import com.deskbill.tools.StringUtils;

public class BillUserDao {
	private QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());

	public BillUser getBillUserByUsername(String billUserName) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM bill_user WHERE userName = ?";
		String[] params = { billUserName };
		List<BillUser> list = qr.query(sql, new BeanListHandler<BillUser>(BillUser.class),params);
		if (list != null && list.isEmpty()) {
			return null;
		}
		if (list == null) {
			return null;
		}
		return list.get(0);
	}
	public BillUser getBillUserById(String id) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM bill_user WHERE id = ?";
		String[] params = { id };
		List<BillUser> list = qr.query(sql, new BeanListHandler<BillUser>(BillUser.class),params);
		if (list != null && list.isEmpty()) {
			return null;
		}
		if (list == null) {
			return null;
		}
		return list.get(0);
	}

	public int addBillUser(BillUser billUser) throws SQLException {
		String sql = "INSERT INTO bill_user(username,password,email,photo,usernick,createTime,updateTime,salt,state)VALUES(?,?,?,?,?,?,?,?,?)";

		Object[] params = { billUser.getUserName(), billUser.getPassword(), billUser.getEmail(), billUser.getPhoto(),
				billUser.getUserNick(), billUser.getCreateTime(), billUser.getUpdateTime(),billUser.getSalt(), billUser.getState() };

		int row = qr.update(sql, params);
		return row;
	}
	
	public void updateBillUserById(BillUser billUser) throws SQLException {
		String sql = "UPDATE bill_user SET updateTime = ? ";
		List<Object> list = new ArrayList<Object>();
		list.add(new Date());
		if (!StringUtils.isNull(billUser.getPassword())) {
			sql += ", password = ? ";
			list.add(billUser.getPassword());
		}
		if (!StringUtils.isNull(billUser.getEmail())) {
			sql += ", email = ? ";
			list.add(billUser.getEmail());
		}
		if (!StringUtils.isNull(billUser.getPhoto())) {
			sql += ", photo = ? ";
			list.add(billUser.getPhoto());
		}
		if (!StringUtils.isNull(billUser.getUserNick())) {
			sql += ", usernick = ? ";
			list.add(billUser.getUserNick());
		}
		if (!StringUtils.isNull(billUser.getSalt())) {
			sql += ", salt = ? ";
			list.add(billUser.getSalt());
		}
		sql += ", state = ?   WHERE id = ?";
		list.add(billUser.getState());
		list.add(billUser.getId());
//		Object[] params = {billUserByUsername.getPassword(),billUserByUsername.getId(),new Date()};
		 int row = qr.update(sql, list.toArray());
	}

}
