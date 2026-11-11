package universite_paris8.iut.ademir.demo1.Modele.Tour;

import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;
import universite_paris8.iut.ademir.demo1.Modele.Projectile.Projectile;
import universite_paris8.iut.ademir.demo1.Modele.Projectile.ProjectileFeu;

public class TourFeu extends Tour{
    public TourFeu(Position position){
        super(25, 175, 4, position, 50, 3);
    }

    @Override
    public void ameliorer(){
        this.setAtk(this.getAtk() + 10);
        this.setPorter(this.getPorter() + 1);
        this.setCadence(this.getCadence() - 5);

    }

    @Override
    protected Projectile nouveauProjectile(double x, double y, Monstre cible){
        return new ProjectileFeu(x, y, getAtk(), cible);
    }
}
