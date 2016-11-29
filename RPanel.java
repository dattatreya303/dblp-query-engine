import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.util.*;

class RPanel extends JPanel{
	private JLabel outBox;
	private JTable table;
	private PublicationTableModel ptmodel;
	private AuthorTableModel atmodel;
	private ArrayList<Publication> pubs;
	private ArrayList<String> auts;

	public RPanel(ArrayList<Publication> pubs){
		setPubList(pubs);
		outBox = new JLabel("No. of records: "+pubs.size());
		ptmodel = new PublicationTableModel(pubs);
		atmodel = new AuthorTableModel(auts);
		table = new JTable();
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				table.setModel(ptmodel);
			}
		});

		add(outBox);
		add(table);
	}

	public void setPubList(ArrayList<Publication> pubs){
		this.pubs = pubs;
	}

	public void setAuthList(ArrayList<String> auts){
		this.auts = auts;
	}
}