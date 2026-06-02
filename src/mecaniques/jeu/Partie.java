package mecaniques.jeu;

import affichage.AffichageJeu;
import affichage.gestionEntrees.Choix;
import affichage.gestionEntrees.Commande;
import mecaniques.cartes.Carte;
import mecaniques.joueurs.*;

import java.util.List;

public class Partie {

    private Joueur m_joueur;
    private Adversaire m_adversaire;
    private int m_score;
    private int m_tour;
    private int m_partie;

    public Partie() {
        m_joueur = new Joueur();
        m_adversaire = new Adversaire();
        m_score = 0;
        m_partie = 1;
    }

    public int demarrer() {
        for (int i = 0; i < 3; i++) {
            m_tour = 1;
            while (m_score < 5 && m_score > -5) {
                if (i == 1) {
                    Choix choix = new Choix(new Pioche(2));
                    m_joueur.ajouterDansPioche(choix.getChoix());
                    m_joueur.croissanceDuLouveteau();
                }
                tourAdversaire();
                m_score += commencerAttaques(m_joueur, m_adversaire);
                tourJoueur();
                m_score -= commencerAttaques(m_adversaire, m_joueur);
                m_tour++;
            }
            m_partie ++;
        }
        return m_score;
    }

    public void tourJoueur() {
        boolean jouer = true;
        boolean piocher = false;
        while (jouer) {
            AffichageJeu aff = new AffichageJeu();
            aff.afficherPlateau(m_joueur,m_adversaire,m_partie,m_tour,m_score);

            Commande commande = new Commande();
            while (!commande.estValide(m_joueur.getNbMain(), piocher)) {
                commande = new Commande();
            }
            switch (commande.getCommande()) {
                case "fin":
                    jouer = false;
                    break;
                case "piocher":
                    m_joueur.piocher();
                    piocher = true;
                    break;
                case "placer":
                    m_joueur.placer(commande.getNumeroCarte(), commande.getPosition());
                    break;
                case "sacrifier":
                    List<Integer>
                    break;
                default:
                    System.out.println("Commande inconnue");
                    break;
            }
        }
    }

    public void tourAdversaire() {
        tourJoueur();
    }
    public int getScore() {return m_score;}
    public int getNumParte(){return m_partie;}
    public int getNumTour(){return m_tour;}

    public int commencerAttaques(Joueur joueur1, Joueur joueur2) {
        int score_tour = 0;
        Ligne attaquant = joueur1.getLigne();
        Ligne defenseur = joueur2.getLigne();
        for (int i = 0; i < 4; i++) {
            Carte att = attaquant.getCartes()[i];
            if (att == null){ continue;}
            Carte def = defenseur.getCartes()[i];
            if (def != null && !att.estVolant()) {
                score_tour += att.doDamage(def);
                if (def.estMort()) {
                    if(def.estCreature()){ joueur1.tuerAdversaire();}
                    defenseur.getCartes()[i] = null;
                }
            } else score_tour += att.attaqueDirect();
            attaquant.courrir(i);
        }
        if (score_tour > 5){ score_tour = 5;}
        return score_tour;
    }
}