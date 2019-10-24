package com.deskbill.services;

import java.sql.SQLException;

import com.deskbill.dao.BillUserDao;
import com.deskbill.domain.BillUser;

public class BillUserService {
	private BillUserDao billUserDao = new BillUserDao();

	public BillUser getBillUserByUsername(String billUserName) {
		// TODO Auto-generated method stub
		try {
			return billUserDao.getBillUserByUsername(billUserName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public int addBillUser(BillUser billUser) {
		try {
			return billUserDao.addBillUser(billUser);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

}
