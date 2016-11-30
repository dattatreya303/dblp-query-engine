/*
Dattatreya Mohapatra, 2015021
Harshit Sharma, 2015036
*/

import java.io.*;
import java.util.*;
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

class PublicationsByTitleParser extends DefaultHandler{
	ArrayList<Publication> relevantPubs;
	String title, journal, volume, url, pages, key, type;
	int year, relevance;
	ArrayList<String> authors = new ArrayList<String>();
	String[] tags;

	boolean iPub = false, iAuthor = false, iTitle = false, iPages = false;
	boolean iYear = false, iVolume = false, iJournal = false, iURL = false;

	public PublicationsByTitleParser(String[] tags){
		this.tags = tags; //new ArrayList<String>();
//		for(String s: tags){
//			this.tags.add(s);
//		}
	}

	public void startDocument()	throws SAXException{
		System.out.println("TOP");
		relevantPubs = new ArrayList<Publication>();
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
		}
		else if(iPub && iTitle){
			this.title = t;
			String[] words = t.trim().split("\\s+");
			int rel = 0;
			for(String tag: tags){
				for(String w: words){
					if (tag.equalsIgnoreCase(w)) {
						rel++;
					}
				}
			}
			relevance = rel;
		}
		else if(iPub && iPages){
			this.pages = t;
		}
		else if(iPub && iYear){
			try{
				this.year = Integer.parseInt(t);
		//		System.out.println("Successful year: " + this.year);
			}catch(Exception e){
				e.printStackTrace();
				System.out.println("year exception " + type);
				System.out.println(key);
				System.out.println(title);
				System.out.println(authors.size());
				System.out.println(iJournal);
				System.out.println(year);
				System.exit(0);
			}
		}
		else if(iPub && iVolume){
			this.volume = t;
		}
		else if(iPub && iJournal){
			this.journal = t;
		//	System.out.println("^^"+t);
		}
		else if(iPub && iURL){
			this.url = t;
		}
	}

	public void endElement(String uri, String localName, String qName) throws SAXException{
		if(qName.equalsIgnoreCase("article") ||	qName.equalsIgnoreCase("inproceedings") ||
			qName.equalsIgnoreCase("proceedings") || qName.equalsIgnoreCase("book") ||
			qName.equalsIgnoreCase("mastersthesis") || qName.equalsIgnoreCase("phdthesis") ||
			qName.equalsIgnoreCase("incollection")){

			iPub = false;
			if(relevance != 0){
				Publication pub = new Publication(type, authors, title, pages, year, volume, journal, url, key, relevance);
				relevantPubs.add(pub);
			}
			authors = null;
			type = title = journal = url = pages = key = "";
			relevance = 0;
		}
		else if((qName.equalsIgnoreCase("author") || qName.equalsIgnoreCase("editor")) && iPub){
			iAuthor = false;
		}
		else if(qName.equalsIgnoreCase("title") && iPub){
			iTitle = false;
		}
		else if(qName.equalsIgnoreCase("pages") && iPub){
			iPages = false;
		}
		else if(qName.equalsIgnoreCase("year") && iPub){
		//	System.out.println("iyear falses qname : " + qName);
			iYear = false;
		}
		else if(qName.equalsIgnoreCase("volume") && iPub){
			iVolume = false;
		}
		else if((qName.equalsIgnoreCase("journal") || qName.equalsIgnoreCase("booktitle")) && iPub){
		//	 System.out.println("iJournal false coz qname is "+qName);
			iJournal = false;
		}
		else if(qName.equalsIgnoreCase("url") && iPub){
			iURL = false;
		}
	}

	public void endDocument() throws SAXException{
		System.out.println("BOTTOM");
	}

	public ArrayList<Publication> getList(){
		return relevantPubs;
	}
}
