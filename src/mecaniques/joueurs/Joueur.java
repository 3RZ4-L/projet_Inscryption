package mecaniques.joueurs;

import mecaniques.cartes.Animal;
import mecaniques.jeu.Ligne;
import java.util.ArrayList;

public class Joueur{
    private Pioche m_pioche;
    private ArrayList<Animal> m_main = new ArrayList<Animal>();
    private Ligne m_ligne;
    private int m_os;
    private int m_goutteSang;

    public Joueur(){
        m_pioche = new Pioche(15);
        int i = 0;
        while (i < 4){
            piocher();
            i++;
        }
        m_ligne =  new Ligne();
        m_os = 0;
        m_goutteSang = 0;
    }

    public void piocher(){
        m_main.add(m_pioche.piocher());
    }

    public void ajouterDansPioche(Animal carte){
        m_pioche.ajouter(carte);
    }

    public void placer(int position,int numCarte){
        boolean estSacrifier = m_ligne.placerCarte(m_main.remove(position), numCarte);
        if (estSacrifier){
            m_os++;
            m_goutteSang++;
        }
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

    public ArrayList<Animal> getMain(){
        return m_main;
    }

    public Ligne getLigne(){
        return m_ligne;
    }
 }
