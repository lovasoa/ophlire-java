package phonetique;
import lecteurString.Consumable;
import lecteurString.ConsumableString;
import lecteurString.LecteurString;


public class RegleSubstitution extends ReglePhonetique {
	public static final String nomType = "SUBS";
	private Consumable ortho;
	private Consumable phono;
	public String description;
	
	public RegleSubstitution(Consumable ortho, String phono) {
		this.ortho = ortho;
		this.phono = new ConsumableString(phono);
	}

	public RegleSubstitution(String ortho, String phono) {
		this.ortho = new ConsumableString(ortho);
		this.phono = new ConsumableString(phono);
	}

	@Override
	public boolean applicable(LecteurMot lect) {
		return lect.getLecteurPhono().matches(phono)
			&& lect.getLecteurOrtho().matches(ortho);
	}

	@Override
	public String appliquer(LecteurMot lect) {
		LecteurString lectPhono = lect.getLecteurPhono();
		if (lectPhono == null || lectPhono.matches(phono)) {
			if (lect.getLecteurOrtho().consume(ortho)) {
				if (lectPhono != null) lectPhono.consume(phono);
				return phono.toString();
			};
		}
		return null;
	}

	@Override
	public String toString() {
		return "(" + ortho + " -> " + phono + ")";
	}
	
	public Consumable getOrtho() {
		return ortho;
	}

	public Consumable getPhono() {
		return phono;
	}

	@Override
	public String getNomType() {
		return RegleSubstitution.nomType;
	}

}
