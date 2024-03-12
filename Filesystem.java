package patilProject05;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Filesystem implements Serializable {
	
	Node root;
	Node currentDirectory;
	
	public Filesystem() {
		
		//A no-argument constructor that makes a root node with an empty string for the name 
		root = new Node(" ", null, true);
		//and sets the current directory to the newly made root node
		currentDirectory = root;
	}
	
	public void checkMakeFile(String name) {
		
		//If the current directory has a child with name already, throws an exception. Otherwise do nothing
		if (this.currentDirectory.name.equals(name)) {
			
			throw new IllegalArgumentException("Current directory already has a child with name.");
		
		} else {
			
			return;
		}
	}
	
	public void Is() {
		
		//Prints all the children of the current directory
		currentDirectory.children();
		for(int i = 0; i < currentDirectory.children().size(); i++) {
			
			System.out.println(currentDirectory.children().get(i));
		}
	}
	
	public void mkdir(String dirname) {
		
		//Adds a new directory child node to the current directory.  Throw an exception if the name already exists
		try {
			currentDirectory.appendChild(dirname, true);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public void touch(String name) {
		
		//makes a new file, not a directory
		checkMakeFile(name);
		currentDirectory.appendChild(name, false);
	}
	
	public void cd(String name) throws NoSuchElementException{
		
		// Changes the currentDirectory to name
		//If currentDirectory doesn't have a child with the given name that is a directory, throw an exception
		if (this.currentDirectory.children != null) {
			
			for(Node n: currentDirectory.children) {
				
				if(n.name.equals(name) && n.isDirectory) {
					
					this.currentDirectory = n;
					return;
				}
			}
		}
		
		throw new NoSuchElementException("currentDirectory doesn't have a child with the given name that is a directory.");
	}
	
	public void rm(String filename) {
		
		//Remove the file with the name filename from currentDirectory
		//Throw an exception if it's a directory or does not exist.
		if (this.currentDirectory.children != null) {
			
			for(Node n: currentDirectory.children) {
				
				if(n.name.equals(filename) && !n.isDirectory) {
					currentDirectory.children.remove(n);
					return;
				}
			}
		}
		
		throw new NoSuchElementException("The file " + filename + "cannot be removed.");
	}
	
	public void rmdir(String dirname) {
		
		//Remove the directory with the name dirname from currentDirectory
		//Throw an exception if it's not a directory or if it's not empty.
		if(this.currentDirectory.children != null) {
			
			for(Node n: currentDirectory.children) {
				
				if(n.name.equals(dirname)) {
					currentDirectory.children.remove(n);
					return;
				}
			}
		}
		
		throw new NoSuchElementException("The directory " + dirname + "cannot be removed.");
	}
	
	public static String prettyPrint(Node root, int depth, StringBuilder sb) {
		
		for(int i = 0; i < depth; i++) {
			//Add tabs for each depth level:
			sb.append("\t");
		}
		
		sb.append(root.toString() + "\n");
		
		//If the node has at least one child node, add the starting node's
		//subtree for each child node:
		if(root.children().size() != 0) {
			
			for(int i = 0; i < root.children.size(); i++) {
				prettyPrint(root.children().get(i), depth + 1, sb);
			}
		}
		//Return the pretty-printed string of the tree:
		return sb.toString();
	}
	
	public void tree() {
		
		System.out.println(prettyPrint(this.currentDirectory, 0, new StringBuilder()));
	}
	
	public String pwd() {
		
		String output = "/";
		Node currentNode = this.currentDirectory;
		while(currentNode.parent != null) {
			
			output = "/" + currentNode.name + output;
			currentNode = currentNode.parent;
		}
		return output;
	}
	
	public class Node implements Serializable {
		
		private String name;
		private ArrayList<Node> children;
		private Node parent;
		private boolean isDirectory;
		
		public Node(String name, Node parent, boolean isDirectory) {
			
			this.name = name;
			this.parent = parent;
			this.isDirectory = isDirectory;
		}
		
		public boolean isDirectory() {
			//return true if this node is a directory, false if it is a file
			return isDirectory;
		}
		
		public ArrayList<Node> children() {
			//return the ArrayList of child node
			return children;
		}
		
		public void appendChild(String n, boolean isDirectory1) {
			//add a new child to this node
			name = n;
			isDirectory = isDirectory1;
		}
		
		public boolean isRoot() {
			//return true if this node is the root (it's parent is null)
			if (parent == null) {
				
				return true;
			}
			return false;
		}
		
		public String toString() {
			return name;
		}
	}
}
