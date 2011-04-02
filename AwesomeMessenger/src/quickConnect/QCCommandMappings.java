package quickConnect;

import org.quickconnect.*;

public abstract class QCCommandMappings {
	
	
	public static void mapCommands() {
		
		QuickConnect.mapCommandToValCO(null, null);
		QuickConnect.mapCommandToBCO("login", BCOHashPassword.class);
		QuickConnect.mapCommandToBCO("login", BCOConnectToServer.class);
		QuickConnect.mapCommandToBCO("login", BCOSendLogin.class);
		QuickConnect.mapCommandToVCO("login", VCOLoginResult.class);
		
		
	}

}
