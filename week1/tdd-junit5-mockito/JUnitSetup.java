// Exercise 1: Setting Up JUnit
// Demonstrates basic JUnit 5 test structure

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JUnitSetup {

    // Basic test method
    @Test
    public void testAddition() {
        int result = 2 + 3;
        assertEquals(5, result);
    }

    // Test with string operations
    @Test
    public void testStringConcatenation() {
        String greeting = "Hello" + " " + "World";
        assertEquals("Hello World", greeting);
    }

    // Test with array operations
    @Test
    public void testArrayLength() {
        int[] numbers = {1, 2, 3, 4, 5};
        assertEquals(5, numbers.length);
    }
}
