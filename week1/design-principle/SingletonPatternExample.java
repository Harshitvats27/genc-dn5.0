// Singleton Pattern Exercise
// Exercise 1: Implementing the Singleton Pattern

// Logger class implementing the Singleton pattern
class Logger {
    private static Logger instance;

    // Private constructor to prevent instantiation from outside
    private Logger() {
        System.out.println("Logger instance created.");
    }

    // Public static method to get the single instance of Logger
    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    // Method to log messages
    public void log(String message) {
        System.out.println("[LOG]: " + message);
    }
}

// Test class to verify Singleton implementation
public class SingletonPatternExample {
    public static void main(String[] args) {
        System.out.println("=== Singleton Pattern Demo ===\n");

        // Get the first instance of Logger
        Logger logger1 = Logger.getInstance();
        logger1.log("Application started.");

        // Get the second instance of Logger
        Logger logger2 = Logger.getInstance();
        logger2.log("User logged in.");

        // Verify that both references point to the same instance
        System.out.println("\n--- Verification ---");
        System.out.println("logger1: " + logger1.hashCode());
        System.out.println("logger2: " + logger2.hashCode());
        System.out.println("Are both instances the same? " + (logger1 == logger2));
        System.out.println("Are both instances the same object? " + (logger1.equals(logger2)));

        // Additional test: Multiple get instance calls
        Logger logger3 = Logger.getInstance();
        Logger logger4 = Logger.getInstance();

        System.out.println("\n--- Additional Verification ---");
        System.out.println("logger3: " + logger3.hashCode());
        System.out.println("logger4: " + logger4.hashCode());
        System.out.println("All four loggers are same instance? " +
            (logger1 == logger2 && logger2 == logger3 && logger3 == logger4));
    }
}
