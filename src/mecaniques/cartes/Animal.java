package mecaniques.cartes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static mecaniques.cartes.Pouvoirs.*;
import static mecaniques.cartes.Pouvoirs.CONTACT_MORTEL;
import static mecaniques.cartes.Pouvoirs.PIQUES_POINTUES;

public class Animal extends Carte {
    private int m_attaque;
    private int m_gouttes_sang;
    private int m_os;
    private boolean m_volant;
    private ArrayList<Pouvoirs> m_pouvoir = new ArrayList<>();
    private int m_nbTour;

    public Animal(String nom, int pdv, int att, int gds, int os, boolean volant, Pouvoirs power) {
        super(nom, pdv);
        m_attaque = att;
        m_gouttes_sang = gds;
        m_os = os;
        m_volant = volant;
        m_pouvoir.add(power);
        m_nbTour = 0;
    }

    public Animal(String nom, int pdv, int att, int gds, int os, boolean volant) {
        super(nom, pdv);
        m_attaque = att;
        m_gouttes_sang = gds;
        m_os = os;
        m_volant = volant;
        m_nbTour = 0;
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

    public void ajouterTour(){
        m_nbTour++;
    }

    public int doDamage(Optional<Carte> other) {
        // Volant = attaque directe toujours
        if (this.estVolant()) {
            return m_attaque;
        }

        if (other.isEmpty()) {
            return m_attaque;
        }

        Carte carte = other.get();
        if (carte.estMort()){
            return m_attaque;
        }

        int dmg = m_attaque;
        if (carte.aPouvoir(PUANT)) {
            dmg--;
        }
        if (dmg < 0) dmg = 0;
        carte.m_points_de_vie -= dmg;

        if (carte.m_points_de_vie < 0) carte.m_points_de_vie = 0;

        if (this.aPouvoir(CONTACT_MORTEL) && dmg > 0 && carte.estCreature()) {
            carte.m_points_de_vie = 0;
        }

        if (carte.aPouvoir(PIQUES_POINTUES) && dmg > 0) {
            m_points_de_vie -= 1;
            if (m_points_de_vie < 0) m_points_de_vie = 0;
        }

        return dmg;
    }

    public int attaqueDirect() {
        return m_attaque;
    }

    public boolean estCreature() {
        return true;
    }

    public String getNamePouvoir() {
        if (m_pouvoir.isEmpty())
            return "";
        String res = "";

        for(int i = 0; i < m_pouvoir.size(); i++){
            res += m_pouvoir.get(i).getNom();

            if(i < m_pouvoir.size()-1){
                res+= ", ";
            }
        }
        return res;
    }

    public Optional<List<Pouvoirs>> getPouvoir() {
        if (m_pouvoir.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(m_pouvoir);
    }

    public boolean aPouvoir(){
        return !m_pouvoir.isEmpty();
    }

    public boolean ajouterPouvoir(Pouvoirs pouvoir) {
        if (this.aPouvoir(pouvoir))
            return false;
        if (pouvoir == CROISSANCE && this.getNom().equals("Loup"))
            return false;
        this.m_pouvoir.add(pouvoir);
        return true;
    }

    public boolean aPouvoir(Pouvoirs pouvoir) {
        return this.m_pouvoir.contains(pouvoir);
    }

    public Optional<Carte> croissance() {
        if (!aPouvoir(CROISSANCE) || m_nbTour < 2) {
            return Optional.empty();
        }

        Optional<List<Pouvoirs>> pouvoirsOpt = getPouvoir();

        if (pouvoirsOpt.isPresent()) {
            List<Pouvoirs> pouvoirs = pouvoirsOpt.get();
            if (pouvoirs.size() == 2) {
                for (Pouvoirs pouvoir : pouvoirs) {
                    if (pouvoir != CROISSANCE) {
                        return Optional.of( new Animal("Loup", 2, 3, 2, 0, false, pouvoir));
                    }
                }
            }
        }
        return Optional.of(new Animal("Loup", 2, 3, 2, 0, false));
    }
}