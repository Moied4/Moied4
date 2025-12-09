package mathOperationsREST;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

@Path("/MathOp")
public class MathOpResource {

    // Part A: GET endpoint to calculate and display results in HTML
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String calculateHTMLOp(@QueryParam("x") int x,
                                  @QueryParam("y") int y,
                                  @QueryParam("z") int z) {

        MathOp op = new MathOp(x, y, z);
        int sum = op.calculateSum();
        int product = op.calculatePrd();

        return "<html><body>" +
                "<h2>Results for x=" + x + ", y=" + y + ", z=" + z + "</h2>" +
                "<p>Sum (x + 2y + 3z): <b>" + sum + "</b></p>" +
                "<p>Product (x * 2y * 3z): <b>" + product + "</b></p>" +
                "</body></html>";
    }

    // Part B: POST endpoint to search HashMap and return JSON
    @POST
    @Path("/OpFormHashMap")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, MathOp> searchFormHashMapListZYZ(@FormParam("x") int x) {

        // Create HashMap with MathOp objects
        Map<String, MathOp> opHashMap = new HashMap<>();
        opHashMap.put("1", new MathOp(1, 2, 3));
        opHashMap.put("4", new MathOp(4, 5, 6));
        opHashMap.put("7", new MathOp(7, 8, 9));

        // Search using form parameter x
        MathOp result = opHashMap.get(String.valueOf(x));

        // Prepare response map
        Map<String, MathOp> response = new HashMap<>();
        if (result != null) {
            response.put("Found", result);
        } else {
            response.put("Not Found", new MathOp(0, 0, 0));
        }

        return response;
    }
}
