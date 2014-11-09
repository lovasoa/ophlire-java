package data;

public class DbListWordsRequest {
	private String sql = "SELECT DISTINCT ortho,phono FROM Mot ORDER BY freqlemfilms DESC";
	public DbListWordsRequest() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return sql;
	}
}
