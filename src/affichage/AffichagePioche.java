package affichage;

import mecaniques.cartes.Animal;
import mecaniques.joueurs.Joueur;
import mecaniques.joueurs.Pioche;

import java.util.ArrayList;

public class AffichagePioche {
    public void afficherMain(Joueur j){
        System.out.println("Votre main : ");

        ArrayList<Animal> main = j.getMain();
        if (main.isEmpty()) {
            System.out.println("\tVotre main est vide.");
            return;
        }

        for (int i = 0; i < main.size(); i++) {
            afficherInfoCart(i + 1, main.get(i));
        }
    }

    public void afficherInfoCart(int num, Animal anim){
        String res = "\t";
        res += num + " -> " + anim.getNom() + " :\n";
        res += "\t\tPV : " + anim.getPv() + "\n";
        res += anim.getAttack() > 0 ? "\t\tAtt : " + anim.getAttack() + "\n" : "";
        res += "\t\tGouttes de sang : " + anim.getGouttesSang() + "\n";
        res += "\t\tOS : " + anim.getOs() + "\n";
        res += anim.estVolant() ? "\t\tVolant" + "\n" : "";
        res += anim.getPouvoir().isEmpty() ? "\t\tPouvoir : " + anim.getPouvoir() + "\n" : "";

        System.out.println(res);
    }

    public void afficherPioche(Pioche p){
        String toRight="\t\t\t\t";
        int nb = p.getNbCartes();
        String spaces = "      "; //6 spaces
        System.out.println(toRight+"Pioche");
        System.out.println(toRight+"*---------------*");
        System.out.println(toRight+"|  " + spaces + spaces + " |");
        System.out.println(toRight+"|  " + spaces + spaces + " |");
        System.out.println(toRight + "|" + spaces + (nb < 10 ? " " + nb : "" + nb) + " |" + spaces); //for 2 nums
        System.out.println(toRight+"|  " + spaces + spaces + " |");
        System.out.println(toRight+"|  " + spaces + spaces + " |");
        System.out.println(toRight+"*---------------*");
    }

    public void afficherInterfacePioche(Pioche p, Joueur j){
        afficherPioche(p);
        System.out.println("---------------------");
        afficherMain(j);
    }
}
