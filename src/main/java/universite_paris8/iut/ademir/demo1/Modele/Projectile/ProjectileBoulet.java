package universite_paris8.iut.ademir.demo1.Modele.Projectile;

import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;

public class ProjectileBoulet extends Projectile {
    public ProjectileBoulet(double x, double y, int degats, Monstre cible) {
        super(x, y, 20, degats, cible, new DegatsSimples(), new DeplacementDirect());
    }
}