// Exercise 3: Assertions in JUnit
// Demonstrates various JUnit assertions

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AssertionsTest {

    @Test
    public void testAssertions() {
        // Assert equals
        assertEquals(5, 2 + 3);

        // Assert true
        assertTrue(5 > 3);

        // Assert false
        assertFalse(5 < 3);

        // Assert null
        assertNull(null);

        // Assert not null
        assertNotNull(new Object());
    }

    @Test
    public void testStringAssertions() {
        assertEquals("JUnit", "JUnit");
        assertNotEquals("Hello", "World");
        assertTrue("Hello".contains("ell"));
        assertFalse("Hello".contains("xyz"));
    }

    @Test
    public void testArrayAssertions() {
        int[] expected = {1, 2, 3};
        int[] actual = {1, 2, 3};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testExceptionAssertions() {
        assertThrows(ArithmeticException.class, () -> {
            int result = 10 / 0;
        });
    }
}
