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
			if(bH.containsKey(aID) && !bH.containsKey(bID) && !wH.contains(bID) && !deadUp(m, bH, tx-2, ty)) {
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
			if(bH.containsKey(aID) && !bH.containsKey(bID) && !wH.contains(bID) && !deadDown(m, bH, tx+2, ty)) {
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
			if(bH.containsKey(aID) && !bH.containsKey(bID) && !wH.contains(bID) && !deadLeft(m, bH, tx, ty-2)) {
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
			if(bH.containsKey(aID) && !bH.containsKey(bID) && !wH.contains(bID) && !deadRight(m, bH, tx, ty+2)) {
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
	
	public static boolean deadUp(SolMap m, HashMap<String, Box> bH, int x, int y) {
		HashSet<String> goal = m.getGoals();
		HashSet<String> wall = m.getWalls();
		String i = getID(x,y);
		String u = getID(x-1,y);
		String l = getID(x,y-1);
		String r = getID(x,y+1);
		String ul = getID(x-1, y-1);
		String ur = getID(x-1, y+1);
		//corner
		if(!goal.contains(i) && wall.contains(u) && 
				(wall.contains(l) || wall.contains(r))) return true;
		//top
		if(wall.contains(u)){
			if((bH.containsKey(l)&& wall.contains(ul) && (!goal.contains(l) || !goal.contains(i)))
					|| (bH.containsKey(r)&& wall.contains(ur) && (!goal.contains(r) || !goal.contains(i))) )
				return true;
		}
		//left
		if(wall.contains(l)){
			if((bH.containsKey(u)&& wall.contains(ul) && (!goal.contains(u) || !goal.contains(i))))
				return true;
		}
		//right
		if(wall.contains(r)){
			if((bH.containsKey(u)&& wall.contains(ur) && (!goal.contains(u) || !goal.contains(i))))
				return true;
		}
		//4
		if((bH.containsKey(u) || wall.contains(u)) && (bH.containsKey(l) || wall.contains(l)) && (bH.containsKey(ul) || wall.contains(ul))) {
			if(!goal.contains(i) || (bH.containsKey(u) && !goal.contains(u)) || (bH.containsKey(l) && !goal.contains(l)) || (bH.containsKey(ul) && !goal.contains(ul)))
				return true;
		}
		if((bH.containsKey(u) || wall.contains(u)) && (bH.containsKey(r) || wall.contains(r)) && (bH.containsKey(ur) || wall.contains(ur))) {
			if(!goal.contains(i) || (bH.containsKey(u) && !goal.contains(u)) || (bH.containsKey(r) && !goal.contains(r)) || (bH.containsKey(ur) && !goal.contains(ur)))
				return true;
		}
		return false;
	}
	
	public static boolean deadDown(SolMap m, HashMap<String, Box> bH, int x, int y) {
		HashSet<String> goal = m.getGoals();
		HashSet<String> wall = m.getWalls();
		String i = getID(x,y);
		String d = getID(x+1,y);
		String l = getID(x,y-1);
		String r = getID(x,y+1);
		String dl = getID(x+1, y-1);
		String dr = getID(x+1, y+1);
		//corner
		if(!goal.contains(i) && wall.contains(d) && 
				(wall.contains(l) || wall.contains(r))) return true;
		//bottom
		if(wall.contains(d)){
			if((bH.containsKey(l)&& wall.contains(dl) && (!goal.contains(l) || !goal.contains(i)))
					|| (bH.containsKey(r)&& wall.contains(dr) && (!goal.contains(r) || !goal.contains(i))) )
				return true;
		}
		//left
		if(wall.contains(l)){
			if((bH.containsKey(d)&& wall.contains(dl) && (!goal.contains(d) || !goal.contains(i))))
				return true;
		}
		//right
		if(wall.contains(r)){
			if((bH.containsKey(d)&& wall.contains(dr) && (!goal.contains(d) || !goal.contains(i))))
				return true;
		}
		//4
		if((bH.containsKey(d) || wall.contains(d)) && (bH.containsKey(l) || wall.contains(l)) && (bH.containsKey(dl) || wall.contains(dl))) {
			if(!goal.contains(i) || (bH.containsKey(d) && !goal.contains(d)) || (bH.containsKey(l) && !goal.contains(l)) || (bH.containsKey(dl) && !goal.contains(dl)))
				return true;
		}
		if((bH.containsKey(d) || wall.contains(d)) && (bH.containsKey(r) || wall.contains(r)) && (bH.containsKey(dr) || wall.contains(dr))) {
			if(!goal.contains(i) || (bH.containsKey(d) && !goal.contains(d)) || (bH.containsKey(r) && !goal.contains(r)) || (bH.containsKey(dr) && !goal.contains(dr)))
				return true;
		}
		return false;
	}
	public static boolean deadLeft(SolMap m, HashMap<String, Box> bH, int x, int y) {
		HashSet<String> goal = m.getGoals();
		HashSet<String> wall = m.getWalls();
		String i = getID(x,y);
		String l = getID(x,y-1);
		String u = getID(x-1,y);
		String d = getID(x+1,y);
		String lu = getID(x-1, y-1);
		String ld = getID(x+1, y-1);
		//corner
		if(!goal.contains(i) && wall.contains(l) && 
				(wall.contains(u) || wall.contains(d))) return true;
		//left
		if(wall.contains(l)){
			if((bH.containsKey(u)&& wall.contains(lu) && (!goal.contains(u) || !goal.contains(i)))
					|| (bH.containsKey(d)&& wall.contains(ld) && (!goal.contains(d) || !goal.contains(i))) )
				return true;
		}
		//top
		if(wall.contains(u)){
			if((bH.containsKey(l)&& wall.contains(lu) && (!goal.contains(l) || !goal.contains(i))))
				return true;
		}		
		//bottom
		if(wall.contains(d)){
			if((bH.containsKey(l)&& wall.contains(ld) && (!goal.contains(l) || !goal.contains(i))))
				return true;
		}
		//4
		if((bH.containsKey(l) || wall.contains(l)) && (bH.containsKey(u) || wall.contains(u)) && (bH.containsKey(lu) || wall.contains(lu))) {
			if(!goal.contains(i) || (bH.containsKey(l) && !goal.contains(l)) || (bH.containsKey(u) && !goal.contains(u)) || (bH.containsKey(lu) && !goal.contains(lu)))
				return true;
		}
		if((bH.containsKey(l) || wall.contains(l)) && (bH.containsKey(d) || wall.contains(d)) && (bH.containsKey(ld) || wall.contains(ld))) {
			if(!goal.contains(i) || (bH.containsKey(l) && !goal.contains(l)) || (bH.containsKey(d) && !goal.contains(d)) || (bH.containsKey(ld) && !goal.contains(ld)))
				return true;
		}
		return false;
	}
	public static boolean deadRight(SolMap m, HashMap<String, Box> bH, int x, int y) {
		HashSet<String> goal = m.getGoals();
		HashSet<String> wall = m.getWalls();
		String i = getID(x,y);
		String r = getID(x,y+1);
		String u = getID(x-1,y);
		String d = getID(x+1,y);
		String ru = getID(x-1, y+1);
		String rd = getID(x+1, y+1);
		//corner
		if(!goal.contains(i) && wall.contains(r) && 
				(wall.contains(u) || wall.contains(d))) return true;
		//right
		if(wall.contains(r)){
			if((bH.containsKey(u)&& wall.contains(ru) && (!goal.contains(u) || !goal.contains(i)))
					|| (bH.containsKey(d)&& wall.contains(rd) && (!goal.contains(d) || !goal.contains(i))) )
				return true;
		}
		//top
				if(wall.contains(u)){
					if((bH.containsKey(r)&& wall.contains(ru) && (!goal.contains(r) || !goal.contains(i))))
						return true;
				}		
		//bottom
		if(wall.contains(d)){
			if((bH.containsKey(r)&& wall.contains(rd) && (!goal.contains(r) || !goal.contains(i))))
						return true;
		}		
		//4
		if((bH.containsKey(r) || wall.contains(r)) && (bH.containsKey(u) || wall.contains(u)) && (bH.containsKey(ru) || wall.contains(ru))) {
			if(!goal.contains(i) || (bH.containsKey(r) && !goal.contains(r)) || (bH.containsKey(u) && !goal.contains(u)) || (bH.containsKey(ru) && !goal.contains(ru)))
				return true;
		}
		if((bH.containsKey(r) || wall.contains(r)) && (bH.containsKey(d) || wall.contains(d)) && (bH.containsKey(rd) || wall.contains(rd))) {
			if(!goal.contains(i) || (bH.containsKey(r) && !goal.contains(r)) || (bH.containsKey(d) && !goal.contains(d)) || (bH.containsKey(rd) && !goal.contains(rd)))
				return true;
		}
		return false;
	}
	public static String getID(int x, int y) {
		return Integer.toString(x)+ '-' + Integer.toString(y);
	}
	public static boolean dead(SolMap m, HashMap<String, Box> bH, int x, int y, int d) {
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
		//Vertical
		if (d<=2 && fixedX && (bH.containsKey(Integer.toString(x)+'-'+Integer.toString(y-1)) || bH.containsKey(Integer.toString(x)+'-'+Integer.toString(y+1)) ))
			return true;
		//Horizontal
		if (d>2 && fixedY && (bH.containsKey(Integer.toString(x-1)+'-'+Integer.toString(y)) || bH.containsKey(Integer.toString(x+1)+'-'+Integer.toString(y)) ))
			return true;
		if ( d==1 && bH.containsKey(Integer.toString(x-1)+'-'+Integer.toString(y)) && 
		(
			(bH.containsKey(Integer.toString(x)+'-'+Integer.toString(y-1)) && bH.containsKey(Integer.toString(x-1)+'-'+Integer.toString(y-1)))
			||
			(bH.containsKey(Integer.toString(x)+'-'+Integer.toString(y+1)) && bH.containsKey(Integer.toString(x-1)+'-'+Integer.toString(y+1)))
		) )
		return true;
		if ( d==2 && bH.containsKey(Integer.toString(x+1)+'-'+Integer.toString(y)) && 
				(
					(bH.containsKey(Integer.toString(x)+'-'+Integer.toString(y-1)) && bH.containsKey(Integer.toString(x+1)+'-'+Integer.toString(y-1)))
					||
					(bH.containsKey(Integer.toString(x)+'-'+Integer.toString(y+1)) && bH.containsKey(Integer.toString(x+1)+'-'+Integer.toString(y+1)))
				) ) 
				return true; 
		if ( d==3 && bH.containsKey(Integer.toString(x)+'-'+Integer.toString(y-1)) && 
				(
					(bH.containsKey(Integer.toString(x-1)+'-'+Integer.toString(y)) && bH.containsKey(Integer.toString(x-1)+'-'+Integer.toString(y-1)))
					||
					(bH.containsKey(Integer.toString(x+1)+'-'+Integer.toString(y)) && bH.containsKey(Integer.toString(x+1)+'-'+Integer.toString(y-1)))
				) )
				return true; 
		if ( d==4 && bH.containsKey(Integer.toString(x)+'-'+Integer.toString(y+1)) && 
				(
					(bH.containsKey(Integer.toString(x-1)+'-'+Integer.toString(y)) && bH.containsKey(Integer.toString(x-1)+'-'+Integer.toString(y+1)))
					||
					(bH.containsKey(Integer.toString(x+1)+'-'+Integer.toString(y+1)) && bH.containsKey(Integer.toString(x+1)+'-'+Integer.toString(y+1)))
				) )
				return true; 
		return false;
	}

	
}
