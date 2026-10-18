package universite_paris8.iut.ademir.demo1.Modele.Tour;

import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;
import universite_paris8.iut.ademir.demo1.Modele.Projectile.Projectile;
import universite_paris8.iut.ademir.demo1.Modele.Projectile.ProjectilePoison;

public class TourPoison extends Tour{

    public TourPoison(Position position){
        super(30, 200, 4, position,50  , 2); // 0,5 seconde
    }

    @Override
    public void ameliorer() {
        this.setAtk(this.getAtk() + 20);
        this.setPorter(this.getPorter() + 1);
        this.setCadence(this.getCadence() - 5);
    }

    @Override
    protected Projectile nouveauProjectile(double x, double y, Monstre cible) {
        return new ProjectilePoison(x, y, getAtk(),cible);
    }

}
