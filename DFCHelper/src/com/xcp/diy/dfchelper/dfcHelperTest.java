package com.xcp.diy.dfchelper;

import com.documentum.fc.common.DfException;

public class dfcHelperTest
{
	public static void main(String[] args)
	{
		try {
			new dfcHelperSession("dmadmin", "demo.demo", "corp");
			dfcHelper dfch = new dfcHelper();
			//String strProcessName = "UpdateAllMembersData";
			//dfch.KillAllProcess(strProcessName);
			String strRep = "corp";
			String strAdminPassword = "demo.demo";
			String strAdminUserName = "dmadmin";
			String strGroup = "wp02_member";
			String strUser = "john.doe5612125178";
			dfch.AddUserToGroup(strRep, strAdminUserName, strAdminPassword, strUser, strGroup);
		} catch (DfException e) {
			e.printStackTrace();
		}
	}

}
