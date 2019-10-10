package ua.nure.kn.ovsiienko.db;

public class DatabaseException extends Exception {

	public DatabaseException(Exception e) {
		super(e);
	}
	public DatabaseException(String string) {
		super(string);
		}
}
