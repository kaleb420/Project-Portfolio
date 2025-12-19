import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class Problem1Test {

    @org.junit.jupiter.api.Test
    void assignGrades() {
        assertEquals(List.of("A"), Problem1.assignGrades(List.of(95.0), List.of("A", "B", "C", "D", "F"), new double[]{90, 80, 70, 60}));
        assertEquals(List.of("B"), Problem1.assignGrades(List.of(80.0), List.of("A", "B", "C", "D", "F"), new double[]{90, 80, 70, 60}));
        assertEquals(List.of("C", "B", "B", "A", "F", "F", "D"), Problem1.assignGrades(List.of(79.0, 85.5, 89.95, 90.14, 0.0, 50.0, 60.01), List.of("A", "B", "C", "D", "F"), new double[]{90, 80, 70, 60}));
        assertEquals(List.of("C+", "B", "B+", "A-", "F", "F", "D-"), Problem1.assignGrades(List.of(79.0, 85.5, 89.95, 90.14, 0.0, 50.0, 60.01), List.of("A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "D-", "F"), new double[]{93, 90, 87, 83, 80, 77, 73, 70, 67, 63, 60}));
        assertEquals(List.of(), Problem1.assignGrades(List.of(), List.of("A", "B", "C", "D", "F"), new double[]{90, 80, 70, 60}));
        assertEquals(List.of("F"), Problem1.assignGrades(List.of(0.0), List.of("A", "B", "C", "D", "F"), new double[]{90, 80, 70, 60}));
        assertEquals(List.of("F"), Problem1.assignGrades(List.of(-50.0), List.of("A", "B", "C", "D", "F"), new double[]{90, 80, 70, 60}));
    }
}