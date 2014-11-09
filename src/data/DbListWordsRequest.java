package data;

public class DbListWordsRequest {
	private Integer nbLettresMin;
	private Integer nbLettresMax;

	public DbListWordsRequest(Integer nbLettresMin, Integer nbLettresMax) {
		setNbLettresMinMax(nbLettresMin, nbLettresMax);
	}

	private String whereClause() {
		if (nbLettresMax != null && nbLettresMin != null) {
			return "length(ortho) BETWEEN " + nbLettresMin + " AND "
					+ nbLettresMax;
		} else if (nbLettresMax != null) {
			return "length(ortho)>" + nbLettresMax;
		} else if (nbLettresMin != null) {
			return "length(ortho)<" + nbLettresMin;
		} else {
			assert (nbLettresMax == null && nbLettresMin == null);
			return null;
		}
	}

	public void setNbLettresMinMax(Integer nbLettresMin, Integer nbLettresMax) {
		if (isValid(nbLettresMin, nbLettresMax)) {
			this.nbLettresMin = nbLettresMin;
			this.nbLettresMax = nbLettresMax;
		} else {
			throw new IllegalArgumentException("DBRequest: cannot set min="
					+ nbLettresMin + " and max=" + nbLettresMax);
		}
	}

	private boolean isValid(Integer nbLettresMin, Integer nbLettresMax) {
		boolean mNull = nbLettresMin == null;
		boolean MNull = nbLettresMax == null;

		return (mNull || nbLettresMin >= 0) && (MNull || nbLettresMax >= 0)
				&& (mNull || MNull || nbLettresMin <= nbLettresMax);
	}

	@Override
	public String toString() {
		String whereClause = whereClause();
		return "SELECT DISTINCT ortho,phono "
				+"FROM Mot "
				+ ((whereClause==null) ? "" : "WHERE "+whereClause)
				+"ORDER BY freqlemfilms DESC";
	}
}
