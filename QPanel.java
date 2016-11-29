abstract class QPanel extends JPanel{
	QueryEngine qe;
	public QPanel(QueryEngine qe){
		this.qe = qe;
	}
	public QueryEngine getEngine(){
		return qe;
	}
	public abstract void search();
}