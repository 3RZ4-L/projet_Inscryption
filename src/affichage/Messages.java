package affichage;

import mecaniques.cartes.Carte;

public class Messages {

    public void resultats(){
        System.out.println("\n*------------------------- Resultats du tour : -------------------------*");
    }

    public void ligneDivision(){
        System.out.println("\n*-----------------------------------------------------------------------*\n");
    }

    public void attaqueDirect(Carte carte){
        System.out.println(carte.getNom() + " a fait attaque direct et infligé " + carte.getAttack() + " dégats.");
    }

    public void attaquerCarte(Carte cart, Carte other){
        System.out.println(cart.getNom() + " a attaqué  " + other.getNom() + " et a infligé " + cart.getAttack() + " dégats.");
    }

    public void died(Carte cart){
        System.out.println(cart.getNom() + " est mort.");
    }

    public void scoreFinal(int score){
        System.out.println("*--------------------------- Score final : " + score + " ---------------------------*\n");
        pause();
    }

    public static void pause() {
        System.out.println("Appuyez sur Entrée pour continuer...");
        new java.util.Scanner(System.in).nextLine();
    }

    public void victoirePartie(int numPart){
        System.out.println("\n*-----------------------------*");
        System.out.println(" Vous avez gagnée la partie " + numPart);
        System.out.println("*-----------------------------*\n");
        pause();
    }

    public void defaitePartie(int numPart){
        System.out.println("\n*-------------------------------*");
        System.out.println(" Vous avez perdu dans la partie " + numPart);
        System.out.println("*-------------------------------*\n");
        pause();
    }

    public void defaiteJeu(){
        System.out.println("\n*---------------------------*");
        System.out.println("\tL'Adversaire a gagnée");
        System.out.println("*---------------------------*\n");
    }

    public void victoireJeu(){
        System.out.println("\n*-----------------------------------------*");
        System.out.println("\tVous avez gagnée, felicitations !!!");
        System.out.println("*-----------------------------------------*\n");
    }
}
