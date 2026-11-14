package universite_paris8.iut.ademir.demo1.Modele.Projectile;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;
public class ProjectileFeu extends Projectile{
    public ProjectileFeu(double x, double y, int degats, Monstre cible){
        super(x, y, 20, degats, cible, new DegatsFeu(), new DeplacementDirect());
    }
}
