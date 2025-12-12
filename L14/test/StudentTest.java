import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.*;

public class StudentTest {

    @Test
    void testSingleEdge() {
        int[][] capacity = {
                {0, 5},
                {0, 0}
        };
        int s = 0, t = 1;

        int[][] flow = MaxFlowLab.maxFlow(capacity, s, t);
        assertEquals(5, flow[0][1]);
        assertEquals(0, flow[1][0]);
    }

    @Test
    void testTwoPaths() {
        int[][] capacity = {
                {0, 10, 10, 0},
                {0, 0, 0, 10},
                {0, 0, 0, 10},
                {0, 0, 0, 0}
        };
        int s = 0, t = 3;

        int[][] flow = MaxFlowLab.maxFlow(capacity, s, t);

        int totalFlow = flow[0][1] + flow[0][2];
        assertEquals(20, totalFlow);

        assertEquals(flow[0][1], flow[1][3]);
        assertEquals(flow[0][2], flow[2][3]);
    }

    @Test
    void testNoPath() {
        int[][] capacity = {
                {0, 0},
                {0, 0}
        };
        int s = 0, t = 1;

        int[][] flow = MaxFlowLab.maxFlow(capacity, s, t);
        assertEquals(0, flow[0][1]);
    }

    @Test
    void testComplexGraph() {
        int[][] capacity = {
                {0, 16, 13, 0, 0, 0},
                {0, 0, 10, 12, 0, 0},
                {0, 4, 0, 0, 14, 0},
                {0, 0, 9, 0, 0, 20},
                {0, 0, 0, 7, 0, 4},
                {0, 0, 0, 0, 0, 0}
        };
        int s = 0, t = 5;

        int[][] flow = MaxFlowLab.maxFlow(capacity, s, t);

        int totalFlow = 0;
        for (int i = 0; i < capacity.length; i++) {
            totalFlow += flow[i][t];
        }
        assertEquals(23, totalFlow);
    }

}
