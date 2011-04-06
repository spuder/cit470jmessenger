package qc;

import org.quickconnect.QuickConnect;
import qc.*;


public class QCCommandMappings {

	public static void mapCommands() {
		
		QuickConnect.mapCommandToBCO("getUsersList", GetUserListBCO.class);
		QuickConnect.mapCommandToVCO("getUsersList", UpdateUserListVCO.class);
		
		QuickConnect.mapCommandToValCO("remoteGetUsersList", CheckPasswordValCO.class);
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
		
		QuickConnect.mapCommandToValCO("login", CheckForLoggedInUserValCO.class);
		QuickConnect.mapCommandToBCO("login", ProcessLoginBCO.class);
		QuickConnect.mapCommandToBCO("login", SendResponseBCO.class);
		
		QuickConnect.mapCommandToBCO("updateRole", UpdateRoleBCO.class);
		QuickConnect.mapCommandToBCO("updateRole", GetUserListBCO.class);
		QuickConnect.mapCommandToVCO("updateRole", UpdateUserListVCO.class);
		
		QuickConnect.mapCommandToBCO("createSession", CreateSessionBCO.class);
		QuickConnect.mapCommandToBCO("createSession", SendResponseBCO.class);
		QuickConnect.mapCommandToBCO("createSession", MakeModeratorOnCreationBCO.class);
		QuickConnect.mapCommandToBCO("createSession", GetSessionUsersOnCreationBCO.class);
		QuickConnect.mapCommandToVCO("createSession", AddSessionOnCreationVCO.class);
		QuickConnect.mapCommandToVCO("createSession", UpdateSessionUsersOnCreationVCO.class);
		
		QuickConnect.mapCommandToBCO("localCreateSession", CreateSessionBCO.class);
		QuickConnect.mapCommandToVCO("localCreateSession", AddSessionVCO.class);
		
		
		QuickConnect.mapCommandToValCO("joinSession", CheckForBannedUserValCO.class);
		QuickConnect.mapCommandToValCO("joinSession", CheckForSessionPasswordValCO.class);
		QuickConnect.mapCommandToValCO("joinSession", CheckPasswordValCO.class);
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
		
		QuickConnect.mapCommandToValCO("fileList", CheckPasswordValCO.class);
		QuickConnect.mapCommandToBCO("fileList", GetFileListBCO.class);
		QuickConnect.mapCommandToBCO("fileList", SendResponseBCO.class);
		
		QuickConnect.mapCommandToValCO("sendMessage", CheckForBannedUserForMessageValCO.class);
		QuickConnect.mapCommandToValCO("sendMessage", CheckPasswordValCO.class);
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
		QuickConnect.mapCommandToValCO("uploadFile", CheckPasswordValCO.class);
		QuickConnect.mapCommandToBCO("uploadFile", ReceiveFileBCO.class);
		QuickConnect.mapCommandToBCO("uploadFile", InsertNewFileBCO.class);
		QuickConnect.mapCommandToBCO("uploadFile", GetFileListBCO.class);
		QuickConnect.mapCommandToBCO("uploadFile", BroadcastResponseBCO.class);
		QuickConnect.mapCommandToBCO("uploadFile", NotifyUsersOfNewFileBCO.class);
		QuickConnect.mapCommandToVCO("uploadFile", UpdateLocalFileVCO.class);
		
		QuickConnect.mapCommandToBCO("localBanUser", AddBannedUserBCO.class);
		QuickConnect.mapCommandToBCO("localBanUser", DeleteBannedSessionUserBCO.class);
		QuickConnect.mapCommandToBCO("localBanUser", NotifySessionOfBannedUserBCO.class);
		QuickConnect.mapCommandToBCO("localBanUser", GetSessionUsersBCO.class);
		QuickConnect.mapCommandToVCO("localBanUser", UpdateSessionUsersVCO.class);
		
		QuickConnect.mapCommandToValCO("banUser", ValidateUserRoleValCO.class);
		QuickConnect.mapCommandToValCO("banUser", ValidateNotBanningHigherValCO.class);
		QuickConnect.mapCommandToValCO("banUser", CheckPasswordValCO.class);
		QuickConnect.mapCommandToBCO("banUser", AddBannedUserBCO.class);
		QuickConnect.mapCommandToBCO("banUser", DeleteBannedSessionUserBCO.class);
		QuickConnect.mapCommandToBCO("banUser", NotifySessionOfBannedUserBCO.class);
		QuickConnect.mapCommandToBCO("banUser", GetSessionUsersBCO.class);
		QuickConnect.mapCommandToVCO("banUser", UpdateSessionUsersVCO.class);
		QuickConnect.mapCommandToBCO("banUser", GetSessionUsersBCO.class);
		QuickConnect.mapCommandToBCO("banUser", SendUserListBCO.class);
		QuickConnect.mapCommandToBCO("banUser", SendResponseBCO.class);
		
		QuickConnect.mapCommandToBCO("leaveUser", LogOffUserBCO.class);
		QuickConnect.mapCommandToBCO("leaveUser", DeleteSessionUserBCO.class);
		QuickConnect.mapCommandToBCO("leaveUser", NotifySessionOfLeftUserBCO.class);
		QuickConnect.mapCommandToBCO("leaveUser", GetSessionUsersBCO.class);
		QuickConnect.mapCommandToVCO("leaveUser", UpdateSessionUsersVCO.class);
		
		QuickConnect.mapCommandToValCO("requestDownload", CheckForBannedUserForFileValCO.class);
		QuickConnect.mapCommandToValCO("requestDownload", CheckPasswordValCO.class);
		QuickConnect.mapCommandToBCO("requestDownload",GetRequestedFileBCO.class);
		QuickConnect.mapCommandToBCO("requestDownload",SendRequestedFileBCO.class);
		
		QuickConnect.mapCommandToValCO("updateFile", CheckForBannedUserForFileValCO.class);
		QuickConnect.mapCommandToValCO("updateFile", CheckPasswordValCO.class);
		QuickConnect.mapCommandToBCO("updateFile", DeactivateOldFileBCO.class);
		QuickConnect.mapCommandToBCO("updateFile", ReceiveFileBCO.class);
		QuickConnect.mapCommandToBCO("updateFile", InsertNewFileBCO.class);
		QuickConnect.mapCommandToBCO("updateFile", GetFileListBCO.class);
		QuickConnect.mapCommandToBCO("updateFile", BroadcastResponseBCO.class);
		QuickConnect.mapCommandToBCO("updateFile", NotifyUsersOfUpdatedFileBCO.class);
		
		QuickConnect.mapCommandToECO("userExists", UserExistsECO.class);
		QuickConnect.mapCommandToECO("localUserExists", LocalUserExistsECO.class);
		
		QuickConnect.mapCommandToECO("sendError", SendErrorMessageECO.class);
		QuickConnect.mapCommandToECO("sendError", SendResponseBCO.class);
		
		QuickConnect.mapCommandToValCO("makeModerator", ValidateUserRoleValCO.class);
		QuickConnect.mapCommandToBCO("makeModerator", MakeModeratorBCO.class);
		QuickConnect.mapCommandToBCO("makeModerator", NotifySessionOfNewModeratorBCO.class);
		QuickConnect.mapCommandToBCO("makeModerator", GetSessionUsersBCO.class);
		QuickConnect.mapCommandToBCO("makeModerator", SendUserListBCO.class);
		QuickConnect.mapCommandToBCO("makeModerator", SendResponseBCO.class);
		QuickConnect.mapCommandToVCO("makeModerator", UpdateSessionUsersVCO.class);

		
		QuickConnect.mapCommandToBCO("localMakeModerator", MakeModeratorBCO.class);
		QuickConnect.mapCommandToBCO("localMakeModerator", NotifySessionOfNewModeratorBCO.class);
		QuickConnect.mapCommandToBCO("localMakeModerator", GetSessionUsersBCO.class);
		QuickConnect.mapCommandToVCO("localMakeModerator", UpdateSessionUsersVCO.class);
		
		QuickConnect.mapCommandToValCO("removeFile", CheckForBannedUserForFileValCO.class);
		QuickConnect.mapCommandToValCO("removeFile", CheckPasswordValCO.class);
		QuickConnect.mapCommandToBCO("removeFile", DeleteFileBCO.class);
		QuickConnect.mapCommandToBCO("removeFile", GetFileListBCO.class);
		QuickConnect.mapCommandToBCO("removeFile", BroadcastResponseBCO.class);
		QuickConnect.mapCommandToBCO("removeFile", NotifyUsersOfDeletedFileBCO.class);
		
		QuickConnect.mapCommandToValCO("getFileVersions", CheckForBannedUserForFileValCO.class);
		QuickConnect.mapCommandToValCO("getFileVersions", CheckPasswordValCO.class);
		QuickConnect.mapCommandToBCO("getFileVersions", GetFileVersionsListBCO.class);
		QuickConnect.mapCommandToBCO("getFileVersions", SendResponseBCO.class);
		
		QuickConnect.mapCommandToValCO("requestVersionDownload", CheckForBannedUserForFileValCO.class);
		QuickConnect.mapCommandToValCO("requestVersionDownload", CheckPasswordValCO.class);
		QuickConnect.mapCommandToBCO("requestVersionDownload", GetRequestedVersionBCO.class);
		QuickConnect.mapCommandToBCO("requestVersionDownload", SendRequestedFileBCO.class);
		
		QuickConnect.mapCommandToECO("sendPromptLogin", SendSessionLoginPromptECO.class);
		QuickConnect.mapCommandToECO("sendPromptLogin", SendResponseBCO.class);
		
	}
	
}
