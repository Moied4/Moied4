package webCar;

import webCar.Car;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/webcar")
public class WebCarServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Create a sample car
        Car car = new Car("Toyota", "Camry", 2020);

        // Set response type
        response.setContentType("text/html");

        // Output HTML
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h2>Car Details</h2>");
        out.println("<p>Make: " + car.getMake() + "</p>");
        out.println("<p>Model: " + car.getModel() + "</p>");
        out.println("<p>Year: " + car.getYear() + "</p>");
        out.println("</body></html>");
    }
}
