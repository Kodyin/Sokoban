package solver;

import java.util.HashMap;

import map.Box;
import map.Boxes;

public class Test {

	public static void main(String args[]) {
		HashMap<String, Integer> visited = new HashMap<String, Integer>();
		Boxes b = new Boxes();
		b.addBox(1, 3);
		b.addBox(2, 4);
		b.addBox(2, 3);
		b.addBox(1, 2);
		b.addBox(3, 1);
		b.addBox(1, 4);
		b.sortBox();
		for (Box i: b.getBoxList()) System.out.println(i.getx()+ "  "+ i.gety());
		Boxes a = new Boxes();
		a.addBox(1, 3);
		a.addBox(2, 4);
		a.addBox(2, 3);
		a.addBox(1, 2);
		a.addBox(3, 1);
		a.addBox(1, 4);
		a.sortBox();
		a.getBoxList().get(1).pushDown();
		Status sa = new Status(a, 1, 1);
		Status sb = new Status(b, 1, 1);
		System.out.println(sa.equals(sb));
		System.out.println(sa.getID());
		System.out.println(sa.getID());
		visited.put(sa.getID(),1);
		System.out.println(visited.get(sb.getID()));
		
	}
}
