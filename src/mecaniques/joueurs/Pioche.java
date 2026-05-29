package mecaniques.joueurs;

import mecaniques.cartes.Animal;
import java.util.Random;
import java.util.Stack;

import static mecaniques.cartes.Pouvoirs.*;

public class Pioche {
    private Stack<Animal> m_pioche = new Stack();

    public Pioche(int n){
        melanger(n);
    }

    public void melanger(int n){
        for (int i = 0; i < n; i++){
            Random rand = new Random();
            int indice = rand.nextInt(13);
            switch(indice) {
                case 0:
                    m_pioche.push(new Animal("Chat", 1, 0, 1, 0, false, NOMBREUSES_VIES));
                    break;
                case 1:
                    m_pioche.push(new Animal("Grizzly", 6, 4, 3, 0, false));
                    break;
                case 2:
                    m_pioche.push(new Animal("Coyote", 1, 2, 0, 4, false));
                    break;
                case 3:
                    m_pioche.push(new Animal("Moineau", 2, 1, 1, 0, true));
                    break;
                case 4:
                    m_pioche.push(new Animal("Hermine", 3, 1, 1, 0, false));
                    break;
                case 5:
                    m_pioche.push(new Animal("Louveteau", 1, 1, 1, 0, false, CROISSANCE));
                    break;
                case 6:
                    m_pioche.push(new Animal("Loup", 2, 3, 2, 0, false));
                    break;
                case 7:
                    m_pioche.push(new Animal("Punaise", 2, 1, 0, 2, false, PUANT));
                    break;
                case 8:
                    m_pioche.push(new Animal("Corbeau", 3, 2, 2, 0, true));
                    break;
                case 9:
                    m_pioche.push(new Animal("Elan", 4, 2, 2, 0, false, COUREUR));
                    break;
                case 10:
                    m_pioche.push(new Animal("Vipère", 1, 1, 2, 0, false, CONTACT_MORTEL));
                    break;
                case 11:
                    m_pioche.push(new Animal("Porc-épic", 2, 1, 1, 0, false, PIQUES_POINTUES));
                    break;
                default:
                    m_pioche.push(new Animal("Ecureuil", 1, 0, 0, 0, false));
                    break;
            }
        }
    }

    public Animal piocher(){
        return m_pioche.pop();
    }

    public void ajouter(Animal carte){
        m_pioche.add(carte);
    }

    public int getNbCartes(){
        return m_pioche.size();
    }
}
