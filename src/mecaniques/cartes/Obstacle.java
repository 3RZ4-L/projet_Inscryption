package mecaniques.cartes;

import java.util.List;
import java.util.Optional;

public class Obstacle extends Carte {

    public Obstacle(String nom, Integer pdv) {
        super(nom, pdv);
    }

    public boolean estCreature() {
        return false;
    }

    public int attaqueDirect() {
        return 0;
    }

    public int doDamage(Optional<Carte> other){
        return 0;
    }

    public int getAttack() {
        return 0;
    }

    public boolean estVolant() {
        return false;
    }

    public String getNamePouvoir() {
        return "";
    }

    public Optional<List<Pouvoirs>> getPouvoir() {
        return Optional.empty();
    }

    public boolean aPouvoir(Pouvoirs pouvoir){
        return false;
    }

    public Optional<Carte> croissance() {
        return Optional.empty();
    }

}