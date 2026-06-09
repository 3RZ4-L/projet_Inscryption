package mecaniques.joueurs;

import mecaniques.cartes.Animal;
import mecaniques.cartes.Pouvoirs;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static mecaniques.cartes.Pouvoirs.PUANT;
import static org.junit.Assert.*;

public class HumainTest {

    private Humain humain;

    @Before
    public void setUp() {
        humain = new Humain();
    }

    @Test
    public void testPeutSacrifier_PositionNegative() {
        assertFalse(humain.peutSacrifier(-1));
    }

    @Test
    public void testPeutSacrifier_PositionTropGrande() {
        assertFalse(humain.peutSacrifier(5));
    }

    @Test
    public void testPeutSacrifier_PositionValideSansCarte() {
        assertFalse(humain.peutSacrifier(0));
    }

    @Test
    public void testAnimalAvecUnPouvoir() {
        Animal animal = new Animal("Punaise", 2, 1, 0, 2, false, PUANT);

        assertTrue(animal.aPouvoir());
        assertTrue(animal.aPouvoir(Pouvoirs.PUANT));
        assertFalse(animal.aPouvoir(Pouvoirs.COUREUR));

        assertEquals("Puant", animal.getNamePouvoir());

        assertTrue(animal.getPouvoir().isPresent());
        assertEquals(1, animal.getPouvoir().get().size());
    }
}