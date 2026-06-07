package affichage.gestionEntrees;

import affichage.AffichageJeu;
import mecaniques.cartes.Animal;
import mecaniques.cartes.Pouvoirs;
import mecaniques.joueurs.Joueur;

import java.util.Optional;
import java.util.Scanner;

public class Choix {
    private Animal m_choix;
    private int sacrifice;

    public Choix(Joueur joueur, Animal carte1, Animal carte2){

        // __________________CHOIX 2 CARTES __________________
        String c1 = "\t1. " + carte1.getNom() + " PV: " + carte1.getPv() +
                " Att: " + carte1.getAttack();
        c1 += carte1.getGouttesSang() == 0 ? "" : " Gouttes de sang: " + carte1.getGouttesSang();
        c1 += carte1.getOs() == 0 ? "" : " Os: " + carte1.getOs();
        c1 += carte1.getNamePouvoir().isEmpty() ? "" : " Pouvoir : " + carte1.getNamePouvoir();

        String c2 = "\t2. " + carte2.getNom() + " PV: " + carte2.getPv() +
                " Att: " + carte2.getAttack();
        c2 += carte2.getGouttesSang() == 0 ? "" : " Gouttes de sang: " + carte2.getGouttesSang();
        c2 += carte2.getOs() == 0 ? "" : " Os: " + carte2.getOs();
        c2 += carte2.getNamePouvoir().isEmpty() ? "" : " Pouvoir : " + carte2.getNamePouvoir();

        System.out.println(
                "\n*-------------------------Choix entre deux animaux :------------------------*\n" +
                        c1+ "\n" +
                        c2 +
                "\n*---------------------------------------------------------------------------*\n");

        Scanner scanner = new Scanner(System.in);
        String entree = scanner.nextLine().trim().toLowerCase();
        while (!estValide(entree)) {
            System.out.println("Choix invalide. Veuillez entrer 1 ou 2 :");
            entree = scanner.nextLine().trim().toLowerCase();
        }
        if (entree.equals("1")){
            m_choix = carte1;
        }
        else m_choix = carte2;

        if (joueur.getLigne().estVide()) return;
        // __________________PIERRE DE SACRIFICE__________________

        System.out.println(
                "\n*----------------------------Pierre de sacrifice :----------------------------*\n" +
                        "Sacrifiez une carte afin de récupèrer son pouvoir (si la carte en possède).\n"
        );

        AffichageJeu aff = new AffichageJeu();
        aff.afficherLigneCart(joueur.getLigne(), "B");

        int position = -1;
        boolean choixValide = false;

        while (!choixValide) {
            entree = scanner.nextLine().trim().toLowerCase();
            String positionStr = entree;
            if (entree.startsWith("a") || entree.startsWith("b")) {
                positionStr = entree.substring(1);
            }
            position = Integer.parseInt(positionStr) - 1;
            if (position >= 0 && position < 4) {
                if (joueur.peutSacrifier(position)) {
                    choixValide = true;
                } else {
                    aff.afficherLigneCart(joueur.getLigne(), "B");
                    System.out.println("Position invalide ou case vide. Veuillez choisir une position contenant une créature (1-4) :");
                }
            } else {
                aff.afficherLigneCart(joueur.getLigne(), "B");
                System.out.println("Position invalide. Veuillez entrer un nombre entre 1 et 4 :");
            }
        }

        Optional<Pouvoirs> pouvoirOpt = joueur.PierreDeSacrifice(position);
        if (pouvoirOpt.isPresent()) {
            m_choix.ajouterPouvoir(pouvoirOpt.get());
            System.out.println(
                    "\n*----------------------------Pierre de sacrifice :----------------------------*\n" +
                            m_choix.getNom() + " a maintenant le pouvoir : " + pouvoirOpt.get().getNom() + "!\n"
            );
        }
        else {
            System.out.println(
                    "\n*----------------------------Pierre de sacrifice :----------------------------*\n" +
                            "La carte sacrifiée n'avait pas de pouvoir spécial.\n"
            );
        }
    }

    public boolean estValide(String entree){
        return (entree.equals("1") ||  entree.equals("2"));
    }

    public boolean estValide2(String num) {
        if (num.length() > 2) return false;
            if (num.startsWith("a") || num.startsWith("b")) {
                num = num.substring(1);
            }
        return (num.equals("1") || num.equals("2") || num.equals("3") || num.equals("4"));
    }

    public Animal getChoix(){
        System.out.println();
        return m_choix;
    }
}
