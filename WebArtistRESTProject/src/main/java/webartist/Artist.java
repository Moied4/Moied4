package webartist;

public class Artist {
    private int artId;
    private String artName;
    private int numArtPaint;
    private double totArtPaint;

    public Artist(int artId, String artName, int numArtPaint, double totArtPaint) {
        this.artId = artId;
        this.artName = artName;
        this.numArtPaint = numArtPaint;
        this.totArtPaint = totArtPaint;
    }

    public int getArtId() {
        return artId;
    }

    public String getArtName() {
        return artName;
    }

    public int getNumArtPaint() {
        return numArtPaint;
    }

    public double getTotArtPaint() {
        return totArtPaint;
    }

    public double calculateTotalTax() {
        double prvTax = totArtPaint * 0.09; // Provincial tax 9%
        double fedTax = totArtPaint * 0.07; // Federal tax 7%
        return prvTax + fedTax;
    }
}

