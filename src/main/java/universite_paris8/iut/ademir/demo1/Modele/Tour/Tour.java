package universite_paris8.iut.ademir.demo1.Modele.Tour;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;
import universite_paris8.iut.ademir.demo1.Modele.Projectile.Projectile;


public abstract class Tour {

    private int atk;
    private int prix;
    private int porter;
    private int nivMax;


    private long dernierTir;
    private long cadence;

    private Position position;

    private IntegerProperty nivT;

    private StrategieCiblage strategieCiblage;


    public Tour(int attaque, int prix, int porter, Position position, long cadence , int nivMax) {
        this.atk = attaque;
        this.prix = prix;
        this.porter = porter;
        this.nivMax = nivMax;

        this.dernierTir = 0;
        this.cadence = cadence;


        this.position = position;

        this.nivT = new SimpleIntegerProperty(1);

        this.strategieCiblage = new CiblagePremier();
    }


    public Projectile attaquer(ObservableList<Monstre> monstres, int compteur) {

        if (compteur - dernierTir >= cadence) {
            Monstre cible = strategieCiblage.choisirCible(monstres, this);

            if (cible != null) {
                dernierTir = compteur;
                return creerProjectile(cible);
            }
        }

        return null;
    }


    protected boolean estAPorter(Monstre monstre) {
        int x = position.getX() - monstre.getPosition().getX();
        int y = position.getY() - monstre.getPosition().getY();
        int CalculePorter = (x * x + y * y);
        if (CalculePorter <= getPorter() * getPorter()){
            return true;
        }
        return false;
    }

    public abstract void ameliorer();
    public Projectile creerProjectile(Monstre cible) {
        double x = getPosition().getX() * 64;
        double y = getPosition().getY() * 64;
        return nouveauProjectile(x, y, cible);
    }

    protected abstract Projectile nouveauProjectile(double x, double y, Monstre cible);




    public int getPrix() {
        return prix;
    }
    public void setPrix(int prix){
        this.prix = prix;
    }

    public int getAtk(){
        return this.atk;
    }
    public void setAtk(int atk){
        this.atk = atk;
    }

    public int getPorter() {
        return porter;
    }
    public void setPorter(int portee) {
        this.porter = portee;
    }

    public long getCadence(){
        return cadence;
    }
    public void setCadence(long cadence){
        this.cadence = cadence;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position pos){
        this.position = pos;
    }

    public int getNivT() {
        return nivT.getValue();
    }

    public void setNivT(int nivT) {
        this.nivT.setValue(nivT);
    }

    public IntegerProperty nivTProperty() {
        return nivT;
    }

    public int getNivMax() {
        return nivMax;
    }

    public void setNivMax(int nivMax) {
        this.nivMax = nivMax;
    }

    public void setStrategieCiblage(StrategieCiblage strategieCiblage) {
        this.strategieCiblage = strategieCiblage;
    }

}
