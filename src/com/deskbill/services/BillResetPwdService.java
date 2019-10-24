package com.deskbill.services;

import java.sql.SQLException;

import com.deskbill.dao.BillResetPwdDao;
import com.deskbill.dao.BillUserDao;
import com.deskbill.domain.BillResetPwd;
import com.deskbill.domain.BillUser;

public class BillResetPwdService {
	private BillResetPwdDao billRestPedDao = new BillResetPwdDao();
	private BillUserDao billuserDao = new BillUserDao();
	public int addBillResPwd(BillResetPwd resetPwd) {
		// TODO Auto-generated method stub
		try {
			return billRestPedDao.addBillResPwd(resetPwd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	public BillResetPwd getRestPwdByUserId(int id) {
		// TODO Auto-generated method stub
		try {
			return billRestPedDao.getBillResetPwdByUserId(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public void updatePwdByUserid(BillUser billUserByUsername)  {
		// TODO Auto-generated method stub
		try {
			billuserDao.updateBillUserById(billUserByUsername);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
