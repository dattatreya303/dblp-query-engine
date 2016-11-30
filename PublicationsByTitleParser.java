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
	String chars = "";

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
		}
		else if((qName.equalsIgnoreCase("author") || qName.equalsIgnoreCase("editor")) && iPub){
			chars = "";
			iAuthor = true;
		}
		else if(qName.equalsIgnoreCase("title") && iPub){
			chars = "";
			iTitle = true;
		}
		else if(qName.equalsIgnoreCase("pages") && iPub){
			chars = "";
			iPages = true;
		}
		else if(qName.equalsIgnoreCase("year") && iPub){
			chars = "";
			iYear = true;
		}
		else if(qName.equalsIgnoreCase("volume") && iPub){
			chars = "";
			iVolume = true;
		}
		else if((qName.equalsIgnoreCase("journal") || qName.equalsIgnoreCase("booktitle")) && iPub){
			chars = "";
			iJournal = true;
		}
		else if(qName.equalsIgnoreCase("url") && iPub){
			chars = "";
			iURL = true;
		}
	}

	public void characters(char ch[], int start, int length) throws SAXException{
		String t = new String(ch, start, length);
		// System.out.println(t);
		if(iPub && iAuthor){
			// System.out.print(this.authors);
			chars += t;
		}
		else if(iPub && iTitle){
			chars += t;
		}
		else if(iPub && iPages){
			chars += t;
		}
		else if(iPub && iYear){
			chars += t;
		}
		else if(iPub && iVolume){
			chars += t;
		}
		else if(iPub && iJournal){
			chars += t;
		}
		else if(iPub && iURL){
			chars += t;
		}
	}

	public void endElement(String uri, String localName, String qName) throws SAXException{
		if(qName.equalsIgnoreCase("article") ||	qName.equalsIgnoreCase("inproceedings") ||
			qName.equalsIgnoreCase("proceedings") || qName.equalsIgnoreCase("book") ||
			qName.equalsIgnoreCase("mastersthesis") || qName.equalsIgnoreCase("phdthesis") ||
			qName.equalsIgnoreCase("incollection")){

			iPub = false;
			if(this.relevance != 0){
				Publication pub = new Publication(type, authors, title, pages, year, volume, journal, url, key, relevance);
				relevantPubs.add(pub);
			}
			authors = null;
			type = title = journal = url = pages = key = "";
			this.relevance = 0;
		}
		else if((qName.equalsIgnoreCase("author") || qName.equalsIgnoreCase("editor")) && iPub){
			this.authors.add(chars);
			iAuthor = false;
		}
		else if(qName.equalsIgnoreCase("title") && iPub){
			this.title = chars;
			String[] words = title.trim().split("\\s+");
			int rel = 0;
			for(String tag: tags){
				for(String w: words){
					if (tag.equalsIgnoreCase(w)) {
						rel++;
					}
				}
			}
			this.relevance = rel;
			iTitle = false;
		}
		else if(qName.equalsIgnoreCase("pages") && iPub){
			this.pages = chars;
			iPages = false;
		}
		else if(qName.equalsIgnoreCase("year") && iPub){
			this.year = Integer.parseInt(chars);
			iYear = false;
		}
		else if(qName.equalsIgnoreCase("volume") && iPub){
			this.volume = chars;
			iVolume = false;
		}
		else if((qName.equalsIgnoreCase("journal") || qName.equalsIgnoreCase("booktitle")) && iPub){
			this.journal = chars;
			iJournal = false;
		}
		else if(qName.equalsIgnoreCase("url") && iPub){
			this.url = chars;
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
