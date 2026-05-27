package mecaniques.jeu;

import mecaniques.cartes.Carte;

public class Bataille {

    public Bataille(){
    }

    public int commencerAttaque(Ligne joueur, Ligne adversaire){
        int score = 0;
        while (score < 5) {
            for (int i = 0; i < 4; i++) {
                if (!joueur.estCaseVide(i) && !adversaire.estCaseVide(i)) {
                    if (joueur.getCartes()[i].getAttack() > 0) {
                        score += joueur.getCartes()[i].doDamage(adversaire.getCartes()[i]);
                    }
                }
                else if (!joueur.estCaseVide(i)) {
                    joueur.getCartes()[i].attaqueDirect();
                }
            }
        }
        return score;
    }
}
