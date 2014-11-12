package phonetique;

import java.util.Hashtable;

public class ListeReglesRecherche {
	private Hashtable<String, ListeRegles> phonoLookup;

	public ListeReglesRecherche(ListeRegles l) {
		this.phonoLookup = new Hashtable<>();
		for (ReglePhonetique r : l) {
			ajouterRegle(r);
		}
	}

	private void ajouterRegle(ReglePhonetique r) {
		String phono = (r instanceof RegleSubstitution) ? r.getPhono()
				.toString() : "";
		ListeRegles l = phonoLookup.get(phono);
		if (l == null) {
			l = new ListeRegles();
		}
		l.add(r);
		phonoLookup.put(phono, l);
	}

	private ListeRegles getPotentialMatches(LecteurMot lecteurMot) {
		ListeRegles l = new ListeRegles();
		String end = lecteurMot.getLecteurPhono().getEnd();
		for (int i = 0; i <= end.length(); i++) {
			ListeRegles subl = phonoLookup.get(end.substring(0, i));
			if (subl != null) {
				l.addAll(subl);
			}
		}
		return l;
	}

	public ListeRegles getListeRegles(LecteurMot lecteurMot) {
		ListeRegles liste = new ListeRegles();

		recherche: while (!lecteurMot.isFinished()) {
			ListeRegles potential = getPotentialMatches(lecteurMot);
			for (ReglePhonetique regle : potential) {
				if (regle.appliquer(lecteurMot) != null) {
					liste.add(regle);
					continue recherche;
				}
			}
			return null;
		}
		return liste;
	}
}
