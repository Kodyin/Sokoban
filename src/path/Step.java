package path;

import map.Box;

public class Step {
	int x, y;
	int steps;
	String path;
	public Step(int i, int j, int s, String p) {
		x = i;
		y = j;
		steps = s;
		path = p;
	}
	public int getx() {
		return x;
	}
	public int gety() {
		return y;
	}
	public int getSteps() {
		return steps;
	}
	public String getPath() {
		return path;
	}
	
	public void goRight() {
		y++;
	}
	
	public void goLeft() {
		y--;
	}
	public void goUp() {
		x--;
	}
	public void goDown() {
		x++;
	}
	public String getID() {
		return Integer.toString(x) + '-' + Integer.toString(y);
	}
	
	public boolean equals(Object o){  
        if (this==o) return true;  
        if (!(o instanceof Box)) return false;  
        final Box other = (Box)o;  
          
        if(this.x == other.getx() && this.y==other.gety())  
            return true;  
        else  
            return false;  
          
    }  
}

