package demo.orsoncharts;

import com.orsoncharts.Chart3D;
import com.orsoncharts.Chart3DFactory;
import com.orsoncharts.Colors;
import com.orsoncharts.axis.CategoryAxis3D;
import com.orsoncharts.axis.NumberAxis3D;
import com.orsoncharts.axis.NumberTickSelector;
import com.orsoncharts.data.DefaultKeyedValues;
import com.orsoncharts.data.KeyedValues;
import com.orsoncharts.data.category.CategoryDataset3D;
import com.orsoncharts.data.category.StandardCategoryDataset3D;
import com.orsoncharts.graphics3d.Dimension3D;
import com.orsoncharts.graphics3d.ViewPoint3D;
import com.orsoncharts.plot.CategoryPlot3D;
import com.orsoncharts.renderer.category.CategoryRenderer3D;

public class LineChart3D1
{
  public static Chart3D createChart(CategoryDataset3D paramCategoryDataset3D)
  {
    Chart3D localChart3D = Chart3DFactory.createLineChart("Web Browser Market Share", "Source: http://gs.statcounter.com", paramCategoryDataset3D, null, null, "Market Share (%)");
    CategoryPlot3D localCategoryPlot3D = (CategoryPlot3D)localChart3D.getPlot();
    localCategoryPlot3D.setDimensions(new Dimension3D(18.0D, 8.0D, 4.0D));
    localCategoryPlot3D.getRowAxis().setVisible(false);
    NumberAxis3D localNumberAxis3D = (NumberAxis3D)localCategoryPlot3D.getValueAxis();
    localNumberAxis3D.setTickSelector(new NumberTickSelector(true));
    localCategoryPlot3D.getRenderer().setColors(Colors.createFancyDarkColors());
    localChart3D.setViewPoint(ViewPoint3D.createAboveViewPoint(30.0D));
    return localChart3D;
  }
  
  public static CategoryDataset3D createDataset()
  {
    StandardCategoryDataset3D localStandardCategoryDataset3D = new StandardCategoryDataset3D();
    localStandardCategoryDataset3D.addSeriesAsRow("Safari", createSafariData());
    localStandardCategoryDataset3D.addSeriesAsRow("Firefox", createFirefoxData());
    localStandardCategoryDataset3D.addSeriesAsRow("Internet Explorer", createInternetExplorerData());
    localStandardCategoryDataset3D.addSeriesAsRow("Chrome", createChromeData());
    return localStandardCategoryDataset3D;
  }
  
  private static KeyedValues<Double> createChromeData()
  {
    DefaultKeyedValues localDefaultKeyedValues = new DefaultKeyedValues();
    localDefaultKeyedValues.put("Jan-12", Double.valueOf(0.284D));
    localDefaultKeyedValues.put("Feb-12", Double.valueOf(0.2984D));
    localDefaultKeyedValues.put("Mar-12", Double.valueOf(0.3087D));
    localDefaultKeyedValues.put("Apr-12", Double.valueOf(0.3123D));
    localDefaultKeyedValues.put("May-12", Double.valueOf(0.3243D));
    localDefaultKeyedValues.put("Jun-12", Double.valueOf(0.3276D));
    localDefaultKeyedValues.put("Jul-12", Double.valueOf(0.3381D));
    localDefaultKeyedValues.put("Aug-12", Double.valueOf(0.3359D));
    localDefaultKeyedValues.put("Sep-12", Double.valueOf(0.3421D));
    localDefaultKeyedValues.put("Oct-12", Double.valueOf(0.3477D));
    localDefaultKeyedValues.put("Nov-12", Double.valueOf(0.3572D));
    localDefaultKeyedValues.put("Dec-12", Double.valueOf(0.3642D));
    localDefaultKeyedValues.put("Jan-13", Double.valueOf(0.3652D));
    localDefaultKeyedValues.put("Feb-13", Double.valueOf(0.3709D));
    localDefaultKeyedValues.put("Mar-13", Double.valueOf(0.3807D));
    localDefaultKeyedValues.put("Apr-13", Double.valueOf(0.3915D));
    localDefaultKeyedValues.put("May-13", Double.valueOf(0.4138D));
    localDefaultKeyedValues.put("Jun-13", Double.valueOf(0.4268D));
    return localDefaultKeyedValues;
  }
  
  private static KeyedValues<Double> createFirefoxData()
  {
    DefaultKeyedValues localDefaultKeyedValues = new DefaultKeyedValues();
    localDefaultKeyedValues.put("Jan-12", Double.valueOf(0.2478D));
    localDefaultKeyedValues.put("Feb-12", Double.valueOf(0.2488D));
    localDefaultKeyedValues.put("Mar-12", Double.valueOf(0.2498D));
    localDefaultKeyedValues.put("Apr-12", Double.valueOf(0.2487D));
    localDefaultKeyedValues.put("May-12", Double.valueOf(0.2555D));
    localDefaultKeyedValues.put("Jun-12", Double.valueOf(0.2456D));
    localDefaultKeyedValues.put("Jul-12", Double.valueOf(0.2373D));
    localDefaultKeyedValues.put("Aug-12", Double.valueOf(0.2285D));
    localDefaultKeyedValues.put("Sep-12", Double.valueOf(0.224D));
    localDefaultKeyedValues.put("Oct-12", Double.valueOf(0.2232D));
    localDefaultKeyedValues.put("Nov-12", Double.valueOf(0.2237D));
    localDefaultKeyedValues.put("Dec-12", Double.valueOf(0.2189D));
    localDefaultKeyedValues.put("Jan-13", Double.valueOf(0.2142D));
    localDefaultKeyedValues.put("Feb-13", Double.valueOf(0.2134D));
    localDefaultKeyedValues.put("Mar-13", Double.valueOf(0.2087D));
    localDefaultKeyedValues.put("Apr-13", Double.valueOf(0.2006D));
    localDefaultKeyedValues.put("May-13", Double.valueOf(0.1976D));
    localDefaultKeyedValues.put("Jun-13", Double.valueOf(0.2001D));
    return localDefaultKeyedValues;
  }
  
  private static KeyedValues<Double> createInternetExplorerData()
  {
    DefaultKeyedValues localDefaultKeyedValues = new DefaultKeyedValues();
    localDefaultKeyedValues.put("Jan-12", Double.valueOf(0.3745D));
    localDefaultKeyedValues.put("Feb-12", Double.valueOf(0.3575D));
    localDefaultKeyedValues.put("Mar-12", Double.valueOf(0.3481D));
    localDefaultKeyedValues.put("Apr-12", Double.valueOf(0.3407D));
    localDefaultKeyedValues.put("May-12", Double.valueOf(0.3212D));
    localDefaultKeyedValues.put("Jun-12", Double.valueOf(0.3231D));
    localDefaultKeyedValues.put("Jul-12", Double.valueOf(0.3204D));
    localDefaultKeyedValues.put("Aug-12", Double.valueOf(0.3285D));
    localDefaultKeyedValues.put("Sep-12", Double.valueOf(0.327D));
    localDefaultKeyedValues.put("Oct-12", Double.valueOf(0.3208D));
    localDefaultKeyedValues.put("Nov-12", Double.valueOf(0.3123D));
    localDefaultKeyedValues.put("Dec-12", Double.valueOf(0.3078D));
    localDefaultKeyedValues.put("Jan-13", Double.valueOf(0.3069D));
    localDefaultKeyedValues.put("Feb-13", Double.valueOf(0.2982D));
    localDefaultKeyedValues.put("Mar-13", Double.valueOf(0.293D));
    localDefaultKeyedValues.put("Jun-13", Double.valueOf(0.2544D));
    localDefaultKeyedValues.put("May-13", Double.valueOf(0.2772D));
    localDefaultKeyedValues.put("Apr-13", Double.valueOf(0.2971D));
    return localDefaultKeyedValues;
  }
  
  private static KeyedValues<Double> createSafariData()
  {
    DefaultKeyedValues localDefaultKeyedValues = new DefaultKeyedValues();
    localDefaultKeyedValues.put("Jan-12", Double.valueOf(0.0662D));
    localDefaultKeyedValues.put("Feb-12", Double.valueOf(0.0677D));
    localDefaultKeyedValues.put("Mar-12", Double.valueOf(0.0672D));
    localDefaultKeyedValues.put("Apr-12", Double.valueOf(0.0713D));
    localDefaultKeyedValues.put("May-12", Double.valueOf(0.07090000000000001D));
    localDefaultKeyedValues.put("Jun-12", Double.valueOf(0.07000000000000001D));
    localDefaultKeyedValues.put("Jul-12", Double.valueOf(0.0712D));
    localDefaultKeyedValues.put("Aug-12", Double.valueOf(0.07389999999999999D));
    localDefaultKeyedValues.put("Sep-12", Double.valueOf(0.077D));
    localDefaultKeyedValues.put("Oct-12", Double.valueOf(0.0781D));
    localDefaultKeyedValues.put("Nov-12", Double.valueOf(0.0783D));
    localDefaultKeyedValues.put("Dec-12", Double.valueOf(0.07920000000000001D));
    localDefaultKeyedValues.put("Jan-13", Double.valueOf(0.083D));
    localDefaultKeyedValues.put("Feb-13", Double.valueOf(0.08599999999999999D));
    localDefaultKeyedValues.put("Mar-13", Double.valueOf(0.08500000000000001D));
    localDefaultKeyedValues.put("Apr-13", Double.valueOf(0.08D));
    localDefaultKeyedValues.put("May-13", Double.valueOf(0.0796D));
    localDefaultKeyedValues.put("Jun-13", Double.valueOf(0.0839D));
    return localDefaultKeyedValues;
  }
}


/* Location:           C:\Users\vinchs\Desktop\temp\
 * Qualified Name:     demo.orsoncharts.LineChart3D1
 * JD-Core Version:    0.7.0.1
 */