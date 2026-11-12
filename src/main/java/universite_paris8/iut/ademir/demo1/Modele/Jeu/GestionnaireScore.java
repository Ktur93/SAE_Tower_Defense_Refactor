package universite_paris8.iut.ademir.demo1.Modele.Jeu;

public class GestionnaireScore {

    private int rubis;

    public GestionnaireScore() {
        this.rubis = 111400;
    }

    public int getRubis() {
        return rubis;
    }

    public void ajouterRubis(int montant) {
        if (montant < 0) {
            System.out.println("Montant invalide");
        }

        rubis += montant;
    }

    public void retirerRubis(int montant) {
        if (montant < 0 || montant > rubis) {
            System.out.println("Montant invalide");
        }

        rubis -= montant;
    }

    public void recommencer() {
        rubis = 400;
    }
}