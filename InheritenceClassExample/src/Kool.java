import java.util.Date;

public class Kool {
    private String colour = "White";
    private boolean filled = false;
    private Date dateCreated;

    // Default constructor
    public GeometricObject() {
        this.dateCreated = new Date(); // Set to current date by default
    }

    // Parameterized constructor
    public GeometricObject(String colour, boolean filled, Date dateCreated) {
        this.colour = colour;
        this.filled = filled;
        this.dateCreated = (dateCreated != null) ? dateCreated : new Date();
    }

    // Getters and setters
    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
        this.filled = true;
    }

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    // toString method
    @Override
    public String toString() {
        return "Geometric object color = " + this.colour +
               ", filled = " + this.filled +
               ", date created = " + this.dateCreated;
    }
}
