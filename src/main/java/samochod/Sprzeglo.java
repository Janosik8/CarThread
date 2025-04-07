package samochod;

public class Sprzeglo extends Komponent{
    private boolean stanSprzegla;

    public Sprzeglo(String nazwa, int waga, int cena){
        this.waga = waga;
        this.nazwa = nazwa;
        this.cena = cena;
        this.stanSprzegla = false;
    }
//set
    public void wcisnij(){
        this.stanSprzegla = true;
    }

    public void zwolnij(){
        this.stanSprzegla = false;
    }

//get
    public boolean getStanSprzegla(){
        return stanSprzegla;
    }


}
