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
                        score += attaquer(joueur.getCartes()[i], adversaire.getCartes()[i]);
                    }
                }
                else if (!joueur.estCaseVide(i)) {
                    attaqueDirect(joueur.getCartes()[i]);
                }
            }
        }
        return score;
    }

    public int attaquer(Carte attaquante, Carte cible){
        if (attaquante.estVolant()){
            return attaqueDirect(attaquante);
        }
        int score = attaquante.getAttack() - cible.getPv();
        attaquante.doDamage(cible);
        if (score > 0){
            return score;
        }
        return 0;
    }

    public int attaqueDirect(Carte attaquante){
        return attaquante.getAttack();
    }
}
