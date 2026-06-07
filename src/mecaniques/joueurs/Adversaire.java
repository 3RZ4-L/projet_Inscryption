package mecaniques.joueurs;

import mecaniques.cartes.Animal;
import mecaniques.cartes.Carte;

import java.util.ArrayList;
import java.util.Optional;

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
            Optional<Carte> playerCarte = ligne.getLigne()[i];
            Optional<Carte> advBattleCarte = getLigne().getLigne()[i];
            Optional<Carte> advAnnonceCarte = m_ligneAnnonce.getLigne()[i];

            if (playerCarte.isPresent() && advBattleCarte.isEmpty() && advAnnonceCarte.isEmpty()) {
                Carte playerCart = playerCarte.get();
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
            if (getLigne().getCarte(i).isEmpty() && m_ligneAnnonce.getCarte(i).isEmpty()){
                return i;
            }
        }
        return -1;
    }

    public int cartChois(int position, Ligne ligne){
        Optional<Carte> plCarte = ligne.getCarte(position);
        if(plCarte != null){
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
            Optional<Carte> carteAnnoncee = m_ligneAnnonce.getLigne()[i];
            if (carteAnnoncee.isPresent() && getLigne().estCaseVide(i)) {
                Carte carte = carteAnnoncee.get();
                getLigne().placerCarte(carte, i);
                m_ligneAnnonce.removeCart(i);
            }
        }
    }
}
