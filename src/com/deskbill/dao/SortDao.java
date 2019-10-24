package com.deskbill.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.deskbill.controller.MainFrameContrller;
import com.deskbill.domain.Sort;
import com.deskbill.tools.JDBCUtils;

public class SortDao {
	private QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
	
	public int addSort(Sort sort){
		try{
			String sql = "INSERT INTO bill_sort(sname,type,sdesc,userId,state)values(?,?,?,?,?)";
			
			Object[] params = {sort.getSname(),sort.getType(),sort.getSdesc(),MainFrameContrller.user.getId(),sort.getState()};
			
			 int row = qr.update(sql, params);
			 return row;
		}catch(SQLException e){
			//throw new RuntimeException(e);
			e.printStackTrace();
		}
		return 0;
	}
	
	public List<Sort> querySortAll(Integer state){
		try{
			String sql = "SELECT * FROM bill_sort WHERE userid = "+MainFrameContrller.user.getId();
			if (state == 0) {
				sql += " And state = 0 ";
			}else if(state == 1) {
				sql += " And state = 1 ";
			}
			List<Sort> list = qr.query(sql, new BeanListHandler<Sort>(Sort.class));
			return list;
		}catch (SQLException e) {
			//throw new RuntimeException(e);
			e.printStackTrace();
		}
		return null;
	}

	public Sort querySortBySid(int sid) {
		try{
			String sql = "SELECT * FROM bill_sort WHERE sid = ?";
			 
			List<Sort> list = qr.query(sql, new BeanListHandler<Sort>(Sort.class),new Object[] {sid});
			if(list == null || list.size()==0) {
				return null;
			}
			return list.get(0);
		}catch (SQLException e) {
			//throw new RuntimeException(e);
			e.printStackTrace();
		}
		return null;
	}

	public int update(Sort sort) {
		try{
			String sql = "UPDATE bill_sort SET sname = ? , type = ? , sdesc = ?  WHERE sid = ?";
			
			Object[] params = {sort.getSname(),sort.getType(),sort.getSdesc(),sort.getSid()};
			
			 int row = qr.update(sql, params);
			 return row;
		}catch (SQLException e) {
			//throw new RuntimeException(e);
			e.printStackTrace();
		}
		return 0;
	}

	public int deleteSortBySid(int sid) {
		try{
			String sql = "UPDATE bill_sort SET state = 0 WHERE sid = ?";
			
			Object[] params = {sid};
			
			 int row = qr.update(sql, params);
			 return row;
		}catch (SQLException e) {
			//throw new RuntimeException(e);
			e.printStackTrace();
		}
		return 0;
	}
	/**
	 * 根据收入/支出 查询分类
	 * @param type
	 * @return
	 */
	public List<Sort> querySortByType(String type) {
		try{
			String sql = "SELECT * FROM bill_sort WHERE type = ? and userid = ?";
			 Object[] param = {type,MainFrameContrller.user.getId()};
			List<Sort> list = qr.query(sql, new BeanListHandler<Sort>(Sort.class),param);
			if(list == null || list.size()==0) {
				return null;
			}
			return list;
		}catch (SQLException e) {
			//throw new RuntimeException(e);
			e.printStackTrace();
		}
		return null;
	}
}
