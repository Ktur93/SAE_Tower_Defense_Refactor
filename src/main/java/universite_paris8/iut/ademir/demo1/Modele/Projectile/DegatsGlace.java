package universite_paris8.iut.ademir.demo1.Modele.Projectile;

import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;

public class DegatsGlace implements EffetProjectile {
    @Override
    public void appliquer(Monstre cible, int degats) {
        cible.recevoirDegatsGlace(degats);
    }
}