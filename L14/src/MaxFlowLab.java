import java.util.*;

/**
 * Lab 14: Max Flow using Ford-Fulkerson.
 * .
 * We represent the graph using:
 *  - capacity[u][v]: original capacity of edge (u,v) (0 if no edge)
 *  - residual[u][v]: residual capacity of edge (u,v)
 *  - flow[u][v]: current flow on edge (u,v)
 * .
 * Your task:
 *   Implement the Ford-Fulkerson main loop in maxFlow(...)
 *   using the given findAugmentingPath(...) helper.
 */
public class MaxFlowLab {

    /**
     * Compute the maximum s-t flow in a directed graph using Ford-Fulkerson.
     * .
     * @param capacity capacity[u][v] is the capacity of edge (u,v)
     * @param s source vertex
     * @param t sink vertex
     * @return value of a maximum flow from s to t
     */
    public static int[][] maxFlow(int[][] capacity, int s, int t) {
        int n = capacity.length;

        // flow[u][v] will store the current flow on edge (u,v)
        int[][] flow = new int[n][n];

        // residual[u][v] will store the residual capacity of edge (u,v)
        int[][] residual = new int[n][n];

        // Initialize flow to 0 and residual to original capacities
        for (int u = 0; u < n; u++) {
            for (int v = 0; v < n; v++) {
                flow[u][v] = 0;
                residual[u][v] = capacity[u][v];
            }
        }

        // TODO: Implement the Ford-Fulkerson main loop.
        // Outline:
        //  1. Repeatedly call findAugmentingPath(residual, s, t)
        //  2. If it returns null, break (no more augmenting paths)
        //  3. Otherwise:
        //      a) Find the bottleneck Δ along the path using residual capacities
        //      b) Walk the path again and:
        //          - Decrease residual[u][v] by Δ
        //          - Increase residual[v][u] by Δ
        //          - Update flow[u][v] or flow[v][u] depending on whether
        //            (u,v) is a forward edge (capacity[u][v] > 0) or a
        //            reverse residual edge
        //  4. At the end, return flow
        while (true){
            int bottleneck=Integer.MAX_VALUE;
            int[] arr=findAugmentingPath(residual, s ,t);
            if (arr==null)
                break;
            int v=t;
            int u=arr[v];
            while (v!=s){
                bottleneck=Math.min(bottleneck, residual[u][v]);
                v=u;
                u=arr[v];
            }
            v=t;
            u=arr[v];
            while (v!=s) {
                residual[u][v]-=bottleneck;
                residual[v][u]+=bottleneck;
                if (capacity[u][v] > 0)
                    flow[u][v]+=bottleneck;
                else
                    flow[v][u]-=bottleneck;
                v=u;
                u=arr[v];
            }
        }
        return flow;
    }

    /**
     * Find an s-t augmenting path in the residual graph using BFS.
     *
     * @param residual residual[u][v] is the residual capacity of edge (u,v)
     * @param s source vertex
     * @param t sink vertex
     * @return parent[] array encoding the path (parent[v] is predecessor of v),
     *         or null if no path from s to t exists.
     */
    public static int[] findAugmentingPath(int[][] residual, int s, int t) {
        int n = residual.length;
        boolean[] visited = new boolean[n];
        int[] parent = new int[n];
        Arrays.fill(parent, -1);

        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(s);
        visited[s] = true;

        while (!queue.isEmpty()) {
            int u = queue.remove();
            for (int v = 0; v < n; v++) {
                // Only follow edges with positive residual capacity
                if (!visited[v] && residual[u][v] > 0) {
                    visited[v] = true;
                    parent[v] = u;
                    queue.add(v);
                    if (v == t) {
                        // Early exit: we've reached t
                        return parent;
                    }
                }
            }
        }

        // t was not reached
        if (!visited[t]) {
            return null;
        }
        return parent;
    }
}
