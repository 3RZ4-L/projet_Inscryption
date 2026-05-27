package mecaniques.jeu;

import mecaniques.cartes.Animal;
import mecaniques.cartes.Carte;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LigneTest {

    private Ligne ligne;
    private Animal testAnimal;

    @Before
    public void setUp() {
        ligne = new Ligne();
        testAnimal = new Animal("Test", 5, 3, 2, 1, false);
    }

    @Test
    public void test_constructor_ligneVide() {
        for (int i = 0; i < 4; i++) {
            assertTrue(ligne.estCaseVide(i));
        }
    }

    @Test
    public void test_estCaseVide_caseVide() {
        assertTrue(ligne.estCaseVide(0));
        assertTrue(ligne.estCaseVide(1));
        assertTrue(ligne.estCaseVide(2));
        assertTrue(ligne.estCaseVide(3));
    }

    @Test
    public void test_estCaseVide_caseOccupee() {
        ligne.placerCarte(testAnimal, 0);
        assertFalse(ligne.estCaseVide(0));
        assertTrue(ligne.estCaseVide(1));
    }

    @Test
    public void test_placerCarte_caseVide_retourneFalse() {
        boolean resultat = ligne.placerCarte(testAnimal, 0);
        assertFalse(resultat);
        assertFalse(ligne.estCaseVide(0));
    }

    @Test
    public void test_placerCarte_caseOccupee_retourneTrue() {
        ligne.placerCarte(testAnimal, 0);
        Animal nouvelleCarte = new Animal("Nouveau", 3, 2, 1, 0, false);
        boolean resultat = ligne.placerCarte(nouvelleCarte, 0);
        assertTrue(resultat);
    }

    @Test
    public void test_placerCarte_remplaceCarteExistante() {
        ligne.placerCarte(testAnimal, 0);
        Animal nouvelleCarte = new Animal("Nouveau", 3, 2, 1, 0, false);
        ligne.placerCarte(nouvelleCarte, 0);

        Carte[] cartes = ligne.getCartes();
        assertEquals("Nouveau", cartes[0].getNom());
    }

    @Test
    public void test_placerCarte_positionsLimites() {
        assertFalse(ligne.placerCarte(testAnimal, 0));

        Animal autre = new Animal("Autre", 3, 2, 1, 0, false);
        assertTrue(ligne.placerCarte(autre, 0));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void test_placerCarte_positionInvalide_negative() {
        ligne.placerCarte(testAnimal, -1);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void test_placerCarte_positionInvalide_tropGrande() {
        ligne.placerCarte(testAnimal, 4);
    }

    @Test
    public void test_getCartes_retourneTableau() {
        Carte[] cartes = ligne.getCartes();
        assertNotNull(cartes);
        assertEquals(4, cartes.length);
    }

    @Test
    public void test_getCartes_apresPlacements() {
        ligne.placerCarte(testAnimal, 0);
        Animal autre = new Animal("Autre", 3, 2, 1, 0, false);
        ligne.placerCarte(autre, 2);

        Carte[] cartes = ligne.getCartes();
        assertEquals(testAnimal, cartes[0]);
        assertNull(cartes[1]);
        assertEquals(autre, cartes[2]);
        assertNull(cartes[3]);
    }
}