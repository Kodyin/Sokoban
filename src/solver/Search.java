package solver;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

import map.*;
import path.BFS;
import util.*;

 

public class Search {
	
	public static void Solve(String data, String format, String file) throws IOException {
		//Mark visited status
		HashSet<String> visited = new HashSet<String>();
		//initial priority queue for status
		PriorityQueue<Status> statusQueue = new PriorityQueue<Status>(new StatusComp());
		//Map initiation
		SolMap M = new SolMap();
		String dir = "";
		if(data.equals("-l")) {
			dir = "data/Sokoban/";
		}
		else if (data.equals("-d")) {
			dir = "data/";
		}
		dir += file;
		if (format.equals("-m")) ParserN.ParseMap(M, dir);
		else Parser.ParseMap(M, dir);
		System.out.println(M.toString());
			
		
		//set and store target ID
		M.setTar();
		String target = M.getTarID();
		M.initH();
		M.setBH();
		long startTime=System.currentTimeMillis();   //StartTime
		//add the original status
		Status ini = new Status(M.getBoxes(), M.getX(), M.getY(), 0, M.getInitH(), "");
		statusQueue.add(ini);
		int states = 1;
		Count c = new Count();
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
				states++;
				visited.add(currString);
				List<Status> childStatus = BFS.bfs(M, curr, visited, c);
				for(Status i : childStatus) 
				{
						if(!visited.contains(i.getID()+ 'x' + Integer.toString(curr.getx())+'y'+ Integer.toString(curr.gety()) )) statusQueue.add(i);
						else c.seen++;
				}		
			}	
			else c.seen++;
		}
		long endTime=System.currentTimeMillis(); //EndTime  
		System.out.println("Solution: " + M.getSol());
		System.out.println("States explored: " + states);
		System.out.println("States seen before: " + Integer.toString(c.seen));
		System.out.println("Dead-lock pruned: " + Integer.toString(c.pruned));
		System.out.println("Millis elapsed: "+(endTime-startTime)+"ms");  

	}
}
