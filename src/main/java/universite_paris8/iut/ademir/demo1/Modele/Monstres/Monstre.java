package universite_paris8.iut.ademir.demo1.Modele.Monstres;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import java.util.ArrayList;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import universite_paris8.iut.ademir.demo1.Modele.Effets.Effet;
import universite_paris8.iut.ademir.demo1.Modele.Effets.EffetGlace;
import universite_paris8.iut.ademir.demo1.Modele.Effets.EffetPoison;

public class Monstre {
    private int recompense;
    private int indiceChemin;
    private int degat;

    private Effet effetPoison;
    private Effet effetGlace;



    private double vitesse;
    private double vitesseNormal;

    private ArrayList<Position> chemin;
    private Position depart;



    private IntegerProperty pv;
    private int pvMax;
    private DoubleProperty x;
    private DoubleProperty y;

    private String monstreID;




    private static int compteurID = 0;

    private BooleanProperty glace;



    public Monstre(int pv, double vitesse, double vitesseNormal, int recompense, int degat, ArrayList<Position> chemin) {
        this.recompense = recompense;
        this.indiceChemin = 0;
        this.degat = degat;

        this.monstreID = "monstre" + compteurID;





        this.vitesse = vitesse;
        this.vitesseNormal = vitesseNormal;


        this.chemin = chemin;
        this.depart = chemin.get(0);


        this.pv = new SimpleIntegerProperty(pv);
        this.pvMax = pv;
        this.x = new SimpleDoubleProperty(depart.getX() * 64);
        this.y = new SimpleDoubleProperty(depart.getY() * 64);


        this.monstreID = "monstre" + compteurID;

        this.glace = new SimpleBooleanProperty(false);
        this.effetPoison = new EffetPoison();
        this.effetGlace = new EffetGlace();


        compteurID++;
    }

    public void avancer() {
        if (effetPoison.isActif()) {
            effetPoison.appliquer(this);
        }

        if (effetGlace.isActif()) {
            effetGlace.appliquer(this);
        }

        if (!estArrive()) {
            Position position = chemin.get(indiceChemin);
            Position posSuivante = chemin.get(indiceChemin + 1);
            double cibleX = posSuivante.getX() * 64;
            double cibleY = posSuivante.getY() * 64;

            // si il est a gauche de la cible, on le fait avancer via sa vitesse
            if (x.get() < cibleX) {
                x.set(x.get()+this.vitesse);

                if (x.get() > cibleX) {
                    x.set(cibleX);
                }
            } else if (x.get() > cibleX) {
                x.set( x.get() - this.vitesse);

                if (x.get() < cibleX) {
                    x.set(cibleX);
                }
            }

            if (y.get() > cibleY) {
                y.set(y.get() - this.vitesse);

                if (y.get() < cibleY) {
                    y.set(cibleY);
                }
            } else if (y.get() < cibleY) {
                y.set(y.get() + this.vitesse);

                if (y.get() > cibleY) {
                    y.set(cibleY);
                }
            }


            if (x.get() == posSuivante.getX() * 64 && y.get() == posSuivante.getY() * 64) {
                this.indiceChemin++;
            }
        }
    }



    public boolean estArrive() {
        return this.indiceChemin >= this.chemin.size() - 1;
    }

    public Position getPosition() {
        return this.chemin.get(this.indiceChemin);
    }

    public int getPv() {
        return this.pv.intValue();
    }

    public void setPv(int nouveauPv) {
        this.pv.setValue(nouveauPv);
    }

    public double getVitesse() {
        return this.vitesse;
    }

    public void setVitesse(double vitesse) {
        this.vitesse = vitesse;
    }

    public int getRecompense() {
        return this.recompense;
    }

    public int getDegat() {
        return this.degat;
    }

    public boolean getMonstreGlacee() {
        return this.estGlace();
    }


    public double getVitesseNormal() {
        return this.vitesseNormal;
    }







    public void recevoirDegats(int degats) {
        pv.setValue(getPv() - degats);
    }

    public void recevoirDegatsGlace(int degats) {
        pv.setValue(getPv() - degats);
        setGlace(true);
        this.effetGlace.activer();
    }

    public void recevoirDegatsPoison(int degats) {
        effetPoison.activer();
    }


    public double getX () {
        return this.x.doubleValue();
    }

    public double getY() {
        return this.y.doubleValue();
    }

    public void setX (double newX) {
        this.x.setValue(newX);
    }

    public void setY (double newY) {
        this.y.setValue(newY);
    }

    public void setRecompense(int recompense) {
        this.recompense = recompense;
    }

    public DoubleProperty xProperty() {
        return x;
    }

    public DoubleProperty yProperty() {
        return y;
    }

    public IntegerProperty pvProperty(){
        return pv;
    }

    public int getPvMax() {
        return pvMax;
    }

    public String getMonstreID() {
        return monstreID;
    }

    public double getDernierePositionX() {
        return this.chemin.get(this.chemin.size() - 1).getX();
    }

    public double getDernierePositionY(){
        return this.chemin.get(this.chemin.size() - 1).getY();
    }

    public boolean estMort() {
        return this.pv.intValue() <= 0;
    }

    public boolean estADestination() {
        return (getDernierePositionX() == this.getPosition().getX() && getDernierePositionY() == this.getPosition().getY());
    }

    public BooleanProperty glaceProperty() {
        return glace;
    }

    public boolean estGlace() {
        return glace.get();
    }

    public void setGlace(boolean estGlaceOuPas) {
        glace.set(estGlaceOuPas);
    }



}

