package mecaniques.jeu;

import mecaniques.cartes.Animal;

public class Ligne {
    private Animal[] m_cartes;

    public Ligne(){
        m_cartes = new Animal[4];
    }

    public boolean estCaseVide(int numCarte){
        return (m_cartes[numCarte] == null);
    }

    /**
     * @param carte la carte placer sur le board
     * @param position
     * @return true si il y a un sacrifice de carte
     */
    public boolean placerCarte(Animal carte, int position){
        if(estCaseVide(position)){
            m_cartes[position] = carte;
            return false;
        }
        m_cartes[position] = carte;
        return true;
    }
}
