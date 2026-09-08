package SRC.model;

public class Epargne  extends Compte{
   public Epargne(String numeroCompte, double soldeInitial) {
        super(numeroCompte, soldeInitial);
    }

    @Override
    public void depotArgent(double montant) {
        this.solde += montant;
    }

    @Override
    public void retraitArgent(double montant) {
        this.solde -= montant;
    }
    
}
