package dhariwal;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class main {

	private JFrame frame;
	private JFileChooser fileChooser;
	private BufferedReader br;
	private File file;
	int returnVal;
	String currentLine;
	private JButton fileBtn;
	private JScrollPane scrollPane;
	private JTextArea textResult;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main window = new main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public main() {
		initialize();
		createEvents();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		fileBtn = new JButton("I call upon the file Gods!");
		fileBtn.setBounds(137, 29, 185, 29);
		frame.getContentPane().add(fileBtn);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(72, 71, 316, 142);
		frame.getContentPane().add(scrollPane);
		
		textResult = new JTextArea();
		scrollPane.setViewportView(textResult);
	}
	
	private void createEvents()
	{ //method when Add Button is clicked
		fileBtn.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				filereading(); //actual method for adding the Big Numbers
			}
		});
	}
	
	public void filereading() {
		LinkedList list = new LinkedList();
		double x = 0;
		fileChooser = new JFileChooser("/users");
		returnVal = fileChooser.showOpenDialog(null);			//THANK YOU GOOGLE for the fileChooser code!
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			file  = fileChooser.getSelectedFile();
			
			try {
				br = new BufferedReader(new FileReader(file));	//bufferedReader that reads from the selected file
				String line = "";	//first line in the file till it finds a \n (line break)
				while ((currentLine = br.readLine()) != null) {
					line += currentLine;
				}
				if (line == null) {	//check to see if the file is empty
					textResult.setText("File Error! File is empty :(");
				}
				else if (br != null ) {	//check to see if the file has any character or empty lines
					for (int i = 0; i < line.length(); i++) {
						char c = line.charAt(i);
						if ((c >= 'A' && c <= 'Z') && (c >= 'a' && c <= 'z')) {
							textResult.setText("File Error! This file contains a character or has an empty line in between.");
						}
					}
				}
				else {
				while ((currentLine = br.readLine()) != null) {
					x = Double.parseDouble(currentLine);	//convert the String (number) to double
					list.insert(x); //add to list
					x = 0;
				}
				double mean = list.mean();
				double stdDeviation = list.stdDev();
				textResult.setText("The mean of all the numbers in this file is " + mean + ". \nThe standard deviation of all the numbers in this file is " + stdDeviation);
				}
			}catch(Exception error) {
				error.printStackTrace();
		}
	}
	}
}

