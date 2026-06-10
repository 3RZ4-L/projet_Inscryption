package mecaniques.cartes;

import org.junit.Test;
import static org.junit.Assert.*;
import static mecaniques.cartes.Pouvoirs.*;

import java.util.List;
import java.util.Optional;

public class AnimalTest {

    @Test
    public void testPouvoirPuant() {
        Animal punaise = new Animal("Punaise", 5, 1, 0, 2, false, PUANT);
        Animal grizzly = new Animal("Grizzly", 6, 4, 3, 0, false);

        int dmg = grizzly.doDamage(Optional.of(punaise));

        assertEquals(3, dmg);
        assertEquals(2, punaise.getPv());
    }

    @Test
    public void testPouvoirCroissance() {
        Animal animal = new Animal("Louveteau", 1, 1, 1, 0, false, CROISSANCE);

        // 2 tours pour évoluer
        animal.ajouterTour();
        animal.ajouterTour();

        Optional<Carte> resultat = animal.croissance();

        assertTrue(resultat.isPresent());
        Animal loup = (Animal) resultat.get();
        assertEquals("Loup", loup.getNom());
    }

    @Test
    public void testPouvoirCroissanceNonPresent() {
        // Un animal sans Croissance ne se transforme pas
        Animal animal = new Animal("Chat", 3, 2, 1, 0, false);

        Optional<Carte> resultat = animal.croissance();

        assertFalse(resultat.isPresent());
    }

    @Test
    public void testPouvoirCroissanceRefuseSurLoup() {
        // Un Loup ne peut pas recevoir le pouvoir Croissance
        Animal loup = new Animal("Loup", 2, 3, 2, 0, false);

        boolean ajoutReussi = loup.ajouterPouvoir(CROISSANCE);

        assertFalse(ajoutReussi);
        assertFalse(loup.aPouvoir(CROISSANCE));
    }

    @Test
    public void testPouvoirCoureur() {
        // Coureur est un pouvoir qui peut être ajouté
        Animal animal = new Animal("Guépard", 4, 3, 2, 0, false);

        boolean ajoutReussi = animal.ajouterPouvoir(COUREUR);

        assertTrue(ajoutReussi);
        assertTrue(animal.aPouvoir(COUREUR));
    }

    @Test
    public void testPouvoirContactMortel() {
        // Contact Mortel tue instantanément la cible si des dégâts sont infligés
        Animal contactMortel = new Animal("Scorpion", 3, 2, 1, 0, false, CONTACT_MORTEL);
        Animal cible = new Animal("Proie", 10, 1, 1, 0, false);

        int degats = contactMortel.doDamage(Optional.of(cible));

        assertEquals(2, degats);
        assertEquals(0, cible.getPv());
        assertTrue(cible.estMort());
    }

    @Test
    public void testPouvoirContactMortelNeTuePasSiZeroDegats() {
        // Contact Mortel ne tue pas si les dégâts sont à 0
        Animal contactMortel = new Animal("Scorpion", 3, 0, 1, 0, false, CONTACT_MORTEL);
        Animal cible = new Animal("Proie", 10, 1, 1, 0, false);

        int degats = contactMortel.doDamage(Optional.of(cible));

        assertEquals(0, degats);
        assertEquals(10, cible.getPv());
        assertFalse(cible.estMort());
    }

    @Test
    public void testPouvoirPiquesPointues() {
        // La cible a Piques Pointues - elle blesse l'attaquant
        Animal porcEpic = new Animal("Porc-épic", 2, 1, 1, 0, false, PIQUES_POINTUES);
        Animal attaquant = new Animal("Grizzly", 6, 3, 3, 0, false);
        int pvAttaquantAvant = attaquant.getPv();

        int degats = attaquant.doDamage(Optional.of(porcEpic));

        assertEquals(3, degats);
        assertEquals(pvAttaquantAvant - 1, attaquant.getPv());
        assertEquals(0, porcEpic.getPv());
    }

    @Test
    public void testPouvoirPiquesPointuesNeBlessePasSiZeroDegats() {
        // Piques Pointues ne blesse pas l'attaquant si aucun dégât n'est infligé
        Animal piquesPointues = new Animal("Porc-épic", 5, 0, 1, 0, false, PIQUES_POINTUES);
        Animal attaquant = new Animal("Prédateur", 4, 3, 1, 0, false);
        int pvAttaquantAvant = attaquant.getPv();

        int degats = piquesPointues.doDamage(Optional.of(attaquant));

        assertEquals(0, degats);
        assertEquals(pvAttaquantAvant, attaquant.getPv());
    }

    @Test
    public void testPouvoirNombreusesVies() {
        // Nombreuses Vies permet d'être imortel (yaaaay)
        Animal animal = new Animal("Phoenix", 5, 3, 2, 0, false, NOMBREUSES_VIES);

        assertTrue(animal.aPouvoir(NOMBREUSES_VIES));
    }

    @Test
    public void testAjouterPouvoirUnique() {
        // On ne peut pas ajouter deux fois le même pouvoir
        Animal animal = new Animal("Test", 5, 3, 1, 0, false);

        boolean premierAjout = animal.ajouterPouvoir(PUANT);
        boolean deuxiemeAjout = animal.ajouterPouvoir(PUANT);

        assertTrue(premierAjout);
        assertFalse(deuxiemeAjout);
    }

}