	package qc;


import javax.swing.JOptionPane;

import org.quickconnect.QuickConnect;
import qc.*;


public class QCCommandMappings {

	public static void mapCommands() {
		
		QuickConnect.mapCommandToBCO("getUsersList", GetUserListBCO.class);
		QuickConnect.mapCommandToVCO("getUsersList", UpdateUserListVCO.class);
		
		QuickConnect.mapCommandToValCO("remoteGetUsersList", ValidateUserRoleValCO.class);
		QuickConnect.mapCommandToBCO("remoteGetUsersList", GetSessionUsersBCO.class);
		QuickConnect.mapCommandToBCO("remoteGetUsersList", SendUserListBCO.class);
		QuickConnect.mapCommandToBCO("remoteGetUsersList", SendResponseBCO.class);
		
		QuickConnect.mapCommandToValCO("remoteAddUser", ValidateUserRoleValCO.class);
		QuickConnect.mapCommandToBCO("remoteAddUser", AddUserBCO.class);
		QuickConnect.mapCommandToBCO("remoteAddUser", GetUserListBCO.class);
		QuickConnect.mapCommandToVCO("remoteAddUser", UpdateUserListVCO.class);
		
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
		
		QuickConnect.mapCommandToBCO("localCreateSession", CreateSessionBCO.class);
		QuickConnect.mapCommandToVCO("localCreateSession", AddSessionVCO.class);
		
		QuickConnect.mapCommandToValCO("joinSession", CheckForBannedUserValCO.class);
		QuickConnect.mapCommandToBCO("joinSession", AddSessionUserBCO.class);
		QuickConnect.mapCommandToBCO("joinSession", SendResponseBCO.class);
		QuickConnect.mapCommandToBCO("joinSession", GetFileListBCO.class);
		QuickConnect.mapCommandToBCO("joinSession", SendResponseBCO.class);
		QuickConnect.mapCommandToBCO("joinSession", NotifySessionOfNewUserBCO.class);
		QuickConnect.mapCommandToBCO("joinSession", GetSessionUsersBCO.class);
		QuickConnect.mapCommandToVCO("joinSession", UpdateSessionUsersVCO.class);
		
		QuickConnect.mapCommandToBCO("updateSessionUsers", GetSessionUsersBCO.class);
		QuickConnect.mapCommandToVCO("updateSessionUsers", UpdateSessionUsersVCO.class);
		
		QuickConnect.mapCommandToBCO("sessionList", GetSessionListBCO.class);
		QuickConnect.mapCommandToBCO("sessionList", SendResponseBCO.class);
		
		QuickConnect.mapCommandToBCO("fileList", GetFileListBCO.class);
		QuickConnect.mapCommandToBCO("fileList", SendResponseBCO.class);
		
		QuickConnect.mapCommandToValCO("sendMessage", CheckForBannedUserForMessageValCO.class);
		QuickConnect.mapCommandToBCO("sendMessage", ParseMessageBCO.class);
		QuickConnect.mapCommandToBCO("sendMessage", BroadcastMessageBCO.class);
		QuickConnect.mapCommandToVCO("sendMessage", UpdateLocalChatVCO.class);
		
		QuickConnect.mapCommandToBCO("getActiveSessions", GetSessionListBCO.class);
		QuickConnect.mapCommandToVCO("getActiveSessions", PopulateChatSessionTabsVCO.class);
		
		QuickConnect.mapCommandToBCO("stopSession", StopSessionBCO.class);
		QuickConnect.mapCommandToBCO("stopSession", NotifyUsersOfStoppedSession.class);
		QuickConnect.mapCommandToBCO("stopSession", RemoveSessionFromController.class);
		QuickConnect.mapCommandToVCO("stopSession", RemoveStoppedSessionVCO.class);
		
		QuickConnect.mapCommandToValCO("remoteCloseSession", ValidateUserRoleValCO.class);
		QuickConnect.mapCommandToBCO("remoteCloseSession", StopSessionBCO.class);
		QuickConnect.mapCommandToBCO("remoteCloseSession", NotifyUsersOfStoppedSession.class);
		QuickConnect.mapCommandToBCO("remoteCloseSession", RemoveSessionFromController.class);
		QuickConnect.mapCommandToVCO("remoteCloseSession", RemoveStoppedSessionVCO.class);
		
		QuickConnect.mapCommandToValCO("uploadFile", CheckForBannedUserForFileValCO.class);
		QuickConnect.mapCommandToBCO("uploadFile", ReceiveFileBCO.class);
		QuickConnect.mapCommandToBCO("uploadFile", InsertNewFileBCO.class);
		QuickConnect.mapCommandToBCO("uploadFile", GetFileListBCO.class);
		QuickConnect.mapCommandToBCO("uploadFile", BroadcastResponseBCO.class);
		QuickConnect.mapCommandToVCO("uploadFile", UpdateLocalFileVCO.class);
		
		QuickConnect.mapCommandToBCO("localBanUser", AddBannedUserBCO.class);
		QuickConnect.mapCommandToBCO("localBanUser", DeleteBannedSessionUserBCO.class);
		QuickConnect.mapCommandToBCO("localBanUser", NotifySessionOfBannedUserBCO.class);
		QuickConnect.mapCommandToBCO("localBanUser", GetSessionUsersBCO.class);
		QuickConnect.mapCommandToVCO("localBanUser", UpdateSessionUsersVCO.class);
		
		QuickConnect.mapCommandToValCO("banUser", ValidateUserRoleValCO.class);
		QuickConnect.mapCommandToBCO("banUser", AddBannedUserBCO.class);
		QuickConnect.mapCommandToBCO("banUser", DeleteBannedSessionUserBCO.class);
		QuickConnect.mapCommandToBCO("banUser", NotifySessionOfBannedUserBCO.class);
		QuickConnect.mapCommandToBCO("banUser", GetSessionUsersBCO.class);
		QuickConnect.mapCommandToVCO("banUser", UpdateSessionUsersVCO.class);
		
		QuickConnect.mapCommandToBCO("leaveUser", DeleteSessionUserBCO.class);
		QuickConnect.mapCommandToBCO("leaveUser", NotifySessionOfLeftUserBCO.class);
		QuickConnect.mapCommandToBCO("leaveUser", GetSessionUsersBCO.class);
		QuickConnect.mapCommandToVCO("leaveUser", UpdateSessionUsersVCO.class);
		
		QuickConnect.mapCommandToValCO("requestDownload", CheckForBannedUserForFileValCO.class);
		QuickConnect.mapCommandToBCO("requestDownload",GetRequestedFileBCO.class);
		QuickConnect.mapCommandToBCO("requestDownload",SendRequestedFileBCO.class);
		
		QuickConnect.mapCommandToValCO("updateFile", CheckForBannedUserForFileValCO.class);
		QuickConnect.mapCommandToBCO("updateFile", DeactivateOldFileBCO.class);
		QuickConnect.mapCommandToBCO("updateFile", ReceiveFileBCO.class);
		QuickConnect.mapCommandToBCO("updateFile", InsertNewFileBCO.class);
		QuickConnect.mapCommandToBCO("updateFile", GetFileListBCO.class);
		QuickConnect.mapCommandToBCO("updateFile", BroadcastResponseBCO.class);
		
		QuickConnect.mapCommandToECO("userExists", UserExistsECO.class);
		QuickConnect.mapCommandToECO("localUserExists", LocalUserExistsECO.class);
		
		QuickConnect.mapCommandToECO("sendError", SendErrorMessageECO.class);
		QuickConnect.mapCommandToECO("sendError", SendResponseBCO.class);
		
		QuickConnect.mapCommandToValCO("makeModerator", ValidateUserRoleValCO.class);
		QuickConnect.mapCommandToBCO("makeModerator", MakeModeratorBCO.class);
		QuickConnect.mapCommandToBCO("makeModerator", NotifySessionOfNewModeratorBCO.class);
		QuickConnect.mapCommandToBCO("makeModerator", GetSessionUsersBCO.class);
		QuickConnect.mapCommandToVCO("makeModerator", UpdateSessionUsersVCO.class);
		
		QuickConnect.mapCommandToBCO("localMakeModerator", MakeModeratorBCO.class);
		QuickConnect.mapCommandToBCO("localMakeModerator", NotifySessionOfNewModeratorBCO.class);
		QuickConnect.mapCommandToBCO("localMakeModerator", GetSessionUsersBCO.class);
		QuickConnect.mapCommandToVCO("localMakeModerator", UpdateSessionUsersVCO.class);
		
		
		
	}
	
}
