package webCourseREST;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

@Path("WebCourse")
public class WebCourseResource {

    // Reads Course data from Course.in file into an ArrayList
    private List<Course> readCoursesFromFile() {
        List<Course> courseList = new ArrayList<>();
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream("Course.in");
            if (is == null) throw new RuntimeException("Course.in file not found!");

            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\t");
                if (parts.length == 4) {
                    String course_no = parts[0].trim();
                    String course_name = parts[1].trim();
                    int credits = Integer.parseInt(parts[2].trim());
                    int max_enrl = Integer.parseInt(parts[3].trim());

                    Course course = new Course(course_no, course_name, max_enrl, credits);
                    courseList.add(course);
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return courseList;
    }

    @GET
    @Path("displayHTMLCourseInfo")
    @Produces(MediaType.TEXT_HTML)
    public String displayHTMLCourseInfo() {
        List<Course> courseList = readCoursesFromFile();

        StringBuilder html = new StringBuilder("<html><body>");
        html.append("<h2>Course List</h2>");
        html.append("<table border='1'>");
        html.append("<tr><th>Course No</th><th>Course Name</th><th>Max Enrollment</th><th>Credits</th><th>Total Fees</th></tr>");

        for (Course c : courseList) {
            html.append("<tr>")
                .append("<td>").append(c.getCourse_no()).append("</td>")
                .append("<td>").append(c.getCourse_name()).append("</td>")
                .append("<td>").append(c.getMax_enrl()).append("</td>")
                .append("<td>").append(c.getCredits()).append("</td>")
                .append("<td>").append(c.calculateTotalFees()).append("</td>")
                .append("</tr>");
        }

        html.append("</table></body></html>");
        return html.toString();
    }

    @GET
    @Path("displayTextCourseInfo")
    @Produces(MediaType.TEXT_PLAIN)
    public String displayTextCourseInfo() {
        List<Course> courseList = readCoursesFromFile();

        StringBuilder sb = new StringBuilder();
        for (Course c : courseList) {
            sb.append(String.format("Course No: %s, Name: %s, Max Enrollment: %d, Credits: %d, Total Fees: %d\n",
                    c.getCourse_no(), c.getCourse_name(), c.getMax_enrl(), c.getCredits(), c.calculateTotalFees()));
        }
        return sb.toString();
    }

    @GET
    @Path("displayJSONCourseInfo")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Course> displayJSONCourseInfo() {
        return readCoursesFromFile();
    }

    @GET
    @Path("searchCourse/{course_no}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchJSONCourseInfo(@PathParam("course_no") String course_no) {
        List<Course> courseList = readCoursesFromFile();
        for (Course c : courseList) {
            if (c.getCourse_no().equalsIgnoreCase(course_no)) {
                return Response.ok(c).build();
            }
        }
        // Return JSON error with 404 NOT FOUND
        return Response.status(Response.Status.NOT_FOUND)
                       .entity("{\"error\":\"Course " + course_no + " not found\"}")
                       .build();
    }

    @GET
    @Path("totalCourseFees/{course_no}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getTotalCourseFees(@PathParam("course_no") String course_no) {
        double total = calculateTotalCourseFees(course_no);
        if (total >= 0) {
            return Response.ok(String.format("%.2f", total)).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("Course number not found.")
                           .build();
        }
    }

    public double calculateTotalCourseFees(String course_no) {
        List<Course> courseList = readCoursesFromFile();
        for (Course c : courseList) {
            if (c.getCourse_no().equalsIgnoreCase(course_no)) {
                return c.calculateTotalFees();
            }
        }
        return -1; // Indicates not found
    }
}
