package mecaniques.jeu;

import gestionEntrees.Choix;
import gestionEntrees.Commande;
import mecaniques.cartes.Carte;
import mecaniques.joueurs.*;

public class Partie {
    private Joueur m_joueur ;
    private Joueur m_adversaire;
    private int m_score;

    Partie(){
        m_joueur = new Joueur();
        m_adversaire = new Joueur();
    }

    public int demarrer(){
        for (int i = 0; i < 3; i++){
            while (m_score < 5 && m_score > -5) {
                if (i == 2) {
                    Choix choix = new Choix(new Pioche(2));
                    m_joueur.ajouterDansPioche(choix.getChoix());
                }
                tourAdversaire();
                m_score += commencerAttaques(m_joueur.getLigne(), m_adversaire.getLigne());
                tourJoueur();
                m_score -= commencerAttaques(m_adversaire.getLigne(), m_joueur.getLigne());
            }
        }
        return m_score;
    }

    public void tourJoueur(){
        boolean jouer = true;
        Commande commande = new Commande();
        int nbPioche = 0;
        while (jouer){
            while(!commande.estValide(m_joueur.getNbCartes(), nbPioche)) {
                commande = new Commande();
            }
            if (commande.getCommande().equals("fin")){
                jouer = false;
            }
            else if (commande.getCommande().equals("piocher")) {
                m_joueur.piocher();
                nbPioche++;
            }
            else{
                m_joueur.placer(commande.getNumeroCarte(), commande.getPosition());
            }
        }
    }

    public void tourAdversaire() {
        tourJoueur();
    }

    public int getScore() {
        return m_score;
    }

    public int commencerAttaques(Ligne attacker, Ligne defenser){
        int score = 0;
        for(int i = 0; i < 4; i++){
            Carte att = attacker.getCartes()[i];
            if(att == null){
                continue;
            }
            Carte def = defenser.getCartes()[i];
            if(def != null && !att.estVolant()){
                score += att.doDamage(def);
                if(def.estMort()){
                    defenser.getCartes()[i] = null;
                }
            }
            else{
                score += att.attaqueDirect();
            }
        }
        if (score > 5) score = 5;
        return score;
    }
}
