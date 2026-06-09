package affichage;

import mecaniques.cartes.Animal;
import mecaniques.cartes.Carte;
import mecaniques.cartes.Pouvoirs;
import mecaniques.joueurs.Joueur;
import mecaniques.joueurs.Ligne;
import mecaniques.joueurs.Adversaire;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class AffichageJeu {
    private CreateArrow m_createArrow = new CreateArrow();
    private CreateCard m_createCard = new CreateCard();

    public void afficherPlateau(Joueur joueur, Adversaire adversaire, int numPartie, int numTour, int score){
        System.out.println("\n________________________________________________ Partie " + numPartie + " _________________________________________________\n");
        System.out.println("\tTour : "+(numTour+1));
        afficherLigneCart(adversaire.getLigne(), "");
        afficherLigneArrow(false);
        afficherLigneCart(adversaire.getLigne(), "A");
        afficherLigneArrow(true);
        afficherLigneCart(joueur.getLigne(), "B");
        System.out.println("Gouttes de Sang : "+joueur.getGoutteSang()+" OS : "+ joueur.getOs()+ "\n");
        afficherMainEtPioche(joueur);
        System.out.println("\n_____________________________________________ Score : " + score + " __________________________________________________\n");

    }

    public void afficherLigneCart(Ligne ligne, String lettre){
        String[] lignesAffichage = new String[8];
        for (int i = 0; i < 8; i++) {
            lignesAffichage[i] = "";
        }
        for (int i = 0; i < 4; i++) {
            String[] carte;
            Optional<Carte> carteOpt = ligne.getCarte(i);
            if (carteOpt.isEmpty()) {
                int num = i+1;
                if (!lettre.equals("")){
                    String coord = lettre + num;
                    carte = coordCard(coord);
                }
                else {
                    carte = emptyCard();
                }

            } else {
                carte = animCard(carteOpt.get());
            }
            for (int y = 0; y < carte.length; y++) {
                lignesAffichage[y] += carte[y] + "    ";
            }
        }
        for (int i = 0; i < 8; i++) {
            System.out.println(lignesAffichage[i]);
        }
    }

    public void afficherLigneArrow(boolean up){
        String[][] arrows;
        if (up) arrows = arrowsUp();
        else arrows = arrowsDown();

        for (int ligne = 0; ligne < 2; ligne++) {
            for (int i = 0; i < arrows.length; i++) {
                System.out.print(arrows[i][ligne] + "   ");
            }
            System.out.println();
        }
    }

    public void afficherMainEtPioche(Joueur joueur) {
        String[] main = LaMain(joueur);
        String[] pioche = LaPioche(joueur.getNbPioche());

        int nbLignes = Math.min(main.length, pioche.length);
        for (int i = 0; i < nbLignes; i++) {
            String ligne = String.format("%-84s %s", main[i], pioche[i]);
            System.out.println(ligne);
        }

        if (nbLignes < main.length) {
            for (int i = nbLignes; i < main.length; i++) {
                System.out.println(main[i]);
            }
        }
        if (nbLignes < pioche.length) {
            String espaces = " ".repeat(85);
            for (int i = nbLignes; i < pioche.length; i++) {
                System.out.println(espaces + pioche[i]);
            }
        }
    }

    public void Consignes(){
        System.out.println(
                "*---------------------------------------------------------------------------*\n" +
                        "|\tActions possibles:                                                     \t|\n" +
                        "|\t[fin] - Terminer votre tour                                            \t|\n" +
                        "|\t[piocher] - Piocher une carte                                          \t|\n" +
                        "|\t[placer <numero carte> <position>] - Placer une carte sur le plateau   \t|\n" +
                        "|\t[sacrifier <liste de sacrifices>] - Sacrificer une carte sur le plateau\t|\n" +
                        "*---------------------------------------------------------------------------*"
        );
    }

    private String[] LaMain(Joueur joueur) {
        ArrayList<Animal> main = joueur.getMain();
        String[] tab = new String[main.size() + 1];

        tab[0] = "Votre main :";
        for (int i = 0; i < main.size(); i++) {
            int num = i+1;
            tab[num] = num + ". " + main.get(i).getNom() + " PV: " + main.get(i).getPv() +
                    " Att: " + main.get(i).getAttack() + " Gouttes de sang: " +
                    main.get(i).getGouttesSang() + " Os: " + main.get(i).getOs();
            if (main.get(i).aPouvoir()){
                tab[num] += " Pouvoir(s): " + main.get(i).getNamePouvoir();
            }
        }
        return tab;
    }

    public String[] LaPioche(int nbCartes) {
        return new String[] {
                "*-----------------*",
                "|                 |",
                "|                 |",
       String.format("|       %-10d|", nbCartes),
                "|      cartes     |",
                "|                 |",
                "|                 |",
                "*-----------------*"
        };
    }

    public String[] emptyCard(){
        return new String[]{
                m_createCard.emptyTopBottom(),
                m_createCard.emptyMiddle(),
                m_createCard.emptyMiddle(),
                m_createCard.emptyMiddle(),
                m_createCard.emptyMiddle(),
                m_createCard.emptyMiddle(),
                m_createCard.emptyMiddle(),
                m_createCard.emptyTopBottom()
        };
    }

    public String[] coordCard(String coord){
        return new String[]{
                m_createCard.emptyTopBottom(),
                m_createCard.emptyMiddle(),
                m_createCard.emptyMiddle(),
                m_createCard.coordinate(coord),
                m_createCard.emptyMiddle(),
                m_createCard.emptyMiddle(),
                m_createCard.emptyMiddle(),
                m_createCard.emptyTopBottom()
        };
    }

    public String[] animCard(Carte carte){
        boolean volant = carte.estVolant();
        int dmg = carte.getAttack();

        Optional<List<Pouvoirs>> optPouvoirs = carte.getPouvoir();
        List<Pouvoirs> pouvoirs = optPouvoirs.orElse(Collections.emptyList());

        String nomPouv1 = !pouvoirs.isEmpty() ? String.valueOf(pouvoirs.get(0).getNom()) : "";
        boolean have1 = !nomPouv1.isEmpty();

        String nomPouv2 = pouvoirs.size() > 1 ? String.valueOf(pouvoirs.get(1).getNom()) : "";
        boolean have2 = !nomPouv2.isEmpty();

        return new String[]{
                m_createCard.animTopBottom(),
                m_createCard.animName(carte),
                m_createCard.separate(),
                "| HP : " + m_createCard.animHP(carte),
                dmg > 0 ? "| DMG : " + m_createCard.animDMG(carte) : m_createCard.animMiddle(),
                (m_createCard.volant(volant).equals("") && have2) ? m_createCard.pouvoir(nomPouv2) : m_createCard.animMiddle(),
                have1 ? m_createCard.pouvoir(nomPouv1) : m_createCard.animMiddle(),
                m_createCard.animTopBottom()
        };
    }

    public String[][] arrowsUp(){
        String[] arrow = {
                m_createArrow.arrowUp(),
                m_createArrow.arrowBody()
        };
        return new String[][]{
                arrow,
                arrow,
                arrow,
                arrow
        };
    }

    public String[][] arrowsDown(){
        String[] arrow = {
                m_createArrow.arrowBody(),
                m_createArrow.arrowDown()
        };
        return new String[][]{
                arrow,
                arrow, 
                arrow, 
                arrow
        };
    }
}