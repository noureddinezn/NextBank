package src.model;
import java.util.HashMap;

import java.util.Map;

public class Client  extends Personne{
   private String idClient;

     private Map<String, Compte> comptes;

  public Client(String nom, String prenom, String email, String motDePasse, String idClient) {
        super(nom, prenom, email, motDePasse);
        this.idClient = idClient; 
         this.comptes = new HashMap<>();
    }

    public String getIdClient() {
        return idClient;
    }

    public void ajouterCompte(Compte compte) {
        if (compte != null) {
            this.comptes.put(compte.getNumeroCompte(), compte);
        }
    }
   public void supprimerCompte(String numeroCompte) {
        this.comptes.remove(numeroCompte);
    }
    public Compte getCompte(String numeroCompte) {
        return this.comptes.get(numeroCompte);
    }
  public Map<String, Compte> getComptes() {
        return comptes;
    }
}
