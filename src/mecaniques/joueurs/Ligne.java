package mecaniques.joueurs;

import affichage.Messages;
import mecaniques.cartes.Carte;
import mecaniques.cartes.Pouvoirs;

import java.util.List;
import java.util.Optional;
import static mecaniques.cartes.Pouvoirs.NOMBREUSES_VIES;

public class Ligne {
    private Optional<Carte>[] m_cartes;


    public Ligne(){
        m_cartes = (Optional<Carte>[]) new Optional[4];
        for (int i = 0; i < 4; i++) {
            m_cartes[i] = Optional.empty();
        }
    }

    public boolean estCaseVide(int numCarte){
        return numCarte >= 0 && numCarte < 4 && m_cartes[numCarte].isEmpty();
    }

    public boolean placerCarte(Carte carte, int position){
        if(estCaseVide(position)){
            m_cartes[position] = Optional.of(carte);
            return true;
        }
        return false;
    }

    public boolean sacrifier(int position){
        if (!estCaseVide(position) && m_cartes[position].get().estCreature()){
            if (m_cartes[position].get().aPouvoir(NOMBREUSES_VIES)) {
                return true;
            }
            m_cartes[position] = Optional.empty();
            return true;
        }
        return false;
    }

    public boolean estSacrifiable(int position) {
        if (position < 0 || position >= m_cartes.length) {
            return false;
        }
        return m_cartes[position].isPresent() && m_cartes[position].get().estCreature();
    }

    public Optional<Pouvoirs> pierreDeSacrifice(int position){
        Optional<Carte> carteOpt = getCarte(position);
        if (carteOpt.isEmpty())
            return Optional.empty();
        Optional<List<Pouvoirs>> lstOpt = carteOpt.get().getPouvoir();
        if (lstOpt.isEmpty() || lstOpt.get().isEmpty()){
            sacrifier(position);
            return Optional.empty();
        }
        Pouvoirs pouvoir = lstOpt.get().get(0);
        sacrifier(position);

        return Optional.of(pouvoir);
    }

    public void croissance(){
        for (int i = 0; i < 4; i++) {
            if (m_cartes[i].isPresent()) {
                Carte carte = m_cartes[i].get();
                carte.ajouterTour();
                Optional<Carte> nouvelleCarte = carte.croissance();
                if (nouvelleCarte.isPresent()) {
                    m_cartes[i] = nouvelleCarte;
                }
            }
        }
    }

    public int attaquer(Joueur joueur, Ligne defenseur) {
        int score = 0;
        boolean[] aDejaAttaque = new boolean[4];

        for (int i = 0; i < 4; i++) {
            if (aDejaAttaque[i]) {
                continue;
            }

            if (m_cartes[i].isEmpty()) {
                continue;
            }

            Carte attaquant = m_cartes[i].get();

            Optional<Carte> defenseurOpt = defenseur.getCarte(i);

            if (attaquant.estVolant() || defenseurOpt.isEmpty()) {
                score += attaquant.doDamage(Optional.empty());
            } else {
                Carte carteDefenseur = defenseurOpt.get();

                attaquant.doDamage(defenseurOpt);

                if (carteDefenseur.estMort()) {
                    if (carteDefenseur.estCreature()) {
                        joueur.tuerAdversaire();
                    }

                    defenseur.removeCart(i);
                }
            }

            aDejaAttaque[i] = true;

            if (attaquant.aPouvoir(Pouvoirs.COUREUR) && !attaquant.estMort()) {
                int nouvellePosition = courrir(i);

                if (nouvellePosition != i) {
                    aDejaAttaque[nouvellePosition] = true;
                }
            }
        }

        return score;
    }



    public int courrir(int position) {
        Optional<Carte> carteOpt = getCarte(position);

        if (carteOpt.isEmpty()) {
            return position;
        }

        if (position + 1 < 4 && estCaseVide(position + 1)) {
            m_cartes[position + 1] = carteOpt;
            m_cartes[position] = Optional.empty();
            return position + 1;
        }

        if (position - 1 >= 0 && estCaseVide(position - 1)) {
            m_cartes[position - 1] = carteOpt;
            m_cartes[position] = Optional.empty();
            return position - 1;
        }

        return position;
    }

    public Optional<Carte>[] getLigne(){
        return m_cartes;
    }

    public void removeCart(int place){
        if(place < 0 || place >= m_cartes.length){
            return;
        }
        m_cartes[place] = Optional.empty();
    }

    public boolean estVide(){
        return estCaseVide(0) && estCaseVide(1) && estCaseVide(2) && estCaseVide(3);
    }

    public Optional<Carte> getCarte(int position) {
        if (position >= 0 && position < m_cartes.length) {
            return m_cartes[position];
        }
        return Optional.empty();
    }
}