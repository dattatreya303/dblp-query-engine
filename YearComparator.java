import java.util.*;

class YearComparator implements Comparator{
	public int compare(Object o1, Object o2){
		Publication p1 = (Publication)o1;
		Publication p2 = (Publication)o2;
		return p1.getYear()-p2.getYear();
	}
}
