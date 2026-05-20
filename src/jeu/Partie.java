package jeu;

import mecaniques.joueurs.*;

public class Partie {
    private Plateau m_plateau;
    private Joueur m_joueur ;
    private Adversaire m_adversaire;
    private int m_score;
    private int m_numero_partie;

    Partie(){
        m_joueur = new Joueur();
        m_adversaire = new Adversaire();
        m_plateau = new Plateau(m_joueur, m_adversaire);
        
        m_score = 0;
        m_numero_partie = 1;
    }

    public void demarrer(){

    }

    public void tourJoueur(){

    }

    public void tourAdversaire() {

    }

    public void phaseAttaque() {

    }

    public boolean verifierFinPartie() {
        return true;
    }

    public int getScore() {
        return 0;
    }

    public Plateau getPlateau() {
        return m_plateau;
    }
}
