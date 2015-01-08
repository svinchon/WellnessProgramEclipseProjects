package demo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Point;
import java.text.DecimalFormat;
import javax.swing.JPanel;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CenterTextMode;
import org.jfree.chart.plot.RingPlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;

public class RingChartDemo2
  extends ApplicationFrame
{
  private static final long serialVersionUID = 1L;
  
  public RingChartDemo2(String paramString)
  {
    super(paramString);
    setContentPane(createDemoPanel());
  }
  
  private static PieDataset createDataset()
  {
    DefaultPieDataset localDefaultPieDataset = new DefaultPieDataset();
    localDefaultPieDataset.setValue("A", new Double(0.653D));
    localDefaultPieDataset.setValue("B", new Double(0.347D));
    return localDefaultPieDataset;
  }
  
  private static JFreeChart createChart(PieDataset paramPieDataset)
  {
    RingPlot localRingPlot = new RingPlot(paramPieDataset);
    localRingPlot.setCenterTextMode(CenterTextMode.VALUE);
    localRingPlot.setCenterTextFont(new Font("SansSerif", 1, 24));
    localRingPlot.setCenterTextColor(Color.LIGHT_GRAY);
    localRingPlot.setCenterTextFormatter(new DecimalFormat("0.0%"));
    JFreeChart localJFreeChart = new JFreeChart("Machine Capacity", JFreeChart.DEFAULT_TITLE_FONT, localRingPlot, false);
    localJFreeChart.setBackgroundPaint(new GradientPaint(new Point(0, 0), new Color(20, 20, 20), new Point(400, 200), Color.DARK_GRAY));
    TextTitle localTextTitle = localJFreeChart.getTitle();
    localTextTitle.setHorizontalAlignment(HorizontalAlignment.LEFT);
    localTextTitle.setPaint(new Color(240, 240, 240));
    localTextTitle.setFont(new Font("Arial", 1, 26));
    localRingPlot.setBackgroundPaint(null);
    localRingPlot.setOutlineVisible(false);
    localRingPlot.setLabelGenerator(null);
    localRingPlot.setSectionPaint("A", Color.ORANGE);
    localRingPlot.setSectionPaint("B", new Color(100, 100, 100));
    localRingPlot.setSectionDepth(0.05D);
    localRingPlot.setSectionOutlinesVisible(false);
    localRingPlot.setShadowPaint(null);
    return localJFreeChart;
  }
  
  public static JPanel createDemoPanel()
  {
    JFreeChart localJFreeChart = createChart(createDataset());
    localJFreeChart.setPadding(new RectangleInsets(4.0D, 8.0D, 2.0D, 2.0D));
    ChartPanel localChartPanel = new ChartPanel(localJFreeChart);
    localChartPanel.setMouseWheelEnabled(true);
    localChartPanel.setPreferredSize(new Dimension(600, 300));
    return localChartPanel;
  }
  
  public static void main(String[] paramArrayOfString)
  {
    RingChartDemo2 localRingChartDemo2 = new RingChartDemo2("JFreeChart: Ring Chart Demo 2");
    localRingChartDemo2.pack();
    RefineryUtilities.centerFrameOnScreen(localRingChartDemo2);
    localRingChartDemo2.setVisible(true);
  }
}


/* Location:           C:\Users\vinchs\Desktop\temp\
 * Qualified Name:     demo.RingChartDemo2
 * JD-Core Version:    0.7.0.1
 */