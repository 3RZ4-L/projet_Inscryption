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
        if (m_pouvoir == null || m_pouvoir.isEmpty())
            return "";
        if (m_pouvoir.size() == 1)
            return m_pouvoir.get(0).getNom();
        if (m_pouvoir.size() == 2)
            return m_pouvoir.get(0).getNom() + ", " + m_pouvoir.get(1).getNom();
        return "";
    }

    public Optional<List<Pouvoirs>> getPouvoir() {
        if (m_pouvoir != null && !m_pouvoir.isEmpty()) {
            return Optional.of(m_pouvoir);
        }
        return Optional.empty();
    }

    public boolean aPouvoir(){
        if (m_pouvoir != null && !m_pouvoir.isEmpty()) {
            return true;
        }
        return false;
    }

    public void ajouterPouvoir(Pouvoirs pouvoir) {
        if (pouvoir != null)
            m_pouvoir.add(pouvoir);
    }

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