package affichage.gestionEntrees;

import mecaniques.cartes.Animal;
import mecaniques.joueurs.Pioche;
import java.util.Scanner;

public class Choix {
    private Animal m_choix;

    public Choix(Animal carte1, Animal carte2){
        String c1 = "1. " + carte1.getNom() + " PV: " + carte1.getPv() +
                " Att: " + carte1.getAttack();
        c1 += carte1.getGouttesSang() == 0 ? "" : " Gouttes de sang: " + carte1.getGouttesSang();
        c1 += carte1.getOs() == 0 ? "" : " Os: " + carte1.getOs();
        c1 += carte1.getNamePouvoir().isEmpty() ? "" : " Pouvoir : " + carte1.getNamePouvoir();
        
        String c2 = "2. " + carte2.getNom() + " PV: " + carte2.getPv() +
                " Att: " + carte2.getAttack();
        c2 += carte2.getGouttesSang() == 0 ? "" : " Gouttes de sang: " + carte2.getGouttesSang();
        c2 += carte2.getOs() == 0 ? "" : " Os: " + carte2.getOs();
        c2 += carte2.getNamePouvoir().isEmpty() ? "" : " Pouvoir : " + carte2.getNamePouvoir();

        System.out.println(
                "*------------------------------------------------------------------------*\n" +
                "\t\t\t\t\t\tChoix entre deux animaux:\n" +
                        c1+ "\n" +
                        c2+ "\n" +
                "*------------------------------------------------------------------------*");

        Scanner scanner = new Scanner(System.in);
        String entree = scanner.nextLine().trim().toLowerCase();
        while (!estValide(entree)) {
            System.out.println("Choix invalide. Veuillez entrer 1 ou 2 :");
            entree = scanner.nextLine().trim().toLowerCase();
        }
        if (entree.equals("1")){
            m_choix = carte1;
            return;
        }
        m_choix = carte2;
    }
    public boolean estValide(String entree){
        return (entree.equals("1") ||  entree.equals("2"));
    }
    public Animal getChoix(){
        return m_choix;
    }
}
