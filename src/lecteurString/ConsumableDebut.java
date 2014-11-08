package lecteurString;

public class ConsumableDebut extends Consumable {
	private Consumable cons;
	public ConsumableDebut(Consumable cons) {
		this.cons = cons;
	}
	@Override
	public int matchedChars(LecteurString lect) {
		return (lect.getPos() == 0) ? cons.matchedChars(lect) : -1;
	}
	@Override
	public String toString() {
		return "^" + cons;
	}

}