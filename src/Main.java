import affichage.AffichageJeu;
import affichage.AffichagePioche;
import mecaniques.jeu.Partie;
import mecaniques.joueurs.Adversaire;
import mecaniques.joueurs.Joueur;
import mecaniques.joueurs.Pioche;

public class Main
{
    public static void main(String[] args)
    {
        Partie p = new Partie();
        p.demarrer();
    }
}
