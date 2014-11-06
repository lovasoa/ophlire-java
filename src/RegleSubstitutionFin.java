
public class RegleSubstitutionFin extends RegleSubstitutionSimple {
	public RegleSubstitutionFin(String ortho, String phono) {
		super(ortho, phono);
	}
	
	@Override
	public boolean applicable(LecteurMot l) {
		return l.getLecteurOrtho().matchLast(this.getOrtho())
				&& l.getLecteurPhono().match(this.getPhono());
	}
}
