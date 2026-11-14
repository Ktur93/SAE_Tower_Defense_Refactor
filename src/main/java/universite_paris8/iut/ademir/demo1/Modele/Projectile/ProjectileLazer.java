package universite_paris8.iut.ademir.demo1.Modele.Projectile;

import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;
import universite_paris8.iut.ademir.demo1.Modele.Projectile.DegatsSansEffet.DegatsLazer;

public class ProjectileLazer extends Projectile {

    private final DegatsLazer degatsLazer;

    public ProjectileLazer(double x, double y, int degats, Monstre cible, DegatsLazer degatsLazer) {
        super(x, y, 20, degats, cible, null, new DeplacementDirect());
        this.degatsLazer = degatsLazer;
    }

    @Override
    public void toucherCible() {
        degatsLazer.appliquer(getCible());
    }
}