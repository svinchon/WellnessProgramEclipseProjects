package demo;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.panel.CrosshairOverlay;
import org.jfree.chart.plot.Crosshair;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.Range;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleEdge;

public class CrosshairOverlayDemo1
  extends JFrame
{
  public CrosshairOverlayDemo1(String paramString)
  {
    super(paramString);
    setContentPane(createDemoPanel());
  }
  
  public static JPanel createDemoPanel()
  {
    return new MyDemoPanel();
  }
  
  public static void main(String[] paramArrayOfString)
  {
    SwingUtilities.invokeLater(new Runnable()
    {
      public void run()
      {
        CrosshairOverlayDemo1 localCrosshairOverlayDemo1 = new CrosshairOverlayDemo1("JFreeChart: CrosshairOverlayDemo1.java");
        localCrosshairOverlayDemo1.pack();
        localCrosshairOverlayDemo1.setVisible(true);
      }
    });
  }
  
  static class MyDemoPanel
    extends JPanel
    implements ChartMouseListener
  {
    private ChartPanel chartPanel;
    private Crosshair xCrosshair;
    private Crosshair yCrosshair;
    
    public MyDemoPanel()
    {
      super();
      JFreeChart localJFreeChart = createChart(createDataset());
      this.chartPanel = new ChartPanel(localJFreeChart);
      this.chartPanel.addChartMouseListener(this);
      CrosshairOverlay localCrosshairOverlay = new CrosshairOverlay();
      this.xCrosshair = new Crosshair((0.0D / 0.0D), Color.GRAY, new BasicStroke(0.0F));
      this.xCrosshair.setLabelVisible(true);
      this.yCrosshair = new Crosshair((0.0D / 0.0D), Color.GRAY, new BasicStroke(0.0F));
      this.yCrosshair.setLabelVisible(true);
      localCrosshairOverlay.addDomainCrosshair(this.xCrosshair);
      localCrosshairOverlay.addRangeCrosshair(this.yCrosshair);
      this.chartPanel.addOverlay(localCrosshairOverlay);
      add(this.chartPanel);
    }
    
    private JFreeChart createChart(XYDataset paramXYDataset)
    {
      JFreeChart localJFreeChart = ChartFactory.createXYLineChart("CrosshairOverlayDemo1", "X", "Y", paramXYDataset);
      return localJFreeChart;
    }
    
    private XYDataset createDataset()
    {
      XYSeries localXYSeries = new XYSeries("S1");
      for (int i = 0; i < 10; i++) {
        localXYSeries.add(i, i + Math.random() * 4.0D);
      }
      XYSeriesCollection localXYSeriesCollection = new XYSeriesCollection(localXYSeries);
      return localXYSeriesCollection;
    }
    
    public void chartMouseClicked(ChartMouseEvent paramChartMouseEvent) {}
    
    public void chartMouseMoved(ChartMouseEvent paramChartMouseEvent)
    {
      Rectangle2D localRectangle2D = this.chartPanel.getScreenDataArea();
      JFreeChart localJFreeChart = paramChartMouseEvent.getChart();
      XYPlot localXYPlot = (XYPlot)localJFreeChart.getPlot();
      ValueAxis localValueAxis = localXYPlot.getDomainAxis();
      double d1 = localValueAxis.java2DToValue(paramChartMouseEvent.getTrigger().getX(), localRectangle2D, RectangleEdge.BOTTOM);
      if (!localValueAxis.getRange().contains(d1)) {
        d1 = (0.0D / 0.0D);
      }
      double d2 = DatasetUtilities.findYValue(localXYPlot.getDataset(), 0, d1);
      this.xCrosshair.setValue(d1);
      this.yCrosshair.setValue(d2);
    }
  }
}


/* Location:           C:\Users\vinchs\Desktop\temp\
 * Qualified Name:     demo.CrosshairOverlayDemo1
 * JD-Core Version:    0.7.0.1
 */