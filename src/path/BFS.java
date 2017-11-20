package path;


import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import map.*;
import solver.*;

public class BFS {
	
	
	public static List<Status> bfs(SolMap m, Status s, HashSet<String> v){
		List<Status> res = new LinkedList<Status>();
		HashSet<String> visited = new HashSet<String>();
		//use box class to take steps.
		Queue<Step> q = new LinkedList<Step>();
		HashMap<String, Box> bH = s.getBoxes().getBoxes();
		HashSet<String> wH = m.getWalls();
		Step init = new Step(s.getx(), s.gety(), 0, "");
		q.add(init);
		visited.add(init.getID());
		while (!q.isEmpty()) {
			Step curr = q.poll();
			//position to move
			String aID = "";
			//push to
			String bID = "";
			int tx = curr.getx(); 
			int ty = curr.gety();
			//System.out.println(curr.getID());
			//Up
			aID = Integer.toString(tx-1) + '-' + Integer.toString(ty);
			bID = Integer.toString(tx-2) + '-' + Integer.toString(ty);
			
			if(!visited.contains(aID) && !bH.containsKey(aID) && !wH.contains(aID)) {
				q.add(new Step(tx-1, ty, curr.steps+1, curr.path+'U'));
				visited.add(aID);
			}
			if(bH.containsKey(aID) && !bH.containsKey(bID) && !wH.contains(bID) && !dead(m, bH, tx-2, ty, true)) {
				Status newS = new Status(s.getBoxes().copyBoxes(), tx-1, ty, s.getStep()+curr.steps+1, s.getPath()+curr.path+'U');
				Box tmp = newS.getBoxes().getBoxes().get(aID);
				tmp.pushUp();
				newS.getBoxes().getBoxes().put(bID, tmp);
				newS.getBoxes().getBoxes().remove(aID);
				newS.getBoxes().sortBox();
				String i = newS.getID() + 'x' + Integer.toString(newS.getx())+'y'+ Integer.toString(newS.gety()); 
				if (!v.contains(i)) res.add(newS);
			}
			//Down
			aID = Integer.toString(tx+1) + '-' + Integer.toString(ty);
			bID = Integer.toString(tx+2) + '-' + Integer.toString(ty);
			
			if(!visited.contains(aID) && !bH.containsKey(aID) && !wH.contains(aID)) {
				q.add(new Step(tx+1, ty, curr.steps+1, curr.path+'D'));
				visited.add(aID);
			}
			if(bH.containsKey(aID) && !bH.containsKey(bID) && !wH.contains(bID) && !dead(m, bH, tx+2, ty, true)) {
				Status newS = new Status(s.getBoxes().copyBoxes(), tx+1, ty, s.getStep()+curr.steps+1, s.getPath()+curr.path+'D');
				Box tmp = newS.getBoxes().getBoxes().get(aID);
				tmp.pushDown();
				newS.getBoxes().getBoxes().put(bID, tmp);
				newS.getBoxes().getBoxes().remove(aID);
				newS.getBoxes().sortBox();
				String i = newS.getID() + 'x' + Integer.toString(newS.getx())+'y'+ Integer.toString(newS.gety()); 
				if (!v.contains(i)) res.add(newS);
			}
			//Left
			aID = Integer.toString(tx) + '-' + Integer.toString(ty-1);
			bID = Integer.toString(tx) + '-' + Integer.toString(ty-2);
			
			if(!visited.contains(aID) && !bH.containsKey(aID) && !wH.contains(aID)) {
				q.add(new Step(tx, ty-1, curr.steps+1, curr.path+'L'));
				visited.add(aID);
			}
			if(bH.containsKey(aID) && !bH.containsKey(bID) && !wH.contains(bID) && !dead(m, bH, tx, ty-2, false)) {
				Status newS = new Status(s.getBoxes().copyBoxes(), tx, ty-1, s.getStep()+curr.steps+1, s.getPath()+curr.path+'L');
				Box tmp = newS.getBoxes().getBoxes().get(aID);
				tmp.pushLeft();
				newS.getBoxes().getBoxes().put(bID, tmp);
				newS.getBoxes().getBoxes().remove(aID);
				newS.getBoxes().sortBox();
				String i = newS.getID() + 'x' + Integer.toString(newS.getx())+'y'+ Integer.toString(newS.gety()); 
				if (!v.contains(i)) res.add(newS);
			}
			//Right
			aID = Integer.toString(tx) + '-' + Integer.toString(ty+1);
			bID = Integer.toString(tx) + '-' + Integer.toString(ty+2);
			
			if(!visited.contains(aID) && !bH.containsKey(aID) && !wH.contains(aID)) {
				q.add(new Step(tx, ty+1, curr.steps+1, curr.path+'R'));
				visited.add(aID);
			}
			if(bH.containsKey(aID) && !bH.containsKey(bID) && !wH.contains(bID) && !dead(m, bH, tx, ty+2, false)) {
				Status newS = new Status(s.getBoxes().copyBoxes(), tx, ty+1, s.getStep()+curr.steps+1, s.getPath()+curr.path+'R');
				Box tmp = newS.getBoxes().getBoxes().get(aID);
				tmp.pushRight();
				newS.getBoxes().getBoxes().put(bID, tmp);
				newS.getBoxes().getBoxes().remove(aID);
				newS.getBoxes().sortBox();
				String i = newS.getID() + 'x' + Integer.toString(newS.getx())+'y'+ Integer.toString(newS.gety()); 
				if (!v.contains(i)) res.add(newS);
			}
			
		}
		return res;
	}
	
	public static boolean dead(SolMap m, HashMap<String, Box> bH, int x, int y, boolean v) {
		if (m.getGoals().contains(Integer.toString(x)+'-'+Integer.toString(y)))
			return false;
		boolean fixedX = false, fixedY = false;
		if (
				m.getWalls().contains(Integer.toString(x-1)+'-'+Integer.toString(y)) 
						|| 
				m.getWalls().contains(Integer.toString(x+1)+'-'+Integer.toString(y))
			)
			fixedX = true;
		if (
				(m.getWalls().contains(Integer.toString(x)+'-'+Integer.toString(y-1)) 
						|| 
				m.getWalls().contains(Integer.toString(x)+'-'+Integer.toString(y+1)))
			)
			fixedY = true;
		if (fixedX && fixedY)  return true;
		if (v && fixedX && (bH.containsKey(Integer.toString(x)+'-'+Integer.toString(y-1)) || bH.containsKey(Integer.toString(x)+'-'+Integer.toString(y+1)) ))
			return true;
		if (!v && fixedY && (bH.containsKey(Integer.toString(x-1)+'-'+Integer.toString(y)) || bH.containsKey(Integer.toString(x+1)+'-'+Integer.toString(y)) ))
			return true;
		
		return false;
	}

	
}
