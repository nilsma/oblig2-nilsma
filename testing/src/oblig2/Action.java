package oblig2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.LineNumberReader;
//import java.io.PrintStream;
import java.util.Scanner;

import javax.swing.JFileChooser;

public class Action implements ActionListener {
	private String choice;
	private JFileChooser fChoose;

	public Action(){
		fChoose = new JFileChooser();
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		choice = event.getActionCommand();
		if(choice.equals("Close")){	
			System.exit(0);
		} else if(choice.equals("Open")){
			try {
				int var = fChoose.showOpenDialog(fChoose);
				if(var==JFileChooser.APPROVE_OPTION){

					//File file = new File("C:\\Users\\BF\\Desktop\\pg100.txt");
					File file = new File("/home/nilsma/pg42104.txt");
					StringBuilder fileToView = new StringBuilder((int)file.length());
					Scanner sc = new Scanner(file);
					String lineSeperator = System.getProperty("line.seperator");
					try{
						while(sc.hasNext()){
							fileToView.append(sc.nextLine()+ lineSeperator);
							textToString();
						}
					}
					finally{
						sc.close();
					}
				}
			} catch(Exception e){
				e.printStackTrace();
			}
		} else {
			System.out.println("File not found");
		}
	}

	public static String textToString(){
		try {
			//File file = new File("C:\\Users\\BF\\Desktop\\pg100.txt");
			File file = new File("/home/nilsma/pg42104.txt");
			StringBuilder fileToView = new StringBuilder((int)file.length());
			Scanner sc = new Scanner(file);
			String lineSeperator = System.getProperty("line.seperator");
			while(sc.hasNext()){
				fileToView.append(sc.nextLine() + lineSeperator);
			}
			return fileToView.toString();
		}
			catch(FileNotFoundException f){
				return ("File not found: OUCH!");
			
			}
		}
	
	
	
	}