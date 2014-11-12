package phonetique;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

/**
 * @author olojkine
 * 
 */
public class TestMot {

	@Test
	public void testListeReglesPhonetiques() {
		// Création des règles de prononciation (l'ordre est important)
		ListeRegles regles = new ListeRegles();
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

		ListeReglesRecherche rech = new ListeReglesRecherche(regles);

		double startTime = System.currentTimeMillis();
		assertEquals("[(s -> s), (an -> @), (s$ -> )]", sans
				.listeReglesPhonetiques(rech).toString());

		assertEquals("[(d -> d), (an -> @), (s$ -> )]", dans
				.listeReglesPhonetiques(rech).toString());

		assertEquals(
				"[(e(?=[bcdfghklmnpqrstvwxz]{2}) -> E), (ss -> s), (e$ -> )]",
				esse.listeReglesPhonetiques(rech).toString());

		// On n'a pas précisé de règle pour prononcer 't', on ne peut donc pas
		// prononcer 'dansant'
		assertNull(enfant.listeReglesPhonetiques(rech));
		double endTime = System.currentTimeMillis();
		System.out.printf("Search time: %g ms.\n", (endTime-startTime));
	}
}