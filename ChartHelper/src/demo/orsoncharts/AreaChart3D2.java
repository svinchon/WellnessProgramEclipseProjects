package demo.orsoncharts;

import com.orsoncharts.Chart3D;
import com.orsoncharts.Chart3DFactory;
import com.orsoncharts.Colors;
import com.orsoncharts.axis.CategoryAxis3D;
import com.orsoncharts.data.DefaultKeyedValues;
import com.orsoncharts.data.category.CategoryDataset3D;
import com.orsoncharts.data.category.StandardCategoryDataset3D;
import com.orsoncharts.graphics3d.ViewPoint3D;
import com.orsoncharts.plot.CategoryPlot3D;
import com.orsoncharts.renderer.category.AreaRenderer3D;
import java.awt.Color;

public class AreaChart3D2
{
  public static Chart3D createChart(CategoryDataset3D paramCategoryDataset3D)
  {
    Chart3D localChart3D = Chart3DFactory.createAreaChart("AreaChart3DDemo2", "Chart created with Orson Charts", paramCategoryDataset3D, "Row", "Category", "Value");
    localChart3D.setChartBoxColor(new Color(255, 255, 255, 128));
    localChart3D.setViewPoint(ViewPoint3D.createAboveLeftViewPoint(40.0D));
    CategoryPlot3D localCategoryPlot3D = (CategoryPlot3D)localChart3D.getPlot();
    localCategoryPlot3D.getRowAxis().setVisible(false);
    AreaRenderer3D localAreaRenderer3D = (AreaRenderer3D)localCategoryPlot3D.getRenderer();
    localAreaRenderer3D.setBaseColor(Color.GRAY);
    localAreaRenderer3D.setColors(Colors.getSAPMultiColor());
    return localChart3D;
  }
  
  public static CategoryDataset3D createDataset()
  {
    StandardCategoryDataset3D localStandardCategoryDataset3D = new StandardCategoryDataset3D();
    DefaultKeyedValues localDefaultKeyedValues1 = new DefaultKeyedValues();
    localDefaultKeyedValues1.put("A", Integer.valueOf(-1));
    localDefaultKeyedValues1.put("B", Integer.valueOf(-4));
    localDefaultKeyedValues1.put("C", Integer.valueOf(-9));
    localDefaultKeyedValues1.put("D", Integer.valueOf(-5));
    localDefaultKeyedValues1.put("E", Integer.valueOf(-5));
    localDefaultKeyedValues1.put("F", Integer.valueOf(-2));
    localDefaultKeyedValues1.put("G", Integer.valueOf(-4));
    localDefaultKeyedValues1.put("H", Integer.valueOf(-7));
    localDefaultKeyedValues1.put("I", Integer.valueOf(-3));
    localDefaultKeyedValues1.put("J", Integer.valueOf(-1));
    localStandardCategoryDataset3D.addSeriesAsRow("Series 1", localDefaultKeyedValues1);
    DefaultKeyedValues localDefaultKeyedValues2 = new DefaultKeyedValues();
    localDefaultKeyedValues2.put("A", Integer.valueOf(1));
    localDefaultKeyedValues2.put("B", Integer.valueOf(12));
    localDefaultKeyedValues2.put("C", Integer.valueOf(14));
    localDefaultKeyedValues2.put("D", Integer.valueOf(7));
    localDefaultKeyedValues2.put("E", Integer.valueOf(2));
    localDefaultKeyedValues2.put("F", Integer.valueOf(-9));
    localDefaultKeyedValues2.put("G", Integer.valueOf(-14));
    localDefaultKeyedValues2.put("H", Integer.valueOf(0));
    localDefaultKeyedValues2.put("I", Integer.valueOf(12));
    localDefaultKeyedValues2.put("J", Integer.valueOf(4));
    localStandardCategoryDataset3D.addSeriesAsRow("Series 2", localDefaultKeyedValues2);
    DefaultKeyedValues localDefaultKeyedValues3 = new DefaultKeyedValues();
    localDefaultKeyedValues3.put("A", Integer.valueOf(5));
    localDefaultKeyedValues3.put("B", Integer.valueOf(13));
    localDefaultKeyedValues3.put("C", Integer.valueOf(19));
    localDefaultKeyedValues3.put("D", Integer.valueOf(11));
    localDefaultKeyedValues3.put("E", Integer.valueOf(10));
    localDefaultKeyedValues3.put("F", Integer.valueOf(5));
    localDefaultKeyedValues3.put("G", Integer.valueOf(-7));
    localDefaultKeyedValues3.put("H", Integer.valueOf(-3));
    localDefaultKeyedValues3.put("I", Integer.valueOf(-15));
    localDefaultKeyedValues3.put("J", Integer.valueOf(-20));
    localStandardCategoryDataset3D.addSeriesAsRow("Series 3", localDefaultKeyedValues3);
    DefaultKeyedValues localDefaultKeyedValues4 = new DefaultKeyedValues();
    localDefaultKeyedValues4.put("A", Integer.valueOf(5));
    localDefaultKeyedValues4.put("B", Integer.valueOf(18));
    localDefaultKeyedValues4.put("C", Integer.valueOf(20));
    localDefaultKeyedValues4.put("D", Integer.valueOf(17));
    localDefaultKeyedValues4.put("E", Integer.valueOf(11));
    localDefaultKeyedValues4.put("F", Integer.valueOf(19));
    localDefaultKeyedValues4.put("G", Integer.valueOf(25));
    localDefaultKeyedValues4.put("H", Integer.valueOf(12));
    localDefaultKeyedValues4.put("I", Integer.valueOf(4));
    localDefaultKeyedValues4.put("J", Integer.valueOf(2));
    localStandardCategoryDataset3D.addSeriesAsRow("Series 4", localDefaultKeyedValues4);
    return localStandardCategoryDataset3D;
  }
}


/* Location:           C:\Users\vinchs\Desktop\temp\
 * Qualified Name:     demo.orsoncharts.AreaChart3D2
 * JD-Core Version:    0.7.0.1
 */