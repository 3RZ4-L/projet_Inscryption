package mecaniques.cartes;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;
import static org.junit.Assert.*;

public class CarteTest {

    private Carte carte;

    @Test
    public void testConstructeur() {
        assertEquals("TestCarte", carte.getNom());
        assertEquals(10, carte.getPv());
    }

    @Test
    public void testGetPv() {
        assertEquals(10, carte.getPv());
    }

    @Test
    public void testGetNom() {
        assertEquals("TestCarte", carte.getNom());
    }

    @Test
    public void testEstMort_Faux() {
        assertFalse(carte.estMort());
    }

    @Test
    public void testEstMort_Vrai() {
        carte.m_points_de_vie = 0;
        assertTrue(carte.estMort());
    }
}