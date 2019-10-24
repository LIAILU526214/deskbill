package com.deskbill.tools;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Paint;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.GradientPaintTransformType;
import org.jfree.ui.StandardGradientPaintTransformer;

public class JFreeChartUtils {
	/**
	 * 使用jfreechart生成图片
	 * 
	 * @param title    标题
	 * @param totalMap 数据（key:分类名称, value：该分类的汇总数据，例如分类名称为"工资收入"，汇总数据为所有工资收入的和）
	 * @param sum      汇总数据和（例如"工资收入汇总 + 股票收入汇总 + ...."）(已取消)
	 * @param path     生成图片保存路径
	 */
	public static JFreeChart pie(String title, Map<String, Double> totalMap, String path) {
		double sum = 0D;
		Set<Entry<String, Double>> entrySet = totalMap.entrySet();
		for (Entry<String, Double> entry : entrySet) {
			sum += entry.getValue();
		}
		DefaultPieDataset pieDataset = new DefaultPieDataset();
		for (String dataName : totalMap.keySet()) {
			double dataValue = totalMap.get(dataName);
			String bf = String.format("%.2f%%", dataValue / sum * 100);
			dataName = dataName + ":" + dataValue + "元(" + bf + ")";
			pieDataset.setValue(dataName, dataValue);
		}
		JFreeChart chart = ChartFactory.createPieChart3D(title, pieDataset, true, true, false);
		Font titleFont = new Font("黑体", Font.BOLD, 20);
		TextTitle textTitle = chart.getTitle();
		textTitle.setFont(titleFont);// 为标题设置上字体

		Font plotFont = new Font("宋体", Font.PLAIN, 16);
		PiePlot plot = (PiePlot) chart.getPlot();
		plot.setLabelFont(plotFont); // 为饼图元素设置上字体

		Font LegendFont = new Font("楷体", Font.PLAIN, 18);
		LegendTitle legend = chart.getLegend(0);
		legend.setItemFont(LegendFont);// 为图例说明设置字体
//		try {
//			ChartUtilities.saveChartAsJPEG(new File(path), chart, 500, 300);
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
		return chart;
	}

	static class CustomCylinderRenderer extends CylinderRenderer {
		private static final long serialVersionUID = 1L;
		private Paint[] colors;

		public CustomCylinderRenderer(Paint[] paramArrayOfPaint) {
			this.colors = paramArrayOfPaint;
		}

		public Paint getItemPaint(int paramInt1, int paramInt2) {
			return this.colors[(paramInt2 % this.colors.length)];
		}
	}

	public static JFreeChart barChart(String title, Map<String, Double> totalMap, String path) {

		DefaultCategoryDataset localDefaultCategoryDataset = new DefaultCategoryDataset();
		for (String dataName : totalMap.keySet()) {
			double dataValue = totalMap.get(dataName);
			localDefaultCategoryDataset.setValue(dataValue, "", dataName);
		}
		Paint[] arrayOfPaint = new Paint[5];
		arrayOfPaint[0] = new GradientPaint(0.0F, 0.0F, Color.red, 0.0F, 0.0F, Color.white);
		arrayOfPaint[1] = new GradientPaint(0.0F, 0.0F, Color.green, 0.0F, 0.0F, Color.white);
		arrayOfPaint[2] = new GradientPaint(0.0F, 0.0F, Color.blue, 0.0F, 0.0F, Color.white);
		arrayOfPaint[3] = new GradientPaint(0.0F, 0.0F, Color.orange, 0.0F, 0.0F, Color.white);
		arrayOfPaint[4] = new GradientPaint(0.0F, 0.0F, Color.magenta, 0.0F, 0.0F, Color.white);

		JFreeChart localJFreeChart = ChartFactory.createBarChart3D(title, "类别", "值(元)", localDefaultCategoryDataset,
				PlotOrientation.VERTICAL, false, true, false);
		CategoryPlot localCategoryPlot = (CategoryPlot) localJFreeChart.getPlot();
		localCategoryPlot.setRangePannable(true);
		CategoryAxis domainAxis = localCategoryPlot.getDomainAxis();
		CustomCylinderRenderer localCustomCylinderRenderer = new CustomCylinderRenderer(arrayOfPaint);
		localCustomCylinderRenderer.setGradientPaintTransformer(
				new StandardGradientPaintTransformer(GradientPaintTransformType.CENTER_HORIZONTAL));
		localCustomCylinderRenderer.setBaseOutlinePaint(Color.gray);
		localCustomCylinderRenderer.setBaseOutlineStroke(new BasicStroke(0.3F));
		localCustomCylinderRenderer.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
		localCategoryPlot.setRenderer(localCustomCylinderRenderer);
		ChartUtilities.applyCurrentTheme(localJFreeChart);
		
		Font titleFont = new Font("黑体", Font.BOLD, 20);
		TextTitle textTitle = localJFreeChart.getTitle();
		textTitle.setFont(titleFont);// 为标题设置上字体
        domainAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 11));
        domainAxis.setLabelFont(new Font("宋体", Font.PLAIN, 20));
        NumberAxis numberaxis = (NumberAxis) localCategoryPlot.getRangeAxis();
        numberaxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 12));
        numberaxis.setLabelFont(new Font("楷体", Font.PLAIN, 16));
        //localJFreeChart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 12));
//		try {
//			ChartUtilities.saveChartAsJPEG(new File(path), localJFreeChart, 500, 300);
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
		return localJFreeChart;
	}

	public static JFreeChart lineChart(String title, Map<String, Double> totalMap, String path) {
		
//		XYSeriesCollection localXYSeriesCollection = new XYSeriesCollection();
//	      for (int i = 0; i < 4; i++)
//	      {
//	        XYSeries localXYSeries = new XYSeries("S" + i);
//	        for (int j = 0; j < 10; j++)
//	          localXYSeries.add(j, j + Math.random() * 4.0D);
//	        localXYSeriesCollection.addSeries(localXYSeries);
//	      }
		 XYSeriesCollection localXYSeriesCollection = new XYSeriesCollection();
		 for (String dataName : totalMap.keySet()) {
				double dataValue = totalMap.get(dataName);
				XYSeries localXYSeries = new XYSeries(dataName);
				localXYSeries.add(0, dataValue);
				localXYSeriesCollection.addSeries(localXYSeries);
			}
		JFreeChart chart = ChartFactory.createXYLineChart(title, "类别", "值(元)", localXYSeriesCollection);
		Font titleFont = new Font("黑体", Font.BOLD, 20);
		TextTitle textTitle = chart.getTitle();
		textTitle.setFont(titleFont);// 为标题设置上字体
		//时序图
		XYPlot plot = (XYPlot) chart.getPlot();
		//纵轴字体
		plot.getRangeAxis().setLabelFont(new Font("宋体", Font.BOLD, 15));
		//横轴框里的标题字体
		chart.getLegend().setItemFont(new Font("宋体", Font.ITALIC, 15));
		//横轴列表字体
		plot.getDomainAxis().setTickLabelFont(new Font("新宋体", 1, 15));
		//横轴小标题字体
		plot.getDomainAxis().setLabelFont(new Font("新宋体", 1, 12));
		
		//折线图
		/*chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));
		CategoryPlot localCategoryPlot = (CategoryPlot) chart.getPlot();
		CategoryAxis domainAxis = localCategoryPlot.getDomainAxis();
		------设置X轴坐标上的文字-----------
		domainAxis.setTickLabelFont(new Font("黑体", Font.PLAIN, 11));
		------设置X轴的标题文字------------
		domainAxis.setLabelFont(new Font("宋体", Font.PLAIN, 12));
		NumberAxis numberaxis = (NumberAxis) localCategoryPlot.getRangeAxis();
		------设置Y轴坐标上的文字-----------
		numberaxis.setTickLabelFont(new Font("黑体", Font.PLAIN, 12));
		------设置Y轴的标题文字------------
		numberaxis.setLabelFont(new Font("黑体", Font.PLAIN, 12));*/
		return chart;
	}
}
