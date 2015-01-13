package com.dsc.uniarch.admin.distributionservice;

import java.rmi.dgc.VMID;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailMessage extends MimeMessage
{

    private static int id = 0;

    public MailMessage(Session session)
    {
        super(session);
    }

    protected void updateMessageID()
        throws MessagingException
    {
        setHeader("Message-ID", (new StringBuilder()).append("<").append(getUniqueMessageIDValue()).append(">").toString());
    }

    public void saveChanges()
        throws MessagingException
    {
        super.saveChanges();
        updateMessageID();
    }

    private String getUniqueMessageIDValue()
    {
        String suffix = null;
        InternetAddress addr = InternetAddress.getLocalAddress(session);
        if(addr != null)
        {
            suffix = addr.getAddress();
        } else
        {
            suffix = "javamailuser@localhost";
        }
        StringBuffer s = new StringBuffer();
        s.append((new VMID()).hashCode()).append('.').append(id++).append(System.currentTimeMillis()).append('.').append("JavaMail.").append(suffix);
        return s.toString();
    }

}
