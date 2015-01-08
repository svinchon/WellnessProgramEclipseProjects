package demo.orsoncharts;

import com.orsoncharts.Chart3D;
import com.orsoncharts.Chart3DFactory;
import com.orsoncharts.data.xyz.XYZDataset;
import com.orsoncharts.data.xyz.XYZSeries;
import com.orsoncharts.data.xyz.XYZSeriesCollection;
import com.orsoncharts.graphics3d.ViewPoint3D;

public class XYZBarChart3D1
{
  public static Chart3D createChart(XYZDataset paramXYZDataset)
  {
    Chart3D localChart3D = Chart3DFactory.createXYZBarChart("XYZBarChart3DDemo1", "Chart created with Orson Charts", paramXYZDataset, "X", "Value", "Z");
    localChart3D.setViewPoint(ViewPoint3D.createAboveRightViewPoint(40.0D));
    return localChart3D;
  }
  
  public static XYZDataset createDataset()
  {
    XYZSeries localXYZSeries1 = new XYZSeries("Series 1");
    localXYZSeries1.add(1.0D, 5.0D, 1.0D);
    XYZSeries localXYZSeries2 = new XYZSeries("Series 2");
    localXYZSeries2.add(2.0D, 8.0D, 2.0D);
    XYZSeries localXYZSeries3 = new XYZSeries("Series 3");
    localXYZSeries3.add(1.0D, 10.0D, 2.0D);
    XYZSeriesCollection localXYZSeriesCollection = new XYZSeriesCollection();
    localXYZSeriesCollection.add(localXYZSeries1);
    localXYZSeriesCollection.add(localXYZSeries2);
    localXYZSeriesCollection.add(localXYZSeries3);
    return localXYZSeriesCollection;
  }
}


/* Location:           C:\Users\vinchs\Desktop\temp\
 * Qualified Name:     demo.orsoncharts.XYZBarChart3D1
 * JD-Core Version:    0.7.0.1
 */