package map;

public class Box implements Cloneable{
	int x, y;
	
	public Box(int i, int j) {
		x = i;
		y = j;
	}
	public int getx() {
		return x;
	}
	public int gety() {
		return y;
	}
	public void pushRight() {
		y++;
	}
	
	public void pushLeft() {
		y--;
	}
	public void pushUp() {
		x--;
	}
	public void pushDown() {
		x++;
	}
	@Override  
    public Object clone() throws CloneNotSupportedException  
    {  
		 Object o = null;
	        try{
	            o = (Box)super.clone();
	        }catch(CloneNotSupportedException e){
	            e.printStackTrace();
	        }
	        return o;  
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
