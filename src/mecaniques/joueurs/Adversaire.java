package mecaniques.joueurs;

import mecaniques.cartes.Animal;
import mecaniques.cartes.Carte;
import mecaniques.jeu.Ligne;

import java.util.ArrayList;

public class Adversaire extends Joueur{
    public Adversaire(){
        super();
    }

    public void jouerTour(Ligne ligne){
        peutPiocher();

        int position = choisirPosition(ligne);
        //while pour placer plusieur carts, if pour mieux prevoir l'action d'Adversaire
        if(position != -1 && getMain().size() > 0){
            int cartChoix = cartChois(position, ligne);

            if(cartChoix == -1){
                return;
            }

            boolean placeCart = placeCart(cartChoix, position);
            if(!placeCart){
                return;
            }

            //position = choisirPosition(ligne);
        }
    }

    private void peutPiocher(){
        try{
            piocher();
        }
        catch(Exception err){
            System.out.println("Pas possible piocher");
        }
    }

    public int choisirPosition(Ligne ligne){
        int defPosition = searchEnemyCard(ligne);
        if(defPosition != -1){
            return defPosition;
        }
        return emptyPlace();
    }

    public int searchEnemyCard(Ligne ligne){
        int placeCart = -1;
        int cartAttMax = -1;

        for(int i = 0; i < 4; i++){
            Carte playerCart = ligne.getCartes()[i];
            Carte advCart = getLigne().getCartes()[i];

            if(playerCart != null && advCart == null){
                if(playerCart.getAttack() > cartAttMax){
                    cartAttMax = playerCart.getAttack();
                    placeCart = i;
                }
            }
        }
        return placeCart;
    }

    public int emptyPlace(){
        for(int i = 0; i < 4; i++){
            if(getLigne().getCartes()[i] == null){
                return i;
            }
        }
        return -1;
    }

    public int cartChois(int position, Ligne ligne){
        Carte plCart = ligne.getCartes()[position];
        if(plCart != null){
            return placeDefense();
        }
        return placeAttacker();
    }

    public int placeDefense(){
        ArrayList<Animal> main = getMain();

        int placement = -1;
        int maxHp = -1;
        int maxDmg = -1;

        for(int i = 0; i < main.size(); i++){
            Animal cart = main.get(i);

            if(cart.getPv() > maxHp){
                maxHp = cart.getPv();
                maxDmg = cart.getAttack();
                placement = i;
            }
            else if(cart.getPv() == maxHp && cart.getAttack() > maxDmg){
                maxDmg = cart.getAttack();
                placement = i;
            }
        }
        return placement;
    }

    public int placeAttacker(){
        ArrayList<Animal> main = getMain();

        int placement = -1;
        int maxHp = -1;
        int maxDmg = -1;

        for(int i = 0; i < main.size(); i++){
            Animal cart = main.get(i);

            if(cart.getAttack() > maxDmg){
                maxDmg = cart.getAttack();
                maxHp = cart.getPv();
                placement = i;
            }
            else if(cart.getAttack() == maxDmg && cart.getPv() > maxHp){
                maxHp = cart.getPv();
                placement = i;
            }
        }
        return placement;
    }

    public boolean placeCart(int idCart, int position){
        if(idCart < 0 || idCart >= getMain().size()){
            return false;
        }

        Animal cart = getMain().get(idCart);
        if(getLigne().placerCarte(cart, position)){
            getMain().remove(idCart);
            return true;
        }
        return false;
    }
}
