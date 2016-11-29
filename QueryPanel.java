import java.awt.*;
import javax.swing.*;

class QueryPanel extends JPanel{

	private JComboBox<String> queryBox;
	private JComboBox<String> searchBy;
	private JTextField nameTitle, sinceYear, customRangeLower, customRangeUpper;
	private JLabel nameTitleTag, sinceYearTag, customRangeTag;
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
		searchBy = new JComboBox<String>();
		searchBy.addItem("Author");
		searchBy.addItem("Title");
		add(queryBox);
		add(searchBy);
		add(nameTitleTag);
		add(nameTitle);
		add(sinceYearTag);
		add(sinceYear);
		add(customRangeTag);
		add(customRangeLower);
		add(customRangeUpper);
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
