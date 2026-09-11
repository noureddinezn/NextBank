package src.service;

import src.model.Compte;
import src.model.Transaction;
import src.model.TypeTransaction;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class TransactionService {

    public void Virement(Compte compteSource, Compte compteDestination, double montant) {
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
            
            compteSource.getHistoriqueTransactions().add(tx);
            compteDestination.getHistoriqueTransactions().add(tx);
            
            System.out.println("Succes : Virement de " + montant + " DH effectue de " + compteSource.getNumeroCompte() + " vers " + compteDestination.getNumeroCompte());
        } else {
            System.out.println("Erreur : Echec du virement. Solde insuffisant ou montant invalide !");
        }
    }

    public void historiqueTransaction(Compte compte) {
        System.out.println("\n--- HISTORIQUE DES TRANSACTIONS (Compte: " + compte.getNumeroCompte() + ") ---");
        
        if (compte.getHistoriqueTransactions().isEmpty()) {
            System.out.println("Aucune transaction trouvee pour ce compte.");
        } else {
            for (Transaction tx : compte.getHistoriqueTransactions()) {
                System.out.println("- ID: " + tx.getIdTransaction() + 
                                   " | Type: " + tx.getType() + 
                                   " | Montant: " + tx.getMontant() + " DH");
            }
        }
        System.out.println("------------------------------------------------------\n");
    }

    // Methode pour enregistrer l'historique dans un fichier texte simple
    public void enregistreFichier(Compte compte) {
        String nomFichier = "Historique_" + compte.getNumeroCompte() + ".txt";

        try (PrintWriter writer = new PrintWriter(new FileWriter(nomFichier))) {
            writer.println("--- HISTORIQUE DES TRANSACTIONS ---");
            writer.println("Compte : " + compte.getNumeroCompte());
            writer.println("-----------------------------------");

            if (compte.getHistoriqueTransactions().isEmpty()) {
                writer.println("Aucune transaction trouvee.");
            } else {
                for (Transaction tx : compte.getHistoriqueTransactions()) {
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