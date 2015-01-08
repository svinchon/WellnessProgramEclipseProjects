package demo.orsoncharts;

import com.orsoncharts.Chart3D;
import com.orsoncharts.Chart3DFactory;
import com.orsoncharts.Colors;
import com.orsoncharts.axis.NumberAxis3D;
import com.orsoncharts.axis.StandardCategoryAxis3D;
import com.orsoncharts.data.DefaultKeyedValues;
import com.orsoncharts.data.category.CategoryDataset3D;
import com.orsoncharts.data.category.StandardCategoryDataset3D;
import com.orsoncharts.graphics3d.ViewPoint3D;
import com.orsoncharts.label.StandardCategoryItemLabelGenerator;
import com.orsoncharts.legend.LegendAnchor;
import com.orsoncharts.plot.CategoryPlot3D;
import com.orsoncharts.renderer.category.CategoryRenderer3D;
import com.orsoncharts.util.Orientation;
import com.orsonpdf.PDFHints;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.RenderingHints;

public class BarChart3D2
{
  public static Chart3D createChart(CategoryDataset3D paramCategoryDataset3D)
  {
    Chart3D localChart3D = Chart3DFactory.createBarChart("Average Maximum Temperature", "http://www.worldclimateguide.co.uk/climateguides/", paramCategoryDataset3D, null, null, "Temp °C");
    localChart3D.getRenderingHints().put(PDFHints.KEY_DRAW_STRING_TYPE, PDFHints.VALUE_DRAW_STRING_TYPE_VECTOR);
    localChart3D.setLegendPosition(LegendAnchor.BOTTOM_RIGHT, Orientation.VERTICAL);
    localChart3D.getViewPoint().panLeftRight(-0.05235987755982988D);
    CategoryPlot3D localCategoryPlot3D = (CategoryPlot3D)localChart3D.getPlot();
    StandardCategoryAxis3D localStandardCategoryAxis3D1 = (StandardCategoryAxis3D)localCategoryPlot3D.getColumnAxis();
    NumberAxis3D localNumberAxis3D = (NumberAxis3D)localCategoryPlot3D.getValueAxis();
    StandardCategoryAxis3D localStandardCategoryAxis3D2 = (StandardCategoryAxis3D)localCategoryPlot3D.getRowAxis();
    localCategoryPlot3D.setGridlineStrokeForValues(new BasicStroke(0.0F));
    localStandardCategoryAxis3D1.setLineColor(new Color(0, 0, 0, 0));
    localNumberAxis3D.setLineColor(new Color(0, 0, 0, 0));
    localStandardCategoryAxis3D2.setLineColor(new Color(0, 0, 0, 0));
    localCategoryPlot3D.getRenderer().setColors(Colors.createPastelColors());
    localCategoryPlot3D.setToolTipGenerator(new StandardCategoryItemLabelGenerator("%2$s (%3$s) = %4$s degrees"));
    return localChart3D;
  }
  
  public static CategoryDataset3D createDataset()
  {
    StandardCategoryDataset3D localStandardCategoryDataset3D = new StandardCategoryDataset3D();
    DefaultKeyedValues localDefaultKeyedValues1 = new DefaultKeyedValues();
    localDefaultKeyedValues1.put("Jan", Integer.valueOf(7));
    localDefaultKeyedValues1.put("Feb", Integer.valueOf(7));
    localDefaultKeyedValues1.put("Mar", Integer.valueOf(10));
    localDefaultKeyedValues1.put("Apr", Integer.valueOf(13));
    localDefaultKeyedValues1.put("May", Integer.valueOf(17));
    localDefaultKeyedValues1.put("Jun", Integer.valueOf(20));
    localDefaultKeyedValues1.put("Jul", Integer.valueOf(22));
    localDefaultKeyedValues1.put("Aug", Integer.valueOf(21));
    localDefaultKeyedValues1.put("Sep", Integer.valueOf(19));
    localDefaultKeyedValues1.put("Oct", Integer.valueOf(15));
    localDefaultKeyedValues1.put("Nov", Integer.valueOf(10));
    localDefaultKeyedValues1.put("Dec", Integer.valueOf(8));
    localStandardCategoryDataset3D.addSeriesAsRow("London", localDefaultKeyedValues1);
    DefaultKeyedValues localDefaultKeyedValues2 = new DefaultKeyedValues();
    localDefaultKeyedValues2.put("Jan", Integer.valueOf(3));
    localDefaultKeyedValues2.put("Feb", Integer.valueOf(5));
    localDefaultKeyedValues2.put("Mar", Integer.valueOf(9));
    localDefaultKeyedValues2.put("Apr", Integer.valueOf(14));
    localDefaultKeyedValues2.put("May", Integer.valueOf(18));
    localDefaultKeyedValues2.put("Jun", Integer.valueOf(22));
    localDefaultKeyedValues2.put("Jul", Integer.valueOf(25));
    localDefaultKeyedValues2.put("Aug", Integer.valueOf(24));
    localDefaultKeyedValues2.put("Sep", Integer.valueOf(20));
    localDefaultKeyedValues2.put("Oct", Integer.valueOf(14));
    localDefaultKeyedValues2.put("Nov", Integer.valueOf(8));
    localDefaultKeyedValues2.put("Dec", Integer.valueOf(4));
    localStandardCategoryDataset3D.addSeriesAsRow("Geneva", localDefaultKeyedValues2);
    DefaultKeyedValues localDefaultKeyedValues3 = new DefaultKeyedValues();
    localDefaultKeyedValues3.put("Jan", Integer.valueOf(9));
    localDefaultKeyedValues3.put("Feb", Integer.valueOf(11));
    localDefaultKeyedValues3.put("Mar", Integer.valueOf(13));
    localDefaultKeyedValues3.put("Apr", Integer.valueOf(16));
    localDefaultKeyedValues3.put("May", Integer.valueOf(20));
    localDefaultKeyedValues3.put("Jun", Integer.valueOf(23));
    localDefaultKeyedValues3.put("Jul", Integer.valueOf(26));
    localDefaultKeyedValues3.put("Aug", Integer.valueOf(26));
    localDefaultKeyedValues3.put("Sep", Integer.valueOf(24));
    localDefaultKeyedValues3.put("Oct", Integer.valueOf(19));
    localDefaultKeyedValues3.put("Nov", Integer.valueOf(13));
    localDefaultKeyedValues3.put("Dec", Integer.valueOf(9));
    localStandardCategoryDataset3D.addSeriesAsRow("Bergerac", localDefaultKeyedValues3);
    DefaultKeyedValues localDefaultKeyedValues4 = new DefaultKeyedValues();
    localDefaultKeyedValues4.put("Jan", Integer.valueOf(22));
    localDefaultKeyedValues4.put("Feb", Integer.valueOf(22));
    localDefaultKeyedValues4.put("Mar", Integer.valueOf(20));
    localDefaultKeyedValues4.put("Apr", Integer.valueOf(17));
    localDefaultKeyedValues4.put("May", Integer.valueOf(14));
    localDefaultKeyedValues4.put("Jun", Integer.valueOf(11));
    localDefaultKeyedValues4.put("Jul", Integer.valueOf(11));
    localDefaultKeyedValues4.put("Aug", Integer.valueOf(12));
    localDefaultKeyedValues4.put("Sep", Integer.valueOf(14));
    localDefaultKeyedValues4.put("Oct", Integer.valueOf(17));
    localDefaultKeyedValues4.put("Nov", Integer.valueOf(19));
    localDefaultKeyedValues4.put("Dec", Integer.valueOf(21));
    localStandardCategoryDataset3D.addSeriesAsRow("Christchurch", localDefaultKeyedValues4);
    DefaultKeyedValues localDefaultKeyedValues5 = new DefaultKeyedValues();
    localDefaultKeyedValues5.put("Jan", Integer.valueOf(20));
    localDefaultKeyedValues5.put("Feb", Integer.valueOf(20));
    localDefaultKeyedValues5.put("Mar", Integer.valueOf(19));
    localDefaultKeyedValues5.put("Apr", Integer.valueOf(17));
    localDefaultKeyedValues5.put("May", Integer.valueOf(14));
    localDefaultKeyedValues5.put("Jun", Integer.valueOf(12));
    localDefaultKeyedValues5.put("Jul", Integer.valueOf(11));
    localDefaultKeyedValues5.put("Aug", Integer.valueOf(12));
    localDefaultKeyedValues5.put("Sep", Integer.valueOf(13));
    localDefaultKeyedValues5.put("Oct", Integer.valueOf(15));
    localDefaultKeyedValues5.put("Nov", Integer.valueOf(17));
    localDefaultKeyedValues5.put("Dec", Integer.valueOf(19));
    localStandardCategoryDataset3D.addSeriesAsRow("Wellington", localDefaultKeyedValues5);
    return localStandardCategoryDataset3D;
  }
}


/* Location:           C:\Users\vinchs\Desktop\temp\
 * Qualified Name:     demo.orsoncharts.BarChart3D2
 * JD-Core Version:    0.7.0.1
 */