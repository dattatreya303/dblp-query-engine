import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

class DBLPGui{
	private JFrame topFrame;
	private QueryPanel queryPanel;
	private RPanel resultPanel;
	private QueryEngine qe;

	public QueryEngine getQueryEngine(){
		return qe;
	}

	public RPanel getRPanel(){
		return resultPanel;
	}

	public DBLPGui(QueryEngine qe) throws Exception{
		this.qe = qe;
		prepareGUI();
		startApp();
	}

	public void prepareGUI() throws Exception{
		queryPanel = new QueryPanel(this);
		resultPanel = new RPanel(this);
	}

	public void startApp(){
		topFrame = new JFrame("DBLP Database");
		topFrame.setLayout(new BorderLayout());
		//topFrame.setSize(topFrame.getMaximumSize());
		topFrame.setSize(500, 500);
		topFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		topFrame.setVisible(true);
		topFrame.add(queryPanel, BorderLayout.WEST);
		topFrame.add(resultPanel, BorderLayout.CENTER);
		topFrame.revalidate();
	}

	public static void main(String[] args) throws Exception{
		QueryEngine qe1 = new QueryEngine();
		DBLPGui gui = new DBLPGui(qe1);
	}
}