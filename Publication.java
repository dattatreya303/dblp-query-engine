import java.io.*;
import java.util.*;

class Publication implements Comparable<Publication>{
	String title, journal, url, pages, key, volume, type;
	int year, relevance;
	ArrayList<String> authors;

	public Publication(String type, ArrayList<String> authorsIn, String title, String pages,
		int year, String volume, String journal, String url, String key, int relevance){

		this.type = type;
		authors = new ArrayList<String>();
		for(String aut: authorsIn){
			this.authors.add(aut);
		}
		this.title = title; this.pages = pages;
		this.year = year; this.volume = volume;	this.journal = journal;
		this.url = url;	this.key = key; this.relevance = relevance;
	}
	public int compareTo(Publication other){
		return other.relevance - this.relevance;
	}
	public String getTitle(){
		return title;
	}

	public String getJournal(){
		return journal;
	}

	public String getURL(){
		return url;
	}

	public String getPages(){
		return pages;
	}

	public int getYear(){
		return year;
	}

	public String getVolume(){
		return volume;
	}

	public String getKey(){
		return key;
	}

	public String getType(){
		return type;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public void setJournal(String journal){
		this.journal = journal;
	}

	public void setURL(String url){
		this.url = url;
	}

	public void setPages(String pages){
		this.pages = pages;
	}

	public void setYear(int year){
		this.year = year;
	}

	public void setVolume(String volume){
		this.volume = volume;
	}

	public void addAuthor(String author){
		this.authors.add(author);
	}

	public void setType(String type){
		this.type = type;
	}

	public String toString(){
		String ret = "";
		ret += "Type: "+type+'\n';
		ret += "Authors: ";
		for(int i=0; i < this.authors.size(); i++){
			ret += this.authors.get(i);
			// System.out.println(ret);
			if(i != this.authors.size() - 1){
				ret += ", ";
			}else{
				ret += '\n';
			}
		}
		// System.out.println("*");
		ret += "Title: "+this.title+'\n';
		ret += "Pages: "+this.pages+'\n';
		ret += "Year: "+String.valueOf(this.year)+'\n';
		ret += "Volume: "+this.volume+'\n';
		ret += "Journal/Booktitle: "+this.journal+'\n';
		ret += "URL: "+this.url+'\n';

		return ret;
	}
}
