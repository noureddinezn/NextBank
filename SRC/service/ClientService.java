package src.service;

import src.model.Client;
import src.model.Compte;

public class ClientService {

    public void ajouterCompte(Client client, Compte compte) {
        if (compte != null) {
            client.getComptes().put(compte.getNumeroCompte(), compte);
            System.out.println("Succes : Compte " + compte.getNumeroCompte() + " a ete ajoute au client.");
        }
    }

    public void supprimerCompte(Client client, String numeroCompte) {
        if (client.getComptes().containsKey(numeroCompte)) {
            client.getComptes().remove(numeroCompte);
            System.out.println("Succes : Le compte " + numeroCompte + " a ete supprime.");
        } else {
            System.out.println("Erreur : Compte introuvable.");
        }
    }

    public Compte getCompte(Client client, String numeroCompte) {
        return client.getComptes().get(numeroCompte);
    }
}