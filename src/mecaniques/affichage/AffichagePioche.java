package mecaniques.affichage;

import cartes.Carte;
import mecaniques.joueurs.Joueur;

import java.util.List;

public class AffichagePioche {
    public void affihcerMain(Joueur j){
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
}
