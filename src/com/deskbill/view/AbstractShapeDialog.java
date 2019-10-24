package com.deskbill.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import com.deskbill.tools.DateChooser;
import com.deskbill.tools.GUITools;

public abstract class AbstractShapeDialog extends JDialog {

	private static final long serialVersionUID = -1925695550338965549L;
	protected JComboBox<String> parentBox = new JComboBox<String>();// 大分类下拉列表
	protected JComboBox<String> chartBox = new JComboBox<String>();// 小分类下拉列表
	protected JTextField beginDateTxt = new JTextField(6);//开始查询时间
	protected JTextField endDateTxt = new JTextField(6);//结束查询时间
	protected JButton okBut = new JButton("查询");//结束查询时间
//	protected JLabel chartLabel = new JLabel();//结束查询时间
	protected ChartPanel chartPanel = null;//结束查询时间
	
	public AbstractShapeDialog(JDialog dialog) {
		super(dialog);
		this.setSize(680, 400);// 设置大小

	}

	protected void initDialog(JFreeChart freeChart) {
		this.addComponent(freeChart);
		addLister();
		this.init();
	}

	private void init() {
		this.pack();
		GUITools.center(this);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}

	public abstract List<String> getImagePaths();

	private void addComponent(JFreeChart freeChart) {
		JPanel selectPabel = new JPanel();
		this.add(selectPabel, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		this.add(panel, BorderLayout.CENTER);
		// 起始日期
		JLabel beginDateLabel = new JLabel("起始：");
		beginDateLabel.setBounds(30, 70, 60, 28);
		this.beginDateTxt.setBounds(70, 70, 80, 28);
		this.beginDateTxt.setEditable(false);
//		beginDateTxt.setText(String.format("%tF", DateUtils.getFirstDayOfMethod()));
		DateChooser.getInstance().register(this.beginDateTxt);

		selectPabel.add(beginDateLabel);
		selectPabel.add(this.beginDateTxt);

		// 结束日期
		JLabel endDateLabel = new JLabel("至：");
		endDateLabel.setBounds(160, 70, 30, 28);
		this.endDateTxt.setBounds(190, 70, 80, 28);
		this.endDateTxt.setEditable(false);
//		endDateTxt.setText(String.format("%tF", new Date()));
		DateChooser.getInstance().register(this.endDateTxt);

		selectPabel.add(endDateLabel);
		selectPabel.add(this.endDateTxt);
		// "选择"下拉列表
		JLabel parentLabel = new JLabel("收/支：");
		parentBox.setModel(new DefaultComboBoxModel<String>(new String[] { "总收&总支", "各项支出", "各项收入" }));
		parentBox.setSelectedItem("总收&总支");
		parentBox.setSelectedIndex(0);
		selectPabel.add(parentLabel);
		selectPabel.add(parentBox);

		JLabel selectChartType = new JLabel("图表类型：");
		chartBox.setModel(new DefaultComboBoxModel<String>(new String[] { "饼状图", /*"折线图",*/ "柱状图" }));
		chartBox.setSelectedItem("饼状图");
		chartBox.setSelectedIndex(0);
		selectPabel.add(selectChartType);
		selectPabel.add(chartBox);
		selectPabel.add(okBut);
		chartPanel = new ChartPanel(freeChart);
		panel.add(chartPanel);
	}
	
	private void addLister() {
		okBut.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				confim();
			}
		});
		
		parentBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				parentChange();
			}
		});
		chartBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				chartChange();
			}
		});
		
	}

	protected abstract void chartChange();

	protected abstract void parentChange();

	protected abstract void confim();
}
