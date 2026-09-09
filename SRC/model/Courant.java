package src.model;

public class Courant  extends Compte{
   public Courant(String numeroCompte, double soldeInitial) {
        super(numeroCompte, soldeInitial);
    }

   

@Override
    public void depotArgent(double montant) {
        if (montant > 0) {
            this.solde += montant;

            Transaction tx = new Transaction((int) (Math.random() * 1000), TypeTransaction.Depot, montant, this.numeroCompte);
            this.historiqueTransactions.add(tx);
        }
    }

  @Override
    public void retraitArgent(double montant) {
        if (montant > 0 && this.solde >= montant) {
            this.solde -= montant;

            Transaction tx = new Transaction((int) (Math.random() * 1000), TypeTransaction.Retrait, montant, this.numeroCompte);
            this.historiqueTransactions.add(tx);
        } else {
            System.out.println("Erreur: Solde insuffisant ou montant invalide !");
        }
    }
    
    
}
