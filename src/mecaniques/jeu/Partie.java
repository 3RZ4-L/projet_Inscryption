package mecaniques.jeu;

import affichage.gestionEntrees.Choix;
import affichage.gestionEntrees.Commande;
import mecaniques.cartes.Carte;
import mecaniques.joueurs.*;

public class Partie {

    private Joueur m_joueur;
    private Joueur m_adversaire;
    private int m_score;

    public Partie() {
        m_joueur = new Joueur();
        m_adversaire = new Joueur();
        m_score = 0;
    }

    public int demarrer() {
        for (int i = 0; i < 3; i++) {
            while (m_score < 5 && m_score > -5) {
                if (i == 1) {
                    Choix choix = new Choix(new Pioche(2));
                    m_joueur.ajouterDansPioche(choix.getChoix());
                    m_joueur.croissance();
                }
                tourAdversaire();
                m_score += commencerAttaques(m_joueur.getLigne(), m_adversaire.getLigne());
                tourJoueur();
                m_score -= commencerAttaques(m_adversaire.getLigne(), m_joueur.getLigne());
            }
        }
        return m_score;
    }

    public void tourJoueur() {
        boolean jouer = true;
        boolean piocher = false;
        while (jouer) {
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
                    for (String sacrifice : commande.getSacrifices()) {
                        System.out.println( "Sacrifice : " + sacrifice);
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

    public int getScore() {
        return m_score;
    }

    public int commencerAttaques(Ligne attaquant, Ligne defenseur) {
        int score = 0;
        for (int i = 0; i < 4; i++) {
            Carte att = attaquant.getCartes()[i];
            if (att == null) {
                continue;
            }
            Carte def = defenseur.getCartes()[i];
            if (def != null && !att.estVolant()) {
                score += att.doDamage(def);
                if (def.estMort()) {
                    defenseur.getCartes()[i] = null;
                }
            } else {
                score += att.attaqueDirect();
            }
            attaquant.courrir(i);
        }
        if (score > 5) {
            score = 5;
        }
        return score;
    }
}