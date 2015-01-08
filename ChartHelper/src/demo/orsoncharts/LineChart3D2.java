package demo.orsoncharts;

import com.orsoncharts.Chart3D;
import com.orsoncharts.Chart3DFactory;
import com.orsoncharts.data.DefaultKeyedValues;
import com.orsoncharts.data.category.CategoryDataset3D;
import com.orsoncharts.data.category.StandardCategoryDataset3D;
import com.orsoncharts.legend.LegendAnchor;
import java.awt.Color;

public class LineChart3D2
{
  public static Chart3D createChart(CategoryDataset3D paramCategoryDataset3D)
  {
    Chart3D localChart3D = Chart3DFactory.createLineChart("Quarterly Profits", "Large Banks in USA", paramCategoryDataset3D, null, "Quarter", "$ millions");
    localChart3D.setChartBoxColor(new Color(255, 255, 255, 128));
    localChart3D.setLegendAnchor(LegendAnchor.TOP_RIGHT);
    return localChart3D;
  }
  
  public static CategoryDataset3D createDataset()
  {
    StandardCategoryDataset3D localStandardCategoryDataset3D = new StandardCategoryDataset3D();
    DefaultKeyedValues localDefaultKeyedValues1 = new DefaultKeyedValues();
    localDefaultKeyedValues1.put("Q3/11", Integer.valueOf(5889));
    localDefaultKeyedValues1.put("Q4/11", Integer.valueOf(1584));
    localDefaultKeyedValues1.put("Q1/12", Integer.valueOf(328));
    localDefaultKeyedValues1.put("Q2/12", Integer.valueOf(2098));
    localDefaultKeyedValues1.put("Q3/12", Integer.valueOf(-33));
    localDefaultKeyedValues1.put("Q4/12", Integer.valueOf(367));
    localDefaultKeyedValues1.put("Q1/13", Integer.valueOf(1110));
    localDefaultKeyedValues1.put("Q2/13", Integer.valueOf(3571));
    localDefaultKeyedValues1.put("Q3/13", Integer.valueOf(2218));
    localStandardCategoryDataset3D.addSeriesAsRow("Bank of America", localDefaultKeyedValues1);
    DefaultKeyedValues localDefaultKeyedValues2 = new DefaultKeyedValues();
    localDefaultKeyedValues2.put("Q3/11", Integer.valueOf(3771));
    localDefaultKeyedValues2.put("Q4/11", Integer.valueOf(956));
    localDefaultKeyedValues2.put("Q1/12", Integer.valueOf(2931));
    localDefaultKeyedValues2.put("Q2/12", Integer.valueOf(2946));
    localDefaultKeyedValues2.put("Q3/12", Integer.valueOf(468));
    localDefaultKeyedValues2.put("Q4/12", Integer.valueOf(1196));
    localDefaultKeyedValues2.put("Q1/13", Integer.valueOf(3808));
    localDefaultKeyedValues2.put("Q2/13", Integer.valueOf(4182));
    localDefaultKeyedValues2.put("Q3/13", Integer.valueOf(3227));
    localStandardCategoryDataset3D.addSeriesAsRow("Citigroup", localDefaultKeyedValues2);
    DefaultKeyedValues localDefaultKeyedValues3 = new DefaultKeyedValues();
    localDefaultKeyedValues3.put("Q3/11", Integer.valueOf(4055));
    localDefaultKeyedValues3.put("Q4/11", Integer.valueOf(4107));
    localDefaultKeyedValues3.put("Q1/12", Integer.valueOf(4248));
    localDefaultKeyedValues3.put("Q2/12", Integer.valueOf(4622));
    localDefaultKeyedValues3.put("Q3/12", Integer.valueOf(4937));
    localDefaultKeyedValues3.put("Q4/12", Integer.valueOf(4857));
    localDefaultKeyedValues3.put("Q1/13", Integer.valueOf(4931));
    localDefaultKeyedValues3.put("Q2/13", Integer.valueOf(5272));
    localDefaultKeyedValues3.put("Q3/13", Integer.valueOf(5317));
    localStandardCategoryDataset3D.addSeriesAsRow("Wells Fargo", localDefaultKeyedValues3);
    DefaultKeyedValues localDefaultKeyedValues4 = new DefaultKeyedValues();
    localDefaultKeyedValues4.put("Q3/11", Integer.valueOf(4262));
    localDefaultKeyedValues4.put("Q4/11", Integer.valueOf(3728));
    localDefaultKeyedValues4.put("Q1/12", Integer.valueOf(4924));
    localDefaultKeyedValues4.put("Q2/12", Integer.valueOf(4960));
    localDefaultKeyedValues4.put("Q3/12", Integer.valueOf(5708));
    localDefaultKeyedValues4.put("Q4/12", Integer.valueOf(5692));
    localDefaultKeyedValues4.put("Q1/13", Integer.valueOf(6529));
    localDefaultKeyedValues4.put("Q2/13", Integer.valueOf(6496));
    localDefaultKeyedValues4.put("Q3/13", Integer.valueOf(-380));
    localStandardCategoryDataset3D.addSeriesAsRow("J.P.Morgan", localDefaultKeyedValues4);
    return localStandardCategoryDataset3D;
  }
}


/* Location:           C:\Users\vinchs\Desktop\temp\
 * Qualified Name:     demo.orsoncharts.LineChart3D2
 * JD-Core Version:    0.7.0.1
 */