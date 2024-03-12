package patilProject05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.FileSystem;
import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		
		Filesystem fileSystem = null;
		Scanner scanner = new Scanner(System.in);

		try {
			
			//reading the file
			FileInputStream fileInput = new FileInputStream("fs.data");
			ObjectInputStream objectInput = new ObjectInputStream(fileInput);
			fileSystem = (Filesystem) objectInput.readObject();
			fileInput.close();
			objectInput.close();
		
		} catch (FileNotFoundException e) {
		
			//creating an empty fileSystem if file is not found
			fileSystem = new Filesystem();
	
		} catch (ClassNotFoundException e) {
			
			System.out.println(e.getMessage());
		
		} catch (IOException e) {
			
			System.out.println(e.getMessage());
		}
		
		boolean quit = false;
		String item = null;
		
		while (!quit) {
			
			System.out.println("$ ");
			
			String shell = scanner.next().toLowerCase();
			if (shell.equals("cd") || shell.equals("touch") || shell.equals("mkdir") || shell.equals("rm") || shell.equals("rmdir")) {
				
				if (scanner.hasNext()) {
					
					item = scanner.next().toLowerCase();
				}
			} 
			
			try {
				
				if (shell.equals("cd")) {
	
					fileSystem.cd(shell);
				} else if (shell.equals("Is")) {
					
					fileSystem.Is();
				} else if (shell.equals("touch")) {
					
					fileSystem.touch(shell);
				} else if (shell.equals("mkdir")) {
					
					fileSystem.mkdir(shell);
				} else if (shell.equals("pwd")) {
					
					fileSystem.pwd();
				} else if (shell.equals("rm")) {
					
					fileSystem.cd(shell);
				} else if (shell.equals("rmdir")) {
					
					fileSystem.rmdir(shell);
				} else if (shell.equals("tree")) {
					
					fileSystem.tree();
				} else if (shell.equals("quit")) {
					
					quit = true;
					System.out.println("Quit");
				}
				
			} catch (IllegalStateException e) {
				
				System.out.println(e);
			} catch (Exception e) {
				
				System.out.println(e);
			}
		} //while loop
		
		try {
			
			FileOutputStream file = new FileOutputStream("fs.data");
			ObjectOutputStream object = new ObjectOutputStream(file);
			object.writeObject(file);
			object.close();
			file.close();
			
		} catch (IOException e) {
			
			System.out.println(e);
		}
	}
}
