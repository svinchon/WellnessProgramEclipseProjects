package com.diy.charthelper;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
//import java.awt.Font;
import java.awt.geom.Ellipse2D;
import java.io.FileOutputStream;
import java.io.IOException;
//import java.net.URL;
//import java.awt.Dimension;

//import javax.swing.ImageIcon;
//import javax.swing.JPanel;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.UUID;

import org.jfree.chart.ChartFactory;
//import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
//import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
//import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class LineChartHelper {

	public LineChartHelper() {
	}

	public static void main(String[] args) {
		new LineChartHelper().generateWeeklyReviewLineChart(
				new String[] {"10", "20", "15", "30", "50"},
				"2015-01-08"
		);
	}

	public String generateWeeklyReviewLineChart(String[] IndexValues, String strLastDay) {
		String strReturn = null;
		try {
			DefaultCategoryDataset dcd = new DefaultCategoryDataset();
			SimpleDateFormat newDateFormat = new SimpleDateFormat("yyyy-MM-DD", Locale.US);
			Date dLastDay = newDateFormat.parse(strLastDay);
			Calendar cal = Calendar.getInstance();
			cal.setTime(dLastDay);
			cal.add(Calendar.DATE, -7);
			//Date dFirstDate = cal.getTime();
			newDateFormat.applyPattern("EEEE d MMM yyyy");
			//String MyDate = newDateFormat.format(dFirstDate);
			//System.out.println(MyDate);
			for (int i=0;i<IndexValues.length;i++) {
				Date dItemDate = cal.getTime();
				String strItemDate = newDateFormat.format(dItemDate);
				//System.out.println(strItemDate);
				dcd.addValue(new Double(IndexValues[i]), "Index", strItemDate.substring(0,2));//.toUpperCase());
				cal.add(Calendar.DATE, 1);
			}
			JFreeChart jfc = createChart(dcd);
			String strDir = ResourceBundle.getBundle("ChartHelper").getString("LocalImageFolder");
			String strFileName = "LineGraphic1_"+generateUniqueIdentifier();
			creatPNGofChart(
					jfc,
					strDir,
					strFileName,
					350,
					200,
					true
			);
			strDir = ResourceBundle.getBundle("ChartHelper").getString("ImageFolderURL");
			strReturn = strDir + "/" + strFileName + ".png";
			System.out.println("ChartHelper: URL="+strReturn);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return strReturn;
	}

	private static JFreeChart createChart(CategoryDataset paramCategoryDataset) {
		JFreeChart jfc = ChartFactory.createLineChart(
			null,						// general title
			null,						// categoryAxisLabel (X)
			null,						// valueAxisLabel (Y)
			paramCategoryDataset,		// dataset
			PlotOrientation.VERTICAL,	// orientation
			false,						// legend
			true,						// tooltips
			false						// urls
		);

		CategoryPlot cp;
		cp = (CategoryPlot)jfc.getPlot();
		cp.setRangePannable(true);
		//cp.setBackgroundPaint(null);
		cp.setOutlineVisible(false); // show hide border
		cp.setRangeGridlinesVisible(true); // show / hide horizontal grid
		cp.setRangeGridlineStroke(new BasicStroke(0.5F)); // horizontal grid lines width
		cp.setDomainGridlinesVisible(true); // show / hide vertical grid lines
		cp.setDomainGridlineStroke(new BasicStroke(0.5F)); // vertical grid lines width

		//ChartUtilities.applyCurrentTheme(jfc);

		// y axis
 		NumberAxis na;
		na = (NumberAxis)cp.getRangeAxis();
		//na.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		na.setVisible(false); // hide
		na.setAxisLinePaint(Color.white);
		//na.setRange(275, 3750); // set min an max

		// x axis
		CategoryAxis ca;
		ca = (CategoryAxis)cp.getDomainAxis();
		ca.setVisible(true); // hide
		ca.setAxisLinePaint(Color.white);
		ca.setTickLabelPaint(Color.white);
		ca.setTickLabelFont(new Font("Arial", Font.PLAIN, 14));

		LineAndShapeRenderer lasr;
		lasr = (LineAndShapeRenderer)cp.getRenderer();
		lasr.setBaseShapesVisible(true); // draw shape for points
		//lasr.setSeriesPaint(0, new Color(0, 250, 0)); // line color
		lasr.setSeriesPaint(0, Color.white); // line color
		lasr.setDrawOutlines(true); // draw shape border
		lasr.setUseFillPaint(true); // draw fill of shape
		lasr.setBaseFillPaint(Color.white); // fill color
		lasr.setSeriesStroke(0, new BasicStroke(3.0F)); // line thickness
		lasr.setSeriesOutlineStroke(0, new BasicStroke(2.0F)); // shape border thickness 
		lasr.setSeriesShape(0, new Ellipse2D.Double(/* x offset */-5.0D, /* y offset */-5.0D, /* x size */10.0D,/* y size */ 10.0D)); // draw shape as ellipse

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