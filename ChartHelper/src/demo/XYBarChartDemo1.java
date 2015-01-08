package demo;

import java.awt.Dimension;
import java.awt.Font;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickMarkPosition;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class XYBarChartDemo1
  extends ApplicationFrame
{
  public XYBarChartDemo1(String paramString)
  {
    super(paramString);
    JPanel localJPanel = createDemoPanel();
    localJPanel.setPreferredSize(new Dimension(500, 270));
    setContentPane(localJPanel);
  }
  
  private static JFreeChart createChart(IntervalXYDataset paramIntervalXYDataset)
  {
    JFreeChart localJFreeChart = ChartFactory.createXYBarChart("State Executions - USA", "Year", true, "Number of People", paramIntervalXYDataset, PlotOrientation.VERTICAL, true, false, false);
    localJFreeChart.addSubtitle(new TextTitle("Source: http://www.amnestyusa.org/abolish/listbyyear.do", new Font("Dialog", 2, 10)));
    XYPlot localXYPlot = (XYPlot)localJFreeChart.getPlot();
    XYBarRenderer localXYBarRenderer = (XYBarRenderer)localXYPlot.getRenderer();
    StandardXYToolTipGenerator localStandardXYToolTipGenerator = new StandardXYToolTipGenerator("{1} = {2}", new SimpleDateFormat("yyyy"), new DecimalFormat("0"));
    localXYBarRenderer.setBaseToolTipGenerator(localStandardXYToolTipGenerator);
    localXYBarRenderer.setMargin(0.1D);
    DateAxis localDateAxis = (DateAxis)localXYPlot.getDomainAxis();
    localDateAxis.setTickMarkPosition(DateTickMarkPosition.MIDDLE);
    localDateAxis.setLowerMargin(0.01D);
    localDateAxis.setUpperMargin(0.01D);
    ChartUtilities.applyCurrentTheme(localJFreeChart);
    return localJFreeChart;
  }
  
  private static IntervalXYDataset createDataset()
  {
    TimeSeries localTimeSeries = new TimeSeries("Executions", "Year", "Count");
    try
    {
      localTimeSeries.add(new Year(1976), new Integer(0));
      localTimeSeries.add(new Year(1977), new Integer(1));
      localTimeSeries.add(new Year(1978), new Integer(0));
      localTimeSeries.add(new Year(1979), new Integer(2));
      localTimeSeries.add(new Year(1980), new Integer(0));
      localTimeSeries.add(new Year(1981), new Integer(1));
      localTimeSeries.add(new Year(1982), new Integer(2));
      localTimeSeries.add(new Year(1983), new Integer(5));
      localTimeSeries.add(new Year(1984), new Integer(21));
      localTimeSeries.add(new Year(1985), new Integer(18));
      localTimeSeries.add(new Year(1986), new Integer(18));
      localTimeSeries.add(new Year(1987), new Integer(25));
      localTimeSeries.add(new Year(1988), new Integer(11));
      localTimeSeries.add(new Year(1989), new Integer(16));
      localTimeSeries.add(new Year(1990), new Integer(23));
      localTimeSeries.add(new Year(1991), new Integer(14));
      localTimeSeries.add(new Year(1992), new Integer(31));
      localTimeSeries.add(new Year(1993), new Integer(38));
      localTimeSeries.add(new Year(1994), new Integer(31));
      localTimeSeries.add(new Year(1995), new Integer(56));
      localTimeSeries.add(new Year(1996), new Integer(45));
      localTimeSeries.add(new Year(1997), new Integer(74));
      localTimeSeries.add(new Year(1998), new Integer(68));
      localTimeSeries.add(new Year(1999), new Integer(98));
      localTimeSeries.add(new Year(2000), new Integer(85));
      localTimeSeries.add(new Year(2001), new Integer(66));
      localTimeSeries.add(new Year(2002), new Integer(71));
      localTimeSeries.add(new Year(2003), new Integer(65));
      localTimeSeries.add(new Year(2004), new Integer(59));
      localTimeSeries.add(new Year(2005), new Integer(60));
    }
    catch (Exception localException)
    {
      System.err.println(localException.getMessage());
    }
    TimeSeriesCollection localTimeSeriesCollection = new TimeSeriesCollection(localTimeSeries);
    return localTimeSeriesCollection;
  }
  
  public static JPanel createDemoPanel()
  {
    return new ChartPanel(createChart(createDataset()));
  }
  
  public static void main(String[] paramArrayOfString)
  {
    XYBarChartDemo1 localXYBarChartDemo1 = new XYBarChartDemo1("State Executions - USA");
    localXYBarChartDemo1.pack();
    RefineryUtilities.centerFrameOnScreen(localXYBarChartDemo1);
    localXYBarChartDemo1.setVisible(true);
  }
}


/* Location:           C:\Users\vinchs\Desktop\temp\
 * Qualified Name:     demo.XYBarChartDemo1
 * JD-Core Version:    0.7.0.1
 */