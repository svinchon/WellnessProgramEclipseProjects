package demo.orsoncharts;

import com.orsoncharts.Chart3D;
import com.orsoncharts.Chart3DFactory;
import com.orsoncharts.Colors;
import com.orsoncharts.TitleAnchor;
import com.orsoncharts.data.PieDataset3D;
import com.orsoncharts.data.StandardPieDataset3D;
import com.orsoncharts.label.StandardPieLabelGenerator;
import com.orsoncharts.plot.PiePlot3D;

public class PieChart3D2
{
  public static PieDataset3D createDataset()
  {
    StandardPieDataset3D localStandardPieDataset3D = new StandardPieDataset3D();
    localStandardPieDataset3D.add("United States", Math.random() * 30.0D);
    localStandardPieDataset3D.add("France", Math.random() * 20.0D);
    localStandardPieDataset3D.add("New Zealand", Math.random() * 12.0D);
    localStandardPieDataset3D.add("United Kingdom", Math.random() * 43.0D);
    localStandardPieDataset3D.add("Australia", Math.random() * 43.0D);
    localStandardPieDataset3D.add("Canada", Math.random() * 43.0D);
    return localStandardPieDataset3D;
  }
  
  public static Chart3D createChart(PieDataset3D paramPieDataset3D)
  {
    Chart3D localChart3D = Chart3DFactory.createPieChart("Orson Charts 3D", "For more info see: http://www.object-refinery.com/orsoncharts/", createDataset());
    localChart3D.setTitleAnchor(TitleAnchor.TOP_LEFT);
    PiePlot3D localPiePlot3D = (PiePlot3D)localChart3D.getPlot();
    localPiePlot3D.setLegendLabelGenerator(new StandardPieLabelGenerator("%s (%3$,.0f%%)"));
    localPiePlot3D.setSectionLabelGenerator(new StandardPieLabelGenerator("%s (%3$,.0f%%)"));
    localPiePlot3D.setSectionColors(Colors.createFancyLightColors());
    return localChart3D;
  }
}


/* Location:           C:\Users\vinchs\Desktop\temp\
 * Qualified Name:     demo.orsoncharts.PieChart3D2
 * JD-Core Version:    0.7.0.1
 */