package demo.orsoncharts;

import com.orsoncharts.Chart3D;
import com.orsoncharts.Chart3DFactory;
import com.orsoncharts.TitleAnchor;
import com.orsoncharts.data.PieDataset3D;
import com.orsoncharts.data.StandardPieDataset3D;
import com.orsoncharts.legend.LegendAnchor;
import com.orsoncharts.util.Orientation;

public class PieChart3D1
{
  public static Chart3D createChart(PieDataset3D paramPieDataset3D)
  {
    Chart3D localChart3D = Chart3DFactory.createPieChart("New Zealand Exports 2012", "http://www.stats.govt.nz/browse_for_stats/snapshots-of-nz/nz-in-profile-2013.aspx", createDataset());
    localChart3D.setTitleAnchor(TitleAnchor.TOP_LEFT);
    localChart3D.setLegendPosition(LegendAnchor.BOTTOM_CENTER, Orientation.HORIZONTAL);
    return localChart3D;
  }
  
  public static PieDataset3D createDataset()
  {
    StandardPieDataset3D localStandardPieDataset3D = new StandardPieDataset3D();
    localStandardPieDataset3D.add("Milk Products", 11625.0D);
    localStandardPieDataset3D.add("Meat", 5114.0D);
    localStandardPieDataset3D.add("Wood/Logs", 3060.0D);
    localStandardPieDataset3D.add("Crude Oil", 2023.0D);
    localStandardPieDataset3D.add("Machinery", 1865.0D);
    localStandardPieDataset3D.add("Fruit", 1587.0D);
    localStandardPieDataset3D.add("Fish", 1367.0D);
    localStandardPieDataset3D.add("Wine", 1177.0D);
    localStandardPieDataset3D.add("Other", 18870.0D);
    return localStandardPieDataset3D;
  }
}


/* Location:           C:\Users\vinchs\Desktop\temp\
 * Qualified Name:     demo.orsoncharts.PieChart3D1
 * JD-Core Version:    0.7.0.1
 */