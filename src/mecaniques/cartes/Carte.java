package mecaniques.cartes;

import static mecaniques.cartes.Pouvoirs.*;

public class Carte {
    String m_nom;
    int m_points_de_vie;

    public Carte (String nom, int pdv){
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
        int dmg = this.getAttack();

        if(other.getPouvoir() == PUANT){
            dmg--;
        }
        if(dmg < 0){
            dmg = 0;
        }

        other.m_points_de_vie -= dmg;
        if(other.m_points_de_vie < 0){
            other.m_points_de_vie = 0;
        }

        if(this.getPouvoir() == CONTACT_MORTEL && dmg > 0 && other.estCreature()){
            other.m_points_de_vie = 0;
        }

        if(other.getPouvoir() == PIQUES_POINTUES && dmg > 0){
            m_points_de_vie -= 1;
            if(m_points_de_vie < 0){
                m_points_de_vie = 0;
            }
        }
        return dmg;
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

    public boolean estCreature(){
        return false;
    }
}
