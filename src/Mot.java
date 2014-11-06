import java.util.ArrayList;

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
	
	public Iterable<ReglePhonetique> listeReglesPhonetiques (Iterable<ReglePhonetique> reglesPhonetiques) {
		LecteurMot lecteur = new LecteurMot(this);
		ArrayList<ReglePhonetique> liste = new ArrayList<>();
		
		while (!lecteur.isFinished()) {
			for (ReglePhonetique r :  reglesPhonetiques) {
				if (r.appliquer(lecteur)) {
					liste.add(r);
				}
			}
		}
		return liste;
	}
}