// Exercise 2: Verifying Interactions
// Ensuring methods are called with specific arguments

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class VerifyInteractionTest {

    @Test
    public void testVerifyInteraction() {
        // Create mock object
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);

        // Create service with mock
        MyService service = new MyService(mockApi);

        // Call the method
        service.fetchData();

        // Verify that getData() was called
        verify(mockApi).getData();
    }

    @Test
    public void testVerifyWithArguments() {
        // Create mock object
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);

        // Create service
        MyService service = new MyService(mockApi);

        // Call method with specific argument
        service.fetchData("testQuery");

        // Verify fetchData was called with specific argument
        verify(mockApi).fetchData("testQuery");
    }

    @Test
    public void testVerifyCallCount() {
        // Create mock object
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);

        // Create service
        MyService service = new MyService(mockApi);

        // Call method multiple times
        service.fetchData();
        service.fetchData();
        service.fetchData();

        // Verify getData was called exactly 3 times
        verify(mockApi, times(3)).getData();
    }

    @Test
    public void testVerifyNoInteractions() {
        // Create mock object
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);

        // Create service but don't call any methods
        MyService service = new MyService(mockApi);

        // Verify no interactions occurred
        verifyNoInteractions(mockApi);
    }

    @Test
    public void testVerifyNeverCalled() {
        // Create mock object
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);

        // Create service and call fetchData (not processData)
        MyService service = new MyService(mockApi);
        service.fetchData();

        // Verify processData was never called
        verify(mockApi, never()).processData(anyString());
    }
}
