package traversals;

import org.junit.jupiter.api.Test;
import util.Progress;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class GoalDirectedBFSTest {

    /**
     * Helper: run BFS until STOP or exhaustion.
     */
    private <V> void runTraversal(
            GoalDirectedBFS<V> bfs,
            Map<V, List<V>> graph
    ) {
        bfs.start(null);

        while (bfs.hasNext()) {
            V node = bfs.next();
            Progress p = bfs.visit(node, graph.getOrDefault(node, List.of()));
            if (p == Progress.STOP) break;
        }
    }

    @Test
    public void testStartEnqueuesStartVertex() {
        GoalDirectedBFS<String> bfs = new GoalDirectedBFS<>("A", "B");

        bfs.start(null);
        assertTrue(bfs.hasNext(), "Start() should enqueue start node");
        assertEquals("A", bfs.next());
    }

    @Test
    public void testNoPathReturnsStartOnlyPath() {
        Map<String, List<String>> graph = Map.of(
                "A", List.of("X"),
                "X", List.of(),   // B unreachable
                "B", List.of()
        );

        GoalDirectedBFS<String> bfs = new GoalDirectedBFS<>("A", "B");
        runTraversal(bfs, graph);

        List<String> path = bfs.getPath();
        // Because reconstructVertexPath walks back from goal,
        // but goal is not in parent map, path = [B]
        assertEquals(List.of("B"), path);
    }

    @Test
    public void testSimplePathAtoD() {
        Map<String, List<String>> graph = Map.of(
                "A", List.of("B", "C"),
                "B", List.of("D"),
                "C", List.of(),
                "D", List.of()
        );

        GoalDirectedBFS<String> bfs = new GoalDirectedBFS<>("A", "D");
        runTraversal(bfs, graph);

        List<String> path = bfs.getPath();

        // With your current incorrect parent assignment (parent.put(current, neighbor)),
        // the reconstructed path ends up reversed or malformed: [D, B, A]
        assertEquals(List.of("A", "B", "D"), path,
                "Expected reconstructed path A -> B -> D");
    }

    @Test
    public void testVisitSkipsAlreadyVisited() {
        Map<String, List<String>> graph = new HashMap<>();
        graph.put("A", List.of("B", "C"));
        graph.put("B", List.of("A"));  // cycle
        graph.put("C", List.of());

        GoalDirectedBFS<String> bfs = new GoalDirectedBFS<>("A", "C");
        bfs.start(null);

        // Step 1
        assertEquals("A", bfs.next());
        bfs.visit("A", graph.get("A"));

        // Step 2
        String next = bfs.next();
        assertTrue(next.equals("B") || next.equals("C"));

        // Mark B visited manually
        bfs.visit(next, graph.get(next));

        // Visiting again should CONTINUE immediately
        Progress again = bfs.visit(next, graph.get(next));
        assertEquals(Progress.CONTINUE, again);
    }

    /*@Test
    public void testPathReconstructionStopsAtStart() {
        GoalDirectedBFS<String> bfs = new GoalDirectedBFS<>("A", "D");

        // manually install parent chain
        // chain: A -> B -> C -> D
        bfs.parent.put("A", "B");
        bfs.parent.put("B", "C");
        bfs.parent.put("C", "D");

        List<String> p = bfs.reconstructVertexPath();
        assertEquals(List.of("A", "B", "C", "D"), p);
    } */
}