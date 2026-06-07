package mecaniques.cartes;

import java.util.Optional;

public class Obstacle extends Carte {

    public Obstacle(String nom, Integer pdv) {
        super(nom, pdv);
    }

    @Override
    public boolean estCreature() {
        return false;
    }

    @Override
    public int attaqueDirect() {
        return 0;
    }

    @Override
    public int getAttack() {
        return 0;
    }

    @Override
    public boolean estVolant() {
        return false;
    }

    @Override
    public String getNamePouvoir() {
        return "";
    }

    @Override
    public Pouvoirs getPouvoir() {
        return Pouvoirs.NULL;
    }

    @Override
    public Optional<Carte> croissance() {
        return Optional.empty();
    }
}