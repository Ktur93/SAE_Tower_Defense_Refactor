package universite_paris8.iut.ademir.demo1.Modele.Tour.Ciblage;

public enum TypeCiblage {

    PREMIER("Premier"),
    PLUS_FAIBLE("Plus faible"),
    PLUS_FORT("Plus fort");

    private final String nom;

    TypeCiblage(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }
}