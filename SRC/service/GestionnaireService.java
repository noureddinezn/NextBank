package src.service;

import src.model.Client;
import src.model.Compte;

public class GestionnaireService {

    // Afficher tous les comptes d'un client specifique
    public void afficherComptesClient(Client client) {
        System.out.println("--- COMPTES DU CLIENT : " + client.getNom() + " " + client.getPrenom() + " ---");
        
        if (client.getComptes().isEmpty()) {
            System.out.println("Aucun compte trouve pour ce client.");
        } else {
            for (String numeroCompte : client.getComptes().keySet()) {
                Compte compte = client.getComptes().get(numeroCompte);
                System.out.println("- Numero: " + numeroCompte + " | Solde: " + compte.getSolde() + " DH");
            }
        }
    }

    // Cloturer (supprimer) un compte pour un client
    public void cloturerCompte(Client client, String numeroCompte) {
        if (client != null && client.getComptes().containsKey(numeroCompte)) {
            client.getComptes().remove(numeroCompte);
            System.out.println("Succes : Le compte " + numeroCompte + " a ete cloture definitivement.");
        } else {
            System.out.println("Erreur : Compte introuvable ou client invalide.");
        }
    }
}