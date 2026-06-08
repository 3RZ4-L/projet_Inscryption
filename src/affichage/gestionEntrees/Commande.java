package affichage.gestionEntrees;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Commande {

    private NomCommande m_commande;
    private int m_position = -1;
    private int m_numeroCarte = -1;
    private final List<Integer> m_sacrifices = new ArrayList<>();

    public Commande() {
        Scanner scanner = new Scanner(System.in);
        String entree = scanner.nextLine().trim().toLowerCase();

        analyserCommande(entree);
    }

    private void analyserCommande(String entree) {
        String[] morceaux = entree.split("\\s+");

        if (morceaux.length == 0) {
            System.out.println("Commande vide");
            return;
        }

        m_commande = NomCommande.fromString(morceaux[0]);

        if (m_commande == null) {
            System.out.println("Commande non reconnue");
            return;
        }

        switch (m_commande) {
            case PLACER:
                traiterPlacer(morceaux);
                break;

            case SACRIFIER:
                traiterSacrifier(morceaux);
                break;

            case PIOCHER:
            case FIN:
                break;

            default:
                System.out.println("Commande inconnue");
                m_commande = null;
        }
    }

    private void traiterPlacer(String[] morceaux) {
        if (morceaux.length != 3) {
            System.out.println("Format invalide :\nplacer <numeroCarte> <position>");
            m_commande = null;
            pause();
            return;
        }

        Integer numeroCarte = convertirEntier(morceaux[1]);
        Integer position = convertirPosition(morceaux[2]);

        if (numeroCarte == null || position == null) {
            m_commande = null;
            return;
        }

        m_numeroCarte = numeroCarte;
        m_position = position;
    }

    private void traiterSacrifier(String[] morceaux) {
        if (morceaux.length < 2) {
            System.out.println("Veuillez préciser au moins une position.");
            m_commande = null;
            pause();
            return;
        }

        for (int i = 1; i < morceaux.length; i++) {
            Integer position = convertirPosition(morceaux[i]);

            if (position == null) {
                continue;
            }
            if (position >= 0 && position < 4) {
                m_sacrifices.add(position);
            } else {
                System.out.println("Position invalide : " + morceaux[i]);
                pause();
            }
        }
    }

    private Integer convertirPosition(String texte) {
        if (texte.startsWith("a") || texte.startsWith("b")) {
            texte = texte.substring(1);
        }

        Integer valeur = convertirEntier(texte);

        if (valeur == null) {
            System.out.println("Position invalide : " + texte);
            pause();
            return null;
        }

        return valeur - 1;
    }

    private Integer convertirEntier(String texte) {
        try {
            return Integer.parseInt(texte);
        } catch (NumberFormatException e) {
            System.out.println("'" + texte + "' n'est pas un nombre valide.");
            pause();
            return null;
        }
    }

    public boolean estValide(int nbCarteMax, boolean aPiochee) {
        if (m_commande == null) {
            return false;
        }

        switch (m_commande) {
            case PLACER:
                return validerPlacer(nbCarteMax);

            case PIOCHER:
                if (aPiochee) {
                    System.out.println("Vous ne pouvez piocher qu'une seule fois par tour.");
                    pause();
                    return false;
                }
                return true;

            case SACRIFIER:
                if (m_sacrifices.isEmpty()) {
                    System.out.println("Veuillez donner au moins une position à sacrifier.");
                    pause();
                    return false;
                }
                return true;

            case FIN:
                return true;

            default:
                return false;
        }
    }

    private boolean validerPlacer(int nbCarteMax) {
        boolean positionValide = m_position >= 0 && m_position < 4;
        boolean carteValide = m_numeroCarte > 0 && m_numeroCarte <= nbCarteMax;

        if (!positionValide) {
            System.out.println("Position invalide. La position doit être entre 1 et 4.");
            pause();
        }

        if (!carteValide) {
            System.out.println("Numéro de carte invalide. Vous avez " + nbCarteMax + " carte(s) en main.");
            pause();
        }

        return positionValide && carteValide;
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

    public static void pause() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void errPlacer() {
        System.out.println("Vous ne pouvez pas placer cette carte.");
        pause();
    }
}