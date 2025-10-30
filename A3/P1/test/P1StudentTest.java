import org.junit.jupiter.api.Test;

public class P1StudentTest {

    @Test
    void empty() {
        AutonomousVehicle av = new AutonomousVehicle(5, 5, 5,
                new int[0][0], new int[0][0], new int[0][0], new int[0][0]);
        System.out.println(av.findBestPath(0, 0, 5));
    }

    @Test
    void outOfBounds() {
        AutonomousVehicle av = new AutonomousVehicle(5, 5, 5,
                new int[0][0], new int[0][0], new int[0][0], new int[0][0]);
        System.out.println(av.findBestPath(-1, 0, 5));
    }

    @Test
    void startAtGoal() {
        AutonomousVehicle av = new AutonomousVehicle(5, 0, 0,
                new int[0][0], new int[0][0], new int[0][0], new int[0][0]);
        System.out.println(av.findBestPath(0, 0, 5));
    }
}