package map;

import java.util.Comparator;

public class BoxComparator implements Comparator{
	public int compare(Object arg0, Object arg1) {
		Box b1=(Box)arg0;
        	Box b2=(Box)arg1;
        if(b1.x != b2.x)
            return b1.x>b2.x? 1:-1;
        else
            return b1.y>b2.y? 1:-1;
	}
}
