package demo;

import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import org.jfree.chart.JFreeChart;
import org.jfree.graphics2d.canvas.CanvasGraphics2D;
import org.jfree.graphics2d.canvas.CanvasUtils;
import org.jfree.ui.RectangleInsets;

public class CanvasExportTask
  implements Runnable
{
  JFreeChart chart;
  int width;
  int height;
  File file;
  
  public CanvasExportTask(JFreeChart paramJFreeChart, int paramInt1, int paramInt2, File paramFile)
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
      CanvasGraphics2D localCanvasGraphics2D = new CanvasGraphics2D("canvas1");
      this.chart.draw(localCanvasGraphics2D, new Rectangle(this.width, this.height));
      CanvasUtils.writeToHTML(this.file, "", localCanvasGraphics2D.getCanvasID(), this.width, this.height, localCanvasGraphics2D.getScript() + "\n");
    }
    catch (IOException localIOException)
    {
      throw new RuntimeException(localIOException);
    }
  }
}


/* Location:           C:\Users\vinchs\Desktop\temp\
 * Qualified Name:     demo.CanvasExportTask
 * JD-Core Version:    0.7.0.1
 */