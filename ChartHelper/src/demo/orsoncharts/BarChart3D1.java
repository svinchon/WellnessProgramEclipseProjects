package demo.orsoncharts;

import com.orsoncharts.Chart3D;
import com.orsoncharts.Chart3DFactory;
import com.orsoncharts.data.DefaultKeyedValues;
import com.orsoncharts.data.category.CategoryDataset3D;
import com.orsoncharts.data.category.StandardCategoryDataset3D;
import com.orsoncharts.legend.LegendAnchor;
import com.orsoncharts.plot.CategoryPlot3D;
import java.awt.Color;

public class BarChart3D1
{
  public static Chart3D createChart(CategoryDataset3D paramCategoryDataset3D)
  {
    Chart3D localChart3D = Chart3DFactory.createBarChart("Quarterly Revenues", "For some large IT companies", paramCategoryDataset3D, null, "Quarter", "$billion Revenues");
    localChart3D.setChartBoxColor(new Color(255, 255, 255, 127));
    localChart3D.setLegendAnchor(LegendAnchor.BOTTOM_RIGHT);
    CategoryPlot3D localCategoryPlot3D = (CategoryPlot3D)localChart3D.getPlot();
    localCategoryPlot3D.setGridlinePaintForValues(Color.BLACK);
    return localChart3D;
  }
  
  public static CategoryDataset3D createDataset()
  {
    StandardCategoryDataset3D localStandardCategoryDataset3D = new StandardCategoryDataset3D();
    DefaultKeyedValues localDefaultKeyedValues1 = new DefaultKeyedValues();
    localDefaultKeyedValues1.put("Q2/11", Double.valueOf(8.180999999999999D));
    localDefaultKeyedValues1.put("Q3/11", Double.valueOf(8.792D));
    localDefaultKeyedValues1.put("Q4/11", Double.valueOf(9.039D));
    localDefaultKeyedValues1.put("Q1/12", Double.valueOf(10.916D));
    localDefaultKeyedValues1.put("Q2/12", Double.valueOf(8.180999999999999D));
    localDefaultKeyedValues1.put("Q3/12", Double.valueOf(9.093999999999999D));
    localDefaultKeyedValues1.put("Q4/12", Double.valueOf(8.958D));
    localDefaultKeyedValues1.put("Q1/13", Double.valueOf(10.946999999999999D));
    localDefaultKeyedValues1.put("Q2/13", Double.valueOf(8.372D));
    localDefaultKeyedValues1.put("Q3/13", Double.valueOf(9.275D));
    localStandardCategoryDataset3D.addSeriesAsRow("Oracle", localDefaultKeyedValues1);
    DefaultKeyedValues localDefaultKeyedValues2 = new DefaultKeyedValues();
    localDefaultKeyedValues2.put("Q2/11", Double.valueOf(9.029999999999999D));
    localDefaultKeyedValues2.put("Q3/11", Double.valueOf(9.720000000000001D));
    localDefaultKeyedValues2.put("Q4/11", Double.valueOf(10.58D));
    localDefaultKeyedValues2.put("Q1/12", Double.valueOf(10.65D));
    localDefaultKeyedValues2.put("Q2/12", Double.valueOf(12.214D));
    localDefaultKeyedValues2.put("Q3/12", Double.valueOf(14.101000000000001D));
    localDefaultKeyedValues2.put("Q4/12", Double.valueOf(14.419D));
    localDefaultKeyedValues2.put("Q1/13", Double.valueOf(13.968999999999999D));
    localDefaultKeyedValues2.put("Q2/13", Double.valueOf(14.105D));
    localDefaultKeyedValues2.put("Q3/13", Double.valueOf(14.893000000000001D));
    localDefaultKeyedValues2.put("Q4/13", Double.valueOf(16.858000000000001D));
    localStandardCategoryDataset3D.addSeriesAsRow("Google", localDefaultKeyedValues2);
    DefaultKeyedValues localDefaultKeyedValues3 = new DefaultKeyedValues();
    localDefaultKeyedValues3.put("Q2/11", Double.valueOf(17.370000000000001D));
    localDefaultKeyedValues3.put("Q3/11", Double.valueOf(17.370000000000001D));
    localDefaultKeyedValues3.put("Q4/11", Double.valueOf(20.890000000000001D));
    localDefaultKeyedValues3.put("Q1/12", Double.valueOf(17.41D));
    localDefaultKeyedValues3.put("Q2/12", Double.valueOf(18.059999999999999D));
    localDefaultKeyedValues3.put("Q3/12", Double.valueOf(16.007999999999999D));
    localDefaultKeyedValues3.put("Q4/12", Double.valueOf(21.456D));
    localDefaultKeyedValues3.put("Q1/13", Double.valueOf(20.489000000000001D));
    localDefaultKeyedValues3.put("Q2/13", Double.valueOf(19.896000000000001D));
    localDefaultKeyedValues3.put("Q3/13", Double.valueOf(18.529D));
    localDefaultKeyedValues3.put("Q4/13", Double.valueOf(24.518999999999998D));
    localStandardCategoryDataset3D.addSeriesAsRow("Microsoft", localDefaultKeyedValues3);
    DefaultKeyedValues localDefaultKeyedValues4 = new DefaultKeyedValues();
    localDefaultKeyedValues4.put("Q2/11", Double.valueOf(28.57D));
    localDefaultKeyedValues4.put("Q3/11", Double.valueOf(28.27D));
    localDefaultKeyedValues4.put("Q4/11", Double.valueOf(46.329999999999998D));
    localDefaultKeyedValues4.put("Q1/12", Double.valueOf(39.200000000000003D));
    localDefaultKeyedValues4.put("Q2/12", Double.valueOf(35.0D));
    localDefaultKeyedValues4.put("Q3/12", Double.valueOf(36.0D));
    localDefaultKeyedValues4.put("Q4/12", Double.valueOf(54.5D));
    localDefaultKeyedValues4.put("Q1/13", Double.valueOf(43.600000000000001D));
    localDefaultKeyedValues4.put("Q2/13", Double.valueOf(35.323D));
    localDefaultKeyedValues4.put("Q3/13", Double.valueOf(37.5D));
    localDefaultKeyedValues4.put("Q4/13", Double.valueOf(57.594000000000001D));
    localStandardCategoryDataset3D.addSeriesAsRow("Apple", localDefaultKeyedValues4);
    return localStandardCategoryDataset3D;
  }
}


/* Location:           C:\Users\vinchs\Desktop\temp\
 * Qualified Name:     demo.orsoncharts.BarChart3D1
 * JD-Core Version:    0.7.0.1
 */