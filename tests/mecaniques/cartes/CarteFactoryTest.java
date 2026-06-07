package mecaniques.cartes;

import org.junit.Test;

import static org.junit.Assert.*;

public class CarteFactoryTest {
    @Test
    public void testGetDeuxAnimaux() {
        Animal[] animaux = CarteFactory.getDeuxAnimaux();

        assertEquals(2, animaux.length);
        assertNotNull(animaux[0]);
        assertNotNull(animaux[1]);

        assertNotEquals("", animaux[0].getNamePouvoir());
        assertNotEquals("", animaux[1].getNamePouvoir());
    }

    @Test
    public void testGetCarteAleatoire() {
        Animal carte = CarteFactory.getCarteAleatoire();
        assertNotNull(carte);
        assertNotNull(carte.getNom());
    }
}