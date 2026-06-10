package mecaniques.cartes;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CarteTest {

    private Carte carte;

    @Before
    public void setUp() {
        carte = new Animal("Test", 10, 5,0,0,false); // ou new Obstacle("Test", 10)
    }

    @Test
    public void testGetPv() {
        assertEquals(10, carte.getPv());
    }

    @Test
    public void testEstMort_Faux() {
        assertFalse(carte.estMort());
    }

    @Test
    public void testEstMort_Vrai() {
        Obstacle choseMorte = new Obstacle("Mort", 0);
        assertTrue(choseMorte.estMort());
    }
}