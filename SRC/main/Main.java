package src.main;

import src.model.Client;
import src.model.Courant;
import src.model.Gestionnaire;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // 1. Initialisation des donnees 
        Gestionnaire admin = new Gestionnaire("Admin", "Super", "admin@nexbank.com", "admin123", "EMP-001");
        Client client = new Client("Zouana", "Noureddine", "noureddine@email.com", "pass123", "C-001");
        Courant compteCourant = new Courant("RIB-123456", 5000.0);
        client.ajouterCompte(compteCourant);

        int choixRole = -1; // Initialisation pour entrer dans la boucle while

        // 2. Boucle principale avec while
        while (choixRole != 0) {
            System.out.println("\n===================================");
            System.out.println("      BIENVENUE CHEZ NEXBANK       ");
            System.out.println("===================================");
            System.out.println("1. Espace Client");
            System.out.println("2. Espace Gestionnaire");
            System.out.println("0. Quitter l'application");
            System.out.print("Choisissez votre profil : ");
            
            choixRole = scanner.nextInt();

            switch (choixRole) {
                case 1:
                    menuClient(scanner, client, compteCourant);
                    break;
                case 2:
                    menuGestionnaire(scanner, admin, client);
                    break;
                case 0:
                    System.out.println("Fermeture du systeme NexBank. A bientot !");
                    break;
                default:
                    System.out.println("Choix invalide, veuillez reessayer !");
            }
        }

        scanner.close(); 
    }

    // --- SOUS-MENU : ESPACE CLIENT ---
    private static void menuClient(Scanner scanner, Client client, Courant compte) {
        int choix = -1; // Initialisation
        
        while (choix != 0) {
            System.out.println("\n--- ESPACE CLIENT : " + client.getNom() + " ---");
            System.out.println("1. Consulter le solde");
            System.out.println("2. Effectuer un depot");
            System.out.println("3. Effectuer un retrait");
            System.out.println("4. Afficher l'historique des transactions");
            System.out.println("0. Retour au menu principal");
            System.out.print("Votre choix : ");
            
            choix = scanner.nextInt();

            switch (choix) {
                case 1:
                    System.out.println("Votre solde actuel est : " + compte.getSolde() + " DH");
                    break;
                case 2:
                    System.out.print("Entrez le montant a deposer : ");
                    double depot = scanner.nextDouble();
                    compte.depotArgent(depot);
                    break;
                case 3:
                    System.out.print("Entrez le montant a retirer : ");
                    double retrait = scanner.nextDouble();
                    compte.retraitArgent(retrait);
                    break;
                case 4:
                    compte.afficherHistorique();
                    break;
                case 0:
                    System.out.println("Retour au menu principal...");
                    break;
                default:
                    System.out.println("Choix invalide !");
            }
        }
    }

    // --- SOUS-MENU : ESPACE GESTIONNAIRE ---
    private static void menuGestionnaire(Scanner scanner, Gestionnaire admin, Client client) {
        int choix = -1; // Initialisation
        
        while (choix != 0) {
            System.out.println("\n--- ESPACE GESTIONNAIRE : " + admin.getNom() + " ---");
            System.out.println("1. Afficher les comptes du client");
            System.out.println("2. Cloturer un compte");
            System.out.println("0. Retour au menu principal");
            System.out.print("Votre choix : ");
            
            choix = scanner.nextInt();
            scanner.nextLine(); // Vider le buffer

            switch (choix) {
                case 1:
                    admin.afficherComptesClient(client);
                    break;
                case 2:
                    System.out.print("Entrez le numero du compte a cloturer (ex: RIB-123456) : ");
                    String numCompte = scanner.nextLine();
                    admin.cloturerCompte(client, numCompte);
                    break;
                case 0:
                    System.out.println("Retour au menu principal...");
                    break;
                default:
                    System.out.println("Choix invalide !");
            }
        }
    }
}