package cartes;

public class Carte {
    String m_nom;
    Integer m_points_de_vie;

    Carte (String nom, Integer pdv){
        m_nom = nom;
        m_points_de_vie = pdv;
    }

    public Integer getPv(){
        return m_points_de_vie;
    }

    @Override
    public String toString() {
        return m_nom;
    }
}
