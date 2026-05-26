package mecaniques.cartes;

import java.util.ArrayList;

public class Animal extends Carte{
    private int m_attaque;
    private int m_gouttes_sang;
    private int m_os;
    private boolean m_volant;
    private ArrayList<Pouvoirs> m_pouvoirs;

    public Animal(String nom, int pdv, int att, int gds, int os, boolean volant) {
        super(nom, pdv);
        m_attaque = att;
        m_gouttes_sang = gds;
        m_os = os;
        m_volant = volant;
        m_pouvoirs = new ArrayList<>();
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

    public ArrayList<Pouvoirs> getPouvoirs(){
        return m_pouvoirs;
    }

    public boolean havePouvoir(Pouvoirs pouv){
        return m_pouvoirs.contains(pouv);
    }

    public boolean addPouvoir(Pouvoirs pouv){
        if(!havePouvoir(pouv)){
            m_pouvoirs.add(pouv);
            return true;
        }
        return false;
    }

    public String getNomsPouvoirs(){
        String res = "";
        for(int i = 0; i < m_pouvoirs.size(); i++){
            res += m_pouvoirs.get(i).getNom();
            if(i < m_pouvoirs.size() - 1){
                res += ", ";
            }
        }
        return res;
    }
}
