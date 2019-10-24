package com.deskbill.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.deskbill.domain.BillResetPwd;
import com.deskbill.domain.BillUser;
import com.deskbill.services.BillResetPwdService;
import com.deskbill.services.BillUserService;
import com.deskbill.tools.Md5PwdEncoder;
import com.deskbill.tools.StringUtils;
import com.deskbill.tools.VarStatic;
import com.deskbill.view.AbstractRegUserDialog;

public class RegisterController extends AbstractRegUserDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	private JPanel mainPanel;
	private JTextField userNameTxt;
	private JTextField userNickTxt;
	private JTextField emailTxt;
	private JPasswordField userPwdTxt;
	private JPasswordField agPwdTxt;
	private JTextField quctTxt;
	private JTextField answerTxt;
	private JTextField hintTxt;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	private JLabel label5;
	private JLabel label6;
	private JLabel label7;
	private JLabel label8;
	private JLabel label11;
	private JLabel label21;
	private JLabel label31;
	private JLabel label41;
	private JLabel label51;
	private JLabel label61;
	private JLabel label71;
	private JLabel label81;
	private JButton regBut;
	private JButton cancelBut;

	private BillUserService billUserService = new BillUserService();
	private BillResetPwdService billResetPwdService = new BillResetPwdService();

	public RegisterController(JFrame frame) {
		super(frame);
		// 设置主界面
		setResizable(false);
		setTitle("欢迎注册");
		setSize(620, 450); /* 设置窗口大小 */
		setLocationRelativeTo(null);
		setUndecorated(true);

		// 设置输入框位置
		mainPanel = new JPanel() {
			private static final long serialVersionUID = 4769407855604249872L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon imageIcon = new ImageIcon(VarStatic.PREFIX + "resource\\img\\4.jpg");
				// g.drawImage(imageIcon.getImage(), 0, 0, 350, 350, null);// 绘制图片与组件大小相同
				g.drawImage(imageIcon.getImage(), 0, 0, getSize().width, getSize().height, this);// 图片会自动缩放
			}
		};
		mainPanel.setOpaque(false);
		mainPanel.setBounds(0, 0, 600, 400);
		mainPanel.setLayout(null); /* 使用绝对布局 */
		this.add(mainPanel);

		userNameTxt = new JTextField();
		userNameTxt.setColumns(10);
		userNameTxt.setBounds(250, 70, 200, 27);
//		userNameTxt.setMargin(new Insets(-1, -1, -1, -1));
//		userNameTxt.setOpaque(false);
//		userNameTxt.setBorder(null);
		mainPanel.add(userNameTxt);

		label11 = new JLabel("              ");
		label11.setBounds(450, 70, 200, 27);
		mainPanel.add(label11);

		userNickTxt = new JTextField();
		userNickTxt.setBounds(250, 100, 200, 27);
		mainPanel.add(userNickTxt);
//		userNickTxt.setOpaque(false);
//		userNickTxt.setBorder(null);
		userNickTxt.setColumns(10);

		label21 = new JLabel("              ");
		label21.setBounds(450, 100, 200, 27);
		mainPanel.add(label21);

		emailTxt = new JTextField();
		emailTxt.setColumns(10);
		emailTxt.setBounds(250, 130, 200, 27);
//		emailTxt.setOpaque(false);
//		emailTxt.setBorder(null);
		mainPanel.add(emailTxt);

		label31 = new JLabel("              ");
		label31.setBounds(450, 130, 200, 27);
		mainPanel.add(label31);

		userPwdTxt = new JPasswordField();
		userPwdTxt.setColumns(10);
		userPwdTxt.setBounds(250, 160, 200, 27);
//		userPwdTxt.setOpaque(false);
//		userPwdTxt.setBorder(null);
		mainPanel.add(userPwdTxt);
		label41 = new JLabel("              ");
		label41.setBounds(450, 160, 200, 27);
		mainPanel.add(label41);

		agPwdTxt = new JPasswordField();
		agPwdTxt.setColumns(10);
		agPwdTxt.setBounds(250, 190, 200, 27);
//		agPwdTxt.setOpaque(false);
//		agPwdTxt.setBorder(null);
		mainPanel.add(agPwdTxt);

		label51 = new JLabel("              ");
		label51.setBounds(450, 190, 200, 27);
		mainPanel.add(label51);

		quctTxt = new JTextField();
		quctTxt.setColumns(10);
		quctTxt.setBounds(250, 220, 200, 27);
//		quctTxt.setOpaque(false);
//		quctTxt.setBorder(null);
		mainPanel.add(quctTxt);

		label61 = new JLabel("              ");
		label61.setBounds(450, 220, 200, 27);
		mainPanel.add(label61);

		answerTxt = new JTextField();
		answerTxt.setColumns(10);
		answerTxt.setBounds(250, 250, 200, 27);
//		answerTxt.setOpaque(false);
//		answerTxt.setBorder(null);
		mainPanel.add(answerTxt);

		label71 = new JLabel("              ");
		label71.setBounds(450, 250, 200, 27);
		mainPanel.add(label71);

		hintTxt = new JTextField();
		hintTxt.setColumns(10);
		hintTxt.setBounds(250, 280, 200, 27);
//		hintTxt.setOpaque(false);
//		hintTxt.setBorder(null);
		mainPanel.add(hintTxt);

		label81 = new JLabel("              ");
		label81.setBounds(450, 280, 200, 27);
		mainPanel.add(label81);
		// 设置ComboBox的位置和属性

		// 设置标签位置
		label1 = new JLabel("注册账号:");
		label1.setBounds(175, 70, 200, 27);
		label1.setFont(new Font("华文行楷", Font.PLAIN, 16));
		mainPanel.add(label1);

		label2 = new JLabel("昵    称:");
		label2.setBounds(175, 100, 200, 27);
		label2.setFont(new Font("华文行楷", Font.PLAIN, 16));
		mainPanel.add(label2);

		label3 = new JLabel("邮    箱:");
		label3.setBounds(175, 130, 200, 27);
		label3.setFont(new Font("华文行楷", Font.PLAIN, 16));
		mainPanel.add(label3);

		label4 = new JLabel("密    码:");
		label4.setBounds(175, 160, 200, 27);
		label4.setFont(new Font("华文行楷", Font.PLAIN, 16));
		mainPanel.add(label4);

		label5 = new JLabel("确认密码:");
		label5.setBounds(175, 190, 200, 25);
		label5.setFont(new Font("华文行楷", Font.PLAIN, 16));
		mainPanel.add(label5);

		label6 = new JLabel("密保问题:");
		label6.setBounds(175, 220, 200, 27);
		label6.setFont(new Font("华文行楷", Font.PLAIN, 16));
		mainPanel.add(label6);

		label7 = new JLabel("密保答案:");
		label7.setBounds(175, 250, 200, 27);
		label7.setFont(new Font("华文行楷", Font.PLAIN, 16));
		mainPanel.add(label7);

		label8 = new JLabel("密保提示:");
		label8.setBounds(175, 280, 200, 27);
		label8.setFont(new Font("华文行楷", Font.PLAIN, 16));
		mainPanel.add(label8);

		// 增加注册按钮
		regBut = new JButton("注   册");
		cancelBut = new JButton("取   消");
		mainPanel.add(regBut);
		mainPanel.add(cancelBut);
		regBut.setBounds(350, 330, 120, 33);
		cancelBut.setBounds(175, 330, 120, 33);
//		cancelBut.setContentAreaFilled(false);			/*异常*/
//		regBut.setContentAreaFilled(false);			/*异常*/
//		regBut.setIcon(new ImageIcon(VarStatic.PREFIX +"resource\\img\\register.png"));
//		cancelBut.setIcon(new ImageIcon(VarStatic.PREFIX +"resource\\img\\cancel.png"));
//		regBut.setText("注册");
//		cancelBut.setText("取消");
		Font font01 = new Font("华文行楷", Font.BOLD | Font.CENTER_BASELINE, 16);
		regBut.setFont(font01);
		cancelBut.setFont(font01);
		regBut.setBackground(new Color(0, 162, 232));
		cancelBut.setBackground(new Color(0, 162, 232));
		regBut.setVisible(true);
		cancelBut.setVisible(true);

		// 按钮交互
		cancelBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancelBut.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					dispose();
				}
			}
		});
		regBut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				boolean regUserData = regUserData();
				if (regUserData) {
					dispose();
				}
			}
		});
		regBut.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					boolean regUserData = regUserData();
					if (regUserData) {
						dispose();
					}
				}
			}

		});
	}

	private boolean regUserData() {
		// TODO Auto-generated method stub
		if (StringUtils.isNull(userNameTxt.getText())) {
			label11.setFont(new Font("华文行楷", Font.PLAIN, 14));
			label11.setForeground(Color.RED);
			label11.setText("注册账号不能为空");
			return false;
		}else {
			label11.setText("        ");
		}
		if (StringUtils.isNull(userNickTxt.getText())) {
			label21.setFont(new Font("华文行楷", Font.PLAIN, 14));
			label21.setForeground(Color.RED);
			label21.setText("昵称不能为空");
			return false;
		}else {
			label21.setText("        ");
		}
		if (StringUtils.isNull(emailTxt.getText())) {
			label31.setFont(new Font("华文行楷", Font.PLAIN, 14));
			label31.setForeground(Color.RED);
			label31.setText("邮箱不能为空");
			return false;
		}else {
			label31.setText("        ");
		}
		if (!StringUtils.isEmail(emailTxt.getText())) {
			label31.setFont(new Font("华文行楷", Font.PLAIN, 14));
			label31.setForeground(Color.RED);
			label31.setText("邮箱格式不正确");
			return false;
		}else {
			label31.setText("        ");
		}
		// 校验密码
		char[] password = agPwdTxt.getPassword();
		char[] password2 = userPwdTxt.getPassword();
		if (password.length != password2.length || password == null || password.length == 0) {
			label51.setFont(new Font("华文行楷", Font.PLAIN, 14));
			label51.setForeground(Color.RED);
			label51.setText("两次密码不一致");
			return false;
		}else {
			label51.setText("        ");
		}
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i < password2.length; i++) {
			if (password[i] == password2[i]) {
				sBuilder.append(password[i]);
			} else {
				label51.setFont(new Font("华文行楷", Font.PLAIN, 14));
				label51.setForeground(Color.RED);
				label51.setText("两次密码不一致");
				return false;
			}
		}
		if (StringUtils.isNull(quctTxt.getText())) {
			label61.setFont(new Font("华文行楷", Font.PLAIN, 14));
			label61.setForeground(Color.RED);
			label61.setText("密保问题不能为空");
			return false;
		}else {
			label61.setText("        ");
		}
		if (StringUtils.isNull(answerTxt.getText())) {
			label71.setFont(new Font("华文行楷", Font.PLAIN, 14));
			label71.setForeground(Color.RED);
			label71.setText("密保答案不能为空");
			return false;
		}else {
			label71.setText("        ");
		}
		if (StringUtils.isNull(hintTxt.getText())) {
			label81.setFont(new Font("华文行楷", Font.PLAIN, 14));
			label81.setForeground(Color.RED);
			label81.setText("提示信息不能为空");
			return false;
		}else {
			label81.setText("        ");
		}

		BillUser buser = billUserService.getBillUserByUsername(userNameTxt.getText());
		if (buser != null) {
			return false;
		}

		BillUser billUser = new BillUser();
		billUser.setCreateTime(new Date());
		billUser.setEmail(emailTxt.getText());
		billUser.setPhoto("resource\\img\\favicon.png");
		billUser.setState(1);
		billUser.setUpdateTime(new Date());
		String randomString = StringUtils.getRandomString();
		Md5PwdEncoder md5PwdEncoder = new Md5PwdEncoder();
		String encodePassword = md5PwdEncoder.encodePassword(sBuilder.toString(), randomString);
		billUser.setSalt(randomString);
		billUser.setPassword(encodePassword);
		billUser.setUserName(userNameTxt.getText());
		billUser.setUserNick(userNickTxt.getText());
		int result = billUserService.addBillUser(billUser);
		if (result == 0) {
			return false;
		}
		BillUser billUserByUsername = billUserService.getBillUserByUsername(billUser.getUserName());
		if (billUserByUsername == null) {
			return false;
		}
		BillResetPwd resetPwd = new BillResetPwd();
		resetPwd.setAnswer(answerTxt.getText());
		resetPwd.setQuestion(quctTxt.getText());
		resetPwd.setUserId(billUserByUsername.getId());
		resetPwd.setHint(hintTxt.getText());
		int addBillResPwd = billResetPwdService.addBillResPwd(resetPwd);
		if (addBillResPwd == 0) {
			return false;
		}
		String path = VarStatic.PREFIX+"resource\\img\\success.png";
		String message = "<html><head></head><body>" + "<font size=8 style=\"font-family:'STXingkai'\"><center>" + "注册成功"  + "</center></font></body></html>";
		JOptionPane.showConfirmDialog(this, message, "注册成功", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon(path));
		return true;
	}
}
