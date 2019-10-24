package com.deskbill.controller;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import com.deskbill.domain.Sort;
import com.deskbill.services.SortService;
import com.deskbill.view.AbstractOperationSortDialog;

public class EditSortController extends AbstractOperationSortDialog {
	private static final long serialVersionUID = -6163016528126601314L;
	SortService sortService = new SortService();
	private int sid ;
	public EditSortController(JDialog dialog,int sid) {
		super(dialog);
		titleLabel.setText("编辑分类");
		super.setTitle("编辑分类");
		this.sid = sid;
		Sort querySortBySid = sortService.querySortBySid(sid);
		parentBox.setSelectedItem(querySortBySid.getType());
		snameTxt.setText(querySortBySid.getSname());
		sdescArea.setText(querySortBySid.getSdesc());
	}

	@Override
	public void confirm() {

		String sname = snameTxt.getText().trim();

		String sdesc = sdescArea.getText();

		if (sname == null || sname.equals("")) {
			JOptionPane.showMessageDialog(this, "请填写分类名称");
			return;
		}

		Sort sort = new Sort();
		sort.setType(parentBox.getSelectedItem().toString());
		sort.setSname(sname);
		sort.setSdesc(sdesc);
		sort.setState(1);
		sort.setSid(sid);
		int update = sortService.update(sort);
		if (update > 0) {
			JOptionPane.showMessageDialog(this, "更新分类成功", "操作成功", JOptionPane.PLAIN_MESSAGE);
			this.dispose();
		}else {
			JOptionPane.showMessageDialog(this, "更新分类失败","操作失败",JOptionPane.ERROR_MESSAGE);
		}


	}

}
