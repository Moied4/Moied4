package webartist;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

@Path("ArtistList")
public class WebArtistResource {

    // Shared HashMap for all operations
    private static HashMap<Integer, Artist> artistHashMap = new HashMap<>();

    // Load initial data from file (once)
    static {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(WebArtistResource.class.getClassLoader()
                        .getResourceAsStream("Artist.in")))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\t");
                int id = Integer.parseInt(parts[0].trim());
                String name = parts[1].trim();
                int numPaintings = Integer.parseInt(parts[2].trim());
                double totalPrice = Double.parseDouble(parts[3].trim());
                Artist artist = new Artist(id, name, numPaintings, totalPrice);
                artistHashMap.put(id, artist);
            }

        } catch (Exception e) {
            e.printStackTrace(); // for debugging
        }
    }

    // ------------------------------
    // 1. Display HTML artist info
    // ------------------------------
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String displayHTMLArtistInfo() {
        StringBuilder html = new StringBuilder();
        int totalPaintings = 0;
        double totalTax = 0;

        html.append("<html><body>");
        html.append("<h2>Artist Information</h2>");
        html.append("<table border='1'>");
        html.append("<tr><th>ID</th><th>Name</th><th>Num Paintings</th><th>Total Price</th><th>Total Tax</th></tr>");

        for (Artist artist : artistHashMap.values()) {
            html.append("<tr>")
                    .append("<td>").append(artist.getArtId()).append("</td>")
                    .append("<td>").append(artist.getArtName()).append("</td>")
                    .append("<td>").append(artist.getNumArtPaint()).append("</td>")
                    .append("<td>").append(String.format("%.2f", artist.getTotArtPaint())).append("</td>")
                    .append("<td>").append(String.format("%.2f", artist.calculateTotalTax())).append("</td>")
                    .append("</tr>");

            totalPaintings += artist.getNumArtPaint();
            totalTax += artist.calculateTotalTax();
        }

        html.append("</table>");
        html.append("<p><b>Total Paintings:</b> ").append(totalPaintings).append("</p>");
        html.append("<p><b>Total Tax:</b> ").append(String.format("%.2f", totalTax)).append("</p>");
        html.append("</body></html>");

        return html.toString();
    }

    // ------------------------------
    // 2. Search artist by ID
    // ------------------------------
    @GET
    @Path("/searchArtist")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchArtist(@QueryParam("art_id") int artId) {
        Artist artist = artistHashMap.get(artId);
        if (artist != null) {
            return Response.ok(artist).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\":\"Artist not found\"}").build();
        }
    }

    // ------------------------------
    // 3. Add new artist via form parameters
    // ------------------------------
    @POST
    @Path("/addNewArtist")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addNewArtistInfo(
            @FormParam("art_id") int artId,
            @FormParam("art_name") String artName,
            @FormParam("num_art_paint") int numArtPaint,
            @FormParam("tot_art_paint") double totArtPaint
    ) {
        if (artistHashMap.containsKey(artId)) {
            return Response.status(Response.Status.CONFLICT)
                    .entity("{\"error\":\"Artist with ID already exists\"}").build();
        }

        Artist artist = new Artist(artId, artName, numArtPaint, totArtPaint);
        artistHashMap.put(artId, artist);
        return Response.ok(artistHashMap).build();
    }
}
