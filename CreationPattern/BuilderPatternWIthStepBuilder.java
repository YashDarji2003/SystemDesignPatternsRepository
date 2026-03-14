import java.util.HashMap;
import java.util.Map;

public class BuilderPatternWIthStepBuilder {

    public static void main(String[] args) {

        HttpRequest request =
                HttpRequest.builder()
                        .withUrl("https://api.example.com/users")
                        .withMethod("POST")
                        .withHeader("Authorization", "Bearer token")
                        .withBody("{\"name\":\"Simon\"}")
                        .withTimeout(5000)
                        .build();

        request.execute();
    }
}

class HttpRequest {

    String url;
    String method;
    Map<String, String> headers = new HashMap<>();
    Map<String, String> queryParam = new HashMap<>();
    String body;
    int timeout;

    public void execute() {

        System.out.println("URL: " + url);
        System.out.println("Method: " + method);

        if (!queryParam.isEmpty()) {
            System.out.println("Query Params:");
            for (Map.Entry<String, String> entry : queryParam.entrySet()) {
                System.out.println(entry.getKey() + " = " + entry.getValue());
            }
        }

        if (!headers.isEmpty()) {
            System.out.println("Headers:");
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                System.out.println(entry.getKey() + " = " + entry.getValue());
            }
        }

        if (body != null && !body.isEmpty()) {
            System.out.println("Body: " + body);
        }

        System.out.println("Timeout: " + timeout);
        System.out.println("Request Executed Successfully");
    }

    // Entry point for builder
    public static UrlStep builder() {
        return new HttpRequestStepBuilder();
    }

    // Builder implementation
    private static class HttpRequestStepBuilder implements
            UrlStep,
            MethodStep,
            HeaderStep,
            OptionalStep {

        private HttpRequest req = new HttpRequest();

        @Override
        public MethodStep withUrl(String url) {
            req.url = url;
            return this;
        }

        @Override
        public HeaderStep withMethod(String method) {
            req.method = method;
            return this;
        }

        @Override
        public OptionalStep withHeader(String key, String value) {
            req.headers.put(key, value);
            return this;
        }

        @Override
        public OptionalStep withBody(String body) {
            req.body = body;
            return this;
        }

        @Override
        public OptionalStep withTimeout(int timeout) {
            req.timeout = timeout;
            return this;
        }

        @Override
        public HttpRequest build() {
            return req;
        }
    }
}

interface UrlStep {
    MethodStep withUrl(String url);
}

interface MethodStep {
    HeaderStep withMethod(String method);
}

interface HeaderStep {
    OptionalStep withHeader(String key, String value);
}

interface OptionalStep {

    OptionalStep withBody(String body);

    OptionalStep withTimeout(int timeout);

    HttpRequest build();
}