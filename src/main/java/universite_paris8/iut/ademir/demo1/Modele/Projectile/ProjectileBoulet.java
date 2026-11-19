package universite_paris8.iut.ademir.demo1.Modele.Projectile;

import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;
import universite_paris8.iut.ademir.demo1.Modele.Projectile.DegatsSansEffet.DegatsSimples;

public class ProjectileBoulet extends Projectile {
    public ProjectileBoulet(double x, double y, int degats, Monstre cible) {
        super(x, y, 20, degats, cible, new DegatsSimples(), new DeplacementDirect());
    }
}