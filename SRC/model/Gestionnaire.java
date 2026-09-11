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

    public void setIdEmploye(String idEmploye) {
        this.idEmploye = idEmploye;
    }
}