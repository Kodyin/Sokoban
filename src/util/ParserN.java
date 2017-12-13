package util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


import map.SolMap;

public class ParserN {
	public static void ParseMap(SolMap m, String filename) throws IOException {
		Scanner sc;
//	        try {
	            sc = new Scanner(new File(filename));
	            int i = 0;
	            while (sc.hasNextLine()) {
	            		String str = sc.nextLine();
	            		i++;
	            		if (str.charAt(0) != 's') parseLine(m, str, i);
	            }
	            m.setx(i);
//	        } catch (IOException e) {
//				System.out.println("Puzzle file not found");
//				return;
//			} 

	        // Iterate over the lines in the file, adding new
	        // vertices as they are found and connecting them with edges.
	  
	        sc.close();
	    }
		private static void parseLine(SolMap g, String str, int r){
		  	  // Check if there is another line of input
		  	  for(int i=0; i<str.length(); i++) {
		  		  if(str.charAt(i) == '#') g.addWall(r, i+1);
		  		  if(str.charAt(i) == '$') g.addBox(r, i+1);
		  		  if(str.charAt(i) == '.') g.addGoal(r, i+1);
		  		  if(str.charAt(i) == '*') {g.addGoal(r, i+1);g.addBox(r, i+1);}
		  		  if(str.charAt(i) == '@') g.initial(r, i+1);
		  	  }
		  	  g.sety(str.length());
		 }
		
	    public static void write(String s) {
            try {
	    		File writename = new File(".\\result\\en\\output.txt"); 
            writename.createNewFile(); 
            BufferedWriter out = new BufferedWriter(new FileWriter(writename, true));  
            out.append(s+ "\r\n"); 
            out.flush();   
            out.close();  
  
            } catch (Exception e) {  
            		e.printStackTrace();  
            }  
	    }
	    
	    
}
