package solver;

import java.util.Comparator;

public class StatusComp implements Comparator<Object>{
	public int compare(Object o1, Object o2)
    {
        Status a1 = (Status)o1;
        Status a2 = (Status)o2;
        return Integer.compare(a1.getStep(), a2.getStep());
    }
}
