package affichage;

import mecaniques.cartes.Carte;
import mecaniques.joueurs.Joueur;
import mecaniques.joueurs.Pioche;

public class AffichagePioche {
    public void afficherMain(Joueur j){
        System.out.println("Votre main : ");

        //j.getMain() doit renvoyer List<Carte>
        /*List<Carte> main = j.getMain();
        if(main.IsEmpty()){
            System.out.println("\tVotre main est vide.");
            return;
        }
        for(int i = 0; i < main.size(); i++{
            afficherInfoCart(i + 1, main.get(i));
        }

        */
    }

    public void afficherInfoCart(int num, Carte carte){
        String res = "\t";
        res += num + " -> " + carte.getNom() + " :\n";
        res += "\t\tPV : " + carte.getPv() + "\n";
        res += carte.getAttack() > 0 ? "\t\tAtt : " + carte.getAttack() + "\n" : "";
        //res += "\t\tGouttes de sang : " + carte.getGoutteSang() + "\n";
        //res += "\t\tOS : " + carte.getOS() + "\n";
        res += carte.estVolant() ? "\t\tVolant" + "\n" : "";

        System.out.println(res);
    }

    public void afficherPioche(Pioche p){
        /*String toRight="\t\t\t\t";
        String spaces = "      "; //6 spaces
        System.out.println(toRight+"Pioche");
        System.out.println(toRight+"*---------------*");
        System.out.println(toRight+"|  " + spaces + spaces + " |");
        System.out.println(toRight+"|  " + spaces + spaces + " |");
        System.out.println(toRight+"|" + spaces + p.getNbCarts.length < 2 ? " " + p.getNbCarts + " |" + spaces : //for 1 num
                                    + p.getNbCarts + " |" + spaces); //for 2 nums
        System.out.println(toRight+"|  " + spaces + spaces + " |");
        System.out.println(toRight+"|  " + spaces + spaces + " |");
        System.out.println(toRight+"*---------------*");*/
    }

    public void afficherInterfacePioche(Pioche p, Joueur j){
        afficherPioche(p);
        System.out.println("---------------------");
        afficherMain(j);
    }
}
