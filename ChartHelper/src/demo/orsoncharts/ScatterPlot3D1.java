package demo.orsoncharts;

import com.orsoncharts.Chart3D;
import com.orsoncharts.Chart3DFactory;
import com.orsoncharts.Colors;
import com.orsoncharts.data.xyz.XYZDataset;
import com.orsoncharts.data.xyz.XYZSeries;
import com.orsoncharts.data.xyz.XYZSeriesCollection;
import com.orsoncharts.graphics3d.Dimension3D;
import com.orsoncharts.graphics3d.ViewPoint3D;
import com.orsoncharts.label.StandardXYZLabelGenerator;
import com.orsoncharts.plot.XYZPlot;
import com.orsoncharts.renderer.xyz.ScatterXYZRenderer;

public class ScatterPlot3D1
{
  public static Chart3D createChart(XYZDataset paramXYZDataset)
  {
    Chart3D localChart3D = Chart3DFactory.createScatterChart("ScatterPlot3DDemo1", "Chart created with Orson Charts", paramXYZDataset, "X", "Y", "Z");
    XYZPlot localXYZPlot = (XYZPlot)localChart3D.getPlot();
    localXYZPlot.setDimensions(new Dimension3D(10.0D, 4.0D, 4.0D));
    localXYZPlot.setLegendLabelGenerator(new StandardXYZLabelGenerator("%s (%2$,d)"));
    ScatterXYZRenderer localScatterXYZRenderer = (ScatterXYZRenderer)localXYZPlot.getRenderer();
    localScatterXYZRenderer.setSize(0.15D);
    localScatterXYZRenderer.setColors(Colors.createIntenseColors());
    localChart3D.setViewPoint(ViewPoint3D.createAboveLeftViewPoint(40.0D));
    return localChart3D;
  }
  
  public static XYZDataset createDataset()
  {
    XYZSeries localXYZSeries1 = createRandomSeries("S1", 10);
    XYZSeries localXYZSeries2 = createRandomSeries("S2", 50);
    XYZSeries localXYZSeries3 = createRandomSeries("S3", 150);
    XYZSeriesCollection localXYZSeriesCollection = new XYZSeriesCollection();
    localXYZSeriesCollection.add(localXYZSeries1);
    localXYZSeriesCollection.add(localXYZSeries2);
    localXYZSeriesCollection.add(localXYZSeries3);
    return localXYZSeriesCollection;
  }
  
  private static XYZSeries createRandomSeries(String paramString, int paramInt)
  {
    XYZSeries localXYZSeries = new XYZSeries(paramString);
    for (int i = 0; i < paramInt; i++) {
      localXYZSeries.add(Math.random() * 100.0D, Math.random() / 100.0D, Math.random() * 100.0D);
    }
    return localXYZSeries;
  }
}


/* Location:           C:\Users\vinchs\Desktop\temp\
 * Qualified Name:     demo.orsoncharts.ScatterPlot3D1
 * JD-Core Version:    0.7.0.1
 */