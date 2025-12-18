import java.util.*;

public class DPJustifier extends AbstractJustifier {

    @Override
    public List<String> justify(List<String> words, int L, double b) {
        int size = words.size();
        int k=0;
        int W=0;
        int S=0;
        boolean last;
        double cost=0;
        double total=0;
        int[] prefix = new int[size + 1];
        for (int i = 0; i < size; i++) {
            prefix[i + 1] = prefix[i] + words.get(i).length();
        }
        double[] dp = new double[size + 1];
        int[] next = new int[size];
        dp[size] = 0.0;
        for (int i = size - 1; i >= 0; i--) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = i; j < size; j++) {
                k=j-i;
                W=prefix[j+1]-prefix[i];
                S=L-W;
                last=j==size-1;
                cost=lineCost(k, S, b, last);
                if (cost < dp[i]) {
                    total=cost+dp[j+1];
                    if (total < dp[i]) {
                        dp[i]=total;
                        next[i]=j+1;
                    }
                }
            }
        }
        List<String> lines = new ArrayList<>();
        int i = 0;
        int j=0;
        while (i < size) {
            j = next[i] - 1;
            last = (next[i] == size);
            lines.add(render(words, i + 1, j + 1, L, last));
            i = next[i];
        }
        return lines;
    }
    /**
     * L1 line cost with strictly positive gaps.
     * - k == 0 (single word):
     *     non-last: feasible iff S == 0 (cost 0); else INF
     *     last:     feasible iff S >= 0 (cost 0); else INF
     * - k > 0:
     *     feasible iff S >= k (each gap >= 1 space)
     *     cost = k*|S/k - b| for non-last
     *     cost = k*max(0, b - S/k) for last
     */
    static double lineCost(int k, int S, double b, boolean last) {
        if (k == 0) {
            if (!last) return (S == 0) ? 0.0 : Double.POSITIVE_INFINITY;
            return (S >= 0) ? 0.0 : Double.POSITIVE_INFINITY;
        }
        if (S < k) return Double.POSITIVE_INFINITY;
        double avg = (double) S / k;
        return last ? k * Math.max(0.0, b - avg) : k * Math.abs(avg - b);
    }
}
