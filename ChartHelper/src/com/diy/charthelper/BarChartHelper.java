package com.diy.charthelper;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ResourceBundle;
import java.util.UUID;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.StackedBarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;

public class BarChartHelper {

	public BarChartHelper()  {
	}
	
	public static void main(String[] args) {
		new BarChartHelper().generateWeeklyReviewBarChart(
			"10", "20", "15", "30"
		);
	}
	
	public String generateWeeklyReviewBarChart(
			String Index1Value1,
			String Index1Value2,
			String Index2Value1,
			String Index2Value2
	) {
		String strReturn = null;
		try {
			DefaultCategoryDataset dcd = new DefaultCategoryDataset();
			dcd.addValue(new Double(Index1Value1), "Val1", "Bar1");
			dcd.addValue(new Double(Index1Value2), "Val2", "Bar1");
			dcd.addValue(new Double(Index2Value1), "Val1", "Bar2");
			dcd.addValue(new Double(Index2Value2), "Val2", "Bar2");
			JFreeChart jfc = createChart(dcd);
			String strDir = ResourceBundle.getBundle("ChartHelper").getString("LocalImageFolder");
			String strFileName = "BarGraphic1_"+generateUniqueIdentifier();
			creatPNGofChart(
					jfc,
					strDir,
					strFileName,
					350,
					100,
					true//transparency
			);
			strDir = ResourceBundle.getBundle("ChartHelper").getString("ImageFolderURL");
			strReturn = strDir + "/" + strFileName + ".png";
			System.out.println("ChartHelper: URL="+strReturn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strReturn;
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
		
		StackedBarRenderer sbr = (StackedBarRenderer)lcp.getRenderer();
		sbr.setBarPainter(new StandardBarPainter());
		sbr.setDrawBarOutline(false);
		sbr.setMaximumBarWidth(.25);
		sbr.setSeriesPaint(0, new Color(141,198,63,230));
		sbr.setSeriesPaint(1, new Color(141,198,63,50));

	    StandardCategoryItemLabelGenerator lscilg;
	    lscilg = new StandardCategoryItemLabelGenerator(//);
	    		"{1}",
	    		NumberFormat.getInstance()
	    );
	    sbr.setItemMargin(0.5D);
	    sbr.setBaseItemLabelGenerator(lscilg);
	    sbr.setBaseItemLabelsVisible(true); 
	    ItemLabelPosition lilp = new ItemLabelPosition(
	    		ItemLabelAnchor.INSIDE9,
	    		TextAnchor.CENTER_LEFT
	    		);
	    sbr.setBaseItemLabelPaint(Color.white);
	    sbr.setBasePositiveItemLabelPosition(lilp);
	    return jfc;
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
		final String writtenFile =  strDirectory + "/" + aFileName + fileExtension;

		try {
			jfc.setBackgroundPaint( new Color(255,255,255,0) );
			if ( aPlotTransparent ) {
				final Plot plot = jfc.getPlot();
				plot.setBackgroundPaint( new Color(255,255,255,0) );
				plot.setBackgroundImageAlpha(0.0f);
			}
			FileOutputStream fos = new FileOutputStream(writtenFile);
			ChartUtilities.writeChartAsPNG(
					fos,
					jfc,
					aWidth,
					aHeight,
					null,
					true, // encodeAlpha
					0
			);
			fos.close();
			System.out.println("Wrote PNG (transparent) file " + writtenFile);

		} catch (IOException ioEx) {

			System.err.println( "Error writing PNG file " + writtenFile + ": " + ioEx.getMessage());

		}
	}

	private static String generateUniqueIdentifier() {
		String strReturn = null;
		UUID ui = UUID.randomUUID();
		strReturn = ui.toString();
		return strReturn;
	}

}
