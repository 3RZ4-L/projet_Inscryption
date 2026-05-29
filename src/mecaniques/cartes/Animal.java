package mecaniques.cartes;

import static mecaniques.cartes.Pouvoirs.NULL;

public class Animal extends Carte{
    private int m_attaque;
    private int m_gouttes_sang;
    private int m_os;
    private boolean m_volant;
    private Pouvoirs m_pouvoir;

    public Animal(String nom, int pdv, int att, int gds, int os, boolean volant, Pouvoirs power) {
        super(nom, pdv);
        m_attaque = att;
        m_gouttes_sang = gds;
        m_os = os;
        m_volant = volant;
        m_pouvoir = power;
    }
    public Animal(String nom, int pdv, int att, int gds, int os, boolean volant) {
        super(nom, pdv);
        m_attaque = att;
        m_gouttes_sang = gds;
        m_os = os;
        m_volant = volant;
        m_pouvoir = NULL;
    }

    public int getAttack(){
        return m_attaque;
    }

    public int getGouttesSang(){
        return m_gouttes_sang;
    }

    public int getOs(){
        return m_os;
    }

    public boolean estVolant(){
        return m_volant;
    }

    public int doDamage(Carte other){
        if (this.estVolant()){
            return attaqueDirect();
        }
        if (other == null || other.estMort()){
            return attaqueDirect();
        }
        other.m_points_de_vie -= this.getAttack();
        return 0;
    }

    public int attaqueDirect(){
        return this.getAttack();
    }

    public String getNamePouvoir(){
        return m_pouvoir.getNom();
    }

    public Pouvoirs getPouvoir(){
        return m_pouvoir;
    }

    public boolean estCreature(){
        return true;
    }
}
