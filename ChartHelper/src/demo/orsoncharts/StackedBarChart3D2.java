package demo.orsoncharts;

import com.orsoncharts.Chart3D;
import com.orsoncharts.Chart3DFactory;
import com.orsoncharts.Colors;
import com.orsoncharts.axis.CategoryAxis3D;
import com.orsoncharts.data.category.CategoryDataset3D;
import com.orsoncharts.data.category.StandardCategoryDataset3D;
import com.orsoncharts.graphics3d.Dimension3D;
import com.orsoncharts.graphics3d.ViewPoint3D;
import com.orsoncharts.plot.CategoryPlot3D;
import com.orsoncharts.renderer.category.StackedBarRenderer3D;

public class StackedBarChart3D2
{
  public static Chart3D createChart(CategoryDataset3D paramCategoryDataset3D)
  {
    Chart3D localChart3D = Chart3DFactory.createStackedBarChart("Water Usage Chart", "Source: http://en.wikipedia.org/wiki/Peak_water#Water_supply", paramCategoryDataset3D, null, null, "Cubic meters / person / year");
    CategoryPlot3D localCategoryPlot3D = (CategoryPlot3D)localChart3D.getPlot();
    localCategoryPlot3D.setDimensions(new Dimension3D(14.0D, 6.5D, 2.0D));
    localCategoryPlot3D.getRowAxis().setVisible(false);
    StackedBarRenderer3D localStackedBarRenderer3D = (StackedBarRenderer3D)localCategoryPlot3D.getRenderer();
    localStackedBarRenderer3D.setBarZWidth(0.3D);
    localStackedBarRenderer3D.setColors(Colors.createBlueOceanColors());
    localChart3D.getViewPoint().moveUpDown(0.1047197551196598D);
    return localChart3D;
  }
  
  public static CategoryDataset3D createDataset()
  {
    StandardCategoryDataset3D localStandardCategoryDataset3D = new StandardCategoryDataset3D();
    localStandardCategoryDataset3D.addValue(Integer.valueOf(197), "Agricultural", "R1", "Brazil");
    localStandardCategoryDataset3D.addValue(Integer.valueOf(64), "Domestic", "R1", "Brazil");
    localStandardCategoryDataset3D.addValue(Integer.valueOf(57), "Industrial", "R1", "Brazil");
    localStandardCategoryDataset3D.addValue(Integer.valueOf(339), "Agricultural", "R1", "Indonesia");
    localStandardCategoryDataset3D.addValue(Integer.valueOf(30), "Domestic", "R1", "Indonesia");
    localStandardCategoryDataset3D.addValue(Integer.valueOf(4), "Industrial", "R1", "Indonesia");
    localStandardCategoryDataset3D.addValue(Integer.valueOf(279), "Agricultural", "R1", "China");
    localStandardCategoryDataset3D.addValue(Integer.valueOf(27), "Domestic", "R1", "China");
    localStandardCategoryDataset3D.addValue(Integer.valueOf(107), "Industrial", "R1", "China");
    localStandardCategoryDataset3D.addValue(Integer.valueOf(92), "Agricultural", "R1", "Germany");
    localStandardCategoryDataset3D.addValue(Integer.valueOf(55), "Domestic", "R1", "Germany");
    localStandardCategoryDataset3D.addValue(Integer.valueOf(313), "Industrial", "R1", "Germany");
    localStandardCategoryDataset3D.addValue(Integer.valueOf(96), "Agricultural", "R1", "Russia");
    localStandardCategoryDataset3D.addValue(Integer.valueOf(102), "Domestic", "R1", "Russia");
    localStandardCategoryDataset3D.addValue(Integer.valueOf(337), "Industrial", "R1", "Russia");
    localStandardCategoryDataset3D.addValue(Integer.valueOf(403), "Agricultural", "R1", "Turkey");
    localStandardCategoryDataset3D.addValue(Integer.valueOf(82), "Domestic", "R1", "Turkey");
    localStandardCategoryDataset3D.addValue(Integer.valueOf(60), "Industrial", "R1", "Turkey");
    localStandardCategoryDataset3D.addValue(Integer.valueOf(536), "Agricultural", "R1", "Bangladesh");
    localStandardCategoryDataset3D.addValue(Integer.valueOf(17), "Domestic", "R1", "Bangladesh");
    localStandardCategoryDataset3D.addValue(Integer.valueOf(6), "Industrial", "R1", "Bangladesh");
    localStandardCategoryDataset3D.addValue(Integer.valueOf(508), "Agricultural", "R1", "India");
    localStandardCategoryDataset3D.addValue(Integer.valueOf(47), "Domestic", "R1", "India");
    localStandardCategoryDataset3D.addValue(Integer.valueOf(30), "Industrial", "R1", "India");
    localStandardCategoryDataset3D.addValue(Integer.valueOf(428), "Agricultural", "R1", "Japan");
    localStandardCategoryDataset3D.addValue(Integer.valueOf(138), "Domestic", "R1", "Japan");
    localStandardCategoryDataset3D.addValue(Integer.valueOf(124), "Industrial", "R1", "Japan");
    localStandardCategoryDataset3D.addValue(Integer.valueOf(325), "Agricultural", "R1", "Italy");
    localStandardCategoryDataset3D.addValue(Integer.valueOf(130), "Domestic", "R1", "Italy");
    localStandardCategoryDataset3D.addValue(Integer.valueOf(268), "Industrial", "R1", "Italy");
    localStandardCategoryDataset3D.addValue(Integer.valueOf(569), "Agricultural", "R1", "Mexico");
    localStandardCategoryDataset3D.addValue(Integer.valueOf(126), "Domestic", "R1", "Mexico");
    localStandardCategoryDataset3D.addValue(Integer.valueOf(37), "Industrial", "R1", "Mexico");
    localStandardCategoryDataset3D.addValue(Integer.valueOf(576), "Agricultural", "R1", "Vietnam");
    localStandardCategoryDataset3D.addValue(Integer.valueOf(68), "Domestic", "R1", "Vietnam");
    localStandardCategoryDataset3D.addValue(Integer.valueOf(203), "Industrial", "R1", "Vietnam");
    localStandardCategoryDataset3D.addValue(Integer.valueOf(794), "Agricultural", "R1", "Egypt");
    localStandardCategoryDataset3D.addValue(Integer.valueOf(74), "Domestic", "R1", "Egypt");
    localStandardCategoryDataset3D.addValue(Integer.valueOf(55), "Industrial", "R1", "Egypt");
    localStandardCategoryDataset3D.addValue(Integer.valueOf(954), "Agricultural", "R1", "Iran");
    localStandardCategoryDataset3D.addValue(Integer.valueOf(21), "Domestic", "R1", "Iran");
    localStandardCategoryDataset3D.addValue(Integer.valueOf(73), "Industrial", "R1", "Iran");
    localStandardCategoryDataset3D.addValue(Integer.valueOf(1029), "Agricultural", "R1", "Pakistan");
    localStandardCategoryDataset3D.addValue(Integer.valueOf(21), "Domestic", "R1", "Pakistan");
    localStandardCategoryDataset3D.addValue(Integer.valueOf(21), "Industrial", "R1", "Pakistan");
    localStandardCategoryDataset3D.addValue(Integer.valueOf(1236), "Agricultural", "R1", "Thailand");
    localStandardCategoryDataset3D.addValue(Integer.valueOf(26), "Domestic", "R1", "Thailand");
    localStandardCategoryDataset3D.addValue(Integer.valueOf(26), "Industrial", "R1", "Thailand");
    localStandardCategoryDataset3D.addValue(Integer.valueOf(165), "Agricultural", "R1", "Canada");
    localStandardCategoryDataset3D.addValue(Integer.valueOf(274), "Domestic", "R1", "Canada");
    localStandardCategoryDataset3D.addValue(Integer.valueOf(947), "Industrial", "R1", "Canada");
    localStandardCategoryDataset3D.addValue(Integer.valueOf(1363), "Agricultural", "R1", "Iraq");
    localStandardCategoryDataset3D.addValue(Integer.valueOf(44), "Domestic", "R1", "Iraq");
    localStandardCategoryDataset3D.addValue(Integer.valueOf(74), "Industrial", "R1", "Iraq");
    localStandardCategoryDataset3D.addValue(Integer.valueOf(656), "Agricultural", "R1", "US");
    localStandardCategoryDataset3D.addValue(Integer.valueOf(208), "Domestic", "R1", "US");
    localStandardCategoryDataset3D.addValue(Integer.valueOf(736), "Industrial", "R1", "US");
    localStandardCategoryDataset3D.addValue(Integer.valueOf(2040), "Agricultural", "R1", "Uzbekistan");
    localStandardCategoryDataset3D.addValue(Integer.valueOf(110), "Domestic", "R1", "Uzbekistan");
    localStandardCategoryDataset3D.addValue(Integer.valueOf(44), "Industrial", "R1", "Uzbekistan");
    return localStandardCategoryDataset3D;
  }
}


/* Location:           C:\Users\vinchs\Desktop\temp\
 * Qualified Name:     demo.orsoncharts.StackedBarChart3D2
 * JD-Core Version:    0.7.0.1
 */