package mecaniques.cartes;

import java.util.List;
import java.util.Optional;

public abstract class Carte {
    protected String m_nom;
    protected int m_points_de_vie;

    public Carte(String nom, int pdv) {
        m_nom = nom;
        m_points_de_vie = pdv;
    }

    public int getPv() {
        return m_points_de_vie;
    }

    public String getNom() {
        return m_nom;
    }

    public boolean estMort() {
        return (m_points_de_vie <= 0);
    }

    public abstract int doDamage(Optional<Carte> other);

    public abstract int attaqueDirect();

    public abstract int getAttack();

    public abstract boolean estVolant();

    public abstract String getNamePouvoir();

    public abstract boolean aPouvoir(Pouvoirs pouvoir);

    public abstract boolean estCreature();

    public abstract Optional<Carte> croissance();

    public abstract Optional<List<Pouvoirs>> getPouvoir();

    public abstract void ajouterTour();
}
