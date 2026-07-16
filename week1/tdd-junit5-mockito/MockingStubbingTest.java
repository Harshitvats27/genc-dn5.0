// Exercise 1: Mocking and Stubbing
// Testing a service with mocked external API

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class MockingStubbingTest {

    @Test
    public void testExternalApi() {
        // Create mock object
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);

        // Stub the method to return predefined value
        when(mockApi.getData()).thenReturn("Mock Data");

        // Create service with mock dependency
        MyService service = new MyService(mockApi);

        // Call method
        String result = service.fetchData();

        // Verify result
        assertEquals("Mock Data", result);
    }

    @Test
    public void testStubWithQuery() {
        // Create mock object
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);

        // Stub fetchData with specific argument
        when(mockApi.fetchData("test")).thenReturn("Stubbed Result");

        // Create service and call method
        MyService service = new MyService(mockApi);
        String result = service.fetchData("test");

        // Verify
        assertEquals("Stubbed Result", result);
    }

    @Test
    public void testStubMultipleCalls() {
        // Create mock object
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);

        // Stub to return different values on consecutive calls
        when(mockApi.getData())
            .thenReturn("First Call")
            .thenReturn("Second Call");

        // Create service
        MyService service = new MyService(mockApi);

        // Verify different returns
        assertEquals("First Call", service.fetchData());
        assertEquals("Second Call", service.fetchData());
    }

    @Test
    public void testStubWithException() {
        // Create mock object
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);

        // Stub to throw exception
        when(mockApi.getData()).thenThrow(new RuntimeException("API Error"));

        // Create service
        MyService service = new MyService(mockApi);

        // Verify exception is thrown
        assertThrows(RuntimeException.class, () -> {
            service.fetchData();
        });
    }
}
