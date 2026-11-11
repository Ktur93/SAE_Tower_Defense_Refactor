package universite_paris8.iut.ademir.demo1.Modele.Projectile;

import javafx.collections.ObservableList;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;

public class DegatsExplosion {

    private static final double RAYON = 100;

    public void exploser(double x, double y, int degats, ObservableList<Monstre> monstres) {
        for (Monstre monstre : monstres) {
            double dx = monstre.getX() - x;
            double dy = monstre.getY() - y;
            double distance = Math.sqrt(dx * dx + dy * dy);
            if (distance <= RAYON) {
                monstre.recevoirDegats(degats);
            }
        }
    }
}