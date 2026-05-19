package jeu;

import cartes.Animal;

import java.util.ArrayList;

public class Joueur{
    private Pioche m_pioche;
    private ArrayList<Animal> m_main;

    Joueur(){
        m_pioche = new Pioche(19);
        int i = 0;
        while (i < 4){
            piocher();
            i--;
        }
    }

    public void piocher(){
        m_main.add(m_pioche.piocher());
    }

    public void jouerCarte(int numCarte){
        Animal carte = m_main.remove(numCarte);
    }

}
