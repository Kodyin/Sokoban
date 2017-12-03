package solver;

import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

import map.*;
import path.BFS;
import util.*;


public class Search {
	
	public static void Solve() {
		//Mark visited status
		HashSet<String> visited = new HashSet<String>();
		//initial priority queue for status
		PriorityQueue<Status> statusQueue = new PriorityQueue<Status>(new StatusComp());
		//Map initiation
		SolMap M = new SolMap();
		//Parser.ParseMap(M, "data/datac.txt");
		ParserN.ParseMap(M,"data/s99.txt");
		//set and store target ID
		M.setTar();
		String target = M.getTarID();
		System.out.println(target);
		M.initH();
		System.out.println(M.getH().get("1-2"));
		M.setBH();
		System.out.println(M.getInitH());
		//add the original status
		Status ini = new Status(M.getBoxes(), M.getX(), M.getY(), 0, M.getInitH(), "");
		statusQueue.add(ini);
		while (!statusQueue.isEmpty()) {
			Status curr = statusQueue.poll();
			String currString = curr.getID();
			//find the solution, immediately set M.sol and terminate
			if (currString.equals(target)) {
				M.setSol(curr.getPath());
				System.out.println("Ter");
				break;
			}
			//if this status not visited, explore it using BFS and add child status 
			currString += 'x' + Integer.toString(curr.getx())+'y'+ Integer.toString(curr.gety()); 
			if (!visited.contains(currString)) {
				//Parser.write("curr  " + currString);
				visited.add(currString);
				List<Status> childStatus = BFS.bfs(M, curr, visited);
				//Parser.write("child");
				for(Status i : childStatus) 
				{
						//Parser.write(i.getID()+'x'+Integer.toString(i.getx())+'y'+Integer.toString(i.gety()) ); 
						if(!visited.contains(i.getID()+ 'x' + Integer.toString(curr.getx())+'y'+ Integer.toString(curr.gety()) )) statusQueue.add(i);
					
				}
				
				
			}	
		}
		System.out.println(M.getSol());
	}
}
