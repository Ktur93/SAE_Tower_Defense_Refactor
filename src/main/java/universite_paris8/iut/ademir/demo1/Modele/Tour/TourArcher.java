package universite_paris8.iut.ademir.demo1.Modele.Tour;

import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;
import universite_paris8.iut.ademir.demo1.Modele.Projectile.Projectile;
import universite_paris8.iut.ademir.demo1.Modele.Projectile.ProjectileFleche;
import universite_paris8.iut.ademir.demo1.Modele.Tour.Ciblage.CiblagePlusFort;

public class TourArcher extends Tour {

    public TourArcher(Position position){
        super(30, 50, 5, position,50 , 5); // 1 seconde

        this.setStrategieCiblage(new CiblagePlusFort());
    }

    @Override
    public void ameliorer() { // test tz
        this.setAtk(this.getAtk() + 10);
        this.setPorter(this.getPorter() + 1);
        this.setCadence(this.getCadence() - 2);
    }

    @Override
    protected Projectile nouveauProjectile(double x, double y, Monstre cible) {
        return new ProjectileFleche(x, y, getAtk(), cible);
    }
}
