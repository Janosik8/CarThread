package samochod;

public class Silnik extends Komponent{
    private double maxObroty;
    private double obroty;
    //konstruktor
    public Silnik(String nazwa, int waga, int cena, double maxObroty){
        this.waga = waga;
        this.nazwa = nazwa;
        this.cena = cena;
        this.maxObroty = maxObroty;
        this.obroty = 0;
    }

//get set
   public void uruchom() {
      this.obroty = 900;
   }

  public void zatrzymaj(){
       this.obroty = 0;
  }

  public void zwiekszObroty(){
       if(this.obroty <= (this.maxObroty + 500)){
       this.obroty+=500;
       }
  }

  public void zmniejszObroty(){
       if(this.obroty >= 600){
           this.obroty -= 200;
       }else{
           zatrzymaj();
       }
  }

  public double getObroty(){
       return this.obroty;
  }


}
