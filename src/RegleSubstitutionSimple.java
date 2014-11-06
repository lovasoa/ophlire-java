
public class RegleSubstitutionSimple extends ReglePhonetique {
	private String ortho;
	private String phono;
	
	public RegleSubstitutionSimple(String ortho, String phono) {
		this.ortho = ortho;
		this.phono = phono;
	}

	@Override
	public boolean applicable(LecteurMot l) {
		return l.getLecteurOrtho().match(ortho) && l.getLecteurPhono().match(phono);
	}
	
	public boolean appliquer(LecteurMot l){
		if (this.applicable(l)) {
			l.getLecteurOrtho().consume(ortho);
			l.getLecteurPhono().consume(phono);
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "RegleSubstitutionSimple [ortho=" + ortho + ", phono=" + phono
				+ "]";
	}

	public String getOrtho() {
		return ortho;
	}

	public String getPhono() {
		return phono;
	}
}
