package mecaniques.jeu;

import mecaniques.cartes.Animal;
import mecaniques.joueurs.Joueur;

public class Ligne {
    private Joueur m_joueur;
    private Animal[] m_cartes;

    Ligne(Joueur joueur){
        m_joueur = joueur;
        m_cartes = new Animal[4];
    }

    public boolean estCaseVide(int numCarte){
        return (m_cartes[numCarte] == null);
    }

    public boolean placerCarte(int numCarte, Animal carte){
        if(estCaseVide(numCarte)){
            m_cartes[numCarte] = carte;
            return true;
        }
        return false;
    }
    public boolean retirerCarte(int numCarte){
        if(estCaseVide(numCarte)) {
            m_joueur.reprendre(m_cartes[numCarte]);
            m_cartes[numCarte] = null;
            return true;
        }
        return false;
    }

    public boolean supprimerCarte(int numCarte){
        if(estCaseVide(numCarte)){
            m_cartes[numCarte] = null;
            return true;
        }
        return false;
    }

    public boolean sacrifierCarte(int numCarte){
        if(estCaseVide(numCarte)){
            m_joueur.sacrifier();
            m_cartes[numCarte] = null;
            return true;
        }

        return false;
    }
}
