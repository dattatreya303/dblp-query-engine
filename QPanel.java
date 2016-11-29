import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

abstract class QPanel extends JPanel{
	QueryEngine qe;
	public QPanel(QueryEngine qe){
		this.qe = qe;
	}
	public QueryEngine getEngine(){
		return qe;
	}
	public abstract void search() throws Exception;
}