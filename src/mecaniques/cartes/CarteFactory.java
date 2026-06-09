package mecaniques.cartes;

import java.util.Random;

import static mecaniques.cartes.Pouvoirs.*;

public class CarteFactory {

    private static final Random RAND = new Random();

    public static Animal[] getDeuxAnimaux() {
        Animal a1 = creerAnimalAvecPouvoir(RAND.nextInt(6));

        Animal a2;
        do {
            a2 = creerAnimalAvecPouvoir(RAND.nextInt(6));
        } while (a1.getNom().equals(a2.getNom()));

        return new Animal[]{a1, a2};
    }

    public static Animal getCarteAleatoire() {
        int chance = RAND.nextInt(100);

        if (chance < 40) {
            return new Animal("Ecureuil", 1, 0, 0, 0, false);
        }

        int indice = RAND.nextInt(13);

        if (indice < 7) {
            return creerAnimalSansPouvoir(indice);
        } else {
            return creerAnimalAvecPouvoir(indice - 7);
        }
    }

    private static Animal creerAnimalAvecPouvoir(int indice) {
        switch (indice) {
            case 0:
                return new Animal("Chat", 1, 0, 1, 0, false, NOMBREUSES_VIES);
            case 1:
                return new Animal("Louveteau", 1, 1, 1, 0, false, CROISSANCE);
            case 2:
                return new Animal("Punaise", 2, 1, 0, 2, false, PUANT);
            case 3:
                return new Animal("Elan", 4, 2, 2, 0, false, COUREUR);
            case 4:
                return new Animal("Vipère", 1, 1, 2, 0, false, CONTACT_MORTEL);
            case 5:
                return new Animal("Porc-épic", 2, 1, 1, 0, false, PIQUES_POINTUES);
            default:
                throw new IllegalArgumentException("Indice invalide");
        }
    }

    private static Animal creerAnimalSansPouvoir(int indice) {
        switch (indice) {
            case 0:
                return new Animal("Ecureuil", 1, 0, 0, 0, false);
            case 1:
                return new Animal("Grizzly", 6, 4, 3, 0, false);
            case 2:
                return new Animal("Coyote", 1, 2, 0, 4, false);
            case 3:
                return new Animal("Moineau", 2, 1, 1, 0, true);
            case 4:
                return new Animal("Hermine", 3, 1, 1, 0, false);
            case 5:
                return new Animal("Loup", 2, 3, 2, 0, false);
            case 6:
                return new Animal("Corbeau", 3, 2, 2, 0, true);
            default:
                throw new IllegalArgumentException("Indice invalide");
        }
    }
}