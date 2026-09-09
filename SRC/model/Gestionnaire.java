package src.model;

public class Gestionnaire extends Personne {
    
    private String idEmploye;

    public Gestionnaire(String nom, String prenom, String email, String motDePasse, String idEmploye) {
        super(nom, prenom, email, motDePasse);
        this.idEmploye = idEmploye;
    }

    public String getIdEmploye() {
        return idEmploye;
    }

    // Afficher tous les comptes d'un client specifique
    public void afficherComptesClient(Client client) {
        System.out.println("--- COMPTES DU CLIENT : " + client.getNom() + " " + client.getPrenom() + " ---");
        
        if (client.getComptes().isEmpty()) {
            System.out.println("Aucun compte trouve pour ce client.");
        } else {
            for (String numeroCompte : client.getComptes().keySet()) {
                Compte compte = client.getCompte(numeroCompte);
                System.out.println("- Numero: " + numeroCompte + " | Solde: " + compte.getSolde() + " DH");
            }
        }
    }

    // Cloturer (supprimer) un compte pour un client
    public void cloturerCompte(Client client, String numeroCompte) {
        if (client != null && client.getCompte(numeroCompte) != null) {
            client.supprimerCompte(numeroCompte);
            System.out.println("Succes : Le compte " + numeroCompte + " a ete cloture definitivement.");
        } else {
            System.out.println("Erreur : Compte introuvable ou client invalide.");
        }
    }
}