package com.deskbill.services;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.deskbill.dao.LedgerDao;
import com.deskbill.domain.BillCostDetail;

public class LedgerService {
	private LedgerDao ledgerDao = new LedgerDao();
	
	public List<BillCostDetail> queryLedgerAll(String costType, String type, String beginDate, String endDate, int state) {
		
		try {
			return ledgerDao.queryLedgerAll(costType, type, beginDate,endDate,state);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public int addLedger(BillCostDetail billCostDetail) {
		// TODO Auto-generated method stub
		try {
			return ledgerDao.addLedger(billCostDetail);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public BillCostDetail queryLedgerById(long id) {
		try {
			return ledgerDao.queryLedgerById(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public int updateLedger(BillCostDetail billCostDetail) {
		try {
			return ledgerDao.updateLedgerById(billCostDetail);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public int deleteLedgerById(Long id) {
		try {
			return ledgerDao.deleteLedgerById(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public double queryCountLedgerInOrPay(String costType, String type, String beginDate, String endDate,
			Integer state) {
		try {
			return ledgerDao.queryCountLedgerInOrPay(costType, type, beginDate,endDate,state);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	/**
	 * 查询收入/支出各项统计
	 * @param string
	 * @param beginTime
	 * @param endTime
	 * @param i
	 * @return 
	 */
	public Map<String, Double> queryCountInOrPayByGroupType(String type, String beginTime, String endTime, int state) {
		// TODO Auto-generated method stub
		Map<String,Double> result = null;
		try {
			result = ledgerDao.queryCountInOrPayByGroupType(type, beginTime,endTime,state);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
