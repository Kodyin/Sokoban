package map;

import java.util.HashMap;
import java.util.HashSet;

import solver.*;

public class SolMap {
	int height, width;
	HashSet<String> wallGrid; 
	HashSet<String> goalGrid;
	Boxes b;
	Boxes tarBoxes;//to use the boxes and status getID function.
	String tar; //target Box string
	String sol;
	int initH;
	int X,Y; //player
	HashMap<String, Integer> hMatrix; //Heuristic Matrix
	public SolMap(){	
		tar = "";
		sol = "";
		b = new Boxes();
		tarBoxes = new Boxes();
		wallGrid = new HashSet<String>();
		goalGrid = new HashSet<String>();
		tar = "";
		sol = "";
		hMatrix = new HashMap<String, Integer>();
		initH = 0;
	}
	
	public void setBH() {
		int res = 0;
		for(Box i: b.getBoxList()) res += hMatrix.get(Integer.toString(i.getx())+'-'+Integer.toString(i.gety()));
		initH = res;
	}
	public int getInitH() {
		return initH;
	}
	public HashMap<String, Integer> getH(){
		return hMatrix;
	}
	public void initH() {
		for(int i = 1; i<=height; i++) {
			for(int j = 1; j<=width; j++) {
				int min = Integer.MAX_VALUE;
				for(Box b:tarBoxes.getBoxList()) {
					min = Math.min(Math.abs(b.getx()-i) + Math.abs(b.gety()-j), min);
				}
				hMatrix.put(Integer.toString(i)+'-'+Integer.toString(j), min);
			}
		}
	}
	public void setx(int x) {
		height = x;
	}
	public void sety(int y) {
		width = y;
	}
	public void set(int x, int y) {
		height = x;
		width = y;
	}
	
	public HashSet<String> getWalls(){
		return wallGrid;
	}
	public HashSet<String> getGoals(){
		return goalGrid;
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
		wallGrid.add(Integer.toString(x)+"-"+Integer.toString(y));
	}
	
	public void addGoal(int x, int y) {
		goalGrid.add(Integer.toString(x)+"-"+Integer.toString(y));
		tarBoxes.addBox(x, y);
	}
	//Use this only once
	public void setTar() {
		tarBoxes.sortBox();
		Status tmp = new Status(tarBoxes, 0, 0, 0, 0, "");
		tar += tmp.getID();
	}
	public String getTarID() {
		return tar;
	}
	public void setSol(String s) {
		sol += s;
	}
	public String getSol() {

		return Integer.toString(sol.length()) + sol;
	}
	public void addBox(int x, int y) {
		b.addBox(x, y);
	}

	public void initial(int x, int y) {
		X=x;
		Y=y;
	}
}
