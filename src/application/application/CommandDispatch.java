package application.application;

import application.middleware.JSONWriteFunctions;

public class CommandDispatch implements Runnable {
	
	String[] cmds;
	
	Command activeCommand;
	long userID = 0;
	
	Thread cmdThread;
		
	public CommandDispatch(String commands) {
		
		cmds = commands.split(";");

	}

	public void execute() {
		cmdThread = new Thread(this);
		cmdThread.start();
	}

	@Override
	public void run() {
		
		
		for (String command : cmds) {
			command = command.toUpperCase();
			activeCommand = Command.valueOf(command.split(" ")[0]);
			userID = Long.parseLong(command.split(" ")[1]);
			
			switch(activeCommand) {
			case SAVE : {
				JSONWriteFunctions.save(userID);
			} break;
			case SHARE: {
				
			} break;
			case EXPORT: {
				
			} break;
			case LOAD: {
				JSONWriteFunctions.createUserData(userID);
			} break;
			case DISPOSE: {
				JSONWriteFunctions.dispose(userID);
			} break;
			
			}
			
			System.out.println(activeCommand.toString() + ": " + userID);
			
		}
		
	}

}
