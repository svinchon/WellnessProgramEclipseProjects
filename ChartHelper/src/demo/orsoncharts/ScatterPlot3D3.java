package demo.orsoncharts;

import com.orsoncharts.Chart3D;
import com.orsoncharts.Chart3DFactory;
import com.orsoncharts.Colors;
import com.orsoncharts.data.DataUtils;
import com.orsoncharts.data.JSONUtils;
import com.orsoncharts.data.KeyedValues3D;
import com.orsoncharts.data.xyz.XYZDataset;
import com.orsoncharts.graphics3d.ViewPoint3D;
import com.orsoncharts.legend.LegendAnchor;
import com.orsoncharts.plot.XYZPlot;
import com.orsoncharts.renderer.xyz.ScatterXYZRenderer;
import com.orsoncharts.style.StandardChartStyle;
import com.orsoncharts.table.TextElement;
import com.orsoncharts.util.Orientation;
import java.io.IOException;
import java.io.InputStreamReader;

public class ScatterPlot3D3
{
  public static XYZDataset[] createDatasets()
  {
    XYZDataset[] arrayOfXYZDataset = new XYZDataset[4];
    arrayOfXYZDataset[0] = createDataset("sepal length", "sepal width", "petal length");
    arrayOfXYZDataset[1] = createDataset("sepal length", "sepal width", "petal width");
    arrayOfXYZDataset[2] = createDataset("sepal length", "petal width", "petal length");
    arrayOfXYZDataset[3] = createDataset("sepal width", "petal width", "petal length");
    return arrayOfXYZDataset;
  }
  
  public static Chart3D createChart(String paramString1, XYZDataset paramXYZDataset, String paramString2, String paramString3, String paramString4)
  {
    Chart3D localChart3D = Chart3DFactory.createScatterChart(null, null, paramXYZDataset, paramString2, paramString3, paramString4);
    TextElement localTextElement = new TextElement(paramString1);
    localTextElement.setFont(StandardChartStyle.createDefaultFont(0, 16));
    localChart3D.setTitle(localTextElement);
    localChart3D.setLegendAnchor(LegendAnchor.BOTTOM_LEFT);
    localChart3D.setLegendOrientation(Orientation.VERTICAL);
    XYZPlot localXYZPlot = (XYZPlot)localChart3D.getPlot();
    ScatterXYZRenderer localScatterXYZRenderer = (ScatterXYZRenderer)localXYZPlot.getRenderer();
    localScatterXYZRenderer.setSize(0.15D);
    localScatterXYZRenderer.setColors(Colors.createIntenseColors());
    localChart3D.setViewPoint(ViewPoint3D.createAboveLeftViewPoint(40.0D));
    return localChart3D;
  }
  
  private static XYZDataset createDataset(Comparable<?> paramComparable1, Comparable<?> paramComparable2, Comparable<?> paramComparable3)
  {
    InputStreamReader localInputStreamReader = new InputStreamReader(ScatterPlot3D3.class.getResourceAsStream("iris.txt"));
    KeyedValues3D localKeyedValues3D;
    try
    {
      localKeyedValues3D = JSONUtils.readKeyedValues3D(localInputStreamReader);
    }
    catch (IOException localIOException)
    {
      throw new RuntimeException(localIOException);
    }
    return DataUtils.extractXYZDatasetFromColumns(localKeyedValues3D, paramComparable1, paramComparable2, paramComparable3);
  }
}


/* Location:           C:\Users\vinchs\Desktop\temp\
 * Qualified Name:     demo.orsoncharts.ScatterPlot3D3
 * JD-Core Version:    0.7.0.1
 */