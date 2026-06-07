package affichage.gestionEntrees;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Commande {
    private NomCommande m_commande;
    private int m_position;
    private int m_numeroCarte;
    private List<Integer> m_sacrifices = new ArrayList();

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

        m_commande = null;
        m_position = -1;
        m_numeroCarte = -1;

        if (morceaux.length > 0) {
            m_commande = NomCommande.fromString(morceaux[0]);
        }
        if (m_commande != null) {
            switch (m_commande) {
                case PLACER:
                    if (morceaux.length == 3) {
                        m_numeroCarte = Integer.parseInt(morceaux[1]);
                        String positionStr = morceaux[2];
                        if (positionStr.startsWith("a") || positionStr.startsWith("b")) {
                            positionStr = positionStr.substring(1);
                        }
                        m_position = Integer.parseInt(positionStr) - 1;
                    }
                    else {
                        System.out.println("Format de la commande [placer] invalide.\nplacer <numéro> <position>");
                        m_commande = null;
                    }
                    break;

                case SACRIFIER:
                    if (morceaux.length > 1)
                        for (int i = 1; i < morceaux.length; i++) {
                            String numStr = morceaux[i];
                            if (numStr.startsWith("a") || numStr.startsWith("b")) {
                                numStr = numStr.substring(1);
                            }
                            int num = Integer.parseInt(numStr) - 1;
                            if (num >= 0 && num < 4) {
                                m_sacrifices.add(num);
                            } else {
                                System.out.println("Position invalide (doit être entre 1 et 4)");
                            }
                        }
                    else{
                        System.out.println("Format de nombre invalide pour le sacrifice");
                    }
                    break;
                case PIOCHER:
                    break;
                case FIN:
                    break;
                default:
                    System.out.println("Commande inconnue");
                    break;
            }
        } else {
            System.out.println("Commande non reconnue");
        }
    }

    public boolean estValide(int nbCarteMax, boolean aPiochee) {
        if (m_commande == null) return false;
        switch (m_commande) {
            case PLACER:
                boolean positionValide = m_position >= 0 && m_position < 4;
                boolean carteValide = m_numeroCarte > 0 && m_numeroCarte <= nbCarteMax;
                if (!positionValide) {
                    System.out.println("Position invalide. La position doit être entre 1 et 4");
                }
                if (!carteValide) {
                    System.out.println("Numéro de carte invalide. Vous avez " + nbCarteMax + " carte(s) en main");
                }
                return positionValide && carteValide;
            case PIOCHER:
                if (!aPiochee) return true;
                System.out.println("Vous ne pouvez piocher qu'une seule fois par tour");
                return false;
            case SACRIFIER:
                if (m_sacrifices.isEmpty()) {
                    System.out.println("Veuillez donner au moins une position à sacrifier");
                    return false;
                }
                return true;
            case FIN:
                return true;
            default:
                return false;
        }
    }

    public NomCommande getCommande() {
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