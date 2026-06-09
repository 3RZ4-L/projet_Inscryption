package mecaniques.joueurs;

import mecaniques.cartes.Pouvoirs;

import java.util.Optional;

public class Humain extends Joueur{
    public Humain(){
        super();
    }


    public boolean peutSacrifier(int position){
        if (position >= 4 || position < 0)
            return false;
        return m_ligne.estSacrifiable(position);
    }

    public Optional<Pouvoirs> pierreDeSacrifice(int position){
        if (peutSacrifier(position)) {
            Optional<Pouvoirs> pouvoir = m_ligne.pierreDeSacrifice(position);
            if (pouvoir.isEmpty()) {
                m_os++;
                m_goutteSang++;
                return Optional.empty();
            }
            return pouvoir;
        }
        return Optional.empty();
    }

}
