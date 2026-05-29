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

    public void placerCarte(Animal carte, int position){
        if(estCaseVide(position)){
            return;
        }
        m_cartes[position] = carte;
    }

    public Carte[] getCartes(){
        return m_cartes;
    }
}
