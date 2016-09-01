package krushkal;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;


public class SimpleGUI extends JFrame{
	int counter;
	Scanner scn;
	String Line;
	String input1, input2;
	JTextField field, field2;
	JTextArea textArea;
	
	public SimpleGUI(){
		go();
		setSize(600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Minimum Spanning Tree");
		
	}
	
	
	public String getInput1() {
		return field.getText();
	}




	public String getInput2() {
		return field2.getText();
	}


	public void go(){
		JPanel panel = new JPanel();
		panel.setBackground(Color.blue);
		getContentPane().add(panel);
		
		JLabel label = new JLabel("Enter File Name");
		field = new JTextField(20);
		
		
		JLabel label2 = new JLabel("Enter no of nodes");
		field2 = new JTextField(20);
		
		
		JCheckBox checkbox = new JCheckBox("Use Kruskal's Algorithm");
		checkbox.setSelected(true);
		
		JButton button = new JButton("Start");
		button.setSize(20,20);
		button.addActionListener(
							new ActionListener(){
								@Override
								public void actionPerformed(ActionEvent e){
									
									String theInput = getInput1();
									String node = getInput2();
									
									
									Scanner thefile = null;
									try {
										thefile = new Scanner(new File(theInput));
									} catch (FileNotFoundException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									int nodes = Integer.parseInt(node);
									
									
									Graph graph = new Graph(nodes);
									Vertex[] thevertices = graph.vertices;
									 for (int v=0; v < thevertices.length; v++) {
									        graph.addVertex(v);
								        }
									 
									 
									 
									 while(thefile.hasNext()){
										 
										 int matrix[][]= new int[nodes+1][nodes+1];
										 
										 for (int i=1; i<matrix.length;i++){
											 for(int j = 1; j<matrix.length;j++){
												int value =  matrix[i][j]=thefile.nextInt();
												
												graph.addEdge(i, j, value);

												 
											 }
										 }

							        try {
										graph.applyKrushkalAlgo();
									} catch (FileNotFoundException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									} catch (UnsupportedEncodingException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
							        try {
										FileReader r = new FileReader("resultJava1.txt");
										try {
											textArea.read(r, " ");
										} catch (IOException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
									} catch (FileNotFoundException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									 }}}
							);
		
		
		textArea = new JTextArea(18, 40);
		textArea.setLineWrap(true);
		textArea.setBackground(Color.WHITE);
		
		JScrollPane scroller = new JScrollPane(textArea);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		JButton button2 = new JButton("STEP");
		button2.setSize(20,20);
		button2.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e){
						counter++;
						if(counter==1){
														
										try {
											FileReader r = new FileReader("resultJava2.txt");
											scn = new Scanner(r);
											Line = scn.nextLine();;
											textArea.append(Line+"\n");
										} catch (FileNotFoundException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}}
										  else
									        {
									            if(scn.hasNextLine())
									            {
									                Line = scn.nextLine();
													textArea.append(Line+"\n");

									            }
									            else
									            {
									                button2.setEnabled(false);
									            }
					}}});
		
		panel.add(checkbox);
		panel.add(label);
		panel.add(field );
		panel.add(label2);
		panel.add(field2 );
		panel.add(button);
		panel.add(scroller);
		panel.add(button2);

	}
	

}
