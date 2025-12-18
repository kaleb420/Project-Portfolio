import java.util.Arrays;
import java.util.Scanner;

public class CoinChange {

    public static int minChange(int amount, int[] denominations) {
        // TODO: Implement the Bottom-Up Dynamic Programming approach
        // Hint: Use an array dp[] where dp[i] represents the minimum number of coins needed for amount i
        int[] dp= new int[amount+1];
        Arrays.fill(dp, 10000000);
        dp[0]=0;
        for (int i=0; i<denominations.length; i++){
            for (int j = denominations[i]; j <= amount; j++) {
                dp[j]=Math.min(dp[j], dp[j-denominations[i]]+1);
            }
        }
        if (dp[amount]==10000000)
            return -1;
        return dp[amount];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // TODO: Allow students to input custom denominations
        System.out.println("Enter coin denominations separated by spaces:");
        String[] input = scanner.nextLine().split(" ");
        int[] denominations = Arrays.stream(input).mapToInt(Integer::parseInt).toArray();

        for (int i = 0; i <= 10; i++) {
            System.out.println("Minimum coins for " + i + " cents: " + minChange(i, denominations));
        }

        scanner.close();
    }
}

// While the bottom up approach uses less memory and avoids redundant calculations, however it primarily
// becomes effective for small amounts, while the top down approach is better for large amounts as well
// as preventing redundant calls, something the bottom up appraoch
