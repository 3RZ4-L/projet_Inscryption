package mecaniques.joueurs;

import mecaniques.cartes.Animal;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static mecaniques.cartes.Pouvoirs.NULL;

public class JoueurTest {

    private Joueur joueur;

    @Before
    public void setUp() {
        joueur = new Joueur();
    }

    @Test
    public void test_constructor_main4Cartes() {
        assertEquals(4, joueur.getNbCartes());
    }

    @Test
    public void test_constructor_scoreOsInitial() {
        assertEquals(0, joueur.getOs());
    }

    @Test
    public void test_constructor_goutteSangInitial() {
        assertEquals(0, joueur.getGoutteSang());
    }

    @Test
    public void test_constructor_ligneExistante() {
        assertNotNull(joueur.getLigne());
    }

    @Test
    public void test_piocher_augmenteMain() {
        int avant = joueur.getNbCartes();
        joueur.piocher();
        assertEquals(avant + 1, joueur.getNbCartes());
    }

    @Test
    public void test_ajouterDansPioche() {
        int avantTaillePioche = getPiocheSize(joueur);
        Animal nouvelleCarte = new Animal("Test", 5, 3, 2, 1, false);
        joueur.ajouterDansPioche(nouvelleCarte);
        assertEquals(avantTaillePioche + 1, getPiocheSize(joueur));
    }

    @Test
    public void test_placer_reduitMain() {
        int avant = joueur.getNbCartes();
        joueur.placer(1, 0);
        assertEquals(avant - 1, joueur.getNbCartes());
    }

    @Test
    public void test_placer_caseVide_pasDeSacrifice() {
        int avantOs = joueur.getOs();
        int avantGoutte = joueur.getGoutteSang();

        joueur.placer(1, 0);

        assertEquals(avantOs, joueur.getOs());
        assertEquals(avantGoutte, joueur.getGoutteSang());
    }

    @Test
    public void test_placer_caseOccupee_sacrifice() {
        joueur.placer(1, 0);
        int avantOs = joueur.getOs();
        int avantGoutte = joueur.getGoutteSang();

        joueur.placer(1, 0);

        assertEquals(avantOs + 1, joueur.getOs());
        assertEquals(avantGoutte + 1, joueur.getGoutteSang());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void test_placer_numeroCarteInvalide() {
        joueur.placer(99, 0);
    }

    @Test
    public void test_getMain_retourneListe() {
        assertNotNull(joueur.getMain());
        assertEquals(4, joueur.getMain().size());
    }

    @Test
    public void test_getLigne_retourneLigne() {
        assertNotNull(joueur.getLigne());
    }

    private int getPiocheSize(Joueur joueur) {
        try {
            java.lang.reflect.Field field = Joueur.class.getDeclaredField("m_pioche");
            field.setAccessible(true);
            Pioche pioche = (Pioche) field.get(joueur);
            return pioche.getNbCartes();
        } catch (Exception e) {
            fail("Impossible d'accéder à la pioche");
            return -1;
        }
    }
}