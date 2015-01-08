package demo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.text.DecimalFormat;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.util.DefaultShadowGenerator;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class TimeSeriesDemo14
  extends ApplicationFrame
{
  public TimeSeriesDemo14(String paramString)
  {
    super(paramString);
    JPanel localJPanel = createDemoPanel();
    localJPanel.setPreferredSize(new Dimension(720, 340));
    setContentPane(localJPanel);
  }
  
  private static JFreeChart createChart(XYDataset paramXYDataset)
  {
    JFreeChart localJFreeChart = ChartFactory.createTimeSeriesChart("Browser Market Share", "Date", "Market Share", paramXYDataset, true, true, false);
    localJFreeChart.addSubtitle(new TextTitle("Source: http://gs.statcounter.com"));
    XYPlot localXYPlot = (XYPlot)localJFreeChart.getPlot();
    localXYPlot.setShadowGenerator(new DefaultShadowGenerator());
    DateAxis localDateAxis = (DateAxis)localXYPlot.getDomainAxis();
    localDateAxis.setLowerMargin(0.0D);
    NumberAxis localNumberAxis = (NumberAxis)localXYPlot.getRangeAxis();
    localNumberAxis.setNumberFormatOverride(new DecimalFormat("0.0%"));
    localXYPlot.setDomainCrosshairVisible(true);
    localXYPlot.setRangeCrosshairVisible(true);
    XYItemRenderer localXYItemRenderer = localXYPlot.getRenderer();
    if ((localXYItemRenderer instanceof XYLineAndShapeRenderer))
    {
      XYLineAndShapeRenderer localXYLineAndShapeRenderer = (XYLineAndShapeRenderer)localXYItemRenderer;
      localXYLineAndShapeRenderer.setBaseStroke(new BasicStroke(3.0F));
      localXYLineAndShapeRenderer.setAutoPopulateSeriesStroke(false);
      localXYLineAndShapeRenderer.setSeriesPaint(0, new Color(1742401));
      localXYLineAndShapeRenderer.setSeriesPaint(1, new Color(10934634));
      localXYLineAndShapeRenderer.setSeriesPaint(2, new Color(16625249));
      localXYLineAndShapeRenderer.setSeriesPaint(3, new Color(16777151));
    }
    return localJFreeChart;
  }
  
  private static XYDataset createDataset()
  {
    TimeSeriesCollection localTimeSeriesCollection = new TimeSeriesCollection();
    localTimeSeriesCollection.addSeries(createChromeData());
    localTimeSeriesCollection.addSeries(createFirefoxData());
    localTimeSeriesCollection.addSeries(createInternetExplorerData());
    localTimeSeriesCollection.addSeries(createSafariData());
    return localTimeSeriesCollection;
  }
  
  private static TimeSeries createChromeData()
  {
    TimeSeries localTimeSeries = new TimeSeries("Chrome");
    localTimeSeries.add(new Month(6, 2013), 0.4268D);
    localTimeSeries.add(new Month(5, 2013), 0.4138D);
    localTimeSeries.add(new Month(4, 2013), 0.3915D);
    localTimeSeries.add(new Month(3, 2013), 0.3807D);
    localTimeSeries.add(new Month(2, 2013), 0.3709D);
    localTimeSeries.add(new Month(1, 2013), 0.3652D);
    localTimeSeries.add(new Month(12, 2012), 0.3642D);
    localTimeSeries.add(new Month(11, 2012), 0.3572D);
    localTimeSeries.add(new Month(10, 2012), 0.3477D);
    localTimeSeries.add(new Month(9, 2012), 0.3421D);
    localTimeSeries.add(new Month(8, 2012), 0.3359D);
    localTimeSeries.add(new Month(7, 2012), 0.3381D);
    localTimeSeries.add(new Month(6, 2012), 0.3276D);
    localTimeSeries.add(new Month(5, 2012), 0.3243D);
    localTimeSeries.add(new Month(4, 2012), 0.3123D);
    localTimeSeries.add(new Month(3, 2012), 0.3087D);
    localTimeSeries.add(new Month(2, 2012), 0.2984D);
    localTimeSeries.add(new Month(1, 2012), 0.284D);
    localTimeSeries.add(new Month(12, 2011), 0.2727D);
    localTimeSeries.add(new Month(11, 2011), 0.2569D);
    localTimeSeries.add(new Month(10, 2011), 0.25D);
    localTimeSeries.add(new Month(9, 2011), 0.2361D);
    localTimeSeries.add(new Month(8, 2011), 0.2316D);
    localTimeSeries.add(new Month(7, 2011), 0.2214D);
    localTimeSeries.add(new Month(6, 2011), 0.2065D);
    localTimeSeries.add(new Month(5, 2011), 0.1936D);
    localTimeSeries.add(new Month(4, 2011), 0.1829D);
    localTimeSeries.add(new Month(3, 2011), 0.1737D);
    localTimeSeries.add(new Month(2, 2011), 0.1654D);
    localTimeSeries.add(new Month(1, 2011), 0.1568D);
    localTimeSeries.add(new Month(12, 2010), 0.1485D);
    localTimeSeries.add(new Month(11, 2010), 0.1335D);
    localTimeSeries.add(new Month(10, 2010), 0.1239D);
    localTimeSeries.add(new Month(9, 2010), 0.1154D);
    localTimeSeries.add(new Month(8, 2010), 0.1076D);
    localTimeSeries.add(new Month(7, 2010), 0.0988D);
    localTimeSeries.add(new Month(6, 2010), 0.0924D);
    localTimeSeries.add(new Month(5, 2010), 0.0861D);
    localTimeSeries.add(new Month(4, 2010), 0.08060000000000001D);
    localTimeSeries.add(new Month(3, 2010), 0.07290000000000001D);
    localTimeSeries.add(new Month(2, 2010), 0.0672D);
    localTimeSeries.add(new Month(1, 2010), 0.0604D);
    localTimeSeries.add(new Month(12, 2009), 0.0545D);
    localTimeSeries.add(new Month(11, 2009), 0.0466D);
    localTimeSeries.add(new Month(10, 2009), 0.0417D);
    localTimeSeries.add(new Month(9, 2009), 0.0369D);
    localTimeSeries.add(new Month(8, 2009), 0.0338D);
    localTimeSeries.add(new Month(7, 2009), 0.0301D);
    localTimeSeries.add(new Month(6, 2009), 0.0282D);
    localTimeSeries.add(new Month(5, 2009), 0.0242D);
    localTimeSeries.add(new Month(4, 2009), 0.0207D);
    localTimeSeries.add(new Month(3, 2009), 0.0173D);
    localTimeSeries.add(new Month(2, 2009), 0.0152D);
    localTimeSeries.add(new Month(1, 2009), 0.0138D);
    return localTimeSeries;
  }
  
  private static TimeSeries createFirefoxData()
  {
    TimeSeries localTimeSeries = new TimeSeries("Firefox");
    localTimeSeries.add(new Month(6, 2013), 0.2001D);
    localTimeSeries.add(new Month(5, 2013), 0.1976D);
    localTimeSeries.add(new Month(4, 2013), 0.2006D);
    localTimeSeries.add(new Month(3, 2013), 0.2087D);
    localTimeSeries.add(new Month(2, 2013), 0.2134D);
    localTimeSeries.add(new Month(1, 2013), 0.2142D);
    localTimeSeries.add(new Month(12, 2012), 0.2189D);
    localTimeSeries.add(new Month(11, 2012), 0.2237D);
    localTimeSeries.add(new Month(10, 2012), 0.2232D);
    localTimeSeries.add(new Month(9, 2012), 0.224D);
    localTimeSeries.add(new Month(8, 2012), 0.2285D);
    localTimeSeries.add(new Month(7, 2012), 0.2373D);
    localTimeSeries.add(new Month(6, 2012), 0.2456D);
    localTimeSeries.add(new Month(5, 2012), 0.2555D);
    localTimeSeries.add(new Month(4, 2012), 0.2487D);
    localTimeSeries.add(new Month(3, 2012), 0.2498D);
    localTimeSeries.add(new Month(2, 2012), 0.2488D);
    localTimeSeries.add(new Month(1, 2012), 0.2478D);
    localTimeSeries.add(new Month(12, 2011), 0.2527D);
    localTimeSeries.add(new Month(11, 2011), 0.2523D);
    localTimeSeries.add(new Month(10, 2011), 0.2639D);
    localTimeSeries.add(new Month(9, 2011), 0.2679D);
    localTimeSeries.add(new Month(8, 2011), 0.2749D);
    localTimeSeries.add(new Month(7, 2011), 0.2795D);
    localTimeSeries.add(new Month(6, 2011), 0.2834D);
    localTimeSeries.add(new Month(5, 2011), 0.2929D);
    localTimeSeries.add(new Month(4, 2011), 0.2967D);
    localTimeSeries.add(new Month(3, 2011), 0.2998D);
    localTimeSeries.add(new Month(2, 2011), 0.3037D);
    localTimeSeries.add(new Month(1, 2011), 0.3068D);
    localTimeSeries.add(new Month(12, 2010), 0.3076D);
    localTimeSeries.add(new Month(11, 2010), 0.3117D);
    localTimeSeries.add(new Month(10, 2010), 0.3124D);
    localTimeSeries.add(new Month(9, 2010), 0.315D);
    localTimeSeries.add(new Month(8, 2010), 0.3109D);
    localTimeSeries.add(new Month(7, 2010), 0.3069D);
    localTimeSeries.add(new Month(6, 2010), 0.3115D);
    localTimeSeries.add(new Month(5, 2010), 0.3164D);
    localTimeSeries.add(new Month(4, 2010), 0.3174D);
    localTimeSeries.add(new Month(3, 2010), 0.3127D);
    localTimeSeries.add(new Month(2, 2010), 0.3182D);
    localTimeSeries.add(new Month(1, 2010), 0.3164D);
    localTimeSeries.add(new Month(12, 2009), 0.3197D);
    localTimeSeries.add(new Month(11, 2009), 0.3221D);
    localTimeSeries.add(new Month(10, 2009), 0.3182D);
    localTimeSeries.add(new Month(9, 2009), 0.3134D);
    localTimeSeries.add(new Month(8, 2009), 0.3128D);
    localTimeSeries.add(new Month(7, 2009), 0.305D);
    localTimeSeries.add(new Month(6, 2009), 0.3033D);
    localTimeSeries.add(new Month(5, 2009), 0.2875D);
    localTimeSeries.add(new Month(4, 2009), 0.2967D);
    localTimeSeries.add(new Month(3, 2009), 0.294D);
    localTimeSeries.add(new Month(2, 2009), 0.2785D);
    localTimeSeries.add(new Month(1, 2009), 0.2703D);
    return localTimeSeries;
  }
  
  private static TimeSeries createInternetExplorerData()
  {
    TimeSeries localTimeSeries = new TimeSeries("Internet Explorer");
    localTimeSeries.add(new Month(6, 2013), 0.2544D);
    localTimeSeries.add(new Month(5, 2013), 0.2772D);
    localTimeSeries.add(new Month(4, 2013), 0.2971D);
    localTimeSeries.add(new Month(3, 2013), 0.293D);
    localTimeSeries.add(new Month(2, 2013), 0.2982D);
    localTimeSeries.add(new Month(1, 2013), 0.3069D);
    localTimeSeries.add(new Month(12, 2012), 0.3078D);
    localTimeSeries.add(new Month(11, 2012), 0.3123D);
    localTimeSeries.add(new Month(10, 2012), 0.3208D);
    localTimeSeries.add(new Month(9, 2012), 0.327D);
    localTimeSeries.add(new Month(8, 2012), 0.3285D);
    localTimeSeries.add(new Month(7, 2012), 0.3204D);
    localTimeSeries.add(new Month(6, 2012), 0.3231D);
    localTimeSeries.add(new Month(5, 2012), 0.3212D);
    localTimeSeries.add(new Month(4, 2012), 0.3407D);
    localTimeSeries.add(new Month(3, 2012), 0.3481D);
    localTimeSeries.add(new Month(2, 2012), 0.3575D);
    localTimeSeries.add(new Month(1, 2012), 0.3745D);
    localTimeSeries.add(new Month(12, 2011), 0.3865D);
    localTimeSeries.add(new Month(11, 2011), 0.4063D);
    localTimeSeries.add(new Month(10, 2011), 0.4018D);
    localTimeSeries.add(new Month(9, 2011), 0.4166D);
    localTimeSeries.add(new Month(8, 2011), 0.4189D);
    localTimeSeries.add(new Month(7, 2011), 0.4245D);
    localTimeSeries.add(new Month(6, 2011), 0.4358D);
    localTimeSeries.add(new Month(5, 2011), 0.4387D);
    localTimeSeries.add(new Month(4, 2011), 0.4452D);
    localTimeSeries.add(new Month(3, 2011), 0.4511D);
    localTimeSeries.add(new Month(2, 2011), 0.4544D);
    localTimeSeries.add(new Month(1, 2011), 0.46D);
    localTimeSeries.add(new Month(12, 2010), 0.4694D);
    localTimeSeries.add(new Month(11, 2010), 0.4816D);
    localTimeSeries.add(new Month(10, 2010), 0.4921D);
    localTimeSeries.add(new Month(9, 2010), 0.4987D);
    localTimeSeries.add(new Month(8, 2010), 0.5134D);
    localTimeSeries.add(new Month(7, 2010), 0.5268000000000001D);
    localTimeSeries.add(new Month(6, 2010), 0.5286D);
    localTimeSeries.add(new Month(5, 2010), 0.5277D);
    localTimeSeries.add(new Month(4, 2010), 0.5326D);
    localTimeSeries.add(new Month(3, 2010), 0.5444D);
    localTimeSeries.add(new Month(2, 2010), 0.545D);
    localTimeSeries.add(new Month(1, 2010), 0.5525D);
    localTimeSeries.add(new Month(12, 2009), 0.5572D);
    localTimeSeries.add(new Month(11, 2009), 0.5657D);
    localTimeSeries.add(new Month(10, 2009), 0.5796D);
    localTimeSeries.add(new Month(9, 2009), 0.5837D);
    localTimeSeries.add(new Month(8, 2009), 0.5869D);
    localTimeSeries.add(new Month(7, 2009), 0.6011D);
    localTimeSeries.add(new Month(6, 2009), 0.5949D);
    localTimeSeries.add(new Month(5, 2009), 0.6209D);
    localTimeSeries.add(new Month(4, 2009), 0.6188D);
    localTimeSeries.add(new Month(3, 2009), 0.6252D);
    localTimeSeries.add(new Month(2, 2009), 0.6443D);
    localTimeSeries.add(new Month(1, 2009), 0.6541D);
    return localTimeSeries;
  }
  
  private static TimeSeries createSafariData()
  {
    TimeSeries localTimeSeries = new TimeSeries("Safari");
    localTimeSeries.add(new Month(6, 2013), 0.0839D);
    localTimeSeries.add(new Month(5, 2013), 0.0796D);
    localTimeSeries.add(new Month(4, 2013), 0.08D);
    localTimeSeries.add(new Month(3, 2013), 0.08500000000000001D);
    localTimeSeries.add(new Month(2, 2013), 0.08599999999999999D);
    localTimeSeries.add(new Month(1, 2013), 0.083D);
    localTimeSeries.add(new Month(12, 2012), 0.07920000000000001D);
    localTimeSeries.add(new Month(11, 2012), 0.0783D);
    localTimeSeries.add(new Month(10, 2012), 0.0781D);
    localTimeSeries.add(new Month(9, 2012), 0.077D);
    localTimeSeries.add(new Month(8, 2012), 0.07389999999999999D);
    localTimeSeries.add(new Month(7, 2012), 0.0712D);
    localTimeSeries.add(new Month(6, 2012), 0.07000000000000001D);
    localTimeSeries.add(new Month(5, 2012), 0.07090000000000001D);
    localTimeSeries.add(new Month(4, 2012), 0.0713D);
    localTimeSeries.add(new Month(3, 2012), 0.0672D);
    localTimeSeries.add(new Month(2, 2012), 0.0677D);
    localTimeSeries.add(new Month(1, 2012), 0.0662D);
    localTimeSeries.add(new Month(12, 2011), 0.0608D);
    localTimeSeries.add(new Month(11, 2011), 0.0592D);
    localTimeSeries.add(new Month(10, 2011), 0.0593D);
    localTimeSeries.add(new Month(9, 2011), 0.056D);
    localTimeSeries.add(new Month(8, 2011), 0.0519D);
    localTimeSeries.add(new Month(7, 2011), 0.0517D);
    localTimeSeries.add(new Month(6, 2011), 0.0507D);
    localTimeSeries.add(new Month(5, 2011), 0.0501D);
    localTimeSeries.add(new Month(4, 2011), 0.0504D);
    localTimeSeries.add(new Month(3, 2011), 0.0502D);
    localTimeSeries.add(new Month(2, 2011), 0.0508D);
    localTimeSeries.add(new Month(1, 2011), 0.0509D);
    localTimeSeries.add(new Month(12, 2010), 0.0479D);
    localTimeSeries.add(new Month(11, 2010), 0.047D);
    localTimeSeries.add(new Month(10, 2010), 0.0456D);
    localTimeSeries.add(new Month(9, 2010), 0.0442D);
    localTimeSeries.add(new Month(8, 2010), 0.0423D);
    localTimeSeries.add(new Month(7, 2010), 0.0409D);
    localTimeSeries.add(new Month(6, 2010), 0.0407D);
    localTimeSeries.add(new Month(5, 2010), 0.0414D);
    localTimeSeries.add(new Month(4, 2010), 0.0423D);
    localTimeSeries.add(new Month(3, 2010), 0.0416D);
    localTimeSeries.add(new Month(2, 2010), 0.0408D);
    localTimeSeries.add(new Month(1, 2010), 0.0376D);
    localTimeSeries.add(new Month(12, 2009), 0.0348D);
    localTimeSeries.add(new Month(11, 2009), 0.0367D);
    localTimeSeries.add(new Month(10, 2009), 0.0347D);
    localTimeSeries.add(new Month(9, 2009), 0.0328D);
    localTimeSeries.add(new Month(8, 2009), 0.0325D);
    localTimeSeries.add(new Month(7, 2009), 0.0302D);
    localTimeSeries.add(new Month(6, 2009), 0.0293D);
    localTimeSeries.add(new Month(5, 2009), 0.0265D);
    localTimeSeries.add(new Month(4, 2009), 0.0275D);
    localTimeSeries.add(new Month(3, 2009), 0.0273D);
    localTimeSeries.add(new Month(2, 2009), 0.0259D);
    localTimeSeries.add(new Month(1, 2009), 0.0257D);
    return localTimeSeries;
  }
  
  public static JPanel createDemoPanel()
  {
    JFreeChart localJFreeChart = createChart(createDataset());
    ChartPanel localChartPanel = new ChartPanel(localJFreeChart);
    localChartPanel.setMouseWheelEnabled(true);
    return localChartPanel;
  }
  
  public static void main(String[] paramArrayOfString)
  {
    TimeSeriesDemo14 localTimeSeriesDemo14 = new TimeSeriesDemo14("JFreeChart: Time Series Demo 14");
    localTimeSeriesDemo14.pack();
    RefineryUtilities.centerFrameOnScreen(localTimeSeriesDemo14);
    localTimeSeriesDemo14.setVisible(true);
  }
}


/* Location:           C:\Users\vinchs\Desktop\temp\
 * Qualified Name:     demo.TimeSeriesDemo14
 * JD-Core Version:    0.7.0.1
 */