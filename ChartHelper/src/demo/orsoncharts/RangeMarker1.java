package demo.orsoncharts;

import com.orsoncharts.Chart3D;
import com.orsoncharts.Chart3DFactory;
import com.orsoncharts.axis.NumberAxis3D;
import com.orsoncharts.data.xyz.XYZDataset;
import com.orsoncharts.data.xyz.XYZSeries;
import com.orsoncharts.data.xyz.XYZSeriesCollection;
import com.orsoncharts.graphics3d.Dimension3D;
import com.orsoncharts.graphics3d.ViewPoint3D;
import com.orsoncharts.interaction.StandardXYZDataItemSelection;
import com.orsoncharts.label.StandardXYZItemLabelGenerator;
import com.orsoncharts.marker.RangeMarker;
import com.orsoncharts.plot.XYZPlot;
import com.orsoncharts.renderer.xyz.ScatterXYZRenderer;
import com.orsoncharts.style.ChartStyle;
import com.orsoncharts.style.ChartStyler;
import com.orsoncharts.style.ChartStyles;
import com.orsoncharts.util.Anchor2D;
import java.awt.Color;

public class RangeMarker1
{
  public static Chart3D createChart(XYZDataset paramXYZDataset)
  {
    Chart3D localChart3D = Chart3DFactory.createScatterChart("RangeMarkerDemo1", null, paramXYZDataset, "X", "Y", "Z");
    localChart3D.setStyle(ChartStyles.createOrson1Style());
    XYZPlot localXYZPlot = (XYZPlot)localChart3D.getPlot();
    localXYZPlot.setDimensions(new Dimension3D(10.0D, 6.0D, 10.0D));
    ChartStyler localChartStyler = new ChartStyler(localChart3D.getStyle());
    NumberAxis3D localNumberAxis3D1 = (NumberAxis3D)localXYZPlot.getXAxis();
    RangeMarker localRangeMarker1 = new RangeMarker(60.0D, 90.0D, "X: 60 to 90");
    localRangeMarker1.receive(localChartStyler);
    localRangeMarker1.setFillColor(new Color(128, 128, 255, 128));
    localRangeMarker1.setLabelAnchor(Anchor2D.BOTTOM_LEFT);
    localNumberAxis3D1.setMarker("X1", localRangeMarker1);
    NumberAxis3D localNumberAxis3D2 = (NumberAxis3D)localXYZPlot.getYAxis();
    RangeMarker localRangeMarker2 = new RangeMarker(0.002D, 0.006D, "Y: 0.002 to 0.006");
    localRangeMarker2.receive(localChartStyler);
    localRangeMarker2.setFillColor(new Color(128, 255, 128, 128));
    localNumberAxis3D2.setMarker("Y1", localRangeMarker2);
    NumberAxis3D localNumberAxis3D3 = (NumberAxis3D)localXYZPlot.getZAxis();
    RangeMarker localRangeMarker3 = new RangeMarker(20.0D, 60.0D, "Z: 20 to 60");
    localRangeMarker3.setLabelAnchor(Anchor2D.TOP_LEFT);
    localRangeMarker3.receive(localChartStyler);
    localRangeMarker3.setFillColor(new Color(255, 128, 128, 128));
    localNumberAxis3D3.setMarker("Z1", localRangeMarker3);
    ScatterXYZRenderer localScatterXYZRenderer = (ScatterXYZRenderer)localXYZPlot.getRenderer();
    localScatterXYZRenderer.setSize(0.15D);
    HighlightXYZColorSource localHighlightXYZColorSource = new HighlightXYZColorSource(localXYZPlot.getDataset(), Color.RED, localRangeMarker1.getRange(), localRangeMarker2.getRange(), localRangeMarker3.getRange(), localChart3D.getStyle().getStandardColors());
    localScatterXYZRenderer.setColorSource(localHighlightXYZColorSource);
    StandardXYZItemLabelGenerator localStandardXYZItemLabelGenerator = new StandardXYZItemLabelGenerator();
    StandardXYZDataItemSelection localStandardXYZDataItemSelection = new StandardXYZDataItemSelection();
    localStandardXYZItemLabelGenerator.setItemSelection(localStandardXYZDataItemSelection);
    localScatterXYZRenderer.setItemLabelGenerator(localStandardXYZItemLabelGenerator);
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
 * Qualified Name:     demo.orsoncharts.RangeMarker1
 * JD-Core Version:    0.7.0.1
 */