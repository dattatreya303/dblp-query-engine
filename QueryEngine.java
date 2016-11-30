/*
Dattatreya Mohapatra, 2015021
Harshit Sharma, 2015036
*/

import java.io.*;
import java.util.*;
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

class QueryEngine{
	ArrayList<Publication> currentPublications;
	TreeMap<String, Integer> allAuthors;
	TreeMap<String, String> aliasMap;
	ArrayList<String> currentAuthors;
	ArrayList<Integer> pubsPerYear;

	public QueryEngine() throws Exception{
		currentPublications = new ArrayList<Publication>();
		System.setProperty("jdk.xml.entityExpansionLimit", "0");
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
		allAuthors = parser.getAllAuthors();
		// System.out.println(aliasMap.size());
		PublicationsByAuthorCountParser parser2 = new PublicationsByAuthorCountParser(aliasMap, allAuthors);
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

	public void publicationsByTitle(String[] tags) throws Exception{
		PublicationsByTitleParser parser = new PublicationsByTitleParser(tags);
		SAXParserFactory spf = SAXParserFactory.newInstance();
	    spf.setNamespaceAware(true);
	    SAXParser saxParser = spf.newSAXParser();
		XMLReader xmlReader = saxParser.getXMLReader();
		xmlReader.setContentHandler(parser);
		xmlReader.parse("dblp.xml");
		currentPublications = null;
		currentPublications = parser.getList();
		System.out.println(currentPublications);
		int j=0;
		// Collections.sort(currentPublications);
		// for(Publication p: currentPublications){
		// 	if(p.relevance >= 1){
		// 		System.out.print(p);
		// 		System.out.println("Relevance: "+p.relevance+"\n");
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
			Collections.sort(currentPublications);
			System.out.println(currentPublications.size());
			for(Publication p: currentPublications){
				System.out.println(p);
			}
		}
		else{
			System.out.println("Author not found");
		}
	}

	public ArrayList<String> authorByPublications(int k){
		currentAuthors = new ArrayList<String>();
		for(String au: allAuthors.keySet()){
			if(allAuthors.get(au) >= k){
				currentAuthors.add(au);
			}
		}
		return currentAuthors;
	}

	public void predictPublications(String author, int year) throws Exception{
		SAXParserFactory spf = SAXParserFactory.newInstance();
	    spf.setNamespaceAware(true);
	    SAXParser saxParser = spf.newSAXParser();
		XMLReader xmlReader = saxParser.getXMLReader();
		PersonParser parser = new PersonParser(author);
		xmlReader.setContentHandler(parser);
		Person p = new Person();
		try{
			xmlReader.parse("dblp.xml");
		}
		catch(SAXBreakerException s){
			p = parser.getPerson();
		}

		if(!p.getKey().equals("")){
			PublicationsByAuthorParser parser2 = new PublicationsByAuthorParser(p);
			xmlReader.setContentHandler(parser2);
			xmlReader.parse("dblp.xml");
			currentPublications = null;
			currentPublications = parser2.getList();
			Collections.sort(currentPublications, new YearComparator());
			// System.out.println(currentPublications.size());
			pubsPerYear = new ArrayList<Integer>();
			int c = 0, prev = year-10;
			Publication pi = currentPublications.get(0);
			int i;
			for(i=0; i<currentPublications.size(); i++){
				pi = currentPublications.get(i);
				if(pi.getYear() >= year-10 && pi.getYear() <= year+1){
					if(pi.getYear() == prev){
						c++;
					}
					else{
						pubsPerYear.add(c);
						c = 1;
						prev = pi.getYear();
					}
				}
			}
			if(pi.getYear() >= year-10 && pi.getYear() <= year+1){
				pubsPerYear.add(c);
			}
			Collections.reverse(pubsPerYear);
			System.out.println(pubsPerYear);
			int prev = 3 + (int)(Math.random()*5), sum = 0;
			for(i = 1; i <= prev; i++){
				sum += pubsPerYear.get(i);
			}
			int avg = sum/prev;
			int ans = (int)(Math.random()*(pubsPerYear.get(1)*prev - sum);
			double answer = ans/(double)prev;
			System.out.println("Mera answer : " + answer + "  Actual pubs: " + pubsPerYear.get(0));
		}
		else{
			System.out.println("Author not found");
		}
	}

	public void sortByRelevance(){
		Collections.sort(currentPublications);
	}

	public void sortByYear(int mode){// mode = 0 for reverse sort, 1 for normal
		Collections.sort(currentPublications, new YearComparator());
		if(mode == 0){
			Collections.reverse(currentPublications);
		}
	}

	public ArrayList<Publication> getCurrentPublications(){
		return currentPublications;
	}

	public ArrayList<String> getCurrentAuthors(){
		return currentAuthors;
	}

	public ArrayList<Integer> getPubsPerYear(){
		return pubsPerYear;
	}
}
