package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import phonetique.ListeRegles;
import phonetique.Mot;
import phonetique.ReglePhonetique;
import phonetique.RegleRegex;
import phonetique.RegleSubstitution;

public class Database {
	Connection connection;

	public Database(File f) throws ClassNotFoundException, SQLException {
		// load the sqlite-JDBC driver using the current class loader
		Class.forName("org.sqlite.JDBC");
		this.connection = DriverManager.getConnection("jdbc:sqlite:"
				+ f.getAbsolutePath());
	}

	public ListeRegles getListeRegles() throws SQLException {
		ListeRegles liste = new ListeRegles();
		ResultSet rs = connection
				.createStatement()
				.executeQuery(
						"SELECT type,ortho,phono FROM ReglePhonetique ORDER BY prio DESC");
		while (rs.next()) {
			ReglePhonetique regle = null;
			String type = rs.getString(1);
			if (type == null)
				type = "NULL";
			switch (type) {
			case RegleRegex.nomType:
				regle = new RegleRegex(rs.getString(2), rs.getString(3));
				break;
			case RegleSubstitution.nomType:
				regle = new RegleSubstitution(rs.getString(2), rs.getString(3));
				break;
			default:
				System.err
						.println("Type de règle non supporté rencontré dans la BDD. "
								+ "Règle: [type="
								+ type
								+ ", ortho="
								+ rs.getString(2)
								+ ", phono="
								+ rs.getString(3) + "]");
			}
			if (regle != null)
				liste.add(regle);
		}
		return liste;
	}

	public void setListeRegles(ListeRegles liste) throws SQLException {
		connection.createStatement().executeUpdate(
				"DELETE FROM ReglePhonetique");
		PreparedStatement insertRegle = connection
				.prepareStatement("INSERT OR IGNORE INTO ReglePhonetique (type, ortho, phono, prio) VALUES (?,?,?,?)");
		int prio = liste.size();
		for (ReglePhonetique r : liste) {
			insertRegle.setString(1, r.getNomType());
			insertRegle.setString(2, r.getOrtho().toString());
			insertRegle.setString(3, r.getPhono().toString());
			insertRegle.setFloat(4, prio--);
			insertRegle.executeUpdate();
		}
	}

	public void exportMatching(File out, ListeRegles liste,
			ListeRegles selection, DbListWordsRequest req)
			throws FileNotFoundException, SQLException {
		PrintWriter writer = new PrintWriter(out);
		ResultSet rs = connection.createStatement()
				.executeQuery(req.toString());
		int i = 0;
		while (rs.next()) {
			System.out.print(++i + "\r");
			Mot m = new Mot(rs.getString(1), rs.getString(2));
			ListeRegles reglesMot = m.listeReglesPhonetiques(liste);
			if (reglesMot != null
					&& (selection == null || reglesMot.containsAll(selection))) {
				writer.println(m.getOrthographe());
			}
		}
		writer.close();
		System.out.println("Recherche terminée");
	}

	public void exportMatching(File out, ListeRegles liste,
			DbListWordsRequest req) throws FileNotFoundException, SQLException {
		exportMatching(out, liste, null, req);
	}
}