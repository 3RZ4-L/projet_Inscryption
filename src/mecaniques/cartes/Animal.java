package mecaniques.cartes;

import java.util.ArrayList;

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

    public void doDamage(Carte other){
        other.takeDamage(m_attaque);
    }

    public String getPouvoir(){
        return m_pouvoir.getNom();
    }
}
