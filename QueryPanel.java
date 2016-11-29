import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

class QueryPanel extends JPanel{

	private JComboBox<String> queryBox;
	private JPanel basePanel;
	private JButton search;
	private QPanel currentQPanel;
	
	// public static final int MESSAGE_X = 85;
	// public static final int MESSAGE_Y = 150;
	// private static final int DEFAULT_WIDTH = 350;
	// private static final int DEFAULT_HEIGHT = 500;
	
	QueryPanel(){
		basePanel = new JPanel();

		queryBox = new JComboBox<String>();
		queryBox.addItem("Query1");
		queryBox.addItem("Query2");
		queryBox.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JComboBox m = (JComboBox)e.getSource();
				String selQuery = (String)m.getSelectedItem();
				// System.out.println(selQuery);
				if(selQuery.equals("Query1")){
					basePanel.removeAll();
					basePanel.add(Q1Panel.getInstance());
					basePanel.revalidate();
					currentQPanel = Q1Panel.getInstance();
				}
				else if(selQuery.equals("Query2")){
					// System.out.println("Query2");
					basePanel.removeAll();
					basePanel.add(Q2Panel.getInstance());
					basePanel.revalidate();
					currentQPanel = Q2Panel.getInstance();
				}
			}
		});
		queryBox.setMaximumSize(queryBox.getPreferredSize());

		search = new JButton();
		search.addActionListener(new ActionListener(){
			currentQPanel.search();
		});

		add(queryBox);
		add(basePanel);

	}
}

// 		search = new JButton("Search");
// 		add(queryBox);
// 		add(searchBy);
// 		add(nameTitleTag);
// 		add(nameTitle);
// 		add(sinceYearTag);
// 		add(sinceYear);
// 		add(customRangeTag);
// 		add(customRangeLower);
// 		add(customRangeUpper);
// 		add(sortByRelevance);
// 		add(sortByYear);
// 		add(search);
// 		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));