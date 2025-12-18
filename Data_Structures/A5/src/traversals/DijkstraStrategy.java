package traversals;

import graphs.WeightedDirectedGraph;
import heap.BinaryMinHeap;
import heap.HeapNode;
import heap.MinHeap;
import util.Edge;
import util.EdgePath;
import util.Progress;
import util.Weight;

import java.util.*;

/**
 * DijkstraStrategy implements Dijkstra's algorithm for finding the shortest
 * path in a weighted directed graph.
 */
public class DijkstraStrategy<V> implements TraversalStrategy<V> {
    // Nodes in the graph are of type V
    private final WeightedDirectedGraph<V> graph;
    // HeapNode<Weight> is used to store the weight (distance) of each node
    // in the heap. The weight is a wrapper around an int, which can be
    // infinite. The heap is a min-heap, so the node with the smallest
    // weight is at the top. The nodeMap and reverseNodeMap are used to
    // navigate between the nodes in the graph and the nodes in the heap.
    private final MinHeap<Weight> heap = new BinaryMinHeap<>();
    private final Map<V, HeapNode<Weight>> nodeMap = new HashMap<>();
    private final Map<HeapNode<Weight>, V> reverseNodeMap = new HashMap<>();
    // The parentMap is used to keep track of the shortest path
    // from the start node to the goal node. The goal is the destination
    // node we are trying to reach.
    private final V goal;
    private final Map<V, Edge<V>> parentMap = new HashMap<>();

    public DijkstraStrategy(WeightedDirectedGraph<V> graph, V goal) {
        this.graph = graph;
        this.goal = goal;
    }

    /**
     * Start the traversal from the given start node. The start node is
     * the source node from which we will begin the traversal. The
     * start node is added to the heap with a distance of 0. All other
     * nodes are added to the heap with an infinite distance.
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
                heap.insert(otherNodes);
            }
        }
    }

    /**
     * Check if the traversal has reached the goal node.
     */
    public boolean hasNext() {
        return !heap.getNodes().isEmpty();
    }

    /**
     * Get the next node in the traversal. The next() method
     * removes the node with the smallest distance from the heap
     * and returns the corresponding vertex in the graph.
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
     * This is the heart of the algorithm. If we reached the goal, we stop. Otherwise,
     * we get the neighbors of the current node and update their distances in the heap.
     * We also update the parent map to keep track of the shortest path.
     */
    public Progress visit(V current, Collection<V> neighbors) {
        if (neighbors==null && parentMap.isEmpty()){
            parentMap.put(current, null);
            return Progress.STOP;
        }
        Map<V, Weight> weightedNeighbors = graph.getWeightedNeighbors(current);
        for (V neighbor : neighbors){
            Weight neighborWeight = weightedNeighbors.get(neighbor);
            Weight uCost = nodeMap.get(current).getValue();
            Weight vCost = nodeMap.get(neighbor).getValue();
            if (neighborWeight==null || uCost==null || vCost==null)
                continue;
            if (uCost.plus(neighborWeight).compareTo(vCost) < 0) {
                heap.reduceValue(nodeMap.get(neighbor), uCost.plus(neighborWeight));
                parentMap.put(neighbor, new Edge<>(current, neighbor, weightedNeighbors.get(neighbor)));
            }
        }
        if (current.equals(goal))
            return Progress.STOP;
        return Progress.CONTINUE;
    }

    /**
     * Get the path from the start node to the goal node. The path is
     * constructed by following the parent map from the goal node to the
     * start node.
     */
    public Optional<EdgePath<V>> getPath () {
        if (!parentMap.containsKey(goal))
            return Optional.empty();
        EdgePath<V> path=new EdgePath<>(new ArrayList<>());
        V current=goal;
        while (parentMap.containsKey(current)){
            path=path.add(parentMap.get(current));
            current=parentMap.get(current).from();
        }
        Collections.reverse(path.edges());
        return Optional.of(path);
    }
}