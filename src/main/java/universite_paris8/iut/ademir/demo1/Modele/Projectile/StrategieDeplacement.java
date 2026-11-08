package universite_paris8.iut.ademir.demo1.Modele.Projectile;

public interface StrategieDeplacement {

    void deplacer(Projectile projectile, double dx, double dy, double distance, int vitesse);
}