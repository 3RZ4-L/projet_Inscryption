package mecaniques.joueurs;

import mecaniques.cartes.Carte;

import java.util.ArrayList;
import java.util.Optional;

public class Joueur{
    private Pioche m_pioche;
    private ArrayList<Carte> m_main = new ArrayList<Carte>();
    private int m_os;
    private int m_goutteSang;

    public Joueur(){
        m_pioche = new Pioche(15);
        int i = 0;
        while (i < 4){
            piocher();
            i++;
        }
        m_os = 0;
        m_goutteSang = 0;
    }

    public void piocher(){
        m_main.add(m_pioche.piocher());
    }

    public Carte placer(int numCarte){
        return m_main.remove(numCarte);
    }

    public void reprendre(Carte carte){
        m_main.add(carte);
    }

    public void sacrifier(){
        m_os++;
        m_goutteSang++;
    }

    public int getNbCartes(){
        return m_main.size();
    }

    public int getOs(){
        return m_os;
    }

    public int getGoutteSang(){
        return m_goutteSang;
    }

    public ArrayList<Carte> getMain(){
        return m_main;
    }
}
