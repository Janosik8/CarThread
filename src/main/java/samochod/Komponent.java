package samochod;

public class Komponent {
    protected String nazwa = new String();
    protected int waga;
    protected  int cena;


//getters and setters
    public String getNazwa(){
        return this.nazwa;
    }

    public int getWaga(){
        return this.waga;
    }

    public int getCena(){
        return this.cena;
    }
    }
