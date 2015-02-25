package com.xcp.diy.dfchelper;

import com.documentum.fc.common.DfException;

public class dfcHelperWrapper {
	
	public String killAllProcess(
		String strAdminUser,
		String strAdminPassword,
		String strRepository,
		String strProcessName
	) {
		try {
			new dfcHelperSession(strAdminUser, strAdminPassword, strRepository);
			dfcHelper dfch = new dfcHelper();
			dfch.KillAllProcess(strProcessName);
		} catch (DfException e) {
			e.printStackTrace();
			return "ERROR";
		}
		return "SUCSESS";
	}

}
