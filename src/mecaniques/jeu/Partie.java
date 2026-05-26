package mecaniques.jeu;

import mecaniques.battle.Battle;
import mecaniques.joueurs.*;

public class Partie {
    private Battle m_battle;
    private Joueur m_joueur ;
    private Adversaire m_adversaire;
    private int m_score;

    Partie(){
        m_battle = new Battle();
        m_joueur = new Joueur();
        m_adversaire = new Adversaire();
        m_score = 0;
    }

    public void demarrer(){

    }

    public void tourJoueur(){
        boolean jouer = true;
        while (jouer){

        }
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
