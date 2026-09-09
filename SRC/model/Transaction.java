package SRC.model;
import java.util.Date;

public class Transaction {

    private int idTransaction;
    private TypeTransaction type; // Utilisation de l'Enum du fichier typeTransaction
    private double montant;
    private Date date;
    private String compteSource;

 public Transaction(int idTransaction, TypeTransaction type, double montant, String compteSource, String compteDestination) {
        this.idTransaction = idTransaction;
        this.type = type;
        this.montant = montant;
        this.date = new Date(); // Date du jour
        this.compteSource = compteSource;
        this.compteDestination = compteDestination;
    }
    public int getIdTransaction() {
        return idTransaction;
    }
    public TypeTransaction getType() {
        return type;
    }

    
}
