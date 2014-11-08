package lecteurString;

public abstract class Consumable {
	public abstract int matchedChars(LecteurString lect);
	public boolean matches(LecteurString l) {
		return matchedChars(l) >= 0;
	}
}
