import graphs.DirectedGraph;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class DirectedGraphTest_Student {
    @Test
    public void addVerticesAndEdges() {
        DirectedGraph<String> g = new DirectedGraph<>();
        g.addVertex("A");
        g.addEdge("A", "B");

        assertTrue(g.getVertices().contains("A"));
        assertTrue(g.getVertices().contains("B"));
        assertTrue(g.hasEdge("A", "B"));
        assertFalse(g.hasEdge("B", "A"));
    }

    @Test
    public void getNeighbors() {
        DirectedGraph<String> g = new DirectedGraph<>();
        g.addEdge("A", "B");
        g.addEdge("A", "C");

        Set<String> neighbors = g.getNeighbors("A");
        assertEquals(Set.of("B", "C"), neighbors);
    }

    @Test
    public void reverseGraph() {
        DirectedGraph<String> g = new DirectedGraph<>();
        g.addEdge("A", "B");
        g.addEdge("B", "C");

        DirectedGraph<String> r = g.reverse();

        assertTrue(r.hasEdge("B", "A"));
        assertTrue(r.hasEdge("C", "B"));
        assertFalse(r.hasEdge("A", "B"));
    }

    @Test
    public void testReverseKeepsAllVertices() {
        DirectedGraph<String> g = new DirectedGraph<>();
        g.addVertex("A");
        g.addVertex("B");
        g.addVertex("C");

        DirectedGraph<String> r = g.reverse();

        assertEquals(Set.of("A", "B", "C"), r.getVertices());
    }
}
