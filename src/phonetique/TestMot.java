package phonetique;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

/**
 * @author olojkine
 * 
 */
public class TestMot {

	@Test
	public void testListeReglesPhonetiques() {
		// Création des règles de prononciation (l'ordre est important)
		ArrayList<ReglePhonetique> regles = new ArrayList<>();
		regles.add(new RegleSubstitution("d", "d"));
		// '@' correspond au son 'an' comme dans "enfant" (@f@)
		regles.add(new RegleSubstitution("an", "@"));
		// 'e' se prononce 'è' devant deux consonnes
		regles.add(new RegleRegex("e(?=[bcdfghklmnpqrstvwxz]{2})", "E"));
		// 's' et 'e' en fin de mot ne se prononcent pas
		regles.add(new RegleRegex("s$", ""));
		regles.add(new RegleRegex("e$", ""));
		regles.add(new RegleSubstitution("ss", "s"));
		regles.add(new RegleSubstitution("s", "s"));

		// Création des mots (on précise leur prononciation
		Mot sans = new Mot("sans", "s@");
		Mot dans = new Mot("dans", "d@");
		Mot enfant = new Mot("dansant", "d@s@");
		Mot esse = new Mot("esse", "Es");
		

		assertEquals(
				"[(s -> s), (an -> @), (s$ -> )]",
				sans.listeReglesPhonetiques(regles).toString());

		assertEquals(
				"[(d -> d), (an -> @), (s$ -> )]",
				dans.listeReglesPhonetiques(regles).toString());

		assertEquals(
				"[(e(?=[bcdfghklmnpqrstvwxz]{2}) -> E), (ss -> s), (e$ -> )]",
				esse.listeReglesPhonetiques(regles).toString());
		
		// On n'a pas précisé de règle pour prononcer 't', on ne peut donc pas prononcer 'dansant'
		assertNull(enfant.listeReglesPhonetiques(regles));
	}
}