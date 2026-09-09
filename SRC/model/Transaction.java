package src.model;
import java.util.Date;

public class Transaction {

   private int idTransaction;
    private TypeTransaction type; // Utilisation de l'Enum
    private double montant;
    private Date date;
    private String compteSource;
    private String compteDestination;

 public Transaction(int idTransaction, TypeTransaction type, double montant, String compteSource) {
        this.idTransaction = idTransaction;
        this.type = type;
        this.montant = montant;
        this.date = new Date(); // Date du jour automatique
        this.compteSource = compteSource;
        this.compteDestination = null; // par défaut
    }
public Transaction(int idTransaction, TypeTransaction type, double montant, String compteSource, String compteDestination) {
        this.idTransaction = idTransaction;
        this.type = type;
        this.montant = montant;
        this.date = new Date(); 
        this.compteSource = compteSource;
        this.compteDestination = compteDestination;
    }

    public int getIdTransaction() {
        return idTransaction;
    }
    public TypeTransaction getType() {
        return type;
    }
    public double getMontant() {
        return montant;
    }

    public Date getDate() {
        return date;
    }

    public String getCompteSource() {
        return compteSource;
    }

    public String getCompteDestination() {
        return compteDestination;
    }

    
}
