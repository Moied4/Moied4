package mathOperationsREST;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.*;

@Path("MathOp")
public class WebMathResource {

    @GET
    @Path("calculateHTMLOp")
    @Produces(MediaType.TEXT_HTML)
    public String calculateHTMLOp(@QueryParam("x") int x,
                                  @QueryParam("y") int y,
                                  @QueryParam("z") int z) {
        MathOp op = new MathOp(x, y, z);
        return "<html><body>" +
               "<h3>Sum: " + op.calculateSum() + "</h3>" +
               "<h3>Product: " + op.calculatePrd() + "</h3>" +
               "</body></html>";
    }

    @GET
    @Path("displayXYZJSON")
    @Produces(MediaType.APPLICATION_JSON)
    public MathOp displayXYZJSON() {
        return new MathOp(1, 2, 3);
    }

    @GET
    @Path("listArray")
    @Produces(MediaType.TEXT_HTML)
    public String displayListXYZ() {
        List<MathOp> listXYZ = new ArrayList<>();
        listXYZ.add(new MathOp(1, 2, 3));
        listXYZ.add(new MathOp(4, 5, 6));
        listXYZ.add(new MathOp(7, 8, 9));

        StringBuilder sb = new StringBuilder("<html><body>");
        for (MathOp m : listXYZ) {
            sb.append("<p>x: ").append(m.getX())
              .append(", y: ").append(m.getY())
              .append(", z: ").append(m.getZ())
              .append(", Sum: ").append(m.calculateSum())
              .append(", Product: ").append(m.calculatePrd())
              .append("</p>");
        }
        sb.append("</body></html>");
        return sb.toString();
    }

    @GET
    @Path("OpHashMap/{x}")
    @Produces(MediaType.APPLICATION_JSON)
    public MathOp searchHashMapListXYZ(@PathParam("x") int x) {
        Map<Integer, MathOp> opHashMap = new HashMap<>();
        opHashMap.put(1, new MathOp(1, 2, 3));
        opHashMap.put(4, new MathOp(4, 5, 6));
        opHashMap.put(7, new MathOp(7, 8, 9));

        return opHashMap.getOrDefault(x, new MathOp(0, 0, 0));
    }
}
