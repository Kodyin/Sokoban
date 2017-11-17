package map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Boxes {
	List<Box> boxlist;
	HashMap<String, Box> boxes;
	
	
	public Boxes() {
		boxlist = new ArrayList<Box>();
		boxes = new HashMap<String, Box>();
	}
	public Boxes(List<Box> i, HashMap<String, Box> j) {
		boxlist = i;
		boxes = j;
	}
	public void addBox(int x, int y) {
		Box tmp = new Box(x,y);
		boxlist.add(tmp);
		boxes.put(Integer.toString(x)+"-"+Integer.toString(y), tmp);
	}
	public void sortBox() {
		Collections.sort(boxlist, new BoxComparator());
	}
	
	public List<Box> getBoxList() {
		return boxlist;
	}
	public HashMap<String, Box> getBoxes() {
		return boxes;
	}
	
	public Boxes copyBoxes() {
		Boxes res = new Boxes();
		List<Box> newboxlist = res.getBoxList();
		HashMap<String, Box> newboxes = res.getBoxes();
		for (Box i: boxlist) {
			Box tmp = new Box(i.getx(),i.gety());
			newboxlist.add(tmp);
			newboxes.put(Integer.toString(i.getx())+"-"+Integer.toString(i.gety()), tmp);
		}
		return res;
	}
	
	
}
