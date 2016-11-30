/*
Dattatreya Mohapatra, 2015021
Harshit Sharma, 2015036
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;
import javax.swing.table.*;
import java.util.*;

class YearTableModel extends AbstractTableModel{
	ArrayList<Integer> years;

	public YearTableModel(ArrayList<Integer> years){
		this.years = years;
	}

	public int getColumnCount() {
        return 2;
    }

    public int getRowCount() {
        return years.size();
    }

    public String getColumnName(int col) {
        String s = "";
        switch(col){
        	case 0:	s = "SNo"; break;
        	case 1:	s = "Publications"; break;
        }
        return s;
    }

    public Object getValueAt(int row, int col) {
        Integer p = years.get(row);
        Object o = new Object();
        switch(col){
        	case 0:	o = row+1; break;
        	case 1:	o = years.get(row); break;
        }
        return o;
    }
}