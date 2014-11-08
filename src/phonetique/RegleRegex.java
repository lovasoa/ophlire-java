package phonetique;
import lecteurString.ConsumableRegex;


public class RegleRegex extends RegleSubstitution {
	public static final String nomType = "REGEX";

	public RegleRegex(String reg, String phono) {
		super(
				ConsumableRegex.fromRegexStr(reg),
				phono
			);
	}
	
	@Override
	public String getNomType() {
		return RegleRegex.nomType;
	}
}
