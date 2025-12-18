import graphs.WeightedBiDirectedGraph;
import graphs.WeightedDirectedGraph;
import traversals.MaximumFlow;
import util.Edge;
import util.EdgePath;
import org.junit.jupiter.api.Test;
import util.Weight;

import java.util.Collection;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class WeightedDirectedGraphTest_Student {
    @Test
    public void testSingleEdge() {
        WeightedDirectedGraph<String> g = new WeightedDirectedGraph<>();
        g.addEdge("S", "T", Weight.of(10));

        MaximumFlow<String> mf = new MaximumFlow<>(g, "S", "T");
        assertEquals(10, mf.runMaxFlow());
    }

    @Test
    public void testTwoParallelPaths() {
        WeightedDirectedGraph<String> g = new WeightedDirectedGraph<>();

        g.addEdge("S", "A", Weight.of(5));
        g.addEdge("A", "T", Weight.of(5));

        g.addEdge("S", "B", Weight.of(8));
        g.addEdge("B", "T", Weight.of(8));

        MaximumFlow<String> mf = new MaximumFlow<>(g, "S", "T");
        assertEquals(13, mf.runMaxFlow());
    }

    @Test
    public void testBottleneck() {
        WeightedDirectedGraph<String> g = new WeightedDirectedGraph<>();

        g.addEdge("S", "A", Weight.of(100));
        g.addEdge("A", "B", Weight.of(3));
        g.addEdge("B", "T", Weight.of(50));

        MaximumFlow<String> mf = new MaximumFlow<>(g, "S", "T");
        assertEquals(3, mf.runMaxFlow());
    }

    @Test
    public void testNoPath() {
        WeightedDirectedGraph<String> g = new WeightedDirectedGraph<>();

        g.addVertex("S");
        g.addVertex("T");

        MaximumFlow<String> mf = new MaximumFlow<>(g, "S", "T");
        assertEquals(0, mf.runMaxFlow());
    }

    @Test
    public void testNeedsMultipleAugmentations() {
        WeightedDirectedGraph<String> g = new WeightedDirectedGraph<>();

        g.addEdge("S", "A", Weight.of(5));
        g.addEdge("A", "T", Weight.of(5));

        g.addEdge("S", "T", Weight.of(10));

        MaximumFlow<String> mf = new MaximumFlow<>(g, "S", "T");
        assertEquals(15, mf.runMaxFlow());
    }

    @Test
    public void testGraphWithCycle() {
        WeightedDirectedGraph<String> g = new WeightedDirectedGraph<>();

        g.addEdge("S", "A", Weight.of(4));
        g.addEdge("A", "T", Weight.of(4));
        g.addEdge("A", "S", Weight.of(9999));

        MaximumFlow<String> mf = new MaximumFlow<>(g, "S", "T");
        assertEquals(4, mf.runMaxFlow());
    }
}
