import affichage.gestionEntrees.Choix;
import mecaniques.cartes.Animal;
import mecaniques.cartes.CarteFactory;
import mecaniques.jeu.Partie;

public class Main
{
    public static void main(String[] args)
    {
//        Partie p = new Partie();
//        p.demarrer();
        Animal[] deuxAnimaux = CarteFactory.getDeuxAnimaux();
        Choix choix = new Choix(deuxAnimaux[0], deuxAnimaux[1]);
    }
}
