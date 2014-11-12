package phonetique;

public class Mot {
	private String orthographe;
	private String phonetique;

	public Mot(String orthographe, String phonetique) {
		this.orthographe = orthographe;
		this.phonetique = phonetique;
	}

	public String getPhonetique() {
		return phonetique;
	}

	public String getOrthographe() {
		return orthographe;
	}

	public ListeRegles listeReglesPhonetiques(ListeReglesRecherche rech) {
		return rech.getListeRegles(new LecteurMot(this));
	}
}
