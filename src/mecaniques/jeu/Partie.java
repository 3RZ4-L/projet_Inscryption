package mecaniques.jeu;

import mecaniques.joueurs.*;

import java.util.Scanner;

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
            Scanner scanner = new Scanner(System.in);
            String action = scanner.nextLine();
            if (action == "fin"){
                jouer = false;
            }
            else if(action == "piocher"){
                m_joueur.piocher();
            }
            else {
                if (action.length() == 11) {
                    String a = action.substring(0, 6);
                    int carte = Integer.parseInt(action.substring(6, 7));
                    int placement = Integer.parseInt(action.substring(7));
                    if (a == "placer"){
                        m_joueur.placer(carte);
                    }
                }
            }
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
