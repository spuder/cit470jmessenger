package quickConnect;

import org.quickconnect.*;

import quickConnect.CreateSessionBCO;
import quickConnect.JoinSessionBCO;
import quickConnect.UpdateSessionsVCO;
import quickConnect.*;

public abstract class QCCommandMappings {
	
	
	public static void mapCommands() {
		
		QuickConnect.mapCommandToValCO(null, null);
		QuickConnect.mapCommandToBCO("login", BCOHashPassword.class);
		QuickConnect.mapCommandToBCO("login", BCOConnectToServer.class);
		QuickConnect.mapCommandToBCO("login", BCOSendLogin.class);
		QuickConnect.mapCommandToVCO("login", VCOLoginResult.class);
		
		QuickConnect.mapCommandToBCO("sendMessage", BCOsendMessage.class);
	
		QuickConnect.mapCommandToBCO("createSession", CreateSessionBCO.class);
		
		QuickConnect.mapCommandToBCO("sessionCreated", ParseSessionIdBCO.class);
		QuickConnect.mapCommandToBCO("sessionCreated", JoinSessionBCO.class);
		
		QuickConnect.mapCommandToVCO("sessionJoined", UpdateSessionsVCO.class);
	}

}
