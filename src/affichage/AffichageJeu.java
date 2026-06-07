package affichage;
import mecaniques.cartes.Animal;
import mecaniques.cartes.Carte;
import mecaniques.joueurs.Ligne;
import mecaniques.joueurs.Adversaire;
import mecaniques.joueurs.Joueur;

import java.util.ArrayList;
import java.util.Optional;

public class AffichageJeu {
    private CreateArrow m_createArrow = new CreateArrow();
    private CreateCard m_createCard = new CreateCard();

    public void afficherPlateau(Joueur joueur, Adversaire adversaire, int numPartie, int numTour, int score){
        System.out.println("\n________________________________________________ Partie " + numPartie + " _________________________________________________\n");
        System.out.println("\tTour : "+numTour);
        afficherLigneCart(adversaire.getLigne(), "");
        afficherLigneArrow(false);
        afficherLigneCart(adversaire.getLigne(), "A");
        afficherLigneArrow(true);
        afficherLigneCart(joueur.getLigne(), "B");
        afficherMainEtPioche(joueur);
        System.out.println("\n_____________________________________________ Score : " + score + " __________________________________________________\n");

    }

    public void message(String Titremessage){
        if (Titremessage.equals("ErrPlacer")){
            Messages m = new Messages();
            m.errPlacer();
        }
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

    private String[] LaMain(Joueur joueur) {
        ArrayList<Animal> main = joueur.getMain();
        String[] tab = new String[main.size() + 1];

        tab[0] = "Votre main :";
        for (int i = 0; i < main.size(); i++) {
            int num = i+1;
            tab[num] = num + ". " + main.get(i).getNom() + " PV: " + main.get(i).getPv() +
                    " Att: " + main.get(i).getAttack() + " Gouttes de sang: " +
                    main.get(i).getGouttesSang() + " Os: " + main.get(i).getOs();
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
        String nomPouv = carte.getNamePouvoir();
        boolean have = !nomPouv.isEmpty();

        return new String[]{
                m_createCard.animTopBottom(),
                m_createCard.animName(carte),
                m_createCard.separate(),
                "| HP : " + m_createCard.animHP(carte),
                dmg > 0 ? "| DMG : " + m_createCard.animDMG(carte) : m_createCard.animMiddle(),
                volant ? "| Volant          |" : m_createCard.animMiddle(),
                have ? m_createCard.pouvoir(carte) : m_createCard.animMiddle(),
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