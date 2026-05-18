package cartes;

public class Carte {
    String m_nom;
    int m_points_de_vie;

    Carte (String nom, int pdv){
        m_nom = nom;
        m_points_de_vie = pdv;
    }

    public int getPv(){
        return m_points_de_vie;
    }

    @Override
    public String toString() {
        return m_nom;
    }
}
