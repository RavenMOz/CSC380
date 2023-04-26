package application.application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MainSequence implements Runnable {

	public final String path = "/home/FamilyTree/files/CommandBuffer.txt";
	BufferedReader reader;
		
	public void start() {
				
		Main.mainThread = new Thread(this);
		Main.mainThread.start();
	}
	
	void setupReader() throws FileNotFoundException {
		reader = new BufferedReader(new FileReader(path));
		/* System.out.println("New Reader Made."); */}
	void resetReader() throws IOException { reader.close(); setupReader(); makeNewBufferFile();}

	void makeNewBufferFile() throws IOException {
		FileWriter f = new FileWriter(path);
		f.flush();
		f.close();
		/* System.out.println("New Command Buffer."); */ 
	}

	@Override
	public void run() {
		
		try {
			
			try { setupReader(); }
			catch (FileNotFoundException e) { 
				System.out.println("Buffer Not Found.");
				makeNewBufferFile();
				setupReader(); 
			}
			
			while (Main.active) {
				
				if (reader.ready()) {
					String cmds = "";
					while (reader.ready()) {
						cmds += reader.readLine() + ";";
					}
					new CommandDispatch(cmds).execute();
					resetReader();
				}
			}
			
			end();
			
		} catch (IOException e) {
			e.printStackTrace();
			end();
		}
		
	}

	private void end() {
		System.out.println("Main Thread Stopping...");
		try { reader.close(); System.out.println("Command Buffer Closed.");} catch (IOException e ) {}
		System.out.println("Main Thread Stopped.");
	}
	
}
