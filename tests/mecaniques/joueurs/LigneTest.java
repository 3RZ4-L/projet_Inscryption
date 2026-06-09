package mecaniques.joueurs;

import mecaniques.cartes.Animal;
import mecaniques.cartes.Carte;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static mecaniques.cartes.Pouvoirs.*;
import static org.junit.Assert.*;

public class LigneTest {
    private Ligne ligne;
    private Animal chat;
    private Animal louveteau;
    private Animal grizzly;

    @Before
    public void setUp() {
        ligne = new Ligne();
        chat = new Animal("Chat", 1, 0, 1, 0, false, NOMBREUSES_VIES);
        louveteau = new Animal("Louveteau", 1, 1, 1, 0, false, CROISSANCE);
        grizzly = new Animal("Grizzly", 6, 4, 3, 0, false);
    }

    @Test
    public void testConstructeur() {
        assertNotNull(ligne);
        for (int i = 0; i < 4; i++) {
            assertTrue(ligne.estCaseVide(i));
        }
    }

    @Test
    public void testPlacerCarte() {
        assertTrue(ligne.placerCarte(chat, 0));
        assertFalse(ligne.estCaseVide(0));
        assertFalse(ligne.placerCarte(louveteau, 0));
    }

    @Test
    public void testEstCaseVide() {
        assertTrue(ligne.estCaseVide(0));
        assertTrue(ligne.estCaseVide(3));

        ligne.placerCarte(chat, 0);
        assertFalse(ligne.estCaseVide(0));
    }

    @Test
    public void testSacrifier() {
        ligne.placerCarte(louveteau, 0);
        assertTrue(ligne.sacrifier(0));
        assertTrue(ligne.estCaseVide(0));

        assertFalse(ligne.sacrifier(1));

        ligne.placerCarte(chat, 0);
        assertTrue(ligne.sacrifier(0));
        assertFalse(ligne.estCaseVide(0));
    }

    @Test
    public void testCroissance() {
        ligne.placerCarte(louveteau, 0);
        ligne.placerCarte(chat, 1);
        ligne.getCarte(0).get().ajouterTour();
        ligne.getCarte(0).get().ajouterTour();
        ligne.getCarte(1).get().ajouterTour();
        ligne.getCarte(1).get().ajouterTour();
        ligne.croissance();

        Optional<Carte> carte0 = ligne.getCarte(0);
        assertTrue(carte0.isPresent());
        assertEquals("Loup", carte0.get().getNom());

        Optional<Carte> carte1 = ligne.getCarte(1);
        assertTrue(carte1.isPresent());
        assertEquals("Chat", carte1.get().getNom());
    }

    @Test
    public void testGetCarte() {
        ligne.placerCarte(chat, 0);
        Optional<Carte> carte = ligne.getCarte(0);
        assertTrue(carte.isPresent());
        assertEquals("Chat", carte.get().getNom());

        Optional<Carte> carteVide = ligne.getCarte(1);
        assertTrue(carteVide.isEmpty());

        Optional<Carte> carteInvalide = ligne.getCarte(5);
        assertTrue(carteInvalide.isEmpty());
    }

    @Test
    public void testRemoveCart() {
        ligne.placerCarte(chat, 0);
        assertFalse(ligne.estCaseVide(0));

        ligne.removeCart(0);
        assertTrue(ligne.estCaseVide(0));

        ligne.removeCart(5);
    }
}