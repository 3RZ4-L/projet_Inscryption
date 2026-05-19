package mecaniques.battle;

import cartes.Carte;

public class Battle {
    private Carte[] m_cartesJoueur;
    private Carte[] m_cartesAdversaire;
    private int m_score;

    public Battle(){
        m_cartesJoueur = new Carte[3];
        m_cartesAdversaire = new Carte[3];
        m_score = 0;
    }

    public Carte[] getCartesJoueur(){
        return m_cartesJoueur;
    }

    public Carte[] getCartesAdversaire(){
        return m_cartesAdversaire;
    }

    public int getScore(){
        return m_score;
    }

    public boolean validPlacementJoueur(Carte carte, int place) {
        if (place >= 3 || place < 0) {
            return false;
        }
        if (getCartesJoueur()[place] != null) {
            return false;
        }
        return true;
    }

    public boolean validPlacementAdversaire(Carte carte, int place){
        if(place >= 3 || place < 0){
            return false;
        }
        if(getCartesAdversaire()[place] != null){
            return false;
        }
        return true;
    }
    
    public boolean joueurGagne(){
        return getScore() >= 5;
    }

    public boolean adversaireGagne(){
        return getScore() <= -5;
    }
}


