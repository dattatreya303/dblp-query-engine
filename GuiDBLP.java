import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

class GuiDBLP{
	private JFrame myFrame;
	private QueryPanel queryPanel;
	private JPanel resultPanel;

	GuiDBLP(){
		myFrame = new JFrame();
		myFrame.add(new JComponent(){
			public static final int MESSAGE_X = 350;
			public static final int MESSAGE_Y = 75;
			private static final int DEFAULT_WIDTH = 900;
			private static final int DEFAULT_HEIGHT = 200;

			public void paintComponent(Graphics g){
				g.setFont(new Font("Serif", Font.BOLD, 36));
				g.drawString("DBLP Query Engine", MESSAGE_X, MESSAGE_Y);
			}

			public Dimension getPreferredSize(){
				return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
			}
		}, BorderLayout.NORTH);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		queryPanel = new QueryPanel();
		resultPanel = new JPanel(){
			public static final int MESSAGE_X = 85;
			public static final int MESSAGE_Y = 150;
			private static final int DEFAULT_WIDTH = 550;
			private static final int DEFAULT_HEIGHT = 500;

			public void paintComponent(Graphics g){
				super.paintComponent(g);
				g.drawString("Result Panel", MESSAGE_X, MESSAGE_Y);
			}

			public Dimension getPreferredSize(){
				return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
			}
		};
		resultPanel.setBorder(BorderFactory.createEtchedBorder());
		resultPanel.setBackground(new Color(250, 255, 210));
		queryPanel.setBackground(new Color(210, 250, 252));
		myFrame.add(queryPanel, BorderLayout.WEST);
		myFrame.add(resultPanel, BorderLayout.EAST);
		myFrame.getContentPane().setBackground(new Color(253, 210, 250));
		myFrame.setVisible(true);
//		myFrame.pack();
		myFrame.setBounds(40, 40, 1000, 600);
	}

	public static void main(String[] args){
		GuiDBLP gugu = new GuiDBLP();
	}
}
