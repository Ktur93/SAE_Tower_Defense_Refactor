package universite_paris8.iut.ademir.demo1.Modele.Projectile;

import javafx.collections.ObservableList;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;
import universite_paris8.iut.ademir.demo1.Modele.Projectile.DegatsSansEffet.DegatsExplosion;
import universite_paris8.iut.ademir.demo1.Modele.Projectile.DegatsSansEffet.DegatsSimples;

public class ProjectileExplosion extends Projectile {

    private final DegatsExplosion degatsExplosion;
    private ObservableList<Monstre> monstres;

    public ProjectileExplosion(double x, double y, int degats, Monstre cible) {
        super(x, y, 20, degats, cible, new DegatsSimples(), new DeplacementDirect());
        this.degatsExplosion = new DegatsExplosion();
    }

    public void setMonstres(ObservableList<Monstre> monstres) {
        this.monstres = monstres;
    }

    @Override
    public void toucherCible() {
        if (monstres != null) {
            degatsExplosion.exploser(getCible().getX(), getCible().getY(), getDegats(), monstres);
        }
    }
}

