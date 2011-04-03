package qc;

import org.quickconnect.QuickConnect;
import qc.*;


public class QCCommandMappings {

	public static void mapCommands() {
		
		QuickConnect.mapCommandToBCO("getUsersList", GetUserListBCO.class);
		QuickConnect.mapCommandToVCO("getUsersList", UpdateUserListVCO.class);
		
		QuickConnect.mapCommandToBCO("addUser", AddUserBCO.class);
		QuickConnect.mapCommandToBCO("addUser", GetUserListBCO.class);
		QuickConnect.mapCommandToVCO("addUser", UpdateUserListVCO.class);
		
		QuickConnect.mapCommandToBCO("deleteUser", DeleteUserBCO.class);
		QuickConnect.mapCommandToBCO("deleteUser", GetUserListBCO.class);
		QuickConnect.mapCommandToVCO("deleteUser", UpdateUserListVCO.class);
		
		QuickConnect.mapCommandToBCO("updateRole", UpdateRoleBCO.class);
		QuickConnect.mapCommandToBCO("updateRole", GetUserListBCO.class);
		QuickConnect.mapCommandToVCO("updateRole", UpdateUserListVCO.class);
		
		
		
	}
	
}
