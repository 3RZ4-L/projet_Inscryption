package cartes;

public class Animal extends Carte{
    Integer m_attaque;
    Integer m_gouttes_sang;
    Integer m_os;

    Animal(String nom, Integer pdv, Integer att, Integer gds, Integer os) {
        super(nom, pdv);
        m_attaque = att;
        m_gouttes_sang = gds;
        m_os = os;
    }

    /**
     * @param adversaire : animal attaqué
     * @return si l'adversaire est mort (pv = 0)
     */
    public Boolean attaque(Animal adversaire){
        if (adversaire.m_points_de_vie > this.m_attaque) {
            adversaire.m_points_de_vie -= this.m_attaque;
            return false;
        }
        else{
            adversaire.m_points_de_vie = 0;
        }
        return true;
    }

    public Boolean attaque(Obstacle obstacle){
        if (obstacle.m_points_de_vie > this.m_attaque) {
            obstacle.m_points_de_vie -= this.m_attaque;
            return false;
        }
        else{
            obstacle.m_points_de_vie = 0;
        }
        return true;
    }
}
