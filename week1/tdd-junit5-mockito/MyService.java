// Service class that depends on ExternalApi
public class MyService {
    private ExternalApi externalApi;

    public MyService(ExternalApi externalApi) {
        this.externalApi = externalApi;
    }

    public String fetchData() {
        return externalApi.getData();
    }

    public String fetchData(String query) {
        return externalApi.fetchData(query);
    }

    public void processData() {
        String data = externalApi.getData();
        externalApi.processData(data.toUpperCase());
    }
}
