package affichage.gestionEntrees;

import affichage.AffichageJeu;
import mecaniques.cartes.Animal;
import mecaniques.cartes.Pouvoirs;
import mecaniques.joueurs.Joueur;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Choix {
    private Animal m_choix;

    public Choix(Joueur joueur, Animal carte1, Animal carte2) {
        choisirAnimal(carte1, carte2);

        if (!joueur.getLigne().estVide() || joueur.getNbMain() > 0) {
            gererPierreDeSacrifice(joueur);
        }
    }
    private void choisirAnimal(Animal carte1, Animal carte2) {
        String c1 = "\t1. " + carte1.getNom() + " PV: " + carte1.getPv() + " Att: " + carte1.getAttack();
        c1 += carte1.getGouttesSang() == 0 ? "" : " Gouttes de sang: " + carte1.getGouttesSang();
        c1 += carte1.getOs() == 0 ? "" : " Os: " + carte1.getOs();
        c1 += carte1.getNamePouvoir().isEmpty() ? "" : " Pouvoir : " + carte1.getNamePouvoir();

        String c2 = "\t2. " + carte2.getNom() + " PV: " + carte2.getPv() + " Att: " + carte2.getAttack();
        c2 += carte2.getGouttesSang() == 0 ? "" : " Gouttes de sang: " + carte2.getGouttesSang();
        c2 += carte2.getOs() == 0 ? "" : " Os: " + carte2.getOs();
        c2 += carte2.getNamePouvoir().isEmpty() ? "" : " Pouvoir : " + carte2.getNamePouvoir();

        System.out.println(
                "\n*-------------------------Choix entre deux animaux :------------------------*\n" +
                        c1 + "\n" +
                        c2 +
                "\n*---------------------------------------------------------------------------*\n");

        Scanner scanner = new Scanner(System.in);
        String entree = scanner.nextLine().trim();

        while (!estValide(entree)) {
            System.out.println("Choix invalide. Veuillez entrer 1 ou 2 :");
            entree = scanner.nextLine().trim();
        }
        m_choix = entree.equals("1") ? carte1 : carte2;
    }

    private void gererPierreDeSacrifice(Joueur joueur) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(
                "\n*----------------------------Pierre de sacrifice :----------------------------*\n" +
                        "Sacrifiez une carte afin de récupérer son pouvoir.\n" +
                        "[fin] pour ne sacrifier aucune carte.\n");

        AffichageJeu aff = new AffichageJeu();
        aff.afficherLigneCart(joueur.getLigne(), "B");

        int position;
        boolean choixValide = false;
        while (!choixValide) {
            String entree = scanner.nextLine().trim().toLowerCase();
            if (entree.equals("fin")) {
                return;
            }
            try {
                String positionStr = entree;
                if (entree.startsWith("a") || entree.startsWith("b")) {
                    positionStr = entree.substring(1);
                }

                position = Integer.parseInt(positionStr) - 1;
                if (position >= 0 && position < 4 && joueur.peutSacrifier(position)) {
                    Optional<Pouvoirs> pouvoirOpt = joueur.pierreDeSacrifice(position);
                    if (pouvoirOpt.isPresent()) {
                        Animal cible = choisirAnimalMain(joueur);
                        cible.ajouterPouvoir(pouvoirOpt.get());
                        System.out.println(cible.getNom() + " a maintenant le pouvoir : " + pouvoirOpt.get().getNom()+"\n");
                    }
                    else {
                        System.out.println( "La carte sacrifiée n'avait pas de pouvoir.");
                    }
                    pause();
                    choixValide = true;
                }
                else {
                    System.out.println("Position invalide ou case vide.");
                }
            }
            catch (NumberFormatException e) {
                System.out.println(
                        "Veuillez entrer une position valide.");
            }
        }
    }

    private Animal choisirAnimalMain(Joueur joueur) {
        Scanner scanner = new Scanner(System.in);
        List<Animal> main = joueur.getMain();
        System.out.println("Choisissez un animal de votre main :");
        for (int i = 0; i < main.size(); i++) {
            int num = i+1;
            Animal animal = main.get(i);
            String s = num + ". " + animal.getNom() + " PV: " + animal.getPv() +
                    " Att: " + animal.getAttack() + " Gouttes de sang: " +
                    animal.getGouttesSang() + " Os: " + animal.getOs();
            if (animal.aPouvoir()){
                s += " Pouvoir(s): " + animal.getNamePouvoir();
            }

            System.out.println(s);
        }
        while (true) {
            try {
                int choix = Integer.parseInt(scanner.nextLine()) - 1;
                if (choix >= 0 && choix < main.size()) {
                    return main.get(choix);
                }
            } catch (NumberFormatException ignored) {
            }
            System.out.println("Choix invalide.");
        }
    }

    public boolean estValide(String entree){
        return (entree.equals("1") ||  entree.equals("2"));
    }

    public Animal getChoix(){
        System.out.println();
        return m_choix;
    }

    public static void pause() {
        System.out.println("Appuyez sur Entrée pour continuer...");
        new java.util.Scanner(System.in).nextLine();
    }
}
