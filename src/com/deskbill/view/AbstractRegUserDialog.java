package com.deskbill.view;

import java.awt.Dialog;

import javax.swing.JFrame;

public abstract class AbstractRegUserDialog extends Dialog {

	private static final long serialVersionUID = -7715734075877916417L;

	public AbstractRegUserDialog(JFrame frame) {
		super(frame);
		// TODO Auto-generated constructor stub
//		init();
	}
	
	public void init() {
		initDialog();
		
	}

	protected void initDialog() {
		setTitle("注册");
	}
}
