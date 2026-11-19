package universite_paris8.iut.ademir.demo1.Modele.Projectile;

import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;

public interface EffetProjectile {
    void appliquer(Monstre cible, int degats);
}
