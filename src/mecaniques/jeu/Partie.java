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
        m_partie = 1;
        m_tour = 1;
    }

    public void demarrer() {
        Messages message = new Messages();
        while (m_partie <= 3) {
            while (!partieTermine()) {
                jouerUnTour();
            }
            if (m_score >= 5) {
                message.victoirePartie(m_partie);
            }
            else {
                message.defaitePartie(m_partie);
                message.defaiteJeu();
                return;
            }

            // A la fin de la 2e Partie, le joueur choisit une carte parmis 2 choix
            // et peut utiliser une pierre de sacrifice
            if (m_partie == 2) {
                choisirCarteFinDeuxiemePartie();
            }

            m_partie++;
            if (m_partie <= 3) {
                nouvellePartie();
            }
        }
        message.victoireJeu();
    }

    public void jouerUnTour() {
        Messages message = new Messages();
        // Début du tour : les cartes avec Croissance peuvent évoluer
        // (on teste si c'est le 2e tour dans la class Animal)
        m_joueur.getLigne().croissance();
        m_adversaire.getLigne().croissance();

        // L'adversaire annonce les cartes qu'il jouera au prochain tour
        m_adversaire.showNextCarts(m_joueur.getLigne());

        // Tour du joueur
        tourJoueur();

        message.resultats();
        // Attaque du joueur
        m_score += commencerAttaques(m_joueur, m_adversaire);
        limiterScore();
        if (partieTermine()) {
            message.scoreFinal(m_score);
            return;
        }
        m_adversaire.jouerTour();
        message.ligneDivision();
        m_score -= commencerAttaques(m_adversaire, m_joueur);
        limiterScore();
        message.scoreFinal(m_score);

        m_tour++;
    }

    public void tourJoueur() {
        boolean jouer = true;
        boolean aPiocher = false;
        while (jouer) {
            AffichageJeu aff = new AffichageJeu();
            aff.afficherPlateau(m_joueur, m_adversaire, m_partie, m_tour, m_score);
            aff.Consignes();

            Commande commande = new Commande();
            while (!commande.estValide(m_joueur.getNbMain(), aPiocher)) {
                commande = new Commande();
            }
            switch (commande.getCommande()) {
                case FIN:
                    jouer = false;
                    break;
                case PIOCHER:
                    boolean peut_piocher = m_joueur.piocher();
                    if (!peut_piocher)
                        commande.affPiocheVide();
                    else
                        aPiocher = true;
                    break;
                case PLACER:
                    boolean p = m_joueur.placer(commande.getNumeroCarte(), commande.getPosition());
                    if (!p) {
                        commande.errPlacer();
                    }
                    break;
                case SACRIFIER:
                    List<Integer> lst = commande.getSacrifices();
                    for (int i = 0; i < lst.size(); i++) {
                        m_joueur.sacrifier(lst.get(i));
                    }
                    break;
                default:
                    break;
            }
        }
    }

    private void choisirCarteFinDeuxiemePartie() {
        Animal[] deuxAnimaux = CarteFactory.getDeuxAnimaux();
        Choix choix = new Choix(m_joueur, deuxAnimaux[0], deuxAnimaux[1]);
        m_joueur.ajouterDansPioche(choix.getChoix());
    }

    private void nouvellePartie() {
        m_score = 0;
        m_tour = 1;

        m_joueur.nouvellePartie();
        m_adversaire = new Adversaire();
    }

    public int commencerAttaques(Joueur joueur1, Joueur joueur2) {
        return joueur1.getLigne().attaquer(joueur1, joueur2.getLigne());
    }

    public boolean partieTermine(){
        return m_score >= 5 || m_score <= -5;
    }

    public void limiterScore(){
        if(m_score > 5){
            m_score = 5;
        }

        if(m_score < -5){
            m_score = -5;
        }
    }
    public int getScore() {return m_score;}
    public int getNumPartie(){return m_partie;}
    public int getNumTour(){return m_tour;}
}