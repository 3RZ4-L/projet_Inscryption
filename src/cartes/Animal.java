package cartes;

public class Animal extends Carte{
    private int m_attaque;
    private int m_gouttes_sang;
    private int m_os;
    private boolean m_volant;

    public Animal(String nom, int pdv, int att, int gds, int os, boolean volant) {
        super(nom, pdv);
        m_attaque = att;
        m_gouttes_sang = gds;
        m_os = os;
        m_volant = volant;
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
