/*
Dattatreya Mohapatra, 2015021
Harshit Sharma, 2015036
*/

import java.util.*;

class Person{
	ArrayList<String> aliases;
	String key;
	ArrayList<Publication> publications;

	public Person(){
		this.key = "";
	}

	public Person(ArrayList<String> aliases, String key){
		this.aliases = new ArrayList<String>();
		for(String a: aliases){
			addAlias(a);
		}
		this.key = key;
	}

	public ArrayList<String> getAliases(){
		return aliases;
	}

	public String getKey(){
		return key;
	}

	public void setKey(String key){
		this.key = key;
	}

	public void addAlias(String name){
		this.aliases.add(name);
	}

	public String toString(){
		String ret = "Names: ";
		for(String a: aliases){
			ret += a+", ";
		}
		return ret;
	}
}