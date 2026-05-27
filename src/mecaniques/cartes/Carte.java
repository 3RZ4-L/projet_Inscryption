package mecaniques.cartes;

import static mecaniques.cartes.Pouvoirs.NULL;

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

    public String getNom() {
        return m_nom;
    }

    public boolean estMort(){
        return(getPv() <= 0);
    }

    public int doDamage(Carte other){
        return 0;
    }

    public int attaqueDirect(){
        return 0;
    }

    public int getAttack(){
        return 0;
    }

    public boolean estVolant(){
        return false;
    }

    public String getNamePouvoir(){
        return "";
    }

    public Pouvoirs getPouvoir(){
        return NULL;
    }
}
