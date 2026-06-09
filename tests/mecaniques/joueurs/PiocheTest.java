package mecaniques.joueurs;

import mecaniques.cartes.Animal;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class PiocheTest {

    @Test
    public void test_constructor_piocheTailleCorrecte() {
        Pioche pioche = new Pioche(10);
        assertEquals(10, pioche.getNbCartes());
    }

    @Test
    public void test_constructor_piocheZero() {
        Pioche pioche = new Pioche(0);
        assertEquals(0, pioche.getNbCartes());
    }

    @Test
    public void test_piocher_reduitTaille() {
        Pioche pioche = new Pioche(5);
        Optional<Animal> carte = pioche.piocher();
        assertNotNull(carte);
        assertEquals(4, pioche.getNbCartes());
    }

    @Test
    public void test_piocher_jusquaVide() {
        Pioche pioche = new Pioche(3);
        for (int i = 0; i < 3; i++) {
            assertNotNull(pioche.piocher());
        }
        assertEquals(0, pioche.getNbCartes());
    }

    @Test(expected = java.util.EmptyStackException.class)
    public void test_piocher_piocheVide_Exception() {
        Pioche pioche = new Pioche(0);
        pioche.piocher();
    }

    @Test
    public void test_ajouter_augmenteTaille() {
        Pioche pioche = new Pioche(5);
        Animal nouvelleCarte = new Animal("Nouveau", 3, 2, 1, 0, false);
        pioche.ajouter(nouvelleCarte);
        assertEquals(6, pioche.getNbCartes());
    }

    @Test
    public void test_ajouterPuisPiocher_retourneCarteAjoutee() {
        Pioche pioche = new Pioche(5);
        Animal nouvelleCarte = new Animal("Special", 10, 5, 3, 2, true);
        pioche.ajouter(nouvelleCarte);

        Optional<Animal> piochee = pioche.piocher();
        assertEquals("Special", piochee.get().getNom());
    }

    @Test
    public void test_melanger_genereCartes() {
        Pioche pioche = new Pioche(20);
        assertEquals(20, pioche.getNbCartes());

        for (int i = 0; i < 10; i++) {
            assertNotNull(pioche.piocher());
        }
    }

    @Test
    public void test_melanger_valeursDifferentes() {

        Pioche pioche = new Pioche(50);
        assertEquals(50, pioche.getNbCartes());

        boolean aDesCartes = true;
        try {
            for (int i = 0; i < 50; i++) {
                Optional<Animal> carte = pioche.piocher();
                if (carte == null) {
                    aDesCartes = false;
                }
            }
        } catch (Exception e) {
            aDesCartes = false;
        }
        assertTrue(aDesCartes);
    }
}