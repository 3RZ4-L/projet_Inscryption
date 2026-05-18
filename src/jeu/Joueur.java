package jeu;

import cartes.Animal;
import java.util.ArrayList;

public class Joueur {
    private ArrayList<Animal> m_pioche;

    Joueur(){
        Pioche pioche = new Pioche();
        for (int i = 0; i < 4; i++){
            m_pioche.add(Pioche.piocher());
        }
    }


}
