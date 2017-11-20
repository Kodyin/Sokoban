package util;

import java.io.File;
import java.util.Scanner;


import map.SolMap;

public class Parser {
	public static void ParseMap(SolMap m, String filename) {
		Scanner sc;
	        try {
	            sc = new Scanner(new File(filename));
	            if (sc.hasNextLine()){
	                String str = sc.nextLine();
	                if (str.charAt(0) != 's') parseHW(m, str);
	              }
	            if (sc.hasNextLine()){
	                String str = sc.nextLine();
	                if (str.charAt(0) != 's') parseWall(m, str);
	              }
	            if (sc.hasNextLine()){
	                String str = sc.nextLine();
	                if (str.charAt(0) != 's') parseBox(m, str);
	              }
	            if (sc.hasNextLine()){
	                String str = sc.nextLine();
	                if (str.charAt(0) != 's') parseGoal(m, str);
	              }
	            if (sc.hasNextLine()){
	                String str = sc.nextLine();
	                if (str.charAt(0) != 's') parseInit(m, str);
	              }  
	        } catch (Exception e) {
	            e.printStackTrace();
	            return;
	        }

	        // Iterate over the lines in the file, adding new
	        // vertices as they are found and connecting them with edges.
	  
	        sc.close();
	    }
	    private static void parseHW(SolMap g, String str){
	    	  Scanner sc = new Scanner(str);
	    	  // Check if there is another line of input
	    	  while(sc.hasNext()){
	    		  int a = sc.nextInt();
	    		  int b = sc.nextInt();
	    	   g.set(a, b);
	    	  }
	    	  sc.close();
	    }
	    private static void parseWall(SolMap g, String str){
	    	  Scanner sc = new Scanner(str);
	    	  if (sc.hasNext()) sc.nextInt(); 
	    	  // Check if there is another line of input
	    	  while(sc.hasNext()){
	    		  int a = sc.nextInt();
	    		  int b = sc.nextInt();
	    	   g.addWall(a, b);
	    	  }
	    	  sc.close();
	    }
	    private static void parseBox(SolMap g, String str){
	    	  Scanner sc = new Scanner(str);
	    	  if (sc.hasNext()) sc.nextInt(); 
	    	  // Check if there is another line of input
	    	  while(sc.hasNext()){
	    		  int a = sc.nextInt();
	    		  int b = sc.nextInt();
	    	   g.addBox(a, b);
	    	  }
	    	  sc.close();
	    }
	    private static void parseGoal(SolMap g, String str){
	    	  Scanner sc = new Scanner(str);
	    	  if (sc.hasNext()) sc.nextInt(); 
	    	  // Check if there is another line of input
	    	  while(sc.hasNext()){
	    		  int a = sc.nextInt();
	    		  int b = sc.nextInt();
	    	   g.addGoal(a, b);
	    	  }
	    	  sc.close();
	    }
	    private static void parseInit(SolMap g, String str){
	    	  Scanner sc = new Scanner(str); 
	    	  // Check if there is another line of input
	    	  while(sc.hasNext()){
	    		  int a = sc.nextInt();
	    		  int b = sc.nextInt();
	    	   g.initial(a, b);
	    	  }
	    	  sc.close();
	    }
	    
	    
}
