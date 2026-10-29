# Inscryption — Jeu de cartes tactique

## Présentation

Application inspirée du jeu *Inscryption* : un jeu de cartes tactique en trois manches où le joueur affronte une IA dans une cabane isolée en pleine forêt. Le premier à obtenir un écart de 5 points en sa faveur remporte la manche.

Le projet est développé en Java dans un cadre pédagogique et suit une architecture orientée objet testée et documentée.

---

## Fonctionnalités

### Plateau et déroulement
- Plateau composé de deux lignes de 4 emplacements : le joueur place ses cartes sur la ligne inférieure, l'adversaire sur la ligne supérieure.
- Balance de score visible à tout moment ; la manche se termine dès qu'un écart de 5 points est atteint.
- L'adversaire annonce ses cartes du tour suivant sur une ligne dédiée au-dessus du plateau.
- Le joueur commence avec 4 cartes en main et peut piocher une carte par tour.
- Des cartes obstacles (Rocher, Sapin) peuvent occuper des emplacements et doivent être détruites avant tout placement.

### Cartes animaux
Chaque carte possède des points d'attaque, des points de vie, un coût en gouttes de sang (sacrifices de cartes alliées), un coût en os (cartes alliées déjà mortes) et un attribut « volant ».

| Nom | Attaque | PV | Sang | Os | Volant |
|---|---|---|---|---|---|
| Chat | 0 | 1 | 1 | 0 | non |
| Grizzly | 4 | 6 | 3 | 0 | non |
| Coyote | 2 | 1 | 0 | 4 | non |
| Moineau | 1 | 2 | 1 | 0 | oui |
| Corbeau | 2 | 3 | 2 | 0 | oui |
| Écureuil | 0 | 1 | 0 | 0 | non |
| Hermine | 1 | 3 | 1 | 0 | non |
| Louveteau | 1 | 1 | 1 | 0 | non |
| Loup | 3 | 2 | 2 | 0 | non |
| Punaise | 1 | 2 | 0 | 2 | non |

### Combat
- À la fin de chaque tour, toutes les cartes animales attaquent.
- Une carte attaquée perd en PV le nombre de points d'attaque de l'attaquant.
- Sans adversaire en face, les points d'attaque s'ajoutent directement au score.
- Les cartes volantes attaquent directement le score, indépendamment de la carte en face.
- Un récapitulatif des dégâts infligés est affiché à la fin de chaque tour.

### Progression
- Pioche initiale de 15 cartes composée majoritairement d'écureuils.
- Trois manches à remporter pour gagner la partie.
- À l'issue de la deuxième manche, le joueur choisit une carte supplémentaire parmi deux propositions.

### Adversaire
L'IA est entièrement déterministe : ses actions sont planifiées à l'avance afin de garantir la reproductibilité des tests et faciliter le débogage. Aucune stratégie aléatoire n'est utilisée.

---

## Utilisation

L'interface en ligne de commande affiche en permanence :
- le plateau (ligne adverse, ligne joueur, obstacles, score) ;
- la main du joueur avec les caractéristiques de chaque carte ;
- le nombre de cartes restantes dans la pioche ;
- les actions disponibles.

### Commandes
| Commande | Description |
|---|---|
| `fin` | Terminer le tour |
| `piocher` | Piocher une carte |
| `placer <numéro_carte> <position>` | Placer une carte sur un emplacement (ex. `placer 2 B1`) |

Les saisies invalides sont refusées avec un message explicite et redemandées à l'utilisateur.

---

## Structure du dépôt

```
├── README.md
├── .gitignore
├── deps/            # Dépendances de test
├── out/             # Classes compilées
├── src/             # Code source
├── tests/           # Tests unitaires
└── uml/             # Diagrammes de classes (semaine<N>.puml)
```

---

## Organisation du développement

- Rendus hebdomadaires sous forme de branches `rendu<numéro>` contenant un programme compilable et un diagramme de classes.

---

## Licence et crédits

Projet pédagogique inspiré de *Inscryption* (Daniel Mullins Games). Le code est produit dans un cadre universitaire et n'est pas affilié à l'éditeur du jeu original.
