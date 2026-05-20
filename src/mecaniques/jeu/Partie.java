package mecaniques.jeu;

import mecaniques.joueurs.*;

public class Partie {
    private Joueur m_joueur ;
    private Adversaire m_adversaire;
    private int m_score;
    private int m_numeroPartie;

    Partie(){
        m_joueur = new Joueur();
        m_adversaire = new Adversaire();
        m_score = 0;
        m_numeroPartie = 1;
    }

    public void demarrer(){

    }

    public void tourJoueur(){

    }

    public void tourAdversaire() {

    }
    public boolean verifierFinPartie() {
        return true;
    }

    public int getScore() {
        return m_score;
    }
}
