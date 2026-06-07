package mecaniques.joueurs;

import mecaniques.cartes.Animal;
import org.junit.Before;
import org.junit.Test;

import static mecaniques.cartes.Pouvoirs.NOMBREUSES_VIES;
import static org.junit.Assert.*;

public class JoueurTest {
    private Joueur joueur;
    private Animal chat;
    private Animal grizzly;

    @Before
    public void setUp() {
        joueur = new Joueur();
        chat = new Animal("Chat", 1, 0, 1, 0, false, NOMBREUSES_VIES);
        grizzly = new Animal("Grizzly", 6, 4, 3, 0, false);
    }

    @Test
    public void testConstructeur() {
        assertEquals(4, joueur.getNbMain()); // 4 cartes piochées au début
        assertEquals(0, joueur.getOs());
        assertEquals(0, joueur.getGoutteSang());
        assertNotNull(joueur.getLigne());
    }

    @Test
    public void testPiocher() {
        int tailleMain = joueur.getNbMain();
        joueur.piocher();
        assertEquals(tailleMain + 1, joueur.getNbMain());
    }

    @Test
    public void testPlacer() {
        joueur.sacrifier(0);
        Animal carte = joueur.getMain().get(0);

        boolean placement = joueur.placer(1, 0);
        assertNotNull(placement);
    }

    @Test
    public void testSacrifier() {
        int osInitial = joueur.getOs();
        int gouttesInitial = joueur.getGoutteSang();

        joueur.sacrifier(0);

        assertTrue(joueur.getOs() > osInitial);
        assertTrue(joueur.getGoutteSang() > gouttesInitial);
    }

    @Test
    public void testTuerAdversaire() {
        int osInitial = joueur.getOs();
        joueur.tuerAdversaire();
        assertEquals(osInitial + 1, joueur.getOs());
    }
}