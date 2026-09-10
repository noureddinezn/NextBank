package src.model;

import java.util.Date;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

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

    // Methode pour afficher l'historique dans la console
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

    // Methode pour sauvegarder l'historique dans un fichier (Flat File Database) en utilisant Character Streams
    public static void enregistreFichier(Compte compte) {
        String nomFichier = "transactions_nexbank.csv";

        // Utilisation de try-with-resources pour fermer le flux automatiquement
        // Le parametre 'true' dans FileWriter permet d'ajouter a la fin du fichier (Append mode) sans ecraser l'ancien contenu
        try (PrintWriter writer = new PrintWriter(new FileWriter(nomFichier, true))) {
            
            if (!compte.historiqueTransactions.isEmpty()) {
                for (Transaction tx : compte.historiqueTransactions) {
                    // Format CSV : numeroCompte, idTransaction, typeTransaction, montant, date
                    writer.println(compte.getNumeroCompte() + "," + 
                                   tx.getIdTransaction() + "," + 
                                   tx.getType() + "," + 
                                   tx.getMontant() + "," + 
                                   tx.getDate());
                }
                System.out.println("Succes : Les transactions ont ete sauvegardees dans " + nomFichier);
            }
            
        } catch (IOException e) {
            System.out.println("Erreur : Impossible de sauvegarder les transactions. " + e.getMessage());
        }
    }
}