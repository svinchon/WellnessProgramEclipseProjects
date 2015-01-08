package demo.orsoncharts;

import com.orsoncharts.Chart3D;
import com.orsoncharts.Chart3DFactory;
import com.orsoncharts.axis.LabelOrientation;
import com.orsoncharts.axis.LogAxis3D;
import com.orsoncharts.data.xyz.XYZDataset;
import com.orsoncharts.data.xyz.XYZSeries;
import com.orsoncharts.data.xyz.XYZSeriesCollection;
import com.orsoncharts.graphics3d.Dimension3D;
import com.orsoncharts.graphics3d.ViewPoint3D;
import com.orsoncharts.plot.XYZPlot;
import com.orsoncharts.renderer.xyz.ScatterXYZRenderer;
import com.orsoncharts.style.ChartStyler;
import java.awt.Color;

public class ScatterPlot3D2
{
  public static Chart3D createChart(XYZDataset paramXYZDataset)
  {
    Chart3D localChart3D = Chart3DFactory.createScatterChart("ScatterPlot3DDemo2", null, paramXYZDataset, "X", "Y", "Z");
    XYZPlot localXYZPlot = (XYZPlot)localChart3D.getPlot();
    ScatterXYZRenderer localScatterXYZRenderer = (ScatterXYZRenderer)localXYZPlot.getRenderer();
    localXYZPlot.setDimensions(new Dimension3D(10.0D, 6.0D, 10.0D));
    localScatterXYZRenderer.setSize(0.1D);
    localScatterXYZRenderer.setColors(new Color[] { new Color(255, 128, 128), new Color(128, 255, 128) });
    LogAxis3D localLogAxis3D = new LogAxis3D("Y (log scale)");
    localLogAxis3D.setTickLabelOrientation(LabelOrientation.PERPENDICULAR);
    localLogAxis3D.receive(new ChartStyler(localChart3D.getStyle()));
    localXYZPlot.setYAxis(localLogAxis3D);
    localChart3D.setViewPoint(ViewPoint3D.createAboveLeftViewPoint(40.0D));
    return localChart3D;
  }
  
  public static XYZDataset createDataset()
  {
    XYZSeries localXYZSeries1 = new XYZSeries("S1");
    for (int i = 0; i < 1000; i++) {
      localXYZSeries1.add(Math.random() * 100.0D, Math.pow(10.0D, Math.random() * 5.0D), Math.random() * 100.0D);
    }
    XYZSeries localXYZSeries2 = new XYZSeries("S2");
    for (int j = 0; j < 1000; j++) {
      localXYZSeries2.add(Math.random() * 100.0D, Math.random() * 100000.0D, Math.random() * 100.0D);
    }
    XYZSeriesCollection localXYZSeriesCollection = new XYZSeriesCollection();
    localXYZSeriesCollection.add(localXYZSeries1);
    localXYZSeriesCollection.add(localXYZSeries2);
    return localXYZSeriesCollection;
  }
}


/* Location:           C:\Users\vinchs\Desktop\temp\
 * Qualified Name:     demo.orsoncharts.ScatterPlot3D2
 * JD-Core Version:    0.7.0.1
 */