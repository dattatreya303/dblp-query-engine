import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

class Q1Panel extends QPanel{
	private JTextField nameTitle, sinceYear, customRangeLower, customRangeUpper;
	private ButtonGroup sortBy;
	private JRadioButton sortByYear, sortByRelevance;
	private JComboBox<String> searchBy;
	
	private static Q1Panel instance = null;

	private Q1Panel(DBLPGui qe){
		super(qe);
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		JLabel nameTitleTag, sinceYearTag, customRangeTag;
		
		searchBy = new JComboBox<String>();
		searchBy.addItem("Author");
		searchBy.addItem("Title");
		// searchBy.setMaximumSize(searchBy.getPreferredSize());

		nameTitleTag = new JLabel("Name/Title tags: ");
		sinceYearTag = new JLabel("Since year: ");
		customRangeTag = new JLabel("Custom range: ");
		nameTitle = new JTextField(20);
		// nameTitle.setMaximumSize(nameTitle.getPreferredSize());
		sinceYear = new JTextField(4);
		// sinceYear.setMaximumSize(sinceYear.getPreferredSize());
		customRangeLower = new JTextField(4);
		// customRangeLower.setMaximumSize(customRangeLower.getPreferredSize());
		customRangeUpper = new JTextField(4);
		// customRangeUpper.setMaximumSize(customRangeUpper.getPreferredSize());

		JPanel temp = new JPanel();
		temp.setLayout(new BoxLayout(temp, BoxLayout.PAGE_AXIS));
		sortBy = new ButtonGroup();
		sortByYear = new JRadioButton("Sort by year", false);
		sortByRelevance = new JRadioButton("Sort by relevance", true);
		sortBy.add(sortByRelevance);
		sortBy.add(sortByYear);
		temp.add(sortByRelevance);
		temp.add(sortByYear);

		add(searchBy);
		add(nameTitleTag);
		add(nameTitle);
		add(sinceYearTag);
		add(sinceYear);
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
			r.setPubList(qe.getCurrentPublications());
		}else{
			qe.publicationsByAuthor(nameTitle.getText());
			r.setPubList(qe.getCurrentPublications());
		}
		if(sortByRelevance.isSelected()){
			qe.sortByRelevance();
		}else{
			// qe.sortByYear(1);
		}
	}
}

