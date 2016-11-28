import java.awt.*;
import javax.swing.*;

class GuiDBLP{
	private JFrame myFrame;
	private JPanel queryPanel;
	private JPanel resultPanel;

	GuiDBLP(){
		myFrame = new JFrame();
		myFrame.add(new JComponent(){
			public static final int MESSAGE_X = 75;
			public static final int MESSAGE_Y = 100;
			private static final int DEFAULT_WIDTH = 300;
			private static final int DEFAULT_HEIGHT = 200;

			public void paintComponent(Graphics g){
				g.drawString("DBLP Query Engine", MESSAGE_X, MESSAGE_Y);
			}

			public Dimension getPreferredSize(){
				return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
			}
		}, BorderLayout.NORTH);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		myFrame.setVisible(true);
		queryPanel = new JPanel();
		resultPanel = new JPanel();
		myFrame.add(queryPanel, BorderLayout.WEST);
		myFrame.add(resultPanel, BorderLayout.EAST);
		myFrame.pack();
	}

	public static void main(String[] args){
		GuiDBLP gugu = new GuiDBLP();
	}
}
