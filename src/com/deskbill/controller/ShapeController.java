package com.deskbill.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JDialog;

import org.jfree.chart.JFreeChart;

import com.deskbill.services.LedgerService;
import com.deskbill.tools.JFreeChartUtils;
import com.deskbill.view.AbstractShapeDialog;

public class ShapeController extends AbstractShapeDialog {
	private static final long serialVersionUID = -3688222479668470570L;
	private List<String> pieImgPaths = new ArrayList<String>();
	private LedgerService ledgerService = new LedgerService();
	String path = "D:\\log\\totle.jpg";

	public ShapeController(JDialog dialog) {
		super(dialog);
		// TODO 自动生成的构造函数存根

	}

	/**
	 * 
	 * @param dialog
	 * @param totalMap
	 * @param chartName 图表名称
	 */
	public ShapeController(JDialog dialog, String beginTime, String endTime, Map<String, Double> totalMap,
			String chartName) {
		// TODO Auto-generated constructor stub
		super(dialog);
		beginDateTxt.setText(beginTime);
		endDateTxt.setText(endTime);
		setTitle("收/支比重统计");
		JFreeChart freeChart = JFreeChartUtils.pie(chartName, totalMap, path);
		pieImgPaths.add(path);
		initDialog(freeChart);
	}

	@Override
	public List<String> getImagePaths() {
		// TODO 自动生成的方法存根
		return pieImgPaths;
	}

	@Override
	protected void confim() {
//		initDialog();
		String beginTime = beginDateTxt.getText();
		String endTime = endDateTxt.getText();
		// "总收&总支", "各项支出", "各项收入"
		String type = parentBox.getSelectedItem().toString();
		// TODO Auto-generated method stub
		Object selectedItem = chartBox.getSelectedItem();
		Map<String, Double> totalMap = null;
		String title = "总收/支比重统计";
		if ("总收&总支".equals(type)) {
			totalMap = new HashMap<String, Double>();
			double inMoney = ledgerService.queryCountLedgerInOrPay(null, "收入", beginTime, endTime, 1);
			double payMoney = ledgerService.queryCountLedgerInOrPay(null, "支出", beginTime, endTime, 1);
			totalMap.put("总收入", inMoney);
			totalMap.put("总支出", payMoney);
		}
		if ("各项支出".equals(type)) {
			title = "各项支出比重统计";
			totalMap = ledgerService.queryCountInOrPayByGroupType("支出", beginTime, endTime, 1);
		}
		if ("各项收入".equals(type)) {
			title = "各项收入比重统计";
			totalMap = ledgerService.queryCountInOrPayByGroupType("收入", beginTime, endTime, 1);
		}
		if (totalMap.isEmpty()) {
			title+="(暂无数据...)";
		}
			if (selectedItem instanceof String) {
				JFreeChart chart ;
				if ("饼状图".equals(selectedItem.toString())) {
					chart = JFreeChartUtils.pie(title, totalMap, path);
					chartPanel.setChart(chart);
				}
				if ("折线图".equals(selectedItem.toString())) {
					chart = JFreeChartUtils.lineChart(title, totalMap, path);
					chartPanel.setChart(chart);
				}
				if ("柱状图".equals(selectedItem.toString())) {
					chart = JFreeChartUtils.barChart(title, totalMap, path);
					chartPanel.setChart(chart);
				}
//				Image image = ImageIO.read(new File(path));
//				ImageIcon imageIcon = new ImageIcon(image);
//				imageIcon.getImage().flush();
//				chartLabel.setIcon(imageIcon);
//				chartLabel.updateUI();
//				chartLabel.repaint();
				
			}
	}

	@Override
	protected void parentChange() {
		// TODO Auto-generated method stub
	}

	@Override
	protected void chartChange() {
		// TODO Auto-generated method stub
		
	}

}
