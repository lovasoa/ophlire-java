package phonetique;
import lecteurString.LecteurString;


public class LecteurMot {
	private LecteurString lecteurOrtho;
	private LecteurString lecteurPhono;
	public LecteurMot(Mot mot) {
		this.lecteurOrtho = new LecteurString(mot.getOrthographe());
		this.lecteurPhono = new LecteurString(mot.getPhonetique());
	}
	public LecteurString getLecteurOrtho() {
		return lecteurOrtho;
	}
	public LecteurString getLecteurPhono() {
		return lecteurPhono;
	}
	public boolean isFinished() {
		return lecteurPhono.isFinished() && lecteurOrtho.isFinished();
	}
}
