package demo;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.renderer.category.CategoryItemRenderer;

class test {
	
	public void main(String[] args) {
		
	}
	
	public void writePngTransparentBasedOnChart(
			final JFreeChart aChart,
			final String aFileName,
			final int aWidth,
			final int aHeight,
			final boolean aPlotTransparent )
	{
		final String fileExtension = ".png";
		String destinationDirectory ="c:\\";
		final String writtenFile =  destinationDirectory
				+ aFileName
				+ fileExtension;
		try
		{
			aChart.setBackgroundPaint( new Color(255,255,255,0) );
			if ( aPlotTransparent )
			{
				final Plot plot = aChart.getPlot();
				plot.setBackgroundPaint( new Color(255,255,255,0) );
				plot.setBackgroundImageAlpha(0.0f);
			}

			final CategoryItemRenderer renderer = aChart.getCategoryPlot().getRenderer();
			renderer.setSeriesPaint(0, Color.blue.brighter());
			renderer.setSeriesVisible(0, true); // default
			renderer.setSeriesVisibleInLegend(0, true);  // default

			ChartUtilities.writeChartAsPNG( new FileOutputStream(writtenFile),
					aChart,
					aWidth, aHeight,
					null,
					true,    // encodeAlpha
					0 );
			System.out.println("Wrote PNG (transparent) file " + writtenFile);
		}
		catch (IOException ioEx)
		{
			System.err.println(  "Error writing PNG file " + writtenFile + ": "
					+ ioEx.getMessage() );
		}
	}
}