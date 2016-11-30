/*
Dattatreya Mohapatra, 2015021
Harshit Sharma, 2015036
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

class Q1Panel extends QPanel{
	private JTextField nameTitle, customRangeLower, customRangeUpper;
	private ButtonGroup sortBy;
	private JRadioButton sortByYear, revSortByYear, sortByRelevance;
	private JComboBox<String> searchBy;
	private String oldTitl = "";
	private int oldCustomRangeLower = 0, oldCustomRangeUpper = 9999;

	private static Q1Panel instance = null;

	private Q1Panel(DBLPGui qe){
		super(qe);
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		JLabel nameTitleTag, sinceYearTag, customRangeTag;
		
		searchBy = new JComboBox<String>();
		searchBy.addItem("Author");
		searchBy.addItem("Title");
		
		nameTitleTag = new JLabel("Name/Title tags: ");
		sinceYearTag = new JLabel("Since year: ");
		customRangeTag = new JLabel("Custom range: ");
		nameTitle = new JTextField(20);
		customRangeLower = new JTextField(4);
		customRangeLower.setText("0");
		customRangeUpper = new JTextField(4);
		customRangeUpper.setText("9999");
		
		JPanel temp = new JPanel();
		temp.setLayout(new BoxLayout(temp, BoxLayout.PAGE_AXIS));
		sortBy = new ButtonGroup();
		sortByYear = new JRadioButton("Sort by year", false);
		sortByRelevance = new JRadioButton("Sort by relevance", true);
		revSortByYear = new JRadioButton("Sort by year (reverse)", false);
		sortBy.add(sortByRelevance);
		sortBy.add(sortByYear);
		sortBy.add(revSortByYear);
		temp.add(sortByRelevance);
		temp.add(sortByYear);
		temp.add(revSortByYear);

		add(searchBy);
		add(nameTitleTag);
		add(nameTitle);
		add(customRangeTag);
		add(customRangeLower);
		add(customRangeUpper);
		add(temp);
	}

	public static Q1Panel getInstance(DBLPGui qe){
		if(instance == null){
			instance = new Q1Panel(qe);
		}
		return instance;
	}

	public void search() throws Exception{
		QueryEngine qe = getEngine().getQueryEngine();
		RPanel r = getEngine().getRPanel();
		if(searchBy.getSelectedItem().equals("Title")){
			qe.publicationsByTitle(nameTitle.getText().split("\\s+"));
		}else if(searchBy.getSelectedItem().equals("Author")){
			qe.publicationsByAuthor(nameTitle.getText());
		}
		if(sortByRelevance.isSelected()){
			qe.sortByRelevance();
		}else if(sortByYear.isSelected()){
			qe.sortByYear(1);
		}else{
			qe.sortByYear(0);
		}
		r.setPubList(qe.betweenYears(Integer.parseInt(customRangeLower.getText()), Integer.parseInt(customRangeUpper.getText())));
	}
}

/*
1. sorting involves parsing, remove that
2. garbage values in query2
*/