package src.main;

import src.model.Client;
import src.model.Compte;
import src.model.Courant;
import src.model.Epargne;
import src.model.Gestionnaire;
import src.model.Transaction;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    
    // Bases de donnees en memoire (HashMaps)
    private static Map<String, Client> baseClients = new HashMap<>();
    private static Map<String, Courant> baseCourants = new HashMap<>();
    private static Map<String, Epargne> baseEpargnes = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Gestionnaire admin = new Gestionnaire("Admin", "Super", "admin@nexbank.com", "admin123", "EMP-001");
        
        int choixAccueil = -1;

        while (choixAccueil != 0) {
            System.out.println("\n===================================");
            System.out.println("      BIENVENUE CHEZ NEXBANK       ");
            System.out.println("===================================");
            System.out.println("1. Inscription (Creer un compte)");
            System.out.println("2. Connexion (Espace Client)");
            System.out.println("3. Espace Gestionnaire");
            System.out.println("0. Quitter l'application");
            System.out.print("Choisissez une option : ");
            
            choixAccueil = scanner.nextInt();
            scanner.nextLine(); 

            switch (choixAccueil) {
                case 1:
                    creerProfil(scanner);
                    break;
                case 2:
                    seConnecterClient(scanner);
                    break;
                case 3:
                    menuGestionnaire(scanner, admin);
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

    private static void creerProfil(Scanner scanner) {
        System.out.println("\n--- INSCRIPTION ---");
        System.out.print("Nom : ");
        String nom = scanner.nextLine();
        System.out.print("Prenom : ");
        String prenom = scanner.nextLine();
        System.out.print("Email : ");
        String email = scanner.nextLine();
        System.out.print("Mot de passe : ");
        String password = scanner.nextLine();

        if (baseClients.containsKey(email)) {
            System.out.println("Erreur : Cet email est deja utilise !");
            return;
        }

        String idClient = "C-" + (int)(Math.random() * 10000);
        Client nouveauClient = new Client(nom, prenom, email, password, idClient);

        String ribCourant = "RIB-" + (int)(Math.random() * 100000);
        String ribEpargne = "EP-" + (int)(Math.random() * 100000);
        
        Courant compteC = new Courant(ribCourant, 0.0);
        Epargne compteE = new Epargne(ribEpargne, 0.0);

        nouveauClient.ajouterCompte(compteC);
        nouveauClient.ajouterCompte(compteE);

        baseClients.put(email, nouveauClient);
        baseCourants.put(email, compteC);
        baseEpargnes.put(email, compteE);

        System.out.println("Succes : Profil cree avec succes ! Vous pouvez maintenant vous connecter.");
    }

    private static void seConnecterClient(Scanner scanner) {
        System.out.println("\n--- CONNEXION ---");
        System.out.print("Email : ");
        String email = scanner.nextLine();
        System.out.print("Mot de passe : ");
        String password = scanner.nextLine();

        if (baseClients.containsKey(email)) {
            Client clientConnecte = baseClients.get(email);
            Courant compteC = baseCourants.get(email);
            Epargne compteE = baseEpargnes.get(email);
            
            System.out.println("Connexion reussie ! Bienvenue " + clientConnecte.getNom());
            menuClient(scanner, clientConnecte, compteC, compteE);
        } else {
            System.out.println("Erreur : Aucun compte trouve avec cet email.");
        }
    }

    // --- NOUVELLE METHODE : Choisir le compte cible (Polymorphisme) ---
    private static Compte choisirCompte(Scanner scanner, Courant compteCourant, Epargne compteEpargne) {
        System.out.println("\nSur quel compte voulez-vous effectuer cette operation ?");
        System.out.println("1. Compte Courant (" + compteCourant.getNumeroCompte() + ")");
        System.out.println("2. Compte Epargne (" + compteEpargne.getNumeroCompte() + ")");
        System.out.print("Votre choix : ");
        
        int choix = scanner.nextInt();
        if (choix == 2) {
            return compteEpargne;
        }
        return compteCourant; // Par defaut ou si choix = 1
    }

    private static void menuClient(Scanner scanner, Client client, Courant compteCourant, Epargne compteEpargne) {
        int choix = -1; 
        
        while (choix != 0) {
            System.out.println("\n--- ESPACE CLIENT : " + client.getNom() + " ---");
            System.out.println("1. Consulter le solde");
            System.out.println("2. Effectuer un depot");
            System.out.println("3. Effectuer un retrait");
            System.out.println("4. Afficher l'historique des transactions");
            System.out.println("5. Effectuer un virement interne");
            System.out.println("6. Telecharger l'historique (Fichier TXT)");
            System.out.println("0. Deconnexion");
            System.out.print("Votre choix : ");
            
            choix = scanner.nextInt();
            Compte compteChoisi = null; // Utilisation polymorphique

            switch (choix) {
                case 1:
                    compteChoisi = choisirCompte(scanner, compteCourant, compteEpargne);
                    System.out.println("Votre solde actuel est : " + compteChoisi.getSolde() + " DH");
                    break;
                case 2:
                    compteChoisi = choisirCompte(scanner, compteCourant, compteEpargne);
                    System.out.print("Entrez le montant a deposer : ");
                    double depot = scanner.nextDouble();
                    compteChoisi.depotArgent(depot);
                    break;
                case 3:
                    compteChoisi = choisirCompte(scanner, compteCourant, compteEpargne);
                    System.out.print("Entrez le montant a retirer : ");
                    double retrait = scanner.nextDouble();
                    compteChoisi.retraitArgent(retrait);
                    break;
                case 4:
                    compteChoisi = choisirCompte(scanner, compteCourant, compteEpargne);
                    Transaction.historiqueTransaction(compteChoisi);
                    break;
                case 5:
                    System.out.println("\nSens du virement :");
                    System.out.println("1. Depuis Compte Courant vers Compte Epargne");
                    System.out.println("2. Depuis Compte Epargne vers Compte Courant");
                    System.out.print("Votre choix : ");
                    int sens = scanner.nextInt();
                    
                    System.out.print("Entrez le montant a transferer : ");
                    double montantVirement = scanner.nextDouble();
                    
                    if (sens == 2) {
                        Transaction.Virement(compteEpargne, compteCourant, montantVirement);
                    } else {
                        Transaction.Virement(compteCourant, compteEpargne, montantVirement);
                    }
                    break;
                case 6:
                    compteChoisi = choisirCompte(scanner, compteCourant, compteEpargne);
                    Transaction.enregistreFichier(compteChoisi);
                    break;
                case 0:
                    System.out.println("Deconnexion en cours...");
                    break;
                default:
                    System.out.println("Choix invalide !");
            }
        }
    }

    private static void menuGestionnaire(Scanner scanner, Gestionnaire admin) {
        int choix = -1; 
        
        while (choix != 0) {
            System.out.println("\n--- ESPACE GESTIONNAIRE : " + admin.getNom() + " ---");
            System.out.println("1. Afficher tous les clients inscrits (Simulation)");
            System.out.println("0. Retour a l'accueil");
            System.out.print("Votre choix : ");
            
            choix = scanner.nextInt();
            scanner.nextLine(); 

            switch (choix) {
                case 1:
                    if (baseClients.isEmpty()) {
                        System.out.println("Aucun client inscrit pour le moment.");
                    } else {
                        System.out.println("--- LISTE DES CLIENTS ---");
                        for (String email : baseClients.keySet()) {
                            System.out.println("- " + baseClients.get(email).getNom() + " (" + email + ")");
                        }
                    }
                    break;
                case 0:
                    System.out.println("Retour a l'accueil...");
                    break;
                default:
                    System.out.println("Choix invalide !");
            }
        }
    }
}