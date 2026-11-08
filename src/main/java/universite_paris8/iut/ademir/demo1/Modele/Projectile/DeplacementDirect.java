package universite_paris8.iut.ademir.demo1.Modele.Projectile;

public class DeplacementDirect implements StrategieDeplacement {

    @Override
    public void deplacer(Projectile projectile, double dx, double dy, double distance, int vitesse){
        projectile.setX(projectile.getX() + vitesse * dx / distance);
        projectile.setY(projectile.getY() + vitesse * dy / distance);
    }
}
