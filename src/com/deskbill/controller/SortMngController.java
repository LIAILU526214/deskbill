package com.deskbill.controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.deskbill.domain.Sort;
import com.deskbill.services.SortService;
import com.deskbill.view.AbstractSortMngDialog;

public class SortMngController extends AbstractSortMngDialog {
	private static final long serialVersionUID = 4685263191224553521L;
	private SortService sortService = new SortService();

	public SortMngController(JFrame frame) {
		super(frame);
		refresh(1);
	}

	@Override
	public void addSort() {
		new AddSortController(this).setVisible(true);
		refresh(1);
	}

	@Override
	public void editSort(Integer sid) {
		new EditSortController(this,sid).setVisible(true);
		refresh(1);
	}

	@Override
	public void deleteSort(Integer sid) {
		int isOK = JOptionPane.showConfirmDialog(this, "确定删除此分类?","删除操作提示",JOptionPane.OK_CANCEL_OPTION);
		if (isOK == 0) {
			int delRow = sortService.deleteSortBySid(sid);
			if (delRow > 0) {
				JOptionPane.showMessageDialog(this, "删除分类成功", "操作成功", JOptionPane.PLAIN_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(this, "删除分类失败", "操作失败", JOptionPane.ERROR_MESSAGE);
			}
		}
		refresh(1);
	}

	private void refresh(Integer state) {
		List<Sort> list = sortService.querySortAll(state);
		if(list == null) {
			list = new ArrayList<>();
		}
		super.setTableModel(list);
	}
}
