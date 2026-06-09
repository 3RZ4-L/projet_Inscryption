package affichage;

import mecaniques.cartes.Carte;
import mecaniques.joueurs.Joueur;

public class Messages {

    public void resultats(){
        System.out.println("\n*------------------------- Resultats du tour : -------------------------*");
    }

    public void attackDirect(Carte cart, int dmg){
        System.out.println(cart.getNom() + " a fait attaque direct et infligé " + dmg + " dégats.");
    }

    public void attackCart(Carte cart, Carte other){
        System.out.println(cart.getNom() + " a attaqué  " + other.getNom() + " et infligé " + cart.getAttack()
        + " dégats.");
        System.out.println("Resultat : \n" + "PV " + other.getNom() + " est : " + other.getPv());
    }

    public void died(Carte cart){
        System.out.println(cart.getNom() + " est mort.");
    }

    public void scoreFinal(int score){
        System.out.println("*--------------------------- Score final : " + score + " ---------------------------*\n");
        pause();
    }

    public void Messagefinal(int score){
        if (score < 0){
            System.out.println("*--------------------------- Vous avez perdu... ---------------------------*\n");
        }
        else{
            System.out.println("*--------------------------- Vous avez gagnez! ---------------------------*\n");
        }
    }

    public static void pause() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void victoirePartie(int numPart){
        System.out.println("Vous avez gagnée la partie :" + numPart +"\n");
        pause();
    }

    public void defaitePartie(int numPart){
        System.out.println("Vous avez perdu dans la partie :" + numPart +"\n");
        pause();
    }

    public void defaiteJeu(){
        System.out.println("L'Adversaire a gagnée\n");
        pause();
    }

    public void victoireJeu(){
        System.out.println("Vous avez gagnée, felicitations !!!\n");
        pause();
    }
}
