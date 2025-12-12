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
        assertEquals(5, flow[0][1], "Max flow should equal edge capacity");
        assertEquals(0, flow[1][0], "No flow should go backward");
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

        // Total flow into sink should be 20
        int totalFlow = flow[0][1] + flow[0][2];
        assertEquals(20, totalFlow, "Max flow should be 20");

        // Flow conservation: incoming = outgoing for intermediate nodes
        assertEquals(flow[0][1], flow[1][3], "Flow through node 1 should match outgoing");
        assertEquals(flow[0][2], flow[2][3], "Flow through node 2 should match outgoing");
    }

    @Test
    void testNoPath() {
        int[][] capacity = {
                {0, 0},
                {0, 0}
        };
        int s = 0, t = 1;

        int[][] flow = MaxFlowLab.maxFlow(capacity, s, t);
        assertEquals(0, flow[0][1], "Max flow should be 0 when no path exists");
    }

    @Test
    void testComplexGraph() {
        // Example graph from CLRS
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

        // Max flow value should be 23
        int totalFlow = 0;
        for (int i = 0; i < capacity.length; i++) {
            totalFlow += flow[i][t];
        }
        assertEquals(23, totalFlow, "Max flow in CLRS example should be 23");
    }

}
