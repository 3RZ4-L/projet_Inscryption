package mecaniques.cartes;

import java.util.Random;

import static mecaniques.cartes.Pouvoirs.*;

public class CarteFactory {
    private static final Random RAND = new Random();

    private static final Animal[] ANIMAUX_AVEC_POUVOIR = {
            new Animal("Chat", 1, 0, 1, 0, false, NOMBREUSES_VIES),
            new Animal("Louveteau", 1, 1, 1, 0, false, CROISSANCE),
            new Animal("Punaise", 2, 1, 0, 2, false, PUANT),
            new Animal("Elan", 4, 2, 2, 0, false, COUREUR),
            new Animal("Vipère", 1, 1, 2, 0, false, CONTACT_MORTEL),
            new Animal("Porc-épic", 2, 1, 1, 0, false, PIQUES_POINTUES)
    };

    private static final Animal[] ANIMAUX_SANS_POUVOIR = {
            new Animal("Ecureuil", 1, 0, 0, 0, false),
            new Animal("Grizzly", 6, 4, 3, 0, false),
            new Animal("Coyote", 1, 2, 0, 4, false),
            new Animal("Moineau", 2, 1, 1, 0, true),
            new Animal("Hermine", 3, 1, 1, 0, false),
            new Animal("Loup", 2, 3, 2, 0, false),
            new Animal("Corbeau", 3, 2, 2, 0, true)
    };

    public static Animal[] getDeuxAnimaux() {
        int index1 = RAND.nextInt(ANIMAUX_AVEC_POUVOIR.length);
        int index2;
        do {
            index2 = RAND.nextInt(ANIMAUX_AVEC_POUVOIR.length);
        } while (index2 == index1);

        return new Animal[]{ANIMAUX_AVEC_POUVOIR[index1], ANIMAUX_AVEC_POUVOIR[index2]};
    }

    public static Animal getCarteAleatoire() {
        int chance = RAND.nextInt(100);

        if (chance < 40) {
            return new Animal("Ecureuil", 1, 0, 0, 0, false);
        }
        int indice = RAND.nextInt(ANIMAUX_SANS_POUVOIR.length + ANIMAUX_AVEC_POUVOIR.length);

        if (indice < ANIMAUX_SANS_POUVOIR.length) {
            return ANIMAUX_SANS_POUVOIR[indice];
        } else {
            return ANIMAUX_AVEC_POUVOIR[indice - ANIMAUX_SANS_POUVOIR.length];
        }
    }
}