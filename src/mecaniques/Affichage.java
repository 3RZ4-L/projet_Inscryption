package mecaniques;

import java.util.ArrayList;

public class Affichage {


    public String afficherEmptyPlace(){
        return("*************\n" +
                "*           *\n" +
                "*           *\n" +
                "*           *\n" +
                "*           *\n" +
                "*           *\n" +
                "*************");
    }

    public String afficherCarteAnimal(/*nom, hp, dmg, extra*/){
        return("*-----------*\n" +
                "|   name    |\n" +
                "|-----------|\n" +
                "| HP :      |\n" +
                "| DMG :     |\n" +
                "| EXT :     |\n" +
                "*-----------*");
    }

    public String afficherCarteCoordonee(){
        return("*************\n" +
                "*           *\n" +
                "*           *\n" +
                "*     O2    *\n" +
                "*           *\n" +
                "*           *\n" +
                "*************");
    }

    public String afficherArrow(){
        return("||\n" +
                "\\" + "/");
    }

    public String afficherGrid(){
        String[][] grid = new String[4][3];
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                if(i == 1 || i == 3){
                    grid[i][j] = afficherArrow();
                }
            }
        }
    }

    public String afficherDeplacement(/* someone */){
        /*
        * if(someone is COMP){
        *
        * }
        * */
    }
}
