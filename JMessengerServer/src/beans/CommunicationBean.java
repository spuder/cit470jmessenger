package beans;

import java.io.Serializable;
import java.util.HashMap;

public class CommunicationBean implements Serializable {
	private static final long serialVersionUID = -1581083365948288661L;
	private String command;
	private HashMap parameters;
	
	public void setCommand(String command) {
		this.command = command;
	}

	public String getCommand() {
		return command;
	}

	public void setParameters(HashMap parameters) {
		this.parameters = parameters;
	}

	public HashMap getParameters() {
		return parameters;
	}
}
