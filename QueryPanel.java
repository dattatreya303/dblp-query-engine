/*
Dattatreya Mohapatra, 2015021
Harshit Sharma, 2015036
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

class QueryPanel extends JPanel{

	private JComboBox<String> queryBox;
	private JPanel basePanel;
	private JButton search;
	private QPanel currentQPanel;
	private DBLPGui qe;
	
	QueryPanel(DBLPGui aqe) throws Exception{
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		qe = aqe;
		basePanel = new JPanel(); search = new JButton();

		queryBox = new JComboBox<String>();
		queryBox.addItem("Query1");	queryBox.addItem("Query2");	queryBox.addItem("Query3");
		queryBox.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				basePanel.removeAll();
				JComboBox m = (JComboBox)e.getSource();
				String selQuery = (String)m.getSelectedItem();
				if(selQuery.equals("Query1")){
					basePanel.add(Q1Panel.getInstance(qe));
					basePanel.revalidate();
					currentQPanel = Q1Panel.getInstance(qe);
				}
				else if(selQuery.equals("Query2")){
					basePanel.add(Q2Panel.getInstance(qe));
					basePanel.revalidate();
					currentQPanel = Q2Panel.getInstance(qe);
				}
				else{
					basePanel.add(Q3Panel.getInstance(qe));
					basePanel.revalidate();
					currentQPanel = Q3Panel.getInstance(qe);
				}
			}
		});
		queryBox.setSelectedIndex(0);
		
		search = new JButton("Search");
		search.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try{
					currentQPanel.search();
				}
				catch(Exception ex){
					// do nothing
				}
			}
		});
		JPanel sPanel = new JPanel();
		sPanel.add(search);

		add(queryBox);
		add(basePanel);
		add(sPanel);
	}
}