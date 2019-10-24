package com.deskbill.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;

import com.deskbill.controller.MainFrameContrller;
import com.deskbill.domain.BillCostDetail;
import com.deskbill.tools.JDBCUtils;
import com.deskbill.tools.StringUtils;

public class LedgerDao {
	private QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());

	public List<BillCostDetail> queryLedgerAll(String costType, String type, String beginDate, String endDate,
			int state) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM bill_cost_detail WHERE state=? and userid = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(state);
		params.add(MainFrameContrller.user.getId());
		if (!StringUtils.isNull(costType)) {
			sql += " AND costType = ?";
			params.add(costType);
		}
		if (!StringUtils.isNull(type)) {
			sql += " AND type = ?";
			params.add(type);
		}
		if (!StringUtils.isNull(beginDate)) {
			sql += " AND costTime >= ?";
			params.add(beginDate);
		}
		if (!StringUtils.isNull(endDate)) {
			sql += " AND costTime <= ?";
			params.add(endDate);
		}
		
		List<BillCostDetail> list = qr.query(sql, new BeanListHandler<BillCostDetail>(BillCostDetail.class), params.toArray());
		return list;
	}

	public int addLedger(BillCostDetail billCostDetail) throws SQLException {
		String sql = "INSERT INTO bill_cost_detail(account,type,costType,ddesc,money,costTime,createTime,updateTime,userid,state)VALUES(?,?,?,?,?,?,?,?,?,?) ";
		Object[] params = {billCostDetail.getAccount(),billCostDetail.getType(),billCostDetail.getCostType(),
				billCostDetail.getDdesc(),billCostDetail.getMoney(),billCostDetail.getCostTime(),billCostDetail.getCreateTime(),
				billCostDetail.getUpdateTime(),MainFrameContrller.user.getId(),billCostDetail.getState()};
		
		 int row = qr.update(sql, params);
		 return row;
	}

	public BillCostDetail queryLedgerById(long id) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM bill_cost_detail WHERE id = ?";
		List<BillCostDetail> list = qr.query(sql, new BeanListHandler<BillCostDetail>(BillCostDetail.class),new Object[] {id});
		if(list == null || list.size()==0) {
			return null;
		}
		return list.get(0);
	}

	public int updateLedgerById(BillCostDetail billCostDetail) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "UPDATE bill_cost_detail SET state = ? " ;
		List<Object> parms = new ArrayList<>(); 
		parms.add(billCostDetail.getState());
		if (billCostDetail.getAccount() != null) {
			sql += ", account = ? ";
			parms.add(billCostDetail.getAccount());
		}
		if (billCostDetail.getCostTime() != null) {
			sql += ", costTime = ?";
			parms.add(billCostDetail.getCostTime());
		}
		if (billCostDetail.getCostType() != null) {
			sql += ", costType = ?";
			parms.add(billCostDetail.getCostType());
		}
		if (billCostDetail.getDdesc() != null) {
			sql += ", ddesc = ?";
			parms.add(billCostDetail.getDdesc());
		}
		if (billCostDetail.getUpdateTime() != null) {
			sql += ", updateTime = ?";
			parms.add(billCostDetail.getUpdateTime());
		}
		if (billCostDetail.getType() != null) {
			sql += ", type = ?";
			parms.add(billCostDetail.getType());
		}
		sql += ", money = ?";
		parms.add(billCostDetail.getMoney());
		
		sql += " WHERE id = ?";
		parms.add(billCostDetail.getId());
		int update = qr.update(sql, parms.toArray());
		return update;
	}

	public int deleteLedgerById(Long id) throws SQLException {
		String sql = "UPDATE bill_cost_detail SET state = 0 WHERE id = ?";
		int update = qr.update(sql, new Object[] {id});
		return update;
	}

	public double queryCountLedgerInOrPay(String costType, String type, String beginDate, String endDate,
			Integer state) throws SQLException {
		String sql = "SELECT sum(money) as sumMoney FROM bill_cost_detail WHERE state=? ";
		List<Object> params = new ArrayList<Object>();
		params.add(state);
		if (!StringUtils.isNull(costType)) {
			sql += " AND costType = ?";
			params.add(costType);
		}
		if (!StringUtils.isNull(type)) {
			sql += " AND type = ?";
			params.add(type);
		}
		if (!StringUtils.isNull(beginDate)) {
			sql += " AND costTime >= ?";
			params.add(beginDate);
		}
		if (!StringUtils.isNull(endDate)) {
			sql += " AND costTime <= ?";
			params.add(endDate);
		}
		Map<String, Object> map = qr.query(sql, new MapHandler(), params.toArray());
		if (map == null || map.size() == 0 || map.get("sumMoney") == null || StringUtils.isNull(map.get("sumMoney").toString())) {
			return 0;
		}
		return Double.parseDouble(map.get("sumMoney").toString());
	}

	public Map<String, Double> queryCountInOrPayByGroupType(String type, String beginDate, String endDate, int state) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "SELECT costType,sum(money) as sumMoney FROM bill_cost_detail WHERE state=? ";
		List<Object> params = new ArrayList<Object>();
		params.add(state);
		if (!StringUtils.isNull(type)) {
			sql += " AND type = ?";
			params.add(type);
		}
		if (!StringUtils.isNull(beginDate)) {
			sql += " AND costTime >= ?";
			params.add(beginDate);
		}
		if (!StringUtils.isNull(endDate)) {
			sql += " AND costTime <= ?";
			params.add(endDate);
		}
		sql += " GROUP BY costType";
		ResultSetHandler<Map<String,Double>> rsh = new ResultSetHandler<Map<String,Double>>() {
			
			@Override
			public Map<String, Double> handle(ResultSet rs) throws SQLException {
				Map<String, Double> resultMap = new HashMap<String, Double>();
				while(rs.next()) {
					String key = rs.getString("costType");
					double value = rs.getDouble("sumMoney");
					resultMap.put(key, value);
				}
				return resultMap;
			}
		};
		Map<String, Double> map = qr.query(sql, rsh , params.toArray());
		return map;
	}
}
