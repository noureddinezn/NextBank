package src.model;

import java.util.HashSet;
import java.util.Set;

public abstract class Compte {
    protected String numeroCompte;
    protected double solde;
    protected Set<Transaction> historiqueTransactions;

    public Compte(String numeroCompte, double solde) {
        this.numeroCompte = numeroCompte;
        this.solde = solde;
        this.historiqueTransactions = new HashSet<>();
    }

    public String getNumeroCompte() {
        return numeroCompte;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public Set<Transaction> getHistoriqueTransactions() {
        return this.historiqueTransactions;
    }
}