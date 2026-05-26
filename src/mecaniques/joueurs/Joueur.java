package mecaniques.joueurs;

import mecaniques.cartes.Animal;

import java.util.ArrayList;
import java.util.Optional;

public class Joueur{
    private Pioche m_pioche;
    private ArrayList<Animal> m_main = new ArrayList<Animal>();
    private int m_os;
    private int m_goutteSang;

    public Joueur(){
        m_pioche = new Pioche(19);
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

    public Animal placer(int numCarte){
        return m_main.remove(numCarte);
    }

    public void reprendre(Animal carte){
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

    public Optional<ArrayList<Animal>> getMain(){
        if (getNbCartes() == 0){
            return Optional.empty();
        }
        return Optional.of(m_main);
    }
}
