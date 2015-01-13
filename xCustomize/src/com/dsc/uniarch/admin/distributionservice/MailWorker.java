package com.dsc.uniarch.admin.distributionservice;

import com.diy.html.HTMLHelperProxy;
import com.diy.soap.SOAPHelper;
import com.dsc.uniarch.cr.common.CRDBOperator;
import com.dsc.uniarch.cr.error.CRException;
import com.dsc.uniarch.exception.SystemException;
import com.dsc.uniarch.util.ConfigManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message.RecipientType;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.dom4j.io.SAXReader;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import sun.misc.BASE64Encoder;

@SuppressWarnings("unused")
public class MailWorker implements Runnable {

	private static final String T_MailINFO = "T_MailINFO";
	private static final String Mail_ID = "Mail_ID";
	private static final String DEFAULT_ENCODING = "utf-8";
	private static final String PUBLISH_ENCODING = "utf-8";
	private static final String COMPUSET_ENCODING = "utf-8";
	private String DWMailTempFolder;
	private static final int TYPE_COMPUSET = 1;
	private int LIMIT_NUMBER = 10;
	private Session session = null;
	private String smtpServer = null;
	private String returnPath = null;
	private String mailId = null;
	private String from = null;
	private String to = null;
	private String cc = null;
	private String bcc = null;
	private String replyto = null;
	private String customfields = null;
	private String subject = null;
	private String fileName = null;
	private boolean asAttachment = false;
	private boolean asMultipart = false;
	private String message = null;
	private String attachment = null;
	private int publisherType = 1;
	private String jobRunId = null;
	private int count = 0;
	private final String T_MAILINFO = "T_MAILINFO";
	private final String MAIL_ID = "MAIL_ID";
	private ArrayList<?> sucMailInfo = null;
	private String[] mailInfo = null;
	private String jobrunid_attribute = null;
	private ArrayList<String> curSendingFiles = new ArrayList<String>();

	public MailWorker(String[] mailInfo) {
		this.jobrunid_attribute = ConfigManager.getInstance().getValue("JOBRUNID_ATTRIBUTE");
		this.mailInfo = mailInfo;
		this.DWMailTempFolder = new StringBuilder().append(new File(new StringBuilder().append(ConfigManager.getInstance().getValue("xPublishTempDir")).append("DWMail").toString()).getPath()).append(File.separator).toString();
		String limit = ConfigManager.getInstance().getValue("LIMIT_NUMBER");
		if (limit != null)
			this.LIMIT_NUMBER = Integer.parseInt(limit);
	}

	public void run() {
		initMessage();
		if (
				(this.smtpServer != null)
				&&
				(this.smtpServer.trim().length() > 0)
		) {
			if (this.count <= this.LIMIT_NUMBER) {
				getSession();
				sendByMail();
			} else {
				try {
					changeMailStatus(this.mailId, "FAILED");
				} catch (Exception ex) {
					LogManager.logError("when updating mail status", ex);
				}
			}
		}
	}

	private void initMessage() {
		this.mailId = this.mailInfo[0];
		this.from = this.mailInfo[1];
		this.to = this.mailInfo[2];
		this.cc = this.mailInfo[3];
		this.fileName = this.mailInfo[4];
		if ((this.fileName == null) || ("null".equalsIgnoreCase(this.fileName)))
		{
			this.fileName = "";
		}
		this.subject = this.mailInfo[5];
		this.smtpServer = this.mailInfo[6];
		this.message = this.mailInfo[7];
		if (this.mailInfo[9] != null) {
			if (Integer.parseInt(this.mailInfo[9]) == 1)
				this.asAttachment = true;
			else {
				this.asAttachment = false;
			}
		}
		if (this.mailInfo[10] != null) {
			if (Integer.parseInt(this.mailInfo[10]) == 1)
				this.asMultipart = true;
			else {
				this.asMultipart = false;
			}
		}
		this.attachment = this.mailInfo[11];
		this.publisherType = 1;
		if (this.mailInfo[12] != null) {
			this.publisherType = Integer.parseInt(this.mailInfo[12]);
		}
		if (this.mailInfo[13] != null) {
			this.count = (Integer.parseInt(this.mailInfo[13]) + 1);
		}
		this.jobRunId = this.mailInfo[14];
		this.bcc = this.mailInfo[15];
		this.replyto = this.mailInfo[16];
		this.customfields = this.mailInfo[17];
		this.returnPath = this.mailInfo[18];
	}

	private void getSession() {
		Properties props = System.getProperties();
		props.put("mail.smtp.host", this.smtpServer);
		String smtpPort = ConfigManager.getInstance().getValue("SMTPPORT");
		if ((smtpPort != null) && (smtpPort.trim().length() > 0)) {
			props.put("mail.smtp.port", smtpPort.trim());
		}
		if ((this.returnPath != null) && (!this.returnPath.isEmpty())) {
			props.put("mail.smtp.from", this.returnPath.replace('[', '<').replace(']', '>'));
		}
		this.session = Session.getDefaultInstance(props, null);
	}

	private String replace(String str, String pattern, String replace) {
		int s = 0;
		int e = 0;
		StringBuilder result = new StringBuilder();
		while ((e = str.indexOf(pattern, s)) >= 0) {
			result.append(str.substring(s, e));
			result.append(replace);
			s = e + pattern.length();
		}
		result.append(str.substring(s));
		return result.toString();
	}

	private String bindImg2Html(String[] imageFiles, String htmlText) {
		StringBuilder tempBuf = new StringBuilder(htmlText);
		if (imageFiles != null) {
			for (int i = 0; i < imageFiles.length; i++) {
				if ((!imageFiles[i].endsWith("xml")) && (!imageFiles[i].endsWith("htm")))
				{
					String fileLink = null;
					int index = -1;
					index = imageFiles[i].lastIndexOf("\\", imageFiles[i].length());
					if (index < 0) {
						index = imageFiles[i].lastIndexOf("/", imageFiles[i].length());
					}
					if (index >= 0)
						fileLink = imageFiles[i].substring(index + 1);
					else {
						fileLink = imageFiles[i];
					}
					String cid = new StringBuilder().append("cid:pic").append(i).toString();
					index = -1;
					int begin = 0;
					index = htmlText.indexOf(fileLink, begin);
					while (index >= 0) {
						begin = index;
						tempBuf.replace(begin, begin + fileLink.length(), cid);
						htmlText = tempBuf.toString();
						index = htmlText.indexOf(fileLink, begin);
					}
				}
			}
		}
		return htmlText;
	}

	private String[] parseFileList() {
		String[] imageFiles = null;
		try {
			if ((this.fileName == null) || (this.fileName.trim().length() == 0)) {
				return null;
			}
			int _index = this.fileName.lastIndexOf(".", this.fileName.length());
			if (_index < 0) {
				return null;
			}
			if (this.publisherType == 1) {
				String fileList = new StringBuilder().append(this.fileName.substring(0, _index)).append("_filelist.xml").toString();

				File fp = new File(fileList);
				if (fp.exists()) {
					SAXReader xmlReader = new SAXReader();
					org.dom4j.Document applistDoc = xmlReader.read(fp);
					org.dom4j.Element root = applistDoc.getRootElement();
					Iterator<?> itr = root.elements().iterator();
					ArrayList<String> imgFiles = new ArrayList<String>();
					while (itr.hasNext()) {
						org.dom4j.Element childElmnt = (org.dom4j.Element)itr.next();
						if (childElmnt.getName().equalsIgnoreCase("File")) {
							String img = childElmnt.attributeValue("HRef");
							if (img != null)
							{
								imgFiles.add(img);
							}
						}
					}
					int index_ = -1;
					index_ = this.fileName.lastIndexOf("\\", this.fileName.length());
					if (index_ < 0)
						index_ = this.fileName.lastIndexOf("/", this.fileName.length());
					String filePath = this.fileName.substring(0, index_ + 1);

					if ((imgFiles != null) && (imgFiles.size() > 0)) {
						imageFiles = new String[imgFiles.size()];
						for (int i = 0; i < imgFiles.size(); i++)
							imageFiles[i] = new StringBuilder().append(filePath).append((String)imgFiles.get(i)).toString();
					}
				}
			}
			else {
				File file = new File(this.fileName);
				String pathName = file.getPath();
				if (!pathName.startsWith(this.DWMailTempFolder)) {
					getPublishImageFiles(this.curSendingFiles);
					if ((this.curSendingFiles != null) && (this.curSendingFiles.size() > 0))
						imageFiles = (String[])this.curSendingFiles.toArray(new String[this.curSendingFiles.size()]);
				}
				else {
					LogManager.logInfo("For DW mail, ignore image files.");
					imageFiles = new String[0];
				}
			}
		}
		catch (Exception e)
		{
			LogManager.logError("MailHandler parseFileList error:", e);
		}

		return imageFiles;
	}

	private void writeArchive(FileOutputStream out, String fileName, boolean bMainHtml, String[] imageFiles, int index, String strImageName)
	{
		String content = null;
		try
		{
			File fHtml = new File(fileName);
			if (!fHtml.exists()) {
				return;
			}
			long fileSize = fHtml.length();
			byte[] data = new byte[(int)fileSize];
			FileInputStream in = new FileInputStream(fHtml);
			in.read(data, 0, (int)fileSize);
			in.close();
			String tidyOff = ConfigManager.getInstance().getValue("tidyoff");
			String fileNameOutput = null;
			if (fileName.substring(0, 1).equals("/"))
				fileNameOutput = new StringBuilder().append("C:").append(fileName).toString();
			else {
				fileNameOutput = fileName;
			}
			if (fileName.endsWith("xml"))
			{
				content = new StringBuilder().append("\r\n\r\n------=admin@docscience.com\r\nContent-Type: text/xml;\r\ncharset=\"iso-8859-1\"\r\nContent-Transfer-Encoding: 7bit\r\nContent-Location: file:///").append(fileNameOutput).append("\r\n\r\n").toString();
				out.write(content.getBytes(), 0, content.length());
			} else if ((fileName.endsWith("htm")) || (fileName.endsWith("html")))
			{
				String encoding;
				if (this.publisherType == 1)
					encoding = "utf-8";
				else {
					encoding = "utf-8";
				}
				String html = new String(data, encoding);
				if (bMainHtml) {
					if (this.publisherType == 1) {
						if ((tidyOff != null) && (tidyOff.equalsIgnoreCase("true")))
						{
							html = setVariableColorArchiveCSet(html);
							int stylePStart = -1;
							int stylePEnd = -1;
							int i = 0;
							while (((i = html.indexOf("<p class=DSCdls>", i)) != -1) && (i < html.length())) {
								stylePStart = i;
								i = html.indexOf("</p>", i + "<p class=DSCdls>".length());

								stylePEnd = i + "</p>".length();
								html = new StringBuilder().append(html.substring(0, stylePStart)).append(html.substring(stylePEnd)).toString();

								i = i - stylePEnd + stylePStart + "</p>".length();
							}
						}
						else
						{
							html = bindImg2Html(imageFiles, html);
						}
					}
					else html = bindImg2Html(imageFiles, html);
				}
				html = replace(html, "=", "=3D");
				data = html.getBytes("utf-8");
				content = new StringBuilder().append("\r\n\r\n------=admin@docscience.com\r\nContent-Type: text/html;\r\n        charset=\"utf-8\"\r\nContent-Transfer-Encoding: quoted-printable\r\nContent-Location: file:///").append(fileNameOutput).append("\r\n\r\n").toString();
				out.write(content.getBytes(), 0, content.length());
			}
			else
			{
				BASE64Encoder base64 = new BASE64Encoder();
				data = base64.encodeBuffer(data).getBytes();
				String strImageNameOutput = null;
				if (strImageName.substring(0, 1).equals("/"))
					strImageNameOutput = new StringBuilder().append("C:").append(strImageName).toString();
				else {
					strImageNameOutput = strImageName;
				}
				if (this.publisherType == 1) {
					if ((tidyOff != null) && (tidyOff.equalsIgnoreCase("true")))
					{
						content = new StringBuilder().append("\r\n\r\n------=admin@docscience.com\r\nContent-Location: file:///").append(strImageNameOutput).append("\r\nContent-Type: ").append(parseImage(fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()))).append("\r\n").append("Content-Transfer-Encoding: base64 \r\n").append("\r\n\r\n").toString();
					}
					else
					{
						content = new StringBuilder().append("\r\n\r\n------=admin@docscience.com\r\nContent-ID:pic").append(index).append("\r\n").append("Content-Type: ").append(parseImage(fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()))).append("\r\n").append("Content-Transfer-Encoding: base64 \r\n").append("\r\n\r\n").toString();
					}

				}
				else
				{
					content = new StringBuilder().append("\r\n\r\n------=admin@docscience.com\r\nContent-ID:pic").append(index).append("\r\nContent-Type: ").append(parseImage(fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()))).append("\r\n").append("Content-Transfer-Encoding: base64 \r\n").append("\r\n\r\n").toString();
				}

				out.write(content.getBytes(), 0, content.length());
			}

			out.write(data, 0, data.length);
		}
		catch (Exception e) {
			LogManager.logError("WriteArchive error info:", e);
		}
	}

	private String setVariableColorArchiveCSet(String html)
	{
		int indexStart = 0;
		int indexEnd = -1;
		int styleIndex = -1;
		indexEnd = html.indexOf("</head>");
		if (indexEnd < 0)
			return html;
		styleIndex = html.lastIndexOf("span.DLSVAR", indexEnd);
		if (styleIndex < 0)
			return html;
		styleIndex = html.indexOf("}", styleIndex);
		if (styleIndex >= indexEnd) {
			return html;
		}
		String strColor = ConfigManager.getInstance().getValue("VariableColor");
		if ((strColor == null) || (strColor.equals(""))) {
			strColor = "black";
		}
		String strStyleContent = new StringBuilder().append("\r\n        mso-style-parent:\"\";\r\n        color:").append(strColor).append(";\r\n").append("        ").toString();

		html = new StringBuilder().append(html.substring(indexStart, styleIndex)).append(strStyleContent).append(html.substring(styleIndex)).toString();
		return html;
	}

	private String convertHtmlToArchive() {
		try
		{
			String[] imageFiles = parseFileList();
			String mhtFile = this.fileName.substring(0, this.fileName.lastIndexOf(".", this.fileName.length()));
			String emailDoc = ConfigManager.getInstance().getValue("EMAILDOC");
			String tidyOff = ConfigManager.getInstance().getValue("tidyoff");
			if ((emailDoc != null) && (emailDoc.equalsIgnoreCase("true")) && (tidyOff != null) && (tidyOff.equalsIgnoreCase("true")))
			{
				mhtFile = new StringBuilder().append(mhtFile).append(".doc").toString();
			}
			else mhtFile = new StringBuilder().append(mhtFile).append(".mht").toString();
			File fp = new File(mhtFile);
			FileOutputStream out = new FileOutputStream(fp);
			out.flush();
			String content = "MIME-Version: 1.0\r\nContent-Type: multipart/related;\r\n        boundary=\"----=admin@docscience.com\"\r\nX-MimeOLE: Produced By Microsoft MimeOLE V6.00.2600.0000\r\n\r\nThis is a multi-part message in MIME format.";
			out.write(content.getBytes(), 0, content.length());
			writeArchive(out, this.fileName, true, imageFiles, 0, null);
			if (imageFiles != null) {
				for (int i = 0; i < imageFiles.length; i++) {
					writeArchive(out, imageFiles[i], false, null, i, imageFiles[i]);
				}

			}
			content = "\r\n\r\n------=admin@docscience.com--";
			out.write(content.getBytes(), 0, content.length());
			out.close();
			return mhtFile;
		}
		catch (Exception e) {
			LogManager.logError("convertHtmlToArchive error:", e);
		}

		return null;
	}

	private String parseImage(String imageType) {
		if (imageType.equalsIgnoreCase("jpg"))
			return "image/jpeg";
		if (imageType.equalsIgnoreCase("gif"))
			return "image/gif";
		if (imageType.equalsIgnoreCase("png")) {
			return "image/png";
		}
		return new StringBuilder().append("image/").append(imageType).toString();
	}

	// this is here
	private void setHtmlBody(MimeMessage message) throws Exception {
		MimeMultipart mainMultipart = new MimeMultipart();
		MimeMultipart htmlMultipart = new MimeMultipart("related");
		String[] imageFiles = parseFileList();
		String htmlText = getHtmlText();
		htmlText = bindImg2Html(imageFiles, htmlText);
		//LogManager.logInfo("===========> HTML TEXT: " + htmlText);
		LogManager.logInfo("===========> Mailworker BEFORE : " + htmlText);
		if (htmlText.indexOf("<!-- WP-EMBED-IMAGES -->")>=0) {
			LogManager.logInfo("Calling HTML Helper BEGIN");
			HTMLHelperProxy hhp;
			hhp = new HTMLHelperProxy();
			htmlText = hhp.embedImages(new String(htmlText));
			LogManager.logInfo("Calling HTML Helper END");
		} else {
			LogManager.logInfo("NOT CALLING HTML Helper");
		}
		LogManager.logInfo("===========> Mailworker AFTER : " + htmlText);
		BodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent(htmlText, "text/html; charset=\"utf-8\"");
		htmlMultipart.addBodyPart(messageBodyPart);
		if (imageFiles != null) {
			for (int i = 0; i < imageFiles.length; i++)
			{
				if ((!imageFiles[i].endsWith("xml")) && (!imageFiles[i].endsWith("htm")))
				{
					messageBodyPart = new MimeBodyPart();
					DataSource fds = new FileDataSource(imageFiles[i]);
					messageBodyPart.setDataHandler(new DataHandler(fds));
					messageBodyPart.setHeader("Content-ID", new StringBuilder().append("pic").append(i).toString());
					htmlMultipart.addBodyPart(messageBodyPart);
				}
			}
		}
		MimeBodyPart htmlBodyPart = new MimeBodyPart();
		htmlBodyPart.setContent(htmlMultipart);
		mainMultipart.addBodyPart(htmlBodyPart);
		addAttachment(this.attachment, mainMultipart);
		message.setContent(mainMultipart);
	}

	private String getHtmlText() throws IOException {
		FileInputStream in = new FileInputStream(this.fileName);
		byte[] body = new byte[(int)new File(this.fileName).length()];
		try
		{
			in.read(body);
		}
		finally {
			try {
				in.close();
			}
			catch (IOException e)
			{
			}
		}
		String htmlText = new String(body, getCurrentEncoding());
		htmlText = checkSpecialHTMLChar(htmlText);

		String tidyOff = ConfigManager.getInstance().getValue("tidyoff");
		if ((this.publisherType == 1) && (tidyOff != null) && (tidyOff.equals("true")))
		{
			setVariableColorArchiveCSet(htmlText);
		}
		return htmlText;
	}

	private String checkSpecialHTMLChar(String htmlText)
	{
		if (htmlText.indexOf(8217) > -1) {
			htmlText = htmlText.replaceAll("’", "&#x2019;");
		}

		return htmlText;
	}

	private String getCurrentEncoding() {
		if (this.publisherType == 1) {
			return "utf-8";
		}
		return "utf-8";
	}

	private void addAttachment(String fileName, Multipart multipart)
	{
		if ((fileName == null) || (fileName.length() <= 0))
			return;
		if (multipart == null)
			return;
		StringTokenizer tokenizer = new StringTokenizer(fileName, "|");
		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();
			File attachment = new File(token);
			if ((!attachment.exists()) || (!attachment.isFile())) {
				LogManager.logWarn(new StringBuilder().append("Attachment: ").append(token).append(" is not a valid file, it will be ignored.").toString());
			}
			else
			{
				try
				{
					FileDataSource fds = new FileDataSource(attachment);
					MimeBodyPart mimeAttach = new MimeBodyPart();
					mimeAttach.setDataHandler(new DataHandler(fds));
					mimeAttach.setFileName(MimeUtility.encodeText(fds.getName(), "UTF-8", "B"));
					multipart.addBodyPart(mimeAttach);
				} catch (Exception ex) {
					LogManager.logWarn(new StringBuilder().append("Cannot add attachment: ").append(token).append(", it will be ignored.").toString());
				}
			}
		}
	}

	private String getPlainText(String source)
	{
		String result = "";
		String beginTag = "<PLAIN_TEXT>";
		String endTag = "</PLAIN_TEXT>";
		int index = 0;
		String tempstring = "";
		int tempstart = source.indexOf(beginTag);
		int tempend = source.indexOf(endTag);
		if ((tempstart != -1) && (tempstart < tempend) && 
				(tempstart != tempend + 1)) {
			result = source.substring(tempstart, tempend + endTag.length());
			result = result.substring(beginTag.length(), result.length() - endTag.length());
		}
		HtmlConverter cht = new HtmlConverter();
		try {
			result = cht.convertSource(source);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private String getPureHtml(String source)
	{
		String result = "";
		String beginTag = "<PLAIN_TEXT>";
		String endTag = "</PLAIN_TEXT>";
		int index = 0;
		String first_string = "";
		String second_string = "";
		int tempstart = source.indexOf(beginTag);
		int tempend = source.indexOf(endTag);
		if ((tempstart != -1) && (tempstart < tempend)) {
			if (tempstart != tempend + 1) {
				first_string = source.substring(0, tempstart);
				second_string = source.substring(tempend + endTag.length());
				result = new StringBuilder().append(first_string).append(second_string).toString();
			}
		}
		else result = source;
		return result;
	}

	@SuppressWarnings("deprecation")
	private void changeMailStatus(String mailId, String status) throws CRException, SystemException {
		String condition = new StringBuilder().append("MAIL_ID=").append(mailId).toString();
		CRDBOperator operator = new CRDBOperator();
		try {
			operator.acquireDBConnection();
			operator.setAttribute("STATUS", 12L, status);
			operator.updateRecord("T_MAILINFO", condition);
		} finally {
			operator.releaseDBConnection();
		}
	}

	@SuppressWarnings("deprecation")
	private void removeMailInfo(String mailID) throws CRException, SystemException
	{
		if ((mailID != null) && (mailID.length() > 0)) {
			CRDBOperator operator = new CRDBOperator();
			try {
				operator.acquireDBConnection();
				operator.removeRecord("T_MailINFO", new StringBuilder().append("Mail_ID=").append(mailID).toString());
			} finally {
				operator.releaseDBConnection();
			}
		}
	}

	@SuppressWarnings("deprecation")
	private void updateFailedMailStatus(String mailId, int count) throws CRException, SystemException {
		String condition = new StringBuilder().append("MAIL_ID=").append(mailId).toString();
		CRDBOperator operator = new CRDBOperator();
		try {
			operator.acquireDBConnection();
			if (count >= this.LIMIT_NUMBER)
				operator.setAttribute("STATUS", 12L, "FAILED");
			else {
				operator.setAttribute("STATUS", 12L, "READY");
			}
			operator.setAttribute("LIMIT", 4L, count);
			operator.updateRecord("T_MAILINFO", condition);
		} finally {
			operator.releaseDBConnection();
		}
	}

	private void sendByMail()
	{
		LogManager.logDebug(new StringBuilder().append("Executing mail instance [mail_id=").append(this.mailId).append("], fileName =").append(this.fileName).append(", by worker=").append(this).toString());
		try
		{
			MailMessage msg = new MailMessage(this.session);
			if (this.from != null) {
				String fromAddress = getFromMailAddress(this.from);
				String fromAlias = getFromMailAlias(this.from);
				msg.setFrom(new InternetAddress(fromAddress, fromAlias));
			}
			if (this.to != null) {
				StringTokenizer nizer = new StringTokenizer(this.to, ";");
				InternetAddress[] address = new InternetAddress[nizer.countTokens()];
				int i = 0;
				while (nizer.hasMoreTokens()) {
					String addr = nizer.nextToken();
					address[i] = new InternetAddress(getFromMailAddress(addr), getFromMailAlias(addr));
					i++;
				}
				msg.setRecipients(RecipientType.TO, address);
			}
			if (this.cc != null) {
				StringTokenizer nizer = new StringTokenizer(this.cc, ";");
				InternetAddress[] address = new InternetAddress[nizer.countTokens()];
				int i = 0;
				while (nizer.hasMoreTokens()) {
					String addr = nizer.nextToken();
					address[i] = new InternetAddress(getFromMailAddress(addr), getFromMailAlias(addr));
					i++;
				}
				msg.setRecipients(RecipientType.CC, address);
			}
			if (this.bcc != null) {
				StringTokenizer nizer = new StringTokenizer(this.bcc, ";");
				InternetAddress[] address = new InternetAddress[nizer.countTokens()];
				int i = 0;
				while (nizer.hasMoreTokens()) {
					String addr = nizer.nextToken();
					address[i] = new InternetAddress(getFromMailAddress(addr), getFromMailAlias(addr));
					i++;
				}
				msg.setRecipients(RecipientType.BCC, address);
			}
			if (this.replyto != null) {
				StringTokenizer nizer = new StringTokenizer(this.replyto, ";");
				InternetAddress[] address = new InternetAddress[nizer.countTokens()];
				int i = 0;
				while (nizer.hasMoreTokens()) {
					String addr = nizer.nextToken();
					address[i] = new InternetAddress(getFromMailAddress(addr), getFromMailAlias(addr));
					i++;
				}
				msg.setReplyTo(address);
			}
			if ((this.customfields != null) && (this.customfields.trim().length() > 0)) {
				String[][] customFieldPair = getCustomfieldPair(this.customfields);
				if (customFieldPair != null) {
					for (int i = 0; i < customFieldPair.length; i++) {
						msg.addHeader(customFieldPair[i][0], customFieldPair[i][1]);
					}
				}
			}
			if (this.subject != null)
				msg.setSubject(this.subject, "utf-8");
			msg.setSentDate(new Date());
			boolean emailAsBody = false;
			if (!this.asAttachment) {
				if (setEmailBody(msg))
					emailAsBody = true;
				else {
					LogManager.logDebug(new StringBuilder().append("Sending mail as body warning:Because file '").append(this.fileName).append("' cannot be sent as email body,it will be sent as attachment.").toString());
				}
			}
			if (!emailAsBody) {
				setEmailAttachment(msg);
			}
			//LogManager.logInfo("============> JUST BEFORE SEND");
			Transport.send(msg);
			if ((this.fileName != null) && (this.fileName.length() > 0)) {
				File f = new File(this.fileName);
				File p = f.getParentFile();
				if (f.exists()) {
					LogManager.logDebug(new StringBuilder().append("Delete mail_id=").append(this.mailId).append(", main file :").append(f.getAbsolutePath()).toString());
					f.delete();
				}
				if (tempFolderReadyToRemove(p)) {
					p.delete();
				}
			}
			if ((emailAsBody) && (this.publisherType != 1) && (this.curSendingFiles != null) && (this.curSendingFiles.size() > 0))
			{
				while (this.curSendingFiles.size() > 0) {
					File f = new File((String)this.curSendingFiles.get(0));
					File p = f.getParentFile();
					if (f.exists()) {
						LogManager.logDebug(new StringBuilder().append("Delete mail_id=").append(this.mailId).append(", and its associate files :").append(f.getAbsolutePath()).toString());
						f.delete();
					}
					if (tempFolderReadyToRemove(p)) {
						p.delete();
					}
					this.curSendingFiles.remove(0);
				}
			}
			removeMailInfo(this.mailInfo[0]);
			MailHandler.accumulateSentCount();
		} catch (Exception e) {
			LogManager.logError("Sending mail error:", e);
			try {
				updateFailedMailStatus(this.mailId, this.count);
			} catch (Exception ex) {
				LogManager.logError("Sending mail error:", e);
			}
			if (this.count >= this.LIMIT_NUMBER)
				MailHandler.accumulateUnSentCount();
		}
	}

	private boolean tempFolderReadyToRemove(File folder) {
		String rootTempDir = ConfigManager.getInstance().getValue("xPublishTempDir");
		return (!folder.getName().equalsIgnoreCase("DWMail")) && (!folder.equals(new File(rootTempDir))) && (folder.list().length == 0);
	}

	private boolean setEmailBody(MimeMessage msg) throws Exception {
		if ((this.fileName.endsWith(".htm")) || (this.fileName.endsWith(".html"))) {
			setHtmlBody(msg);
		} else if (this.fileName.endsWith(".txt")) {
			byte[] textContents = new byte[(int)new File(this.fileName).length()];
			FileInputStream in = new FileInputStream(this.fileName);
			in.read(textContents);
			in.close();
			String body = new String(textContents, "utf-8");
			String tidyOff = ConfigManager.getInstance().getValue("tidyoff");
			if ((this.publisherType == 1) && (tidyOff != null) && (tidyOff.equals("true")))
			{
				setVariableColorArchiveCSet(body);
			}
			MimeBodyPart bodyPart = new MimeBodyPart();
			bodyPart.setText(body);
			Multipart mp = new MimeMultipart();
			mp.addBodyPart(bodyPart);
			addAttachment(this.attachment, mp);
			msg.setContent(mp);
		}
		else if (this.fileName.endsWith(".mht")) {
			MimeBodyPart bodyPart = new MimeBodyPart();
			FileDataSource fds = new FileDataSource(this.fileName);
			bodyPart.setDataHandler(new DataHandler(fds));
			bodyPart.setFileName(this.fileName);
			Multipart mp = new MimeMultipart();
			mp.addBodyPart(bodyPart);
			addAttachment(this.attachment, mp);
			msg.setContent(mp);
		} else if ((this.fileName == null) || (this.fileName.length() == 0)) {
			if (this.message != null)
				msg.setContent(this.message.replace("\n", "<br>"), "text/html");
			else
				msg.setContent("", "text/html");
		} else {
			return false;
		}
		return true;
	}

	private boolean setEmailAttachment(MimeMessage msg)
			throws Exception
			{
		if ((this.publisherType == 1) && (
				(this.fileName.endsWith(".htm")) || (this.fileName.endsWith(".html"))))
		{
			this.fileName = convertHtmlToArchive();
		}

		MimeBodyPart mbp1 = new MimeBodyPart();
		if (this.message != null)
		{
			if ("true".equalsIgnoreCase(ConfigManager.getInstance().getValue("HTMLFormatting")))
			{
				mbp1.setContent(this.message, "text/html; charset=\"utf-8\"");
			}
			else
			{
				mbp1.setText(this.message, "utf-8");
			}
		}
		else mbp1.setText("This mail has an attachment.");
		MimeBodyPart mbp2 = new MimeBodyPart();
		FileDataSource fds = new FileDataSource(this.fileName);
		mbp2.setDataHandler(new DataHandler(fds));
		if (this.asAttachment) {
			int lastSlashPosition = 0;
			lastSlashPosition = this.fileName.lastIndexOf("\\");
			if (this.fileName.lastIndexOf("/") > lastSlashPosition)
				lastSlashPosition = this.fileName.lastIndexOf("/");
			mbp2.setFileName(MimeUtility.encodeText(getFriendlyAttachmentName(this.fileName.substring(lastSlashPosition + 1, this.fileName.length())), "utf-8", "B"));
		}
		else
		{
			mbp2.setFileName(MimeUtility.encodeText(this.fileName, "utf-8", "B"));
		}

		Multipart mp = new MimeMultipart();
		mp.addBodyPart(mbp1);
		mp.addBodyPart(mbp2);
		msg.setContent(mp);
		addAttachment(this.attachment, mp);
		return true;
			}

	private String getFriendlyAttachmentName(String filename)
	{
		int lastDashSeperator = filename.lastIndexOf(255);
		if (lastDashSeperator > 0) {
			String file_suffix = filename.substring(filename.lastIndexOf("."), filename.length());
			return new StringBuilder().append(filename.substring(0, lastDashSeperator)).append(file_suffix).toString();
		}
		return filename;
	}

	private String getFromMailAddress(String sInput)
	{
		StringBuilder retStr = new StringBuilder();
		String allStr = sInput;
		String[] ss = allStr.split(",");
		int len = ss.length;
		for (int i = 0; i < len; i++) {
			String s = ss[i];
			if (s != null)
			{
				int idxB_begin = s.indexOf(91);
				int idxB_end = s.indexOf(93);
				if ((idxB_begin >= 0) && (idxB_end >= 0)) {
					s = s.substring(idxB_begin + 1, idxB_end);
				}
				int idxP_begin = s.indexOf(40);
				int idxP_end = s.indexOf(41);
				if ((idxP_begin >= 0) && (idxP_end >= 0)) {
					s = s.substring(idxP_begin + 1, idxP_end);
				}
				retStr.append(s);
				break;
			}
		}
		return retStr.toString();
	}

	private String getFromMailAlias(String sInput)
	{
		StringBuilder retStr = new StringBuilder();
		String allStr = sInput;
		String[] ss = allStr.split(",");
		int len = ss.length;
		int i = 0; if (i < len) {
			String s = ss[i];
			if (s != null)
			{
				int idxB_begin = s.indexOf(91);
				int idxB_end = s.indexOf(93);
				if ((idxB_begin >= 0) && (idxB_end >= 0)) {
					s = s.substring(0, idxB_begin);
					retStr.append(s.trim());
				}
				else {
					int idxP_begin = s.indexOf(40);
					int idxP_end = s.indexOf(41);
					if ((idxP_begin >= 0) && (idxP_end >= 0)) {
						s = s.substring(0, idxP_begin);
						retStr.append(s.trim());
					}
				}
			}
		}
		return retStr.toString();
	}

	private void getPublishImageFiles(ArrayList<String> imageFiles)
	{
		int index = this.fileName.lastIndexOf(".", this.fileName.length());
		if (index < 0) {
			return;
		}
		ArrayList<String> result = new ArrayList<String>();
		String fileSeperator = this.fileName.lastIndexOf(File.separator) < 0 ? getFileSeperator() : File.separator;
		String path = this.fileName.substring(0, this.fileName.lastIndexOf(fileSeperator));
		String prefix = this.fileName.substring(this.fileName.lastIndexOf(fileSeperator) + 1, index);
		String[] files = new File(path).list();
		for (int i = 0; (files != null) && (i < files.length); i++) {
			if ((!files[i].equals(this.fileName.substring(this.fileName.lastIndexOf(fileSeperator) + 1))) && (files[i].startsWith(prefix)))
			{
				result.add(files[i]);
			}
		}
		for (int i = 0; i < result.size(); i++)
			imageFiles.add(new StringBuilder().append(path).append(fileSeperator).append((String)result.get(i)).toString());
	}

	private String getFileSeperator()
	{
		if ("/".equals(File.separator)) {
			return "\\";
		}
		return "/";
	}

	private String[][] getCustomfieldPair(String customFieldXML) throws JDOMException, IOException {
		SAXBuilder builder = new SAXBuilder();
		org.jdom.Document dataDOM = builder.build(new StringReader(customFieldXML));
		org.jdom.Element root = dataDOM.getRootElement();
		List<?> items = root.getChildren("Customfield");
		String[][] result = (String[][])null;
		if (items != null) {
			result = new String[items.size()][2];
			for (int i = 0; i < items.size(); i++) {
				org.jdom.Element item = (org.jdom.Element)items.get(i);
				result[i][0] = item.getAttributeValue("name");
				result[i][1] = item.getText();
			}
		}
		return result;
	}

}

/* Location:           C:\xStuff\4.5SP1B13\jboss-as-7.1.1.Final\standalone\deployments\xPression.ear\UniArch_Admin.war\WEB-INF\classes\
 * Qualified Name:     com.dsc.uniarch.admin.distributionservice.MailWorker
 * JD-Core Version:    0.6.2
 */