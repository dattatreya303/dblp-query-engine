/*
Dattatreya Mohapatra, 2015021
Harshit Sharma, 2015036
*/

import java.io.*;
import java.util.*;
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

class PublicationsByAuthorCountParser extends DefaultHandler{
	TreeMap<String, Integer> allAuthors;
	TreeMap<String, String> aliasMap;

	ArrayList<String> authors = new ArrayList<String>();
	
	boolean iPub = false, iAuthor = false;
	String chars = "";
	int f=0;
	
	public PublicationsByAuthorCountParser(TreeMap<String, String> aliasMap, TreeMap<String, Integer> allAuthors){
		System.setProperty("jdk.xml.entityExpansionLimit", "0");
		this.aliasMap = aliasMap;
		this.allAuthors = allAuthors;
	}

	public void startDocument()	throws SAXException{
		System.out.println("TOP");
	}

	public void startElement(String uri, String localName, String qName, Attributes attr) throws SAXException{
		if(qName.equalsIgnoreCase("article") ||	qName.equalsIgnoreCase("inproceedings") ||
			qName.equalsIgnoreCase("proceedings") || qName.equalsIgnoreCase("book") ||
			qName.equalsIgnoreCase("mastersthesis") || qName.equalsIgnoreCase("phdthesis") ||
			qName.equalsIgnoreCase("incollection")){

			iPub = true;
			authors = new ArrayList<String>();
			// System.out.println(key);
		}
		else if((qName.equalsIgnoreCase("author") || qName.equalsIgnoreCase("editor")) && iPub){
			chars = "";
			iAuthor = true;
		}
	}

	public void characters(char ch[], int start, int length) throws SAXException{
		String t = new String(ch, start, length);
		// System.out.println(t);
		if(iPub && iAuthor){
			// System.out.print(this.authors);
			chars += t;
		}
	}

	public void endElement(String uri, String localName, String qName) throws SAXException{
		if(qName.equalsIgnoreCase("article") ||	qName.equalsIgnoreCase("inproceedings") ||
			qName.equalsIgnoreCase("proceedings") || qName.equalsIgnoreCase("book") ||
			qName.equalsIgnoreCase("mastersthesis") || qName.equalsIgnoreCase("phdthesis") ||
			qName.equalsIgnoreCase("incollection")){

			iPub = false;

			for(String au: authors){
				if("Philip S. Yu".equalsIgnoreCase(au)){
					f++;
				}
				if(allAuthors.containsKey(au)){
					allAuthors.put(au, allAuthors.get(au) + 1);
				}
				else if(aliasMap.containsKey(au)){
					allAuthors.put(aliasMap.get(au), allAuthors.get(aliasMap.get(au)) + 1);
				}
				else{
					allAuthors.put(au, 1);
				}
			}

			authors = null;
		}
		else if(qName.equalsIgnoreCase("author") && iPub){
			this.authors.add(chars);
			iAuthor = false;
		}
	}

	public void endDocument() throws SAXException{
		System.out.println("BOTTOM");
		System.out.println(f);
	}

	public TreeMap<String, Integer> getCountMap(){
		return allAuthors;
	}
}
