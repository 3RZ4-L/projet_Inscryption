package mecaniques.joueurs;

import cartes.Animal;
import java.util.Random;
import java.util.Stack;

public class Pioche {
    private Stack m_pioche;

    public Pioche(int n){
        melanger(n);
    }

    public void melanger(int n){
        for (int i = 0; i < n; i++){
            Random rand = new Random();
            int indice = rand.nextInt();
            switch(indice) {
                case 0:
                    m_pioche.push(new Animal("Chat", 0, 1, 1, 0, false));
                    break;
                case 1:
                    m_pioche.push(new Animal("Grizzly", 4, 6, 3, 0, false));
                    break;
                case 2:
                    m_pioche.push(new Animal("Coyote", 2, 1, 0, 4, false));
                    break;
                case 3:
                    m_pioche.push(new Animal("Moineau", 1, 2, 1, 0, true));
                    break;
                case 4:
                    m_pioche.push(new Animal("Hermine", 1, 3, 1, 0, false));
                    break;
                case 5:
                    m_pioche.push(new Animal("Louveteau", 1, 1, 1, 0, false));
                    break;
                case 6:
                    m_pioche.push(new Animal("Loup", 3, 2, 2, 0, false));
                    break;
                case 7:
                    m_pioche.push(new Animal("Punaise", 1, 2, 0, 2, false));
                    break;
                case 8:
                    m_pioche.push(new Animal("Corbeau", 2, 3, 2, 0, true));
                    break;
                default:
                    m_pioche.push(new Animal("Ecureuil", 0, 1, 0, 0, false));
                    break;
            }
        }
    }

    public Animal piocher(){
        return (Animal)m_pioche.pop();
    }
}
