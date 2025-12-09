package webHelloService;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL)
public class HelloService {

    @WebMethod
    public String sayHello(String name) {
        if (name == null || name.isEmpty()) name = "Guest";
        return "Hello " + name + "! Welcome to WebHelloServiceProject.";
    }
}
