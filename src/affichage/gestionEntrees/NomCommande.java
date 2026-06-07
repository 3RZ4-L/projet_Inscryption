package affichage.gestionEntrees;

public enum NomCommande {
    FIN,
    PIOCHER,
    PLACER,
    SACRIFIER;

    public static NomCommande fromString(String texte) {
        if (texte == null) return null;
        switch (texte) {
            case "fin":
                return FIN;
            case "piocher":
                return PIOCHER;
            case "placer":
                return PLACER;
            case "sacrifier":
                return SACRIFIER;
            default:
                return null;
        }
    }
}
