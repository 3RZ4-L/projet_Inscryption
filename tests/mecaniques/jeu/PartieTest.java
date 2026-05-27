package mecaniques.jeu;

import mecaniques.cartes.Animal;
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
    public void test_constructeur_scoreInitial() {
        assertEquals("Le score initial doit être 0", 0, partie.getScore());
    }

    @Test
    public void test_commencerAttaques_lignesVides() {
        Ligne joueur = new Ligne();
        Ligne adversaire = new Ligne();

        int score = partie.commencerAttaques(joueur, adversaire);

        assertEquals("Aucune attaque possible, score doit rester 0", 0, score);
    }

    @Test
    public void test_commencerAttaques_attaqueDirecte_caseAdversaireVide() {
        Ligne joueur = new Ligne();
        Ligne adversaire = new Ligne();
        Animal attaquant = new Animal("Lion", 5, 4, 2, 1, false);
        joueur.placerCarte(attaquant, 0);

        int score = partie.commencerAttaques(joueur, adversaire);

        assertEquals("L'attaque directe doit ajouter la valeur d'attaque au score", 4, score);
    }

    @Test
    public void test_commencerAttaques_degatsNormaux() {
        Ligne joueur = new Ligne();
        Ligne adversaire = new Ligne();
        Animal attaquant = new Animal("Lion", 5, 4, 2, 1, false);
        Animal defenseur = new Animal("Proie", 4, 1, 0, 0, false);
        joueur.placerCarte(attaquant, 0);
        adversaire.placerCarte(defenseur, 0);

        int score = partie.commencerAttaques(joueur, adversaire);
        score = partie.commencerAttaques(joueur, adversaire);

        assertEquals(4, score);
        assertTrue(defenseur.estMort());
    }

    @Test
    public void test_commencerAttaques_degatsNuls_carteNonTuee() {
        Ligne joueur = new Ligne();
        Ligne adversaire = new Ligne();
        Animal attaquant = new Animal("A", 5, 3, 2, 1, false);
        Animal defenseur = new Animal("B", 10, 1, 0, 0, false);
        joueur.placerCarte(attaquant, 0);
        adversaire.placerCarte(defenseur, 0);

        int score = partie.commencerAttaques(joueur, adversaire);

        assertEquals(0, score);
        assertEquals(7, defenseur.getPv());
    }

    @Test
    public void test_commencerAttaques_attaqueVolante_attaqueDirecte() {
        Ligne joueur = new Ligne();
        Ligne adversaire = new Ligne();
        Animal volant = new Animal("A", 5, 4, 2, 1, true);
        Animal defenseur = new Animal("B", 10, 1, 0, 0, false);
        joueur.placerCarte(volant, 0);
        adversaire.placerCarte(defenseur, 0);

        int score = partie.commencerAttaques(joueur, adversaire);

        assertEquals(4, score);
        assertEquals(10, defenseur.getPv());
    }

    @Test
    public void test_commencerAttaques_limiteScore5() {
        Ligne joueur = new Ligne();
        Ligne adversaire = new Ligne();

        for (int i = 0; i < 4; i++) {
            joueur.placerCarte(new Animal("Fort", 10, 10, 0, 0, false), i);
            adversaire.placerCarte(new Animal("Faible", 1, 0, 0, 0, false), i);
        }

        int score = partie.commencerAttaques(joueur, adversaire);

        assertTrue("Le score ne doit pas dépasser 5", score <= 5);
    }

    @Test
    public void test_commencerAttaques_casesMelangees() {
        Ligne joueur = new Ligne();
        Ligne adversaire = new Ligne();

        joueur.placerCarte(new Animal("Attaquant1", 5, 4, 0, 0, false), 0);
        adversaire.placerCarte(new Animal("Defenseur1", 10, 0, 0, 0, false), 1);
        joueur.placerCarte(new Animal("Attaquant2", 5, 3, 0, 0, false), 2);

        int score = partie.commencerAttaques(joueur, adversaire);

        assertTrue(score >= -5 && score <= 5);
    }

    @Test
    public void test_commencerAttaques_attaqueZero() {
        Ligne joueur = new Ligne();
        Ligne adversaire = new Ligne();
        Animal attaquant = new Animal("Mouton", 3, 0, 1, 0, false);
        joueur.placerCarte(attaquant, 0);

        int score = partie.commencerAttaques(joueur, adversaire);

        assertEquals(0, score);
    }

    @Test
    public void test_commencerAttaques_plusieursToursBoucle() {
        Ligne joueur = new Ligne();
        Ligne adversaire = new Ligne();

        joueur.placerCarte(new Animal("Machin", 10, 1, 0, 0, false), 0);

        int score = partie.commencerAttaques(joueur, adversaire);
        score += partie.commencerAttaques(joueur, adversaire);
        score += partie.commencerAttaques(joueur, adversaire);

        assertEquals(3, score);
    }

}