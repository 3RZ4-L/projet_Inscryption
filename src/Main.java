import affichage.AffichageGrid;

import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        AffichageGrid aff = new AffichageGrid();
        String[][] cartesCoord = {
                aff.coordCard("O1"),
                aff.coordCard("O2"),
                aff.coordCard("O3"),
                aff.coordCard("04"),
                aff.coordCard("O5")
        };

        int whosTurn = 0;
        if(whosTurn == 0) {
            /*String[][] cartesComp = {
                    aff.emptyCard(),
                    aff.animCard("Bob2", "223", "412", true),
                    aff.emptyCard(),
                    aff.animCard("Bobolo", "20", "10", false),
                    aff.animCard("Bob", "2", "4", true),
            };
            aff.afficherLigne(cartesComp);*/
            String[][] arrowsDown = {
                    aff.affArrowDown(),
                    aff.affArrowDown(),
                    aff.affArrowDown(),
                    aff.affArrowDown(),
                    aff.affArrowDown()
            };
            aff.afficherLigne(arrowsDown);
        }
        aff.afficherLigne(cartesCoord);

        /*
        String[][] cartesPlayer = {
                aff.emptyCard(),
                aff.animCard("Bob3", "223", "412", true),
                aff.emptyCard(),
                aff.animCard("Bobolo2", "20", "10", false),
                aff.animCard("Bob4", "2", "4", true),
        };
        aff.afficherLigne(cartesPlayer);
         */
    }
}
