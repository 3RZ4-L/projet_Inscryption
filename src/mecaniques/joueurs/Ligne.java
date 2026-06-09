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

    public boolean estSacrifiable(int position){
        if(!estCaseVide(position) && m_cartes[position].get().estCreature())
            return true;
        return false;
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

        return Optional.of(pouvoir);
    }

    public void croissance(){
        for (int i = 0; i < 4; i++) {
            if (m_cartes[i].isPresent()) {
                Carte carte = m_cartes[i].get();
                Optional<Carte> nouvelleCarte = carte.croissance();
                if (nouvelleCarte.isPresent()) {
                    m_cartes[i] = nouvelleCarte;
                }
            }
        }
    }

    public int attaquer(Joueur joueur, Ligne defenseur){
        Messages message = new Messages();
        int score = 0;
        for (int i = 0; i < 4; i++) {
            if (m_cartes[i].isEmpty()) continue;

            Carte att = m_cartes[i].get();
            Optional<Carte> def = defenseur.m_cartes[i];

            boolean isDirectAttack = false;
            if (att.estVolant()) {
                isDirectAttack = true;
            } else if (def.isEmpty()) {
                isDirectAttack = true;
            } else if (def.get().estMort()) {
                isDirectAttack = true;
            }

            int dmg = att.doDamage(def);

            if (isDirectAttack) {
                score += dmg;
                message.attackDirect(att, dmg);
            } else if (def.isPresent()) {
                message.attackCart(att, def.get());
            }

            if (def.isPresent()) {
                if (def.get().estMort()) {
                    message.died(def.get());
                    if (def.get().estCreature()) {
                        joueur.tuerAdversaire();
                    }
                    defenseur.m_cartes[i] = Optional.empty();
                }
                this.courrir(i);
            }
        }
        return score;
    }

    public boolean courrir(int position){
        if (position + 1 < 4 && estCaseVide(position+1)){
            placerCarte(m_cartes[position].get(), position+1);
            m_cartes[position] = Optional.empty();
            return true;
        } else if (position - 1 >= 0 && estCaseVide(position-1)) {
            placerCarte(m_cartes[position].get(), position-1);
            m_cartes[position] = Optional.empty();
            return true;
        }
        return false;
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