package mecaniques.jeu;

import affichage.AffichageJeu;
import affichage.gestionEntrees.*;
import mecaniques.cartes.*;
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
                    Animal[] deuxAnimaux = CarteFactory.getDeuxAnimaux();
                    Choix choix = new Choix(deuxAnimaux[0], deuxAnimaux[1]);
                    m_joueur.ajouterDansPioche(choix.getChoix());
                    m_joueur.croissanceDuLouveteau();
                }
                tourAdversaire();
                m_score += commencerAttaques(m_joueur, m_adversaire);
                tourJoueur();
                m_score -= commencerAttaques(m_adversaire, m_joueur);
                m_tour++;
                if (m_score > 5) m_score = 5;
                if (m_score < -5) m_score = -5;
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
                case FIN:
                    jouer = false;
                    break;
                case PIOCHER:
                    m_joueur.piocher();
                    piocher = true;
                    break;
                case PLACER:
                    boolean p = m_joueur.placer(commande.getNumeroCarte(), commande.getPosition());
                    if (!p){
                        aff.message("ErrPlacer");
                    }
                    break;
                case SACRIFIER:
                    List<Integer> lst = commande.getSacrifices();
                    for (int i = 0; i < lst.size(); i++){
                        m_joueur.sacrifier(lst.get(i));
                    }
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
        int score_tour = joueur1.getLigne().attaquer(joueur1, joueur2.getLigne());
        if (score_tour > 5)
            score_tour = 5;
        if (score_tour < 0)
            score_tour = 0;
        return score_tour;
    }
}