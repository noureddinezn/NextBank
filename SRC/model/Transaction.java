package src.model;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class Transaction {

    private int idTransaction;
    private TypeTransaction type; 
    private double montant;
    private Date date;
    private String compteSource;
    private String compteDestination;

    public Transaction(int idTransaction, TypeTransaction type, double montant, String compteSource) {
        this.idTransaction = idTransaction;
        this.type = type;
        this.montant = montant;
        this.date = new Date(); 
        this.compteSource = compteSource;
        this.compteDestination = null; 
    }

    public Transaction(int idTransaction, TypeTransaction type, double montant, String compteSource, String compteDestination) {
        this.idTransaction = idTransaction;
        this.type = type;
        this.montant = montant;
        this.date = new Date(); 
        this.compteSource = compteSource;
        this.compteDestination = compteDestination;
    }

    public int getIdTransaction() {
        return idTransaction;
    }

    public TypeTransaction getType() {
        return type;
    }

    public double getMontant() {
        return montant;
    }

    public Date getDate() {
        return date;
    }

    public String getCompteSource() {
        return compteSource;
    }

    public String getCompteDestination() {
        return compteDestination;
    }

    public static void Virement(Compte compteSource, Compte compteDestination, double montant) {
        System.out.println("--- TENTATIVE DE VIREMENT ---");
        
        if (montant > 0 && compteSource.getSolde() >= montant) {
            
            compteSource.setSolde(compteSource.getSolde() - montant);
            compteDestination.setSolde(compteDestination.getSolde() + montant);
            
            Transaction tx = new Transaction(
                (int) (Math.random() * 1000), 
                TypeTransaction.Virement, 
                montant, 
                compteSource.getNumeroCompte(), 
                compteDestination.getNumeroCompte()
            );
            
            compteSource.historiqueTransactions.add(tx);
            compteDestination.historiqueTransactions.add(tx);
            
            System.out.println("Succes : Virement de " + montant + " DH effectue de " + compteSource.getNumeroCompte() + " vers " + compteDestination.getNumeroCompte());
        } else {
            System.out.println("Erreur : Echec du virement. Solde insuffisant ou montant invalide !");
        }
    }

    public static void historiqueTransaction(Compte compte) {
        System.out.println("\n--- HISTORIQUE DES TRANSACTIONS (Compte: " + compte.getNumeroCompte() + ") ---");
        
        if (compte.historiqueTransactions.isEmpty()) {
            System.out.println("Aucune transaction trouvee pour ce compte.");
        } else {
            for (Transaction tx : compte.historiqueTransactions) {
                System.out.println("- ID: " + tx.getIdTransaction() + 
                                   " | Type: " + tx.getType() + 
                                   " | Montant: " + tx.getMontant() + " DH");
            }
        }
        System.out.println("------------------------------------------------------\n");
    }
    // Methode pour enregistrer l'historique dans un fichier texte simple
    public static void enregistreFichier(Compte compte) {
        String nomFichier = "Historique_" + compte.getNumeroCompte() + ".txt";

        try (PrintWriter writer = new PrintWriter(new FileWriter(nomFichier))) {
            writer.println("--- HISTORIQUE DES TRANSACTIONS ---");
            writer.println("Compte : " + compte.getNumeroCompte());
            writer.println("-----------------------------------");

            if (compte.historiqueTransactions.isEmpty()) {
                writer.println("Aucune transaction trouvee.");
            } else {
                for (Transaction tx : compte.historiqueTransactions) {
                    writer.println("- ID: " + tx.getIdTransaction() + 
                                   " | Type: " + tx.getType() + 
                                   " | Montant: " + tx.getMontant() + " DH");
                }
            }
            
            System.out.println("Succes : L'historique a ete enregistre dans le fichier [" + nomFichier + "]");
            
        } catch (IOException e) {
            System.out.println("Erreur : Impossible de sauvegarder le fichier.");
        }
    }
    
}