package mecaniques.cartes;

import java.util.ArrayList;
import java.util.Optional;

import static mecaniques.cartes.Pouvoirs.CROISSANCE;
import static mecaniques.cartes.Pouvoirs.NULL;

public class Animal extends Carte {
    private int m_attaque;
    private int m_gouttes_sang;
    private int m_os;
    private boolean m_volant;
    private ArrayList<Pouvoirs> m_pouvoir = new ArrayList<>();

    public Animal(String nom, int pdv, int att, int gds, int os, boolean volant, Pouvoirs power) {
        super(nom, pdv);
        m_attaque = att;
        m_gouttes_sang = gds;
        m_os = os;
        m_volant = volant;
        m_pouvoir.add(power);
    }

    public Animal(String nom, int pdv, int att, int gds, int os, boolean volant) {
        super(nom, pdv);
        m_attaque = att;
        m_gouttes_sang = gds;
        m_os = os;
        m_volant = volant;
    }

    public int getAttack() {
        return m_attaque;
    }

    public int getGouttesSang() {
        return m_gouttes_sang;
    }

    public int getOs() {
        return m_os;
    }

    public boolean estVolant() {
        return m_volant;
    }

    /*public int doDamage(Carte other) {    ##enlever, car les pouvoirs ne fonctionnent pas
        if (this.estVolant()) {
            return attaqueDirect();
        }
        if (other == null || other.estMort()) {
            return attaqueDirect();
        }
        other.m_points_de_vie -= this.getAttack();
        return this.getAttack();
    }*/

    public int attaqueDirect() {
        return this.getAttack();
    }

    public boolean estCreature() {
        return true;
    }

    public String getNamePouvoir() {
        if (/*m_pouvoir == null || */m_pouvoir.isEmpty())
            return "";
        String res = "";
        /*if (m_pouvoir.size() == 1)
            return m_pouvoir.get(0).getNom();
        if (m_pouvoir.size() == 2)
            return m_pouvoir.get(0).getNom() + ", " + m_pouvoir.get(1).getNom();*/
        for(int i = 0; i < m_pouvoir.size(); i++){
            res += m_pouvoir.get(i).getNom();
            if(i < m_pouvoir.size()){
                res += " ,";
            }
        }
        return res;
    }

    public Optional<Pouvoirs> getPouvoir() {
        if (m_pouvoir != null && !m_pouvoir.isEmpty()) {
            return Optional.of(m_pouvoir.get(0));
        }
        return Optional.empty();
    }

    public boolean aPouvoir(){
        if (m_pouvoir != null && !m_pouvoir.isEmpty()) {
            return true;
        }
        return false;
    }

    /*public void ajouterPouvoir(Optional<Pouvoirs> pouvoir) { ##pour Joueur##
        if (pouvoir.isPresent())
            m_pouvoir.add(pouvoir.get());
    }*/

    public boolean aPouvoir(Pouvoirs pouvoir) {
        return m_pouvoir.contains(pouvoir);
    }

    public Optional<Carte> croissance() {
        if (this.aPouvoir(CROISSANCE)) {
            return Optional.of(new Animal("Loup", 2, 3, 2, 0, false));
        }
        return Optional.empty();
    }
}