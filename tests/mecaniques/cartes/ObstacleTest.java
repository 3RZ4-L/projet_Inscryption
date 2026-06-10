package mecaniques.cartes;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;
import static org.junit.Assert.*;

public class ObstacleTest {

    private Obstacle obstacle;

    @Before
    public void setUp() {
        obstacle = new Obstacle("Machin", 10);
    }

    @Test
    public void testEstCreature() {
        assertFalse(obstacle.estCreature());
    }

    @Test
    public void testObstacleEstMort() {
        Obstacle mur = new Obstacle("Machin-Bidule", 0);
        assertTrue(mur.estMort());
    }
}