package qc;

import org.quickconnect.QuickConnect;
import qc.*;


public class QCCommandMappings {

	public static void mapCommands() {
		
		QuickConnect.mapCommandToBCO("getUsersList", GetUserListBCO.class);
		QuickConnect.mapCommandToVCO("getUsersList", UpdateUserListVCO.class);
		
		QuickConnect.mapCommandToBCO("addUser", AddUserBCO.class);
		QuickConnect.mapCommandToVCO("addUser", UpdateUserListVCO.class);
		
	}
	
}
