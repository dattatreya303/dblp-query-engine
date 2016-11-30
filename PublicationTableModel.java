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
        	case 0:	s = "SNo"; break;
        	case 1:	s = "Authors"; break;
        	case 2:	s = "Title"; break;
        	case 3:	s = "Pages"; break;
        	case 4:	s = "Year"; break;
        	case 5:	s = "Volume"; break;
        	case 6:	s = "Journal/Booktitle"; break;
        	case 7:	s = "URL"; break;
        }
        return s;
    }

    public Object getValueAt(int row, int col) {
        Publication p = pubs.get(row);
        Object o = new Object();
        switch(col){
        	case 0:	o = row+1; break;
        	case 1:	o = p.getAuthors(); break;
        	case 2:	o = p.getTitle(); break;
        	case 3:	o = p.getPages(); break;
        	case 4:	o = p.getYear(); break;
        	case 5:	o = p.getVolume(); break;
        	case 6:	o = p.getJournal(); break;
        	case 7:	o = p.getURL(); break; 
        }
        return o;
    }
}