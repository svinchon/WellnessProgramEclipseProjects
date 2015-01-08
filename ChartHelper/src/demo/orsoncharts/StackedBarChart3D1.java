package demo.orsoncharts;

import com.orsoncharts.Chart3D;
import com.orsoncharts.Chart3DFactory;
import com.orsoncharts.data.DefaultKeyedValues;
import com.orsoncharts.data.category.CategoryDataset3D;
import com.orsoncharts.data.category.StandardCategoryDataset3D;

public class StackedBarChart3D1
{
  public static Chart3D createChart(CategoryDataset3D paramCategoryDataset3D)
  {
    Chart3D localChart3D = Chart3DFactory.createStackedBarChart("Stacked Bar Chart", "Put the data source here", paramCategoryDataset3D, null, null, "Value");
    return localChart3D;
  }
  
  public static CategoryDataset3D createDataset()
  {
    StandardCategoryDataset3D localStandardCategoryDataset3D = new StandardCategoryDataset3D();
    DefaultKeyedValues localDefaultKeyedValues1 = new DefaultKeyedValues();
    localDefaultKeyedValues1.put("A", Double.valueOf(4.0D));
    localDefaultKeyedValues1.put("B", Double.valueOf(2.0D));
    localDefaultKeyedValues1.put("C", Double.valueOf(3.0D));
    localDefaultKeyedValues1.put("D", Double.valueOf(5.0D));
    localDefaultKeyedValues1.put("E", Double.valueOf(2.0D));
    localDefaultKeyedValues1.put("F", Double.valueOf(1.0D));
    DefaultKeyedValues localDefaultKeyedValues2 = new DefaultKeyedValues();
    localDefaultKeyedValues2.put("A", Double.valueOf(1.0D));
    localDefaultKeyedValues2.put("B", Double.valueOf(2.0D));
    localDefaultKeyedValues2.put("C", Double.valueOf(3.0D));
    localDefaultKeyedValues2.put("D", Double.valueOf(2.0D));
    localDefaultKeyedValues2.put("E", Double.valueOf(3.0D));
    localDefaultKeyedValues2.put("F", Double.valueOf(1.0D));
    DefaultKeyedValues localDefaultKeyedValues3 = new DefaultKeyedValues();
    localDefaultKeyedValues3.put("A", Double.valueOf(6.0D));
    localDefaultKeyedValues3.put("B", Double.valueOf(6.0D));
    localDefaultKeyedValues3.put("C", Double.valueOf(6.0D));
    localDefaultKeyedValues3.put("D", Double.valueOf(4.0D));
    localDefaultKeyedValues3.put("E", Double.valueOf(4.0D));
    localDefaultKeyedValues3.put("F", Double.valueOf(4.0D));
    DefaultKeyedValues localDefaultKeyedValues4 = new DefaultKeyedValues();
    localDefaultKeyedValues4.put("A", Double.valueOf(9.0D));
    localDefaultKeyedValues4.put("B", Double.valueOf(8.0D));
    localDefaultKeyedValues4.put("C", Double.valueOf(7.0D));
    localDefaultKeyedValues4.put("D", Double.valueOf(6.0D));
    localDefaultKeyedValues4.put("D", Double.valueOf(3.0D));
    localDefaultKeyedValues4.put("E", Double.valueOf(4.0D));
    localDefaultKeyedValues4.put("F", Double.valueOf(6.0D));
    DefaultKeyedValues localDefaultKeyedValues5 = new DefaultKeyedValues();
    localDefaultKeyedValues5.put("A", Double.valueOf(9.0D));
    localDefaultKeyedValues5.put("B", Double.valueOf(8.0D));
    localDefaultKeyedValues5.put("C", Double.valueOf(7.0D));
    localDefaultKeyedValues5.put("D", Double.valueOf(6.0D));
    localDefaultKeyedValues5.put("E", Double.valueOf(7.0D));
    localDefaultKeyedValues5.put("F", Double.valueOf(9.0D));
    localStandardCategoryDataset3D.addSeriesAsRow("Series 1", "Row 1", localDefaultKeyedValues1);
    localStandardCategoryDataset3D.addSeriesAsRow("Series 2", "Row 2", localDefaultKeyedValues2);
    localStandardCategoryDataset3D.addSeriesAsRow("Series 3", "Row 2", localDefaultKeyedValues3);
    localStandardCategoryDataset3D.addSeriesAsRow("Series 4", "Row 3", localDefaultKeyedValues4);
    localStandardCategoryDataset3D.addSeriesAsRow("Series 5", "Row 3", localDefaultKeyedValues5);
    return localStandardCategoryDataset3D;
  }
}


/* Location:           C:\Users\vinchs\Desktop\temp\
 * Qualified Name:     demo.orsoncharts.StackedBarChart3D1
 * JD-Core Version:    0.7.0.1
 */