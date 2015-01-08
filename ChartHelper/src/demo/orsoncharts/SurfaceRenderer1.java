package demo.orsoncharts;

import com.orsoncharts.Chart3D;
import com.orsoncharts.Chart3DFactory;
import com.orsoncharts.Range;
import com.orsoncharts.axis.ValueAxis3D;
import com.orsoncharts.data.function.Function3D;
import com.orsoncharts.graphics3d.Dimension3D;
import com.orsoncharts.plot.XYZPlot;
import com.orsoncharts.renderer.GradientColorScale;
import com.orsoncharts.renderer.xyz.SurfaceRenderer;
import java.awt.Color;

public class SurfaceRenderer1
{
  public static Chart3D createChart()
  {
    Function3D local1 = new Function3D()
    {
      public double getValue(double paramAnonymousDouble1, double paramAnonymousDouble2)
      {
        return Math.cos(paramAnonymousDouble1) * Math.sin(paramAnonymousDouble2);
      }
    };
    Chart3D localChart3D = Chart3DFactory.createSurfaceChart("SurfaceRendererDemo1", "y = cos(x) * sin(z)", local1, "X", "Y", "Z");
    XYZPlot localXYZPlot = (XYZPlot)localChart3D.getPlot();
    localXYZPlot.setDimensions(new Dimension3D(10.0D, 5.0D, 10.0D));
    ValueAxis3D localValueAxis3D1 = localXYZPlot.getXAxis();
    localValueAxis3D1.setRange(-3.141592653589793D, 3.141592653589793D);
    ValueAxis3D localValueAxis3D2 = localXYZPlot.getZAxis();
    localValueAxis3D2.setRange(-3.141592653589793D, 3.141592653589793D);
    SurfaceRenderer localSurfaceRenderer = (SurfaceRenderer)localXYZPlot.getRenderer();
    localSurfaceRenderer.setColorScale(new GradientColorScale(new Range(-1.0D, 1.0D), Color.RED, Color.YELLOW));
    return localChart3D;
  }
}


/* Location:           C:\Users\vinchs\Desktop\temp\
 * Qualified Name:     demo.orsoncharts.SurfaceRenderer1
 * JD-Core Version:    0.7.0.1
 */