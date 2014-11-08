package data;

import java.io.File;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class Database {
	Connection connection;
	private static final String[] requiredTables = {"Mot", "ReglePhonetique"};

	public Database(File f) throws ClassNotFoundException, SQLException {
		// load the sqlite-JDBC driver using the current class loader
		Class.forName("org.sqlite.JDBC");
		this.connection = DriverManager.getConnection("jdbc:sqlite:"
				+ f.getAbsolutePath());
		this.checkValid();
	}

	private void checkValid() throws SQLException {
		Set<String> tbls = new HashSet<String>();
		DatabaseMetaData meta = connection.getMetaData();
		ResultSet res = meta.getTables(null, null, null,
				new String[] { "TABLE" });
		while (res.next()) {
			System.out.println(res.getString("TABLE_NAME"));
		}
	}
}
