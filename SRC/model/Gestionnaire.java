package src.model;

public class Gestionnaire extends Personne {
        private String idGestionnaire;

        public Gestionnaire(String nom, String prenom, String email, String motDePasse, String idGestionnaire) {
        // Call the parent class (Personne) constructor
        super(nom, prenom, email, motDePasse);
        this.idGestionnaire = idGestionnaire;
    }
    public String getIdGestionnaire() {
        return idGestionnaire;
    }
    public void setIdGestionnaire(String idGestionnaire) {
        this.idGestionnaire = idGestionnaire;
    }

}
