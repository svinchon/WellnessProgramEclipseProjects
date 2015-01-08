package demo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.text.DecimalFormat;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RefineryUtilities;

public class BarChartDemo11
  extends ApplicationFrame
{
  public BarChartDemo11(String paramString)
  {
    super(paramString);
    JPanel localJPanel = createDemoPanel();
    localJPanel.setPreferredSize(new Dimension(500, 270));
    setContentPane(localJPanel);
  }
  
  private static CategoryDataset createDataset()
  {
    DefaultCategoryDataset localDefaultCategoryDataset = new DefaultCategoryDataset();
    localDefaultCategoryDataset.addValue(33.0D, "S1", "GNU General Public License (GPL) 2.0");
    localDefaultCategoryDataset.addValue(13.0D, "S1", "Apache License 2.0");
    localDefaultCategoryDataset.addValue(12.0D, "S1", "GNU General Public License (GPL) 3.0");
    localDefaultCategoryDataset.addValue(11.0D, "S1", "MIT License");
    localDefaultCategoryDataset.addValue(7.0D, "S1", "BSD License 2.0");
    localDefaultCategoryDataset.addValue(6.0D, "S1", "Artistic Licence (Perl)");
    localDefaultCategoryDataset.addValue(6.0D, "S1", "GNU Lesser General Public License (LGPL) 2.1");
    localDefaultCategoryDataset.addValue(3.0D, "S1", "GNU Lesser General Public License (LGPL) 3.0");
    localDefaultCategoryDataset.addValue(2.0D, "S1", "Eclipse Public License");
    localDefaultCategoryDataset.addValue(1.0D, "S1", "Code Project 1.02 License");
    return localDefaultCategoryDataset;
  }
  
  private static JFreeChart createChart(CategoryDataset paramCategoryDataset)
  {
    JFreeChart localJFreeChart = ChartFactory.createBarChart("Open Source Projects By License", "License", "Percent", paramCategoryDataset);
    localJFreeChart.removeLegend();
    TextTitle localTextTitle = new TextTitle("Source: http://www.blackducksoftware.com/resources/data/top-20-licenses (as at 30 Aug 2013)", new Font("Dialog", 0, 9));
    localTextTitle.setPosition(RectangleEdge.BOTTOM);
    localJFreeChart.addSubtitle(localTextTitle);
    CategoryPlot localCategoryPlot = (CategoryPlot)localJFreeChart.getPlot();
    localCategoryPlot.setOrientation(PlotOrientation.HORIZONTAL);
    localCategoryPlot.setDomainGridlinesVisible(true);
    localCategoryPlot.getDomainAxis().setMaximumCategoryLabelWidthRatio(0.8F);
    NumberAxis localNumberAxis = (NumberAxis)localCategoryPlot.getRangeAxis();
    localNumberAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
    BarRenderer localBarRenderer = (BarRenderer)localCategoryPlot.getRenderer();
    localBarRenderer.setDrawBarOutline(false);
    StandardCategoryToolTipGenerator localStandardCategoryToolTipGenerator = new StandardCategoryToolTipGenerator("{1}: {2} percent", new DecimalFormat("0"));
    localBarRenderer.setBaseToolTipGenerator(localStandardCategoryToolTipGenerator);
    GradientPaint localGradientPaint = new GradientPaint(0.0F, 0.0F, Color.BLUE, 0.0F, 0.0F, new Color(0, 0, 64));
    localBarRenderer.setSeriesPaint(0, localGradientPaint);
    return localJFreeChart;
  }
  
  public static JPanel createDemoPanel()
  {
    JFreeChart localJFreeChart = createChart(createDataset());
    return new ChartPanel(localJFreeChart);
  }
  
  public static void main(String[] paramArrayOfString)
  {
    BarChartDemo11 localBarChartDemo11 = new BarChartDemo11("JFreeChart: BarChartDemo11.java");
    localBarChartDemo11.pack();
    RefineryUtilities.centerFrameOnScreen(localBarChartDemo11);
    localBarChartDemo11.setVisible(true);
  }
}


/* Location:           C:\Users\vinchs\Desktop\temp\
 * Qualified Name:     demo.BarChartDemo11
 * JD-Core Version:    0.7.0.1
 */