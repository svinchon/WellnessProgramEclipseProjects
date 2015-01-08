package demo;

import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import org.jfree.chart.JFreeChart;
import org.jfree.graphics2d.svg.SVGGraphics2D;
import org.jfree.graphics2d.svg.SVGUtils;
import org.jfree.ui.RectangleInsets;

public class SVGExportTask
  implements Runnable
{
  JFreeChart chart;
  int width;
  int height;
  File file;
  
  public SVGExportTask(JFreeChart paramJFreeChart, int paramInt1, int paramInt2, File paramFile)
  {
    this.chart = paramJFreeChart;
    this.file = paramFile;
    this.width = paramInt1;
    this.height = paramInt2;
    paramJFreeChart.setBorderVisible(true);
    paramJFreeChart.setPadding(new RectangleInsets(2.0D, 2.0D, 2.0D, 2.0D));
  }
  
  public void run()
  {
    try
    {
      SVGGraphics2D localSVGGraphics2D = new SVGGraphics2D(this.width, this.height);
      this.chart.draw(localSVGGraphics2D, new Rectangle(this.width, this.height));
      SVGUtils.writeToHTML(this.file, "title", localSVGGraphics2D.getSVGElement());
    }
    catch (IOException localIOException)
    {
      throw new RuntimeException(localIOException);
    }
  }
}


/* Location:           C:\Users\vinchs\Desktop\temp\
 * Qualified Name:     demo.SVGExportTask
 * JD-Core Version:    0.7.0.1
 */