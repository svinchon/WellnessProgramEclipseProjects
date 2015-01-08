package demo;

import com.orsonpdf.PDFDocument;
import com.orsonpdf.PDFGraphics2D;
import com.orsonpdf.Page;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.jfree.chart.JFreeChart;

public class PDFChartTransferable
  implements Transferable
{
  final DataFlavor pdfFlavor = new DataFlavor("application/pdf", "PDF");
  private JFreeChart chart;
  private int width;
  private int height;
  
  public PDFChartTransferable(JFreeChart paramJFreeChart, int paramInt1, int paramInt2)
  {
    this(paramJFreeChart, paramInt1, paramInt2, true);
  }
  
  public PDFChartTransferable(JFreeChart paramJFreeChart, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    try
    {
      this.chart = ((JFreeChart)paramJFreeChart.clone());
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      this.chart = paramJFreeChart;
    }
    this.width = paramInt1;
    this.height = paramInt2;
  }
  
  public DataFlavor[] getTransferDataFlavors()
  {
    return new DataFlavor[] { this.pdfFlavor };
  }
  
  public boolean isDataFlavorSupported(DataFlavor paramDataFlavor)
  {
    return this.pdfFlavor.equals(paramDataFlavor);
  }
  
  public Object getTransferData(DataFlavor paramDataFlavor)
    throws UnsupportedFlavorException, IOException
  {
    if (this.pdfFlavor.equals(paramDataFlavor))
    {
      PDFDocument localPDFDocument = new PDFDocument();
      Rectangle2D.Double localDouble = new Rectangle2D.Double(0.0D, 0.0D, this.width, this.height);
      Page localPage = localPDFDocument.createPage(localDouble);
      PDFGraphics2D localPDFGraphics2D = localPage.getGraphics2D();
      this.chart.draw(localPDFGraphics2D, localDouble);
      return new ByteArrayInputStream(localPDFDocument.getPDFBytes());
    }
    throw new UnsupportedFlavorException(paramDataFlavor);
  }
}


/* Location:           C:\Users\vinchs\Desktop\temp\
 * Qualified Name:     demo.PDFChartTransferable
 * JD-Core Version:    0.7.0.1
 */