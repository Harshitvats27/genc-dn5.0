// Exercise 7: Financial Forecasting

/*
 * Recursive Algorithms:
 * 
 * Recursion is a technique where a method calls itself to solve smaller
 * instances of the same problem. It breaks complex problems into simpler
 * sub-problems until reaching a base case.
 * 
 * Key components:
 * 1. Base Case: Stops recursion (prevents infinite loop)
 * 2. Recursive Case: Method calls itself with modified parameters
 */

public class FinancialForecasting {

    // Recursive method to calculate future value
    // Formula: FV = PV * (1 + rate)^years
    // Time Complexity: O(n) where n = years
    static double futureValueRecursive(double presentValue, double growthRate, int years) {
        // Base case: no more years to compound
        if (years == 0) {
            return presentValue;
        }
        return futureValueRecursive(presentValue * (1 + growthRate), growthRate, years - 1);
    }
    static double futureValueIterative(double presentValue, double growthRate, int years) {
        double futureValue = presentValue;
        for (int i = 0; i < years; i++) {
            futureValue *= (1 + growthRate);
        }
        return futureValue;
    }
    static double[] memo = new double[100];
    static double futureValueMemoized(double presentValue, double growthRate, int years) {
        if (years == 0) {
            return presentValue;
        }
        if (memo[years] != 0) {
            return memo[years];
        }
        memo[years] = futureValueMemoized(presentValue, growthRate, years - 1) * (1 + growthRate);
        return memo[years];
    }
    public static void main(String[] args) {
        System.out.println("=== Financial Forecasting Tool ===\n");
        double presentValue = 10000.0;
        double growthRate = 0.08; // 8% annual growth
        int years = 10;
        System.out.println("--- Parameters ---");
        System.out.println("Present Value: $" + presentValue);
        System.out.println("Growth Rate: " + (growthRate * 100) + "%");
        System.out.println("Years: " + years);
        System.out.println("\n--- Recursive Approach ---");
        long startTime = System.nanoTime();
        double fvRecursive = futureValueRecursive(presentValue, growthRate, years);
        long endTime = System.nanoTime();
        System.out.println("Future Value: $" + String.format("%.2f", fvRecursive));
        System.out.println("Time taken: " + (endTime - startTime) + " ns");

        System.out.println("\n--- Iterative Approach (Optimized) ---");
        startTime = System.nanoTime();
        double fvIterative = futureValueIterative(presentValue, growthRate, years);
        endTime = System.nanoTime();
        System.out.println("Future Value: $" + String.format("%.2f", fvIterative));
        System.out.println("Time taken: " + (endTime - startTime) + " ns");
        System.out.println("\n--- Memoized Approach (Optimized) ---");
        java.util.Arrays.fill(memo, 0);
        startTime = System.nanoTime();
        double fvMemoized = futureValueMemoized(presentValue, growthRate, years);
        endTime = System.nanoTime();
        System.out.println("Future Value: $" + String.format("%.2f", fvMemoized));
        System.out.println("Time taken: " + (endTime - startTime) + " ns");
        System.out.println("\n--- Complexity Analysis ---");
        System.out.println("Recursive:     O(n) time, O(n) stack space");
        System.out.println("Iterative:     O(n) time, O(1) space");
        System.out.println("Memoized:      O(n) time, O(n) space (avoids recomputation)");
        System.out.println("\nOptimization: Use iterative approach for best performance,");
        System.out.println("or memoization when recursive logic is complex.");
    }
}
