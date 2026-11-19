package universite_paris8.iut.ademir.demo1.Modele.Projectile;

import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public abstract class Projectile {
    private StrategieDeplacement strategieDeplacement;
    private EffetProjectile effet;
    private DoubleProperty xProperty;
    private DoubleProperty yProperty;
    private int vitesse;
    private int degats;
    private Monstre cible;

    public Projectile(double x, double y, int vitesse, int degats, Monstre cible, EffetProjectile effet, StrategieDeplacement strategieDeplacement) {
        this.xProperty = new SimpleDoubleProperty(x);
        this.yProperty = new SimpleDoubleProperty(y);
        this.vitesse = vitesse;
        this.degats = degats;
        this.cible = cible;
        this.effet = effet;
        this.strategieDeplacement = strategieDeplacement;
    }

    public int getDegats() {
        return degats;
    }

    public Monstre getCible() {
        return cible;
    }

    private boolean cibleInvalide(){
        return cible == null || cible.estMort();
    }
    private double calculerDistance(double dx, double dy) {
        return Math.sqrt(dx * dx + dy * dy);
    }
    private boolean cibleAtteinte(double distance) {
        return distance <= vitesse;
    }




    public boolean avancer() {
        if (cibleInvalide()) {
            return true;
        }

        double dx = cible.getX() - getX();
        double dy = cible.getY() - getY();

        double distance = calculerDistance(dx, dy);

        if (cibleAtteinte(distance)) {
            toucherCible();
            return true;
        }

        strategieDeplacement.deplacer(this,dx,dy,distance,vitesse);

        return false;
    }

    public double getX() {
        return xProperty.getValue();
    }

    public double getY() {
        return yProperty.getValue();
    }


    public void setY(double y) {
        yProperty.setValue(y);
    }

    public void setX(double x) {
        xProperty.setValue(x);
    }

    public DoubleProperty xProperty() {
        return xProperty;
    }

    public DoubleProperty yProperty() {
        return yProperty;
    }

    public void toucherCible() {
        effet.appliquer(cible, degats);
    }
}