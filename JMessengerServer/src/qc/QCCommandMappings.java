package qc;

import org.quickconnect.QuickConnect;


public class QCCommandMappings {

	public static void mapCommands() {
		
		QuickConnect.mapCommandToBCO("getUsersList", GetUserListBCO.class);
		QuickConnect.mapCommandToValCO("getUsersList", UpdateUserListVCO.class);
		
	}
	
}
