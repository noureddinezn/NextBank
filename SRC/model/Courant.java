package src.model;

public class Courant  extends Compte{
    public Courant(String numeroCompte, double soldeInitial) {
        super(numeroCompte, soldeInitial);
    }

    @Override 

   public void depotArgent(double montant){
      this.solde += montant;

    }

    @Override 

  public void retraitArgent(double montant){
            this.solde -= montant;
    }
    
    
}
