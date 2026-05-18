package jeu;

import cartes.Animal;
import cartes.Carte;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class Pioche {
    private static Animal[] m_lstAnimaux = new Animal[10];
    private Stack m_pioche;

    public Pioche() {
        m_lstAnimaux[0] = new Animal("Grizzly", 4, 6,3, 0, false);
        m_lstAnimaux[1] = new Animal("Coyote", 2,	1,	0,	4, false);
        m_lstAnimaux[2] = new Animal("Moineau", 1, 2, 1, 0, true);
        m_lstAnimaux[3] = new Animal("Hermine",	1,	3,	1,	0, false);
        m_lstAnimaux[4] = new Animal("Louveteau",	1,	1,	1,	0, false);
        m_lstAnimaux[5] = new Animal("Loup",3,	2,	2,	0, false);
        m_lstAnimaux[6] = new Animal("Punaise",	1,	2,	0,	2, false);
        m_lstAnimaux[7] = new Animal("Corbeau",	2,	3,	2,	0, true);
        m_lstAnimaux[8] = new Animal("Ecureuil",	0,	1,	0,	0, false);
        m_lstAnimaux[9] = new Animal("Chat",	0,	1,	1,	0, false);
    };

    public static Animal piocher(){
        Random rand = new Random();
        int indice = rand.nextInt();
        return m_lstAnimaux[indice];
    }
}
