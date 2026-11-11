package universite_paris8.iut.ademir.demo1.Modele.Projectile;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;

public class ProjectileExplosion extends Projectile {

    public ProjectileExplosion(double x, double y, int degats, Monstre cible) {
        super(x, y, 20, degats, cible, new DegatsSimples(), new DeplacementDirect());
    }
}

