/*
Dattatreya Mohapatra, 2015021
Harshit Sharma, 2015036
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

class Q2Panel extends QPanel{
	private JLabel noPubsTag;
	private JTextField noPubs;

	private static Q2Panel instance = null;
	
	private Q2Panel(DBLPGui qe){
		super(qe);
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		noPubsTag = new JLabel("No. of publications: ");
		// noPubsTag.setMaximumSize(noPubsTag.getPreferredSize());
		noPubs = new JTextField(20);
		// noPubs.setMaximumSize(noPubs.getPreferredSize());
		add(noPubsTag);
		add(noPubs);
	}

	public static Q2Panel getInstance(DBLPGui qe){
		if(instance == null){
			instance =  new Q2Panel(qe);
		}
		return instance;
	}

	public void search(){
		QueryEngine qe = getEngine().getQueryEngine();
		RPanel r = getEngine().getRPanel();
		if("".equalsIgnoreCase(noPubs.getText().trim())){
			r.getOutBox().setText("All fields are compulsary!");
		}
		else{
			try{
				int n = Integer.parseInt(noPubs.getText().trim());
				if(n < 0){
					throw new NumberFormatException();
				}
				qe.authorByPublications(n);
				r.setAuthList(qe.getCurrentAuthors());
			}
			catch(NumberFormatException nu){
				r.getOutBox().setText("Invalid parameter in <No. of publications>!");
			}
		}
	}
}