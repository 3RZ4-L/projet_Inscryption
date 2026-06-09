package mecaniques.joueurs;

import mecaniques.cartes.Animal;
import mecaniques.cartes.CarteFactory;

import java.util.Optional;
import java.util.Stack;

public class Pioche {
    private Stack<Animal> m_pioche = new Stack();

    public Pioche(int n){
        for (int i = 0; i < n; i++){
            m_pioche.push(CarteFactory.getCarteAleatoire());
        }
    }

    public Optional<Animal> piocher(){
        if(m_pioche.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(m_pioche.pop());
    }
    public void ajouter(Animal carte){
        m_pioche.add(carte);
    }
    public int getNbCartes(){
        return m_pioche.size();
    }
}
