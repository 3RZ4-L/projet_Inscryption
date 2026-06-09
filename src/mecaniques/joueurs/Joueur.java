package mecaniques.joueurs;

import mecaniques.cartes.Animal;
import mecaniques.cartes.Pouvoirs;

import java.util.ArrayList;
import java.util.Optional;

public class Joueur {
    protected Pioche m_pioche;
    protected ArrayList<Animal> m_main = new ArrayList<Animal>();
    protected Ligne m_ligne;
    protected int m_os;
    protected int m_goutteSang;

    public Joueur() {
        m_pioche = new Pioche(15);
        int i = 0;
        while (i < 4) {
            piocher();
            i++;
        }
        m_ligne = new Ligne();
        m_os = 0;
        m_goutteSang = 0;
    }

    public boolean piocher() {
        Optional<Animal> carte = m_pioche.piocher();
        if (carte.isPresent()) {
            m_main.add(carte.get());
            return true;
        }
        return false;
    }

    public boolean placer(int numCarte, int position) {
        if (numCarte - 1 < 0 || numCarte - 1 >= m_main.size()) {
            return false;
        }

        Animal carte = m_main.get(numCarte - 1);

        if (carte.getGouttesSang() > m_goutteSang || carte.getOs() > m_os) {
            return false;
        }

        boolean placementReussi = m_ligne.placerCarte(carte, position);

        if (placementReussi) {
            m_main.remove(numCarte - 1);
            m_goutteSang -= carte.getGouttesSang();
            m_os -= carte.getOs();
        }
        return placementReussi;
    }

    public void sacrifier(int position) {
        if (m_ligne.sacrifier(position)) {
            m_goutteSang++;
            m_os++;
        }
    }

    public void nouvellePartie() {
        m_ligne = new Ligne();
        m_main.clear();
        m_os = 0;
        m_goutteSang = 0;

        for (int i = 0; i < 4; i++) {
            piocher();
        }
    }

    public void ajouterDansPioche(Animal carte) {
        m_pioche.ajouter(carte);
    }

    public void tuerAdversaire() { m_os++; }
    public int getNbMain() { return m_main.size(); }
    public int getNbPioche() { return m_pioche.getNbCartes(); }
    public int getOs() { return m_os; }
    public int getGoutteSang() { return m_goutteSang;}
    public ArrayList<Animal> getMain() { return m_main;}
    public Ligne getLigne() { return m_ligne;}
}