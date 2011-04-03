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
		
		QuickConnect.mapCommandToBCO("login", ProcessLoginBCO.class);
		QuickConnect.mapCommandToBCO("login", SendResponseBCO.class);
		
		QuickConnect.mapCommandToBCO("updateRole", UpdateRoleBCO.class);
		QuickConnect.mapCommandToBCO("updateRole", GetUserListBCO.class);
		QuickConnect.mapCommandToVCO("updateRole", UpdateUserListVCO.class);
		
		//TODO Add the make user moderator BCOs to this. Will need to modify the VCO index number with that change.
		QuickConnect.mapCommandToBCO("createSession", CreateSessionBCO.class);
		QuickConnect.mapCommandToBCO("createSession", SendResponseBCO.class);
		QuickConnect.mapCommandToVCO("createSession", AddSessionVCO.class);
		
		QuickConnect.mapCommandToBCO("joinSession", AddSessionUserBCO.class);
		QuickConnect.mapCommandToBCO("joinSession", SendResponseBCO.class);
		//QuickConnect.mapCommandToVCO("joinSession", UpdateSessionUsersVCO.class);
		
		QuickConnect.mapCommandToBCO("updateSessionUsers", GetSessionUsersBCO.class);
		QuickConnect.mapCommandToVCO("updateSessionUsers", UpdateSessionUsersVCO.class);
	}
	
}
