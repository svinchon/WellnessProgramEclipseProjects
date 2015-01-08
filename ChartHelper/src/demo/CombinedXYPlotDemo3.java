package demo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import javax.swing.JPanel;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CombinedRangeXYPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.time.Day;
import org.jfree.data.time.MovingAverage;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class CombinedXYPlotDemo3
  extends ApplicationFrame
{
  public CombinedXYPlotDemo3(String paramString)
  {
    super(paramString);
    JFreeChart localJFreeChart = createCombinedChart();
    ChartPanel localChartPanel = new ChartPanel(localJFreeChart, true, true, true, false, true);
    localChartPanel.setPreferredSize(new Dimension(500, 270));
    setContentPane(localChartPanel);
  }
  
  private static JFreeChart createCombinedChart()
  {
    TimeSeriesCollection localTimeSeriesCollection1 = new TimeSeriesCollection();
    TimeSeries localTimeSeries1 = createEURTimeSeries();
    localTimeSeriesCollection1.addSeries(localTimeSeries1);
    TimeSeriesCollection localTimeSeriesCollection2 = new TimeSeriesCollection();
    TimeSeries localTimeSeries2 = MovingAverage.createMovingAverage(localTimeSeries1, "EUR/GBP (30 Day MA)", 30, 30);
    localTimeSeriesCollection2.addSeries(localTimeSeries1);
    localTimeSeriesCollection2.addSeries(localTimeSeries2);
    TimeSeriesCollection localTimeSeriesCollection3 = new TimeSeriesCollection();
    localTimeSeriesCollection3.addSeries(localTimeSeries1);
    JFreeChart localJFreeChart = null;
    NumberAxis localNumberAxis = new NumberAxis("Value");
    localNumberAxis.setAutoRangeIncludesZero(false);
    CombinedRangeXYPlot localCombinedRangeXYPlot = new CombinedRangeXYPlot(localNumberAxis);
    XYPlot localXYPlot1 = new XYPlot(localTimeSeriesCollection1, new DateAxis("Date 1"), null, new StandardXYItemRenderer());
    localCombinedRangeXYPlot.add(localXYPlot1, 1);
    XYPlot localXYPlot2 = new XYPlot(localTimeSeriesCollection2, new DateAxis("Date 2"), null, new StandardXYItemRenderer());
    localCombinedRangeXYPlot.add(localXYPlot2, 1);
    XYPlot localXYPlot3 = new XYPlot(localTimeSeriesCollection3, new DateAxis("Date 3"), null, new XYBarRenderer(0.2D));
    localCombinedRangeXYPlot.add(localXYPlot3, 1);
    localJFreeChart = new JFreeChart("Demo Chart", JFreeChart.DEFAULT_TITLE_FONT, localCombinedRangeXYPlot, true);
    TextTitle localTextTitle = new TextTitle("This is a subtitle", new Font("SansSerif", 1, 12));
    localJFreeChart.addSubtitle(localTextTitle);
    localJFreeChart.setBackgroundPaint(new GradientPaint(0.0F, 0.0F, Color.WHITE, 0.0F, 1000.0F, Color.BLUE));
    ChartUtilities.applyCurrentTheme(localJFreeChart);
    return localJFreeChart;
  }
  
  public static TimeSeries createEURTimeSeries()
  {
    TimeSeries localTimeSeries = new TimeSeries("EUR/GBP");
    localTimeSeries.add(new Day(2, 1, 2001), new Double(1.5788D));
    localTimeSeries.add(new Day(3, 1, 2001), new Double(1.5913D));
    localTimeSeries.add(new Day(4, 1, 2001), new Double(1.5807D));
    localTimeSeries.add(new Day(5, 1, 2001), new Double(1.5711D));
    localTimeSeries.add(new Day(8, 1, 2001), new Double(1.5778D));
    localTimeSeries.add(new Day(9, 1, 2001), new Double(1.5851D));
    localTimeSeries.add(new Day(10, 1, 2001), new Double(1.5846D));
    localTimeSeries.add(new Day(11, 1, 2001), new Double(1.5727D));
    localTimeSeries.add(new Day(12, 1, 2001), new Double(1.5585D));
    localTimeSeries.add(new Day(15, 1, 2001), new Double(1.5694D));
    localTimeSeries.add(new Day(16, 1, 2001), new Double(1.5629D));
    localTimeSeries.add(new Day(17, 1, 2001), new Double(1.5831D));
    localTimeSeries.add(new Day(18, 1, 2001), new Double(1.5624D));
    localTimeSeries.add(new Day(19, 1, 2001), new Double(1.5694D));
    localTimeSeries.add(new Day(22, 1, 2001), new Double(1.5615D));
    localTimeSeries.add(new Day(23, 1, 2001), new Double(1.5656D));
    localTimeSeries.add(new Day(24, 1, 2001), new Double(1.5795D));
    localTimeSeries.add(new Day(25, 1, 2001), new Double(1.5852D));
    localTimeSeries.add(new Day(26, 1, 2001), new Double(1.5797D));
    localTimeSeries.add(new Day(29, 1, 2001), new Double(1.5862D));
    localTimeSeries.add(new Day(30, 1, 2001), new Double(1.5803D));
    localTimeSeries.add(new Day(31, 1, 2001), new Double(1.5714D));
    localTimeSeries.add(new Day(1, 2, 2001), new Double(1.5717D));
    localTimeSeries.add(new Day(2, 2, 2001), new Double(1.5735D));
    localTimeSeries.add(new Day(5, 2, 2001), new Double(1.5691D));
    localTimeSeries.add(new Day(6, 2, 2001), new Double(1.5676D));
    localTimeSeries.add(new Day(7, 2, 2001), new Double(1.5677D));
    localTimeSeries.add(new Day(8, 2, 2001), new Double(1.5737D));
    localTimeSeries.add(new Day(9, 2, 2001), new Double(1.5654D));
    localTimeSeries.add(new Day(12, 2, 2001), new Double(1.5621D));
    localTimeSeries.add(new Day(13, 2, 2001), new Double(1.5761D));
    localTimeSeries.add(new Day(14, 2, 2001), new Double(1.5898D));
    localTimeSeries.add(new Day(15, 2, 2001), new Double(1.6045D));
    localTimeSeries.add(new Day(16, 2, 2001), new Double(1.5852D));
    localTimeSeries.add(new Day(19, 2, 2001), new Double(1.5704D));
    localTimeSeries.add(new Day(20, 2, 2001), new Double(1.5892D));
    localTimeSeries.add(new Day(21, 2, 2001), new Double(1.5844D));
    localTimeSeries.add(new Day(22, 2, 2001), new Double(1.5934D));
    localTimeSeries.add(new Day(23, 2, 2001), new Double(1.5951D));
    localTimeSeries.add(new Day(26, 2, 2001), new Double(1.5848D));
    localTimeSeries.add(new Day(27, 2, 2001), new Double(1.5706D));
    localTimeSeries.add(new Day(28, 2, 2001), new Double(1.568D));
    localTimeSeries.add(new Day(1, 3, 2001), new Double(1.5645D));
    localTimeSeries.add(new Day(2, 3, 2001), new Double(1.5754D));
    localTimeSeries.add(new Day(5, 3, 2001), new Double(1.5808D));
    localTimeSeries.add(new Day(6, 3, 2001), new Double(1.5766D));
    localTimeSeries.add(new Day(7, 3, 2001), new Double(1.5756D));
    localTimeSeries.add(new Day(8, 3, 2001), new Double(1.576D));
    localTimeSeries.add(new Day(9, 3, 2001), new Double(1.5748D));
    localTimeSeries.add(new Day(12, 3, 2001), new Double(1.5779D));
    localTimeSeries.add(new Day(13, 3, 2001), new Double(1.5837D));
    localTimeSeries.add(new Day(14, 3, 2001), new Double(1.5886D));
    localTimeSeries.add(new Day(15, 3, 2001), new Double(1.5931D));
    localTimeSeries.add(new Day(16, 3, 2001), new Double(1.5945D));
    localTimeSeries.add(new Day(19, 3, 2001), new Double(1.588D));
    localTimeSeries.add(new Day(20, 3, 2001), new Double(1.5817D));
    localTimeSeries.add(new Day(21, 3, 2001), new Double(1.5927D));
    localTimeSeries.add(new Day(22, 3, 2001), new Double(1.6065D));
    localTimeSeries.add(new Day(23, 3, 2001), new Double(1.6006D));
    localTimeSeries.add(new Day(26, 3, 2001), new Double(1.6007D));
    localTimeSeries.add(new Day(27, 3, 2001), new Double(1.5989D));
    localTimeSeries.add(new Day(28, 3, 2001), new Double(1.6135D));
    localTimeSeries.add(new Day(29, 3, 2001), new Double(1.6282D));
    localTimeSeries.add(new Day(30, 3, 2001), new Double(1.609D));
    localTimeSeries.add(new Day(2, 4, 2001), new Double(1.6107D));
    localTimeSeries.add(new Day(3, 4, 2001), new Double(1.6093D));
    localTimeSeries.add(new Day(4, 4, 2001), new Double(1.588D));
    localTimeSeries.add(new Day(5, 4, 2001), new Double(1.5931D));
    localTimeSeries.add(new Day(6, 4, 2001), new Double(1.5968D));
    localTimeSeries.add(new Day(9, 4, 2001), new Double(1.6072D));
    localTimeSeries.add(new Day(10, 4, 2001), new Double(1.6167D));
    localTimeSeries.add(new Day(11, 4, 2001), new Double(1.6214D));
    localTimeSeries.add(new Day(12, 4, 2001), new Double(1.612D));
    localTimeSeries.add(new Day(17, 4, 2001), new Double(1.6229D));
    localTimeSeries.add(new Day(18, 4, 2001), new Double(1.6298D));
    localTimeSeries.add(new Day(19, 4, 2001), new Double(1.6159D));
    localTimeSeries.add(new Day(20, 4, 2001), new Double(1.5996D));
    localTimeSeries.add(new Day(23, 4, 2001), new Double(1.6042D));
    localTimeSeries.add(new Day(24, 4, 2001), new Double(1.6061D));
    localTimeSeries.add(new Day(25, 4, 2001), new Double(1.6045D));
    localTimeSeries.add(new Day(26, 4, 2001), new Double(1.597D));
    localTimeSeries.add(new Day(27, 4, 2001), new Double(1.6095D));
    localTimeSeries.add(new Day(30, 4, 2001), new Double(1.6141D));
    localTimeSeries.add(new Day(1, 5, 2001), new Double(1.6076D));
    localTimeSeries.add(new Day(2, 5, 2001), new Double(1.6077D));
    localTimeSeries.add(new Day(3, 5, 2001), new Double(1.6035D));
    localTimeSeries.add(new Day(4, 5, 2001), new Double(1.606D));
    localTimeSeries.add(new Day(8, 5, 2001), new Double(1.6178D));
    localTimeSeries.add(new Day(9, 5, 2001), new Double(1.6083D));
    localTimeSeries.add(new Day(10, 5, 2001), new Double(1.6107D));
    localTimeSeries.add(new Day(11, 5, 2001), new Double(1.6209D));
    localTimeSeries.add(new Day(14, 5, 2001), new Double(1.6228D));
    localTimeSeries.add(new Day(15, 5, 2001), new Double(1.6184D));
    localTimeSeries.add(new Day(16, 5, 2001), new Double(1.6167D));
    localTimeSeries.add(new Day(17, 5, 2001), new Double(1.6223D));
    localTimeSeries.add(new Day(18, 5, 2001), new Double(1.6305D));
    localTimeSeries.add(new Day(21, 5, 2001), new Double(1.642D));
    localTimeSeries.add(new Day(22, 5, 2001), new Double(1.6484D));
    localTimeSeries.add(new Day(23, 5, 2001), new Double(1.6547D));
    localTimeSeries.add(new Day(24, 5, 2001), new Double(1.6444D));
    localTimeSeries.add(new Day(25, 5, 2001), new Double(1.6577D));
    localTimeSeries.add(new Day(29, 5, 2001), new Double(1.6606D));
    localTimeSeries.add(new Day(30, 5, 2001), new Double(1.6604D));
    localTimeSeries.add(new Day(31, 5, 2001), new Double(1.6772D));
    localTimeSeries.add(new Day(1, 6, 2001), new Double(1.6717D));
    localTimeSeries.add(new Day(4, 6, 2001), new Double(1.6685D));
    localTimeSeries.add(new Day(5, 6, 2001), new Double(1.6621D));
    localTimeSeries.add(new Day(6, 6, 2001), new Double(1.646D));
    localTimeSeries.add(new Day(7, 6, 2001), new Double(1.6333D));
    localTimeSeries.add(new Day(8, 6, 2001), new Double(1.6265D));
    localTimeSeries.add(new Day(11, 6, 2001), new Double(1.6311D));
    localTimeSeries.add(new Day(12, 6, 2001), new Double(1.6238D));
    localTimeSeries.add(new Day(13, 6, 2001), new Double(1.63D));
    localTimeSeries.add(new Day(14, 6, 2001), new Double(1.6289D));
    localTimeSeries.add(new Day(15, 6, 2001), new Double(1.6276D));
    localTimeSeries.add(new Day(18, 6, 2001), new Double(1.6299D));
    localTimeSeries.add(new Day(19, 6, 2001), new Double(1.6353D));
    localTimeSeries.add(new Day(20, 6, 2001), new Double(1.6378D));
    localTimeSeries.add(new Day(21, 6, 2001), new Double(1.6567D));
    localTimeSeries.add(new Day(22, 6, 2001), new Double(1.6523D));
    localTimeSeries.add(new Day(25, 6, 2001), new Double(1.6418D));
    localTimeSeries.add(new Day(26, 6, 2001), new Double(1.6429D));
    localTimeSeries.add(new Day(27, 6, 2001), new Double(1.6439D));
    localTimeSeries.add(new Day(28, 6, 2001), new Double(1.6605D));
    localTimeSeries.add(new Day(29, 6, 2001), new Double(1.6599D));
    localTimeSeries.add(new Day(2, 7, 2001), new Double(1.6727D));
    localTimeSeries.add(new Day(3, 7, 2001), new Double(1.662D));
    localTimeSeries.add(new Day(4, 7, 2001), new Double(1.6628D));
    localTimeSeries.add(new Day(5, 7, 2001), new Double(1.673D));
    localTimeSeries.add(new Day(6, 7, 2001), new Double(1.6649D));
    localTimeSeries.add(new Day(9, 7, 2001), new Double(1.6603D));
    localTimeSeries.add(new Day(10, 7, 2001), new Double(1.6489D));
    localTimeSeries.add(new Day(11, 7, 2001), new Double(1.6421D));
    localTimeSeries.add(new Day(12, 7, 2001), new Double(1.6498D));
    localTimeSeries.add(new Day(13, 7, 2001), new Double(1.6447D));
    localTimeSeries.add(new Day(16, 7, 2001), new Double(1.6373D));
    localTimeSeries.add(new Day(17, 7, 2001), new Double(1.6443D));
    localTimeSeries.add(new Day(18, 7, 2001), new Double(1.6246D));
    localTimeSeries.add(new Day(19, 7, 2001), new Double(1.6295D));
    localTimeSeries.add(new Day(20, 7, 2001), new Double(1.6362D));
    localTimeSeries.add(new Day(23, 7, 2001), new Double(1.6348D));
    localTimeSeries.add(new Day(24, 7, 2001), new Double(1.6242D));
    localTimeSeries.add(new Day(25, 7, 2001), new Double(1.6241D));
    localTimeSeries.add(new Day(26, 7, 2001), new Double(1.6281D));
    localTimeSeries.add(new Day(27, 7, 2001), new Double(1.6296D));
    localTimeSeries.add(new Day(30, 7, 2001), new Double(1.6279D));
    localTimeSeries.add(new Day(31, 7, 2001), new Double(1.63D));
    localTimeSeries.add(new Day(1, 8, 2001), new Double(1.629D));
    localTimeSeries.add(new Day(2, 8, 2001), new Double(1.6237D));
    localTimeSeries.add(new Day(3, 8, 2001), new Double(1.6138D));
    localTimeSeries.add(new Day(6, 8, 2001), new Double(1.6121D));
    localTimeSeries.add(new Day(7, 8, 2001), new Double(1.617D));
    localTimeSeries.add(new Day(8, 8, 2001), new Double(1.6135D));
    localTimeSeries.add(new Day(9, 8, 2001), new Double(1.5996D));
    localTimeSeries.add(new Day(10, 8, 2001), new Double(1.5931D));
    localTimeSeries.add(new Day(13, 8, 2001), new Double(1.5828D));
    localTimeSeries.add(new Day(14, 8, 2001), new Double(1.5824D));
    localTimeSeries.add(new Day(15, 8, 2001), new Double(1.5783D));
    localTimeSeries.add(new Day(16, 8, 2001), new Double(1.581D));
    localTimeSeries.add(new Day(17, 8, 2001), new Double(1.5761D));
    localTimeSeries.add(new Day(20, 8, 2001), new Double(1.5831D));
    localTimeSeries.add(new Day(21, 8, 2001), new Double(1.587D));
    localTimeSeries.add(new Day(22, 8, 2001), new Double(1.5808D));
    localTimeSeries.add(new Day(23, 8, 2001), new Double(1.5845D));
    localTimeSeries.add(new Day(24, 8, 2001), new Double(1.5844D));
    localTimeSeries.add(new Day(28, 8, 2001), new Double(1.5924D));
    localTimeSeries.add(new Day(29, 8, 2001), new Double(1.595D));
    localTimeSeries.add(new Day(30, 8, 2001), new Double(1.5941D));
    localTimeSeries.add(new Day(31, 8, 2001), new Double(1.5968D));
    localTimeSeries.add(new Day(3, 9, 2001), new Double(1.602D));
    localTimeSeries.add(new Day(4, 9, 2001), new Double(1.6236D));
    localTimeSeries.add(new Day(5, 9, 2001), new Double(1.6352D));
    localTimeSeries.add(new Day(6, 9, 2001), new Double(1.6302D));
    localTimeSeries.add(new Day(7, 9, 2001), new Double(1.618D));
    localTimeSeries.add(new Day(10, 9, 2001), new Double(1.6218D));
    localTimeSeries.add(new Day(11, 9, 2001), new Double(1.6182D));
    localTimeSeries.add(new Day(12, 9, 2001), new Double(1.6157D));
    localTimeSeries.add(new Day(13, 9, 2001), new Double(1.6171D));
    localTimeSeries.add(new Day(14, 9, 2001), new Double(1.596D));
    localTimeSeries.add(new Day(17, 9, 2001), new Double(1.5952D));
    localTimeSeries.add(new Day(18, 9, 2001), new Double(1.5863D));
    localTimeSeries.add(new Day(19, 9, 2001), new Double(1.579D));
    localTimeSeries.add(new Day(20, 9, 2001), new Double(1.5811D));
    localTimeSeries.add(new Day(21, 9, 2001), new Double(1.5917D));
    localTimeSeries.add(new Day(24, 9, 2001), new Double(1.6005D));
    localTimeSeries.add(new Day(25, 9, 2001), new Double(1.5915D));
    localTimeSeries.add(new Day(26, 9, 2001), new Double(1.6012D));
    localTimeSeries.add(new Day(27, 9, 2001), new Double(1.6032D));
    localTimeSeries.add(new Day(28, 9, 2001), new Double(1.6133D));
    localTimeSeries.add(new Day(1, 10, 2001), new Double(1.6147D));
    localTimeSeries.add(new Day(2, 10, 2001), new Double(1.6002D));
    localTimeSeries.add(new Day(3, 10, 2001), new Double(1.6041D));
    localTimeSeries.add(new Day(4, 10, 2001), new Double(1.6172D));
    localTimeSeries.add(new Day(5, 10, 2001), new Double(1.6121D));
    localTimeSeries.add(new Day(8, 10, 2001), new Double(1.6044D));
    localTimeSeries.add(new Day(9, 10, 2001), new Double(1.5974D));
    localTimeSeries.add(new Day(10, 10, 2001), new Double(1.5915D));
    localTimeSeries.add(new Day(11, 10, 2001), new Double(1.6022D));
    localTimeSeries.add(new Day(12, 10, 2001), new Double(1.6014D));
    localTimeSeries.add(new Day(15, 10, 2001), new Double(1.5942D));
    localTimeSeries.add(new Day(16, 10, 2001), new Double(1.5925D));
    localTimeSeries.add(new Day(17, 10, 2001), new Double(1.6007D));
    localTimeSeries.add(new Day(18, 10, 2001), new Double(1.6D));
    localTimeSeries.add(new Day(19, 10, 2001), new Double(1.603D));
    localTimeSeries.add(new Day(22, 10, 2001), new Double(1.6014D));
    localTimeSeries.add(new Day(23, 10, 2001), new Double(1.5995D));
    localTimeSeries.add(new Day(24, 10, 2001), new Double(1.5951D));
    localTimeSeries.add(new Day(25, 10, 2001), new Double(1.5953D));
    localTimeSeries.add(new Day(26, 10, 2001), new Double(1.6057D));
    localTimeSeries.add(new Day(29, 10, 2001), new Double(1.6051D));
    localTimeSeries.add(new Day(30, 10, 2001), new Double(1.6027D));
    localTimeSeries.add(new Day(31, 10, 2001), new Double(1.6144D));
    localTimeSeries.add(new Day(1, 11, 2001), new Double(1.6139D));
    localTimeSeries.add(new Day(2, 11, 2001), new Double(1.6189D));
    localTimeSeries.add(new Day(5, 11, 2001), new Double(1.6248D));
    localTimeSeries.add(new Day(6, 11, 2001), new Double(1.6267D));
    localTimeSeries.add(new Day(7, 11, 2001), new Double(1.6281D));
    localTimeSeries.add(new Day(8, 11, 2001), new Double(1.631D));
    localTimeSeries.add(new Day(9, 11, 2001), new Double(1.6313D));
    localTimeSeries.add(new Day(12, 11, 2001), new Double(1.6272D));
    localTimeSeries.add(new Day(13, 11, 2001), new Double(1.6361D));
    localTimeSeries.add(new Day(14, 11, 2001), new Double(1.6323D));
    localTimeSeries.add(new Day(15, 11, 2001), new Double(1.6252D));
    localTimeSeries.add(new Day(16, 11, 2001), new Double(1.6141D));
    localTimeSeries.add(new Day(19, 11, 2001), new Double(1.6086D));
    localTimeSeries.add(new Day(20, 11, 2001), new Double(1.6055D));
    localTimeSeries.add(new Day(21, 11, 2001), new Double(1.6132D));
    localTimeSeries.add(new Day(22, 11, 2001), new Double(1.6074D));
    localTimeSeries.add(new Day(23, 11, 2001), new Double(1.6065D));
    localTimeSeries.add(new Day(26, 11, 2001), new Double(1.6061D));
    localTimeSeries.add(new Day(27, 11, 2001), new Double(1.6039D));
    localTimeSeries.add(new Day(28, 11, 2001), new Double(1.6069D));
    localTimeSeries.add(new Day(29, 11, 2001), new Double(1.6044D));
    localTimeSeries.add(new Day(30, 11, 2001), new Double(1.5928D));
    return localTimeSeries;
  }
  
  public static JPanel createDemoPanel()
  {
    JFreeChart localJFreeChart = createCombinedChart();
    return new ChartPanel(localJFreeChart);
  }
  
  public static void main(String[] paramArrayOfString)
  {
    CombinedXYPlotDemo3 localCombinedXYPlotDemo3 = new CombinedXYPlotDemo3("JFreeChart: CombinedXYPlotDemo3.java");
    localCombinedXYPlotDemo3.pack();
    RefineryUtilities.centerFrameOnScreen(localCombinedXYPlotDemo3);
    localCombinedXYPlotDemo3.setVisible(true);
  }
}


/* Location:           C:\Users\vinchs\Desktop\temp\
 * Qualified Name:     demo.CombinedXYPlotDemo3
 * JD-Core Version:    0.7.0.1
 */