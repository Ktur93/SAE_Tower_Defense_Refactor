package universite_paris8.iut.ademir.demo1.Modele.Monstres;

public enum TypeMonstre {

    ZOMBIE("zombie"),
    ARAIGNEE("araignee"),
    SQUELETTE("squelette"),
    PILLAGER("pillager"),
    CHAUVE_SOURIS("chauveSouris"),
    BOSS("boss");

    private final String nom;

    TypeMonstre(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }
}