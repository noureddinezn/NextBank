package SRC.model;

public abstract class Compte  {
    protected String numeroCompte;
    protected double solde;
    // protected Set<Transaction> historiqueTransactions;


public Compte(String numeroCompte, double soldeInitial) {
        this.numeroCompte = numeroCompte;
        this.solde = soldeInitial;
        // this.historiqueTransactions = new HashSet<>();
    }

    public String getNumeroCompte() {
        return numeroCompte;
    }
    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }
    // Abstract methods for Polymorphism (à implémenter dans les classes filles)
    public abstract void depotArgent(double montant);
    public abstract void retraitArgent(double montant);

    
}
