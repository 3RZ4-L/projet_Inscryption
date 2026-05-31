import affichage.AffichageJeu;
import mecaniques.cartes.Animal;
import mecaniques.jeu.Ligne;
import mecaniques.joueurs.Adversaire;
import mecaniques.joueurs.Joueur;

import static mecaniques.cartes.Pouvoirs.NOMBREUSES_VIES;

public class Main
{
    public static void main(String[] args)
    {
        Joueur j = new Joueur();
        Adversaire a = new Adversaire();
        j.placer(2, 2);
        a.placer(1,3);

        AffichageJeu aff = new AffichageJeu();
        aff.afficherPlateau(j,a,2,-4);
    }
}
