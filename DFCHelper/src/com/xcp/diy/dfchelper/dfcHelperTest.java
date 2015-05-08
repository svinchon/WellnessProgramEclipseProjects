package com.xcp.diy.dfchelper;

import com.documentum.fc.common.DfException;

public class dfcHelperTest
{
	@SuppressWarnings("unused")
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
			String strUser = "svinchon";
			//dfch.AddUserToGroup(strRep, strAdminUserName, strAdminPassword, strUser, strGroup);
			System.out.println("Path of of object 090d8b668000cdcf is: \""+dfch.GetFullPathFromId("090d8b668000cdcf")+"\"");
		} catch (DfException e) {
			e.printStackTrace();
		}
	}

}
