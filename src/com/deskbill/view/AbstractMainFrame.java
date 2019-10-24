package com.deskbill.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.deskbill.tools.GUITools;
import com.deskbill.tools.StringUtils;
import com.deskbill.tools.VarStatic;

/**
 * 
 * 主窗体类，需要子类继承后，显示 包括两个按钮，功能，由子类实现
 */
public abstract class AbstractMainFrame extends JFrame {
	private static final long serialVersionUID = 3155513441329688921L;

	static {
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*
	 * 组件
	 */
	protected JButton ledgerBtn = new JButton("账务管理");// 账务管理
	protected JButton sortBtn = new JButton("分类管理");// 分类管理
	protected JButton registBtn = new JButton("注册");
	protected JButton loginBtn = new JButton("登录");

	protected JButton logoutBtn = new JButton("注销");
	protected JLabel forgetBtn = new JLabel("忘记密码");
	protected JLabel weixinBtn = new JLabel("微信公众号: 追梦乘风");
	protected JLabel aboutBtn = new JLabel(
			"<html>关于我们:<a href=\"www.lutongli.com\"><font color=\"#f5f4f2\" font-family='STKaiti'>www.lutongli.com</font></a></html>");

	protected JTextField userName = new JTextField();// 账号
	protected JPasswordField userPwd = new JPasswordField();// 密码
	protected JLabel regUserLabel = new JLabel();// 账号
	protected JLabel regPwdLabel = new JLabel();// 密码

	protected JLabel userNameLabel = new JLabel(new ImageIcon(VarStatic.PREFIX + "resource\\img\\user_login.png"));
	protected JLabel userPwdLabel = new JLabel(new ImageIcon(VarStatic.PREFIX + "resource\\img\\user_pwd.png"));

	public AbstractMainFrame() {
		this.init();// 初始化操作
		this.addComponent();// 添加组件
		this.addListener();// 添加监听器
	}

	// 初始化操作
	private void init() {
		this.setTitle("追梦乘风账单管理系统");// 标题
		this.setSize(600, 400);// 窗体大小与位置
		GUITools.center(this);// 设置窗口在屏幕上的位置
		this.setResizable(false);// 窗体大小固定
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 关闭窗口默认操作
	}

	// 添加组件
	private void addComponent() {
		ImageIcon imageIcon = new ImageIcon(VarStatic.PREFIX + "resource\\img\\logo.png");
		this.setIconImage(imageIcon.getImage());
		Font font01 = new Font("华文行楷", Font.BOLD | Font.ITALIC | Font.CENTER_BASELINE, 28);
		// this.add(this.titleLabel, BorderLayout.NORTH);
		JLabel northlabel = new JLabel("每一份支出都是投资  每一份收入都是回报", JLabel.CENTER);
		northlabel.setForeground(new Color(0, 162, 232));
		northlabel.setFont(font01);
		JPanel p = new JPanel();
		p.add(northlabel);
		p.setBackground(Color.DARK_GRAY);
		this.add(p, BorderLayout.NORTH);

		JPanel btnPanel = new JPanel() {
			private static final long serialVersionUID = 4998199893347614799L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon imageIcon = new ImageIcon(VarStatic.PREFIX + "resource\\img\\img29.jpg");
				// g.drawImage(imageIcon.getImage(), 0, 0, 350, 350, null);// 绘制图片与组件大小相同
				g.drawImage(imageIcon.getImage(), 0, 0, getSize().width, getSize().height, this);// 图片会自动缩放
			}

		};
		btnPanel.setLayout(null);
		this.getContentPane().add(btnPanel);

		this.add(btnPanel);

		ledgerBtn.setBounds(40, 20, 120, 50);
		sortBtn.setBounds(440, 20, 120, 50);

		Font font = new Font("华文彩云", Font.BOLD, 20);

		ledgerBtn.setFont(font);
		sortBtn.setFont(font);
		sortBtn.setVisible(false);
		ledgerBtn.setVisible(false);
		btnPanel.add(ledgerBtn);
		btnPanel.add(sortBtn);

		logoutBtn.setFont(new Font("华文行楷", Font.PLAIN, 14));
		logoutBtn.setBounds(470, 75, 60, 20);
		logoutBtn.setBackground(Color.RED);
		logoutBtn.setVisible(false);
		btnPanel.add(logoutBtn);
		// 注册登录
		userName.setBounds(290, 166, 150, 30);
		userPwd.setBounds(290, 206, 150, 30);

		userNameLabel.setBounds(200, 166, 150, 30);
		userPwdLabel.setBounds(200, 206, 150, 30);

		userPwd.setVisible(true);
		userName.setVisible(true);
		btnPanel.add(userNameLabel);
		btnPanel.add(userName);
		btnPanel.add(userPwdLabel);
		btnPanel.add(userPwd);
		regUserLabel.setFont(new Font("华文行楷", Font.PLAIN, 16));
		regUserLabel.setForeground(Color.RED);
		regPwdLabel.setFont(new Font("华文行楷", Font.PLAIN, 16));
		regPwdLabel.setForeground(Color.RED);
		regUserLabel.setBounds(440, 166, 150, 30);
		regPwdLabel.setBounds(440, 206, 150, 30);
		btnPanel.add(regPwdLabel);
		btnPanel.add(regUserLabel);

		// 注册登录
		loginBtn.setBounds(365, 250, 80, 30);
		registBtn.setBounds(265, 250, 80, 30);
		registBtn.setVisible(true);
		loginBtn.setVisible(true);
		btnPanel.add(loginBtn);
		btnPanel.add(registBtn);
		forgetBtn.setBounds(390, 290, 100, 30);
		forgetBtn.setFont(new Font("华文行楷", Font.PLAIN | Font.ROMAN_BASELINE, 14));
		forgetBtn.setForeground(Color.WHITE);
		forgetBtn.setToolTipText("忘记密码");
		forgetBtn.setDisplayedMnemonic('0');
		forgetBtn.setHorizontalAlignment(SwingConstants.CENTER);
		forgetBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));// 当鼠标位于标签上时呈手型
		btnPanel.add(forgetBtn);

		aboutBtn.setBounds(8, 285, 600, 30);
		aboutBtn.setFont(new Font("宋体", Font.PLAIN | Font.ROMAN_BASELINE, 12));
		aboutBtn.setForeground(Color.WHITE);
		aboutBtn.setToolTipText("<html>微信公众号: 追梦乘风 \r\n 网站:<a href=\"www.lutongli.com\"> www.lutongli.com</a></html>");
		aboutBtn.setDisplayedMnemonic('0');
		aboutBtn.setHorizontalAlignment(SwingConstants.CENTER);
		aboutBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));// 当鼠标位于标签上时呈手型
		btnPanel.add(aboutBtn);

		weixinBtn.setBounds(10, 290, 180, 30);
		weixinBtn.setFont(new Font("华文行楷", Font.PLAIN | Font.ROMAN_BASELINE, 16));
		weixinBtn.setForeground(Color.WHITE);
		weixinBtn.setToolTipText("<html>微信公众号: 追梦乘风 \r\n 网站:<a href=\"www.lutongli.com\"> www.lutongli.com</a></html>");
		weixinBtn.setDisplayedMnemonic('0');
		weixinBtn.setHorizontalAlignment(SwingConstants.CENTER);
		weixinBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));// 当鼠标位于标签上时呈手型
		btnPanel.add(weixinBtn);
	}

	// 添加监听器
	private void addListener() {
		// 账务管理模块，开启按钮
		ledgerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ledgerMng();
			}
		});
		ledgerBtn.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				super.keyPressed(e);
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					ledgerMng();
				}
			}

		});
		// 分类管理模块，开启按钮
		sortBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sortMng();
			}
		});
		sortBtn.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				super.keyPressed(e);
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					sortMng();
				}
			}

		});
		// 登录
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginMng();
			}
		});

		loginBtn.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				super.keyPressed(e);
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					loginMng();
				}
			}

		});

		logoutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logoutMng();
			}
		});

		logoutBtn.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				super.keyPressed(e);
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					logoutMng();
				}
			}

		});

		// 注册
		registBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registMng();
			}
		});
		registBtn.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				super.keyPressed(e);
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					registMng();
				}
			}

		});

		// 添加鼠标监听器
		weixinBtn.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseReleased(e);
				jlabel_mouseReleased(e);
			}

			private void jlabel_mouseReleased(MouseEvent e) {
				try {
					Runtime.getRuntime().exec("cmd /c start https://www.lutongli.com");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		});
		aboutBtn.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseReleased(e);
				jlabel_mouseReleased(e);
			}
			
			private void jlabel_mouseReleased(MouseEvent e) {
				try {
					Runtime.getRuntime().exec("cmd /c start https://www.lutongli.com");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
		});
		
		forgetBtn.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseReleased(e);
				forgetBtn_mouseReleased(e);
			}
			
		});
	}

	protected abstract void forgetBtn_mouseReleased(MouseEvent e);
	
	protected abstract void logoutMng();

	public abstract void ledgerMng();

	public abstract void sortMng();

	public abstract void loginMng();

	public abstract void registMng();
}
