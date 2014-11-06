import java.util.ArrayList;


public class Dictionnaire {
	private ArrayList<ReglePhonetique> regles;
	
	public Dictionnaire() {
		this.regles = new ArrayList<>();
	}

	public ArrayList<ReglePhonetique> getReglesPhonetiques() {
		return this.regles;
	}
}
