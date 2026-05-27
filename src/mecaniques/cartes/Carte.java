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

    public void takeDamage(int dmg){
        m_points_de_vie -= dmg;
    }



    public void doDamage(Carte other){
        // Vide car par défaut une carte n'attaque pas.
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
