package affichage.gestionEntrees;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Commande {
    String m_commande;
    int m_position;
    int m_numeroCarte;
    List<Integer> m_sacrifices = new ArrayList();

    public Commande() {
        System.out.println(
                "*---------------------------------------------------------------------------*\n" +
                "|\tActions possibles:                                                     \t|\n" +
                "|\t[fin] - Terminer votre tour                                            \t|\n" +
                "|\t[piocher] - Piocher une carte                                          \t|\n" +
                "|\t[placer <numero carte> <position>] - Placer une carte sur le plateau   \t|\n" +
                "|\t[sacrifier <liste de sacrifices>] - Sacrificer une carte sur le plateau\t|\n" +
                "*---------------------------------------------------------------------------*"
        );
        Scanner scanner = new Scanner(System.in);
        String entree = scanner.nextLine().trim().toLowerCase();
        String[] morceaux = entree.split("\\s+");

        if (morceaux.length > 0) {
            m_commande = morceaux[0];
        }
        if (m_commande.equals("placer")) {
            if (morceaux.length == 3) {
                m_numeroCarte = Integer.parseInt(morceaux[1]);
                m_position = Integer.parseInt(morceaux[2].substring(1))-1;
            }
        }
        else if (m_commande.equals("sacrifier")) {
            for (int i = 1; i < morceaux.length; i++) {
                int num = Integer.parseInt(morceaux[i].substring(1))-1;
                m_sacrifices.add(num);
            }
        }
    }

    public boolean estValide(int nbCarteMax, boolean piocher) {
        if ("placer".equals(m_commande)) {
            return m_position >= 0
                    && m_position < 5
                    && m_numeroCarte > 0
                    && m_numeroCarte <= nbCarteMax;
        }
        if ("piocher".equals(m_commande)) {
            if (!piocher) {
                return true;
            }
            System.out.println("Vous ne pouvez piocher qu'une seule fois");
            return false;
        }
        if ("sacrifier".equals(m_commande)) {
            if (m_sacrifices.isEmpty()) {
                return false;
            }
            for (int num : m_sacrifices) {
                if (num <= 0 || num > 4) {
                    return false;
                }
            }
            return true;
        }
        return "fin".equals(m_commande);
    }

    public String getCommande() {
        return m_commande;
    }

    public int getPosition() {
        return m_position;
    }

    public int getNumeroCarte() {
        return m_numeroCarte;
    }

    public List<Integer> getSacrifices() {
        return m_sacrifices;
    }
}