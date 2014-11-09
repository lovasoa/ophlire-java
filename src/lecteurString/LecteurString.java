package lecteurString;
import java.util.regex.Pattern;

public class LecteurString {
	private String str;
	private int pos = 0;

	public LecteurString(String str) {
		this.str = str;
	}
	
	public boolean matches(Consumable cons) {
		return cons.matches(this);
	}

	public boolean match(Pattern deb) {
		return deb.matcher(str.substring(pos)).matches();
	}
	
	public boolean consume (int nchars) {
		if (nchars + pos <= str.length()){
			pos += nchars;
			return true;
		}
		return false;
	}
	
	public boolean consume (Consumable cons) {
		int matchedChars = cons.matchedChars(this);
		if (matchedChars < 0) return false;
		return this.consume(matchedChars);
	}

	public boolean isFinished() {
		return pos==str.length();
	}

	public String getStr() {
		return str;
	}
	
	public String getEnd() {
		return str.substring(pos);
	}

	public int getPos() {
		return pos;
	}
}