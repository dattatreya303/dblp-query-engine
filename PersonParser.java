/*
Dattatreya Mohapatra, 2015021
Harshit Sharma, 2015036
*/

import java.io.*;
import java.util.*;
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

class PersonParser extends DefaultHandler{
	Person person;
	ArrayList<String> aliases;
	boolean iPerson = false, iAuthor = false, iAll = false;
	String byAuthor, key;
	TreeMap<String, String> tm;
	TreeMap<String, Integer> allA;

	public PersonParser(String byAuthor){
		System.setProperty("jdk.xml.entityExpansionLimit", "0");
		person = new Person();
		this.byAuthor = byAuthor;
	}

	public PersonParser(){
		System.setProperty("jdk.xml.entityExpansionLimit", "0");
		this.iAll = true;
		tm = new TreeMap<String, String>();
		allA = new TreeMap<String, Integer>();
	}

	public void startDocument()	throws SAXException{
		System.out.println("TOP");
	}

	public void startElement(String uri, String localName, String qName, Attributes attr) throws SAXException{
		if(qName.equalsIgnoreCase("www")){
			key = attr.getValue("key");
			if(key.startsWith("homepages")){
				aliases = new ArrayList<String>();
				iPerson = true;
			}
		}
		else if(qName.equalsIgnoreCase("author") && iPerson){
			iAuthor = true;
		}
	}

	public void characters(char ch[], int start, int length) throws SAXException{
		String t = new String(ch, start, length);
		// System.out.println(t);
		if(iAuthor && iPerson){
			// System.out.print(this.authors);
			this.aliases.add(t);
			iAuthor = false;
		}
	}

	public void endElement(String uri, String localName, String qName) throws SAXException{
		if(qName.equalsIgnoreCase("www") && key.startsWith("homepages")){
			iPerson = false;
			if(iAll && aliases.size()>0){
				allA.put(aliases.get(0), 0);
				for(int i=1; i<aliases.size(); i++){
					tm.put(aliases.get(i), aliases.get(0));
				}
			}else{
				for(String a: aliases){
					if(byAuthor.equalsIgnoreCase(a)){
						person = new Person(aliases, key);
						throw new SAXBreakerException(); // terminate parser
					}
				}
			}
		}
	}

	public void endDocument() throws SAXException{
		System.out.println("BOTTOM");
	}

	public Person getPerson(){
		if("".equals(person.getKey())){
			return null;
		}
		return person;
	}

	public TreeMap<String, Integer> getAllAuthors(){
		return allA;
	}

	public TreeMap<String, String> getPeople(){
		if(iAll){
			return tm;
		}
		System.out.println("Invalid parser");
		return null;
	}
}