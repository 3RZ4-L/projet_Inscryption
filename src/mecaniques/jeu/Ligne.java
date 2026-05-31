package mecaniques.jeu;

import mecaniques.cartes.Animal;
import mecaniques.cartes.Carte;
import static mecaniques.cartes.Pouvoirs.CROISSANCE;
import static mecaniques.cartes.Pouvoirs.NOMBREUSES_VIES;

public class Ligne {
    private Carte[] m_cartes;

    public Ligne(){
        m_cartes = new Carte[4];
    }

    public boolean estCaseVide(int numCarte){
        return (m_cartes[numCarte] == null) || numCarte < 0 || numCarte > 3;
    }

    public boolean placerCarte(Carte carte, int position){
        if(estCaseVide(position)){
            m_cartes[position] = carte;
            return true;
        }
        return false;
    }

    public boolean sacrifier(int position){
        if(!estCaseVide(position) && m_cartes[position].estCreature()){
            if (m_cartes[position].getPouvoir() == NOMBREUSES_VIES) {
                return true;
            }
            m_cartes[position] = null;
            return true;
        }
        return false;
    }

    public void croissance(){
        for (int i = 0; i < 4; i++){
            if (m_cartes[i].getPouvoir() == CROISSANCE){
                m_cartes[i] = new Animal("Loup", 2, 3, 2, 0, false);
            }
        }
    }

    public boolean courrir(int position){
        if (estCaseVide(position+1)){
            placerCarte((Animal)m_cartes[position], position+1);
            return true;
        } else if (estCaseVide(position-1)) {
            placerCarte((Animal)m_cartes[position], position-1);
            return true;
        }
        return false;
    }

    public Carte[] getCartes(){
        return m_cartes;
    }
}
