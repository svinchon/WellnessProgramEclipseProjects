package com.dsc.uniarch.admin.distributionservice;

import com.dsc.uniarch.cr.common.CRDBOperator;
import com.dsc.uniarch.cr.error.CRException;
import com.dsc.uniarch.exception.SystemException;
import com.dsc.uniarch.util.ConfigManager;
import com.dsc.uniarch.util.ServerInfoHelper;

import java.util.concurrent.ThreadPoolExecutor;

@SuppressWarnings("unused")
public class MailHandler extends DSThreadPoolHandler
{
	private static long mailSent = 0L;

	private static long mailUnSent = 0L;

	private final String T_MAILINFO = "T_MAILINFO";

	public String getHandlerName()
	{
		return "MailHandler";
	}

	public static synchronized void accumulateSentCount() {
		mailSent += 1L;
	}

	public static synchronized void accumulateUnSentCount() {
		mailUnSent += 1L;
	}

	public void process() {
		try {
			LogManager.logDebug("Start sending mail...");
			while (true)
			{
				String[][] mailInfo = getMailInfo();
				if ((mailInfo == null) || (mailInfo.length == 0)) {
					LogManager.logDebug("No mail records found ...");
				}
				else {
					LogManager.logDebug(new StringBuilder().append(mailInfo.length).append(" mail records found ...").toString());
					updateInprocessMailInfo(mailInfo);
					sendMails(mailInfo);
					if (isStopped()) {
						break;
					}
				}
			}
			LogManager.logDebug(new StringBuilder().append(mailSent).append(" mails sent; ").append(mailUnSent).append(" not sent . ").toString());
			mailSent = 0L;
			mailUnSent = 0L;
		} catch (Exception e) {
			LogManager.logError("Send mail error:", e);
		}
	}

	private void sendMails(String[][] mailInfo) {
		for (int i = 0; i < mailInfo.length; i++) {
			MailWorker worker = new MailWorker(mailInfo[i]);
			try {
				execute(worker);
			} catch (Exception e) {
				LogManager.logError("Send mail error:", e);
			}
		}
	}

	protected ThreadPoolExecutor createThreadPool()
	{
		int minPoolSize = 1;
		int maxPoolSize = 1;
		String minPoolSizeConfig = ConfigManager.getInstance().getValue("MailMinimumPoolSize");
		if (minPoolSizeConfig != null) {
			minPoolSizeConfig = minPoolSizeConfig.trim();
			if (minPoolSizeConfig.length() > 0) {
				minPoolSize = Integer.parseInt(minPoolSizeConfig);
			}
		}
		String maxPoolSizeConfig = ConfigManager.getInstance().getValue("MailMaximumPoolSize");
		if (maxPoolSizeConfig != null) {
			maxPoolSizeConfig = maxPoolSizeConfig.trim();
			if (maxPoolSizeConfig.length() > 0) {
				maxPoolSize = Integer.parseInt(maxPoolSizeConfig);
			}
		}

		return createThreadPool(minPoolSize, maxPoolSize);
	}

	private String[][] getMailInfo() throws CRException, SystemException {
		String[] cols = { "MAIL_ID", "FROMADDR", "TOADDR", "CCADDR", "FILENAME", "SUBJECT", "SMTPSERVER", "MESSAGE", "STATUS", "ASATTACHMENT", "ASMULTIPART", "ATTACHMENT", "PUBLISHERTYPE", "LIMIT", "JOBRUN_ID", "BCCADDR", "REPLYTOADDR", "CUSTOMFIELDS", "RETURNPATH" };

		StringBuilder cond = new StringBuilder();
		cond.append("STATUS='READY' AND SERVERNAME=").append("'").append(ServerInfoHelper.getServerName()).append("'");

		int numberOfRecords = Integer.parseInt(ConfigManager.getInstance().getValue("NumberOfRecords"));

		CRDBOperator operator = new CRDBOperator();
		return operator.queryAttrListAllValues("T_MAILINFO", cols, "MAIL_ID", cond.toString(), 0, numberOfRecords - 1);
	}

	private void updateInprocessMailInfo(String[][] mails) throws CRException, SystemException {
		if ((mails == null) || (mails.length == 0)) {
			return;
		}

		StringBuilder cond = new StringBuilder("MAIL_ID");
		cond.append(" IN (");
		for (int i = 0; i < mails.length; i++) {
			if (i > 0) {
				cond.append(",");
			}
			cond.append(mails[i][0]);
		}
		cond.append(")");

		CRDBOperator operator = new CRDBOperator();
		operator.setAttribute("STATUS", 12L, "PROCESS");
		operator.updateRecord("T_MAILINFO", cond.toString());
	}
}

/* Location:           C:\xStuff\4.5SP1B13\jboss-as-7.1.1.Final\standalone\deployments\xPression.ear\UniArch_Admin.war\WEB-INF\classes\
 * Qualified Name:     com.dsc.uniarch.admin.distributionservice.MailHandler
 * JD-Core Version:    0.6.2
 */