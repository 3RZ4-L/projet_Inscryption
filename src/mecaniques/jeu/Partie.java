package mecaniques.jeu;

import affichage.AffichageJeu;
import affichage.Messages;
import affichage.gestionEntrees.*;
import mecaniques.cartes.*;
import mecaniques.joueurs.*;

import java.util.List;

public class Partie {

    private Humain m_joueur;
    private Adversaire m_adversaire;
    private int m_score;
    private int m_tour;
    private int m_partie;

    public Partie() {
        m_joueur = new Humain();
        m_adversaire = new Adversaire();
        m_score = 0;
        m_tour = 1;
        m_partie = 1;
    }

    /*public void demarrer() {
        while (m_score < 5 && m_score > -5) {
            m_tour = 0;
            for (int i = 0; i < 3; i++){
                tourAdversaire();
                if (i == 1) {
                    Animal[] deuxAnimaux = CarteFactory.getDeuxAnimaux();
                    Choix choix = new Choix(m_joueur, deuxAnimaux[0], deuxAnimaux[1]);
                    m_joueur.ajouterDansMain(choix.getChoix());
                    m_joueur.croissanceDuLouveteau();
                }
                tourJoueur();
                Messages message = new Messages();
                message.resultats();
                m_score += commencerAttaques(m_joueur, m_adversaire);
                m_score -= commencerAttaques(m_adversaire, m_joueur);
                message.scoreFinal(m_score);
                m_tour++;
                if (m_score > 5) m_score = 5;
                if (m_score < -5) m_score = -5;
            }
            m_partie ++;
        }
    }*/

    public void demarrer(){
        while(!partieTermine){
            jouerUnTour();
        }
        afficherResultatPartie();
    }

    public void jouerUnTour(){
        // 1. L'adversaire annonce la carte qu'il jouera à son prochain tour
        m_adversaire.showNextCarts(m_joueur.getLigne());

        // 2. Le joueur joue
        tourJoueur();

        // 3. Les cartes du joueur attaquent
        int scoreJouer = commencerAttaques(m_joueur, m_adversaire);
        m_score += scoreJouer;
        limiterScore();

        if(partieTermine){
            return;
        }

        // 4. L'adversaire joue automatiquement les cartes annoncées
        m_adversaire.jouerTour();

        // 5. Les cartes de l'adversaire attaquent
        int scoreAdversaire = commencerAttaques(m_adversaire, m_joueur);
        m_score -= scoreAdversaire;
        limiterScore();

        // 6. Fin du tour
        Messages message = new Messages();
        message.scoreFinal(m_score);

        m_tour++;
    }

    public void tourJoueur() {
        boolean jouer = true;
        boolean aPioche = false;

        while (jouer) {
            AffichageJeu aff = new AffichageJeu();
            aff.afficherPlateau(m_joueur,m_adversaire,m_partie,m_tour,m_score);
            aff.Consignes();

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
                        commande.errPlacer();
                    }
                    break;
                case SACRIFIER:
                    List<Integer> lst = commande.getSacrifices();
                    for (int i = 0; i < lst.size(); i++){
                        m_joueur.sacrifier(lst.get(i));
                    }
                    break;
                default:
                    break;
            }
        }
    }

    public void tourAdversaire() {
        boolean jouer = true;
        boolean piocher = false;
        while (jouer) {
            AffichageJeu aff = new AffichageJeu();
            aff.afficherPlateau(m_joueur,m_adversaire,m_partie,m_tour,m_score);
            aff.Consignes();

            Commande commande = new Commande();
            while (!commande.estValide(m_adversaire.getNbMain(), piocher)) {
                commande = new Commande();
            }
            switch (commande.getCommande()) {
                case FIN:
                    jouer = false;
                    break;
                case PIOCHER:
                    m_adversaire.piocher();
                    piocher = true;
                    break;
                case PLACER:
                    boolean p = m_adversaire.placer(commande.getNumeroCarte(), commande.getPosition());
                    if (!p){
                        commande.errPlacer();
                    }
                    break;
                case SACRIFIER:
                    List<Integer> lst = commande.getSacrifices();
                    for (int i = 0; i < lst.size(); i++){
                        m_adversaire.sacrifier(lst.get(i));
                    }
                    break;
                default:
                    break;
            }
        }
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