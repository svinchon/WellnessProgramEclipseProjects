package com.diy.charthelper;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.geom.Ellipse2D;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.ResourceBundle;
import java.util.UUID;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQConstants;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQSequence;
import javax.xml.xquery.XQStaticContext;

import net.sf.saxon.xqj.SaxonXQDataSource;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class XML2ChartHelper {

	public static void main(String[] args) {
		String xml = File2String("C:/GIT/WellnessProgramXPression/xDWTemplates/LineChartData.xml");
		Log(new XML2ChartHelper().generateLineChartFromXML(xml));
	}
	
	public String generateLineChartFromXML(String xml) {
		String r = "";
		// create data out of xml
		DefaultCategoryDataset dcd = new DefaultCategoryDataset();
		int itemCount = new Integer(strRunXQuery(xml, "declare variable $doc external;count($doc/LineChart/Labels/Label)"));
		int dataSetCount = new Integer(strRunXQuery(xml, "declare variable $doc external;count($doc/LineChart/DataSets/DataSet)"));
		String[] labels = strGetValuesFromXML(xml, "/LineChart/Labels/Label");
		for (int ds=1;ds<=dataSetCount;ds++) {
			String[] values = strGetValuesFromXML(xml, "/LineChart/DataSets/DataSet["+ds+"]/Values/Value");
			String dataSetLabel = strGetValueFromXML(xml, "/LineChart/DataSets/DataSet["+ds+"]/Label");
			for (int l=0;l<itemCount;l++) {
				Log(labels[l]+values[l]);	
				double value = new Double(values[l]);
				//String dataSetLabel;
				String itemLabel = labels[l];
				dcd.addValue(value, dataSetLabel, itemLabel);
			}
		}
		// create chart
		JFreeChart jfc = ChartFactory.createLineChart(
				null,						// general title
				null,						// categoryAxisLabel (X)
				null,						// valueAxisLabel (Y)
				dcd,						// dataset
				PlotOrientation.VERTICAL,	// orientation
				false,						// legend
				true,						// tooltips
				false						// urls
				);
		
		// adjust chart
		CategoryPlot cp;
		cp = (CategoryPlot)jfc.getPlot();
		cp.setRangePannable(true);
		//cp.setBackgroundPaint(null);
		cp.setOutlineVisible(false);						// show hide border
		cp.setRangeGridlinesVisible(true);					// show / hide horizontal grid
		cp.setRangeGridlineStroke(new BasicStroke(0.5F));	// horizontal grid lines width
		cp.setDomainGridlinesVisible(true);					// show / hide vertical grid lines
		cp.setDomainGridlineStroke(new BasicStroke(0.5F));	// vertical grid lines width

		// y axis
 		NumberAxis na;
		na = (NumberAxis)cp.getRangeAxis();
		//na.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		na.setVisible(false); // hide
		na.setAxisLinePaint(Color.white);
		//na.setRange(275, 3750); 							// set min an max

		// x axis
		CategoryAxis ca;
		ca = (CategoryAxis)cp.getDomainAxis();
		ca.setVisible(true); // hide
		ca.setAxisLinePaint(Color.white);
		ca.setTickLabelPaint(Color.white);
		ca.setTickLabelFont(new Font("Arial", Font.PLAIN, 14));

		LineAndShapeRenderer lasr;
		lasr = (LineAndShapeRenderer)cp.getRenderer();
		lasr.setBaseShapesVisible(true);						// draw shape for points
		lasr.setDrawOutlines(true);								// draw shape border
		lasr.setUseFillPaint(true);								// draw fill of shape
		lasr.setBaseFillPaint(Color.white);						// fill color
		lasr.setSeriesPaint(0, Color.white); 					// line color
		lasr.setSeriesStroke(0, new BasicStroke(3.0F));			// line thickness
		lasr.setSeriesOutlineStroke(0, new BasicStroke(2.0F));	// shape border thickness 
		lasr.setSeriesShape(
				0,
				new Ellipse2D.Double(
						-5.0D,									/* x offset */
						-5.0D,									/* y offset */
						10.0D,									/* x size */
						10.0D									/* y size */ 
						)
				);												// draw shape as ellipse
		String strDir;
		strDir = ResourceBundle.getBundle("ChartHelper").getString("LocalImageFolder");
		String strFileName = "LineGraphic1_"+generateUniqueIdentifier();
		String localPath = strDir + "/" + strFileName + ".png";
		System.out.println("ChartHelper: local ="+localPath);
		jfc.setBackgroundPaint( new Color(255,255,255,0) );
		boolean transparent = true;
		if (transparent) {
			final Plot plot = jfc.getPlot();
			plot.setBackgroundPaint( new Color(255,255,255,0) );
			plot.setBackgroundImageAlpha(0.0f);
		}
		try {
			FileOutputStream fos = new FileOutputStream(strDir+"/"+strFileName+".png");
			ChartUtilities.writeChartAsPNG(
				fos,
				jfc,
				300,		// width
				200,		// height
				null,
				true,		// encode Alpha
				0
			);
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		strDir = ResourceBundle.getBundle("ChartHelper").getString("ImageFolderURL");
		r = strDir + "/" + strFileName + ".png";
		System.out.println("ChartHelper: URL="+r);
		return r;
	}

	public static String strRunXQuery(String strInputAsString, String strXQUERYAsString) {
		String strResult = "ERROR";
		try {
			//String strInputAsString = "<root><item index='1'>item A</item><item index='2'>item B</item></root>";
			//String strXQUERYAsString = "declare variable $doc external;<xdata>{for $i in $doc/root/item return $i}</xdata>";			
			ByteArrayInputStream bais = new ByteArrayInputStream(strInputAsString.getBytes());
			SaxonXQDataSource ds = new SaxonXQDataSource();
			XQConnection con = ds.getConnection();
			XQStaticContext ctx = con.getStaticContext();
			ctx.setBindingMode(XQConstants.BINDING_MODE_DEFERRED);
			con.setStaticContext(ctx);
			XQPreparedExpression expr = con.prepareExpression(strXQUERYAsString);
			expr.bindDocument(new QName("doc"), bais, null, null);
			XQSequence result = expr.executeQuery();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			result.writeSequence(baos, null);
			strResult = baos.toString("UTF-8");
			result.close();
			expr.close();
			con.close();
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println("Input data: "+strInputAsString.replaceAll("[\n\t]",""));
			System.out.println("Input xquery: "+strXQUERYAsString.replaceAll("[\n\t]",""));
			System.out.println("Error message : "+e.getMessage());
			strResult = "ERROR: "+e.getMessage();
		}
		return strResult;
    }
	
	public static String strGetValueFromXML(String strXML, String strXPath) {
		String result="";
		try {
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(
				new InputSource(new StringReader(strXML))
			);
			XPath xpath = XPathFactory.newInstance().newXPath();
			NodeList nodes = (NodeList) xpath.evaluate(strXPath, doc, XPathConstants.NODESET);
			if (nodes!=null && nodes.getLength()>0) {
				String strValue=nodes.item(0).getTextContent();
				//System.out.println("XML Helper => Extracted "+strXPath+" value: "+strValue);
				return strValue;
			} else {
				result = "ERROR strGetValueFromXML (xpath returned null or empty)";
				System.out.println("Input data: "+strXML.replaceAll("[\n\t]",""));
				System.out.println("Input xpath: "+strXPath.replaceAll("[\n\t]",""));
				System.out.println("Error message : "+"xpath returned null or empty");			}
			nodes = null;
			xpath = null;
			doc = null;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Input data: "+strXML.replaceAll("[\n\t]",""));
			System.out.println("Input xpath: "+strXPath.replaceAll("[\n\t]",""));
			System.out.println("Error message : "+e.getMessage());
			result = "ERROR: "+e.getMessage();
		}
		return result;
	}

	public static String[] strGetValuesFromXML(String strXML, String strXPath) {
		String[] result= null;
		try {
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(
				new InputSource(new StringReader(strXML))
			);
			XPath xpath = XPathFactory.newInstance().newXPath();
			/*NamespaceContext ns = new NamespaceContext() {
				
				@SuppressWarnings("rawtypes")
				@Override
				public Iterator getPrefixes(String namespaceURI) {
					return null;
				}
				
				@Override
				public String getPrefix(String namespaceURI) {
					ResourceBundle rb = ResourceBundle.getBundle("HIPHelper");
					HashMap<String, String> m1 = new HashMap<String, String>();
					HashMap<String, String> m2 = new HashMap<String, String>();
					String namespaces = rb.getString("namespaces");
					String[] namespace = namespaces.split(";");
					for (int i=0;i<namespace.length;i++) {
						String[] ns = namespace[i].split(" ");
						m1.put(ns[0], ns[1]);
						m2.put(ns[1], ns[0]);
					}
					return (String)m2.get(namespaceURI);
				}
				
				@Override
				public String getNamespaceURI(String prefix) {
					ResourceBundle rb = ResourceBundle.getBundle("HIPHelper");
					HashMap<String, String> m1 = new HashMap<String, String>();
					HashMap<String, String> m2 = new HashMap<String, String>();
					String namespaces = rb.getString("namespaces");
					String[] namespace = namespaces.split(";");
					for (int i=0;i<namespace.length;i++) {
						String[] ns = namespace[i].split(" ");
						m1.put(ns[0], ns[1]);
						m2.put(ns[1], ns[0]);
					}
					return (String)m1.get(prefix);				}
			};
			xpath.setNamespaceContext(ns);*/
			NodeList nodes = (NodeList) xpath.evaluate(strXPath, doc, XPathConstants.NODESET);
			if (nodes!=null && nodes.getLength()>0) {
				int iArraySize = nodes.getLength();
				result = new String[iArraySize];
				for (int iNode=0;iNode<iArraySize;iNode++) {
					result[iNode] = nodes.item(iNode).getTextContent();					
				}
			} else {
				result = new String[1];
				result[0] = "ERROR strGetValueFromXML (xpath returned null or empty)";
				System.out.println("Input data: "+strXML.replaceAll("[\n\t]",""));
				System.out.println("Input xpath: "+strXPath.replaceAll("[\n\t]",""));
				System.out.println("Error message : "+"xpath returned null or empty");
			}
			nodes = null;
			xpath = null;
			doc = null;
		} catch (Exception e) {
			System.out.println("Input data: "+strXML.replaceAll("[\n\t]",""));
			System.out.println("Input xpath: "+strXPath.replaceAll("[\n\t]",""));
			System.out.println("Error message : "+e.getMessage());
			result = new String[1];
			result[0] = "ERROR: "+e.getMessage();
			e.printStackTrace();
		}
		return result;
	}

	static String File2String(String path) {
		String strReturn = "";
		try {
			File f = new File(path);
			FileInputStream fis = new FileInputStream(f);
			byte[] bDoc = new byte[fis.available()];
			fis.read(bDoc);
			strReturn = new String(bDoc);
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return strReturn;
	}
	
	static void Log(String msg) {
		System.out.println(msg);
	}
	
	private static String generateUniqueIdentifier() {
		String strReturn = null;
		UUID ui = UUID.randomUUID();
		strReturn = ui.toString();
		return strReturn;
	}
	
}
