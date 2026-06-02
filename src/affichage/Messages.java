package affichage;

import mecaniques.cartes.Carte;

public class Messages {
    public void attackDirect(Carte cart, int dmg){
        System.out.println(cart.getNom() + " a fait attaque direct et infligé " + dmg + " dégats.\n");
    }

    public void attackCart(Carte cart, Carte other){
        System.out.println(cart.getNom() + " a attaqué  " + other.getNom() + " et infligé " + cart.getAttack()
        + " dégats.");
        System.out.println("Resultat : \n" + "PV " + other.getNom() + " est : " + other.getPv());
    }

    public void died(Carte cart){
        System.out.println(cart.getNom() + " est mort.");
    }

    public void score(int score){
        System.out.println("Score : " + score);
    }

    public static void pause(){
        try{
            Thread.sleep(2000);
        }
        catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }

    public void errPlacer(){
        System.out.println("\n------------------------------------------------------------------------");
        System.out.println("Vous ne pouvez pas placer cette carte, os ou goutte de sang inssufisante");
        System.out.println("------------------------------------------------------------------------");
        pause();
    }
}
