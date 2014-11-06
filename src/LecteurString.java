public class LecteurString {
	private String str;
	private int pos = 0;

	public LecteurString(String str) {
		this.str = str;
	}
	
	public boolean match(String deb) {
		return str.substring(pos).startsWith(deb);
	}

	public boolean matchLast(String deb) {
		return pos==str.length()-deb.length() && this.match(deb);
	}
	
	public boolean matchFirst(String deb) {
		return pos==0 && this.match(deb);
	}
	
	public int consume (int nchars) {
		if (pos+nchars > str.length()) nchars = str.length()-pos;
		this.pos += nchars;
		return nchars;
	}
	public int consume(String deb) {
		return this.consume(deb.length());
	}
	
	public boolean isFinished() {
		return pos==str.length();
	}

	public String getStr() {
		return str;
	}

	public int getPos() {
		return pos;
	}
}