package src.service;

import src.model.Compte;
import src.model.Courant;
import src.model.Epargne;
import src.exception.MontantInvalideException;
import src.exception.SoldeInsuffisantException;

public class CompteService {

    public void depotArgent(Compte compte, double montant) throws MontantInvalideException {
        if (montant <= 0) {
            throw new MontantInvalideException("Operation annulee : Le montant du depot doit etre superieur a zero.");
        }
        compte.setSolde(compte.getSolde() + montant);
        System.out.println("Succes : Depot effectue. Nouveau solde : " + compte.getSolde() + " DH");
    }

    public void retraitArgent(Compte compte, double montant) throws MontantInvalideException, SoldeInsuffisantException {
        if (montant <= 0) {
            throw new MontantInvalideException("Operation annulee : Le montant du retrait doit etre superieur a zero.");
        }
        
       
        if (compte instanceof Courant) {
            double decouvertAutorise = 1000.0;
            if ((compte.getSolde() + decouvertAutorise) < montant) {
                throw new SoldeInsuffisantException("Operation annulee : Solde insuffisant (Decouvert max de 1000 DH atteint).");
            }
        } else if (compte instanceof Epargne) {
            if (compte.getSolde() < montant) {
                throw new SoldeInsuffisantException("Operation annulee : Solde insuffisant. Le compte Epargne n'autorise pas de decouvert.");
            }
        }

        compte.setSolde(compte.getSolde() - montant);
        System.out.println("Succes : Retrait effectue sur votre " + compte.getClass().getSimpleName() + ". Nouveau solde : " + compte.getSolde() + " DH");
    }
}