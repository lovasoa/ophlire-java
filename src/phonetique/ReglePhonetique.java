package phonetique;


public abstract class ReglePhonetique {
	public abstract boolean applicable(LecteurMot l);
	public abstract String appliquer(LecteurMot l);
}
