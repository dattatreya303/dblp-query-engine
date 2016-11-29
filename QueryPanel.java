import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

class QueryPanel extends JPanel{

	private JComboBox<String> queryBox, searchBy;
	private JTextField nameTitle, sinceYear, customRangeLower, customRangeUpper;
	private JLabel nameTitleTag, sinceYearTag, customRangeTag;
	private ButtonGroup sortBy;
	private JRadioButton sortByYear, sortByRelevance;
	private JButton search;
	public static final int MESSAGE_X = 85;
	public static final int MESSAGE_Y = 150;
	private static final int DEFAULT_WIDTH = 350;
	private static final int DEFAULT_HEIGHT = 500;
	
	QueryPanel(){
		nameTitleTag = new JLabel("Name/Title tags :");
		sinceYearTag = new JLabel("Since year :");
		customRangeTag = new JLabel("Custom range :");
		nameTitle = new JTextField(20);
		sinceYear = new JTextField(4);
		customRangeLower = new JTextField(4);
		customRangeUpper = new JTextField(4);
		queryBox = new JComboBox<String>();
		queryBox.addItem("Query1");
		queryBox.addItem("Query2");
		queryBox.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JComboBox m = (JComboBox)e.getSource();
				String selQuery = (String)m.getSelectedItem();
				// System.out.println(selQuery);
				if(selQuery.equals("Query1")){
					
				}
				else if(selQuery.equals("Query2"){
					
				}
			}
		});
		searchBy = new JComboBox<String>();
		searchBy.addItem("Author");
		searchBy.addItem("Title");
		sortBy = new ButtonGroup();
		sortByYear = new JRadioButton("Sort by year", false);
		sortByRelevance = new JRadioButton("Sort by relevance", true);
		sortBy.add(sortByRelevance);
		sortBy.add(sortByYear);
		search = new JButton("Search");
		add(queryBox);
		add(searchBy);
		add(nameTitleTag);
		add(nameTitle);
		add(sinceYearTag);
		add(sinceYear);
		add(customRangeTag);
		add(customRangeLower);
		add(customRangeUpper);
		add(sortByRelevance);
		add(sortByYear);
		add(search);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
	//	g.drawString("Query Panel", MESSAGE_X, MESSAGE_Y);
	}

	public Dimension getPreferredSize(){
		return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
}
