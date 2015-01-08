package demo.orsoncharts;

import com.orsoncharts.Colors;
import com.orsoncharts.renderer.category.StandardCategoryColorSource;
import com.orsoncharts.util.ArgChecks;
import java.awt.Color;

public class HighlightCategoryColorSource
  extends StandardCategoryColorSource
{
  private int highlightRowIndex;
  private int highlightColumnIndex;
  private Color highlightColor;
  
  public HighlightCategoryColorSource()
  {
    this(-1, -1, Color.RED, Colors.getDefaultColors());
  }
  
  public HighlightCategoryColorSource(int paramInt1, int paramInt2, Color paramColor, Color... paramVarArgs)
  {
    super(paramVarArgs);
    this.highlightRowIndex = paramInt1;
    this.highlightColumnIndex = paramInt2;
    this.highlightColor = paramColor;
  }
  
  public int getHighlightRowIndex()
  {
    return this.highlightRowIndex;
  }
  
  public void setHighlightRowIndex(int paramInt)
  {
    this.highlightRowIndex = paramInt;
  }
  
  public int getHighlightColumnIndex()
  {
    return this.highlightColumnIndex;
  }
  
  public void setHighlightColumnIndex(int paramInt)
  {
    this.highlightColumnIndex = paramInt;
  }
  
  public Color getHighlightColor()
  {
    return this.highlightColor;
  }
  
  public void setHighlightColor(Color paramColor)
  {
    ArgChecks.nullNotPermitted(paramColor, "color");
    this.highlightColor = paramColor;
  }
  
  public Color getColor(int paramInt1, int paramInt2, int paramInt3)
  {
    Color localColor = super.getColor(paramInt1, paramInt2, paramInt3);
    if ((paramInt2 == this.highlightRowIndex) && (paramInt3 == this.highlightColumnIndex)) {
      localColor = this.highlightColor;
    }
    return localColor;
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == this) {
      return true;
    }
    if (!(paramObject instanceof HighlightCategoryColorSource)) {
      return false;
    }
    HighlightCategoryColorSource localHighlightCategoryColorSource = (HighlightCategoryColorSource)paramObject;
    if (this.highlightColumnIndex != localHighlightCategoryColorSource.highlightColumnIndex) {
      return false;
    }
    if (this.highlightRowIndex != localHighlightCategoryColorSource.highlightRowIndex) {
      return false;
    }
    if (!this.highlightColor.equals(localHighlightCategoryColorSource.highlightColor)) {
      return false;
    }
    return super.equals(paramObject);
  }
}


/* Location:           C:\Users\vinchs\Desktop\temp\
 * Qualified Name:     demo.orsoncharts.HighlightCategoryColorSource
 * JD-Core Version:    0.7.0.1
 */