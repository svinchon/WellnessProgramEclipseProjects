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
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.RectangleEdge;

public class CrosshairOverlayDemo2
  extends JFrame
{
  public CrosshairOverlayDemo2(String paramString)
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
        CrosshairOverlayDemo2 localCrosshairOverlayDemo2 = new CrosshairOverlayDemo2("JFreeChart: CrosshairOverlayDemo2.java");
        localCrosshairOverlayDemo2.pack();
        localCrosshairOverlayDemo2.setVisible(true);
      }
    });
  }
  
  static class MyDemoPanel
    extends JPanel
    implements ChartMouseListener
  {
    private static final int SERIES_COUNT = 4;
    private ChartPanel chartPanel;
    private Crosshair xCrosshair;
    private Crosshair[] yCrosshairs;
    
    public MyDemoPanel()
    {
      super();
      JFreeChart localJFreeChart = createChart(createDataset());
      this.chartPanel = new ChartPanel(localJFreeChart);
      this.chartPanel.addChartMouseListener(this);
      CrosshairOverlay localCrosshairOverlay = new CrosshairOverlay();
      this.xCrosshair = new Crosshair((0.0D / 0.0D), Color.GRAY, new BasicStroke(0.0F));
      this.xCrosshair.setLabelVisible(true);
      localCrosshairOverlay.addDomainCrosshair(this.xCrosshair);
      this.yCrosshairs = new Crosshair[4];
      for (int i = 0; i < 4; i++)
      {
        this.yCrosshairs[i] = new Crosshair((0.0D / 0.0D), Color.GRAY, new BasicStroke(0.0F));
        this.yCrosshairs[i].setLabelVisible(true);
        if (i % 2 != 0) {
          this.yCrosshairs[i].setLabelAnchor(RectangleAnchor.TOP_RIGHT);
        }
        localCrosshairOverlay.addRangeCrosshair(this.yCrosshairs[i]);
      }
      this.chartPanel.addOverlay(localCrosshairOverlay);
      add(this.chartPanel);
    }
    
    private JFreeChart createChart(XYDataset paramXYDataset)
    {
      JFreeChart localJFreeChart = ChartFactory.createXYLineChart("CrosshairOverlayDemo2", "X", "Y", paramXYDataset);
      return localJFreeChart;
    }
    
    private XYDataset createDataset()
    {
      XYSeriesCollection localXYSeriesCollection = new XYSeriesCollection();
      for (int i = 0; i < 4; i++)
      {
        XYSeries localXYSeries = new XYSeries("S" + i);
        for (int j = 0; j < 10; j++) {
          localXYSeries.add(j, j + Math.random() * 4.0D);
        }
        localXYSeriesCollection.addSeries(localXYSeries);
      }
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
      this.xCrosshair.setValue(d1);
      for (int i = 0; i < 4; i++)
      {
        double d2 = DatasetUtilities.findYValue(localXYPlot.getDataset(), i, d1);
        this.yCrosshairs[i].setValue(d2);
      }
    }
  }
}


/* Location:           C:\Users\vinchs\Desktop\temp\
 * Qualified Name:     demo.CrosshairOverlayDemo2
 * JD-Core Version:    0.7.0.1
 */