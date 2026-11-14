package universite_paris8.iut.ademir.demo1.Modele.Projectile;

import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;
import universite_paris8.iut.ademir.demo1.Modele.Projectile.DegatsAvecEffet.DegatsGlace;

public class ProjectileGlace extends Projectile {

    public ProjectileGlace(double x, double y, int degats, Monstre cible) {
        super(x, y, 20, degats, cible, new DegatsGlace(), new DeplacementDirect());
    }
}