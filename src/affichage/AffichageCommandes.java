package affichage;

public class AffichageCommandes {
    public void affActions(){
        System.out.println("*---------------------------------------------------------------------------*\n"+
                           "| Actions possibles:                                                    \t|\n" +
                           "|\t[fin] - Terminer votre tour                                            \t|\n" +
                           "|\t[piocher] - Piocher une carte                                          \t|\n" +
                           "|\t[placer <numero carte> <position>] - Placer une carte sur le plateau   \t|\n" +
                           "|\t[sacrifier <liste de sacrifices>] - Sacrificer une carte sur le plateau\t|\n" +
                           "*---------------------------------------------------------------------------*"
        );
    }

    public void affErrPiocher(){
        System.out.println("Vous ne pouvez piocher qu'une seule fois par tour.");
    }

    public void affErrSacrificer(){
        System.out.println("Il n'y a pas de carte à sacrifier sur cet place, il faut choisir la place pas vide");
    }

}
