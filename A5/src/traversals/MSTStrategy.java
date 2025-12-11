package traversals;

import graphs.WeightedBiDirectedGraph;
import heap.BinaryMinHeap;
import heap.HeapNode;
import heap.MinHeap;
import util.Edge;
import util.Progress;
import util.Weight;

import java.util.*;

/**
 * This class implements a Minimum Spanning Tree (MST) strategy using Prim's algorithm.
 * This is almost identical to Dijkstra's algorithm. The only difference is that
 * the current node cost is not added to the total cost.
 */
public class MSTStrategy<V> implements TraversalStrategy<V> {
    private final WeightedBiDirectedGraph<V> graph;
    private final MinHeap<Weight> heap = new BinaryMinHeap<>();
    private final Map<V, HeapNode<Weight>> nodeMap = new HashMap<>();
    private final Map<HeapNode<Weight>, V> reverseNodeMap = new HashMap<>();
    private final Map<V, Edge<V>> parentMap = new HashMap<>();
    private final Set<V> visited = new HashSet<>();

    public MSTStrategy(WeightedBiDirectedGraph<V> graph) {
        this.graph = graph;
    }

    /**
     * Initializes the traversal with the given starting vertex. We add
     * all vertices to the heap with infinite distance, except for the starting vertex
     * which is set to 0.
     */
    public void start(V start) {
        HeapNode<Weight> startNode=new BinaryMinHeap.Heap2Node<>(Weight.ZERO);
        nodeMap.put(start, startNode);
        reverseNodeMap.put(startNode, start);
        heap.insert(nodeMap.get(start));
        for (V vertex : graph.getVertices()){
            if (!vertex.equals(start)) {
                HeapNode<Weight> otherNodes=new BinaryMinHeap.Heap2Node<>(Weight.infinity());
                nodeMap.put(vertex, otherNodes);
                reverseNodeMap.put(otherNodes, vertex);
                heap.insert(nodeMap.get(vertex));
            }
        }
    }

    /**
     * Checks if there are more nodes to visit in the heap.
     */
    public boolean hasNext() {
        return !heap.getNodes().isEmpty();
    }

    /**
     * Removes the minimum node from the heap and returns the corresponding vertex.
     * This method should only be called if hasNext() is true.
     */
    public V next() {
        if (!hasNext())
            return null;
        try {
            HeapNode<Weight> min=heap.removeMin();
            return reverseNodeMap.get(min);
        }
        catch (MinHeap.EmptyHeapExc e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Visits the current vertex and its neighbors. If the current vertex or
     * any of its neighbors have already been visited, it returns CONTINUE.
     * Otherwise, we compute the cost to each neighbor as the weight of the
     * edge from the current node to the neighbor. If this cost is less than the
     * current cost of the neighbor, we update the cost in the heap and set the
     * parent of the neighbor to the current node.
     */
    public Progress visit(V current, Collection<V> neighbors) {
        if (visited.contains(current))
            return Progress.CONTINUE;
        visited.add(current);
        Map<V, Weight> weightedNeighbors=graph.getWeightedNeighbors(current);
        for (V neighbor : neighbors){
            if (!visited.contains(neighbor)) {
                Weight neighborWeight=weightedNeighbors.get(neighbor);
                if (neighborWeight.compareTo(nodeMap.get(neighbor).getValue())<0) {
                    heap.reduceValue(nodeMap.get(neighbor), neighborWeight);
                    parentMap.put(neighbor, new Edge<>(current, neighbor, neighborWeight));
                }
            }
        }
        return Progress.CONTINUE;
    }

    /**
     * Returns all the edges in the MST. This is done by iterating over the parentMap
     * and creating edges from each child to its parent.
     */
    public Collection<Edge<V>> getMSTEdges() {
        Collection<Edge<V>> edges=new ArrayList<>();
        for (V vertex : parentMap.keySet()) {
            edges.add(parentMap.get(vertex).flip());
        }
        return edges;
    }
}
