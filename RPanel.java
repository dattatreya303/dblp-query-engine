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
	private TableModel cmodel;
	private ArrayList<Publication> pubs;
	private ArrayList<String> auts;
	private DBLPGui dblpGUI;
	private JButton nextB, prevB;
	int cpage = 0;

	public RPanel(DBLPGui adblpGUI){
		//setPubList(pubs);
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.dblpGUI = adblpGUI;
		outBox = new JLabel("No. of records: "+0);
		//ptmodel = new PublicationTableModel(pubs);
		atmodel = new AuthorTableModel(auts);
		table = new JTable();
		
		prevB = new JButton("Previous");
		prevB.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				cpage--;
				showPage(cpage);
			}
		});
		nextB = new JButton("Next");
		nextB.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				cpage++;
				showPage(cpage);
			}
		});

		add(outBox);
		add(table);
		add(prevB);
		add(nextB);
	}

	public void setPubList(ArrayList<Publication> pubs){
		this.pubs = pubs;
		ptmodel = new PublicationTableModel(pubs);
		cmodel = ptmodel;
		outBox.setText("No. of records: "+pubs.size());
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				table.setModel(ptmodel);
				cpage = 0;
				showPage(0);
			}
		});
	}

	public void setAuthList(ArrayList<String> auts){
		this.auts = auts;
		atmodel = new AuthorTableModel(auts);
		cmodel = atmodel;
		outBox.setText("No. of records: "+auts.size());
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				table.setModel(atmodel);
				cpage = 0;
				showPage(0);
			}
		});
	}

	public void showPage(int cpage){
		RowFilter<Object, Object> fft = new RowFilter<Object, Object>(){
			public boolean include(Entry entry){
				Integer sn = (Integer)entry.getValue(0);
				return ((20*cpage+1)<=sn && sn<=(20*(cpage+1)));
			}
		};
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(cmodel);
		sorter.setRowFilter(fft);
		table.setRowSorter(sorter);
	}
}