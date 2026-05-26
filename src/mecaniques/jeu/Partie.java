package mecaniques.jeu;

import commande.Commande;
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
        Commande commande = new Commande();
        int nbPioche = 0;
        while (jouer){
            while(!commande.estValide(m_joueur.getNbCartes())) {
                commande = new Commande();
            }
            if (commande.getCommande() == "fin"){
                jouer = false;
            }
            else if (commande.getCommande() == "piocher") {
                if (nbPioche == 1){
                    System.out.println("Vous ne pouvez plus piocher");
                }
                else {
                    m_joueur.piocher();
                    nbPioche++;
                }
            }
            else{
                m_joueur.placer(commande.getNumeroCarte(), commande.getPosition());
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
