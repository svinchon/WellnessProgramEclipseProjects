package demo;

import java.awt.Dimension;
import java.awt.font.TextAttribute;
import java.text.AttributedString;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYDotRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;

public class ScatterPlotDemo5
  extends ApplicationFrame
{
  public ScatterPlotDemo5(String paramString)
  {
    super(paramString);
    JPanel localJPanel = createDemoPanel();
    localJPanel.setPreferredSize(new Dimension(500, 270));
    setContentPane(localJPanel);
  }
  
  public static XYDataset createDataset()
  {
    XYSeriesCollection localXYSeriesCollection = new XYSeriesCollection();
    XYSeries localXYSeries1 = new XYSeries("S1");
    XYSeries localXYSeries2 = new XYSeries("S2");
    for (int i = 0; i < 100; i++)
    {
      localXYSeries1.add(Math.random() * 50.0D, Math.random() * 100.0D);
      localXYSeries2.add(Math.random() * 50.0D, Math.random() * 100.0D);
    }
    localXYSeriesCollection.addSeries(localXYSeries1);
    localXYSeriesCollection.addSeries(localXYSeries2);
    return localXYSeriesCollection;
  }
  
  public static JPanel createDemoPanel()
  {
    JFreeChart localJFreeChart = ChartFactory.createScatterPlot("Scatter Plot Demo 5", "X", "Y", createDataset());
    XYPlot localXYPlot = (XYPlot)localJFreeChart.getPlot();
    localXYPlot.setBackgroundPaint(null);
    localXYPlot.setAxisOffset(RectangleInsets.ZERO_INSETS);
    localXYPlot.setOutlineVisible(false);
    XYDotRenderer localXYDotRenderer = new XYDotRenderer();
    localXYDotRenderer.setDotWidth(4);
    localXYDotRenderer.setDotHeight(4);
    localXYPlot.setRenderer(localXYDotRenderer);
    localXYPlot.setDomainCrosshairVisible(true);
    localXYPlot.setRangeCrosshairVisible(true);
    NumberAxis localNumberAxis1 = (NumberAxis)localXYPlot.getDomainAxis();
    AttributedString localAttributedString1 = new AttributedString("H20");
    localAttributedString1.addAttribute(TextAttribute.SUPERSCRIPT, TextAttribute.SUPERSCRIPT_SUB, 1, 2);
    localNumberAxis1.setAttributedLabel(localAttributedString1);
    localNumberAxis1.setPositiveArrowVisible(true);
    localNumberAxis1.setAutoRangeIncludesZero(false);
    NumberAxis localNumberAxis2 = (NumberAxis)localXYPlot.getRangeAxis();
    AttributedString localAttributedString2 = new AttributedString("kg x 106");
    localAttributedString2.addAttribute(TextAttribute.SUPERSCRIPT, TextAttribute.SUPERSCRIPT_SUPER, 7, 8);
    localNumberAxis2.setAttributedLabel(localAttributedString2);
    localNumberAxis2.setPositiveArrowVisible(true);
    ChartPanel localChartPanel = new ChartPanel(localJFreeChart);
    localChartPanel.setMouseWheelEnabled(true);
    return localChartPanel;
  }
  
  public static void main(String[] paramArrayOfString)
  {
    ScatterPlotDemo5 localScatterPlotDemo5 = new ScatterPlotDemo5("JFreeChart: ScatterPlotDemo5.java");
    localScatterPlotDemo5.pack();
    RefineryUtilities.centerFrameOnScreen(localScatterPlotDemo5);
    localScatterPlotDemo5.setVisible(true);
  }
}


/* Location:           C:\Users\vinchs\Desktop\temp\
 * Qualified Name:     demo.ScatterPlotDemo5
 * JD-Core Version:    0.7.0.1
 */