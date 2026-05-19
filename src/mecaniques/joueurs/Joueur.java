package mecaniques.joueurs;

import cartes.Animal;

import java.util.ArrayList;

public class Joueur{
    private Pioche m_pioche;
    private ArrayList<Animal> m_main = new ArrayList<Animal>();
    private int m_os;
    private int m_goutteSang;

    Joueur(){
        m_pioche = new Pioche(19);
        int i = 0;
        while (i < 4){
            piocher();
            i--;
        }
        m_os = 0;
        m_goutteSang = 0;
    }

    public void piocher(){
        m_main.add(m_pioche.piocher());
    }

    public void placer(int numCarte){
        Animal carte = m_main.remove(numCarte);
    }
    public void sacrifier(){
        m_os++;
        m_goutteSang++;
    }
}
