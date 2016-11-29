import java.io.*;
import java.util.*;
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

class PublicationsByAuthorParser extends DefaultHandler{
	HashMap<Publication, Integer> dict;
	String title, journal, volume, url, pages, key, type;
	int year, relevance;
	ArrayList<String> authors = new ArrayList<String>();
	Person byAuthor;

	boolean iPub = false, iAuthor = false, iTitle = false, iPages = false;
	boolean iYear = false, iVolume = false, iJournal = false, iURL = false;

	public PublicationsByAuthorParser(Person byAuthor){
		this.byAuthor = byAuthor;
	}

	public void startDocument()	throws SAXException{
		System.out.println("TOP");
		dict = new HashMap<Publication, Integer>();
		type = title = journal = url = pages = key = "";
		relevance = 0;
	}

	public void startElement(String uri, String localName, String qName, Attributes attr) throws SAXException{
		if(qName.equalsIgnoreCase("article") ||	qName.equalsIgnoreCase("inproceedings") ||
			qName.equalsIgnoreCase("proceedings") || qName.equalsIgnoreCase("book") ||
			qName.equalsIgnoreCase("mastersthesis") || qName.equalsIgnoreCase("phdthesis") ||
			qName.equalsIgnoreCase("incollection")){

			type = qName;
			iPub = true;
			key = attr.getValue("key");
			authors = new ArrayList<String>();
			// System.out.println(key);
		}
		else if((qName.equalsIgnoreCase("author") || qName.equalsIgnoreCase("editor")) && iPub){
			iAuthor = true;
		}
		else if(qName.equalsIgnoreCase("title") && iPub){
			iTitle = true;
		}
		else if(qName.equalsIgnoreCase("pages") && iPub){
			iPages = true;
		}
		else if(qName.equalsIgnoreCase("year") && iPub){
		//	System.out.println("iyear trues qname : " + qName);
			iYear = true;
		}
		else if(qName.equalsIgnoreCase("volume") && iPub){
			iVolume = true;
		}
		else if((qName.equalsIgnoreCase("journal") || qName.equalsIgnoreCase("booktitle")) && iPub){
		//	 System.out.println("iJournal true coz qname is "+qName);
			iJournal = true;
		}
		else if(qName.equalsIgnoreCase("url") && iPub){
			iURL = true;
		}

	}

	public void characters(char ch[], int start, int length) throws SAXException{
		String t = new String(ch, start, length);
		// System.out.println(t);
		if(iPub && iAuthor){
			// System.out.print(this.authors);
			this.authors.add(t);
			iAuthor = false;
		}
		else if(iPub && iTitle){
			this.title = t;
			iTitle = false;
		}
		else if(iPub && iPages){
			this.pages = t;
			iPages = false;
		}
		else if(iPub && iYear){
			try{
				this.year = Integer.parseInt(t);
			}catch(Exception e){
				System.out.println(type);
				System.out.println(key);
				System.out.println(title);
				System.out.println(authors.size());
				System.out.println(iJournal);
				System.out.println(year);
				System.exit(0);
			}
			iYear = false;
		}
		else if(iPub && iVolume){
			this.volume = t;
			iVolume = false;
		}
		else if(iPub && iJournal){
			this.journal = t;
			iJournal = false;
			// System.out.println("^^");
		}
		else if(iPub && iURL){
			this.url = t;
			iURL = false;
		}
	}

	public void endElement(String uri, String localName, String qName) throws SAXException{
		if(qName.equalsIgnoreCase("article") ||	qName.equalsIgnoreCase("inproceedings") ||
			qName.equalsIgnoreCase("proceedings") || qName.equalsIgnoreCase("book") ||
			qName.equalsIgnoreCase("mastersthesis") || qName.equalsIgnoreCase("phdthesis") ||
			qName.equalsIgnoreCase("incollection")){

			iPub = false;
			
			ArrayList<String> byAuthorAliases = byAuthor.getAliases();
			for(String au: authors){
				for(String al: byAuthorAliases){
					if(au.equalsIgnoreCase(al)){
						Publication pub = new Publication(type, authors, title, pages, year, volume, journal, url, key);
						dict.put(pub, relevance);
					}
				}
			}

			authors = null;
			type = title = journal = url = pages = key = "";
			relevance = 0;
			iAuthor = iTitle = iPages = iYear = iVolume = iJournal = iURL = false;
		}
	}

	public void endDocument() throws SAXException{
		System.out.println("BOTTOM");
	}

	// public static void main(String[] args) throws Exception{
	// 	System.setProperty("jdk.xml.entityExpansionLimit", "0");

	// 	SAXParserFactory spf = SAXParserFactory.newInstance();
	//     spf.setNamespaceAware(true);
	//     SAXParser saxParser = spf.newSAXParser();
	// 	XMLReader xmlReader = saxPars
	// er.getXMLReader();
	// 	PublicationsByAuthorParser parser = new PublicationsByAuthorParser();
	// 	xmlReader.setContentHandler(parser);
	// 	xmlReader.parse("dblp.xml");
	// 	// saxParser.parse(new File("dblp.xml"), parser);
	// 	System.out.println(parser.getList().size());
	// 	int j=0;
	// 	for(Publication p: parser.getList().keySet()){
	// 		System.out.println(p);
	// 		System.out.println("Relevance: "+parser.getList().get(p));
	// 		j += 1;
	// 		if(j == 10){
	// 			break;
	// 		}
	// 	}
	// }

	public HashMap<Publication, Integer> getList(){
		return dict;
	}
}
