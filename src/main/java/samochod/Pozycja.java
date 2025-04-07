package samochod;

public class Pozycja {
    private double x;
    private double y;


    public Pozycja() {
        this.x = 0;
        this.y = 0;
    }

    public Pozycja(double x, double y) {
        this.x = x;
        this.y = y;
    }

    //get
    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    //set
    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
