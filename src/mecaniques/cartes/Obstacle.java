package mecaniques.cartes;

public class Obstacle extends Carte{
    public Obstacle(String nom, Integer pdv){
        super(nom, pdv);
    }

    public boolean estCreature(){
        return false;
    }
}

