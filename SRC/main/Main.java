package src.main;

import src.model.Client;
import src.model.Courant;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- DÉMARRAGE DU TEST NEXBANK ---");

        Client client = new Client("Zouana", "Noureddine", "noureddine@email.com", "pass123", "C-001");
        System.out.println("Client créé : " + client.getPrenom() + " " + client.getNom());

        Courant compteCourant = new Courant("RIB-123456", 0.0);
        System.out.println("Compte courant créé avec solde initial : " + compteCourant.getSolde() + " DH");

        client.ajouterCompte(compteCourant);
        System.out.println("Compte lié au client avec succès.");

        System.out.println("\n--- OPÉRATIONS SUR LE COMPTE ---");
        
        System.out.println("Dépôt de 5000 DH...");
        compteCourant.depotArgent(5000);
        System.out.println("Nouveau solde : " + compteCourant.getSolde() + " DH");

        System.out.println("Retrait de 1500 DH...");
        compteCourant.retraitArgent(1500);
        System.out.println("Nouveau solde : " + compteCourant.getSolde() + " DH");

        System.out.println("Tentative de retrait de 10000 DH...");
        compteCourant.retraitArgent(10000); 

        System.out.println("\n--- FIN DU TEST ---");
    }
}