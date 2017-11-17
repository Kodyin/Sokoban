package solver;

import map.Box;
import map.Boxes;

public class Status {
	Boxes boxPos;
	int posx, posy;
	int step;
	public Status(Boxes a, int x, int y) {
		boxPos = a;
		posx = x;
		posy = y;
	}
	//Compare
	public boolean equals(Object o){  
        if (this==o) return true;  
        if (!(o instanceof Status)) return false;  
        final Status other = (Status) o;        
        if(this.boxPos.getBoxList().equals(other.getBoxes().getBoxList())  && this.posx==other.getx() && this.posy==other.gety())  
            return true;  
        else  
            return false;   
    }  
	//Status String ID
	public String getID() {
		String res = "";
		for(Box i: boxPos.getBoxList()) {
			res += Integer.toString(i.getx()) + ',' + Integer.toString(i.gety())+':';
		}
		res += 'x' + Integer.toString(posx)+'y'+ Integer.toString(posy);
		return res;
	}
	public int getStep() {
		return step;
	}
	public Boxes getBoxes() {
		return boxPos;
	}
	public int getx() {
		return posx;
	}
	public int gety() {
		return posy;
	}
}
