// Exercise 2: E-commerce Platform Search Function

/*
 * Asymptotic Notation:
 * 
 * Big O notation describes the upper bound of an algorithm's growth rate.
 * It helps analyze how runtime scales with input size (n).
 * 
 * For Search Operations:
 * - Best Case: O(1) - Element found at first position
 * - Average Case: O(n) for Linear, O(log n) for Binary
 * - Worst Case: O(n) for Linear, O(log n) for Binary
 */

// Product class with attributes for searching
class Product {
    int productId;
    String productName;
    String category;

    Product(int productId, String productName, String category) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{ID=" + productId + ", Name='" + productName + "', Category='" + category + "'}";
    }
}

public class EcommerceSearch {

    // Linear Search: O(n) - Works on unsorted arrays
    // Best: O(1), Average: O(n), Worst: O(n)
    static Product linearSearch(Product[] products, String productName) {
        for (int i = 0; i < products.length; i++) {
            if (products[i].productName.equalsIgnoreCase(productName)) {
                return products[i];
            }
        }
        return null;
    }

    // Binary Search: O(log n) - Requires sorted array
    // Best: O(1), Average: O(log n), Worst: O(log n)
    static Product binarySearch(Product[] sortedProducts, String productName) {
        int left = 0;
        int right = sortedProducts.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int cmp = sortedProducts[mid].productName.compareToIgnoreCase(productName);

            if (cmp == 0) {
                return sortedProducts[mid];
            } else if (cmp < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }

    // Helper: Sort products by name for binary search
    static Product[] sortByProductName(Product[] products) {
        Product[] sorted = products.clone();
        for (int i = 0; i < sorted.length - 1; i++) {
            for (int j = 0; j < sorted.length - i - 1; j++) {
                if (sorted[j].productName.compareToIgnoreCase(sorted[j + 1].productName) > 0) {
                    Product temp = sorted[j];
                    sorted[j] = sorted[j + 1];
                    sorted[j + 1] = temp;
                }
            }
        }
        return sorted;
    }

    public static void main(String[] args) {
        System.out.println("=== E-commerce Platform Search Function ===\n");

        // Setup products
        Product[] products = {
            new Product(101, "Laptop", "Electronics"),
            new Product(102, "Smartphone", "Electronics"),
            new Product(103, "Headphones", "Accessories"),
            new Product(104, "Keyboard", "Accessories"),
            new Product(105, "Monitor", "Electronics")
        };

        // Display all products
        System.out.println("--- All Products ---");
        for (Product p : products) {
            System.out.println(p);
        }

        // Linear Search
        System.out.println("\n--- Linear Search ---");
        String searchName = "Headphones";
        long startTime = System.nanoTime();
        Product result1 = linearSearch(products, searchName);
        long endTime = System.nanoTime();
        System.out.println("Searching for: " + searchName);
        System.out.println("Result: " + result1);
        System.out.println("Time taken: " + (endTime - startTime) + " ns");

        // Binary Search (requires sorted array)
        System.out.println("\n--- Binary Search ---");
        Product[] sortedProducts = sortByProductName(products);
        System.out.println("Sorted products:");
        for (Product p : sortedProducts) {
            System.out.println("  " + p);
        }

        startTime = System.nanoTime();
        Product result2 = binarySearch(sortedProducts, searchName);
        endTime = System.nanoTime();
        System.out.println("\nSearching for: " + searchName);
        System.out.println("Result: " + result2);
        System.out.println("Time taken: " + (endTime - startTime) + " ns");

        // Comparison
        System.out.println("\n--- Time Complexity Comparison ---");
        System.out.println("Linear Search: O(n) - No sorting needed, simple implementation");
        System.out.println("Binary Search: O(log n) - Requires sorted data, faster for large datasets");
        System.out.println("\nRecommendation: Use Binary Search for e-commerce platforms");
        System.out.println("because product catalogs are typically large and can be pre-sorted.");
    }
}
