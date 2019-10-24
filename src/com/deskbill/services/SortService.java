package com.deskbill.services;

import java.util.List;

import com.deskbill.dao.SortDao;
import com.deskbill.domain.Sort;

public class SortService {
	private SortDao sortDao = new SortDao();
	
	public int  addSort(Sort sort){
		return sortDao.addSort(sort);
	}
	
	public List<Sort> querySortAll(Integer state){
		return sortDao.querySortAll(state);
	}

	public Sort querySortBySid(int sid) {
		// TODO Auto-generated method stub
		return sortDao.querySortBySid(sid);
	}

	public int update(Sort sort) {
		// TODO Auto-generated method stub
		return sortDao.update(sort);
	}

	public int deleteSortBySid(int sid) {
		// TODO Auto-generated method stub
		return sortDao.deleteSortBySid(sid);
	}

	public List<Sort> querySortByType(String type) {
		// TODO Auto-generated method stub
		return sortDao.querySortByType(type);
	}
}
