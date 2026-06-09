package mecaniques.joueurs;

import mecaniques.cartes.Animal;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class JoueurTest {

    private Joueur joueur;

    @Before
    public void setUp() {
        joueur = new Joueur();
    }

    @Test
    public void testConstructeur() {
        assertEquals(4, joueur.getNbMain());
        assertEquals(0, joueur.getOs());
        assertEquals(0, joueur.getGoutteSang());
        assertNotNull(joueur.getLigne());
    }

    @Test
    public void testPiocher() {
        int avant = joueur.getNbMain();
        boolean resultat = joueur.piocher();

        assertTrue(resultat);
        assertEquals(avant + 1, joueur.getNbMain());
    }

    @Test
    public void testAjouterDansMain() {
        Animal animal = new Animal("Chat", 3, 2, 1, 0, false);
        int avant = joueur.getNbMain();

        joueur.ajouterDansMain(animal);

        assertEquals(avant + 1, joueur.getNbMain());
        assertTrue(joueur.getMain().contains(animal));
    }

    @Test
    public void testPlacer_NumeroCarteInvalide() {
        int avant = joueur.getNbMain();

        boolean resultat = joueur.placer(99, 0);

        assertFalse(resultat);
        assertEquals(avant, joueur.getNbMain());
    }

    @Test
    public void testPlacer_NumeroCarteZero() {
        int avant = joueur.getNbMain();

        boolean resultat = joueur.placer(0, 0);

        assertFalse(resultat);
        assertEquals(avant, joueur.getNbMain());
    }

    @Test
    public void testGetNbMain() {
        assertEquals(4, joueur.getNbMain());
        joueur.piocher();
        assertEquals(5, joueur.getNbMain());
    }

    @Test
    public void testGetOs() {
        assertEquals(0, joueur.getOs());
    }

    @Test
    public void testGetGoutteSang() {
        assertEquals(0, joueur.getGoutteSang());
    }

    @Test
    public void testGetMain() {
        assertNotNull(joueur.getMain());
        assertEquals(4, joueur.getMain().size());
    }

    @Test
    public void testGetLigne() {
        assertNotNull(joueur.getLigne());
    }

    @Test
    public void testGetNbPioche() {
        assertEquals(11, joueur.getNbPioche()); // 15 - 4 piochées = 11
        joueur.piocher();
        assertEquals(10, joueur.getNbPioche());
    }


    @Test
    public void testTuerAdversaire() {
        joueur.tuerAdversaire();
        assertEquals(1, joueur.getOs());

        joueur.tuerAdversaire();
        assertEquals(2, joueur.getOs());
    }
}