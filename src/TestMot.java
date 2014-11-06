import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

/**
 * 
 */

/**
 * @author olojkine
 * 
 */
public class TestMot {

	@Test
	public void testListeReglesPhonetiques() {
		// Création des règles de prononciation (l'ordre est important)
		ArrayList<ReglePhonetique> regles = new ArrayList<>();
		regles.add(new RegleSubstitutionSimple("d", "d"));
		// '@' correspond au son 'an' comme dans "enfant" (@f@)
		regles.add(new RegleSubstitutionSimple("an", "@"));
		// 's' en fin de mot ne se prononce pas
		regles.add(new RegleSubstitutionFin("s", ""));
		regles.add(new RegleSubstitutionSimple("s", "s"));

		// Création des mots (on précise leur prononciation
		Mot sans = new Mot("sans", "s@");
		Mot dans = new Mot("dans", "d@");
		Mot enfant = new Mot("dansant", "d@s@");

		assertEquals(
				"[RegleSubstitutionSimple [ortho=s, phono=s], RegleSubstitutionSimple [ortho=an, phono=@], RegleSubstitutionSimple [ortho=s, phono=]]",
				sans.listeReglesPhonetiques(regles).toString());

		assertEquals(
				"[RegleSubstitutionSimple [ortho=d, phono=d], RegleSubstitutionSimple [ortho=an, phono=@], RegleSubstitutionSimple [ortho=s, phono=]]",
				dans.listeReglesPhonetiques(regles).toString());
		
		// On n'a pas précisé de règle pour prononcer 't', on ne peut donc pas prononcer 'dansant'
		assertNull(enfant.listeReglesPhonetiques(regles));
	}

}
