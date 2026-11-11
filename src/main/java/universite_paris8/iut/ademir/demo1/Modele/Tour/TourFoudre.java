package universite_paris8.iut.ademir.demo1.Modele.Tour;

import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;
import universite_paris8.iut.ademir.demo1.Modele.Projectile.Projectile;
import universite_paris8.iut.ademir.demo1.Modele.Projectile.ProjectileFoudre;

public class TourFoudre extends Tour {

    public TourFoudre(Position position) {
        super(30, 200, 4, position, 55, 2);
    }

    @Override
    public void ameliorer() {
        this.setAtk(this.getAtk() + 15);
        this.setPorter(this.getPorter() + 1);
        this.setCadence(this.getCadence() - 3);
    }

    @Override
    protected Projectile nouveauProjectile(double x, double y, Monstre cible) {
        return new ProjectileFoudre(x, y, getAtk(), cible);
    }
}