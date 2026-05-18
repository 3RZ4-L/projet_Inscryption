package mecaniques.affichage;

public class CreateArrow {
    private int m_width = 17;

    public String arrowUp(){
        String spaces = "";
        for (int i = 0; i < m_width/2 - 2; i++){
            spaces += " ";
        }
        return spaces + "/\\" + spaces;
    }

    public String arrowDown(){
        String spaces = "";
        for (int i = 0; i < m_width/2; i++){
            spaces += " ";
        }
        return spaces + "\\/" + spaces;
    }

    public String arrowBody(){
        String spaces = "";
        for (int i = 0; i < m_width/2; i++){
            spaces += " ";
        }
        return spaces + "||" + spaces;
    }
}
