package com.diy.charthelper;

import java.awt.Color;
import java.awt.Font;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ResourceBundle;
import java.util.UUID;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
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

public class GaugeChartHelper {

	public GaugeChartHelper()  {
	}
	
	public static void main(String[] args) {
		new GaugeChartHelper().generateWeeklyReviewGaugeChart(
				//"Steps", "30", "00FF00"
				"test1", "30", "FF0000"
		);
	}
	
	public String generateWeeklyReviewGaugeChart(
			String label,
			String percentageOfGoal,
			String color
	) {
		Log(label + ", " + percentageOfGoal + ", " + color);
		String strReturn = null;
		try {
			String[] labels = label.split(":");
			Log(""+labels.length);
			String label2;
			Double percentageDone = new Double(percentageOfGoal);
			DefaultCategoryDataset dcd = new DefaultCategoryDataset();
			dcd.addValue(
					- percentageDone,
					""+labels[0],
					"Cat1"
					 );
			if (labels.length > 1) { label2 = labels[1]; } else { label2 = " "; }
			Log(labels[0]);
			Log(label2);
			dcd.addValue(
					100 - percentageDone,
					label2,
					"Cat1"
					);
			/*
			dcd.addValue(new Double(percentageOfGoal), "Val1", "Cat2");
			dcd.addValue(100 - new Double(percentageOfGoal), "Val2", "Cat2");
			*/
			JFreeChart jfc = createChart(label, color, dcd);
			String strDir = ResourceBundle.getBundle("ChartHelper").getString("LocalImageFolder");
			String strFileName = "GaugeGraphic1_"+generateUniqueIdentifier();
			creatPNGofChart(
					jfc,
					strDir,
					strFileName,
					300,
					55,//65,
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
		
	private static JFreeChart createChart(String label, String color, CategoryDataset paramCategoryDataset) {
		JFreeChart jfc = ChartFactory.createStackedBarChart(
				null,//label, //"Stacked Bar Chart Demo 6",
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
		//lcp.setOutlineVisible(true);
		lcp.setOutlineVisible(false);
		lcp.setRangeGridlinesVisible(false);
		lcp.setDomainGridlinesVisible(false);
		
		// horizontal axis
		ValueAxis ra = lcp.getRangeAxis();
		ra.setVisible(false);
		// this makes sure the bar start on the left without margin
		ra.setLowerMargin(0);
		// same on the right
		ra.setUpperMargin(0);
		
		// vertical axis
		CategoryAxis da = lcp.getDomainAxis();
		da.setVisible(false);
		// this moves the bar down so labels are visible
		da.setLowerMargin(0.1);
		da.setUpperMargin(0.0);
	    da.setCategoryMargin(0.0D);
	    
		//lcp.clearRangeMarkers();
		StackedBarRenderer sbr = (StackedBarRenderer)lcp.getRenderer();
		sbr.setBarPainter(new StandardBarPainter());
		sbr.setDrawBarOutline(false);
		sbr.setMaximumBarWidth(.20);
		// get color from HEX code
		Color c = Color.decode("0x"+color);
		// solid
		sbr.setSeriesPaint(0, new Color(c.getRed(),c.getGreen(),c.getBlue(),230));
		// transparent
		sbr.setSeriesPaint(1, new Color(c.getRed(),c.getGreen(),c.getBlue(),50));
	    /* labels on top of bar */
	    StandardCategoryItemLabelGenerator lscilg;
	    lscilg = new StandardCategoryItemLabelGenerator(
	    		"{0}",
	    		NumberFormat.getInstance()
	    );
	    //sbr.setItemMargin(2D);
	    sbr.setBaseItemLabelFont(new Font("Arial", 1, 12));
	    sbr.setBaseItemLabelGenerator(lscilg);
	    sbr.setBaseItemLabelsVisible(true); 
	    sbr.setBaseItemLabelPaint(c);
	    ItemLabelPosition lilp1 = new ItemLabelPosition(
	    		ItemLabelAnchor.OUTSIDE1,
	    		TextAnchor.BOTTOM_RIGHT
	    		);
	    sbr.setBasePositiveItemLabelPosition(lilp1);
	    ItemLabelPosition lilp2 = new ItemLabelPosition(
	    		ItemLabelAnchor.OUTSIDE11,
	    		TextAnchor.BOTTOM_LEFT
	    );
	    sbr.setBaseNegativeItemLabelPosition(lilp2);
	    /* chart title */
		/*
		Map<TextAttribute, Object> map = new Hashtable<TextAttribute, Object>();
		//map.put(TextAttribute.BACKGROUND, Color.RED);
		map.put(TextAttribute.FOREGROUND, Color.WHITE);
		TextTitle my_Chart_title=new TextTitle(
				label,
				new Font (map)
		);
		jfc.setTitle(my_Chart_title);
		*/
	    /* label on top of bar */
	    /*
	    CategoryTextAnnotation lcta = new CategoryTextAnnotation(
	   	   		label,
	    		"Cat1",
	    		0.0D
	    );
	    lcta.setFont(new Font("SansSerif", 1, 12));
	    lcta.setTextAnchor(TextAnchor.BOTTOM_LEFT);
	    lcta.setPaint(Color.white);
	    lcta.setPaint(new Color(c.getRed(),c.getGreen(),c.getBlue()));
	    lcta.setCategoryAnchor(CategoryAnchor.START);
	    lcp.addAnnotation(lcta);
	    */
	    /*
	    CategoryTextAnnotation lcta2 = new CategoryTextAnnotation(
	    		label,
	    		"Cat2",
	    		0.0D
	    );
	    lcta2.setFont(new Font("SansSerif", 1, 12));
	    lcta2.setTextAnchor(TextAnchor.BOTTOM_LEFT);
	    lcta2.setPaint(Color.white);
	    lcta2.setPaint(new Color(c.getRed(),c.getGreen(),c.getBlue()));
	    lcta2.setCategoryAnchor(CategoryAnchor.START);
	    lcp.addAnnotation(lcta2);
	    */
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
	
	static void Log(String msg) {
		System.out.println("GaugeChartHelper: "+msg);
	}

}
