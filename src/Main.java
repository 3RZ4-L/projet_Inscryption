import affichage.AffichageGrid;

public class Main
{
    public static void main(String[] args)
    {
        AffichageGrid aff = new AffichageGrid();
        String[][] cartesCoord = {
                aff.coordCard("O1"),
                aff.coordCard("O2"),
                aff.coordCard("O3"),
                aff.coordCard("O4"),
                aff.coordCard("O5")
        };
        String[][] arrowsDown = {
                aff.affArrowDown(),
                aff.affArrowDown(),
                aff.affArrowDown(),
                aff.affArrowDown(),
                aff.affArrowDown()
        };

        String[][] arrowsUp = {
                aff.affArrowUp(),
                aff.affArrowUp(),
                aff.affArrowUp(),
                aff.affArrowUp(),
                aff.affArrowUp()
        };
        aff.afficherLigneArrow(arrowsDown);
        aff.afficherLigneCart(cartesCoord);
        aff.afficherLigneArrow(arrowsUp);

    }
}
