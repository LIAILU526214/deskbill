package com.deskbill.controller;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import com.deskbill.domain.Sort;
import com.deskbill.services.SortService;
import com.deskbill.view.AbstractOperationSortDialog;

public class AddSortController extends AbstractOperationSortDialog {
	private static final long serialVersionUID = -7986296623248796274L;

	public AddSortController(JDialog dialog) {
		super(dialog);
		titleLabel.setText("添加分类");
		super.setTitle("添加分类");
	}

	@Override
	public void confirm() {
		//String parent = parentBox.getSelectedItem().toString();

		String sname = snameTxt.getText().trim();

		String sdesc = sdescArea.getText();

		/*if ("=请选择=".equals(parent)) {
			JOptionPane.showMessageDialog(this, "请选择分类", "错误提示", JOptionPane.ERROR_MESSAGE);
			return;
		}*/

		if (sname == null || sname.equals("")) {
			JOptionPane.showMessageDialog(this, "请填写分类名称");
			return;
		}

		Sort sort = new Sort();
		sort.setType(parentBox.getSelectedItem().toString());
		sort.setSname(sname);
		sort.setSdesc(sdesc);
		sort.setState(1);

		SortService sortService = new SortService();

		int addSort = sortService.addSort(sort);
		if (addSort > 0) {
			JOptionPane.showMessageDialog(this, "添加分类成功", "操作成功", JOptionPane.PLAIN_MESSAGE);
			this.dispose();
		}else {
			JOptionPane.showMessageDialog(this, "添加分类失败", "操作失败", JOptionPane.ERROR_MESSAGE);
		}

	}
}
