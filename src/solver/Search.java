package solver;

import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

import map.*;
import path.BFS;
import util.Parser;


public class Search {
	
	public static void Solve() {
		//Mark visited status
		HashSet<String> visited = new HashSet<String>();
		//initial priority queue for status
		PriorityQueue<Status> statusQueue = new PriorityQueue<Status>(new StatusComp());
		//Map initiation
		SolMap M = new SolMap();
		Parser.ParseMap(M);
		//set and store target ID
		M.setTar();
		String target = M.getTarID();
		//add the original status
		Status ini = new Status(M.getBoxes(), M.getX(), M.getY(), 0, "");
		statusQueue.add(ini);
		while (!statusQueue.isEmpty()) {
			Status curr = statusQueue.poll();
			String currString = curr.getID();
			//find the solution, immediately set M.sol and terminate
			if (currString.equals(target)) {
				M.setSol(curr.getPath());
				break;
			}
			//if this status not visited, explore it using BFS and add child status 
			currString += 'x' + Integer.toString(curr.getx())+'y'+ Integer.toString(curr.gety()); 
			if (!visited.contains(currString)) {
				List<Status> childStatus = BFS.bfs(M, curr);
				for(Status i : childStatus) statusQueue.add(i);
				System.out.println(currString);
				visited.add(currString);
			}	
		}
		System.out.println(M.getSol());
		System.out.println(2);

		
	}
}
