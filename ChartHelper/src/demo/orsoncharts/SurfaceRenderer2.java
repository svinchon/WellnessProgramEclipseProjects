package demo.orsoncharts;

import com.orsoncharts.Chart3D;
import com.orsoncharts.Chart3DFactory;
import com.orsoncharts.Range;
import com.orsoncharts.axis.ValueAxis3D;
import com.orsoncharts.data.function.Function3D;
import com.orsoncharts.graphics3d.Dimension3D;
import com.orsoncharts.legend.LegendAnchor;
import com.orsoncharts.plot.XYZPlot;
import com.orsoncharts.renderer.RainbowScale;
import com.orsoncharts.renderer.xyz.SurfaceRenderer;
import com.orsoncharts.util.Orientation;

public class SurfaceRenderer2
{
  public static Chart3D createChart()
  {
    Function3D local1 = new Function3D()
    {
      public double getValue(double paramAnonymousDouble1, double paramAnonymousDouble2)
      {
        return Math.sin(paramAnonymousDouble1 * paramAnonymousDouble1 + paramAnonymousDouble2 * paramAnonymousDouble2);
      }
    };
    Chart3D localChart3D = Chart3DFactory.createSurfaceChart("SurfaceRendererDemo2", "y = sin(x^2 + z^2)", local1, "X", "Y", "Z");
    XYZPlot localXYZPlot = (XYZPlot)localChart3D.getPlot();
    localXYZPlot.setDimensions(new Dimension3D(10.0D, 5.0D, 10.0D));
    ValueAxis3D localValueAxis3D1 = localXYZPlot.getXAxis();
    localValueAxis3D1.setRange(-2.0D, 2.0D);
    ValueAxis3D localValueAxis3D2 = localXYZPlot.getZAxis();
    localValueAxis3D2.setRange(-2.0D, 2.0D);
    SurfaceRenderer localSurfaceRenderer = (SurfaceRenderer)localXYZPlot.getRenderer();
    localSurfaceRenderer.setColorScale(new RainbowScale(new Range(-1.0D, 1.0D)));
    localSurfaceRenderer.setDrawFaceOutlines(false);
    localChart3D.setLegendPosition(LegendAnchor.BOTTOM_RIGHT, Orientation.VERTICAL);
    return localChart3D;
  }
}


/* Location:           C:\Users\vinchs\Desktop\temp\
 * Qualified Name:     demo.orsoncharts.SurfaceRenderer2
 * JD-Core Version:    0.7.0.1
 */