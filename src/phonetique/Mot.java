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

	public ListeRegles listeReglesPhonetiques(
			Iterable<ReglePhonetique> reglesPhonetiques) {
		LecteurMot lecteur = new LecteurMot(this);
		ListeRegles liste = new ListeRegles();

		recherche: while (true) {
			for (ReglePhonetique r : reglesPhonetiques) {
				if (r.appliquer(lecteur) != null) {
					liste.add(r);
					continue recherche;
				}
			}
			// Aucune regle de substitution trouvee
			return (lecteur.isFinished()) ? liste : null;
		}
	}
}
