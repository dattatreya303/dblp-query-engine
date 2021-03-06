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

class AuthorTableModel extends AbstractTableModel{
	ArrayList<String> authors;

	public AuthorTableModel(ArrayList<String> authors){
		this.authors = authors;
	}

	public int getColumnCount() {
        return 2;
    }

    public int getRowCount() {
        return authors.size();
    }

    public String getColumnName(int col) {
        String s = "";
        switch(col){
        	case 0:	s = "SNo"; break;
        	case 1:	s = "Author"; break;
        }
        return s;
    }

    public Object getValueAt(int row, int col) {
        String p = authors.get(row);
        Object o = new Object();
        switch(col){
        	case 0:	o = row+1; break;
        	case 1:	o = p; break;
        }
        return o;
    }
}