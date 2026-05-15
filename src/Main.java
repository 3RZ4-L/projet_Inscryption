import mecaniques.Affichage;

public class Main
{
    public static void main(String[] args)
    {
        Affichage aff = new Affichage();
        String[][] cartes = {
                aff.emptyCard(),
                aff.coordCard("O2"),
                aff.animCard("Bobolo", "20", "10", false),
                aff.animCard("Bob", "2", "4", true),
        };
        aff.afficherLigne(cartes);
    }
}
