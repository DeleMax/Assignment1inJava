package krushkal;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class KrushkalAlgorithm {
	static long start1 = System.currentTimeMillis();
	
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException, InvocationTargetException, InterruptedException {

		
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run(){
				new SimpleGUI().setVisible(true);
			}
		});
	}
		
}	