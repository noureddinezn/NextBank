package src.model;
import java.util.HashSet;
import java.util.Set;

public abstract class Compte  {
    protected String numeroCompte;
    protected double solde;
   protected Set<Transaction> historiqueTransactions;


public Compte(String numeroCompte, double soldeInitial) {
        this.numeroCompte = numeroCompte;
        this.solde = soldeInitial;
         this.historiqueTransactions = new HashSet<>();
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
public void afficherHistorique() {
        System.out.println("\n--- HISTORIQUE DES TRANSACTIONS (Compte: " + this.numeroCompte + ") ---");
        
        if (this.historiqueTransactions.isEmpty()) {
            System.out.println("Aucune transaction trouvee pour ce compte.");
        } else {
            for (Transaction tx : this.historiqueTransactions) {
                System.out.println("- ID: " + tx.getIdTransaction() + 
                                   " | Type: " + tx.getType() + 
                                   " | Montant: " + tx.getMontant() + " DH" +
                                   " | Date: " + tx.getDate());
            }
        }
        System.out.println("------------------------------------------------------\n");
    }
    
}
