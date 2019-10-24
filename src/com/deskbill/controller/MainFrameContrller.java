package com.deskbill.controller;

import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.deskbill.domain.BillResetPwd;
import com.deskbill.domain.BillUser;
import com.deskbill.services.BillResetPwdService;
import com.deskbill.services.BillUserService;
import com.deskbill.tools.Md5PwdEncoder;
import com.deskbill.tools.StringUtils;
import com.deskbill.tools.VarStatic;
import com.deskbill.view.AbstractMainFrame;

public class MainFrameContrller extends AbstractMainFrame {
	private static final long serialVersionUID = 6779644762195372992L;
	private BillUserService BillUserService = new BillUserService();
	private BillResetPwdService billRestPwdService = new BillResetPwdService();
	public static BillUser user = null;

	/**
	 * 账务
	 */
	@Override
	public void ledgerMng() {
		new LedgerMngController(this).setVisible(true);
	}

	/**
	 * 分类
	 */
	@Override
	public void sortMng() {
		new SortMngController(this).setVisible(true);
	}

	@Override
	public void loginMng() {
		String billUserName = userName.getText();
		if (StringUtils.isNull(billUserName)) {
			regUserLabel.setText("账号不能为空");
			return;
		} else {
			regUserLabel.setText(" ");
		}
		char[] password = userPwd.getPassword();
		if (password == null || password.length == 0) {
			regPwdLabel.setText("密码不能为空");
			return;
		} else {
			regPwdLabel.setText(" ");
		}
		BillUser billUserByUsername = BillUserService.getBillUserByUsername(billUserName);
		if (billUserByUsername == null) {
			regPwdLabel.setText("账号或密码错误");
			return;
//			String path = VarStatic.PREFIX+"resource\\img\\success.png";
//			String message = "<html><head></head><body>" + "<font size=8 style=\"font-family:'STXingkai'\"><center>" + "注册成功"  + "</center></font></body></html>";
//			JOptionPane.showConfirmDialog(this, message, "注册成功", JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE, new ImageIcon(path));
		} else {
			regPwdLabel.setText(" ");
		}
		Md5PwdEncoder md = new Md5PwdEncoder();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < password.length; i++) {
			sb.append(password[i]);
		}
		if (!md.isPasswordValid(billUserByUsername.getPassword(), sb.toString(), billUserByUsername.getSalt())) {
			regPwdLabel.setText("账号或密码错误");
			return;
		} else {
			regPwdLabel.setText(" ");
		}
		setTitle("追梦乘风账单管理系统 - " + billUserByUsername.getUserNick());
		user = billUserByUsername;
		loginBtn.setVisible(false);
		registBtn.setVisible(false);
		userName.setVisible(false);
		userNameLabel.setVisible(false);
		userPwd.setVisible(false);
		userPwdLabel.setVisible(false);
		forgetBtn.setVisible(false);
		ledgerBtn.setVisible(true);
		sortBtn.setVisible(true);
		logoutBtn.setVisible(true);
	}

	@Override
	public void registMng() {
		new RegisterController(this).setVisible(true);
	}

	@Override
	protected void logoutMng() {
		// TODO Auto-generated method stub
		user = null;
		setTitle("追梦乘风账单管理系统 ");
		loginBtn.setVisible(true);
		registBtn.setVisible(true);
		userName.setVisible(true);
		userNameLabel.setVisible(true);
		userPwd.setVisible(true);
		forgetBtn.setVisible(true);
		userPwdLabel.setVisible(true);
		ledgerBtn.setVisible(false);
		sortBtn.setVisible(false);
		logoutBtn.setVisible(false);
	}

	@Override
	protected void forgetBtn_mouseReleased(MouseEvent e) {
		{
			String message = "请输入要找回的账号: (剩余次数 3 次)";
			BillUser billUserByUsername = null;
			String showInputDialog = JOptionPane.showInputDialog(null, message);
			if (StringUtils.isNull(showInputDialog)
					| (billUserByUsername = BillUserService.getBillUserByUsername(showInputDialog)) == null) {
				JOptionPane.showMessageDialog(null, "您的输入账号可能不存在,请重新输入.....");
				showInputDialog = JOptionPane.showInputDialog(null, "请输入要找回的账号: (剩余次数 " + 2 + " 次)");
				if (StringUtils.isNull(showInputDialog)
						| (billUserByUsername = BillUserService.getBillUserByUsername(showInputDialog)) == null) {
					JOptionPane.showMessageDialog(null, "您的输入账号可能不存在,请重新输入.....");
					showInputDialog = JOptionPane.showInputDialog(null, "请输入要找回的账号: (剩余次数 " + 1 + " 次)");
					if (StringUtils.isNull(showInputDialog)
							| (billUserByUsername = BillUserService.getBillUserByUsername(showInputDialog)) == null) {
						JOptionPane.showMessageDialog(null, "您的输入次数已用完......");
						return;
					}
				}
			}
			BillResetPwd bi = billRestPwdService.getRestPwdByUserId(billUserByUsername.getId());
			StringBuilder sb = new StringBuilder();
			if (bi != null && bi.getQuestion() != null) {
				JTextField ans = new JTextField();
				JOptionPane.showMessageDialog(null, ans, "密保问题: " + bi.getQuestion() + " (剩余次数3次)",
						JOptionPane.PLAIN_MESSAGE);
				if (!bi.getAnswer().equals(ans.getText())) {
					JOptionPane.showMessageDialog(null, "您的密保问题答案错误.....");
					JOptionPane.showMessageDialog(null, ans, "密保问题: " + bi.getQuestion() + " (剩余次数2次)",
							JOptionPane.PLAIN_MESSAGE);
					if (!bi.getAnswer().equals(ans.getText())) {
//						JOptionPane.showMessageDialog(null, "您的密保问题答案错误.....");
						int op = JOptionPane.showConfirmDialog(null, message, "您的密保问题答案错误,是否需要密保提示?",
								JOptionPane.YES_NO_OPTION);
						if (op == 0) {
							ans.setToolTipText("提示: " + bi.getHint());
							JOptionPane.showMessageDialog(null, ans, "密保问题: " + bi.getQuestion() + " (剩余次数1次)",
									JOptionPane.PLAIN_MESSAGE);
							if (!bi.getAnswer().equals(ans.getText())) {
								JOptionPane.showMessageDialog(null, "您的次数已用完.......");
								return;
							}
						} else {
							JOptionPane.showMessageDialog(null, ans, "密保问题: " + bi.getQuestion() + " (剩余次数1次)",
									JOptionPane.PLAIN_MESSAGE);
							if (!bi.getAnswer().equals(ans.getText())) {
								JOptionPane.showMessageDialog(null, "您的次数已用完.......");
								return;
							}
						}
					}
				}
			}
			JPasswordField pw = new JPasswordField();
			JPasswordField pw1 = new JPasswordField();
			JOptionPane.showMessageDialog(null, pw, "请输入你的密码 (剩余次数3次)", JOptionPane.PLAIN_MESSAGE);
			if (pw.getPassword() == null || pw.getPassword().length == 0) {
				JOptionPane.showMessageDialog(null, "您的密码输入有误,请重新输入.....");
				if (pw.getPassword() == null || pw.getPassword().length == 0) {
					JOptionPane.showMessageDialog(null, pw, "请输入你的密码 (剩余次数2次)", JOptionPane.PLAIN_MESSAGE);
					if (pw.getPassword() == null || pw.getPassword().length == 0) {
						JOptionPane.showMessageDialog(null, "您的密码输入有误,请重新输入.....");
						JOptionPane.showMessageDialog(null, pw, "请输入你的密码 (剩余次数1次)", JOptionPane.PLAIN_MESSAGE);
						if (pw.getPassword() == null || pw.getPassword().length == 0) {
							JOptionPane.showMessageDialog(null, "您的次数已用完.......");
							return;
						}
					}
				}
			}
			boolean flag = false;
			JOptionPane.showMessageDialog(null, pw1, "请再次输入你的密码 (剩余次数3次)", JOptionPane.PLAIN_MESSAGE);
			if (pw1.getPassword() == null || pw1.getPassword().length == 0
					|| pw1.getPassword().length != pw.getPassword().length) {
				JOptionPane.showMessageDialog(null, "两次密码不一致,请重新输入.....");
				JOptionPane.showMessageDialog(null, pw1, "请再次输入你的密码 (剩余次数2次)", JOptionPane.PLAIN_MESSAGE);
				if (pw1.getPassword() == null || pw1.getPassword().length == 0
						|| pw1.getPassword().length != pw.getPassword().length) {
					JOptionPane.showMessageDialog(null, "两次密码不一致,请重新输入.....");
					JOptionPane.showMessageDialog(null, pw1, "请再次输入你的密码 (剩余次数1次)", JOptionPane.PLAIN_MESSAGE);
					if (pw1.getPassword() == null || pw1.getPassword().length == 0
							|| pw1.getPassword().length != pw.getPassword().length) {
						JOptionPane.showMessageDialog(null, "您的次数已用完.......");
						return;
					}
				}
			}
			for (int i = 0; i < pw.getPassword().length; i++) {
				if (pw1.getPassword()[i] == pw.getPassword()[i]) {
					sb.append(pw.getPassword()[i]);
					if (i + 1 == pw.getPassword().length) {
						Md5PwdEncoder m = new Md5PwdEncoder();
						String encodePassword = m.encodePassword(sb.toString(), billUserByUsername.getSalt());
						billUserByUsername.setPassword(encodePassword);
						billRestPwdService.updatePwdByUserid(billUserByUsername);
						break;
					}
				} else {
					JOptionPane.showMessageDialog(null, "两次密码不一致,请重新输入.....");
					flag = true;
					break;
				}
			}
			if (flag) {
				flag = false;
				JOptionPane.showMessageDialog(null, pw1, "请再次输入你的密码 (剩余次数2次)", JOptionPane.PLAIN_MESSAGE);
				if (pw1.getPassword() == null || pw1.getPassword().length == 0
						|| pw1.getPassword().length != pw.getPassword().length) {
					JOptionPane.showMessageDialog(null, "两次密码不一致,请重新输入.....");
					JOptionPane.showMessageDialog(null, pw1, "请再次输入你的密码 (剩余次数1次)", JOptionPane.PLAIN_MESSAGE);
					if (pw1.getPassword() == null || pw1.getPassword().length == 0
							|| pw1.getPassword().length != pw.getPassword().length) {
						JOptionPane.showMessageDialog(null, "您的次数已用完.......");
						return;
					}
				}
				sb = new StringBuilder();
				for (int i = 0; i < pw.getPassword().length; i++) {
					if (pw1.getPassword()[i] == pw.getPassword()[i]) {
						sb.append(pw.getPassword()[i]);
						if (i + 1 == pw.getPassword().length) {
							Md5PwdEncoder m = new Md5PwdEncoder();
							String encodePassword = m.encodePassword(sb.toString(), billUserByUsername.getSalt());
							billUserByUsername.setPassword(encodePassword);
							billRestPwdService.updatePwdByUserid(billUserByUsername);
							break;
						}
					} else {
						JOptionPane.showMessageDialog(null, "两次密码不一致,请重新输入.....");
						flag = true;
						break;
					}
				}
				if (flag) {
					flag = false;
					JOptionPane.showMessageDialog(null, pw1, "请再次输入你的密码 (剩余次数1次)", JOptionPane.PLAIN_MESSAGE);
					if (pw1.getPassword() == null || pw1.getPassword().length == 0
							|| pw1.getPassword().length != pw.getPassword().length) {
						JOptionPane.showMessageDialog(null, "您的次数已用完.......");
						return;
					}
					sb = new StringBuilder();
					for (int i = 0; i < pw.getPassword().length; i++) {
						if (pw1.getPassword()[i] == pw.getPassword()[i]) {
							sb.append(pw.getPassword()[i]);
							if (i + 1 == pw.getPassword().length) {
								Md5PwdEncoder m = new Md5PwdEncoder();
								String encodePassword = m.encodePassword(sb.toString(), billUserByUsername.getSalt());
								billUserByUsername.setPassword(encodePassword);
								billRestPwdService.updatePwdByUserid(billUserByUsername);
								break;
							}
						} else {
							JOptionPane.showMessageDialog(null, "您的次数已用完.....");
							flag = true;
							return;
						}
					}
				}
			}
			String path = VarStatic.PREFIX + "resource\\img\\success.png";
			String message1 = "<html><head></head><body>" + "<font size=8 style=\"font-family:'STXingkai'\"><center>"
					+ "密码更新成功" + "</center></font></body></html>";
			JOptionPane.showConfirmDialog(this, message1, "密码更新成功", JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.INFORMATION_MESSAGE, new ImageIcon(path));
		}

	}

}
