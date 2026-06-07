//package mecaniques.cartes;
//
//import org.junit.Test;
//import static org.junit.Assert.*;
//import static mecaniques.cartes.Pouvoirs.*;
//
//public class AnimalTest {
//
//    @Test
//    public void test_constructor_complet() {
//        Animal animal = new Animal("Lion", 5, 3, 2, 1, true, NOMBREUSES_VIES);
//        assertEquals("Lion", animal.getNom());
//        assertEquals(5, animal.getPv());
//        assertEquals(3, animal.getAttack());
//        assertEquals(2, animal.getGouttesSang());
//        assertEquals(1, animal.getOs());
//        assertTrue(animal.estVolant());
//        assertEquals(NOMBREUSES_VIES, animal.getPouvoir());
//    }
//
//    @Test
//    public void test_constructor_sansPouvoir() {
//        Animal animal = new Animal("Lion", 5, 3, 2, 1, false);
//        assertEquals("Lion", animal.getNom());
//        assertEquals(5, animal.getPv());
//        assertEquals(3, animal.getAttack());
//        assertEquals(2, animal.getGouttesSang());
//        assertEquals(1, animal.getOs());
//        assertFalse(animal.estVolant());
//        assertEquals(NULL, animal.getPouvoir());
//    }
//
//    @Test
//    public void test_getAttack() {
//        Animal animal = new Animal("Lion", 5, 7, 2, 1, false);
//        assertEquals(7, animal.getAttack());
//    }
//
//    @Test
//    public void test_getGouttesSang() {
//        Animal animal = new Animal("Lion", 5, 3, 4, 1, false);
//        assertEquals(4, animal.getGouttesSang());
//    }
//
//    @Test
//    public void test_getOs() {
//        Animal animal = new Animal("Lion", 5, 3, 2, 5, false);
//        assertEquals(5, animal.getOs());
//    }
//
//    @Test
//    public void test_attaqueDirect() {
//        Animal animal = new Animal("Lion", 5, 8, 2, 1, false);
//        assertEquals(8, animal.attaqueDirect());
//    }
//
//    @Test
//    public void test_doDamage_nonVolant_carteTuee() {
//        Animal attaquant = new Animal("Lion", 5, 6, 2, 1, false);
//        Animal defenseur = new Animal("Proie", 4, 1, 0, 0, false);
//
//        int score = attaquant.doDamage(defenseur);
//
//        assertEquals(2, score);
//        assertTrue(defenseur.estMort());
//    }
//
//    @Test
//    public void test_doDamage_nonVolant_carteNonTuee() {
//        Animal attaquant = new Animal("Lion", 5, 3, 2, 1, false);
//        Animal defenseur = new Animal("Proie", 10, 1, 0, 0, false);
//
//        int score = attaquant.doDamage(defenseur);
//
//        assertEquals(0, score);
//        assertEquals(7, defenseur.getPv());
//        assertFalse(defenseur.estMort());
//    }
//
//    @Test
//    public void test_doDamage_volant_utiliseAttaqueDirecte() {
//        Animal attaquant = new Animal("Aigle", 5, 4, 2, 1, true);
//        Animal defenseur = new Animal("Proie", 10, 1, 0, 0, false);
//
//        int score = attaquant.doDamage(defenseur);
//
//        assertEquals(4, score);
//        assertEquals(10, defenseur.getPv());
//    }
//
//    @Test
//    public void test_doDamage_attackZero() {
//        Animal attaquant = new Animal("Mouton", 3, 0, 1, 0, false);
//        Animal defenseur = new Animal("Proie", 5, 1, 0, 0, false);
//
//        int score = attaquant.doDamage(defenseur);
//
//        assertEquals(0, score);
//        assertEquals(5, defenseur.getPv());
//    }
//
//    @Test
//    public void test_estMort_apresDegats() {
//        Animal animal = new Animal("Test", 5, 3, 1, 0, false);
//        Animal autre = new Animal("Autre", 10, 2, 0, 0, false);
//
//        animal.doDamage(autre);
//        assertFalse(animal.estMort());
//    }
//
//    @Test
//    public void test_getNamePouvoir() {
//        Animal animal = new Animal("Test", 5, 3, 2, 1, false, CROISSANCE);
//        assertEquals("Croissance", animal.getNamePouvoir());
//    }
//
//    @Test
//    public void test_getNamePouvoir_NULL() {
//        Animal animal = new Animal("Test", 5, 3, 2, 1, false);
//        assertEquals("", animal.getNamePouvoir());
//    }
//}