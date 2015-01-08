package demo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.geom.Ellipse2D;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class ChartHelper {

	public ChartHelper() {
		//localJPanel.setPreferredSize(new Dimension(500, 270));
	}

	private static CategoryDataset createDataset() {
		DefaultCategoryDataset localDefaultCategoryDataset = new DefaultCategoryDataset();
		// addValue(Number value, Comparable rowKey, Comparable columnKey)
		localDefaultCategoryDataset.addValue(2102.0D, "Classes", "JDK 1.0");
		localDefaultCategoryDataset.addValue(504.0D, "Classes", "JDK 1.1");
		localDefaultCategoryDataset.addValue(1520.0D, "Classes", "JDK 1.2");
		localDefaultCategoryDataset.addValue(1842.0D, "Classes", "JDK 1.3");
		localDefaultCategoryDataset.addValue(2991.0D, "Classes", "JDK 1.4");
		localDefaultCategoryDataset.addValue(3500.0D, "Classes", "JDK 1.5");
		return localDefaultCategoryDataset;
	}

	private static JFreeChart createChart(CategoryDataset paramCategoryDataset) {
		JFreeChart localJFreeChart = ChartFactory.createLineChart(
			"TT",						// general title
			null,						// categoryAxisLabel (X)
			"xxxxx",						// valueAxisLabel (Y)
			paramCategoryDataset,		// dataset
			PlotOrientation.VERTICAL,	// orientation
			false,						// legend
			true,						// tooltips
			false						// urls
		);

		//localJFreeChart.addSubtitle(new TextTitle("Number of Classes By Release"));
		//TextTitle localTextTitle = new TextTitle("Source: Java In A Nutshell (5th Edition) by David Flanagan (O'Reilly)");
		//localTextTitle.setFont(new Font("SansSerif", 0, 10));
		//localTextTitle.setPosition(RectangleEdge.BOTTOM);
		//localTextTitle.setHorizontalAlignment(HorizontalAlignment.RIGHT);
		//localJFreeChart.addSubtitle(localTextTitle);
		CategoryPlot localCategoryPlot = (CategoryPlot)localJFreeChart.getPlot();
		localCategoryPlot.setRangePannable(true);
		localCategoryPlot.setRangeGridlinesVisible(false);
		/*
		URL localURL = null = LineChartDemo1.class.getClassLoader().getResource("OnBridge11small.png");
		if (localURL != null) {
			ImageIcon localObject = new ImageIcon(localURL);
			localJFreeChart.setBackgroundImage(((ImageIcon)localObject).getImage());
			localCategoryPlot.setBackgroundPaint(null);
		}
		 */
		localCategoryPlot.setBackgroundPaint(null);
		NumberAxis numberAxis = (NumberAxis)localCategoryPlot.getRangeAxis();
		numberAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		ChartUtilities.applyCurrentTheme(localJFreeChart);
		LineAndShapeRenderer lasr = (LineAndShapeRenderer)localCategoryPlot.getRenderer();
		
		lasr.setBaseShapesVisible(true); // draw shape for points
		//lasr.setDrawSeriesLineAsPath(true);
		//lasr.setSeriesPaint(0, null);
		//lasr.setBaseShapesFilled(true);
		lasr.setSeriesPaint(0, new Color(0, 0, 0)); // line color
		lasr.setDrawOutlines(true); // draw shape border
		lasr.setUseFillPaint(true); // draw fill of shape
		lasr.setBaseFillPaint(Color.white); // fill color
		lasr.setSeriesStroke(
				0, // index
				new BasicStroke(3.0F)// line thickness
		);
		lasr.setSeriesOutlineStroke(
				0,
				new BasicStroke(2.0F) // shape border thickness 
		);
		lasr.setSeriesShape(
				0,
				new Ellipse2D.Double( // draw shape as ellipse
						-5.0D, // x offset
						-5.0D, // y offset
						10.0D, // x size
						10.0D // y size
				));

		return localJFreeChart;
	}

	public static void main(String[] args) {
		//ChartHelper localLineChartDemo1 = new ChartHelper();
		JFreeChart localJFreeChart = createChart(createDataset());
		creatPNGofChart(localJFreeChart, "c://Temp//", "toto", 500, 270, true);
	}

	public static void creatPNGofChart(
			final JFreeChart aChart,
			final String strDirectory,
			final String aFileName,
			final int aWidth,
			final int aHeight,
			final boolean aPlotTransparent
	) {
		final String fileExtension = ".png";
		final String writtenFile =  strDirectory + aFileName + fileExtension;
		try {
			aChart.setBackgroundPaint( new Color(255,255,255,0) );
			if ( aPlotTransparent ) {
				final Plot plot = aChart.getPlot();
				plot.setBackgroundPaint( new Color(255,255,255,0) );
				plot.setBackgroundImageAlpha(0.0f);
			}
			final CategoryItemRenderer renderer = aChart.getCategoryPlot().getRenderer();
			renderer.setSeriesPaint(0, Color.blue.brighter());
			renderer.setSeriesVisible(0, true);
			renderer.setSeriesVisibleInLegend(0, true);
			ChartUtilities.writeChartAsPNG(
				new FileOutputStream(writtenFile),
				aChart,
				aWidth, aHeight,
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