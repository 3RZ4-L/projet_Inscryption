package mecaniques.jeu;

import mecaniques.cartes.Animal;
import mecaniques.cartes.Carte;

public class Ligne {
    private Carte[] m_cartes;

    public Ligne(){
        m_cartes = new Animal[4];
    }

    public boolean estCaseVide(int numCarte){
        return (m_cartes[numCarte] == null);
    }

    public boolean placerCarte(Animal carte, int position){
        if(position < 0 || position >= m_cartes.length){
            return false;
        }

        if(!estCaseVide(position)){
            return false;
        }
        m_cartes[position] = carte;
        return true;
    }

    public Carte[] getCartes(){
        return m_cartes;
    }
}
