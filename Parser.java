import java.io.*;
import java.util.*;
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

class Parser extends DefaultHandler{
	ArrayList<Publication> list = new ArrayList<Publication>();
	String title, journal, volume, url, pages, key, type;
	int year;
	ArrayList<String> authors = new ArrayList<String>();
	String byYear;

	boolean iPub = false, iAuthor = false, iTitle = false, iPages = false;
	boolean iYear = false, iVolume = false, iJournal = false, iURL = false;

	public void startDocument()	throws SAXException{
		System.out.println("TOP");
	}

	public void startElement(String uri, String localName, String qName, Attributes attr) throws SAXException{
		if(qName.equalsIgnoreCase("article") ||	qName.equalsIgnoreCase("inproceedings")||
			qName.equalsIgnoreCase("proceedings")||	qName.equalsIgnoreCase("book")||
			qName.equalsIgnoreCase("mastersthesis")||	qName.equalsIgnoreCase("phdthesis")){

			type = qName;
			title = ""; journal = ""; url = "";
			pages= "";
			iPub = true;
			key = attr.getValue("key");
			authors = new ArrayList<String>();
			// System.out.println(key);
		}
		else if(qName.equalsIgnoreCase("author") || qName.equalsIgnoreCase("editor")){
			iAuthor = true;
		}
		else if(qName.equalsIgnoreCase("title")){
			iTitle = true;
		}
		else if(qName.equalsIgnoreCase("pages")){
			iPages = true;
		}
		else if(qName.equalsIgnoreCase("year")){
			iYear = true;
		}
		else if(qName.equalsIgnoreCase("volume")){
			iVolume = true;
		}
		else if(qName.equalsIgnoreCase("journal") || qName.equalsIgnoreCase("booktitle")){
			iJournal = true;
		}
		else if(qName.equalsIgnoreCase("url")){
			iURL = true;
		}
	}

	public void characters(char ch[], int start, int length) throws SAXException{
		String t = new String(ch, start, length);
		// System.out.println(t);
		if(iAuthor){
			// System.out.print(this.authors);
			this.authors.add(t);
			iAuthor = false;
		}
		else if(iTitle){
			this.title = t;
			iTitle = false;
		}
		else if(iPages){
			this.pages = t;
			iPages = false;
		}
		else if(iYear){
			this.year = Integer.parseInt(t);
			iYear = false;
		}
		else if(iVolume){
			this.volume = t;
			iVolume = false;
		}
		else if(iJournal){
			this.journal = t;
			iJournal = false;
		}
		else if(iURL){
			this.url = t;
			iURL = false;
		}
	}

	public void endElement(String uri, String localName, String qName) throws SAXException{
		if(qName.equalsIgnoreCase("article") ||	qName.equalsIgnoreCase("inproceedings")||
			qName.equalsIgnoreCase("proceedings")||	qName.equalsIgnoreCase("book")||
			qName.equalsIgnoreCase("mastersthesis")||	qName.equalsIgnoreCase("phdthesis")){

			iPub = false;
			if(year == 1956){
				Publication pub = new Publication(type, authors, title, pages, year, volume, journal, url, key);
				list.add(pub);
				authors = null;
			}
		}
	}

	public void endDocument() throws SAXException{
		System.out.println("BOTTOM");
	}

	public static void main(String[] args) throws Exception{
		System.setProperty("jdk.xml.entityExpansionLimit", "0");

		SAXParserFactory spf = SAXParserFactory.newInstance();
	    spf.setNamespaceAware(true);
	    SAXParser saxParser = spf.newSAXParser();
		XMLReader xmlReader = saxParser.getXMLReader();
		Parser parser = new Parser();
		xmlReader.setContentHandler(parser);
		xmlReader.parse("dblp.xml");
		// saxParser.parse(new File("dblp.xml"), parser);
		System.out.println(parser.getList().size());
		int j=0;
		for(Publication p: parser.getList()){
			if(!"article".equals(p.getType())){
				System.out.println(p);
				j += 1;
				if(j == 10){
					break;
				}
			}
		}
	}	

	public ArrayList<Publication> getList(){
		return list;
	}
}