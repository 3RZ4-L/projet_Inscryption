package mecaniques;

import java.util.ArrayList;

public class Affichage {
    private int m_width = 17;

    public String emptyTopBottom(){
        String res = "";
        for(int i = 0; i <= m_width; i++){
            res+="*";
        }
        return res;
    }

    public String emptyMiddle(){
        String res = "*";
        for(int i = 0; i < m_width-1; i++){
            res+=" ";
        }
        res+="*";
        return res;
    }

    public String coordinate(String coord){
        if(coord.length() == 2){
            String res = "*";
            for(int i = 0; i < m_width-3; i++){
                switch(i){
                    case 6:
                        res += coord.charAt(0);
                        break;
                    case 7:
                        res += coord.charAt(1);
                        break;
                }
                res+=" ";
            }
            res+="*";
            return res;
        }
        return"";
    }

    public String animTopBottom(){
        String res = "*";
        for(int i = 0; i < m_width-1; i++){
            res+="-";
        }
        res+="*";
        return res;
    }

    public String separate(){
        String res = "|";
        for(int i = 0; i < m_width-1; i++){
            res+="-";
        }
        res+="|";
        return res;
    }

    public String animMiddle(){
        String res = "|";
        for(int i = 0; i < m_width-1; i++){
            res+=" ";
        }
        res+="|";
        return res;
    }

    public String animName(String nom){
        String res = "| ";
        //if(nom.length() < m_width - 6){
            res+=nom;
            for(int i = 0; i < m_width-2-nom.length(); i++){
                res+=" ";
            }
            res+="|";
            return res;
        /*}
        return"bruh";*/
    }

    public String animHP(String hp){
        String res = "";
        res+=hp;
        for(int i = 0; i < m_width-7-hp.length(); i++){
            res+=" ";
        }
        res+="|";
        return res;
    }

    public String animDMG(String dmg){
        String res = "";
        res+=dmg;
        for(int i = 0; i < m_width-8-dmg.length(); i++){
            res+=" ";
        }
        res+="|";
        return res;
    }

    public String[] emptyCard(){
        return new String[]{
                emptyTopBottom(),
                emptyMiddle(),
                emptyMiddle(),
                emptyMiddle(),
                emptyMiddle(),
                emptyMiddle(),
                emptyTopBottom()
        };
    }

    public String[] coordCard(String coord){
        return new String[]{
                emptyTopBottom(),
                emptyMiddle(),
                emptyMiddle(),
                coordinate(coord),
                emptyMiddle(),
                emptyMiddle(),
                emptyTopBottom()
        };
    }

    public String[] animCard(String nom, String hp, String dmg, boolean volant){
        String spaces = "         |";
        return new String[]{
                animTopBottom(),
                animName(nom),
                separate(),
                "| HP : " + animHP(hp),
                "| DMG : " + animDMG(dmg),
                volant ? "| Volant" + spaces : animMiddle(),
                animTopBottom()
        };
    }

    public void afficherLigne(String[][] cartes){
        for(int ligne = 0; ligne < 7; ligne ++){
            for (int i = 0; i < cartes.length; i++){
                System.out.print(cartes[i][ligne] + "    ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
