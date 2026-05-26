package GestionEntrees;

import java.util.Scanner;

public class Commande {
    String m_commande;
    String m_position;
    String m_numeroCarte;

    public Commande() {
        System.out.println("Actions possibles:\n" +
                "\t[fin] Terminer votre tour\n" +
                "\t[piocher] Piocher une carte\n" +
                "\t[placer <numero carte> <position>] Placer une carte sur le plateau");
        Scanner scanner = new Scanner(System.in);
        String entree = scanner.nextLine().trim().toLowerCase();
        if (entree.length() == 9 || entree.length() == 8) {
            m_commande = entree.substring(0, 6);
            m_numeroCarte = entree.substring(6, 7);
            m_position = entree.substring(-1);
        }
        else m_commande = entree;
    }

    public boolean estValide(int nbCarteMax, int nbpioche){
        try {
            if (m_commande.equals("placer") &&
                    Integer.parseInt(m_position) < 5 &&
                    Integer.parseInt(m_position) >= 0 &&
                    Integer.parseInt(m_numeroCarte) >= 0 &&
                    Integer.parseInt(m_numeroCarte) <= nbCarteMax) return true;
        }catch (Exception err){
            return false;
        }
        if (m_commande.equals("fin"))return true;
        if (m_commande.equals("piocher") && nbpioche < 1) return true;
        if (m_commande.equals("pioche")){
            System.out.println("Vous ne pouvez piocher qu'une seule fois");
            return false;
        }
        System.out.println("Veuillez reéssayer\n");
        return false;
    }

    public String getCommande(){
        return m_commande;
    }

    public int getPosition(){
        return Integer.parseInt(m_position);
    }
    public int getNumeroCarte(){
        return Integer.parseInt(m_numeroCarte);
    }
}
