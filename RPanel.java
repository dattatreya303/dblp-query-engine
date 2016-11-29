import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.util.*;

class RPanel extends JPanel{
	private JLabel outBox;
	private JTable table;
	private TableModel tmodel;
	private ArrayList<Publication> pubs;

	public RPanel(ArrayList<Publication> pubs){
		setList(pubs);
		outBox = new JLabel();
		tmodel = new TableModel(pubs);
		table = new JTable();
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				table.setModel(tmodel);
			}
		});
	}

	public void setList(ArrayList<Publication> pubs){
		this.pubs = pubs;
	}
}