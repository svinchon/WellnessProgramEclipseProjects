package com.xcp.diy.dfchelper;

import com.documentum.fc.common.DfException;

public class dfcHelperTest
{
	public static void main(String[] args)
	{
		try {
			new dfcHelperSession("dmadmin", "demo.demo", "corp");
			dfcHelper dfch = new dfcHelper();
			String strProcessName = "UpdateAllMembersData";
			dfch.KillAllProcess(strProcessName);
		} catch (DfException e) {
			e.printStackTrace();
		}
	}

}
