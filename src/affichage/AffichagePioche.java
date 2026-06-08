package affichage;

import mecaniques.cartes.Animal;
import mecaniques.cartes.Pouvoirs;
import mecaniques.joueurs.Joueur;
import mecaniques.joueurs.Pioche;

import java.util.ArrayList;
import java.util.List;

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
        if (anim.estVolant()) {
            res += "\t\tVolant\n";
        } else {
            List<Pouvoirs> pouvoirs = anim.getPouvoir().get();
            for (int i = 0; i < pouvoirs.size() && i < 2; i++) {
                res += "\t\tPouvoir " + (i+1) + " : " + pouvoirs.get(i).getNom() + "\n";
            }
        }
        System.out.println(res);
    }

    public void afficherPioche(Pioche p){
        String toRight="\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t";
        int nb = p.getNbCartes();
        String spaces = "      "; //6 spaces
        System.out.println(toRight+"Pioche");
        System.out.println(toRight+"*---------------*");
        System.out.println(toRight+"|  " + spaces + spaces + " |");
        System.out.println(toRight+"|  " + spaces + spaces + " |");
        System.out.println(toRight + "|" + spaces + (nb < 10 ? " " + nb : "" + nb) + spaces +" |");
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
