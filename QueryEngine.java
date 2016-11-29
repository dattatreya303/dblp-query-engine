import java.io.*;
import java.util.*;
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

class QueryEngine{
	HashMap<Publication, Integer> currentPublications;
	ArrayList<Person> currentAuthors;

	public QueryEngine(){
		currentPublications = new HashMap<Publication, Integer>();
		currentAuthors = new ArrayList<Person>();
		System.setProperty("jdk.xml.entityExpansionLimit", "0");
	}

	public void publicationsByTitle(ArrayList<String> tags){
		PublicationsByTitleParser parser = new PublicationsByTitleParser(tags);
		SAXParserFactory spf = SAXParserFactory.newInstance();
	    spf.setNamespaceAware(true);
	    SAXParser saxParser = spf.newSAXParser();
		XMLReader xmlReader = saxParser.getXMLReader();
		xmlReader.setContentHandler(parser);
		xmlReader.parse("dblp.xml");
		currentPublications = parser.getList();
	}

	public void publicationsByAuthor(String author){
		SAXParserFactory spf = SAXParserFactory.newInstance();
	    spf.setNamespaceAware(true);
	    SAXParser saxParser = spf.newSAXParser();
		XMLReader xmlReader = saxParser.getXMLReader();
		PersonParser parser = new PersonParser("Peter Henning");
		xmlReader.setContentHandler(parser);

		Person aPerson = new Person();
		try{
			xmlReader.parse("dblp.xml");
		}
		catch(SAXBreakerException s){
			aPerson = parser.getPerson();
		}

		if(!aPerson.getKey().equals("")){
			PublicationsByAuthorParser parser2 = new PublicationsByAuthorParser(aPerson);
			xmlReader.setContentHandler(parser2);
			xmlReader.parse("dblp.xml");
			System.out.println(parser2.getList().size());
		}
	}

	public HashSet<Publication> sortByRelevance(){
		Set<Publication> pubs = currentPublications.keySet();
		Iterator it = pubs.iterator();
		// sort
	}

	public HashSet<Publication> sortByDate(int mode){// mode = 0 for reverse sort
		Set<Publication> pubs = currentPublications.keySet();
		Iterator it = pubs.iterator();
		// sort
	}

	public HashSet<Publication> inBetween(int year1, int year2){
		Set<Publication> pubs = currentPublications.keySet();
		Set<Publication> newPubs = new Set<Publication>();
		Iterator it = pubs.iterator();
		while(it.hasNext()){
			Publication pt = it.next();
			if(pt.getYear() >= year1 && pt.getYear <= year2){
				newPubs.add(pt);
			}
		}

		return newPubs;
	}

	public HashSet<Publication> inBetween(int year1){// since year1
		HashSet<Publication> pubs = currentPublications.keySet();
		HashSet<Publication> newPubs = new Set<Publication>();
		Iterator it = pubs.iterator();
		while(it.hasNext()){
			Publication pt = it.next();
			if(pt.getYear() >= year1){
				newPubs.add(pt);
			}
		}

		return newPubs;
	}
}