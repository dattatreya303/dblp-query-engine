import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

class DBLPGui{
	private JFrame topFrame;
	private QueryPanel queryPanel;
	private RPanel resultPanel;
	private QueryEngine qe;

	public DBLPGui(QueryEngine qe){
		this.qe = qe;
		prepareGUI();
		startApp();
	}

	public void prepareGUI(){
		queryPanel = new QueryPanel();
		resultPanel = new RPanel();
	}

	public void startApp(){
		topFrame = new JFrame("DBLP Database");
		topFrame.setLayout(new BorderLayout());
		topFrame.setSize(topFrame.getMaximumSize());
		topFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		topFrame.setVisible(true);
		topFrame.add(queryPanel, BorderLayout.WEST);
		topFrame.add(resultPanel, BorderLayout.CENTER);
		topFrame.revalidate();
	}

	public static void main(String[] args){
		QueryEngine qe1 = new QueryEngine();
		DBLPGui gui = new DBLPGui(qe1);
	}
}