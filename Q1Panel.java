/*
Dattatreya Mohapatra, 2015021
Harshit Sharma, 2015036
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

class Q1Panel extends QPanel{
	private JLabel nameTitleTag, sinceYearTag, customRangeTag;
	private JTextField nameTitle, customRangeLower, customRangeUpper;
	private ButtonGroup sortBy;
	private JRadioButton sortByRelevance;
	// private JRadioButton sortByYear, revSortByYear;
	private JComboBox<String> searchBy;
	// private String oldTitl = "";
	// private int oldCustomRangeLower = 0, oldCustomRangeUpper = 9999;

	private static Q1Panel instance = null;

	private Q1Panel(DBLPGui qe){
		super(qe);
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		searchBy = new JComboBox<String>();
		searchBy.addItem("Author");
		searchBy.addItem("Title");
		
		nameTitleTag = new JLabel("Name/Title tags: ");
		sinceYearTag = new JLabel("Since year: ");
		customRangeTag = new JLabel("Custom range: ");
		nameTitle = new JTextField(50);
		customRangeLower = new JTextField(4);
		customRangeLower.setText("0");
		customRangeUpper = new JTextField(4);
		customRangeUpper.setText("9999");
		
		JPanel temp = new JPanel();
		temp.setLayout(new BoxLayout(temp, BoxLayout.PAGE_AXIS));
		sortBy = new ButtonGroup();
		sortByYear = new JRadioButton("Sort by year", false);
		sortByRelevance = new JRadioButton("Sort by relevance", true);
		// revSortByYear = new JRadioButton("Sort by year (reverse)", false);
		sortBy.add(sortByRelevance);
		// sortBy.add(sortByYear);
		// sortBy.add(revSortByYear);
		temp.add(sortByRelevance);
		// temp.add(sortByYear);
		// temp.add(revSortByYear);

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
		if("".equalsIgnoreCase(nameTitle.getText().trim().trim()) || "".equalsIgnoreCase(customRangeLower.getText().trim()) || "".equalsIgnoreCase(customRangeUpper.getText().trim())){
			r.getOutBox().setText("All fields are compulsary!");
		}
		else{
			try{
				int ly, hy;
				ly = Integer.parseInt(customRangeLower.getText().trim());
				hy = Integer.parseInt(customRangeUpper.getText().trim());
				if(ly < 0 || hy < 0 || ly > hy){
					raise NumberFormatException;
				}
				if(searchBy.getSelectedItem().equals("Title")){
					qe.publicationsByTitle(nameTitle.getText().trim().split("\\s+"));
				}else if(searchBy.getSelectedItem().equals("Author")){
					qe.publicationsByAuthor(nameTitle.getText().trim());
				}
				if(sortByRelevance.isSelected()){
					qe.sortByRelevance();
				}else if(sortByYear.isSelected()){
					qe.sortByYear(1);
				}else{
					qe.sortByYear(0);
				}
				r.setPubList(qe.betweenYears(ly, hy));
			}
			catch(NumberFormatException nu){
				r.getOutBox().setText("Invalid parameter in <customRange>!");
			}
		}
	}
}

/*
1. sorting involves parsing, remove that
*/