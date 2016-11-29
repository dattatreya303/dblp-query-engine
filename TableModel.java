import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;
import javax.swing.table.*;

class TableModel extends AbstractTableModel{
	ArrayList<Publication> pubs;
	
	public TableModel(ArrayList<Publication> pubs){
		this.pubs = pubs;
	}

	public int getColumnCount() {
        return 8;
    }

    public int getRowCount() {
        return pubs.size();
    }

    public String getColumnName(int col) {
        switch(col){
        	case 0:	return "SNo"; break;
        	case 1:	return "Authors"; break;
        	case 2:	return "Title"; break;
        	case 3:	return "Pages"; break;
        	case 4:	return "Year"; break;
        	case 5:	return "Volume"; break;
        	case 6:	return "Journal/Booktitle"; break;
        	case 7:	return "URL"; break;
        }
    }

    public Object getValueAt(int row, int col) {
        Publication p = pubs.get(row);
        switch(col){
        	case 0:	return row+1; break;
        	case 1:	return p.getAuthors(); break;
        	case 2:	return p.getTitle(); break;
        	case 3:	return p.getPages(); break;
        	case 4:	return p.getYear(); break;
        	case 5:	return p.getVolume(); break;
        	case 6:	return p.getJournal(); break;
        	case 7:	return p.getURL(); break; 
        }
    }
}