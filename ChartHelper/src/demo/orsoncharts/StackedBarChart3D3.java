package demo.orsoncharts;

import com.orsoncharts.Chart3D;
import com.orsoncharts.Chart3DFactory;
import com.orsoncharts.Colors;
import com.orsoncharts.axis.LabelOrientation;
import com.orsoncharts.axis.StandardCategoryAxis3D;
import com.orsoncharts.data.category.CategoryDataset3D;
import com.orsoncharts.data.category.StandardCategoryDataset3D;
import com.orsoncharts.label.StandardCategoryItemLabelGenerator;
import com.orsoncharts.label.StandardCategoryLabelGenerator;
import com.orsoncharts.plot.CategoryPlot3D;
import com.orsoncharts.renderer.category.StackedBarRenderer3D;
import com.orsoncharts.table.StandardRectanglePainter;
import com.orsoncharts.util.Fit2D;
import java.awt.Color;
import java.net.URL;
import javax.swing.ImageIcon;

public class StackedBarChart3D3
{
  public static Chart3D createChart(CategoryDataset3D paramCategoryDataset3D)
  {
    Chart3D localChart3D = Chart3DFactory.createStackedBarChart("The Sinking of the Titanic", "Survival data for 2,201 passengers", paramCategoryDataset3D, null, "Class", "Passengers");
    URL localURL = StackedBarChart3D3.class.getResource("iStock_000003105870Small.jpg");
    ImageIcon localImageIcon = new ImageIcon(localURL);
    StandardRectanglePainter localStandardRectanglePainter = new StandardRectanglePainter(Color.WHITE, localImageIcon.getImage(), Fit2D.SCALE_TO_FIT_TARGET);
    localChart3D.setBackground(localStandardRectanglePainter);
    localChart3D.setChartBoxColor(new Color(255, 255, 255, 155));
    CategoryPlot3D localCategoryPlot3D = (CategoryPlot3D)localChart3D.getPlot();
    localCategoryPlot3D.setLegendLabelGenerator(new StandardCategoryLabelGenerator("%s (%3$,.0f)"));
    localCategoryPlot3D.setToolTipGenerator(new StandardCategoryItemLabelGenerator("%s, %s, %s = %4$.0f"));
    StandardCategoryAxis3D localStandardCategoryAxis3D1 = (StandardCategoryAxis3D)localCategoryPlot3D.getRowAxis();
    localStandardCategoryAxis3D1.setTickLabelGenerator(new StandardCategoryLabelGenerator("%s (%3$,.0f)"));
    StandardCategoryAxis3D localStandardCategoryAxis3D2 = (StandardCategoryAxis3D)localCategoryPlot3D.getColumnAxis();
    localStandardCategoryAxis3D2.setTickLabelGenerator(new StandardCategoryLabelGenerator("%s (%3$,.0f)"));
    localStandardCategoryAxis3D2.setTickLabelOrientation(LabelOrientation.PARALLEL);
    localStandardCategoryAxis3D2.setMaxTickLabelLevels(2);
    StackedBarRenderer3D localStackedBarRenderer3D = (StackedBarRenderer3D)localCategoryPlot3D.getRenderer();
    localStackedBarRenderer3D.setColors(Colors.createIceCubeColors());
    return localChart3D;
  }
  
  public static CategoryDataset3D createDataset()
  {
    StandardCategoryDataset3D localStandardCategoryDataset3D = new StandardCategoryDataset3D();
    localStandardCategoryDataset3D.addValue(Integer.valueOf(146), "Survivors", "Women/Children", "1st");
    localStandardCategoryDataset3D.addValue(Integer.valueOf(104), "Survivors", "Women/Children", "2nd");
    localStandardCategoryDataset3D.addValue(Integer.valueOf(103), "Survivors", "Women/Children", "3rd");
    localStandardCategoryDataset3D.addValue(Integer.valueOf(20), "Survivors", "Women/Children", "Crew");
    localStandardCategoryDataset3D.addValue(Integer.valueOf(57), "Survivors", "Men", "1st");
    localStandardCategoryDataset3D.addValue(Integer.valueOf(14), "Survivors", "Men", "2nd");
    localStandardCategoryDataset3D.addValue(Integer.valueOf(75), "Survivors", "Men", "3rd");
    localStandardCategoryDataset3D.addValue(Integer.valueOf(192), "Survivors", "Men", "Crew");
    localStandardCategoryDataset3D.addValue(Integer.valueOf(4), "Victims", "Women/Children", "1st");
    localStandardCategoryDataset3D.addValue(Integer.valueOf(13), "Victims", "Women/Children", "2nd");
    localStandardCategoryDataset3D.addValue(Integer.valueOf(141), "Victims", "Women/Children", "3rd");
    localStandardCategoryDataset3D.addValue(Integer.valueOf(3), "Victims", "Women/Children", "Crew");
    localStandardCategoryDataset3D.addValue(Integer.valueOf(118), "Victims", "Men", "1st");
    localStandardCategoryDataset3D.addValue(Integer.valueOf(154), "Victims", "Men", "2nd");
    localStandardCategoryDataset3D.addValue(Integer.valueOf(387), "Victims", "Men", "3rd");
    localStandardCategoryDataset3D.addValue(Integer.valueOf(670), "Victims", "Men", "Crew");
    return localStandardCategoryDataset3D;
  }
}


/* Location:           C:\Users\vinchs\Desktop\temp\
 * Qualified Name:     demo.orsoncharts.StackedBarChart3D3
 * JD-Core Version:    0.7.0.1
 */