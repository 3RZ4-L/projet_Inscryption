package commande;

import java.util.Scanner;

public class Commande {
    String m_commande;
    String m_position;
    String m_numeroCarte;

    public Commande() {
        Scanner scanner = new Scanner(System.in);
        String entree = scanner.nextLine().trim().toLowerCase();
        if (entree.length() == 9 || entree.length() == 8) {
            m_commande = entree.substring(0, 6);
            m_numeroCarte = entree.substring(6, 7);
            m_position = entree.substring(-1);
        }
        else m_commande = entree;
    }

    public boolean estValide(int nbCarteMax){
        if (m_commande == "fin" || m_commande == "piocher") return true;
        if (m_commande == "placer" &&
            Integer.parseInt(m_position) < 5 &&
            Integer.parseInt(m_position) >= 0 &&
            Integer.parseInt(m_numeroCarte) >= 0 &&
            Integer.parseInt(m_numeroCarte) <= nbCarteMax) return true;
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
