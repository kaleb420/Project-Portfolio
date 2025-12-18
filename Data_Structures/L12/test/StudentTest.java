import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StudentTest {
    private static ArrayList<Integer>[] makeGraph(int n) {
        ArrayList<Integer>[] g = (ArrayList<Integer>[]) new ArrayList[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }
        return g;
    }

    private static void addEdge(ArrayList<Integer>[] graph, int u, int v) {
        graph[u].add(v);
        graph[v].add(u);
    }

    @Test
    public void noEulerCircuit() {
        ArrayList<Integer>[] graph = makeGraph(4);
        addEdge(graph, 0, 3);

        assertFalse(EulerCircuit.hasEulerCircuit(graph));
    }

    @Test
    public void usesAll() {
        ArrayList<Integer>[] graph = makeGraph(5);
        addEdge(graph, 0, 1);
        addEdge(graph, 1, 2);
        addEdge(graph, 2, 3);
        addEdge(graph, 3, 4);
        addEdge(graph, 4, 0);

        assertTrue(EulerCircuit.hasEulerCircuit(graph));
    }

    @Test
    public void usesSome() {
        ArrayList<Integer>[] graph = makeGraph(5);
        addEdge(graph, 0, 3);
        addEdge(graph, 1, 2);
        addEdge(graph, 2, 3);
        addEdge(graph, 3, 4);
        addEdge(graph, 4, 0);

        assertFalse(EulerCircuit.hasEulerCircuit(graph));
    }

}
