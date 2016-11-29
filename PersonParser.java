import java.io.*;
import java.util.*;
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

class PersonParser extends DefaultHandler{
	Person person;
	ArrayList<String> aliases;
	boolean iPerson = false, iAuthor = false;
	String byAuthor, key;

	public PersonParser(String byAuthor){
		person = new Person();
		this.byAuthor = byAuthor;
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
		else if(qName.equalsIgnoreCase("author")){
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
			for(String a: aliases){
				if(byAuthor.equalsIgnoreCase(a)){
					person = new Person(aliases, key);
					throw new SAXBreakerException(); // terminate parser
				}
			}
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
	// 	XMLReader xmlReader = saxParser.getXMLReader();
	// 	PersonParser parser = new PersonParser("abcd");
	// 	xmlReader.setContentHandler(parser);
	// 	try{
	// 		xmlReader.parse("dblp.xml");
	// 	}
	// 	catch(SAXBreakerException s){
	// 		System.out.println(parser.getPerson());
	// 	}
	// }

	public Person getPerson(){
		if("".equals(person.getKey())){
			return null;
		}
		return person;
	}
}