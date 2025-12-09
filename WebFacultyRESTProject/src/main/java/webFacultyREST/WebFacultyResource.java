package webFacultyREST;

import jakarta.json.stream.JsonGenerator;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.*;
import java.util.*;
import jakarta.json.*;

@Path("/WebFaculty")
public class WebFacultyResource {

    private static final String INPUT_FILE = "Faculty.in";
    private Map<Integer, Faculty> facultyMap = new HashMap<>();

    // Constructor: load initial data
    public WebFacultyResource() {
        try {
            facultyMap = loadFacultyData();
        } catch (Exception e) {
            // Just print friendly message instead of stack trace
            System.out.println("Error loading faculty data: " + e.getMessage());
        }
    }

    // ------------------ DISPLAY HTML ------------------
    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response displayHTMLFacultyInfo() {
        try {
            StringBuilder html = new StringBuilder();
            html.append("<html><head><title>Faculty Info</title></head><body>");
            html.append("<h2>Unsorted Faculty Data</h2>");
            html.append(buildHtmlTable(facultyMap.values()));

            html.append("<h2>Sorted Faculty Data (by Bonus)</h2>");
            List<Faculty> sortedList = new ArrayList<>(facultyMap.values());
            sortedList.sort(Comparator.comparingDouble(Faculty::doCalc_Bonus));
            html.append(buildHtmlTable(sortedList));

            html.append("</body></html>");
            return Response.ok(html.toString()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error processing request: " + e.getMessage())
                    .build();
        }
    }

    // ------------------ LOAD FACULTY DATA ------------------
    private Map<Integer, Faculty> loadFacultyData() throws Exception {
        Map<Integer, Faculty> map = new HashMap<>();
        InputStream is = getClass().getClassLoader().getResourceAsStream(INPUT_FILE);
        if (is == null) throw new Exception("Input file " + INPUT_FILE + " not found.");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split("\\t");
                if (tokens.length != 6) continue;

                Integer id = Integer.parseInt(tokens[0].trim());
                String lname = tokens[1].trim();
                String fname = tokens[2].trim();
                String zipcode = tokens[3].trim();
                double salary = Double.parseDouble(tokens[4].trim());
                double bonusRate = Double.parseDouble(tokens[5].trim());

                Faculty faculty = new Faculty(id, lname, fname, zipcode, salary, bonusRate);
                map.put(id, faculty);
            }
        }
        return map;
    }

    // ------------------ HTML TABLE ------------------
    private String buildHtmlTable(Collection<Faculty> faculties) {
        StringBuilder sb = new StringBuilder();
        sb.append("<table border='1' cellpadding='5' cellspacing='0'>");
        sb.append("<tr>")
          .append("<th>ID</th>")
          .append("<th>Last Name</th>")
          .append("<th>First Name</th>")
          .append("<th>Zip Code (JSON Info)</th>")
          .append("<th>Salary</th>")
          .append("<th>Bonus Rate (%)</th>")
          .append("<th>Bonus Amount</th>")
          .append("</tr>");
        for (Faculty f : faculties) {
            String zipJson = fetchZipCodeJson(f.getF_zipcodeBirth());
            sb.append("<tr>")
              .append("<td>").append(f.getF_Id()).append("</td>")
              .append("<td>").append(f.getF_Lname()).append("</td>")
              .append("<td>").append(f.getF_Fname()).append("</td>")
              .append("<td><pre style='white-space:pre-wrap;'>").append(zipJson).append("</pre></td>")
              .append("<td>").append(String.format("%.2f", f.getF_Salary())).append("</td>")
              .append("<td>").append(String.format("%.2f", f.getF_BonusRate())).append("</td>")
              .append("<td>").append(String.format("%.2f", f.doCalc_Bonus())).append("</td>")
              .append("</tr>");
        }
        sb.append("</table>");
        return sb.toString();
    }

    // ------------------ FETCH ZIP JSON ------------------
    private String fetchZipCodeJson(String zipcode) {
        String apiUrl = "http://api.zippopotam.us/us/" + zipcode;
        try {
            java.net.URL url = new java.net.URL(apiUrl);
            java.net.HttpURLConnection conn = (java.net.HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            int code = conn.getResponseCode();
            if (code != 200) return "No data found for ZIP: " + zipcode;

            try (InputStream is = conn.getInputStream();
                 JsonReader jsonReader = Json.createReader(is)) {
                JsonObject jsonObject = jsonReader.readObject();
                Map<String, Object> properties = new HashMap<>();
                properties.put(JsonGenerator.PRETTY_PRINTING, true);
                StringWriter sw = new StringWriter();
                try (JsonWriter jsonWriter = Json.createWriterFactory(properties).createWriter(sw)) {
                    jsonWriter.writeObject(jsonObject);
                }
                return sw.toString();
            }
        } catch (Exception e) {
            return "Error fetching ZIP data";
        }
    }

    // ------------------ SEARCH FACULTY ------------------
    @GET
    @Path("/searchFaculty")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchFaculty(@QueryParam("f_id") int facultyId) {
        Faculty f = facultyMap.get(facultyId);
        if (f != null) return Response.ok(convertFacultyToJson(f)).build();
        return Response.status(Response.Status.NOT_FOUND)
                .entity("{\"error\":\"Faculty ID " + facultyId + " not found.\"}")
                .build();
    }

    private JsonObject convertFacultyToJson(Faculty f) {
        return Json.createObjectBuilder()
                .add("f_id", f.getF_Id())
                .add("f_Lname", f.getF_Lname())
                .add("f_Fname", f.getF_Fname())
                .add("f_zipcodeBirth", f.getF_zipcodeBirth())
                .add("f_Salary", f.getF_Salary())
                .add("f_BonusRate", f.getF_BonusRate())
                .build();
    }

    // ------------------ ADD NEW FACULTY ------------------
    @POST
    @Path("/addNewFaculty")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public JsonArray addNewFaculty(
            @FormParam("f_id") int f_id,
            @FormParam("f_Lname") String f_Lname,
            @FormParam("f_Fname") String f_Fname,
            @FormParam("f_zipcodeBirth") String f_zipcodeBirth,
            @FormParam("f_Salary") double f_Salary,
            @FormParam("f_BonusRate") double f_BonusRate) {

        Faculty newFaculty = new Faculty(f_id, f_Lname, f_Fname, f_zipcodeBirth, f_Salary, f_BonusRate);
        facultyMap.put(f_id, newFaculty);

        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (Faculty f : facultyMap.values()) {
            arrayBuilder.add(convertFacultyToJson(f));
        }
        return arrayBuilder.build();
    }
}
