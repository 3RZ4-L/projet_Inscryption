package mecaniques.cartes;

import org.junit.Test;
import static org.junit.Assert.*;
import static mecaniques.cartes.Pouvoirs.*;

import java.util.List;
import java.util.Optional;

public class AnimalTest {

    @Test
    public void testPouvoirPuant() {
        // Un animal avec Puant réduit les dégâts reçus de 1
        Animal animalAvecPuant = new Animal("Mouffette", 5, 3, 1, 0, false, PUANT);
        Animal attaquant = new Animal("Prédateur", 5, 3, 1, 0, false);

        int degats = animalAvecPuant.doDamage(Optional.of(attaquant));

        // L'attaquant inflige normalement 3 dégâts, mais Puant réduit de 1
        assertEquals(2, degats);
        assertEquals(3, animalAvecPuant.getPv()); // 5 - 2 = 3
    }

    @Test
    public void testPouvoirCroissance() {
        // Un animal avec Croissance peut se transformer en Loup
        Animal animal = new Animal("Louveteau", 1, 1, 1, 0, false, CROISSANCE);

        Optional<Carte> resultat = animal.croissance();

        assertTrue(resultat.isPresent());
        Animal loup = (Animal) resultat.get();
        assertEquals("Loup", loup.getNom());
        assertEquals(2, loup.getPv());
        assertEquals(3, loup.getAttack());
        assertEquals(2, loup.getGouttesSang());
        assertEquals(0, loup.getOs());
        assertFalse(loup.estVolant());
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
        assertEquals(10, cible.getPv()); // Pas de dégâts, donc pas de mort instantanée
        assertFalse(cible.estMort());
    }

    @Test
    public void testPouvoirPiquesPointues() {
        // Piques Pointues inflige 1 dégât à l'attaquant
        Animal piquesPointues = new Animal("Porc-épic", 5, 2, 1, 0, false, PIQUES_POINTUES);
        Animal attaquant = new Animal("Prédateur", 4, 3, 1, 0, false);
        int pvAttaquantAvant = attaquant.getPv();

        int degats = piquesPointues.doDamage(Optional.of(attaquant));

        assertEquals(2, degats);
        assertEquals(pvAttaquantAvant - 1, attaquant.getPv());
        assertEquals(3, piquesPointues.getPv()); // 5 - 2 = 3
    }

    @Test
    public void testPouvoirPiquesPointuesNeBlessePasSiZeroDegats() {
        // Piques Pointues ne blesse pas l'attaquant si aucun dégât n'est infligé
        Animal piquesPointues = new Animal("Porc-épic", 5, 0, 1, 0, false, PIQUES_POINTUES);
        Animal attaquant = new Animal("Prédateur", 4, 3, 1, 0, false);
        int pvAttaquantAvant = attaquant.getPv();

        int degats = piquesPointues.doDamage(Optional.of(attaquant));

        assertEquals(0, degats);
        assertEquals(pvAttaquantAvant, attaquant.getPv()); // Pas de blessure
    }

    @Test
    public void testPouvoirNombreusesVies() {
        // Nombreuses Vies permet de revenir à la vie (à implémenter)
        Animal animal = new Animal("Phoenix", 5, 3, 2, 0, false, NOMBREUSES_VIES);

        assertTrue(animal.aPouvoir(NOMBREUSES_VIES));
        // Note: La mécanique complète de Nombreuses Vies reste à implémenter
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

    @Test
    public void testGetPouvoir() {
        Animal animal = new Animal("Test", 5, 3, 1, 0, false, PUANT);
        animal.ajouterPouvoir(COUREUR);

        Optional<List<Pouvoirs>> pouvoirs = animal.getPouvoir();

        assertTrue(pouvoirs.isPresent());
        assertEquals(2, pouvoirs.get().size());
        assertTrue(pouvoirs.get().contains(PUANT));
        assertTrue(pouvoirs.get().contains(COUREUR));
    }

    @Test
    public void testGetNamePouvoir() {
        Animal animal = new Animal("Test", 5, 3, 1, 0, false, PUANT);
        animal.ajouterPouvoir(COUREUR);

        String nomsPouvoirs = animal.getNamePouvoir();

        assertTrue(nomsPouvoirs.contains("Puant"));
        assertTrue(nomsPouvoirs.contains("Coureur"));
    }
}