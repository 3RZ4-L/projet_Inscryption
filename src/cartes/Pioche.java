package cartes;

import java.util.ArrayList;

public class Pioche {
    
    public Animal create_chat(){
        return new Animal("Chat", 0, 1,1, 0);
    }

    public Animal create_grizzly(){
        return new Animal("Grizzly", 4, 6,3, 0);
    }

    public Animal create_coyote(){
        return new Animal("Coyote", 2,	1,	0,	4);
    }

    public Volant create_moineau() {
        return new Volant("Moineau", 1, 2, 1, 0);
    }

    public Animal create_hermine(){
        return new Animal("Hermine",	1,	3,	1,	0);
    }

    public Animal create_louveteau(){
        return new Animal("Louveteau",	1,	1,	1,	0);
    }

    public Animal create_loup(){
        return new Animal("Loup",	3,	2,	2,	0);
    }

    public Animal create_punaise(){
        return new Animal("Punaise",	1,	2,	0,	2);
    }

    public Volant create_corbeau(){
        return new Volant("Corbeau",	2,	3,	2,	0);
    }

    public Animal create_ecureil(){
        return new Animal("Ecureuil",	0,	1,	0,	0);
    }

    public Obstacle create_richer(){
        return new Obstacle("Rocher",	5);
    }

    public Obstacle create_sapin(){
        return new Obstacle("Sapin",3);
    }
}
