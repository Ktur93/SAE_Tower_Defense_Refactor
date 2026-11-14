package universite_paris8.iut.ademir.demo1.Modele.Projectile.DegatsAvecEffet;

import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;
import universite_paris8.iut.ademir.demo1.Modele.Projectile.EffetProjectile;

public class DegatsGlace implements EffetProjectile {
    @Override
    public void appliquer(Monstre cible, int degats) {
        cible.recevoirDegatsGlace(degats);
    }
}