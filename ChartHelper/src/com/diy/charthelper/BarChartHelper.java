package com.diy.charthelper;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.StackedBarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class BarChartHelper {

	public BarChartHelper(String paramString)  {
	}

	private static CategoryDataset createDataset()
	{
		DefaultCategoryDataset dcd = new DefaultCategoryDataset();
		//long l = 86400000L;
		dcd.addValue(70	, "Val1", "Bar1");
		dcd.addValue(30	, "Val2", "Bar1");
		//dcd.addValue(20	, "Val3", "Bar1");
		dcd.addValue(40	, "Val1", "Bar2");
		dcd.addValue(60	, "Val2", "Bar2");
		//dcd.addValue(65	, "Val3", "Bar2");
		return dcd;
	}

	private static JFreeChart createChart(CategoryDataset paramCategoryDataset) {
		JFreeChart jfc = ChartFactory.createStackedBarChart(
				null, //"Stacked Bar Chart Demo 6",
				null, //"Category",
				null, //"Value",
				paramCategoryDataset,
				PlotOrientation.HORIZONTAL,
				false,
				false,
				false
				);

		CategoryPlot lcp;
		lcp = (CategoryPlot)jfc.getPlot();
		lcp.setOutlineVisible(false);
		lcp.setRangeGridlinesVisible(false);
		lcp.setDomainGridlinesVisible(false);
		lcp.getRangeAxis().setVisible(false);
		lcp.getRangeAxis().setUpperMargin(0.0);
		lcp.getDomainAxis().setVisible(false);
		
		//lcp.set

		ChartUtilities.applyCurrentTheme(jfc);

		StackedBarRenderer sbr;
		sbr = (StackedBarRenderer)lcp.getRenderer();
		sbr.setBarPainter(new StandardBarPainter());
		sbr.setDrawBarOutline(false);
		sbr.setMaximumBarWidth(.25);
		sbr.setSeriesPaint(0, new Color(141,198,63,230));
		sbr.setSeriesPaint(1, new Color(141,198,63,50));

		return jfc;
	}

	public static void main(String[] paramArrayOfString)
	{
		CategoryDataset cs = createDataset();
		JFreeChart jfc = createChart(cs);
		creatPNGofChart(
				jfc,
				"D:/xData/Business/Projects/2014/Q3/2014q3-09.WellnessProgram/xDW/Graphics/",
				"BarGraphic1",
				390,
				100,
				true
		);
	}
	
	public static void creatPNGofChart(
			final JFreeChart jfc,
			final String strDirectory,
			final String aFileName,
			final int aWidth,
			final int aHeight,
			final boolean aPlotTransparent
			) {

		final String fileExtension = ".png";
		final String writtenFile =  strDirectory + aFileName + fileExtension;

		try {
			jfc.setBackgroundPaint( new Color(255,255,255,0) );
			if ( aPlotTransparent ) {
				final Plot plot = jfc.getPlot();
				plot.setBackgroundPaint( new Color(255,255,255,0) );
				plot.setBackgroundImageAlpha(0.0f);
			}
			ChartUtilities.writeChartAsPNG(
					new FileOutputStream(writtenFile),
					jfc,
					aWidth,
					aHeight,
					null,
					true, // encodeAlpha
					0
			);
			System.out.println("Wrote PNG (transparent) file " + writtenFile);

		} catch (IOException ioEx) {

			System.err.println( "Error writing PNG file " + writtenFile + ": " + ioEx.getMessage());

		}
	}
}
