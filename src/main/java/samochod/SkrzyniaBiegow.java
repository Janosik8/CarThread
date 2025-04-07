package samochod;

public class SkrzyniaBiegow extends Komponent {
    private  int aktualnyBieg;
    private  int iloscBiegow;
    private  float aktualnePrzelozenie;
    public Sprzeglo sprzeglo;
    //konstruktor
    public  SkrzyniaBiegow(String nazwa, int waga, int cena, int iloscBiegow, Sprzeglo sprzeglo){
        this.iloscBiegow = iloscBiegow;
        this.aktualnyBieg = 0;
        this.aktualnePrzelozenie = 100;
        this.sprzeglo = sprzeglo;
        this.waga = waga;
        this.nazwa = nazwa;
        this.cena = cena;
    }

    //set
    public void zwiekszBieg(){
       if(this.aktualnyBieg < this.iloscBiegow && this.sprzeglo.getStanSprzegla()){
           this.aktualnyBieg += 1;
       }
    }

    public void zmniejszBieg(){
       if(this.aktualnyBieg > 0 && this.sprzeglo.getStanSprzegla()){
           this.aktualnyBieg -= 1;
       }
    }

//get
    public int getAktualnyBieg(){
       return this.aktualnyBieg;
    }

    public float getAktPrzelozenie(){
       return this.aktualnePrzelozenie;
    }


}
