package com.deskbill.tools;

import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.geom.Arc2D;
import java.awt.geom.Arc2D.Double;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;

import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.entity.CategoryItemEntity;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.labels.CategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.renderer.category.CategoryItemRendererState;
import org.jfree.data.category.CategoryDataset;
import org.jfree.ui.RectangleEdge;

public class CylinderRenderer extends BarRenderer3D
{
	private static final long serialVersionUID = 6405830893321473430L;

public CylinderRenderer()
  {
  }

  public CylinderRenderer(double paramDouble1, double paramDouble2)
  {
    super(paramDouble1, paramDouble2);
  }

  public void drawItem(Graphics2D paramGraphics2D, CategoryItemRendererState paramCategoryItemRendererState, Rectangle2D paramRectangle2D, CategoryPlot paramCategoryPlot, CategoryAxis paramCategoryAxis, ValueAxis paramValueAxis, CategoryDataset paramCategoryDataset, int paramInt1, int paramInt2, int paramInt3)
  {
    Number localNumber = paramCategoryDataset.getValue(paramInt1, paramInt2);
    if (localNumber == null)
      return;
    double d1 = localNumber.doubleValue();
    Rectangle2D.Double localDouble = new Rectangle2D.Double(paramRectangle2D.getX(), paramRectangle2D.getY() + getYOffset(), paramRectangle2D.getWidth() - getXOffset(), paramRectangle2D.getHeight() - getYOffset());
    PlotOrientation localPlotOrientation = paramCategoryPlot.getOrientation();
    double d2 = calculateBarW0(paramCategoryPlot, localPlotOrientation, localDouble, paramCategoryAxis, paramCategoryItemRendererState, paramInt1, paramInt2);
    double[] arrayOfDouble = calculateBarL0L1(d1);
    if (arrayOfDouble == null)
      return;
    RectangleEdge localRectangleEdge = paramCategoryPlot.getRangeAxisEdge();
    float f1 = (float)paramValueAxis.valueToJava2D(arrayOfDouble[0], localDouble, localRectangleEdge);
    float f2 = (float)paramValueAxis.valueToJava2D(arrayOfDouble[1], localDouble, localRectangleEdge);
    float f3 = Math.min(f1, f2);
    float f4 = Math.abs(f2 - f1);
    GeneralPath localGeneralPath = new GeneralPath();
    Ellipse2D.Double localDouble1 = null;
    Double localObject1;
	if (localPlotOrientation == PlotOrientation.HORIZONTAL)
    {
      localGeneralPath.moveTo((float)(f3 + getXOffset() / 2.0D), (float)d2);
      localGeneralPath.lineTo((float)(f3 + f4 + getXOffset() / 2.0D), (float)d2);
      localObject1 = new Arc2D.Double(f3 + f4, d2, getXOffset(), paramCategoryItemRendererState.getBarWidth(), 90.0D, 180.0D, 0);
      localGeneralPath.append((Shape)localObject1, true);
      localGeneralPath.lineTo((float)(f3 + getXOffset() / 2.0D), (float)(d2 + paramCategoryItemRendererState.getBarWidth()));
      localObject1 = new Arc2D.Double(f3, d2, getXOffset(), paramCategoryItemRendererState.getBarWidth(), 270.0D, -180.0D, 0);
      localGeneralPath.append((Shape)localObject1, true);
      localGeneralPath.closePath();
      localDouble1 = new Ellipse2D.Double(f3 + f4, d2, getXOffset(), paramCategoryItemRendererState.getBarWidth());
    }
    else
    {
      localGeneralPath.moveTo((float)d2, (float)(f3 - getYOffset() / 2.0D));
      localGeneralPath.lineTo((float)d2, (float)(f3 + f4 - getYOffset() / 2.0D));
      localObject1 = new Arc2D.Double(d2, f3 + f4 - getYOffset(), paramCategoryItemRendererState.getBarWidth(), getYOffset(), 180.0D, 180.0D, 0);
      localGeneralPath.append((Shape)localObject1, true);
      localGeneralPath.lineTo((float)(d2 + paramCategoryItemRendererState.getBarWidth()), (float)(f3 - getYOffset() / 2.0D));
      localObject1 = new Arc2D.Double(d2, f3 - getYOffset(), paramCategoryItemRendererState.getBarWidth(), getYOffset(), 0.0D, -180.0D, 0);
      localGeneralPath.append((Shape)localObject1, true);
      localGeneralPath.closePath();
      localDouble1 = new Ellipse2D.Double(d2, f3 - getYOffset(), paramCategoryItemRendererState.getBarWidth(), getYOffset());
    }
    Object localObject11 = getItemPaint(paramInt1, paramInt2);
    if ((getGradientPaintTransformer() != null) && ((localObject11 instanceof GradientPaint)))
    {
      GradientPaint localObject2 = (GradientPaint)localObject11;
      localObject11 = getGradientPaintTransformer().transform((GradientPaint)localObject2, localGeneralPath);
    }
    paramGraphics2D.setPaint((Paint)localObject11);
    paramGraphics2D.fill(localGeneralPath);
    if ((localObject11 instanceof GradientPaint))
      paramGraphics2D.setPaint(((GradientPaint)localObject11).getColor2());
    if (localDouble1 != null)
      paramGraphics2D.fill(localDouble1);
    if ((isDrawBarOutline()) && (paramCategoryItemRendererState.getBarWidth() > 3.0D))
    {
      paramGraphics2D.setStroke(getItemOutlineStroke(paramInt1, paramInt2));
      paramGraphics2D.setPaint(getItemOutlinePaint(paramInt1, paramInt2));
      paramGraphics2D.draw(localGeneralPath);
      if (localDouble1 != null)
        paramGraphics2D.draw(localDouble1);
    }
    Object localObject2 = getItemLabelGenerator(paramInt1, paramInt2);
    if ((localObject2 != null) && (isItemLabelVisible(paramInt1, paramInt2)))
      drawItemLabel(paramGraphics2D, paramCategoryDataset, paramInt1, paramInt2, paramCategoryPlot, (CategoryItemLabelGenerator)localObject2, localGeneralPath.getBounds2D(), d1 < 0.0D);
    if (paramCategoryItemRendererState.getInfo() != null)
    {
      EntityCollection localEntityCollection = paramCategoryItemRendererState.getEntityCollection();
      if (localEntityCollection != null)
      {
        String str1 = null;
        CategoryToolTipGenerator localCategoryToolTipGenerator = getToolTipGenerator(paramInt1, paramInt2);
        if (localCategoryToolTipGenerator != null)
          str1 = localCategoryToolTipGenerator.generateToolTip(paramCategoryDataset, paramInt1, paramInt2);
        String str2 = null;
        if (getItemURLGenerator(paramInt1, paramInt2) != null)
          str2 = getItemURLGenerator(paramInt1, paramInt2).generateURL(paramCategoryDataset, paramInt1, paramInt2);
        CategoryItemEntity localCategoryItemEntity = new CategoryItemEntity(localGeneralPath.getBounds2D(), str1, str2, paramCategoryDataset, paramCategoryDataset.getRowKey(paramInt1), paramCategoryDataset.getColumnKey(paramInt2));
        localEntityCollection.add(localCategoryItemEntity);
      }
    }
  }
}

/* Location:           C:\Users\admin\Downloads\JAVASWT\jfreechart-1.0.19\jfreechart-1.0.19-demo.jar
 * Qualified Name:     demo.CylinderRenderer
 * JD-Core Version:    0.6.2
 */