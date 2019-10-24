package com.deskbill.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import com.deskbill.domain.BillCostDetail;
import com.deskbill.domain.Sort;
import com.deskbill.services.LedgerService;
import com.deskbill.services.SortService;
import com.deskbill.tools.ComBoxModelUtils;
import com.deskbill.tools.DateUtils;
import com.deskbill.view.AbstractOperationLedgerDialog;
@SuppressWarnings("unchecked")
public class EditLedgerController extends AbstractOperationLedgerDialog {
	private static final long serialVersionUID = 6784140528277755313L;
	LedgerService ledgerService = new LedgerService();
	SortService sortService = new SortService();
	private long id;
	public EditLedgerController(JDialog dialog) {
		super(dialog);
		titleLabel.setText("编辑账务");
		setTitle("编辑账务");
		
	}

	public EditLedgerController(JDialog dialog, long id) {
		super(dialog);
		titleLabel.setText("编辑账务");
		this.id = id;
		BillCostDetail billCostDetail = ledgerService.queryLedgerById(id);
		if (billCostDetail == null) {
			return ;
		}
		accountTxt.setText(billCostDetail.getAccount());
		ldescTxt.setText(billCostDetail.getDdesc());
		moneyTxt.setText(String.valueOf(billCostDetail.getMoney()));
		List<Sort> querySortByType = sortService.querySortByType(billCostDetail.getType());
		sortBox.setModel(new ComBoxModelUtils<Sort>(querySortByType, "sname"));
		parentBox.setSelectedItem(billCostDetail.getType());
		sortBox.setSelectedItem(billCostDetail.getCostType());
	}

	@Override
	public void changeParent() {
		List<Sort> list = sortService.querySortByType(parentBox.getSelectedItem().toString());
		if (list == null || list.size() == 0) {
			return ;
		}
		sortBox.setModel(new ComBoxModelUtils<Sort>(list,"sname"));
		sortBox.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
			}
			
		});
		sortBox.setSelectedIndex(0);

	}

	@Override
	public void confirm() {
				BillCostDetail billCostDetail = new BillCostDetail();
				try {
					billCostDetail.setCostTime(DateUtils.formatStringDate(createtimeTxt.getText(), "yyyy-MM-dd"));
				} catch (ParseException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(this, "请选择日期", "操作失败", JOptionPane.PLAIN_MESSAGE);
					return ;
				}
				billCostDetail.setId(id);
				billCostDetail.setUpdateTime(new Date());
				billCostDetail.setAccount(accountTxt.getText());
				billCostDetail.setCostType(sortBox.getSelectedItem().toString());
				billCostDetail.setDdesc(ldescTxt.getText());
				billCostDetail.setType(parentBox.getSelectedItem().toString());
				billCostDetail.setState(1);
				billCostDetail.setMoney(Double.parseDouble(moneyTxt.getText()));
				
				int result = ledgerService.updateLedger(billCostDetail);
				if (result > 0) {
					JOptionPane.showMessageDialog(this, "更新账单成功", "操作成功", JOptionPane.PLAIN_MESSAGE);
					this.dispose();
				}else {
					JOptionPane.showMessageDialog(this, "更新账单失败", "操作失败", JOptionPane.ERROR_MESSAGE);
				}
	}

}
