package mecaniques.battle;

import cartes.Carte;
import mecaniques.affichage.AffichageBattle;

import java.util.Optional;

public class Battle {
    private static final int NB_CARTS = 4;
    private static final int SCORE_MAX = 5;

    private Optional<Carte>[] m_cartesJoueur;
    private Optional<Carte>[] m_cartesAdversaire;
    private int m_score;

    public Battle(){
        m_cartesJoueur = (Optional<Carte>[]) new Optional[NB_CARTS];
        m_cartesAdversaire = (Optional<Carte>[]) new Optional[NB_CARTS];
        for(int i = 0; i < NB_CARTS; i++){
            m_cartesJoueur[i] = Optional.empty();
            m_cartesAdversaire[i] = Optional.empty();
        }
        m_score = 0;
    }

    public Optional<Carte>[] getCartesJoueur(){
        return m_cartesJoueur;
    }

    public Optional<Carte>[] getCartesAdversaire(){
        return m_cartesAdversaire;
    }

    public int getScore(){
        return m_score;
    }

    public boolean validPlacement(int place){
        return (place >= 0 && place < NB_CARTS);
    }

    public boolean placerCart(Optional<Carte>[] personnage, Carte cart, int place){
        if(!validPlacement(place)){
            return false;
        }
        if(!personnage[place].isEmpty()){
            return false;
        }
        personnage[place] = Optional.of(cart);
        return true;
    }

    public boolean validPlacementJoueur(Carte carte, int place) {
        return(placerCart(m_cartesJoueur, carte, place));
    }

    public boolean validPlacementAdversaire(Carte carte, int place){
        return(placerCart(m_cartesAdversaire, carte, place));
    }

    private void doAttack(Optional<Carte>[] attaquers, Optional<Carte>[] defenseurs, boolean joueursAttack, AffichageBattle aff){
        for(int i = 0; i < NB_CARTS; i++){
            if(!attaquers[i].isEmpty() && attaquers[i].get().getAttack() > 0){
                typeAttack(attaquers[i].get(), defenseurs, i, joueursAttack, aff);
            }
        }
    }

    private void typeAttack(Carte cart, Optional<Carte>[] defenseurs, int position, boolean joueursAttack, AffichageBattle aff){
        Optional<Carte> other = defenseurs[position];

        if(other.isEmpty() || cart.estVolant()){
            attackDirect(cart, joueursAttack,aff);
        }
        else{
            attackCart(cart, defenseurs, position, aff);
        }

    }

    private void attackCart(Carte attaquer, Optional<Carte>[] defenseurs, int position, AffichageBattle aff){
        Carte other = defenseurs[position].get();

        attaquer.doDamage(other);
        aff.attackCart(attaquer, other);

        if(other.estMort()){
            aff.died(other);
            defenseurs[position] = Optional.empty();
        }
    }
    private void attackDirect(Carte cart, boolean joueursAttack, AffichageBattle aff){
        int dmg = cart.getAttack();

        if(joueursAttack){
            m_score += dmg;
        }
        else{
            m_score -= dmg;
        }
        aff.attackDirect(cart, dmg);
    }

    public boolean joueurGagne(){
        return getScore() >= SCORE_MAX;
    }

    public boolean adversaireGagne(){
        return getScore() <= -SCORE_MAX;
    }
}


