package ua.nure.kn.ovsiienko.agent;

import ua.nure.kn.ovsiienko.db.DatabaseException;

public class SearchException extends Exception {
	private String str;
	
	public SearchException(Exception e) {
		this.str = e.toString();
	}


}
