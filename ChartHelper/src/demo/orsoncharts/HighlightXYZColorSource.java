package demo.orsoncharts;

import com.orsoncharts.Range;
import com.orsoncharts.data.xyz.XYZDataset;
import com.orsoncharts.renderer.xyz.StandardXYZColorSource;
import java.awt.Color;

public class HighlightXYZColorSource
  extends StandardXYZColorSource
{
  private XYZDataset dataset;
  private Range xRange;
  private Range yRange;
  private Range zRange;
  private Color highlightColor;
  
  public HighlightXYZColorSource(XYZDataset paramXYZDataset, Color paramColor, Range paramRange1, Range paramRange2, Range paramRange3, Color... paramVarArgs)
  {
    super(paramVarArgs);
    this.dataset = paramXYZDataset;
    this.xRange = paramRange1;
    this.yRange = paramRange2;
    this.zRange = paramRange3;
    this.highlightColor = paramColor;
  }
  
  public Color getColor(int paramInt1, int paramInt2)
  {
    double d1 = this.dataset.getX(paramInt1, paramInt2);
    double d2 = this.dataset.getY(paramInt1, paramInt2);
    double d3 = this.dataset.getZ(paramInt1, paramInt2);
    if ((this.xRange.contains(d1)) && (this.yRange.contains(d2)) && (this.zRange.contains(d3))) {
      return this.highlightColor;
    }
    return super.getColor(paramInt1, paramInt2);
  }
}


/* Location:           C:\Users\vinchs\Desktop\temp\
 * Qualified Name:     demo.orsoncharts.HighlightXYZColorSource
 * JD-Core Version:    0.7.0.1
 */