package CreationPattern;
public class BuilderPattern {
    public static void main(String[] args) {
            HttpRequest http=new HttpBuilder().withBody("Yash")
            .withMethod("POST")
            .withUrl("http://yash.coom")
            .withTimeOut(122).build();
            http.execute();
        }
}

class HttpRequest {
    String url;
    String method;
    String body;
    Integer timeOut;

    void execute() {
        System.out.println(url);
        System.out.println(method);
        System.out.println(body);
        System.out.println(timeOut);
    }
}

class HttpBuilder
{
    HttpRequest req;
    
       HttpBuilder() {
        req = new HttpRequest(); // ✅ critical fix
    }
    HttpBuilder withUrl(String url)
    {
        req.url=url;
        return this;
    }
     HttpBuilder withMethod(String method)
    {
        req.method=method;
        return this;
    }
     HttpBuilder withBody(String body)
    {
        req.body=body;
        return this;
    }
     HttpBuilder withTimeOut(Integer timeout)
    {
        req.timeOut=timeout;
        return this;
    }
    HttpRequest build()
    {
        return req;
    }

}
