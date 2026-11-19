package universite_paris8.iut.ademir.demo1.Modele.Projectile;

import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;
import universite_paris8.iut.ademir.demo1.Modele.Projectile.DegatsAvecEffet.DegatsPoison;

public class ProjectilePoison extends Projectile {

    public ProjectilePoison(double x, double y, int degats, Monstre cible) {
        super(x, y, 20, degats, cible, new DegatsPoison(), new DeplacementDirect());
    }
}