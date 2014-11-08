package lecteurString;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ConsumableRegex extends Consumable {
	private Pattern reg;

	private ConsumableRegex(String reg) {
		this.reg = Pattern.compile(reg);
	}
	
	public static Consumable fromRegexStr(String reg) {
		return reg.startsWith("^") ?
				new ConsumableDebut(new ConsumableRegex(reg)) :
				new ConsumableRegex("^" + reg);
	}

	@Override
	public int matchedChars(LecteurString lect) {
		Matcher m = reg.matcher(lect.getEnd());
		if (m.find()) {
			int start = m.start();
			int end = m.end();
			if (start == 0 && end > 0) {
				return end-start;
			}
		}
		return -1;
	}
	
	@Override
	public String toString() {
		return reg.pattern().substring(1);
	}
}
