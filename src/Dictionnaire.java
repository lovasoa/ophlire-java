import java.util.ArrayList;

import phonetique.ReglePhonetique;



public class Dictionnaire {
	private ArrayList<ReglePhonetique> regles;
	
	public Dictionnaire() {
		this.regles = new ArrayList<>();
	}

	public ArrayList<ReglePhonetique> getReglesPhonetiques() {
		return this.regles;
	}
}
