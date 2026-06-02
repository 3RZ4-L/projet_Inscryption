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

    public boolean placer(int numCarte, int position){
        boolean placee = false;
        Animal carte = m_main.remove(numCarte - 1);
        if (carte.getGouttesSang() == 0 && carte.getOs() == 0){
            m_ligne.placerCarte(carte, position);
            placee = true;
        }
        else if (carte.getGouttesSang() <= m_goutteSang){
            m_ligne.placerCarte(carte, position);
            m_goutteSang -= carte.getGouttesSang();
            placee = true;
        }
        else if (carte.getOs() <= m_os){
            m_ligne.placerCarte(carte, position);
            m_os -= carte.getOs();
            placee = true;
        }
        return placee;
    }

    public void sacrifier(int numCarte, int position){
        Animal carte = m_main.remove(numCarte - 1);
        m_ligne.sacrifier(position);
        m_goutteSang ++;
        m_os ++;
    }

    public void croissanceDuLouveteau(){
        m_ligne.croissance();
    }

    public void tuerAdversaire(){
        m_os ++;
    }

    public int getNbMain(){
        return m_main.size();
    }
    public int getNbPioche(){
        return m_pioche.getNbCartes();
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
