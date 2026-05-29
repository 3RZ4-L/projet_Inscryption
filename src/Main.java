import affichage.AffichageBattle;
import affichage.AffichageCommandes;
import affichage.AffichageGrid;
import affichage.AffichagePioche;
import mecaniques.joueurs.Pioche;

public class Main
{
    public static void main(String[] args)
    {
        AffichageGrid aff = new AffichageGrid();
        String[][] cartesVideAdversaire = {
                aff.coordCard("A1"),
                aff.coordCard("A2"),
                aff.coordCard("A3"),
                aff.coordCard("A4")
        };
        String[][] cartesVideCentre = {
                aff.coordCard("O1"),
                aff.coordCard("O2"),
                aff.coordCard("O3"),
                aff.coordCard("O4")
        };
        String[][] cartesVideJoueur = {
                aff.coordCard("B1"),
                aff.coordCard("B2"),
                aff.coordCard("B3"),
                aff.coordCard("B4")
        };
        String[][] arrowsDown = {
                aff.affArrowDown(),
                aff.affArrowDown(),
                aff.affArrowDown(),
                aff.affArrowDown()
        };

        String[][] arrowsUp = {
                aff.affArrowUp(),
                aff.affArrowUp(),
                aff.affArrowUp(),
                aff.affArrowUp()
        };
        aff.afficherLigneCart(cartesVideAdversaire);
        aff.afficherLigneArrow(arrowsDown);
        aff.afficherLigneCart(cartesVideCentre);
        aff.afficherLigneArrow(arrowsUp);
        aff.afficherLigneCart(cartesVideJoueur);

        AffichageCommandes affCom = new AffichageCommandes();
        affCom.affActions();

        AffichagePioche affPioche = new AffichagePioche();
        affPioche.afficherPioche(new Pioche(14));
    }
}
