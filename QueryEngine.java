import java.io.*;
import java.util.*;
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

class QueryEngine{
	HashMap<Publication, Integer> currentPublications;
	TreeMap<String, Integer> allAuthors;
	TreeMap<String, String> aliasMap;

	public QueryEngine() throws Exception{
		System.setProperty("jdk.xml.entityExpansionLimit", "0");
		currentPublications = new HashMap<Publication, Integer>();
		aliasMap = new TreeMap<String, String>(); 
		allAuthors = new TreeMap<String, Integer>();

		PersonParser parser = new PersonParser();
		SAXParserFactory spf = SAXParserFactory.newInstance();
	    spf.setNamespaceAware(true);
	    SAXParser saxParser = spf.newSAXParser();
		XMLReader xmlReader = saxParser.getXMLReader();
		xmlReader.setContentHandler(parser);
		xmlReader.parse("dblp.xml");
		aliasMap = parser.getPeople();
		System.out.println(aliasMap.size());
		PublicationsByAuthorCountParser parser2 = new PublicationsByAuthorCountParser(aliasMap);
		xmlReader.setContentHandler(parser2);
		xmlReader.parse("dblp.xml");
		allAuthors = parser2.getCountMap();
		// System.out.println(allAuthors.containsValue(1));
		// int j=0;
		// for(String a: allAuthors.keySet()){
		// 	System.out.print(a+": ");
		// 	System.out.print(allAuthors.get(a)+", ");
		// 	System.out.println();
		// 	j+=1;
		// 	if(j == 10){
		// 		break;
		// 	}
		// }
	}

	public void publicationsByTitle(ArrayList<String> tags) throws Exception{
		PublicationsByTitleParser parser = new PublicationsByTitleParser(tags);
		SAXParserFactory spf = SAXParserFactory.newInstance();
	    spf.setNamespaceAware(true);
	    SAXParser saxParser = spf.newSAXParser();
		XMLReader xmlReader = saxParser.getXMLReader();
		xmlReader.setContentHandler(parser);
		xmlReader.parse("dblp.xml");
		currentPublications = null;
		currentPublications = parser.getList();
		int j=0;
		// for(Publication p: currentPublications.keySet()){
		// 	if(currentPublications.get(p) > 1){
		// 		System.out.print(p);
		// 		System.out.println("Relevance: "+currentPublications.get(p)+"\n");
		// 		j += 1;
		// 		if(j == 10){
		// 			break;
		// 		}
		// 	}
		// }
	}

	public void publicationsByAuthor(String author) throws Exception{
		SAXParserFactory spf = SAXParserFactory.newInstance();
	    spf.setNamespaceAware(true);
	    SAXParser saxParser = spf.newSAXParser();
		XMLReader xmlReader = saxParser.getXMLReader();
		PersonParser parser = new PersonParser(author);
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
			currentPublications = null;
			currentPublications = parser2.getList();
			// System.out.println(currentPublications.size());
			// for(Publication p: currentPublications.keySet()){
			// 	System.out.println(p);
			// }
		}
		else{
			System.out.println("Author not found");
		}
	}

	public ArrayList<String> authorByPublications(int k){
		ArrayList<String> selAuthors = new ArrayList<String>();
		for(String au: allAuthors.keySet()){
			if(allAuthors.get(au) >= k){
				selAuthors.add(au);
			}
		}
		return selAuthors;
	}

	public static void main(String[] args) throws Exception{
		QueryEngine qe = new QueryEngine();
		// qe.publicationsByAuthor("Peter Henning");
		// ArrayList<String> temp = new ArrayList<String>();
		// temp.add("quantum");
		// temp.add("computing");
		// qe.publicationsByTitle(temp);
	}

	// public HashSet<Publication> sortByRelevance(){
	// 	Set<Publication> pubs = currentPublications.keySet();
	// 	Iterator it = pubs.iterator();
	// 	// sort
	// }

	// public HashSet<Publication> sortByDate(int mode){// mode = 0 for reverse sort
	// 	Set<Publication> pubs = currentPublications.keySet();
	// 	Iterator it = pubs.iterator();
	// 	// sort
	// }

	// public HashSet<Publication> inBetween(int year1, int year2){
	// 	Set<Publication> pubs = currentPublications.keySet();
	// 	Set<Publication> newPubs = new Set<Publication>();
	// 	Iterator it = pubs.iterator();
	// 	while(it.hasNext()){
	// 		Publication pt = it.next();
	// 		if(pt.getYear() >= year1 && pt.getYear <= year2){
	// 			newPubs.add(pt);
	// 		}
	// 	}

	// 	return newPubs;
	// }

	// public HashSet<Publication> inBetween(int year1){// since year1
	// 	HashSet<Publication> pubs = currentPublications.keySet();
	// 	HashSet<Publication> newPubs = new Set<Publication>();
	// 	Iterator it = pubs.iterator();
	// 	while(it.hasNext()){
	// 		Publication pt = it.next();
	// 		if(pt.getYear() >= year1){
	// 			newPubs.add(pt);
	// 		}
	// 	}

	// 	return newPubs;
	// }
}