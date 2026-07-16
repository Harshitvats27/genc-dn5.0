// Exercise 4: Arrange-Act-Assert (AAA) Pattern
// Test Fixtures, Setup and Teardown Methods in JUnit

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class AAAPatternTest {

    private MyService service;
    private ExternalApi mockApi;

    // Setup method - runs before each test
    @BeforeAll
    public static void setUpClass() {
        System.out.println("Setting up test class (runs once)");
    }

    @BeforeEach
    public void setUp() {
        System.out.println("Setting up test (runs before each test)");
        // Arrange: Create mock and service
        mockApi = new ExternalApi() {
            @Override
            public String getData() { return "Test Data"; }
            @Override
            public String fetchData(String query) { return "Result: " + query; }
            @Override
            public void processData(String data) { }
        };
        service = new MyService(mockApi);
    }

    // Teardown method - runs after each test
    @AfterEach
    public void tearDown() {
        System.out.println("Tearing down test (runs after each test)");
        service = null;
        mockApi = null;
    }

    @AfterAll
    public static void tearDownClass() {
        System.out.println("Tearing down test class (runs once)");
    }

    // AAA Pattern Test
    @Test
    public void testFetchData() {
        // Arrange
        String expected = "Test Data";

        // Act
        String result = service.fetchData();

        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void testFetchDataWithQuery() {
        // Arrange
        String query = "JUnit";
        String expected = "Result: JUnit";

        // Act
        String result = service.fetchData(query);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void testProcessData() {
        // Arrange
        // (service already set up in @BeforeEach)

        // Act
        service.processData();

        // Assert
        // No exception means success
        assertNotNull(service);
    }
}
