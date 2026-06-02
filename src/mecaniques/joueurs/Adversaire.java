package mecaniques.joueurs;

import mecaniques.cartes.Animal;
import mecaniques.cartes.Carte;
import mecaniques.jeu.Ligne;

import java.util.ArrayList;

public class Adversaire extends Joueur{
    private Ligne m_ligneAnnonce;

    public Adversaire(){
        super();
        m_ligneAnnonce = new Ligne();
    }

    public void showNextCarts(Ligne ligne) {
        peutPiocher();
        int position = choisirPositionAnnonce(ligne);

        if (position != -1 && getMain().size() > 0) {
            int cartChoix = cartChois(position, ligne);
            if (cartChoix != -1) {
                placeCartAnnonce(cartChoix, position);
            }
        }
    }

    public void jouerTour() {
        annonceToBattle();
        getLigne().croissance();
    }

    private void peutPiocher(){
        try{
            piocher();
        }
        catch(Exception err){
        }
    }

    public int choisirPositionAnnonce(Ligne ligne){
        int defPosition = searchEnemyCard(ligne);
        if (defPosition != -1 && m_ligneAnnonce.estCaseVide(defPosition)) {
            return defPosition;
        }
        return emptyPlaceAnnonce();
    }

    public int searchEnemyCard(Ligne ligne){
        int placeCart = -1;
        int cartAttMax = -1;

        for(int i = 0; i < 4; i++){
            Carte playerCart = ligne.getCartes()[i];
            Carte advBattleCart = getLigne().getCartes()[i];
            Carte advAnnonceCart = m_ligneAnnonce.getCartes()[i];

            if (playerCart != null && advBattleCart == null && advAnnonceCart == null) {
                if (playerCart.getAttack() > cartAttMax) {
                    cartAttMax = playerCart.getAttack();
                    placeCart = i;
                }
            }
        }
        return placeCart;
    }

    public int emptyPlaceAnnonce(){
        for(int i = 0; i < 4; i++){
            if(getLigne().getCartes()[i] == null && m_ligneAnnonce.getCartes()[i] == null){
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

    public boolean placeCartAnnonce(int idCart, int position){
        if(idCart < 0 || idCart >= getMain().size()){
            return false;
        }

        Animal cart = getMain().get(idCart);
        if(m_ligneAnnonce.placerCarte(cart, position)){
            getMain().remove(idCart);
            return true;
        }
        return false;
    }

    public void annonceToBattle(){
        for (int i = 0; i < 4; i++) {
            Carte carteAnnoncee = m_ligneAnnonce.getCartes()[i];
            if (carteAnnoncee != null && getLigne().estCaseVide(i)) {
                getLigne().placerCarte(carteAnnoncee, i);
                m_ligneAnnonce.removeCart(i);
            }
        }
    }
}
