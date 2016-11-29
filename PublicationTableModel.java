import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;
import javax.swing.table.*;

class PublicationTableModel extends AbstractTableModel{
	ArrayList<Publication> pubs;
	
	public PublicationTableModel(ArrayList<Publication> pubs){
		this.pubs = pubs;
	}

	public int getColumnCount() {
        return 8;
    }

    public int getRowCount() {
        return pubs.size();
    }

    public String getColumnName(int col){
        String s  = "";
        switch(col){
        	case 0:	s = "SNo";
        	case 1:	s = "Authors";
        	case 2:	s = "Title";
        	case 3:	s = "Pages";
        	case 4:	s = "Year";
        	case 5:	s = "Volume";
        	case 6:	s = "Journal/Booktitle";
        	case 7:	s = "URL";
        }
        return s;
    }

    public Object getValueAt(int row, int col) {
        Publication p = pubs.get(row);
        Object o = new Object();
        switch(col){
        	case 0:	o = row+1;
        	case 1:	o = p.getAuthors();
        	case 2:	o = p.getTitle();
        	case 3:	o = p.getPages();
        	case 4:	o = p.getYear();
        	case 5:	o = p.getVolume();
        	case 6:	o = p.getJournal();
        	case 7:	o = p.getURL(); 
        }
        return o;
    }
}