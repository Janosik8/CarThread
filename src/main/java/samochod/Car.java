package samochod;

import java.util.ArrayList;
import java.util.List;

public class Car extends Thread {
    public Pozycja pozycja = new Pozycja();
    public Pozycja cel;
    public SkrzyniaBiegow skrzynia;
    public Silnik silnik;
    private  String nrRejest;
    private String model;
    private  Integer maxVel;
    private Boolean stanWlaczenia;



    public  Car(String nrRej, String model, int max_Vel, SkrzyniaBiegow skrzynia, Silnik silnik) {
        this.stanWlaczenia = false;
        this.nrRejest = nrRej;
        this.model = model;
        this.maxVel = max_Vel;
        this.silnik = silnik;
        this.skrzynia = skrzynia;
        start();
    }

    @Override
    public void run() {
            while (true) {
                if (this.cel != null) {
                    double odleglosc = Math.sqrt(Math.pow(this.cel.getX() - this.pozycja.getX(), 2) +
                            Math.pow(this.cel.getY() - this.pozycja.getY(), 2));
                    double dx = 0.1*getAktPredkosc() * (this.cel.getX() - this.pozycja.getX()) /
                            odleglosc;
                    double dy = 0.1*getAktPredkosc() * (this.cel.getY() - this.pozycja.getY()) /
                            odleglosc;
//                    double dx2 = Math.round((this.getAktPozycjaX() + dx) * 100) / 100.0;
//                    double dy2 = Math.round((this.getAktPozycjaY() + dy) * 100) / 100.0;
                    this.pozycja.setPosition(this.getAktPozycjaX() + dx, this.getAktPozycjaY() + dy);

                    notifyListeners();
                    if (Math.round(this.getAktPozycjaY()) == Math.round(this.cel.getY()) &&
                            Math.round(this.getAktPozycjaX()) == Math.round(this.cel.getX())) {
                        this.cel = null;
                    }
            }
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
    }

    public void jedzDo(Pozycja cel1){
            this.cel = cel1;
            System.out.println(this.cel.getX() + " " + this.cel.getY());
    }

    public void wlacz() {
        silnik.uruchom();
   }

   public void wylacz(){
        silnik.zatrzymaj();
   }


//observer methods
private List<Listener> listeners = new ArrayList<>();
    public void addListener(Listener listener) {
        listeners.add(listener);
        System.out.println("Listener added");
    }
    public void removeListener(Listener listener) {
        listeners.remove(listener);
        System.out.println("listener removed");
    }
    private void notifyListeners() {
            for (Listener listener : listeners) {
                listener.update();
            }
    }



   //get
   public int getWaga(){
       return skrzynia.getWaga() + skrzynia.sprzeglo.getWaga()+ silnik.getWaga();
   }

   public double  getAktPredkosc() {
            if(skrzynia.getAktualnyBieg() == 0 || silnik.getObroty() == 0){
                return 0.0;
            }
            double value = skrzynia.getAktualnyBieg() % 10 + silnik.getObroty() % 10;

       return value;
   }

   public Double getAktPozycjaX(){
    return this.pozycja.getX();
   }

    public Double getAktPozycjaY(){
        return this.pozycja.getY();
    }

   public String getModel(){
        return this.model;
   }

    public String getNrRej(){
        return this.nrRejest;
    }

    public int getMaxVel(){
        return this.maxVel;
    }

}
