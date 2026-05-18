package mecaniques.affichage;

public class Affichage {
    private CreateArrow m_createArrow = new CreateArrow();
    private CreateCard m_createCard = new CreateCard();

    public void afficherLigne(String[][] cartes){
        for(int ligne = 0; ligne < 7; ligne ++){
            for (int i = 0; i < cartes.length; i++){
                System.out.print(cartes[i][ligne] + "    ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public String[] emptyCard(){
        return new String[]{
                m_createCard.emptyTopBottom(),
                m_createCard.emptyMiddle(),
                m_createCard.emptyMiddle(),
                m_createCard.emptyMiddle(),
                m_createCard.emptyMiddle(),
                m_createCard.emptyMiddle(),
                m_createCard.emptyTopBottom()
        };
    }

    public String[] coordCard(String coord){
        return new String[]{
                m_createCard.emptyTopBottom(),
                m_createCard.emptyMiddle(),
                m_createCard.emptyMiddle(),
                m_createCard.coordinate(coord),
                m_createCard.emptyMiddle(),
                m_createCard.emptyMiddle(),
                m_createCard.emptyTopBottom()
        };
    }

    public String[] animCard(String nom, String hp, String dmg, boolean volant){
        String spaces = "         |";
        return new String[]{
                m_createCard.animTopBottom(),
                m_createCard.animName(nom),
                m_createCard.separate(),
                "| HP : " + m_createCard.animHP(hp),
                "| DMG : " + m_createCard.animDMG(dmg),
                volant ? "| Volant" + spaces : m_createCard.animMiddle(),
                m_createCard.animTopBottom()
        };
    }

    public String[] affArrowUp(){
        return new String[]{
                "",
                "",
                m_createArrow.arrowUp(),
                m_createArrow.arrowBody(),
                m_createArrow.arrowBody(),
                "",
                ""
        };
    }

    public String[] affArrowDown(){
        return new String[]{

                "",
                "",
                m_createArrow.arrowBody(),
                m_createArrow.arrowBody(),
                m_createArrow.arrowDown(),
                "",
                ""
        };
    }
}
