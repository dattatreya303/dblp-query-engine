import java.awt.*;
import javax.swing.*;

class GuiDBLP{
	private JFrame myFrame;
	private JPanel queryPanel;
	private JPanel resultPanel;

	GuiDBLP(){
		myFrame = new JFrame();
		myFrame.add(new JComponent(){
			public static final int MESSAGE_X = 500;
			public static final int MESSAGE_Y = 75;
			private static final int DEFAULT_WIDTH = 900;
			private static final int DEFAULT_HEIGHT = 200;

			public void paintComponent(Graphics g){
				g.drawString("DBLP Query Engine", MESSAGE_X, MESSAGE_Y);
			}

			public Dimension getPreferredSize(){
				return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
			}
		}, BorderLayout.NORTH);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		queryPanel = new JPanel(){
			public static final int MESSAGE_X = 85;
			public static final int MESSAGE_Y = 150;
			private static final int DEFAULT_WIDTH = 450;
			private static final int DEFAULT_HEIGHT = 600;

			public void paintComponent(Graphics g){
				super.paintComponent(g);
				g.drawString("Query Panel", MESSAGE_X, MESSAGE_Y);
			}

			public Dimension getPreferredSize(){
				return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
			}
		};
		resultPanel = new JPanel(){
			public static final int MESSAGE_X = 85;
			public static final int MESSAGE_Y = 150;
			private static final int DEFAULT_WIDTH = 450;
			private static final int DEFAULT_HEIGHT = 600;

			public void paintComponent(Graphics g){
				super.paintComponent(g);
				g.drawString("Result Panel", MESSAGE_X, MESSAGE_Y);
			}

			public Dimension getPreferredSize(){
				return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
			}
		};
		resultPanel.setBackground(new Color(0, 250, 0));
		queryPanel.setBackground(new Color(0, 0, 250));
		myFrame.add(queryPanel, BorderLayout.WEST);
		myFrame.add(resultPanel, BorderLayout.EAST);
		myFrame.setBackground(new Color(253, 0, 0));
		myFrame.setVisible(true);
//		myFrame.pack();
		myFrame.setBounds(40, 40, 1000, 800);
	}

	public static void main(String[] args){
		GuiDBLP gugu = new GuiDBLP();
	}
}
