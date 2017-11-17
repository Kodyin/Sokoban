package solver;

import java.util.HashMap;
import java.util.PriorityQueue;

import map.*;
import util.Parser;


public class Search {
	
	public static void Solve() {
		HashMap<String, Integer> visited = new HashMap<String, Integer>();
		SolMap M = new SolMap();
		PriorityQueue<Status> StatusQueue = new PriorityQueue<Status>(new StatusComp());
		Parser.ParseMap(M);
		Status ini = new Status(M.getBoxes(), M.getX(), M.getY());
		StatusQueue.add(ini);
		
	}
}
