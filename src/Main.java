import affichage.AffichageCommandes;
import affichage.AffichageJeu;
import affichage.AffichagePioche;
import mecaniques.joueurs.Adversaire;
import mecaniques.joueurs.Joueur;
import mecaniques.joueurs.Pioche;

public class Main
{
    public static void main(String[] args)
    {
        AffichageCommandes affCom = new AffichageCommandes();
        affCom.affActions();

        AffichagePioche affPioche = new AffichagePioche();
        affPioche.afficherPioche(new Pioche(14));

        Joueur j = new Joueur();
        Adversaire a = new Adversaire();
        j.placer(2, 2);
        a.placer(1,3);

        AffichageJeu aff = new AffichageJeu();
        aff.afficherPlateau(j,a,2,-4);
    }
}
