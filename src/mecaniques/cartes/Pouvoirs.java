package mecaniques.cartes;

public enum Pouvoirs {
    NOMBREUSES_VIES("Nombreuses Vies"),
    CROISSANCE("Croissance"),
    PUANT("Puant"),
    COUREUR("Coureur"),
    CONTACT_MORTEL("Contact Mortel"),
    PIQUES_POINTUES("Piques pointues");

    private String m_nom;

    Pouvoirs(String nm){
        m_nom = nm;
    }

    public String getNom(){
        return m_nom;
    }
}
