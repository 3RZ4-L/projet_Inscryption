package affichage.gestionEntrees;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Commande {
    String m_commande;
    String m_position;
    String m_numeroCarte;
    List<String> m_sacrifices = new ArrayList<>();

    public Commande() {
        System.out.println(
                "Actions possibles:\n" +
                        "\t[fin] Terminer votre tour\n" +
                        "\t[piocher] Piocher une carte\n" +
                        "\t[placer <numero carte> <position>] Placer une carte sur le plateau\n" +
                        "\t[sacrifier <liste de sacrifices>]");
        Scanner scanner = new Scanner(System.in);
        String entree = scanner.nextLine().trim().toLowerCase();
        String[] morceaux = entree.split("\\s+");

        if (morceaux.length > 0) {
            m_commande = morceaux[0];
        }
        if (m_commande.equals("placer")) {
            if (morceaux.length == 3) {
                m_numeroCarte = morceaux[1];
                m_position = morceaux[2];
            }
        }
        else if (m_commande.equals("sacrifier")) {
            for (int i = 1; i < morceaux.length; i++) {
                m_sacrifices.add(morceaux[i].toUpperCase());
            }
        }
    }

    public boolean estValide(int nbCarteMax, boolean piocher) {
        try {
            if (m_commande.equals("placer") &&
                    Integer.parseInt(m_position) < 5 &&
                    Integer.parseInt(m_position) >= 0 &&
                    Integer.parseInt(m_numeroCarte) >= 0 &&
                    Integer.parseInt(m_numeroCarte) <= nbCarteMax) {
                return true;
            }
        } catch (Exception err) {
            return false;
        }
        if (m_commande.equals("piocher") && !piocher) {
            return true;
        }
        if (m_commande.equals("piocher")) {
            System.out.println("Vous ne pouvez piocher qu'une seule fois");
            return false;
        }
        if (m_commande.equals("sacrifier")) {
            if (m_sacrifices.size() > 0) {
                return true;
            }
            return false;
        }
        return m_commande.equals("fin");
    }

    public String getCommande() {
        return m_commande;
    }

    public int getPosition() {
        return Integer.parseInt(m_position);
    }

    public int getNumeroCarte() {
        return Integer.parseInt(m_numeroCarte);
    }

    public List<String> getSacrifices() {
        return m_sacrifices;
    }
}