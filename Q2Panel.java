import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

class Q2Panel extends QPanel{
	private JLabel noPubsTag;
	private JTextField noPubs;

	private static Q2Panel instance = null;
	
	private Q2Panel(){
		noPubsTag = new JLabel("No. of publications: ");
		noPubsTag.setMaximumSize(noPubsTag.getPreferredSize());
		noPubs = new JTextField(20);
		noPubs.setMaximumSize(noPubs.getPreferredSize());
		add(noPubsTag);
		add(noPubs);
	}

	public static Q2Panel getInstance(){
		if(instance == null){
			instance =  new Q2Panel();
		}
		return instance;
	}

	public void search(){
		QueryEngine qe = getEngine();
	}
}