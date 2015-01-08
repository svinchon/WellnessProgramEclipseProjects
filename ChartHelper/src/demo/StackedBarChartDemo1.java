package demo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.font.TextAttribute;
import java.text.AttributedString;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.StackedBarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class StackedBarChartDemo1
  extends ApplicationFrame
{
  public StackedBarChartDemo1(String paramString)
  {
    super(paramString);
    JPanel localJPanel = createDemoPanel();
    localJPanel.setPreferredSize(new Dimension(500, 270));
    setContentPane(localJPanel);
  }
  
  private static CategoryDataset createDataset()
  {
    DefaultCategoryDataset localDefaultCategoryDataset = new DefaultCategoryDataset();
    localDefaultCategoryDataset.addValue(197.0D, "Agricultural", "Brazil");
    localDefaultCategoryDataset.addValue(64.0D, "Domestic", "Brazil");
    localDefaultCategoryDataset.addValue(57.0D, "Industrial", "Brazil");
    localDefaultCategoryDataset.addValue(339.0D, "Agricultural", "Indonesia");
    localDefaultCategoryDataset.addValue(30.0D, "Domestic", "Indonesia");
    localDefaultCategoryDataset.addValue(4.0D, "Industrial", "Indonesia");
    localDefaultCategoryDataset.addValue(279.0D, "Agricultural", "China");
    localDefaultCategoryDataset.addValue(27.0D, "Domestic", "China");
    localDefaultCategoryDataset.addValue(107.0D, "Industrial", "China");
    localDefaultCategoryDataset.addValue(92.0D, "Agricultural", "Germany");
    localDefaultCategoryDataset.addValue(55.0D, "Domestic", "Germany");
    localDefaultCategoryDataset.addValue(313.0D, "Industrial", "Germany");
    localDefaultCategoryDataset.addValue(96.0D, "Agricultural", "Russia");
    localDefaultCategoryDataset.addValue(102.0D, "Domestic", "Russia");
    localDefaultCategoryDataset.addValue(337.0D, "Industrial", "Russia");
    localDefaultCategoryDataset.addValue(403.0D, "Agricultural", "Turkey");
    localDefaultCategoryDataset.addValue(82.0D, "Domestic", "Turkey");
    localDefaultCategoryDataset.addValue(60.0D, "Industrial", "Turkey");
    localDefaultCategoryDataset.addValue(536.0D, "Agricultural", "Bangladesh");
    localDefaultCategoryDataset.addValue(17.0D, "Domestic", "Bangladesh");
    localDefaultCategoryDataset.addValue(6.0D, "Industrial", "Bangladesh");
    localDefaultCategoryDataset.addValue(508.0D, "Agricultural", "India");
    localDefaultCategoryDataset.addValue(47.0D, "Domestic", "India");
    localDefaultCategoryDataset.addValue(30.0D, "Industrial", "India");
    localDefaultCategoryDataset.addValue(428.0D, "Agricultural", "Japan");
    localDefaultCategoryDataset.addValue(138.0D, "Domestic", "Japan");
    localDefaultCategoryDataset.addValue(124.0D, "Industrial", "Japan");
    localDefaultCategoryDataset.addValue(325.0D, "Agricultural", "Italy");
    localDefaultCategoryDataset.addValue(130.0D, "Domestic", "Italy");
    localDefaultCategoryDataset.addValue(268.0D, "Industrial", "Italy");
    localDefaultCategoryDataset.addValue(569.0D, "Agricultural", "Mexico");
    localDefaultCategoryDataset.addValue(126.0D, "Domestic", "Mexico");
    localDefaultCategoryDataset.addValue(37.0D, "Industrial", "Mexico");
    localDefaultCategoryDataset.addValue(576.0D, "Agricultural", "Vietnam");
    localDefaultCategoryDataset.addValue(68.0D, "Domestic", "Vietnam");
    localDefaultCategoryDataset.addValue(203.0D, "Industrial", "Vietnam");
    localDefaultCategoryDataset.addValue(794.0D, "Agricultural", "Egypt");
    localDefaultCategoryDataset.addValue(74.0D, "Domestic", "Egypt");
    localDefaultCategoryDataset.addValue(55.0D, "Industrial", "Egypt");
    localDefaultCategoryDataset.addValue(954.0D, "Agricultural", "Iran");
    localDefaultCategoryDataset.addValue(21.0D, "Domestic", "Iran");
    localDefaultCategoryDataset.addValue(73.0D, "Industrial", "Iran");
    localDefaultCategoryDataset.addValue(1029.0D, "Agricultural", "Pakistan");
    localDefaultCategoryDataset.addValue(21.0D, "Domestic", "Pakistan");
    localDefaultCategoryDataset.addValue(21.0D, "Industrial", "Pakistan");
    localDefaultCategoryDataset.addValue(1236.0D, "Agricultural", "Thailand");
    localDefaultCategoryDataset.addValue(26.0D, "Domestic", "Thailand");
    localDefaultCategoryDataset.addValue(26.0D, "Industrial", "Thailand");
    localDefaultCategoryDataset.addValue(165.0D, "Agricultural", "Canada");
    localDefaultCategoryDataset.addValue(274.0D, "Domestic", "Canada");
    localDefaultCategoryDataset.addValue(947.0D, "Industrial", "Canada");
    localDefaultCategoryDataset.addValue(1363.0D, "Agricultural", "Iraq");
    localDefaultCategoryDataset.addValue(44.0D, "Domestic", "Iraq");
    localDefaultCategoryDataset.addValue(74.0D, "Industrial", "Iraq");
    localDefaultCategoryDataset.addValue(656.0D, "Agricultural", "US");
    localDefaultCategoryDataset.addValue(208.0D, "Domestic", "US");
    localDefaultCategoryDataset.addValue(736.0D, "Industrial", "US");
    localDefaultCategoryDataset.addValue(2040.0D, "Agricultural", "Uzbekistan");
    localDefaultCategoryDataset.addValue(110.0D, "Domestic", "Uzbekistan");
    localDefaultCategoryDataset.addValue(44.0D, "Industrial", "Uzbekistan");
    return localDefaultCategoryDataset;
  }
  
  private static JFreeChart createChart(CategoryDataset paramCategoryDataset)
  {
    JFreeChart localJFreeChart = ChartFactory.createStackedBarChart("Freshwater Usage By Country", "Country", "Value", paramCategoryDataset);
    localJFreeChart.addSubtitle(new TextTitle("Source: http://en.wikipedia.org/wiki/Peak_water#Water_supply"));
    CategoryPlot localCategoryPlot = (CategoryPlot)localJFreeChart.getPlot();
    CategoryAxis localCategoryAxis = localCategoryPlot.getDomainAxis();
    localCategoryAxis.setLowerMargin(0.01D);
    localCategoryAxis.setUpperMargin(0.01D);
    localCategoryAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_90);
    AttributedString localAttributedString = new AttributedString("m3/person/year");
    localAttributedString.addAttribute(TextAttribute.WEIGHT, TextAttribute.WEIGHT_ULTRABOLD);
    localAttributedString.addAttribute(TextAttribute.SIZE, Integer.valueOf(14));
    localAttributedString.addAttribute(TextAttribute.SUPERSCRIPT, TextAttribute.SUPERSCRIPT_SUPER, 1, 2);
    localCategoryPlot.getRangeAxis().setAttributedLabel(localAttributedString);
    StackedBarRenderer localStackedBarRenderer = (StackedBarRenderer)localCategoryPlot.getRenderer();
    localStackedBarRenderer.setDrawBarOutline(false);
    localStackedBarRenderer.setBarPainter(new StandardBarPainter());
    localStackedBarRenderer.setBaseItemLabelsVisible(true);
    localStackedBarRenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
    localStackedBarRenderer.setBaseItemLabelPaint(Color.WHITE);
    localStackedBarRenderer.setSeriesPaint(0, new Color(0, 55, 122));
    localStackedBarRenderer.setSeriesPaint(1, new Color(24, 123, 58));
    localStackedBarRenderer.setSeriesPaint(2, Color.RED);
    return localJFreeChart;
  }
  
  public static JPanel createDemoPanel()
  {
    JFreeChart localJFreeChart = createChart(createDataset());
    return new ChartPanel(localJFreeChart);
  }
  
  public static void main(String[] paramArrayOfString)
  {
    StackedBarChartDemo1 localStackedBarChartDemo1 = new StackedBarChartDemo1("JFreeChart: StackedBarChartDemo1");
    localStackedBarChartDemo1.pack();
    RefineryUtilities.centerFrameOnScreen(localStackedBarChartDemo1);
    localStackedBarChartDemo1.setVisible(true);
  }
}


/* Location:           C:\Users\vinchs\Desktop\temp\
 * Qualified Name:     demo.StackedBarChartDemo1
 * JD-Core Version:    0.7.0.1
 */