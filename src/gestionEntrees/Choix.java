package gestionEntrees;

import mecaniques.cartes.Animal;
import mecaniques.joueurs.Pioche;
import java.util.Scanner;

public class Choix {
    private Animal m_choix;

    public Choix(Pioche pioche){
        Scanner scanner = new Scanner(System.in);
        String entree = scanner.nextLine().trim().toLowerCase();
        while(!estValide(entree)){
            scanner = new Scanner(System.in);
            entree = scanner.nextLine().trim().toLowerCase();
        }
        if (entree.equals("1")){
            m_choix = pioche.piocher();
            return;
        }
        pioche.piocher();
        m_choix = pioche.piocher();
    }
    public boolean estValide(String entree){
        return (entree.equals("1") ||  entree.equals("2"));
    }
    public Animal getChoix(){
        return m_choix;
    }
}
