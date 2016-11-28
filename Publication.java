class Publication{
	String title, journal, url;
	int pages, year, volume;

	public String getTitle(){
		return title
	}

	public String getJournal(){
		return journal;
	}

	public String getURL(){
		return url;
	}

	public int getPages(){
		return pages;
	}

	public int getYear(){
		return year;
	}

	public int getVolume(){
		return volume;
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

	public void setPages(int pages){
		this.pages = pages;
	}

	public void setYear(int year){
		this.year = year;
	}

	public void setVolume(int volume){
		this.volume = volume;
	}
}