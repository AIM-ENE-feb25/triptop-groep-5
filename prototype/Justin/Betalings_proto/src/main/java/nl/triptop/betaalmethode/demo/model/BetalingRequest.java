package nl.triptop.betaalmethode.demo.model;

public class BetalingRequest {
    private String methode;
    private double bedrag;

    public String getMethode() {
        return methode;
    }

    public void setMethode(String methode) {
        this.methode = methode;
    }

    public double getBedrag() {
        return bedrag;
    }

    public void setBedrag(double bedrag) {
        this.bedrag = bedrag;
    }
}
