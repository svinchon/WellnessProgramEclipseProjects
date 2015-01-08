package demo;

import java.awt.Dimension;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.DefaultHighLowDataset;
import org.jfree.data.xy.OHLCDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class CandlestickChartDemo1
  extends ApplicationFrame
{
  private static final Calendar calendar = new Calendar() {
	
	@Override
	public void roll(int field, boolean up) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public int getMinimum(int field) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int getMaximum(int field) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int getLeastMaximum(int field) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int getGreatestMinimum(int field) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	protected void computeTime() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected void computeFields() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void add(int field, int amount) {
		// TODO Auto-generated method stub
		
	}
};
  
  public CandlestickChartDemo1(String paramString)
  {
    super(paramString);
    JPanel localJPanel = createDemoPanel();
    localJPanel.setPreferredSize(new Dimension(500, 270));
    setContentPane(localJPanel);
  }
  
  private static JFreeChart createChart(OHLCDataset paramOHLCDataset)
  {
    JFreeChart localJFreeChart = ChartFactory.createCandlestickChart("Candlestick Demo 1", "Time", "Value", paramOHLCDataset, true);
    XYPlot localXYPlot = (XYPlot)localJFreeChart.getPlot();
    localXYPlot.setDomainPannable(true);
    NumberAxis localNumberAxis = (NumberAxis)localXYPlot.getRangeAxis();
    localNumberAxis.setAutoRangeIncludesZero(false);
    localNumberAxis.setUpperMargin(0.0D);
    localNumberAxis.setLowerMargin(0.0D);
    return localJFreeChart;
  }
  
  private static Date createDate(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    calendar.clear();
    calendar.set(paramInt1, paramInt2 - 1, paramInt3, paramInt4, paramInt5);
    return calendar.getTime();
  }
  
  public static OHLCDataset createDataset()
  {
    Date[] arrayOfDate = new Date[47];
    double[] arrayOfDouble1 = new double[47];
    double[] arrayOfDouble2 = new double[47];
    double[] arrayOfDouble3 = new double[47];
    double[] arrayOfDouble4 = new double[47];
    double[] arrayOfDouble5 = new double[47];
    int i = 1;
    int j = 2;
    arrayOfDate[0] = createDate(2001, i, 4, 12, 0);
    arrayOfDouble1[0] = 47.0D;
    arrayOfDouble2[0] = 33.0D;
    arrayOfDouble3[0] = 35.0D;
    arrayOfDouble4[0] = 33.0D;
    arrayOfDouble5[0] = 100.0D;
    arrayOfDate[1] = createDate(2001, i, 5, 12, 0);
    arrayOfDouble1[1] = 47.0D;
    arrayOfDouble2[1] = 32.0D;
    arrayOfDouble3[1] = 41.0D;
    arrayOfDouble4[1] = 37.0D;
    arrayOfDouble5[1] = 150.0D;
    arrayOfDate[2] = createDate(2001, i, 6, 12, 0);
    arrayOfDouble1[2] = 49.0D;
    arrayOfDouble2[2] = 43.0D;
    arrayOfDouble3[2] = 46.0D;
    arrayOfDouble4[2] = 48.0D;
    arrayOfDouble5[2] = 70.0D;
    arrayOfDate[3] = createDate(2001, i, 7, 12, 0);
    arrayOfDouble1[3] = 51.0D;
    arrayOfDouble2[3] = 39.0D;
    arrayOfDouble3[3] = 40.0D;
    arrayOfDouble4[3] = 47.0D;
    arrayOfDouble5[3] = 200.0D;
    arrayOfDate[4] = createDate(2001, i, 8, 12, 0);
    arrayOfDouble1[4] = 60.0D;
    arrayOfDouble2[4] = 40.0D;
    arrayOfDouble3[4] = 46.0D;
    arrayOfDouble4[4] = 53.0D;
    arrayOfDouble5[4] = 120.0D;
    arrayOfDate[5] = createDate(2001, i, 9, 12, 0);
    arrayOfDouble1[5] = 62.0D;
    arrayOfDouble2[5] = 55.0D;
    arrayOfDouble3[5] = 57.0D;
    arrayOfDouble4[5] = 61.0D;
    arrayOfDouble5[5] = 110.0D;
    arrayOfDate[6] = createDate(2001, i, 10, 12, 0);
    arrayOfDouble1[6] = 65.0D;
    arrayOfDouble2[6] = 56.0D;
    arrayOfDouble3[6] = 62.0D;
    arrayOfDouble4[6] = 59.0D;
    arrayOfDouble5[6] = 70.0D;
    arrayOfDate[7] = createDate(2001, i, 11, 12, 0);
    arrayOfDouble1[7] = 55.0D;
    arrayOfDouble2[7] = 43.0D;
    arrayOfDouble3[7] = 45.0D;
    arrayOfDouble4[7] = 47.0D;
    arrayOfDouble5[7] = 20.0D;
    arrayOfDate[8] = createDate(2001, i, 12, 12, 0);
    arrayOfDouble1[8] = 54.0D;
    arrayOfDouble2[8] = 33.0D;
    arrayOfDouble3[8] = 40.0D;
    arrayOfDouble4[8] = 51.0D;
    arrayOfDouble5[8] = 30.0D;
    arrayOfDate[9] = createDate(2001, i, 13, 12, 0);
    arrayOfDouble1[9] = 47.0D;
    arrayOfDouble2[9] = 33.0D;
    arrayOfDouble3[9] = 35.0D;
    arrayOfDouble4[9] = 33.0D;
    arrayOfDouble5[9] = 100.0D;
    arrayOfDate[10] = createDate(2001, i, 14, 12, 0);
    arrayOfDouble1[10] = 54.0D;
    arrayOfDouble2[10] = 38.0D;
    arrayOfDouble3[10] = 43.0D;
    arrayOfDouble4[10] = 52.0D;
    arrayOfDouble5[10] = 50.0D;
    arrayOfDate[11] = createDate(2001, i, 15, 12, 0);
    arrayOfDouble1[11] = 48.0D;
    arrayOfDouble2[11] = 41.0D;
    arrayOfDouble3[11] = 44.0D;
    arrayOfDouble4[11] = 41.0D;
    arrayOfDouble5[11] = 80.0D;
    arrayOfDate[12] = createDate(2001, i, 17, 12, 0);
    arrayOfDouble1[12] = 60.0D;
    arrayOfDouble2[12] = 30.0D;
    arrayOfDouble3[12] = 34.0D;
    arrayOfDouble4[12] = 44.0D;
    arrayOfDouble5[12] = 90.0D;
    arrayOfDate[13] = createDate(2001, i, 18, 12, 0);
    arrayOfDouble1[13] = 58.0D;
    arrayOfDouble2[13] = 44.0D;
    arrayOfDouble3[13] = 54.0D;
    arrayOfDouble4[13] = 56.0D;
    arrayOfDouble5[13] = 20.0D;
    arrayOfDate[14] = createDate(2001, i, 19, 12, 0);
    arrayOfDouble1[14] = 54.0D;
    arrayOfDouble2[14] = 32.0D;
    arrayOfDouble3[14] = 42.0D;
    arrayOfDouble4[14] = 53.0D;
    arrayOfDouble5[14] = 70.0D;
    arrayOfDate[15] = createDate(2001, i, 20, 12, 0);
    arrayOfDouble1[15] = 53.0D;
    arrayOfDouble2[15] = 39.0D;
    arrayOfDouble3[15] = 50.0D;
    arrayOfDouble4[15] = 49.0D;
    arrayOfDouble5[15] = 60.0D;
    arrayOfDate[16] = createDate(2001, i, 21, 12, 0);
    arrayOfDouble1[16] = 47.0D;
    arrayOfDouble2[16] = 33.0D;
    arrayOfDouble3[16] = 41.0D;
    arrayOfDouble4[16] = 40.0D;
    arrayOfDouble5[16] = 30.0D;
    arrayOfDate[17] = createDate(2001, i, 22, 12, 0);
    arrayOfDouble1[17] = 55.0D;
    arrayOfDouble2[17] = 37.0D;
    arrayOfDouble3[17] = 43.0D;
    arrayOfDouble4[17] = 45.0D;
    arrayOfDouble5[17] = 90.0D;
    arrayOfDate[18] = createDate(2001, i, 23, 12, 0);
    arrayOfDouble1[18] = 54.0D;
    arrayOfDouble2[18] = 42.0D;
    arrayOfDouble3[18] = 50.0D;
    arrayOfDouble4[18] = 42.0D;
    arrayOfDouble5[18] = 150.0D;
    arrayOfDate[19] = createDate(2001, i, 24, 12, 0);
    arrayOfDouble1[19] = 48.0D;
    arrayOfDouble2[19] = 37.0D;
    arrayOfDouble3[19] = 37.0D;
    arrayOfDouble4[19] = 47.0D;
    arrayOfDouble5[19] = 120.0D;
    arrayOfDate[20] = createDate(2001, i, 25, 12, 0);
    arrayOfDouble1[20] = 58.0D;
    arrayOfDouble2[20] = 33.0D;
    arrayOfDouble3[20] = 39.0D;
    arrayOfDouble4[20] = 41.0D;
    arrayOfDouble5[20] = 80.0D;
    arrayOfDate[21] = createDate(2001, i, 26, 12, 0);
    arrayOfDouble1[21] = 47.0D;
    arrayOfDouble2[21] = 31.0D;
    arrayOfDouble3[21] = 36.0D;
    arrayOfDouble4[21] = 41.0D;
    arrayOfDouble5[21] = 40.0D;
    arrayOfDate[22] = createDate(2001, i, 27, 12, 0);
    arrayOfDouble1[22] = 58.0D;
    arrayOfDouble2[22] = 44.0D;
    arrayOfDouble3[22] = 49.0D;
    arrayOfDouble4[22] = 44.0D;
    arrayOfDouble5[22] = 20.0D;
    arrayOfDate[23] = createDate(2001, i, 28, 12, 0);
    arrayOfDouble1[23] = 46.0D;
    arrayOfDouble2[23] = 41.0D;
    arrayOfDouble3[23] = 43.0D;
    arrayOfDouble4[23] = 44.0D;
    arrayOfDouble5[23] = 60.0D;
    arrayOfDate[24] = createDate(2001, i, 29, 12, 0);
    arrayOfDouble1[24] = 56.0D;
    arrayOfDouble2[24] = 39.0D;
    arrayOfDouble3[24] = 39.0D;
    arrayOfDouble4[24] = 51.0D;
    arrayOfDouble5[24] = 40.0D;
    arrayOfDate[25] = createDate(2001, i, 30, 12, 0);
    arrayOfDouble1[25] = 56.0D;
    arrayOfDouble2[25] = 39.0D;
    arrayOfDouble3[25] = 47.0D;
    arrayOfDouble4[25] = 49.0D;
    arrayOfDouble5[25] = 70.0D;
    arrayOfDate[26] = createDate(2001, i, 31, 12, 0);
    arrayOfDouble1[26] = 53.0D;
    arrayOfDouble2[26] = 39.0D;
    arrayOfDouble3[26] = 52.0D;
    arrayOfDouble4[26] = 47.0D;
    arrayOfDouble5[26] = 60.0D;
    arrayOfDate[27] = createDate(2001, j, 1, 12, 0);
    arrayOfDouble1[27] = 51.0D;
    arrayOfDouble2[27] = 30.0D;
    arrayOfDouble3[27] = 45.0D;
    arrayOfDouble4[27] = 47.0D;
    arrayOfDouble5[27] = 90.0D;
    arrayOfDate[28] = createDate(2001, j, 2, 12, 0);
    arrayOfDouble1[28] = 47.0D;
    arrayOfDouble2[28] = 30.0D;
    arrayOfDouble3[28] = 34.0D;
    arrayOfDouble4[28] = 46.0D;
    arrayOfDouble5[28] = 100.0D;
    arrayOfDate[29] = createDate(2001, j, 3, 12, 0);
    arrayOfDouble1[29] = 57.0D;
    arrayOfDouble2[29] = 37.0D;
    arrayOfDouble3[29] = 44.0D;
    arrayOfDouble4[29] = 56.0D;
    arrayOfDouble5[29] = 20.0D;
    arrayOfDate[30] = createDate(2001, j, 4, 12, 0);
    arrayOfDouble1[30] = 49.0D;
    arrayOfDouble2[30] = 40.0D;
    arrayOfDouble3[30] = 47.0D;
    arrayOfDouble4[30] = 44.0D;
    arrayOfDouble5[30] = 50.0D;
    arrayOfDate[31] = createDate(2001, j, 5, 12, 0);
    arrayOfDouble1[31] = 46.0D;
    arrayOfDouble2[31] = 38.0D;
    arrayOfDouble3[31] = 43.0D;
    arrayOfDouble4[31] = 40.0D;
    arrayOfDouble5[31] = 70.0D;
    arrayOfDate[32] = createDate(2001, j, 6, 12, 0);
    arrayOfDouble1[32] = 55.0D;
    arrayOfDouble2[32] = 38.0D;
    arrayOfDouble3[32] = 39.0D;
    arrayOfDouble4[32] = 53.0D;
    arrayOfDouble5[32] = 120.0D;
    arrayOfDate[33] = createDate(2001, j, 7, 12, 0);
    arrayOfDouble1[33] = 50.0D;
    arrayOfDouble2[33] = 33.0D;
    arrayOfDouble3[33] = 37.0D;
    arrayOfDouble4[33] = 37.0D;
    arrayOfDouble5[33] = 140.0D;
    arrayOfDate[34] = createDate(2001, j, 8, 12, 0);
    arrayOfDouble1[34] = 59.0D;
    arrayOfDouble2[34] = 34.0D;
    arrayOfDouble3[34] = 57.0D;
    arrayOfDouble4[34] = 43.0D;
    arrayOfDouble5[34] = 70.0D;
    arrayOfDate[35] = createDate(2001, j, 9, 12, 0);
    arrayOfDouble1[35] = 48.0D;
    arrayOfDouble2[35] = 39.0D;
    arrayOfDouble3[35] = 46.0D;
    arrayOfDouble4[35] = 47.0D;
    arrayOfDouble5[35] = 70.0D;
    arrayOfDate[36] = createDate(2001, j, 10, 12, 0);
    arrayOfDouble1[36] = 55.0D;
    arrayOfDouble2[36] = 30.0D;
    arrayOfDouble3[36] = 37.0D;
    arrayOfDouble4[36] = 30.0D;
    arrayOfDouble5[36] = 30.0D;
    arrayOfDate[37] = createDate(2001, j, 11, 12, 0);
    arrayOfDouble1[37] = 60.0D;
    arrayOfDouble2[37] = 32.0D;
    arrayOfDouble3[37] = 56.0D;
    arrayOfDouble4[37] = 36.0D;
    arrayOfDouble5[37] = 70.0D;
    arrayOfDate[38] = createDate(2001, j, 12, 12, 0);
    arrayOfDouble1[38] = 56.0D;
    arrayOfDouble2[38] = 42.0D;
    arrayOfDouble3[38] = 53.0D;
    arrayOfDouble4[38] = 54.0D;
    arrayOfDouble5[38] = 40.0D;
    arrayOfDate[39] = createDate(2001, j, 13, 12, 0);
    arrayOfDouble1[39] = 49.0D;
    arrayOfDouble2[39] = 42.0D;
    arrayOfDouble3[39] = 45.0D;
    arrayOfDouble4[39] = 42.0D;
    arrayOfDouble5[39] = 90.0D;
    arrayOfDate[40] = createDate(2001, j, 14, 12, 0);
    arrayOfDouble1[40] = 55.0D;
    arrayOfDouble2[40] = 42.0D;
    arrayOfDouble3[40] = 47.0D;
    arrayOfDouble4[40] = 54.0D;
    arrayOfDouble5[40] = 70.0D;
    arrayOfDate[41] = createDate(2001, j, 15, 12, 0);
    arrayOfDouble1[41] = 49.0D;
    arrayOfDouble2[41] = 35.0D;
    arrayOfDouble3[41] = 38.0D;
    arrayOfDouble4[41] = 35.0D;
    arrayOfDouble5[41] = 20.0D;
    arrayOfDate[42] = createDate(2001, j, 16, 12, 0);
    arrayOfDouble1[42] = 47.0D;
    arrayOfDouble2[42] = 38.0D;
    arrayOfDouble3[42] = 43.0D;
    arrayOfDouble4[42] = 42.0D;
    arrayOfDouble5[42] = 10.0D;
    arrayOfDate[43] = createDate(2001, j, 17, 12, 0);
    arrayOfDouble1[43] = 53.0D;
    arrayOfDouble2[43] = 42.0D;
    arrayOfDouble3[43] = 47.0D;
    arrayOfDouble4[43] = 48.0D;
    arrayOfDouble5[43] = 20.0D;
    arrayOfDate[44] = createDate(2001, j, 18, 12, 0);
    arrayOfDouble1[44] = 47.0D;
    arrayOfDouble2[44] = 44.0D;
    arrayOfDouble3[44] = 46.0D;
    arrayOfDouble4[44] = 44.0D;
    arrayOfDouble5[44] = 30.0D;
    arrayOfDate[45] = createDate(2001, j, 19, 12, 0);
    arrayOfDouble1[45] = 46.0D;
    arrayOfDouble2[45] = 40.0D;
    arrayOfDouble3[45] = 43.0D;
    arrayOfDouble4[45] = 44.0D;
    arrayOfDouble5[45] = 50.0D;
    arrayOfDate[46] = createDate(2001, j, 20, 12, 0);
    arrayOfDouble1[46] = 48.0D;
    arrayOfDouble2[46] = 41.0D;
    arrayOfDouble3[46] = 46.0D;
    arrayOfDouble4[46] = 41.0D;
    arrayOfDouble5[46] = 100.0D;
    return new DefaultHighLowDataset("Series 1", arrayOfDate, arrayOfDouble1, arrayOfDouble2, arrayOfDouble3, arrayOfDouble4, arrayOfDouble5);
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
    CandlestickChartDemo1 localCandlestickChartDemo1 = new CandlestickChartDemo1("JFreeChart : CandlestickChartDemo1.java");
    localCandlestickChartDemo1.pack();
    RefineryUtilities.centerFrameOnScreen(localCandlestickChartDemo1);
    localCandlestickChartDemo1.setVisible(true);
  }
}


/* Location:           C:\Users\vinchs\Desktop\temp\
 * Qualified Name:     demo.CandlestickChartDemo1
 * JD-Core Version:    0.7.0.1
 */