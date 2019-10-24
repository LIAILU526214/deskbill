package com.deskbill.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.deskbill.domain.BillResetPwd;
import com.deskbill.tools.JDBCUtils;

public class BillResetPwdDao {
	private QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());

	public BillResetPwd getBillResetPwdByUserId(int userId) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM bill_reset_pwd WHERE userId = ?";
		String[] params = { userId+"" };
		List<BillResetPwd> list = qr.query(sql, new BeanListHandler<BillResetPwd>(BillResetPwd.class),params);
		if (list != null && list.isEmpty()) {
			return null;
		}
		if (list == null) {
			return null;
		}
		return list.get(0);
	}


	public int addBillResPwd(BillResetPwd resetPwd) throws SQLException{
		String sql = "INSERT INTO bill_reset_pwd(question,answer,userId,hint)VALUES(?,?,?,?)";
		Object[] params = { resetPwd.getQuestion(), resetPwd.getAnswer(), resetPwd.getUserId(),resetPwd.getHint() };

		int row = qr.update(sql, params);
		return row;
	}


}
