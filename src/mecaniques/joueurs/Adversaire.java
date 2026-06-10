package mecaniques.joueurs;

import mecaniques.cartes.Animal;
import mecaniques.cartes.Carte;

import java.util.ArrayList;
import java.util.Optional;

public class Adversaire extends Joueur{
    private Ligne m_ligneAnnonce;
    private static final int CARTES_PAR_TOUR = 2;
    private ArrayList<Animal> m_cartesASacrifier;

    public Adversaire(){
        super();
        m_ligneAnnonce = new Ligne();
        m_cartesASacrifier = new ArrayList<>();
    }

    public void showNextCarts(Ligne ligne) {
        peutPiocher();
        m_cartesASacrifier.clear();

        int cartesPlacees = 0;
        int tentativesMax = 8;

        while (cartesPlacees < CARTES_PAR_TOUR && tentativesMax > 0 && getMain().size() > 0) {
            int position = choisirPositionAnnonce(ligne);

            if (position != -1) {
                int cartChoix = cartChois(position, ligne);
                if (cartChoix != -1) {
                    if (placeCartAnnonceAvecSacrificeDiffere(cartChoix, position)) {
                        cartesPlacees++;
                    }
                }
            }
            tentativesMax--;
        }
        appliquerSacrificesDiffers();
    }

    public void jouerTour() {
        annonceToBattle();
    }

    public void peutPiocher(){
        piocher();
    }

    public boolean enoughRessources(Animal anim){
        return getRessourcesAvecSacrifices() >= anim.getGouttesSang() && anim.getOs() <= m_os;
    }

    private int getRessourcesAvecSacrifices() {
        int ressourcesTotales = m_goutteSang;

        for (Animal cart : m_cartesASacrifier) {
            ressourcesTotales++;
        }

        for (int i = 0; i < 4; i++) {
            if (getLigne().estSacrifiable(i)) {
                ressourcesTotales++;
            }
        }

        return ressourcesTotales;
    }

    public void spendRessources(Animal anim){
        if (anim.getGouttesSang() <= m_goutteSang) {
            m_goutteSang -= anim.getGouttesSang();
        }
        else {
            int manque = anim.getGouttesSang() - m_goutteSang;
            m_goutteSang = 0;
            for (int i = 0; i < manque && i < m_cartesASacrifier.size(); i++) {
            }
        }
        m_os -= anim.getOs();
    }

    public int choisirPositionAnnonce(Ligne ligne){
        int defPosition = searchEnemyCard(ligne);
        if (defPosition != -1 && m_ligneAnnonce.estCaseVide(defPosition)) {
            return defPosition;
        }
        return emptyPlaceAnnonce();
    }

    public int emptyPlaceAnnonce() {
        for (int i = 0; i < 4; i++) {
            if (getLigne().getCarte(i).isEmpty() && m_ligneAnnonce.getCarte(i).isEmpty()) {
                return i;
            }
        }
        return -1;
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

    public int cartChois(int position, Ligne ligne){
        Optional<Carte> plCarte = ligne.getCarte(position);
        if(plCarte.isPresent()){
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

            if (!peutJouerAvecSacrificeDiffere(cart)) {
                continue;
            }

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

            if (!peutJouerAvecSacrificeDiffere(cart)) {
                continue;
            }

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

    public boolean placeCartAnnonceAvecSacrificeDiffere(int idCart, int position) {
        if (idCart < 0 || idCart >= getMain().size()) {
            return false;
        }
        Animal cart = getMain().get(idCart);

        if (!peutJouerAvecSacrificeDiffere(cart)) {
            return false;
        }
        if (!planifierSacrifices(cart)) {
            return false;
        }
        if (m_ligneAnnonce.placerCarte(cart, position)) {
            getMain().remove(idCart);
            return true;
        }
        return false;
    }

    private boolean planifierSacrifices(Animal animal) {
        int sangRequis = animal.getGouttesSang();
        int sangDisponible = m_goutteSang;

        int sacrificesNecessaires = Math.max(0, sangRequis - sangDisponible);
        ArrayList<Integer> positionsSacrifice = new ArrayList<>();

        for (int i = 0; i < 4 && sacrificesNecessaires > 0; i++) {
            Optional<Carte> carte = getLigne().getCarte(i);
            if (carte.isPresent() && getLigne().estSacrifiable(i)) {
                Carte c = carte.get();
                if (c.getPv() <= 1 && c.getAttack() == 0) {
                    positionsSacrifice.add(i);
                    sacrificesNecessaires--;
                }
            }
        }
        if (sacrificesNecessaires > 0) {
            return false;
        }
        for (int pos : positionsSacrifice) {
            Optional<Carte> carte = getLigne().getCarte(pos);
            if (carte.isPresent() && carte.get() instanceof Animal) {
                m_cartesASacrifier.add((Animal) carte.get());
            }
        }
        m_goutteSang = Math.max(0, m_goutteSang - animal.getGouttesSang());
        m_os -= animal.getOs();
        return true;
    }

    private void appliquerSacrificesDiffers() {
        for (Animal carteASacrifier : m_cartesASacrifier) {
            for (int i = 0; i < 4; i++) {
                Optional<Carte> carte = getLigne().getCarte(i);
                if (carte.isPresent() && carte.get().equals(carteASacrifier)) {
                    sacrifier(i);
                    break;
                }
            }
        }
        m_cartesASacrifier.clear();
        m_goutteSang = Math.max(0, m_goutteSang);
    }

    public boolean placeCartAnnonce(int idCart, int position) {
        if (idCart < 0 || idCart >= getMain().size()) {
            return false;
        }
        Animal cart = getMain().get(idCart);
        if (!enoughRessources(cart)) {
            boolean sacrificeReussi = sacrifierPour(cart);

            if (!sacrificeReussi) {
                return false;
            }
        }
        if (m_ligneAnnonce.placerCarte(cart, position)) {
            spendRessources(cart);
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

    private boolean sacrifierPour(Animal animal) {
        while (m_goutteSang < animal.getGouttesSang()) {
            int position = trouverCarteSacrifiable();

            if (position == -1) {
                return false;
            }
            sacrifier(position);
        }
        return m_os >= animal.getOs();
    }

    private int trouverCarteSacrifiable() {
        for (int i = 0; i < 4; i++) {
            Optional<Carte> carte = getLigne().getCarte(i);
            if (carte.isPresent() && getLigne().estSacrifiable(i)) {
                Carte c = carte.get();
                if (c.getPv() <= 1 && c.getAttack() == 0) {
                    return i;
                }
            }
        }
        return -1;
    }

    private boolean peutJouerAvecSacrifice(Animal animal) {
        if (animal.getOs() > m_os)
            return false;
        if (animal.getGouttesSang() <= m_goutteSang)
            return true;
        int sangPossible = m_goutteSang;

        for (int i = 0; i < 4; i++) {
            if (getLigne().estSacrifiable(i)) {
                sangPossible++;
            }
        }
        return sangPossible >= animal.getGouttesSang();
    }

    private boolean peutJouerAvecSacrificeDiffere(Animal animal) {
        if (animal.getOs() > m_os)
            return false;
        return getRessourcesAvecSacrifices() >= animal.getGouttesSang();
    }

    public Ligne getLigneAnnonce(){
        return m_ligneAnnonce;
    }
}