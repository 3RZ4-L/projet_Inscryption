package mecaniques.cartes;

import org.junit.Test;
import static org.junit.Assert.*;

public class CarteTest {

    @Test
    public void test_constructor_getNom() {
        Carte carte = new Carte("TestCard", 10);
        assertEquals("TestCard", carte.getNom());
    }

    @Test
    public void test_constructor_getPv() {
        Carte carte = new Carte("TestCard", 10);
        assertEquals(10, carte.getPv());
    }

    @Test
    public void test_estMort_pvPositif() {
        Carte carte = new Carte("TestCard", 5);
        assertFalse(carte.estMort());
    }

    @Test
    public void test_estMort_pvZero() {
        Carte carte = new Carte("TestCard", 0);
        assertTrue(carte.estMort());
    }

    @Test
    public void test_estMort_pvNegatif() {
        Carte carte = new Carte("TestCard", -3);
        assertTrue(carte.estMort());
    }

    @Test
    public void test_doDamage_retourneZero() {
        Carte carte = new Carte("TestCard", 10);
        Carte other = new Carte("Other", 5);
        assertEquals(0, carte.doDamage(other));
    }

    @Test
    public void test_attaqueDirect_retourneZero() {
        Carte carte = new Carte("TestCard", 10);
        assertEquals(0, carte.attaqueDirect());
    }

    @Test
    public void test_getAttack_retourneZero() {
        Carte carte = new Carte("TestCard", 10);
        assertEquals(0, carte.getAttack());
    }

    @Test
    public void test_estVolant_retourneFalse() {
        Carte carte = new Carte("TestCard", 10);
        assertFalse(carte.estVolant());
    }

    @Test
    public void test_getNamePouvoir_retourneChaineVide() {
        Carte carte = new Carte("TestCard", 10);
        assertEquals("", carte.getNamePouvoir());
    }

    @Test
    public void test_getPouvoir_retourneNULL() {
        Carte carte = new Carte("TestCard", 10);
        assertEquals(Pouvoirs.NULL, carte.getPouvoir());
    }
}