package traversals;

import util.Progress;

import java.util.*;

/**
 * GoalDirectedBFS finds a path from start to goal using BFS. The idea
 * is to use a regular BFS strategy but stop when we reach the goal. In addition,
 * in order to reconstruct the path, we need to keep track of the parent of each node.
 * The parent of a node is the node from which we reached it. This way, when we
 * reach the goal, we can reconstruct the path by following the parent pointers.
 */
public class GoalDirectedBFS<V> implements TraversalStrategy<V> {
    private final V start, goal;
    private final Queue<V> toVisit = new LinkedList<>();
    private final Set<V> visited = new HashSet<>();
    private final Map<V, V> parent = new HashMap<>();
    private List<V> path = List.of();

    public GoalDirectedBFS(V start, V goal) {
        this.start=start;
        this.goal=goal;
    }

    public void start(V s) {
        toVisit.add(start);

    }

    public boolean hasNext() {
        return !toVisit.isEmpty();
    }

    public V next() {
        if (hasNext())
            return toVisit.poll();
        return null;
    }

    public List<V> getPath() {
        return path;
    }

    /**
     * Visits the current node and its neighbors. If the current node has already been
     * visited, it continues to the next node. Otherwise, if the current node is the goal,
     * it reconstructs the path and stops the traversal. If the current node is not the goal,
     * we add its neighbors to the queue but only if they have not been visited, and
     * they are not already in the parent map. This way, we avoid adding the same node
     * multiple times to the queue, and more importantly, we ensure that the parent
     * map only contains the first time we reach a node.
     */
    public Progress visit(V current, Collection<V> neighbors) {
        if (visited.contains(current))
            return Progress.CONTINUE;
        if (current.equals(goal)) {
            path=reconstructVertexPath();
            return Progress.STOP;
        }
        visited.add(current);
        for (V neighbor : neighbors) {
            if (!parent.containsKey(neighbor) && !parent.containsValue(neighbor)) {
                parent.put(neighbor, current);
                toVisit.add(neighbor);
            }
        }
        return Progress.CONTINUE;
    }

    public List<V> reconstructVertexPath() {
        LinkedList<V> path = new LinkedList<>();
        for (V at = goal; at != null; at = parent.get(at)) {
            path.addFirst(at);
            if (at.equals(start)) break;
        }
        return path;
    }
}