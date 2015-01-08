package demo;

import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import org.jfree.chart.JFreeChart;

public class DemoPanel
  extends JPanel
{
	  @SuppressWarnings({ "rawtypes", "unchecked" })
List charts = new ArrayList();
  
  public DemoPanel(LayoutManager paramLayoutManager)
  {
    super(paramLayoutManager);
  }
  
  public void addChart(JFreeChart paramJFreeChart)
  {
    this.charts.add(paramJFreeChart);
  }
  
  public JFreeChart[] getCharts()
  {
    int i = this.charts.size();
    JFreeChart[] arrayOfJFreeChart = new JFreeChart[i];
    for (int j = 0; j < i; j++) {
      arrayOfJFreeChart[j] = ((JFreeChart)this.charts.get(j));
    }
    return arrayOfJFreeChart;
  }
}


/* Location:           C:\Users\vinchs\Desktop\temp\
 * Qualified Name:     demo.DemoPanel
 * JD-Core Version:    0.7.0.1
 */