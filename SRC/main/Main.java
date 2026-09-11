package src.main;

import src.model.Client;
import src.model.Compte;
import src.model.Courant;
import src.model.Epargne;
import src.model.Gestionnaire;
import src.exception.MontantInvalideException;
import src.exception.SoldeInsuffisantException;
import src.service.ClientService;
import src.service.CompteService;
import src.service.GestionnaireService;
import src.service.TransactionService;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    
    private static Map<String, Client> baseClients = new HashMap<>();
    private static Map<String, Courant> baseCourants = new HashMap<>();
    private static Map<String, Epargne> baseEpargnes = new HashMap<>();

    private static ClientService clientService = new ClientService();
    private static CompteService compteService = new CompteService();
    private static TransactionService transactionService = new TransactionService();
    private static GestionnaireService gestionnaireService = new GestionnaireService();

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

        clientService.ajouterCompte(nouveauClient, compteC);
        clientService.ajouterCompte(nouveauClient, compteE);

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
            System.out.println("Connexion reussie ! Bienvenue " + clientConnecte.getNom());
            // Appel dynamique : on passe uniquement le client !
            menuClient(scanner, clientConnecte);
        } else {
            System.out.println("Erreur : Aucun compte trouve avec cet email.");
        }
    }

    // --- METHODE DYNAMIQUE QUI BOUCLE SUR LES COMPTES DU CLIENT ---
    private static Compte choisirCompte(Scanner scanner, Client client) {
        System.out.println("\n--- VOS COMPTES ---");
        if (client.getComptes().isEmpty()) {
            System.out.println("Vous n'avez aucun compte actif.");
            return null;
        }

        int index = 1;
        Compte[] listeComptes = new Compte[client.getComptes().size()];
        
        // Boucle classique pour afficher les comptes
        for (Compte c : client.getComptes().values()) {
            System.out.println(index + ". " + c.getClass().getSimpleName() + " (" + c.getNumeroCompte() + ") - Solde: " + c.getSolde() + " DH");
            listeComptes[index - 1] = c;
            index++;
        }

        System.out.print("Choisissez un compte (1 - " + (index - 1) + ") : ");
        int choix = scanner.nextInt();

        if (choix >= 1 && choix < index) {
            return listeComptes[choix - 1];
        } else {
            System.out.println("Choix invalide. Selection automatique du premier compte.");
            return listeComptes[0];
        }
    }

    // --- MENU CLIENT DYNAMIQUE ---
    private static void menuClient(Scanner scanner, Client client) {
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
            Compte compteChoisi = null; 

            switch (choix) {
                case 1:
                    compteChoisi = choisirCompte(scanner, client);
                    if (compteChoisi != null) {
                        System.out.println("Votre solde actuel est : " + compteChoisi.getSolde() + " DH");
                    }
                    break;
                case 2:
                    compteChoisi = choisirCompte(scanner, client);
                    if (compteChoisi != null) {
                        System.out.print("Entrez le montant a deposer : ");
                        double depot = scanner.nextDouble();
                        try {
                            compteService.depotArgent(compteChoisi, depot);
                        } catch (MontantInvalideException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    break;
                case 3:
                    compteChoisi = choisirCompte(scanner, client);
                    if (compteChoisi != null) {
                        System.out.print("Entrez le montant a retirer : ");
                        double retrait = scanner.nextDouble();
                        try {
                            compteService.retraitArgent(compteChoisi, retrait);
                        } catch (MontantInvalideException | SoldeInsuffisantException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    break;
                case 4:
                    compteChoisi = choisirCompte(scanner, client);
                    if (compteChoisi != null) {
                        transactionService.historiqueTransaction(compteChoisi);
                    }
                    break;
                case 5:
                    System.out.println("\n--- COMPTE SOURCE ---");
                    Compte compteSource = choisirCompte(scanner, client);
                    
                    if (compteSource != null) {
                        System.out.println("\n--- COMPTE DESTINATION ---");
                        Compte compteDest = choisirCompte(scanner, client);
                        
                        if (compteSource.getNumeroCompte().equals(compteDest.getNumeroCompte())) {
                            System.out.println("Erreur : Vous ne pouvez pas faire un virement vers le meme compte !");
                        } else {
                            System.out.print("Entrez le montant a transferer : ");
                            double montantVirement = scanner.nextDouble();
                            transactionService.Virement(compteSource, compteDest, montantVirement);
                        }
                    }
                    break;
                case 6:
                    compteChoisi = choisirCompte(scanner, client);
                    if (compteChoisi != null) {
                        transactionService.enregistreFichier(compteChoisi);
                    }
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