package mecaniques.battle;

import cartes.Carte;

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
        return (place > 0 && place <= NB_CARTS);
    }

    public boolean placerCart(Optional<Carte>[] personnage, Carte cart, int place){
        if(!validPlacement(place)){
            return false;
        }
        if(!personnage[place].isEmpty()){
            return false;
        }
        personnage[place] = cart;
        return true;
    }

    public boolean validPlacementJoueur(Carte carte, int place) {
        return(placerCart(m_cartesJoueur, carte, place));
    }

    public boolean validPlacementAdversaire(Carte carte, int place){
        return(placerCart(m_cartesAdversaire, carte, place));
    }

    public String attackDirect(Carte cart, boolean joueurAttacks){
        int dmg = cart.getAttack();

        if(joueurAttacks){
            m_score += dmg;
        }
        else{
            m_score -= dmg;
        }

        return cart.getNom() + " a attacké directement et inflige " + dmg + " dégats.\n";
    }

    public String attackCart(Carte cart, Optional<Carte>[] defenseur, int place){
        String res = "";
        Carte other = defenseur[place];

        cart.doDamage(other);
        res += cart.getNom() + " a attacké " + other.getNom() + " et inflige dégats " + cart.getAttack() + "."
        + "\n" + other.getPv() + " - " + cart.getAttack() + " = " + other.getPv();

        if(other.estMort()){
            res += "\n" + other.getNom() + " a été tue par " + cart.getNom() + ".\n";
            defenseur[place] = Optional.empty();
        }
        return res;
    }

    public String typeAttack(Carte cart, Optional<Carte[]> defenseur, int place, boolean joueurAttacks){
        Carte other = defenseur[place];
        if(other.isEmpty() || cart.estVolant()){
           return(attackDirect(cart, joueurAttacks));
        }
        return(attackCart(cart, defenseur, place));

    }

    /*private String doAttack(Carte[] attaquers, Optional<Carte[]> defenseurs, boolean joueurAttacks){
        String res = "";

        for(int i = 0; i < NB_CARTS; i++){
            Carte attacker = attaquers[i];

            if(!attacker.isEmpty() && attacker.getAttack() > 0){
                res += typeAttack(attacker, defenseurs, i, joueurAttacks);
            }

        }
        return res;
    }*/

    private void doAttack();

    private void typeAttack();

    private void attack

    public boolean joueurGagne(){
        return getScore() >= SCORE_MAX;
    }

    public boolean adversaireGagne(){
        return getScore() <= -SCORE_MAX;
    }
}


