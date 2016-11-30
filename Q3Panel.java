/*
Dattatreya Mohapatra, 2015021
Harshit Sharma, 2015036
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

class Q3Panel extends QPanel{
	private JLabel authorTag, yearTag;
	private JTextField author, year;

	private static Q3Panel instance = null;
	
	private Q3Panel(DBLPGui qe){
		super(qe);
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		authorTag = new JLabel("Author: ");
		yearTag = new JLabel("Year: ");
		// noPubsTag.setMaximumSize(noPubsTag.getPreferredSize());
		author = new JTextField(50);
		year = new JTextField(4);
		// noPubs.setMaximumSize(noPubs.getPreferredSize());
		add(authorTag);
		add(author);
		add(yearTag);
		add(year);
	}

	public static Q3Panel getInstance(DBLPGui qe){
		if(instance == null){
			instance =  new Q3Panel(qe);
		}
		return instance;
	}

	public void search() throws Exception{
		QueryEngine qe = getEngine().getQueryEngine();
		RPanel r = getEngine().getRPanel();
		if("".equalsIgnoreCase(author.getText().trim()) || "".equalsIgnoreCase(year.getText().trim())){
			r.getOutBox().setText("All fields are compulsary!");			
		}
		else{
			try{
				int n = Integer.parseInt(year.getText().trim());
				double answer = qe.predictPublications(author.getText().trim(), n);
				r.getOutBox().setText("Prediction: "+answer);
				// qe.authorByPublications(Integer.parseInt(noPubs.getText().trim()));
				r.setYearList(qe.getPubsPerYear());
			}
			catch(NumberFormatException nu){
				r.getOutBox().setText("Invalid parameter in <"+yearTag+">!");
			}
		}
	}
}