package map;

import java.util.HashMap;

import solver.*;

public class SolMap {
	int height, width;
	HashMap<String, Integer> grid; //"1-1" stands for square at (1,1), the values are null-empty, 1-wall, 0-goal 
	Boxes b;
	Boxes tarBoxes;//to use the boxes and status getID function.
	String tar; //target Box string
	String sol;
	int X,Y; //player
	public SolMap(){	
		tar = "";
		sol = "";
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
		tarBoxes.addBox(x, y);
	}
	//Use this only once
	public void setTar() {
		Status tmp = new Status(tarBoxes, 0, 0, 0, "");
		tar += tmp.getID();
	}
	public String getTarID() {
		return tar;
	}
	public void setSol(String s) {
		sol += s;
	}
	public String getSol() {
		return sol;
	}
	public void addBox(int x, int y) {
		b.addBox(x, y);
	}
	public void initial(int x, int y) {
		X=x;
		Y=y;
	}
}
