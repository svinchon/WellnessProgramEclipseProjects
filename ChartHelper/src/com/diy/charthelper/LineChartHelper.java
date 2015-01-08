package com.diy.charthelper;

import java.awt.BasicStroke;
import java.awt.Color;
//import java.awt.Font;
import java.awt.geom.Ellipse2D;
import java.io.FileOutputStream;
import java.io.IOException;
//import java.net.URL;
//import java.awt.Dimension;

//import javax.swing.ImageIcon;
//import javax.swing.JPanel;

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
		//localJPanel.setPreferredSize(new Dimension(500, 270));
	}

	private static CategoryDataset createDataset() {

		DefaultCategoryDataset dcd = new DefaultCategoryDataset();

		// addValue(Number value, Comparable rowKey, Comparable columnKey)
		dcd.addValue(2102.0D, "Classes", "JDK 1.0");
		dcd.addValue(504.0D, "Classes", "JDK 1.1");
		dcd.addValue(1520.0D, "Classes", "JDK 1.2");
		dcd.addValue(1842.0D, "Classes", "JDK 1.3");
		dcd.addValue(2991.0D, "Classes", "JDK 1.4");
		dcd.addValue(3500.0D, "Classes", "JDK 1.5");

		return dcd;

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

		// x axis
 		NumberAxis na;
		na = (NumberAxis)cp.getRangeAxis();
		//na.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		na.setVisible(false); // hide
		na.setRange(275, 3750); // set min an max

		// y axis
		CategoryAxis ca;
		ca = (CategoryAxis)cp.getDomainAxis();
		ca.setVisible(false); // hide

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

	public static void main(String[] args) {

		CategoryDataset cs = createDataset();
		JFreeChart jfc = createChart(cs);
		creatPNGofChart(
				jfc,
				"D:/xData/Business/Projects/2014/Q3/2014q3-09.WellnessProgram/xDW/Graphics/",
				"Graphic1",
				390,
				200,
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

//localJFreeChart.addSubtitle(new TextTitle("Number of Classes By Release"));
//TextTitle localTextTitle = new TextTitle("Source: Java In A Nutshell (5th Edition) by David Flanagan (O'Reilly)");
//localTextTitle.setFont(new Font("SansSerif", 0, 10));
//localTextTitle.setPosition(RectangleEdge.BOTTOM);
//localTextTitle.setHorizontalAlignment(HorizontalAlignment.RIGHT);
//localJFreeChart.addSubtitle(localTextTitle);
/*
URL localURL = null = LineChartDemo1.class.getClassLoader().getResource("OnBridge11small.png");
if (localURL != null) {
	ImageIcon localObject = new ImageIcon(localURL);
	localJFreeChart.setBackgroundImage(((ImageIcon)localObject).getImage());
	localCategoryPlot.setBackgroundPaint(null);
}
//final CategoryItemRenderer renderer = aChart.getCategoryPlot().getRenderer();
//renderer.setSeriesPaint(0, Color.blue.brighter());
//renderer.setSeriesPaint(0, Color.green.brighter());
//renderer.setSeriesVisible(0, true);
//renderer.setSeriesVisibleInLegend(0, true);
*/