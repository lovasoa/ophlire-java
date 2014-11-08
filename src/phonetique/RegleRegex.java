package phonetique;
import lecteurString.ConsumableRegex;


public class RegleRegex extends RegleSubstitution {
	public RegleRegex(String reg, String phono) {
		super(
				ConsumableRegex.fromRegexStr(reg),
				phono
			);
	}
}
