package universite_paris8.iut.ademir.demo1.Modele.Tour;

import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;
import universite_paris8.iut.ademir.demo1.Modele.Projectile.DegatsSansEffet.DegatsLazer;
import universite_paris8.iut.ademir.demo1.Modele.Projectile.Projectile;
import universite_paris8.iut.ademir.demo1.Modele.Projectile.ProjectileLazer;

public class TourTDE extends Tour{
    private final DegatsLazer degatsLazer = new DegatsLazer();
    public TourTDE(Position position) {
        super(30, 200, 4, position, 60, 2);
    }

    @Override
    public void ameliorer() {
        this.setAtk(this.getAtk() + 15);
        this.setPorter(this.getPorter() + 1);
        this.setCadence(this.getCadence() - 3);
    }

    @Override
    protected Projectile nouveauProjectile(double x, double y, Monstre cible) {
        return new ProjectileLazer(x, y, getAtk(), cible , degatsLazer);
    }
}
