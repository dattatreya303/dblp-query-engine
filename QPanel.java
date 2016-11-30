import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

abstract class QPanel extends JPanel{
	DBLPGui qe;
	public QPanel(DBLPGui qe){
		this.qe = qe;
	}
	public DBLPGui getEngine(){
		return qe;
	}
	public abstract void search() throws Exception;
}