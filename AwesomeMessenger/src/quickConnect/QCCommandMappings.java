package quickConnect;

import org.quickconnect.*;

import quickConnect.*;

public abstract class QCCommandMappings {
	
	
	public static void mapCommands() {
		
		QuickConnect.mapCommandToValCO(null, null);
		QuickConnect.mapCommandToBCO("login", BCOHashPassword.class);
		QuickConnect.mapCommandToBCO("login", BCOConnectToServer.class);
		QuickConnect.mapCommandToBCO("login", BCOSendLogin.class);
		QuickConnect.mapCommandToVCO("login", VCOLoginResult.class);
		
		QuickConnect.mapCommandToBCO("sendMessage", BCOsendMessage.class);
		
		QuickConnect.mapCommandToVCO("receiveMessage", ReceiveMessageBCO.class);
		
		QuickConnect.mapCommandToVCO("receiveSystemMessage", ReceiveSystemMessageVCO.class);
	
		QuickConnect.mapCommandToBCO("createSession", CreateSessionBCO.class);
		
		QuickConnect.mapCommandToBCO("sessionCreated", ParseSessionIdBCO.class);
		QuickConnect.mapCommandToBCO("sessionCreated", JoinSessionBCO.class);
		
		QuickConnect.mapCommandToVCO("sessionJoined", UpdateSessionsVCO.class);
		
		QuickConnect.mapCommandToBCO("sessionList", RequestSessionListBCO.class);
		
		QuickConnect.mapCommandToVCO("sessionListResponse", DisplaySessionListVCO.class);
		
		QuickConnect.mapCommandToBCO("joinSession", JoinSessionBCO.class);
		
		QuickConnect.mapCommandToBCO("leaveSession", LeaveSessionBCO.class);
		QuickConnect.mapCommandToVCO("leaveSession", DeleteSessionTabVCO.class);
		
		QuickConnect.mapCommandToVCO("fileListResponse", DisplayFileListVCO.class);
		
		QuickConnect.mapCommandToBCO("uploadFile", SendFileToUploadBCO.class);
		
		QuickConnect.mapCommandToBCO("requestDownload", SendDownloadRequestBCO.class);
		
		QuickConnect.mapCommandToBCO("fileDownloadResponse", ActualReceiveFileBCO.class);
		QuickConnect.mapCommandToBCO("fileDownloadResponse", DownloadFileBCO.class);
		
		QuickConnect.mapCommandToBCO("updateFile", SendUpdatedFileBCO.class);
		
		QuickConnect.mapCommandToVCO("error", HandleErrorVCO.class);
		
		QuickConnect.mapCommandToBCO("sendFile", ActualFileUploadBCO.class);
		
		QuickConnect.mapCommandToBCO("addUser", AddUserToServerBCO.class);
		
		QuickConnect.mapCommandToBCO("closeSession", CloseSessionOnServerBCO.class);
		
		QuickConnect.mapCommandToBCO("getUsersList", RequestSessionUsersListBCO.class); // send "sessionId" - String
		
		QuickConnect.mapCommandToVCO("userListResponse", DisplaySessionUsersListVCO.class); // receive "list" - Vector->{"username","role"}
	
		QuickConnect.mapCommandToBCO("banUser", SendBanUserBCO.class);
		//QuickConnect.mapCommandToBCO("banUser", RequestSessionUsersListBCO.class);
		
		QuickConnect.mapCommandToBCO("makeModerator", SendMakeModeratorBCO.class);
		//QuickConnect.mapCommandToBCO("makeModerator", RequestSessionUsersListBCO.class);
		QuickConnect.mapCommandToBCO("removeFile", SendDeleteFileBCO.class);
		
		QuickConnect.mapCommandToBCO("getFileVersions", RequestFileVersionsListBCO.class);
		
		QuickConnect.mapCommandToVCO("fileVersionListResponse", DisplayFileVersionsListVCO.class);
		
		QuickConnect.mapCommandToBCO("downloadVersion", SendDownloadVersionRequestBCO.class);
		
		QuickConnect.mapCommandToBCO("getSessionPassword", GetSessionPasswordBCO.class);
		QuickConnect.mapCommandToBCO("getSessionPassword", JoinPasswordProtectedSessionBCO.class);
	}

}
