import java.io.*;
import java.util.*;

class Publication{
	String title, journal, url, pages, key;
	int year, volume;
	ArrayList<String> authors;

	public Publication(ArrayList<String> authors, String title, String pages,
		int year, int volume, String journal, String url, String key){

		this.authors = authors; this.title = title; this.pages = pages;
		this.year = year; this.volume = volume;	this.journal = journal;
		this.url = url;	this.key = key;
	}

	public Publication(){
		this.authors = new ArrayList<String>();
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

	public int getVolume(){
		return volume;
	}

	public String getKey(){
		return key;
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

	public void setVolume(int volume){
		this.volume = volume;
	}

	public void addAuthor(String author){
		this.authors.add(author);
	}
}