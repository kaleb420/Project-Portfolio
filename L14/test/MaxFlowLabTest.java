import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.*;

public class MaxFlowLabTest {

    // --- helper: compute value of flow from source s ---
    private int flowValue(int[][] flow, int s) {
        int n = flow.length;
        int value = 0;
        for (int v = 0; v < n; v++) {
            value += flow[s][v];
        }
        return value;
    }

    // --- helper: assert capacity constraints 0 <= f(u,v) <= c(u,v) ---
    private void assertCapacityConstraints(int[][] flow, int[][] capacity) {
        int n = flow.length;
        for (int u = 0; u < n; u++) {
            for (int v = 0; v < n; v++) {
                assertTrue(flow[u][v] >= 0,
                        "Flow must be non-negative on edge (" + u + "," + v + ")");
                assertTrue(flow[u][v] <= capacity[u][v],
                        "Flow exceeds capacity on edge (" + u + "," + v + ")");
            }
        }
    }

    // --- helper: assert flow conservation for all v != s,t ---
    private void assertFlowConservation(int[][] flow, int s, int t) {
        int n = flow.length;
        for (int v = 0; v < n; v++) {
            if (v == s || v == t) continue;
            int inflow = 0;
            int outflow = 0;
            for (int u = 0; u < n; u++) {
                inflow  += flow[u][v];
                outflow += flow[v][u];
            }
            assertEquals(inflow, outflow,
                    "Flow conservation violated at vertex " + v);
        }
    }

    // --- helper: build residual graph from capacity and flow ---
    private int[][] buildResidual(int[][] capacity, int[][] flow) {
        int n = capacity.length;
        int[][] residual = new int[n][n];
        for (int u = 0; u < n; u++) {
            for (int v = 0; v < n; v++) {
                // Forward residual capacity
                residual[u][v] = capacity[u][v] - flow[u][v];
                // Reverse residual capacity is implicitly flow[u][v]
                // BUT we do not overwrite here; reverse edges will be seen as residual[v][u]
                // when iterating (u,v) and (v,u).
                // (This matches the usual invariant if flow is anti-symmetric.)
                if (flow[u][v] > 0) {
                    residual[v][u] += flow[u][v];
                }
            }
        }
        return residual;
    }

    // --- helper: assert no s-t path in residual graph (optimality) ---
    private void assertNoAugmentingPath(int[][] capacity, int[][] flow, int s, int t) {
        int[][] residual = buildResidual(capacity, flow);
        int[] parent = MaxFlowLab.findAugmentingPath(residual, s, t);
        assertNull(parent, "There should be no augmenting path in the final residual graph");
    }

    // --- BASIC TESTS ---

    @Test
    public void simpleGraph() {
        // s=0, t=3
        int[][] capacity = {
                { 0, 3, 2, 0 },
                { 0, 0, 5, 2 },
                { 0, 0, 0, 3 },
                { 0, 0, 0, 0 }
        };

        int[][] flow = MaxFlowLab.maxFlow(capacity, 0, 3);

        assertEquals(5, flowValue(flow, 0), "Max flow value should be 5");
        assertCapacityConstraints(flow, capacity);
        assertFlowConservation(flow, 0, 3);
        assertNoAugmentingPath(capacity, flow, 0, 3);
    }

    @Test
    public void cormenExample() {
        // Cormen-style example: max flow = 23
        //    s  v1  v2  v3  v4   t
        int[][] capacity = {
                { 0, 16, 13,  0,  0,  0 }, // s
                { 0,  0, 10, 12,  0,  0 }, // v1
                { 0,  4,  0,  0, 14,  0 }, // v2
                { 0,  0,  9,  0,  0, 20 }, // v3
                { 0,  0,  0,  7,  0,  4 }, // v4
                { 0,  0,  0,  0,  0,  0 }  // t
        };

        int[][] flow = MaxFlowLab.maxFlow(capacity, 0, 5);

        assertEquals(23, flowValue(flow, 0), "Max flow value should be 23");
        assertCapacityConstraints(flow, capacity);
        assertFlowConservation(flow, 0, 5);
        assertNoAugmentingPath(capacity, flow, 0, 5);
    }

    @Test
    public void noPathGraph() {
        // s=0, t=3, no edges out of s
        int[][] capacity = {
                { 0, 0, 0, 0 },
                { 0, 0, 1, 0 },
                { 0, 0, 0, 2 },
                { 0, 0, 0, 0 }
        };

        int[][] flow = MaxFlowLab.maxFlow(capacity, 0, 3);

        assertEquals(0, flowValue(flow, 0), "Max flow value should be 0");
        assertCapacityConstraints(flow, capacity);
        assertFlowConservation(flow, 0, 3);
        assertNoAugmentingPath(capacity, flow, 0, 3);
    }

    @Test
    public void requiresReverseEdges() {
        // Graph where we need to use reverse residual edges to reach max flow.
        //
        //  s(0) -> 1 (capacity 10)
        //  s(0) -> 2 (capacity 10)
        //  1 -> 2 (capacity 2)
        //  1 -> t(3) (capacity 4)
        //  2 -> t(3) (capacity 10)
        //
        // Max flow from s to t is 14.
        int[][] capacity = {
                //  0   1   2   3
                {  0, 10, 10,  0 }, // 0 = s
                {  0,  0,  2,  4 }, // 1
                {  0,  0,  0, 10 }, // 2
                {  0,  0,  0,  0 }  // 3 = t
        };

        int[][] flow = MaxFlowLab.maxFlow(capacity, 0, 3);

        assertEquals(14, flowValue(flow, 0), "Max flow value should be 14");
        assertCapacityConstraints(flow, capacity);
        assertFlowConservation(flow, 0, 3);
        assertNoAugmentingPath(capacity, flow, 0, 3);
    }
}
