public class MovieProgram {

    // Movie class Superclass declared static 
    static class Movie {
        private String title;
        private String nameOfDirector;

        // Constructor to initialize instance variables of objects when they are created
        public Movie(String title, String nameOfDirector) {
            this.title = title;
            this.nameOfDirector = nameOfDirector;
        }

        // Getter and setter for title
        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        // Getter and setter for Director
        public String getNameOfDirector() {
            return nameOfDirector;
        }

        public void setNameOfDirector(String nameOfDirector) {
            this.nameOfDirector = nameOfDirector;
        }

        // toString method to return the string representation of Movie
        @Override
        public String toString() {
            return "Movie Title: " + title + ", Director: " + nameOfDirector;
        }
    }

    // ComedyMovie subclass inherits Movie
    static class ComedyMovie extends Movie {
        private double ticketPrice;

        // Constructor to initialize all variables
        public ComedyMovie(String title, String nameOfDirector, double ticketPrice) {
            super(title, nameOfDirector);  // Call to superlass constructor
            this.ticketPrice = ticketPrice;
        }

        // Getter and setter for ticketPrice 
        public double getTicketPrice() {
            return  ticketPrice;
        }

        public void setTicketPrice(double ticketPrice) {
            this.ticketPrice = ticketPrice;
        }

        // toString method to return the string representation of ComedyMovie
        @Override
        public String toString() {
            return super.toString() + ", Ticket Price: $" + ticketPrice;
        }
    }

    // ActionMovie subclass inherits Movie
    static class ActionMovie extends Movie {
        private double ticketPrice;

        // Constructor to initialize all variables
        public ActionMovie(String title, String nameOfDirector, double ticketPrice) {
            super(title, nameOfDirector);  // Call to superclass constructor
            this.ticketPrice = ticketPrice;
        }

        // Getter and setter for ticketPrice
        public double getTicketPrice() {
            return ticketPrice;
        }

        public void setTicketPrice(double ticketPrice) {
            this.ticketPrice = ticketPrice;
        }

        // toString()method to return the string representation of ActionMovie
        @Override
        public String toString() {
            return super.toString() + ", Ticket Price: $" + ticketPrice;
        }
    }

    // SpyActionMovie subclass inherits ActionMovie
    static class SpyActionMovie extends ActionMovie {
        private double ticketPrice;

        // Constructor to initialize all variables
        public SpyActionMovie(String title, String nameOfDirector, double ticketPrice) {
            super(title, nameOfDirector, ticketPrice);  // Call to super class constructor
            this.ticketPrice = ticketPrice;
        }

        // Getter 
        public double getTicketPrice() {
            return ticketPrice;
        }
        // Setter
        public void setTicketPrice(double ticketPrice) {
            this.ticketPrice = ticketPrice;
        }

        // toString method to return the string representation of SpyActionMovie
        @Override
        public String toString() {
            return super.toString();
        }
    }

    // MovieDriver class main method 
    public static void main(String[] args) {
        // Create an array of Movie objects
        Movie[] movies = new Movie[3];
        
        // Create Action movie
        movies[0] = new ActionMovie("Outlawed", "Adam Collins", 10.00);
        
        // Create Spy Action movie
        movies[1] = new SpyActionMovie("Cloak and Dagger", "Fritz Lang", 12.00);
        
        // Create Comedy movie
        movies[2] = new ComedyMovie("Bridesmaids", "Paul Feig", 8.00);

        // Display details of each movie to console by for loop through each element of movies array
        for (int i = 0; i < movies.length; i++) {
            System.out.println(movies[i]);
        

        }
    }
}
