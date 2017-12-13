package solver;

import java.io.IOException;


public class Main {

	public static void main(String args[]) {
		try {
			String data = args[0];
			String format = args[1];
			String file = args[2];
			
			
			
			if ((data.equals("-l") || data.equals("-d")) && (format.equals("-m") || format.equals("-s"))) {
				Search.Solve(data, format, file);
			}
			else {
				System.out.println("Invalid command");
			}
				
		} catch (IOException e) {
			System.out.println("Puzzle file not found");
		} 

		
	}
}
