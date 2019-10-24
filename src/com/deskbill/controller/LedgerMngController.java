package com.deskbill.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.deskbill.domain.BillCostDetail;
import com.deskbill.domain.Sort;
import com.deskbill.services.LedgerService;
import com.deskbill.services.SortService;
import com.deskbill.view.AbstractLedgerMngDialog;

@SuppressWarnings("unchecked")
public class LedgerMngController extends AbstractLedgerMngDialog {
	private static final long serialVersionUID = -5760763980375818442L;
	private LedgerService ledgerService = new LedgerService();
	SortService sortService = new SortService();
	Map<String, Double> totalMap = new HashMap<String, Double>();
	public LedgerMngController(JFrame frame) {
		super(frame);
		List<Sort> querySortAll = sortService.querySortAll(1);
		List<Object> objs = new ArrayList<>();
		objs.add("全部分类");
		if (querySortAll != null) {
			for (Sort query : querySortAll) {
				objs.add(query.getSname());
			}
		}
		sortBox.setModel(new DefaultComboBoxModel<Object>(objs.toArray()));
		refresh(1);
	}

	@Override
	public void addLedger() {
		new AddLedgerController(this).setVisible(true);
		refresh(1);

	}

	@Override
	public void editLedger(Long id) {
		new EditLedgerController(this, id).setVisible(true);
		refresh(1);
	}

	@Override
	public void deleteLedger(Long id) {
		int isOK = JOptionPane.showConfirmDialog(this, "确定删除此账单?", "删除操作提示", JOptionPane.OK_CANCEL_OPTION);
		if (isOK == 0) {
			int delRow = ledgerService.deleteLedgerById(id);
			if (delRow > 0) {
				JOptionPane.showMessageDialog(this, "删除账单成功", "操作成功", JOptionPane.PLAIN_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this, "删除账单失败", "操作失败", JOptionPane.ERROR_MESSAGE);
			}
		}
		refresh(1);

	}

	@Override
	public void queryLedger() {
		refresh(1);
	}

	@Override
	public void parentChange() {
		String str = parentBox.getSelectedItem().toString();
		List<Sort> querySortAll = null;
		if ("收入/支出".equals(str)) {
			querySortAll = sortService.querySortAll(1);
		} else {
			querySortAll = sortService.querySortByType(str);
		}
		List<Object> objs = new ArrayList<>();
		objs.add("全部分类");
		if (querySortAll != null) {
			for (Sort query : querySortAll) {
				objs.add(query.getSname());
			}
		}
		sortBox.setModel(new DefaultComboBoxModel<Object>(objs.toArray()));
	}

	@Override
	public void pie() {
//		Map<String, String> paramMap = new HashMap<>();
//		map.
		new ShapeController(this,beginDateTxt.getText(),endDateTxt.getText(),totalMap,"总收入/总支出").setVisible(true);
		//refresh(1);
	}

	private void refresh(Integer state) {
		String beginDate = beginDateTxt.getText();
		String endDate = endDateTxt.getText();
		String type = parentBox.getSelectedItem().toString();
		if ("收入/支出".equals(type)) {
			type = null;
		}
		String costType = sortBox.getSelectedItem().toString();
		if ("全部分类".equals(costType)) {
			costType = null;
		}
		List<BillCostDetail> billCostDetails = ledgerService.queryLedgerAll(costType, type, beginDate, endDate, state);
		if (billCostDetails == null) {
			billCostDetails = new ArrayList<>();
		}
		super.setTableModel(billCostDetails);
		double pay = 0.0;
		double in = 0.0;
		if (type == null) {
			 in = ledgerService.queryCountLedgerInOrPay(costType, "收入", beginDate, endDate, state);
			 pay = ledgerService.queryCountLedgerInOrPay(costType, "支出", beginDate, endDate, state);
		}else if("收入".equals(type)) {
			 in = ledgerService.queryCountLedgerInOrPay(costType, "收入", beginDate, endDate, state);
		}else if ("支出".equals(type)) {
			pay = ledgerService.queryCountLedgerInOrPay(costType, "支出", beginDate, endDate, state);
		}
		inMoneyTotalLabel.setText("总收入："+in+"元");
		payMoneyTotalLabel.setText("总支出："+pay+"元");
		totalMap.put("总收入", in);
		totalMap.put("总支出", pay);
	}

}
