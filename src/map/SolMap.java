package map;

import java.util.HashMap;

public class SolMap {
	int height, width;
	HashMap<String, Integer> grid; //"1-1" stands for square at (1,1), the values are null-empty, 1-wall, 0-goal 
	Boxes b;
	int X,Y; //player
	public SolMap(){	
	}
	
	public void set(int x, int y) {
		height = x;
		width = y;
	}
	
	public Boxes getBoxes() {
		return b;
	}
	public int getX() {
		return X;
	}
	public int getY() {
		return Y;
	}
	public void addWall(int x, int y) {
		grid.put(Integer.toString(x)+"-"+Integer.toString(y), 1);
	}
	
	public void addGoal(int x, int y) {
		grid.put(Integer.toString(x)+"-"+Integer.toString(y), 0);
	}
	public void addBox(int x, int y) {
		b.addBox(x, y);
	}
	public void initial(int x, int y) {
		X=x;
		Y=y;
	}
	
}
