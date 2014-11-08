package lecteurString;
public class ConsumableString extends Consumable {
	private String str;
	public ConsumableString(String str) {
		this.str = str;
	}
	@Override
	public int matchedChars(LecteurString lect) {
		return lect.getEnd().startsWith(str) ? str.length() : -1;
	}

	@Override
	public String toString() {
		return str;
	}
}
