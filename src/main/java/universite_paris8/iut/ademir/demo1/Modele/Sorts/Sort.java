package universite_paris8.iut.ademir.demo1.Modele.Sorts;

public enum Sort {

    BOULE_FEU(50, 40, 100),
    BOULE_NEIGE(35, 15, 100);

    private final int cout;
    private final int degats;
    private final double rayon;

    Sort(int cout, int degats, double rayon) {
        this.cout = cout;
        this.degats = degats;
        this.rayon = rayon;
    }

    public int getCout() {
        return cout;
    }

    public int getDegats() {
        return degats;
    }

    public double getRayon() {
        return rayon;
    }
}