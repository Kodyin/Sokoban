package solver;

import java.util.HashMap;
import java.util.PriorityQueue;

import map.Box;
import map.Boxes;
import map.SolMap;
import util.Parser;

public class Test {

	public static void main(String args[]) {
		HashMap<String, Integer> visited = new HashMap<String, Integer>();
		Boxes b = new Boxes();
		b.addBox(1, 2);
		b.addBox(2, 4);
		b.addBox(2, 3);
		b.sortBox();
		for (Box i: b.getBoxList()) System.out.println(i.getx()+ "  "+ i.gety());
		Boxes a = new Boxes();
		a.addBox(1, 2);
		a.addBox(2, 4);
		a.addBox(2, 3);
		a.sortBox();
		Boxes c = new Boxes();
		c.addBox(1, 3);
		c.addBox(2, 4);
		c.addBox(2, 3);
		c.sortBox();
		//a.getBoxList().get(1).pushDown();
		Status sa = new Status(a, 1, 1, 5, "");
		Status sb = new Status(b, 1, 1, 0, "");
		Status sc = new Status(c, 1, 1, 1, "");
		
		
		SolMap M = new SolMap();
		PriorityQueue<Status> StatusQueue = new PriorityQueue<Status>(new StatusComp());
		Parser.ParseMap(M);
		
		StatusQueue.add(sa);
		StatusQueue.add(sb);
		StatusQueue.add(sc);
		while (!StatusQueue.isEmpty()) {
			Status curr = StatusQueue.poll();
			if (!visited.containsKey(curr.getID())) {
				System.out.println(curr.getID() + "  " + curr.getStep());
				visited.put(curr.getID(), 1);
			}
			
		}
		
//		System.out.println(sa.getID());
//		System.out.println(sb.getID());
//		System.out.println(sa.equals(sb));
//	
		visited.put(sa.getID(),1);
		System.out.println(visited.get(sb.getID()));
		
	}
}
