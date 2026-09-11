package src.model;

import java.util.HashMap;
import java.util.Map;

public class Client extends Personne {
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

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    public Map<String, Compte> getComptes() {
        return comptes;
    }

    public void setComptes(Map<String, Compte> comptes) {
        this.comptes = comptes;
    }
}