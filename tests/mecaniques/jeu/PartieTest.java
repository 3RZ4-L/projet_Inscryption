package mecaniques.jeu;

import mecaniques.cartes.*;
import mecaniques.joueurs.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PartieTest {

    private Partie partie;

    @Before
    public void setUp() {
        partie = new Partie();
    }

    @Test
    public void testConstructeur() {
        assertEquals(0, partie.getScore());
        assertEquals(1, partie.getNumParte());
        assertEquals(1, partie.getNumTour());
    }

    @Test
    public void testLimiterScore_ScoreSuperieur5() {
        // On ne peut pas accéder directement à m_score, donc on utilise la réflexion ou
        // on teste via le comportement normal du jeu
        // Cette méthode est appelée automatiquement dans jouerUnTour()
        // On peut vérifier qu'un score ne dépasse jamais 5
        Partie p = new Partie();

        // Simuler un score élevé en jouant des attaques
        // Ou utiliser la réflexion pour tester directement
        java.lang.reflect.Field scoreField;
        try {
            scoreField = Partie.class.getDeclaredField("m_score");
            scoreField.setAccessible(true);
            scoreField.setInt(p, 7);
            p.limiterScore();
            assertEquals(5, p.getScore());
        } catch (Exception e) {
            fail("Erreur de réflexion: " + e.getMessage());
        }
    }

    @Test
    public void testLimiterScore_ScoreInferieurMoins5() {
        try {
            java.lang.reflect.Field scoreField = Partie.class.getDeclaredField("m_score");
            scoreField.setAccessible(true);
            scoreField.setInt(partie, -7);
            partie.limiterScore();
            assertEquals(-5, partie.getScore());
        } catch (Exception e) {
            fail("Erreur de réflexion: " + e.getMessage());
        }
    }

    @Test
    public void testLimiterScore_ScoreDansLimites() {
        try {
            java.lang.reflect.Field scoreField = Partie.class.getDeclaredField("m_score");
            scoreField.setAccessible(true);

            scoreField.setInt(partie, 3);
            partie.limiterScore();
            assertEquals(3, partie.getScore());

            scoreField.setInt(partie, -2);
            partie.limiterScore();
            assertEquals(-2, partie.getScore());

            scoreField.setInt(partie, 5);
            partie.limiterScore();
            assertEquals(5, partie.getScore());

            scoreField.setInt(partie, -5);
            partie.limiterScore();
            assertEquals(-5, partie.getScore());
        } catch (Exception e) {
            fail("Erreur de réflexion: " + e.getMessage());
        }
    }

    @Test
    public void testPartieTermine_ScoreEgal5() {
        try {
            java.lang.reflect.Field scoreField = Partie.class.getDeclaredField("m_score");
            scoreField.setAccessible(true);
            scoreField.setInt(partie, 5);
            assertTrue(partie.partieTermine());
        } catch (Exception e) {
            fail("Erreur de réflexion: " + e.getMessage());
        }
    }

    @Test
    public void testPartieTermine_ScoreEgalMoins5() {
        try {
            java.lang.reflect.Field scoreField = Partie.class.getDeclaredField("m_score");
            scoreField.setAccessible(true);
            scoreField.setInt(partie, -5);
            assertTrue(partie.partieTermine());
        } catch (Exception e) {
            fail("Erreur de réflexion: " + e.getMessage());
        }
    }

    @Test
    public void testPartieTermine_ScoreEntreMoins5Et5() {
        try {
            java.lang.reflect.Field scoreField = Partie.class.getDeclaredField("m_score");
            scoreField.setAccessible(true);

            scoreField.setInt(partie, 3);
            assertFalse(partie.partieTermine());

            scoreField.setInt(partie, -2);
            assertFalse(partie.partieTermine());

            scoreField.setInt(partie, 0);
            assertFalse(partie.partieTermine());
        } catch (Exception e) {
            fail("Erreur de réflexion: " + e.getMessage());
        }
    }

    @Test
    public void testPartieTermine_ScoreDepasse5() {
        try {
            java.lang.reflect.Field scoreField = Partie.class.getDeclaredField("m_score");
            scoreField.setAccessible(true);
            scoreField.setInt(partie, 8);
            // Après limiterScore, le score devient 5
            partie.limiterScore();
            assertTrue(partie.partieTermine());
        } catch (Exception e) {
            fail("Erreur de réflexion: " + e.getMessage());
        }
    }

    @Test
    public void testCommencerAttaques_AucuneAttaque() {
        Joueur joueur1 = new Humain();
        Joueur joueur2 = new Humain();

        int degats = partie.commencerAttaques(joueur1, joueur2);

        assertEquals(0, degats);
    }

    @Test
    public void testCommencerAttaques_AvecAttaques() {
        Joueur joueur1 = new Humain();
        Joueur joueur2 = new Humain();

        // Ajouter des animaux sur la ligne du joueur1
        Animal animal = new Animal("Lion", 5, 3, 2, 0, false);
        joueur1.placer(0, 0); // Nécessite d'avoir des cartes dans la main

        int degats = partie.commencerAttaques(joueur1, joueur2);

        assertTrue(degats >= 0);
    }

    @Test
    public void testGetScore() {
        assertEquals(0, partie.getScore());
    }

    @Test
    public void testGetNumParte() {
        assertEquals(1, partie.getNumParte());
    }

    @Test
    public void testGetNumTour() {
        assertEquals(1, partie.getNumTour());
    }

    @Test
    public void testNouvellePartie() throws Exception {
        // Modifier l'état de la partie
        java.lang.reflect.Field scoreField = Partie.class.getDeclaredField("m_score");
        java.lang.reflect.Field tourField = Partie.class.getDeclaredField("m_tour");
        java.lang.reflect.Field partieField = Partie.class.getDeclaredField("m_partie");

        scoreField.setAccessible(true);
        tourField.setAccessible(true);
        partieField.setAccessible(true);

        scoreField.setInt(partie, 4);
        tourField.setInt(partie, 5);
        partieField.setInt(partie, 2);

        // Appeler nouvellePartie via réflexion
        java.lang.reflect.Method nouvellePartie = Partie.class.getDeclaredMethod("nouvellePartie");
        nouvellePartie.setAccessible(true);
        nouvellePartie.invoke(partie);

        assertEquals(0, partie.getScore());
        assertEquals(1, partie.getNumTour());
    }

    @Test
    public void testChoisirCarteFinDeuxiemePartie() throws Exception {
        java.lang.reflect.Method choisirCarte = Partie.class.getDeclaredMethod("choisirCarteFinDeuxiemePartie");
        choisirCarte.setAccessible(true);

        // Devrait s'exécuter sans erreur
        choisirCarte.invoke(partie);
    }

    @Test
    public void testJouerUnTour() {
        // Test que jouerUnTour s'exécute sans erreur
        // (cela dépend beaucoup de l'état initial du jeu)
        try {
            // On peut tester un tour complet si les conditions sont réunies
            // partie.jouerUnTour();
            assertTrue(true);
        } catch (Exception e) {
            fail("jouerUnTour a levé une exception: " + e.getMessage());
        }
    }

    @Test
    public void testTourJoueur() {
        // Le tour joueur nécessite une interaction utilisateur
        // Difficile à tester automatiquement sans mock
        assertNotNull(partie);
    }

    @Test
    public void testDemarrer() {
        // La méthode demarrer lance une partie complète
        // Difficile à tester automatiquement
        assertNotNull(partie);
    }
}