package ua.nure.kn.ovsiienko.db;

import java.sql.Connection;

public interface ConnectionFactory {

    Connection getConnection() throws DatabaseException;
}
